package dev.khtml.hackathon.security;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import dev.khtml.hackathon.support.error.CoreApiException;
import dev.khtml.hackathon.support.error.ErrorType;
import dev.khtml.hackathon.util.HttpResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
		AuthenticationException authException) throws IOException {
		HttpResponseUtil.writeErrorResponse(
			response,
			new CoreApiException(ErrorType.UNAUTHORIZED, "[Error] 로그인이 필요합니다."));
	}
}
