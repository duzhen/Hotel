package com.hotel.hotelsearch.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "hotel")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlTransient
    private Long id;

//    @Pattern(regexp = "[a-zA-Z ]{5,45}", message = "illegal name")
    private String name;

//    @Pattern(regexp = "[a-zA-Z ]{5,45}", message = "illegal city")
    private String city;
    
//    @Pattern(regexp = "[a-zA-Z ]{5,45}", message = "illegal country")
//    @Pattern(regexp = "[Argentina|Brazil|Canada]", message = "illegal country")
    private String country;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Hotel [id=" + id + ", name=" + name + ", city=" + city + ", country=" + country + "]";
	}
    
    
}