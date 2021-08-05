const URL = "http://localhost:8080/project1/";
const params = new URLSearchParams(window.location.search);
let myId = params.get('id');


let addReimbButton = document.getElementById("addReimbButton");
let myReimbsButton = document.getElementById("myReimbsButton");
let allReimbsButton = document.getElementById("allReimbsButton");
let viewByStatusButton = document.getElementById("viewByStatusButton");
let newReimbForm = document.getElementById("newReimbForm");
let addButton = document.getElementById("addButton");
let userTable = document.getElementById("userTable");
// document.getElementById("logInForm").remove();

addReimbButton.onclick = getNewReimb;
addButton.onclick = addReimb;
myReimbsButton.onclick = myReimbs;

function getNewReimb(){
  document.getElementById("newReimbForm").display="block";
  document.getElementById("addButton").display="block";
  let newAmount = document.getElementById("amount").value;
  let newDescription = document.getElementById("description").value;
  let newAuthor = myId;
  let newTypeId = document.querySelector('input[name="reason"]:checked').value;

  let reimburse = {
    amount:newAmount,
    description:newDescription,
    author:newAuthor,
    typeId:newTypeId
  }
  

  return reimburse;
}
async function addReimb(){
  let reimburse = getNewReimb();

  let response = await fetch(URL+'employee', {
    method:'POST',
    body:JSON.stringify(reimburse),
});

if(response.status===201){
  console.log("Reimbursement successfully created");
}else {
  console.log("Reimbursement could not be created");
}
  document.getElementById('amount').value='';
  document.getElementById('description').value='';
}

async function myReimbs(){
  let input = myId;
  let response = await fetch(URL+"employee/"+input);

  if(response.status===200){
    let data = await response.json();
    populateReimbTable(data);
  }else{
    console.log("Could not retrieve your reimbursements");
  }
}

function populateReimbTable(data) {
  let tbody = document.getElementById("tableBody");

  tbody.innerHTML = "";

  for(let reimbursement of data){
    let row = document.createElement("tr");

    for(let cell in reimbursement){
      let td = document.createElement("td");
      td.innerText=reimbursement[cell];
      row.appendChild(td);
      }
      tbody.appendChild(row);
    }
    
  }


