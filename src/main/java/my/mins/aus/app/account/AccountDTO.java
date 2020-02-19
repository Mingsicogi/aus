package my.mins.aus.app.account;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AccountDTO {

    @NotBlank(message = "empId can not be null")
    private String empId;
    @NotBlank(message = "password can not be null")
    private String password;
    @NotBlank(message = "name can not be null")
    private String name;

    @Min(value = 1)
    private int age;

    private String role;
}
