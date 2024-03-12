package uz.pdp.online.reqobj.comment;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReqCommentEditObjBody {
        private String textarea;
        private int user_id;
        private String book_id;
        private int comment_id;

}

