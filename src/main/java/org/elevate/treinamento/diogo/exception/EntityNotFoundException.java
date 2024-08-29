package org.elevate.treinamento.diogo.exception;

import org.elevate.treinamento.diogo.util.ErrorEnum;
import org.elevate.treinamento.diogo.util.ErrorMessageBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private static final String GENERIC_MSG = "Entity not found";
	private static final String MSG = " not found with id ";
	private static final String MSG_LANG = " not found with lang ";
	private final String errorCode;

	public <T> EntityNotFoundException(Class<T> entityClass, Integer id, String errorCode) {
		super(entityClass.getSimpleName() + MSG + id);
		this.errorCode = errorCode;
	}

	public <T> EntityNotFoundException(Class<T> entityClass, String errorCode) {
		super(entityClass.getSimpleName() + GENERIC_MSG);
		this.errorCode = errorCode;
	}

	public <T> EntityNotFoundException(Class<T> entityClass, String id, String errorCode) {
		super(entityClass.getSimpleName() + MSG + id);
		this.errorCode = errorCode;
	}

	public <T> EntityNotFoundException(Class<T> entityClass, String id, String lang, String errorCode) {
		super(entityClass.getSimpleName() + MSG + id + MSG_LANG + lang);
		this.errorCode = errorCode;
	}

	/**
	 * Constructor for entity not found server error.
	 * 
	 * @param entityClass
	 * @param e
	 * @param errorEnum
	 */
	public EntityNotFoundException(ErrorEnum errorEnum, Object... params) {
		super(ErrorMessageBuilder.build(errorEnum, params));
		errorCode = errorEnum.getErrorCode();
	}

	public String getErrorCode() {
		return errorCode;
	}
}
