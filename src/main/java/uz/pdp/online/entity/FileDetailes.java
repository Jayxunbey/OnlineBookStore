package uz.pdp.online.entity;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class FileDetailes {

    private Long id;
    private String bookId;
    private String fileName;
    private String fileAbsolutePath;
    private String extension;
    private long size;

    public FileDetailes(String bookId,String fileName, String fileAbsolutePath, String extension, long size) {


        this.bookId=bookId;
        this.fileName = fileName;
        this.fileAbsolutePath = fileAbsolutePath;
        this.extension = extension;
        this.size = size;
    }
}
