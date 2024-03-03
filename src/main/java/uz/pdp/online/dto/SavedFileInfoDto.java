package uz.pdp.online.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;


@Getter
@ToString
@AllArgsConstructor
public class SavedFileInfoDto {
    private String fileName;
    private String fileAbsolutePath;
    private String extension;
    private long size;

}
