package org.elevate.treinamento.diogo.util;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public enum ErrorEnum {
	BAD_REQUEST("E00001"),
	NOT_FOUND("E00002"),
	INVALID_OFFSET_VALUE("E00003"),
	INVALID_MAX_RESULT_VALUE("E00004"),
	INTERNAL_SERVER_ERROR("E00005");

	private String errorCode;

	private ErrorEnum(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * Get resource bundle message.
	 *
	 * @return
	 */
	public String getErrorMessage() {
		final ResourceBundle rb = ResourceBundle.getBundle("messages");
		return (rb == null ? "" : rb.getString(errorCode));
	}

	/**
	 * Get resource bundle message.
	 *
	 * @param params
	 * @return
	 */
	public String getErrorMessage(Object... params) {
		final ResourceBundle rb = ResourceBundle.getBundle("messages");
		return (rb == null ? "" : MessageFormat.format(rb.getString(errorCode), params));
	}

}