package uz.pdp.online.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

   private String name;
   private String genre;
   private String year;
   private MultipartFile file;
   private MultipartFile image;

}
