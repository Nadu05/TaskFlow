//implement the Interface for this class

package springboot.taskflow.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import springboot.taskflow.model.UserEntity;
import springboot.taskflow.payload.UserSignInDTO;
import springboot.taskflow.payload.UserSignUpDTO;
import springboot.taskflow.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

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
        user.setPassword(userEntity.getPassword());
        user.setFirstName(userEntity.getFirstName());
        user.setLastName(userEntity.getLastName());
        userRepository.save(user);

    }

    public void SignIn(UserSignInDTO UserCredential) {
        //Authentication auth = authenticationManager.authenticate(authenticationManager)
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                UserCredential.getUsername(), UserCredential.getPassword()
        ));
        var user =userRepository.findByUsername(UserCredential.getUsername()).orElseThrow(
                ()->new IllegalArgumentException("Invalid username or password"));
        // var jwt = ;
    }




}
