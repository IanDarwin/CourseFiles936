App = Ember.Application.create();

App.Router.map(function() {
  // put your routes here
});

App.IndexRoute = Ember.Route.extend({
  model: function() {
    return [ 
		{'date':'2015-06-01', 'title':'Blues vs Tigers', 'venue':'Candlestick Park'},
		{'date':'2015-06-02', 'title':'Raptors vs Nicks', 'venue':'Rogers Stadium'}
	];
  }
});
