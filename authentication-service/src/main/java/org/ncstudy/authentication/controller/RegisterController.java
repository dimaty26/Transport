package org.ncstudy.authentication.controller;

import org.ncstudy.authentication.model.UserData;
import org.ncstudy.authentication.repository.UserRepository;
import org.ncstudy.authentication.service.MailSender;
import org.ncstudy.authentication.service.UserDetailsServiceImpl;
import org.ncstudy.authentication.validation.PasswordCustomValidation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class RegisterController {

    private final UserDetailsServiceImpl service;
    private final MailSender sender;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterController(UserDetailsServiceImpl service, MailSender sender, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.service = service;
        this.sender = sender;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<String>register(UserData userData) throws Exception {
        PasswordCustomValidation validation = new PasswordCustomValidation();
        if (!validation.isValid(userData.getPassword()))
            return new ResponseEntity<>(validation.getMessageTemplate(), HttpStatus.BAD_REQUEST);
        userData.setActivationCode(UUID.randomUUID());
        service.addUser(userData);
        sender.send(userData.getEmail(),
                "TransportEye Activation",
//                "Link for activation: http://localhost:8901/users/activation/" + userData.getActivationCode());
                "Link for activation: http://authenticationservice:8901/users/activation/" + userData.getActivationCode());
        return new ResponseEntity<>("Register message was sent", HttpStatus.OK);
    }

    @GetMapping("/activation/{uuid}")
    public String activation(@PathVariable UUID uuid) throws UserPrincipalNotFoundException {
        service.activateUser(uuid);
        return "/login"; // todo: redirect
    }

    @PutMapping("/password/recovery")
    public ResponseEntity<String> recovery(@RequestParam String username){
        UserData userData = userRepository.findByUsername(username);
        if (userData == null)
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
        userData.setResetPasswordCode(UUID.randomUUID());
        userRepository.save(userData);
        sender.send(userData.getEmail(),
                "TransportEye Change Password",
//                "Link to change password: http://localhost:8080/password/reset/" + userData.getResetPasswordCode());
        "Link to change password: http://frontendservice:8080/password/reset/" + userData.getResetPasswordCode());
        return new ResponseEntity<>("Link was send to email", HttpStatus.OK);
    }
    // todo: reset page for link

    @PutMapping("/password/change")
    public ResponseEntity<String> savePassword(
            @RequestParam UUID uuid,
            @RequestParam(name = "new_password") String newPassword,
            @RequestParam(name = "repeat_password")String repeatPassword){
        if (!newPassword.equals(repeatPassword))
            return new ResponseEntity<>("Passwords not equals", HttpStatus.BAD_REQUEST);
        PasswordCustomValidation validation = new PasswordCustomValidation();
        if (!validation.isValid(newPassword))
            return new ResponseEntity<>(validation.getMessageTemplate(), HttpStatus.BAD_REQUEST);
        UserData userData = userRepository.findByResetPasswordCode(uuid);
        if (userData == null)
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
        userData.setResetPasswordCode(null);
        userData.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(userData);
        return new ResponseEntity<>("Password was changed", HttpStatus.OK);
    }
}
