App = Ember.Application.create();

App.Router.map(function() {
  // put your routes here
});

App.IndexRoute = Ember.Route.extend({
  model: function() {
	return Ember.$.getJSON('http://localhost:8080/ticketmanor/rest/sports').
		then(function(data) {
			return data.splice(0,9);
		});
	}
});
