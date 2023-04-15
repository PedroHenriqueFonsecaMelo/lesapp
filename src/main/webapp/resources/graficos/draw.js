google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(drawChart);
let customObjectList = [["Coluna", "Valor"]];
let j = JSON.parse(document.getElementById('json').value);

for (let i = 0; i < j.length; i++) {
    customObjectList.push(j[i]);
}

for (let i = 1; i < customObjectList.length; i++) {
    customObjectList[i][1] = parseInt(customObjectList[i][1]);
}


function drawChart() {
   
    
    var data = google.visualization.arrayToDataTable(customObjectList);


var options = {
    title: 'Vendas'
};

var chart = new google.visualization.PieChart(document.getElementById('piechart'));

chart.draw(data, options);
}
