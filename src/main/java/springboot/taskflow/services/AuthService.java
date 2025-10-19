//implement the Interface for this class

package springboot.taskflow.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import springboot.taskflow.model.UserEntity;
import springboot.taskflow.payload.auth.JWTResponse;
import springboot.taskflow.payload.auth.UserSignInDTO;
import springboot.taskflow.payload.auth.UserSignUpDTO;
import springboot.taskflow.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public void SignUp(UserSignUpDTO userEntity) {

        if (userRepository.findByUsername(userEntity.getUsername()).isPresent()) {
            throw  new IllegalArgumentException("Username is already in use");
        }
        if (userRepository.findByEmail(userEntity.getEmail()).isPresent()) {
            throw  new IllegalArgumentException("Email is already in use");
        }
        UserEntity user =new UserEntity();
        user.setUsername(userEntity.getUsername());
        user.setEmail(userEntity.getEmail());
        user.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        user.setFirstName(userEntity.getFirstName());
        user.setLastName(userEntity.getLastName());
        userRepository.save(user);

    }

    public JWTResponse SignIn(UserSignInDTO UserCredential) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                UserCredential.getUsername(), UserCredential.getPassword()
        ));

        var user =userRepository.findByUsername(UserCredential.getUsername()).orElseThrow(
                ()->new IllegalArgumentException("Invalid username or password"));
        var jwt =jwtService.generateToken((UserDetails) user) ;

        return JWTResponse.builder().token(jwt).build();
    }

}





