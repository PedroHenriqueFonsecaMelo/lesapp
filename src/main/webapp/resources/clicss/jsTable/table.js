let tables = JSON.parse(document.getElementById('json').value);

function changeTable(e) {
       
    document.getElementById("TABLE1").innerHTML = tables[parseInt(e)];

}
