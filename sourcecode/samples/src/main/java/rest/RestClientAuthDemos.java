package rest;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.ticketmanor.model.Event;

/**
 * REST Client authorization methods demos.
 * Compilable but not workable at present; needs server setup and/or valid url, user, pass
 */
public class RestClientAuthDemos {

	public static final String URL = "https://some.place:8080/some/service";

	public static final String userName = "my-name", password = "you didn't guess it";

	/** Using a URLConnection 
	 * @throws IOException When things go south
	 */
	public static Event restClientAuthUrlDemo() throws IOException {
		URL url = new URL(URL);
		URLConnection conn = url.openConnection();
		if (userName != null || password != null) {
			String cred = userName + ":" + password;
			String auth = "Basic " + Base64.getEncoder().encode(cred.getBytes());
			conn.setRequestProperty("Authorization", "Basic " + auth);
		}
		// Get the data and convert it to an event
		// ...
		return null;
	}

	/** Using the JAX-RS Client API */
	public static Event restClientAuthApiDemo() {
	   Client cl = ClientBuilder.newClient();
	   WebTarget target = cl.target(URL);
	   String cred = userName + ":" + password;
	   Event e = target.request(MediaType.APPLICATION_JSON)
		  .header("Authorization", "Basic " + Base64.getEncoder().encode(cred.getBytes()))
		  .get(Event.class); // send GET for Event
	   System.out.println(
		  "Got an event: " + e.getDate() + e.getVenue());
	   return e;
	}
}
