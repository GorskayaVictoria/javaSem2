package ru.itis.demo.dto;

import lombok.Data;

import javax.validation.constraints.Email;


//import org.hibernate.validator.constraints.*;
//import javax.validation.constraints.*;

@Data
public class ProfileForm {
    @Email(message = "{errors.incorrect.email}")
    private String email;

//    @NotNull(message = "{errors.null.age}")
//    @Min(value = 0, message = "{errors.incorrect.age}")
//    private Integer age;

}
