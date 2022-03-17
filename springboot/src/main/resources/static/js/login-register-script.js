const signinBtn=document.getElementById("signin");
const signupBtn=document.getElementById("signup");
const firstForm=document.getElementById("form1");
const secondForm=document.getElementById("form2");
const conrainer= document.querySelector(".container")

signinBtn.addEventListener("click",()=>{
    conrainer.classList.remove("right-panel-active")
})

signupBtn.addEventListener("click",()=>{
    conrainer.classList.add("right-panel-active")
})

firstForm.addEventListener("submit",(e)=>e.preventDefault())
secondForm.addEventListener("submit",(e)=>e.preventDefault())