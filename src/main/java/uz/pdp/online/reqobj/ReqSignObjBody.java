package uz.pdp.online.reqobj;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.context.annotation.PropertySource;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@PropertySource("classpath:property/application.properties")
public class ReqSignObjBody {
    @NonNull
     @Size(min = 5, max = 15 , message = "Username must be between 5 and 15 character")
    private String username;
    @NotNull @Email
    private String email;
    @NotNull
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$", message = "password size must be least 8 character and contains Upper case, lover case and numbers")
    private String new_password;
    @NotNull
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$",  message = "password size must be least 8 character and contains Upper case, lover case and numbers")
    private String repeat_password;

}
