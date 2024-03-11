package uz.pdp.online.reqobj;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReqRatingObjBody {
    private String bookId;
    private int star;
}
