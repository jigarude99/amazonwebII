function $(id){
  return document.getElementById(id);
}
window.onload = () => {
    const user = $("username");
    const name = $('name');
    const cedula = $('cedula');
    const phone = $('phone');
    const dataUser = JSON.parse(localStorage.getItem("userInfo"));
    user.innerHTML = '<i class="material-icons left">account_circle</i>'+ dataUser.username;
    name.value = dataUser.name;
    cedula.value = dataUser.cedula;
    phone.value = dataUser.phone;
}
function update() {
  const dataUser = JSON.parse(localStorage.getItem("userInfo"));
  const data = {
    name:$('name').value,
    cedula:$('cedula').value,
    username:dataUser.username,
    phone:$('phone').value,
    password:$('password').value
  },
  params = {
    method: "PUT", 
    headers: new Headers({'Content-Type': 'application/json'}), 
    body:JSON.stringify(data) 
  }
  fetch("./../edit", params)
  .then(resp => resp.json())
  .then(resp => {
    if(resp.status === 200){
      localStorage.setItem("userInfo",JSON.stringify(resp.data));
      location.href = "./../views/dashboard.html";
    } else {
      M.toast({ html: resp.message+", status("+resp.status+")", inDuration:500, outDuration:500 })
    }
  });
}
  function deleteUser(){
    const dataUser = JSON.parse(localStorage.getItem("userInfo"));

    const data = {
      username:dataUser.username,
      password:$('password').value
    },
    params = {
      method: "DELETE", 
      headers: new Headers({'Content-Type': 'application/json'}), 
      body:JSON.stringify(data) 
    }
    fetch("./../edit", params)
    .then(resp => resp.json())
    .then(resp => {
      if(resp.status === 200){
        localStorage.clear();
        location.href = "./../";
      } else {
        M.toast({ html: resp.message+", status("+resp.status+")", inDuration:500, outDuration:500 })
      }
    });    

  }
$('btn').hidden = true
  function check() {
      if( $('password').value.length > 5) {
        $('btn').hidden = false;
      } else {
        $('btn').hidden = true;
      }
  }
function out() {
  const params = {
    method: "GET", 
    headers: new Headers({'Content-Type': 'application/json'}), 
  }
  fetch("./../logout", params)
  .then(resp => resp.json())
  .then(resp => {
    if(resp.status === 200){
      M.toast({ html: 'Bye!', completeCallback: window.location.href = "./../", inDuration: 500, outDuration: 500 })
    } else {
      M.toast({html: data.message+", status("+data.status+")",inDuration:500,outDuration:500})
    }
  });
}
document.addEventListener('DOMContentLoaded', function() {
  M.updateTextFields();
});