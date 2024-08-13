package dev.khtml.hackathon.support.error;

public class PromptException extends RuntimeException {

	private final ErrorType errorType;

	private final Object data;

	public PromptException(ErrorType errorType) {
		super(errorType.getMessage());
		this.errorType = errorType;
		this.data = null;
	}

	public PromptException(ErrorType errorType, Object data) {
		super(errorType.getMessage());
		this.errorType = errorType;
		this.data = data;
	}

	public ErrorType getErrorType() {
		return errorType;
	}

	public Object getData() {
		return data;
	}

}
