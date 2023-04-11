google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(drawChart);
var customObjectList = JSON.parse('${grafico}');

console.log(customObjectList);

function drawChart() {
   
    
    var data = google.visualization.arrayToDataTable([
        ['Task', 'Hours per Day'],
        customObjectList
    ]);


var options = {
    title: 'Vendas'
};

var chart = new google.visualization.PieChart(document.getElementById('piechart'));

chart.draw(data, options);
}
