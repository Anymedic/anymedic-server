package dev.khtml.hackathon.support.response;

import dev.khtml.hackathon.support.error.ErrorMessage;
import dev.khtml.hackathon.support.error.ErrorType;

public class ApiResponse<S> {

	private final ResultType result;

	private final S data;

	private final ErrorMessage error;

	private ApiResponse(ResultType result, S data, ErrorMessage error) {
		this.result = result;
		this.data = data;
		this.error = error;
	}

	public static ApiResponse<?> success() {
		return new ApiResponse<>(ResultType.SUCCESS, null, null);
	}

	public static <S> ApiResponse<S> success(S data) {
		return new ApiResponse<>(ResultType.SUCCESS, data, null);
	}

	public static ApiResponse<?> error(ErrorType error) {
		return new ApiResponse<>(ResultType.ERROR, null, new ErrorMessage(error));
	}

	public static <S> ApiResponse<S> error(ErrorType error, S errorData) {
		return new ApiResponse<>(ResultType.ERROR, null, new ErrorMessage(error, errorData));
	}

	public ResultType getResult() {
		return result;
	}

	public Object getData() {
		return data;
	}

	public ErrorMessage getError() {
		return error;
	}

}
