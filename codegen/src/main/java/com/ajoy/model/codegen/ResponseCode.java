package com.ajoy.model.codegen;

public class ResponseCode<E>
{	
	private E object;
	private boolean isSuccess = true;
	private int errorCode;
	
	public ResponseCode()
	{		
	}
	
	public ResponseCode(boolean isSuccess)
	{
		this.isSuccess = isSuccess;
	}

	public ResponseCode(boolean flag, int errorCode)
	{
		isSuccess = flag;
		this.errorCode = errorCode;
	}

	public String toString()
	{
		return "isSuccss: "+isSuccess+", code: "+errorCode;
	}

	public E getObject() {
		return object;
	}

	public void setObject(E object) {
		this.object = object;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	
	
}
