var mapFunction = function() {
    for (var idx = 0; idx < this.credit.length; idx++) {
        var key = this.credit[idx].currency;
        var value = {
		balance: parseFloat(this.credit[idx].balance),
		counter: 1
	}
    	emit(key, value);
     }
};

var reduceFunction = function(keyCur, values) {
    var wartosci = { balance: 0, counter: 0 };
    for (var idx = 0; idx < values.length; idx++) {
        wartosci.balance += values[idx].balance;
        wartosci.counter += values[idx].counter;
    }
    return wartosci;
};

var finalizeFunction = function(key, redVal) {

    redVal.avg_balance = redVal.balance / redVal.counter;
    return {total_balance: redVal.balance, avg_balance: redVal.avg_balance, count: redVal.counter};

};

printjson(
	db.people.mapReduce(
  	  mapFunction,
 	  reduceFunction,
 	   {
		out: {inline:1},
		query: { $and: [{ "sex": "Female" }, { "nationality": "Poland" }] },
		finalize: finalizeFunction

 	   }
	)
);
