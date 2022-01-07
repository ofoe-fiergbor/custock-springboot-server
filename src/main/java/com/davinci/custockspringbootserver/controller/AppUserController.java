package com.davinci.custockspringbootserver.controller;

import com.davinci.custockspringbootserver.domain.dto.auth.AddRoleToUserDto;
import com.davinci.custockspringbootserver.domain.dto.auth.CreateRoleDto;
import com.davinci.custockspringbootserver.domain.dto.auth.CreateUserDto;
import com.davinci.custockspringbootserver.domain.dto.auth.LoginDto;
import com.davinci.custockspringbootserver.domain.model.AppUser;
import com.davinci.custockspringbootserver.domain.model.Role;
import com.davinci.custockspringbootserver.domain.repositories.AppUserRepository;
import com.davinci.custockspringbootserver.exceptions.UserNotFoundException;
import com.davinci.custockspringbootserver.services.implementation.AppUserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AppUserController {

    private final AppUserServiceImpl userService;
    private final AppUserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody CreateUserDto user)  {
        if (userRepository.existsAppUserByEmail(user.getEmail())) {
            return new ResponseEntity<>("Email already exits.", HttpStatus.BAD_REQUEST);
        }
        try {
            return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>("Error: "+e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginDto user) throws AuthenticationException {
        Optional<AppUser> appUser = userRepository.findAppUserByEmail(user.getEmail());
        if (appUser.isEmpty()) {
            return new ResponseEntity<>("Email does not exits.", HttpStatus.BAD_REQUEST);
        }
        try {
            return new ResponseEntity<>(userService.authenticateUser(user.getEmail(), user.getPassword(), appUser.get()),
                    HttpStatus.OK);
        } catch (AuthenticationException exception) {
            return new ResponseEntity<>("Invalid credentials", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        Optional<AppUser> user = userRepository.findById(id);
        if (user.isEmpty()) {
            return new ResponseEntity<>("User does not exist", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }

    @PostMapping("/roletouser")
    public ResponseEntity<?> addRoleToUser(@RequestBody AddRoleToUserDto roleToUser) {
        userService.addRoleToUser(roleToUser.getEmail(), roleToUser.getRoleName());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/createrole")
    public ResponseEntity<?> createRole(@RequestBody CreateRoleDto roleDto) {
        return new ResponseEntity<>(userService.saveRole(new Role(null, roleDto.getName())),
                HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout() {
        return new ResponseEntity<>(userService.logout(), HttpStatus.OK);
    }


}
