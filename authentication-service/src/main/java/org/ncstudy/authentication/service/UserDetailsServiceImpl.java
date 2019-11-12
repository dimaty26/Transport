package org.ncstudy.authentication.service;

import org.ncstudy.authentication.model.Role;
import org.ncstudy.authentication.model.UserData;
import org.ncstudy.authentication.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDetailsServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserData userData = userRepository.findByUsername(s);
        if (userData == null)
            throw new UsernameNotFoundException(String.format("The username %s does not exist", s));
        List<GrantedAuthority> authorities = new ArrayList<>();
        userData.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName())));
        return new User(userData.getUsername(), userData.getPassword(), authorities);
    }

    public void addUser(UserData userData) throws Exception {
        if (userRepository.existsByEmail(userData.getEmail()))
            throw new Exception("User with this email already exist");
        if (userRepository.existsByUsername(userData.getUsername()))
            throw new Exception("User with this name already exist");
        userData.setRoles(Collections.singletonList(Role.USER));
        userData.setPassword(passwordEncoder.encode(userData.getPassword()));
        userRepository.save(userData);
    }

    public void activateUser(UUID uuid) throws UserPrincipalNotFoundException {
        UserData userData = userRepository.findByActivationCode(uuid);
        if (userData == null)
            throw new UserPrincipalNotFoundException("User by activation code not found");
        userData.setActive(true);
        userData.setActivationCode(null);
        userRepository.save(userData);
    }

    public void setRoles(String username, List<Role> roles) {
        UserData userData = userRepository.findByUsername(username);
        if (userData == null)
            throw new UsernameNotFoundException(String.format("The username %s doesn't exist", username));
        userData.setRoles(roles);
        userRepository.save(userData);
    }

    @PostConstruct
    private void setupDefaultUsers() {
        if (userRepository.count() == 0) {
            userRepository.save(new UserData(
                    "john.doe",
                    passwordEncoder.encode("userpass"),
                    Collections.singletonList(Role.USER),
                    true,
                    null));
            userRepository.save(new UserData(
                    "john.admindoe",
                    passwordEncoder.encode("adminpass"),
                    // todo: check is java > 9 in docker //\\ Changed to Java 8 Arrays.asList
                    Arrays.asList(Role.USER, Role.ADMIN),
                    true,
                    null));
        }
    }


}
