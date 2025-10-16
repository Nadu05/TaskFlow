package springboot.taskflow.services;


import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;
import springboot.taskflow.model.UserEntity;

import java.security.Key;

@Service
public class JwtService {

    private Key jwtKey; //this should be final
    private final Long jwtExpiration = 3600L;

    public String generateToken(UserEntity user) {
        return "jwttoken";
    }

}
