const plus = document.querySelector(".plus"),
minus = document.querySelector(".minus"),
num = document.querySelector(".num");

let a = 1;
var value=1;//global variable 
var number = document.getElementById("livrolink").href;

plus.addEventListener("click", (e)=>{
  a++;
  
  a = (a < 10) ? "0" + a : a;
  num.innerText = a;

  
  value = value + 1;
  var text = e.currentTarget.id;

  
  document.getElementById("livrolink").href = number.substring(number.indexOf("cart")-1) + "/" + value;
  
  
  document.getElementById("in" + text.substring(4)).value = value;
});

minus.addEventListener("click", (e)=>{
  if(a > 1){
    a--;
    
    a = (a < 10) ? "0" + a : a;
    num.innerText = a;

    value = value - 1;
    const text = e.currentTarget.id;

    document.getElementById("livrolink").href = document.getElementById("livrolink").getAttribute("href") + "/" + parseInt(text.substring(5))+1;
  
    document.getElementById("in" + text.substring(5)).value = number;
  }
});