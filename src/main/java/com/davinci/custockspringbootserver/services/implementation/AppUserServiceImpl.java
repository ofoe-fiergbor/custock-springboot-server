package com.davinci.custockspringbootserver.services.implementation;

import com.davinci.custockspringbootserver.domain.dto.product.CreateSupplierDto;
import com.davinci.custockspringbootserver.domain.dto.auth.CreateUserDto;
import com.davinci.custockspringbootserver.domain.dto.auth.LoginResponse;
import com.davinci.custockspringbootserver.domain.model.AppUser;
import com.davinci.custockspringbootserver.domain.model.Role;
import com.davinci.custockspringbootserver.domain.repositories.AppUserRepository;
import com.davinci.custockspringbootserver.domain.repositories.RoleRepository;
import com.davinci.custockspringbootserver.domain.repositories.SupplierRepository;
import com.davinci.custockspringbootserver.exceptions.UserNotFoundException;
import com.davinci.custockspringbootserver.services.interfece.AppUserService;
import com.davinci.custockspringbootserver.utils.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService, UserDetailsService {

    private final AppUserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final ProductServiceImpl productService;
    private final SupplierRepository supplierRepository;
    private final JwtUtils jwtUtils;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser user = userRepository.findAppUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found in the database"));
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return new User(user.getEmail(), user.getPassword(), authorities);
    }

    @Override
    public AppUser saveUser(CreateUserDto user) throws UserNotFoundException {
        AppUser newUser = new AppUser();
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.getRoles().add(roleRepository.findRoleByName("ROLE_USER"));
        userRepository.save(newUser);
        productService.createSupplier(new CreateSupplierDto("Cash Supplier", "n/a", newUser.getId()));
        return newUser;
    }



    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String email, String roleName) {
        Optional<AppUser> user = userRepository.findAppUserByEmail(email);
        Role role = roleRepository.findRoleByName(roleName);
        user.get().getRoles().add(role);
    }

    @Override
    public LoginResponse authenticateUser(String email, String password, AppUser appUser) throws AuthenticationException{
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
           authenticationManager.authenticate(authenticationToken);

            String token = jwtUtils.createAccessToken(email);

            LoginResponse loginResponse = new LoginResponse(
                    appUser.getId(),
                    appUser.getFirstName(),
                    appUser.getLastName(),
                    appUser.getEmail(),
                    token,
                    appUser.getRoles()
            );

            return loginResponse;
    }


}
