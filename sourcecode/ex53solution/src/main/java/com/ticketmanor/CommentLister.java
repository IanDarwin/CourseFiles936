package com.ticketmanor;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ticketmanor.model.FeedbackForm;

@Named
@SessionScoped
public class CommentLister implements Serializable {
	private static final long serialVersionUID = -8054003420102880724L;

	@PersistenceContext EntityManager em;
	
	public List<FeedbackForm> list() {
		System.out.println("CommentLister.list()");
		return em.createQuery("from FeedbackForm ff order by ff.date desc", FeedbackForm.class).getResultList();
	}
}
