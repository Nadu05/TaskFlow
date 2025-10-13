package springboot.taskflow.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot.taskflow.payload.UserSignUpDTO;
import springboot.taskflow.services.AuthService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    @GetMapping ("/test")
    public String test() {
        return "test-ok ";
    }
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
}
