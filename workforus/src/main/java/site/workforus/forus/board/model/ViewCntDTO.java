package site.workforus.forus.board.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@ToString
@Getter
@Setter
@Alias("viewCntDto")
public class ViewCntDTO {
    private int postId;
    private int viewCnt;

    public ViewCntDTO(){}
    public ViewCntDTO (int postId, int viewCnt) {
        this.postId = postId;
        this.viewCnt = viewCnt;
    }
}
