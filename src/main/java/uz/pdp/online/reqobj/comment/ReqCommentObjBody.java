package uz.pdp.online.reqobj;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReqCommentObjBody {
    private String textarea;
    private String book_id;
}
