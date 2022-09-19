package site.workforus.forus.commute.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import oracle.jdbc.proxy.annotation.Post;
import site.workforus.forus.commute.model.CommuteDTO;
import site.workforus.forus.commute.service.CommuteService;


@Controller
@RequestMapping(value="/work")
public class CommuteController{
	
	private static final Logger logger = LoggerFactory.getLogger(CommuteController.class);

	 @Autowired
	 private CommuteService service;
	
	// 근태 페이지 조회
	@RequestMapping(value="", method=RequestMethod.GET)
	public String getData(Model model) throws Exception { 	

		// 테스트용 empId값
			String empId = "A2022100";
		// int commuteNo = 100; 
		/*
		 * empId가 일치하고 commuteDt가 있는지 체크
		 * 1. commuteDt가 일치하는게 없다면 -> 출근하면됨
		 * 2. commuteDt가 일치하는게 있다면 
		 * 	  a. commuteTime이 나타나야함 
		 * 	  b. 출근버튼이 다시 눌리면 안됨
		 */
		
		// 근태내역 정보 조회
		// Employee loginData = (Employee)session.getAttribute("loginData");
		// String empId = loginData.getEmpId(); 
		
		// 오늘날짜랑, 사원id -> 오늘날짜에 출근기록이 있음
		CommuteDTO data = service.selectData(empId);
		
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1; 
        int day = cal.get(Calendar.DATE);
//        int dayOfmonth = cal.get(Ca	lendar.DAY_OF_MONTH);
//        int dayOfweek = cal.get(Calendar.DAY_OF_WEEK);
        
        service.updateGetoff(empId);
        
		// 금일 출근기록이 있음
		if(data != null) {
			// db에 저장된 date타입에서 시간만 잘라냄
			data.setCommuteTime(data.getCommuteTime().substring(11));
			// 퇴근기록이 있음
			if(data.getGetoffTime() != null) {
				data.setGetoffTime(data.getGetoffTime().substring(11));
				data.setWorkTime(data.getWorkTime().substring(11));
				data.setAddedTime(data.getAddedTime().replace("1970-01-01", year + "-" + month + "-" + day));
				data.setAddedTime(data.getAddedTime().substring(11));
				// data.setWeekAddtime(data.getWeekAddtime().substring(11));
				// String weektime = _calculate(data.getWeekWorktime());
				// data.setWeekWorktime(data.getWeekWorktime().substring(11));
				// model.addAttribute("weektime", weektime);
			}
		}
		
		String remainTime = "00:00:00";
		long progress = 0;
		if(data == null || (data.getCommuteTime() != null && data.getGetoffTime() == null)) {
			if(service.beforeSelect(empId) != null) {
				CommuteDTO temp = service.beforeSelect(empId); // 이번주에 마지막 출근기록 DTO
				if(temp.getGetoffTime()!= null) {
					String tempTime = (service.calculate(temp.getWeekWorktime()));
					remainTime = service.remainTime(temp.getWeekWorktime());
					progress = service.progress(temp.getWeekWorktime());	
					temp.setWeekAddtime(temp.getWeekAddtime().substring(11));
					
					model.addAttribute("weekWorktime", tempTime);
					model.addAttribute("weekAddtime", temp.getWeekAddtime());
				}
			
			}
		} else {
			 String tempTime = (service.calculate(data.getWeekWorktime()));
			 remainTime = service.remainTime(data.getWeekWorktime());
			progress = service.progress(data.getWeekWorktime());	
			data.setWeekAddtime(data.getWeekAddtime().substring(11));
			data.setWeekWorktime(data.getWeekWorktime().substring(11));
			
			model.addAttribute("weekWorktime", tempTime);
			model.addAttribute("weekAddtime", data.getWeekAddtime());
			
		}
		
		model.addAttribute("data", data);
		model.addAttribute("remainTime", remainTime);
		model.addAttribute("progress", progress);
		
		return "commute/commute";
	}
	
	// 연봉조회
	@RequestMapping(value="/salary", method=RequestMethod.GET)
	public String getSalary(Model model) {
		return "commute/salary";
	}
	
	
	
	// 출근 기록
	@PostMapping(value="/in", produces="application/json; charset=UTF-8")
	@ResponseBody
	public String commuteIn(Model model, @RequestParam String intime) throws Exception {
		
		JSONObject json = new JSONObject();
		
		// 테스트용 empId
		String empId = "A2022100";
		// 로그인세션에서 id 가져와야함
		
		
		// 날짜와 아이디 검색해서 출근기록 있으면 실행되면 안돼...
		CommuteDTO data = service.selectData(empId);
		
		if(data == null) {
			logger.info("데이가.. 없음.. 그러면 집어넣어야지");
			service.insertIntime(empId);			
		} else {
			logger.info("데이터 있으니까 그냥 넘어가");
		}
		
		return json.toJSONString();
		// return "redirect:/commute/commute";
	}
	
	// 퇴근 기록
	@RequestMapping(value="/out")
	@ResponseBody
	public String commuteOut(Model model) throws Exception {
		JSONObject json = new JSONObject();
		// 테스트용 empId
		String empId = "A2022100";
		Date nowTime = service.nowTime();
		Date defaultTime = new Date(nowTime.getYear(), nowTime.getMonth(), nowTime.getDate(), 9, 0, 0);
		CommuteDTO data = service.selectData(empId);
		Calendar cal = Calendar.getInstance();

		
				
		if(data != null) {	// 출근기록이 있음
			if(nowTime.after(defaultTime)) { // 9시 이전 퇴근 불가능
				service.updateOuttime(empId); // 퇴근시간 업데이트
				
				// 주단위 시간 업데이트 
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				SimpleDateFormat timeformat = new SimpleDateFormat("HH:mm:ss");
				int today = 0;
				if(Calendar.DAY_OF_WEEK != 1) {
					today = cal.get(Calendar.DAY_OF_WEEK);	// 일요일이 아니면 해당하는 숫자로 변경
				}
				int addSum = 0;
				int workSum = 0;
				// 월 ~ 토
				if(today != 0) {
					
					for(int i = 0; i < today; i++) {
						cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY + i);
						String weekday = sdf.format(cal.getTime()); // 일요일~토요일해당 날짜
						
						CommuteDTO calData = service.selectdata(empId, weekday);
						if(calData != null) {	// 출근기록이 있음
							if(calData.getGetoffTime() != null) {		// 그날에 퇴근시간이 있다면.. 
								
								calData.setWorkTime(calData.getWorkTime().substring(11));
								calData.setAddedTime(calData.getAddedTime().substring(11));
								
								Date addtime = timeformat.parse(calData.getAddedTime());
								Date worktime = timeformat.parse(calData.getWorkTime());
								
								Date weekAddtime = timeformat.parse("00:00:00");
								Date weekWorktime = timeformat.parse("00:00:00");
								
								int addsecond = addtime.getHours() * 3600 + addtime.getMinutes() * 60 + addtime.getSeconds();
								addSum += addsecond;
								long hour1 = addSum / (60 * 60);		
								long minute1 = ((addSum % (60 * 60))) / 60;
								long second1 = ((addSum % (60 * 60))) % 60;	
								
								String tmp1 = String.format("%02d:%02d:%02d", hour1, minute1, second1);
								weekAddtime = timeformat.parse(tmp1);
								
								int worksecond = worktime.getHours() * 3600 + worktime.getMinutes() * 60 + worktime.getSeconds();
								workSum += worksecond;
								long hour2 = workSum / (60 * 60);		
								long minute2 = ((workSum % (60 * 60))) / 60;
								long second2 = ((workSum % (60 * 60))) % 60;	
								
								String tmp2 = String.format("%02d:%02d:%02d", hour2, minute2, second2);
								weekWorktime = timeformat.parse(tmp2);
								
								model.addAttribute("tmp2", tmp2);
								service.updateWeek(empId, weekAddtime, weekWorktime);
								
								logger.info(calData.getCommuteDt());
								logger.info("★weekAddtime: {}",weekAddtime);
								logger.info("★weekWorktime: {}",weekWorktime);
								
								
							}
						}	
						
					}
				} else {
					for(int i = 0; i <= today; i++) {
						cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY + i);
						String weekday = sdf.format(cal.getTime()); // 일요일~토요일해당 날짜
						
						CommuteDTO calData = service.selectdata(empId, weekday);
						if(calData != null) {
							
							if(calData.getGetoffTime() != null) {		// 그날에 퇴근시간이 있다면.. 
								
								calData.setWorkTime(calData.getWorkTime().substring(11));
								calData.setAddedTime(calData.getAddedTime().substring(11));
								
								Date addtime = timeformat.parse(calData.getAddedTime());
								Date worktime = timeformat.parse(calData.getWorkTime());
								
								Date weekAddtime = timeformat.parse("00:00:00");
								Date weekWorktime = timeformat.parse("00:00:00");
								
								int addsecond = addtime.getHours() * 3600 + addtime.getMinutes() * 60 + addtime.getSeconds();
								addSum += addsecond;
								long hour1 = addSum / (60 * 60);		
								long minute1 = ((addSum % (60 * 60))) / 60;
								long second1 = ((addSum % (60 * 60))) % 60;	
								
								String tmp1 = String.format("%02d:%02d:%02d", hour1, minute1, second1);
								weekAddtime = timeformat.parse(tmp1);
								
								int worksecond = worktime.getHours() * 3600 + worktime.getMinutes() * 60 + worktime.getSeconds();
								workSum += worksecond;
								long hour2 = workSum / (60 * 60);		
								long minute2 = ((workSum % (60 * 60))) / 60;
								long second2 = ((workSum % (60 * 60))) % 60;	
								
								String tmp2 = String.format("%02d:%02d:%02d", hour2, minute2, second2);
								weekWorktime = timeformat.parse(tmp2);
								
								logger.info(calData.getCommuteDt());
								logger.info("★weekAddtime: {}",weekAddtime);
								logger.info("★weekWorktime: {}",weekWorktime);
								
								model.addAttribute("tmp2", tmp2);
								model.addAttribute("weekAddtime", weekAddtime);
								model.addAttribute("weekWorktime", weekWorktime);
							}
						}
						
						
					}
				}
				
			}			
		}
		
		model.addAttribute("data", data);
		
		return json.toJSONString();
	}
	
	// 근태기록 조회하기
	@PostMapping(value="/record")
	@ResponseBody
	public String commuteRec(Model model, @RequestParam("year") int year, @RequestParam("month") int month) {
		// test값 	
		String empId = "A2022100";
		month += 1;
		String yearstr = Integer.toString(year);
		String monthstr = null;
		if(month < 10) {
			monthstr = "0" + month;
		} else {
			monthstr = month + "";
		}
		
		String yearmonth1 = yearstr + monthstr;
		System.out.println(yearmonth1);
		
		List<CommuteDTO> listData = service.getList(empId, yearmonth1);
		int cntList = service.cntList(empId, yearmonth1);
		JSONObject json = new JSONObject();

		
		model.addAttribute("cntList", cntList);
		model.addAttribute("listData", listData);
		model.addAttribute("year", year);
		model.addAttribute("month", month);
		
		
		return json.toJSONString();
	}
	
	
	
	

	

	
	
} 	
