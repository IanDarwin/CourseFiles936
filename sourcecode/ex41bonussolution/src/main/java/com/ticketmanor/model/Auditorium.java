package com.ticketmanor.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/*
 * Auditorium - XXX
 */
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity @Table(name="auditoriums")
public class Auditorium   {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	long id;

	String name;
	String address;
	@ManyToOne Venue venue;
}
