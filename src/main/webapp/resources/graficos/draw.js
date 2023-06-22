let customObjectList = [];

let googleData = JSON.parse(document.getElementById('json').value);
let googleDataDetalhes = JSON.parse(document.getElementById('json2').value);
let select = document.getElementById('AnosGraph');

google.charts.load('50', {'packages':['corechart']});

for (let i = 1; i < googleData.length; i++){
  for (let j = 1; j < googleData[i].length; j++) {
    if( googleData[i][j] != null){
      googleData[i][j] = parseInt(googleData[i][j]);
    }
  }
}
for (let i = 1; i < googleDataDetalhes.length; i++){
  for (let j = 1; j < googleDataDetalhes[i].length; j++) {
    if( googleDataDetalhes[i][j] != null){
      googleDataDetalhes[i][j] = parseInt(googleDataDetalhes[i][j]);
    }
  }
}

google.charts.setOnLoadCallback(drawChart);


document.getElementById("relatorio-tab").addEventListener("click", function() {
  
  setTimeout(drawChart, 1000);;

});


function drawChart(dados) {


   let data = null;
   
   if(dados === undefined){

      
    data = google.visualization.arrayToDataTable(googleData);

   } else {

    

    data = google.visualization.arrayToDataTable(dados);

   }
 
 
  var options = {
    chart: {
      title: 'Tabela de vendas por livro',
      subtitle: 'Sales, Expenses, and Profit'
    }
  };
  
  var chart = new google.visualization.LineChart(document.getElementById('piechart'));
  data.sort([{column: 0}, {column: 1}]);
  chart.draw(data,options);
}

const selectElement = document.getElementById("AnosGraph");

selectElement.addEventListener("change", (event) => {
  let filtros = event.target.value;
  let result = googleData.slice(0,1);

  if(filtros == "null" ){
     drawChart(googleData);

  } else{

   for (let i = 1; i < googleDataDetalhes.length; i++) {
      if(googleDataDetalhes[i][0].includes(filtros) || googleDataDetalhes[i][0].includes(filtros.toString())){
        result.push(googleDataDetalhes[i]);
      }
    }

    drawChart(result);

  }
  
});

let elementsArray = document.getElementsByName("dataFiltro");

elementsArray.forEach(function(elem) {
  elem.addEventListener("change",  (event) =>{
    let result = googleData.slice(0,1);

     if (elementsArray[1].value != "" && elementsArray[0].value != ""){
      let dateFrom =  new Date(elementsArray[0].value);
      let dateTo = new Date(elementsArray[1].value);

      dateFrom.setDate(dateFrom.getDate() + 1);
      dateTo.setDate(dateTo.getDate() + 1);
      
      for (let i = 1; i < googleDataDetalhes.length; i++) {
        let googleTodate = new Date(googleDataDetalhes[i][0]);
        googleTodate.setDate(googleTodate.getDate() + 1);
        console.log("i = " + i);
        console.log("googleTodate = " + googleTodate);

        if(googleTodate >= dateFrom && googleTodate <= dateTo){
          result.push(googleDataDetalhes[i]);
          console.log("adicionado");
        }
      }

      
      if(result.length>1){
        drawChart(result);
      }
     }
  });
});
