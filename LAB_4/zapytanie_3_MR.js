var mapFunction = function() {
        var key = this.job;
	if (key) {
	    emit(key, 1);
	}
};

var reduceFunction = function(keyJob, values) {
    return values.length;
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

