package org.elevate.treinamento.diogo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final PasswordEncoder passwordEncoder;

	public CustomUserDetailsService(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Aqui você normalmente buscaria o usuário de um banco de dados
		// Para este exemplo, vamos criar um usuário hardcoded
		if ("user".equals(username)) {
			return createUser("user", "user", "USER");
		} else if ("admin".equals(username)) {
			return createUser("admin", "admin", "ROLE_"+"ADMIN");
		}
		{
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

	private UserDetails createUser(String username, String password, String... roles) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}

		return new User(username, passwordEncoder.encode(password), authorities);
	}
}