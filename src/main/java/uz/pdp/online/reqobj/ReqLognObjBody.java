package uz.pdp.online.reqobj;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import uz.pdp.online.entity.User;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReqLognObjBody {
    @Size(min = 5, max = 15 , message = "Username must be between 5 and 15 character")
    private String username;
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$", message = "password size must be least 8 character and contains Upper case, lover case and numbers")
    private String password;
}
