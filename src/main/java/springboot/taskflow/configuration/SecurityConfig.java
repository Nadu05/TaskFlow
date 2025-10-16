package springboot.taskflow.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import springboot.taskflow.repositories.UserRepository;

@Configuration
@RequiredArgsConstructor

public class SecurityConfig {

    public final UserRepository userRepository  ;


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config )
            throws Exception
    {
        return config.getAuthenticationManager();
    }

   @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService,
                                                         PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder); // Use the BCrypt bean
        return authProvider;
    }


    @Bean
   public UserDetailsService userDetailsService() {
        return username -> (org.springframework.security.core.userdetails.UserDetails)
                userRepository.findByUsername(username).orElseThrow(
                ()->new UsernameNotFoundException("User Not found"));
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
