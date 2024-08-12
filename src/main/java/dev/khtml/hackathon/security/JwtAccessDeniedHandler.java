package dev.khtml.hackathon.security;

import java.io.IOException;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import dev.khtml.hackathon.support.error.CoreApiException;
import dev.khtml.hackathon.support.error.ErrorType;
import dev.khtml.hackathon.util.HttpResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
		AccessDeniedException accessDeniedException) throws IOException {
		HttpResponseUtil.writeErrorResponse(
			response,
			new CoreApiException(ErrorType.NOT_FOUND, "[Error] 접근 권한이 없습니다."));
	}
}
