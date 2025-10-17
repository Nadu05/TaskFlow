package springboot.taskflow.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot.taskflow.payload.JWTResponse;
import springboot.taskflow.payload.UserSignInDTO;
import springboot.taskflow.payload.UserSignUpDTO;
import springboot.taskflow.services.AuthService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@RequestBody UserSignUpDTO userSignUpDTO) {
        try{
            authService.SignUp(userSignUpDTO);
                    return ResponseEntity.ok("Sign up successful");
        }
        catch(IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
    @PostMapping("/sign-in")
    public ResponseEntity<JWTResponse> signIn( @RequestBody UserSignInDTO request) {
        return ResponseEntity.ok(authService.SignIn(request));
    }

}
