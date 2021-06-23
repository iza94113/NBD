var mapFunction = function() {
    for (var idx = 0; idx < this.credit.length; idx++) {
        var key = this.credit[idx].currency;
        var value = parseFloat(this.credit[idx].balance);
    }
    emit(key, value);
};

var reduceFunction = function(keycur, values) {
    return Array.sum(values);
};

printjson(
	db.people.mapReduce(
  	  mapFunction,
 	  reduceFunction,
 	   {
		out: {inline:1}
 	   }
	)
);

