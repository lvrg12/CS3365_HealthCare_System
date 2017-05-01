function openTab(evt, tabName) {
    var i, tabcontent, tablinks;
    
    tabcontent = document.getElementsByClassName("tabcontent");
    
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    
    tablinks = document.getElementsByClassName("tablinks");
    
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    
    document.getElementById(tabName).style.display = "block";
    evt.currentTarget.className += " active";
}

function openInputTab(evt, tabName) {
    var i, inputcontent, inputlinks;
    
    inputcontent = document.getElementsByClassName("inputcontent");
    
    for (i = 0; i < inputcontent.length; i++) {
        inputcontent[i].style.display = "none";
    }
    
    inputlinks = document.getElementsByClassName("inputlinks");
    
    for (i = 0; i < inputlinks.length; i++) {
        inputlinks[i].className = inputlinks[i].className.replace(" active", "");
    }
    
    document.getElementById(tabName).style.display = "inline";
    evt.currentTarget.className += " active";
}

function fillTable(numCols, headName, tableName) {
	var table = document.getElementById(tableName);
    var i, j, row, cell;
    
	for(i = 0; i < 10; i++) {
    	row = addAfter(headName);
        for(j = 0; j < numCols; j++) {
        	cell = row.insertCell(j);
            cell.innerHTML = "PLACEHOLDER";
		}
    }
}

fillTable(5, "PatientAccHead", "PatientAccTable");
fillTable(4, "SchedHead", "SchedTable");
fillTable(8, "TreatRecHead", "TreatRecTable");
fillTable(7, "PayRecHead", "PayRecTable");
fillTable(4, "ReportsHead", "ReportsTable");
fillTable(7, "PatientPayHead", "PatientPayTable");

function addAfter(rowId){
    var target = document.getElementById(rowId);
    var newElement = document.createElement('tr');

    target.parentNode.insertBefore(newElement, target.nextSibling );
    return newElement;
}