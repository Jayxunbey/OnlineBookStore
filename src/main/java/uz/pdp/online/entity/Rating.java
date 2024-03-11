package uz.pdp.online.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rating {
    private int id;
    private int userId;
    private String bookId;
    private int rate;
    private float averageOfAll;
}
