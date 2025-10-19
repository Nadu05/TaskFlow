package springboot.taskflow.payload.auth;

import lombok.Data;

@Data
public class UserSignUpDTO {

    //validation should implement with the spring validation
    private String Username;
    private String Password;
    private String Email;
    private String FirstName;
    private String LastName;
}
