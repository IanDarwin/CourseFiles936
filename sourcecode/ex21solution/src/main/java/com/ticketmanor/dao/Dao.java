package com.ticketmanor.dao;

import java.util.List;

import com.ticketmanor.model.Event;
import com.ticketmanor.model.Ticket;

public interface Dao {

	public abstract List<Ticket> getAvailableSeats(Event e);

}