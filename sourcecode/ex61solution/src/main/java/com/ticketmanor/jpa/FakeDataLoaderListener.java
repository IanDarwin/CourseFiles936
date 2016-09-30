package com.ticketmanor.jpa;

import javax.ejb.EJB;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/** Load some fake data. Tried to make this a Singleton EJB
 * but WildFly didn't run its @PostConstruct method.
 * If you convert it back to a singleton, remember to unplug
 * it from web.xml!
 * @author Ian Darwin
 */
public class FakeDataLoaderListener implements ServletContextListener {

	@EJB FakeDataLoaderEjb ejb;
	
	@Override
	public void contextInitialized(ServletContextEvent ctx) {
		System.out.println("FakeEventLoader.contextInitialized()");
		ejb.loadFakeData();
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// Empty
	}
}
