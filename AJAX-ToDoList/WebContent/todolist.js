// Sends a new request to fetch the to-do list
function load() {
    var req = new XMLHttpRequest();
    req.onreadystatechange = function() {
        if (req.readyState != 4) return;
        if (req.status != 200) return;
        var response = JSON.parse(req.responseText);
        if (Array.isArray(response)) {
            updateList(response);
        } else {
            displayError(response.error);
        }
    }

    req.open("GET", "fetch-list.do", true);
    req.send(); 
}

function updateList(items) {
    // Removes the old to-do list items
    var list = document.getElementById("list");
    while (list.hasChildNodes()) {
        list.removeChild(list.firstChild);
    }

    // Parses the response to get a list of JavaScript objects for 
    // the items.

    // Adds each new todo-list item to the list
    for (var i = 0; i < items.length; i++) {
        // Extracts the item id and text from the response
        var id = items[i]["id"];
        var itemText = items[i]["text"];
        var ipAddr   = items[i]["ip_addr"];
        var userName = items[i]["user_name"];
  
        // Builds a new HTML list item for the todo-list item
        var newItem = document.createElement("li");
        newItem.innerHTML = "<button onclick='deleteItem("+id+")'>X</button> " +
                            itemText +
                            ' <span class="details">' +
                            "(uniqueId = " + id +
                            ", user = " + userName +
                            ", ipAddress = " + ipAddr + ")" +
                            '</span>';

        // Adds the todo-list item to the HTML list
        list.appendChild(newItem);
    }
}

function displayError(message) {
    var errorElement = document.getElementById("error");
    errorElement.innerHTML = message;
}

function addItem(position) {
    var itemTextElement = document.getElementById("item");
    var itemTextValue   = itemTextElement.value;

    // Clear input box and old error message (if any)
    itemTextElement.value = '';
    displayError('');

    var req = new XMLHttpRequest();
    req.onreadystatechange = function() {
        if (req.readyState != 4) return;
        if (req.status != 200) return;
        var response = JSON.parse(req.responseText);
        if (Array.isArray(response)) {
            updateList(response);
        } else {
            displayError(response.error);
        }
    }

    req.open("POST", "add.do", true);
    req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    req.send("item="+itemTextValue+"&action="+position);

}

function deleteItem(id) {
    var req = new XMLHttpRequest();
    req.onreadystatechange = function() {
        if (req.readyState != 4) return;
        if (req.status != 200) return;
        var response = JSON.parse(req.responseText);
        if (Array.isArray(response)) {
            updateList(response);
        } else {
            displayError(response.error);
        }
    }

    req.open("POST", "delete.do", true);
    req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    req.send("id="+id);
}


// causes list to be re-fetched every 5 seconds
window.setInterval(load, 5000);
