package com.ticketmanor;

import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ticketmanor.model.FeedbackForm;

@Named
public class CommentLister {

	@PersistenceContext EntityManager em;
	
	public List<FeedbackForm> list() {
		System.err.println("CommentLister.list()");
		return em.createQuery("from FeedbackForm ff order by ff.date desc", FeedbackForm.class).getResultList();
	}
}
