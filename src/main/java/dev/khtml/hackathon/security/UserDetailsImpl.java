package dev.khtml.hackathon.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserDetailsImpl implements UserDetails {
	private final Long id;
	private final String password;
	private final Collection<? extends GrantedAuthority> authorities;

	@Builder
	public UserDetailsImpl(
		Long id,
		String password,
		Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.password = password;
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getUsername() {
		return id.toString();
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
