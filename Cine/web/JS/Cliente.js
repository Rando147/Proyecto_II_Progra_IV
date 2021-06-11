/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var url="http://localhost:8080/Cine/";







function register(){
         console.log("LOGIN");
        //if (!loginValidar()) return;
        cliente = {
            id: $("#id").val(),
            password: $("#password").val(),
            nombre:$("#nombre").val(),
            apellidos :$("#apellidos").val(),
            numero_cuenta :$("#numero_cuenta").val(),
            type = "CLIENTE"
        };
        usuario = {
            id: $("#id").val(),
            password: $("#password").val(),
            type = "CLIENTE"
        };
        console.log(cliente);
        let request = new Request(url+'api/cliente/register', {method: 'POST', headers: { 'Content-Type': 'application/json'},body: JSON.stringify(cliente)});
        (async ()=>{
            const response = await fetch(request);
            if (!response.ok) {errorMessage(response.status,$("#loginDialog #errorDiv"));return;}
            usuario = await response.json();
            sessionStorage.setItem('Usuario', JSON.stringify(usuario));
            $('#loginDialog').modal('hide');            
           switch(cliente.type){
               case 'ADMINISTRATOR': console.log("LOGIN ADMINISTRATOR");//document.location = url+"listado.html"; 
                   break;
               case 'CLIENTE': console.log("LOGIN CLIENTE");//document.location = url+"about.html"; 
                   break;
           }                           
        })(); 
    
    }
  function errorMessage(status,place){  
        switch(status){
            case 404: error= "Registro no encontrado"; break;
            case 403: case 405: error="Usuario no autorizado"; break;
            case 406: case 405: error="Usuario ya existe"; break;
            case 500: error="Usuario no existe"; break;        
        };            
        place.html('<div class="alert alert-danger fade show">' +
        '<button type="button" class="close" data-dismiss="alert">' +
        '&times;</button><h4 class="alert-heading">Error!</h4>'+error+'</div>');
        return;        
    }  
  
  function load(){
        //let request = new Request(url+'index.html', {method: 'GET'});
//        (async ()=>{
//            const response = await fetch(request);
//            if (!response.ok) {errorMessage(response.status,$("#loginDialog #errorDiv"));return;}
//            content = await response.text();
            //$('body').append(content); 
            $("#register").click(register);
            //$("#logout").click(logout);
            console.log("LOAD CLIENTE");
//        })();     
  }
  
  $(load);  
