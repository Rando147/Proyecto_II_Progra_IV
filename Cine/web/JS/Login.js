var url = "http://localhost:8080/Cine/";


function login() {
    console.log("LOGIN");
    //if (!loginValidar()) return;
    usuario = {
        id: $("#id").val(),
        password: $("#password").val(),
        type: "LOGIN"
    };
    console.log(usuario);
    let request = new Request(url + 'api/usuario/login', {method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify(usuario)});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#loginDialog #errorDiv"));
            return false;
        }
        usuario = await response.json();
        sessionStorage.setItem('Usuario', JSON.stringify(usuario));
        $('#modalLogin').modal('hide');
        switch (usuario.type) {
            case 'ADMINISTRATOR':
                console.log("LOGIN ADMINISTRATOR");//document.location = url+"listado.html"; 
                break;
            case 'CLIENTE':
                console.log("LOGIN CLIENTE");//document.location = url+"about.html"; 
                console.log(usuario);
                break;
        }
    })();

}
function show() {
    alert('Hola');
}
function loginValidar() {
    $("#loginForm").addClass("was-validated");
    return $("#loginForm").get(0).checkValidity();
}

function logout() {
    let request = new Request(url + 'api/login', {method: 'DELETE', headers: {}});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#loginDialog #errorDiv"));
            return;
        }
        sessionStorage.removeItem('user');
        document.location = url + "about.html";
    })();
}

function errorMessage(status, place) {
    switch (status) {
        case 404:
            error = "Registro no encontrado";
            break;
        case 403:
        case 405:
            error = "Usuario no autorizado";
            break;
        case 406:
        case 405:
            error = "Usuario ya existe";
            break;
        case 500:
            error = "Usuario no existe";
            break;
    }
    ;
    place.html('<div class="alert alert-danger fade show">' +
            '<button type="button" class="close" data-dismiss="alert">' +
            '&times;</button><h4 class="alert-heading">Error!</h4>' + error + '</div>');
    return;
}

function openLoginModal(){
    $('#modalLogin').modal('show');
    
    
    $('#login-cancelar-btn').on('click', ()=>{
        $('#modalLogin').modal('hide');
        
    });
    $('#login-exit-btn').on('click', ()=>{
        $('#modalLogin').modal('hide');
        
    });
     
     
}

function loadLogin() {
    $('#login-aceptar-btn').click(login);
    console.log("LOAD LOGIN");
}

$(loadLogin);