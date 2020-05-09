package ru.itis.demo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Data
public class RegForm {
    @Email(message = "{errors.incorrect.email}")
    private String email;


    @Pattern(regexp="^[a-zA-Z0-9_]*$",message = "{errors.incorrect.name}")
    @NotNull(message = "{errors.incorrect.name}")
    private String name;


    @NotNull(message = "{errors.password.required}")
    @Size(min = 6, message = "{errors.password.size}")
    private String password;



    @NotNull(message = "{errors.password.required}")
    @Size(min = 6, message = "{errors.password.size}")
    private String confirmPassword;

    @AssertTrue(message="{errors.password.eq}")
    private boolean isValid() {
        return this.password.equals(this.confirmPassword);
    }



}