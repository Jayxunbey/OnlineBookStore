package uz.pdp.online.entity;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {
    private Long id;
    private Long userId;
    private String bookId;
    private String text;
    private Date date;
}
