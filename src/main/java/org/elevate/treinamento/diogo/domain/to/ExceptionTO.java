package org.elevate.treinamento.diogo.domain.to;

public class ExceptionTO extends BaseTO {

	private String field;
	private String errorMsg;

	public ExceptionTO(String field, String errorMsg) {
		super();
		this.field = field;
		this.errorMsg = errorMsg;
	}

	public ExceptionTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getField() {
		return field;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setField(String field) {
		this.field = field;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
