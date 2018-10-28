package com.hotel.hotelsearch;
public class ParamConflictException extends ParamException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6806454834279105987L;

	public ParamConflictException(String message) {
        super(message);
    }

    public ParamConflictException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParamConflictException(Throwable cause) {
        super(cause);
    }

    protected ParamConflictException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}