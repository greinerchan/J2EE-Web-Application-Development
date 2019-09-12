// This method is called when the keyUp event occurs in some of the inputs in the form.
// This method gets all the parameters and sends them in a request to the server.
function advancedSearch() {
	var lastName      = document.getElementById("lastName").value;
	var firstName     = document.getElementById("firstName").value;
	var anyPhone      = document.getElementById("anyPhone").value;
	var email         = document.getElementById("email").value;
	var additional    = document.getElementById("additional").value;
	var address       = document.getElementById("address").value;
	var city          = document.getElementById("city").value;
	var state         = document.getElementById("state").value;
	var country       = document.getElementById("country").value;
	var zip           = document.getElementById("zip").value;
	var receivedCards = document.getElementById("receivedCards").value;
	var sentCards     = document.getElementById("sentCards").value;
	
	if (lastName == "" && firstName == "" && anyPhone == "" && email == "" &&
			additional == "" && address == "" && city == "" && state == "" &&
			country == "" && zip == "" && receivedCards == "" && sentCards == "") {
		//We clean the last search results from the HTML DOM.
		cleanSearchResults();
		return;
	}
	
	var url="advanced-search-ajax.do";
	
	request = new XMLHttpRequest();

	//We set the callback page to updatePage.
	request.onreadystatechange = function() {
		updatePage(request);
	};
	
	//We open a POST request.
	request.open("POST",url,true);
	
	//Because the request is POST we must set its content-type
	request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	
	//We send the request.
	request.send("lastName="+escape(lastName)+
			"&firstName="+escape(firstName)+
			"&anyPhone="+escape(anyPhone)+
			"&email="+escape(email)+
			"&additional="+escape(additional)+
			"&address="+escape(address)+
			"&city="+escape(city)+
			"&state="+escape(state)+
			"&country="+escape(country)+
			"&zip="+escape(zip)+
			"&receivedCards="+escape(receivedCards)+
			"&sentCards="+escape(sentCards));
}

//This method receives the response from the server, and updates the DOM with the search results.
function updatePage(request) {
	if (request.readyState != 4) return;
	
	if (request.status != 200) {
		alert("Error, request status is "+request.status);
		return;
	}

	//We clean the last search results from the HTML DOM.
	cleanSearchResults();
	
	//We get the XML from the server.
	var xmlDoc=request.responseXML;
			
	//We display each of the results in the HTML DOM.
	var results = xmlDoc.getElementsByTagName("result");
	
	if (results.length > 0) {
		//We create the heading that displays "Search Results:"
		var searchResultsEl = document.getElementById("searchResults");
		var headingEl = document.createElement("h2");
		var headingText = document.createTextNode("Search Results");
		searchResultsEl.appendChild(headingEl);
		headingEl.appendChild(headingText);

		for (var i=0; i<results.length; i++){
			//We get the parameters from the XML doc.
			var id        = results[i].getElementsByTagName("id")[0].firstChild.nodeValue;
			var last      = results[i].getElementsByTagName("lastName")[0].firstChild.nodeValue;
			var first     = results[i].getElementsByTagName("firstNames")[0].firstChild.nodeValue;
			
			//We add the results to the DOM
			
			var aEl = document.createElement("a");
			aEl.href = "lookup-entry.do?id="+id;
			aEl.appendChild(document.createTextNode(last + ", "+ first));
			searchResultsEl.appendChild(aEl);

			var brEl = document.createElement("br");
			searchResultsEl.appendChild(brEl);
		}
	}
}

// This method cleans the search results from the HTML.
function cleanSearchResults() {
	var searchResultsEl = document.getElementById("searchResults");
	while (searchResultsEl.hasChildNodes()) {
		searchResultsEl.removeChild(searchResultsEl.firstChild);
	}
}
 			