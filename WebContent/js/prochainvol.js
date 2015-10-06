function loadAirportsOptionList() {
	$('#airports').empty();
	var MapAirports = localStorage["airports"];
	if (MapAirports) {
		var airports = $.parseJSON(MapAirports);
		console.log("MapAirports is reloaded from local storage, length "+airports.length);
		$.each(airports, function(i, obj) {
			$('#airports').append(
					$("<option></option>").text(airports[i]));
		});
	} else {
		console.log("MapAirports was empty, loading it from the web !");
		var airports = [];
		$.getJSON('GetAirports', {}, function(data) {
			$.each(data, function(i, obj) {
				var iata = obj.iata;
				var name = obj.name;
				var fullName = "(" + iata + ") " + name;
				airports.push(fullName);
				$('#airports').append(
						$("<option></option>").text(
								fullName));
			});
			localStorage["airports"] = JSON.stringify(airports);
			console.log("airports loaded, length = "+localStorage["airports"].length);
		});
	}
}
