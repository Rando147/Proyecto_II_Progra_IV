
var url="http://localhost:8080/Cine/";


function register(){
         console.log("REGISTER");
        //if (!loginValidar()) return;
        cliente = {
            id: $("#idR").val(),
            password: $("#passwordR").val(),
            nombre:$("#nombre").val(),
            apellidos :$("#apellidos").val(),
            numero_cuenta :$("#numero_cuenta").val(),
            type: "CLIENTE"
        };
        usuario = {
            id: "DEFAULT",
            password: "DEFAULT",
            type: "DEFAULT"
        };
        console.log(cliente);
        console.log(usuario);
        let request = new Request(url+'api/usuario/register', {method: 'POST', headers: { 'Content-Type': 'application/json'},body: JSON.stringify(cliente)});
        (async ()=>{
            const response = await fetch(request);
            if (!response.ok) {errorMessage(response.status,$("#loginDialog #errorDiv"));return false;}
            usuario = await response.json();
            sessionStorage.setItem('Usuario', JSON.stringify(usuario));
            $('#modalRegistro').modal('hide');            
           switch(cliente.type){
               case 'ADMINISTRATOR': console.log("LOGIN ADMINISTRATOR");//document.location = url+"listado.html"; 
                   break;
               case 'CLIENTE': console.log("LOGIN REGISTER CLIENTE");//document.location = url+"about.html"; 
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
        let request = new Request(url+'registro.html', {method: 'GET'});
        (async ()=>{
            const response = await fetch(request);
            if (!response.ok) {errorMessage(response.status,$("#loginDialog #errorDiv"));return;}
            content = await response.text();
            $('body').append(content); 
            $("#register").click(register);
            //$("#logout").click(logout);
            console.log("LOAD CLIENTE");
        })();     
  }
  
  $(load);  
