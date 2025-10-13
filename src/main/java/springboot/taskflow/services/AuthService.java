//implement the Interface for this class

package springboot.taskflow.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import springboot.taskflow.model.UserEntity;
import springboot.taskflow.payload.UserSignUpDTO;
import springboot.taskflow.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    public void SignUp(UserSignUpDTO userEntity) {

        if (userRepository.findByUsername(userEntity.getUsername()).isPresent()) {
            throw  new IllegalArgumentException("Username is already in use");
        }
        if (userRepository.findByEmail(userEntity.getEmail()).isPresent()) {
            throw  new IllegalArgumentException("Email is already in use");
        }
        UserEntity user =new UserEntity();//you have to update the User Entity
        user.setUsername(userEntity.getUsername());
        user.setEmail(userEntity.getEmail());
        user.setPassword(userEntity.getPassword());
        user.setFirstName(userEntity.getFirstName());
        user.setLastName(userEntity.getLastName());
        //
        //user.getFirstName();//.......
        userRepository.save(user);




    }


}
