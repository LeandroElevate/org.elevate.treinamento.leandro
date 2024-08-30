package org.elevate.treinamento.leandro.util;

public class ErrorMessageBuilder {
	/**
	 * Error message builder without arguments.
	 * 
	 * @param errorEnum
	 * @return
	 */
	public static final String build(ErrorEnum errorEnum) {
		return errorEnum.getErrorMessage();
	}

	/**
	 * Error message builder with arguments.
	 * 
	 * @param errorEnum
	 * @param params
	 * @return
	 */
	public static final String build(ErrorEnum errorEnum, Object... params) {
		return errorEnum.getErrorMessage(params);
	}

	private ErrorMessageBuilder() {
	}

}