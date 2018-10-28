package com.hotel.hotelsearch.bean;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "error")
public class APIError implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8896171763295841804L;

	@XmlElement(name = "code")
	private int code;
	
	@XmlElement(name = "message")
	private String msg;

	public APIError(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

}
