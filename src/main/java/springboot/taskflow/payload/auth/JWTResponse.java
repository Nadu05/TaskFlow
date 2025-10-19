package springboot.taskflow.payload.auth;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JWTResponse {
    private String token;
}
