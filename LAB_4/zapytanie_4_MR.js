var mapFunction = function() {
	var bmi= parseFloat(this.weight)*10000/(parseFloat(this.height)*parseFloat(this.height))
	var key = this.nationality;
	var value = {
	bmi: bmi,
        counter: 1
    };
    emit(key, value);
};

var reduceFunction = function(keyNat, values) {
    var wartosci = { bmi: 0, counter: 0, min: 0 };
    wartosci.min = values[0].bmi;
    wartosci.max = values[0].bmi;
    for (var idx = 0; idx < values.length; idx++) {
        wartosci.bmi += values[idx].bmi;
        wartosci.counter += values[idx].counter;
        if (values[idx].bmi < wartosci.min) wartosci.min = values[idx].bmi;
        if (values[idx].bmi > wartosci.max) wartosci.max = values[idx].bmi;
    }

    return wartosci;
};

var finalizeFunction = function(key, redVal) {

    redVal.avg_bmi = redVal.bmi / redVal.counter;

    return {avg_bmi: redVal.avg_bmi, min_bmi: redVal.min, max_bmi: redVal.max};

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
