let customObjectList = [];

let googleData = JSON.parse(document.getElementById('json').value);
let select = document.getElementById('AnosGraph');

google.charts.load('50', {'packages':['bar']});
google.charts.setOnLoadCallback(drawChart);


document.getElementById("relatorio-tab").addEventListener("click", function() {

  setTimeout(drawChart, 1000);;

});

for (let i = 1; index < googleData.length; i++){
  for (let j = 0; j < googleData[i].length; j++) {
    if( googleData[i][j] != null){
      googleData[i][j] =  googleData[i][j].substring(1, googleData[i][j].length-1);
    }
  }
}
function drawChart(dados) {


   let data = null;
   
   if(dados == undefined){

      for (let i = 1; index < googleData.length; i++){
        for (let j = 0; j < googleData[i].length; j++) {
          if( googleData[i][j] != null && googleData[i][j].includes("'")){
            googleData[i][j] =  googleData[i][j].substring(1, googleData[i][j].length-1);
          }
        }
      }

    data = google.visualization.arrayToDataTable(googleData);

   } else {

    for (let i = 1; index < dados.length; i++){
      for (let j = 0; j < dados[i].length; j++) {
        if( dados[i][j] != null && dados[i][j].includes("'")){
          dados[i][j] =  dados[i][j].substring(1, dados[i][j].length-1);
        }
      }
    }

    data = google.visualization.arrayToDataTable(dados);
   }
 
 
  var options = {
    chart: {
      title: 'Tabela de vendas por livro',
      subtitle: 'Sales, Expenses, and Profit'
    }
  };

  var chart = new google.charts.Bar(document.getElementById('piechart'));

  chart.draw(data,options);
}

const selectElement = document.getElementById("AnosGraph");

selectElement.addEventListener("change", (event) => {
  let filtros = event.target.value;
  let result = googleData.slice(0,1);

  if(filtros == "null" ){
     drawChart(googleData);

  } else{

   for (let i = 1; i < googleData.length; i++) {
      if(googleData[i][0].includes(filtros) || googleData[i][0].includes(filtros.toString())){
        result.push(googleData[i]);
      }
    }

    drawChart(result);

  }
  
});

function Filtrar(filtros){
  
  
};
drawChart();