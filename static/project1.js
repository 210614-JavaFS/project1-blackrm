const URL = "http://localhost:8080/project1/";

let logInButton = document.getElementById("logInButton");

logInButton.onclick = getUser;

async function getUser(){
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;

    let response = await fetch(URL+"login/"+username+"/"+password);

    if(response.status===200){
        let user = await response.json();
        if(user.password != null){
            console.log('you have successfully logged in');
        }
        else{
            console.log('user not found');
        }
    }
}

async function allReimbursements(){
    let response = await fetch(URL+'all');
  
    if(response.status===200){
      let data = await response.json();
      populateReimbursements(data);
    }else{
      console.log('Reimbursements could not be found');
    }
  }

  function populateReimbursements(data){
    let tbody = document.getElementById("tableBody");
  
    tbody.innerHTML = "";
  
    for(let reimbursement of data){
      let row = document.createElement("tr");
  
      for(let cell in reimbursement){
        let td = document.createElement("td");
        td.innerText=reimbursement[cell];
      }
      row.appendChild(td);
  
      tbody.appendChild(row);
    }
  }

