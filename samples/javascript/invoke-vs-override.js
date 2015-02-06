// Invoke vs override
MyClass.aMethod(function(data) {
	this.data = data;
});

MyClass.aMethod = function(data) {
	this.data = data;
}
