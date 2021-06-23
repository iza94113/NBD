var mapFunction = function() {
    var key = this.sex;
    var value = {
        waga: parseFloat(this.weight),
        wzrost: parseFloat(this.height),
        counter: 1
    };
    emit(key, value);
};

var reduceFunction = function(keySex, values) {
    var wartosci = { waga: 0, wzrost: 0, counter: 0 };
    for (var idx = 0; idx < values.length; idx++) {
        wartosci.waga += values[idx].waga;
        wartosci.wzrost += values[idx].wzrost;
        wartosci.counter += values[idx].counter;
    }
    return wartosci;
};

var finalizeFunction = function(key, reducedVal) {

    reducedVal.avg_weight = reducedVal.waga / reducedVal.counter;
    reducedVal.avg_height = reducedVal.wzrost / reducedVal.counter;
    return {avg_weight:reducedVal.avg_weight, avg_height: reducedVal.avg_height};

};

printjson(
	db.people.mapReduce(
  	  mapFunction,
 	  reduceFunction,
 	   {
		out: {inline:1},
		finalize: finalizeFunction

 	   }
	)
);
