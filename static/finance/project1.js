const URL = "http://localhost:8080/project1/";
const params = new URLSearchParams(window.location.search);
let myId = params.get('id');


let addReimbButton = document.getElementById("addReimbButton");
let myReimbsButton = document.getElementById("myReimbsButton");
let allReimbsButton = document.getElementById("allReimbsButton");
let viewByStatusButton = document.getElementById("viewByStatusButton");
let newReimbForm = document.getElementById("newReimbForm");
let addButton = document.getElementById("addButton");
let userTable2 = document.getElementById("userTable2");
let choiceButton = document.getElementById("choiceButton");
let updateButton = document.getElementById("updateButton");

choiceButton.onclick = chooseViewByStatus;
updateButton.onclick = updateReimb;

function reimbsTable(data) {
  let tbody = document.getElementById("tableBody2");

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
  

    async function updateReimb(){
      let id = document.getElementById("id2").value;
      let statusId = document.querySelector('input[name="viewing"]:checked').value;
      let myUpdate = {
        id:id,
        statusId:statusId,
        myId:myId,
      }

      let response = await fetch(URL+"finance/"+id+"/"+statusId+"/"+myId, {
        method:'POST',
        body:JSON.stringify(myUpdate),
      });
    }

    async function chooseViewByStatus(){
      let myChoice =  document.querySelector('input[name="viewing"]:checked').value;

      let response = await fetch(URL+"finance/"+myChoice);

    if(response.status===200){
      let data = await response.json();
      reimbsTable(data);
    }else{
      console.log("Could not find any reimbursements");
    }
    }