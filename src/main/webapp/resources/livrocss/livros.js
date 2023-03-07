const plus = document.querySelector(".plus"),
minus = document.querySelector(".minus"),
num = document.querySelector(".num");
let a = 1;
var value=1;//global variable 


plus.addEventListener("click", (e)=>{
  a++;
  
  a = (a < 10) ? "0" + a : a;
  num.innerText = a;

  var number = 1;
  value = value + 1;
  var text = e.currentTarget.id;
  console.log(text.substring(4));
  console.log(text);

  document.getElementById("in" + text.substring(4)).value = value;
});

minus.addEventListener("click", (e)=>{
  if(a > 1){
    a--;
    
    a = (a < 10) ? "0" + a : a;
    num.innerText = a;

    value = value - 1;
    const text = e.currentTarget.id;
    console.log(text.substring(5));
  
    document.getElementById("in" + text.substring(5)).value = number;
  }
});