package com.tipocambio.service.impl;

import static com.tipocambio.utils.GeneralConstant.NOT_FOUND_USER;

import com.tipocambio.model.UsuarioModel;
import com.tipocambio.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioModel usuarioModel = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(NOT_FOUND_USER + username));

        return new User(usuarioModel.getUsername(), usuarioModel.getPassword(), new ArrayList<>());
    }

    public UsuarioModel createUser(String username, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        UsuarioModel usuarioModel = new UsuarioModel();
        usuarioModel.setUsername(username);
        usuarioModel.setPassword(encodedPassword);
        return userRepository.save(usuarioModel);
    }
}
