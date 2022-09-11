<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>workforus - 게시판</title>
    <link rel="stylesheet" href="static/css/pages/board2.css">
    <%@ include file="../module/header.jsp" %>
</head>
<body>
	<%@ include file="../module/navigation.jsp" %>
	
    <div id="app">
      <div id="main">
      	<div class="page-heading">
      		<h3>Board</h3> <!-- 게시판 이름 -->
      	</div>
	      	<!-- board -->
      	<div class="page-content">
			<section class="row">
				<div class="col-12"> 
					<!-- 상단 -->
					<div class="section-top radius">
						<div class="info-container">
							<ul class="info-ul ">
								<li class="info">게시판 주소 : http://workforus/board?id=1</li>
								<li class="info">운영자 : 바나나킥</li>
							</ul>
							<div class="dropdown" style="position: relative; margin-bottom: 5px;" >
								<a class="nav-link active dropdown-toggle text-gray-600" href="#" data-bs-toggle="dropdown" aria-expanded="false">
								게시판 멤버</a>
								<ul class="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownMenuButton" style="min-width: 11rem;">
									<li>
										<h6 class="dropdown-header">멤버</h6>
									</li>
									<li>
										<a class="dropdown-item" href="#">
											<i class="icon-mid bi bi-person me-2"></i>박보검
										</a>
									</li>
									<li>
										<a class="dropdown-item" href="#">
											<i class="icon-mid bi bi-person me-2"></i>홍길동
										</a>
									</li>
								</ul>
							</div>
						</div>
					</div>
					<!-- 메인 -->
					<div class="section-main radius">
						<div class="main-button">
							<button type="submit" class="btn"><i class="bi bi-pencil"></i> 새글작성</button>
							<button type="submit" class="btn"><i class="bi bi-trash"></i> 삭제</button>
							<button type="submit" class="btn"><i class="bi bi-plus-circle"></i> 게시판생성</button>
							<hr style="margin: 0px; margin-bottom: 3px;">
						</div>
						<div class="dataTable-container">
							<table class="table table-striped dataTable-table">
								 <colgroup>
						            <col width=5%>
						            <col width=10%>
						            <col width=40%>
						            <col width=10%>
						            <col width=10%>
						            <col width=10%>
						            <col width=10%>
						        </colgroup>
								<thead>
									<tr>
										<th><input type="checkbox"></th>
										<th>번호</th>
										<th>제목</th>
										<th>작성자</th>
										<th>작성일</th>
										<th>조회</th>
										<th>좋아요</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td><input type="checkbox"></td>
										<td><i class="bi bi-megaphone-fill"></i></td>
										<td>테스트 중입니다.</td>
										<td>바나나킥</td>
										<td>2022-09-11</td>
										<td>1</td>
										<td>1000</td>
									</tr>
									<tr>
										<td><input type="checkbox"></td>
										<td>1</td>
										<td>테스트 중입니다.</td>
										<td>바나나킥</td>
										<td>2022-09-11</td>
										<td>1</td>
										<td>1000</td>
									</tr>
									<tr>
										<td><input type="checkbox"></td>
										<td>2</td>
										<td>테스트 중입니다.</td>
										<td>바나나킥</td>
										<td>2022-09-11</td>
										<td>1</td>
										<td>1000</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</section>
		</div>
		<!-- footer -->
        <%@ include file="../module/footer.jsp" %>
      </div>
    </div>
    <script src="static/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
    <script src="static/js/bootstrap.bundle.min.js"></script>

    <script src="static/vendors/apexcharts/apexcharts.js"></script>
    <script src="static/js/pages/dashboard.js"></script>

    <script src="static/js/main.js"></script>
</body>
</html>