let tables = JSON.parse(document.getElementById('json').value);

function changeTable(e) {

    for (let index = 0; index < tables.length; index++) {
        if(tables[index][0] == e){
            document.getElementById("TABLE1").innerHTML = tables[index][1];
        }
    }
}
