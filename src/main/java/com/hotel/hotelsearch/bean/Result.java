package com.hotel.hotelsearch.bean;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.hotel.hotelsearch.entity.Hotel;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "results")
public class Result implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8004345911125655405L;
	@XmlElement(name = "results")
	private List<Hotel> hotel;

	public Result(List<Hotel> hotel) {
		super();
		this.hotel = hotel;
	}

}
