
var url = "http://localhost:8080/Cine/";


  
//-----------------------------------------pelicula-------------------------------------------------
var pelicula = {pelicula: "", descripcion: "", duracion: ""};

function loadPelicula() {
    pelicula = Object.fromEntries((new FormData($("#forPeli").get(0))).entries());
}

function resetPelicula() {
    pelicula = {pelicula: "", descripcion: "", duracion: ""};
}
function resetSala() {
    sala = {sala: "", asientos: ""};
}

function Pelicula() {
    loadPelicula();
    let request = new Request(url + 'api/admin/pelicula', {method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify(pelicula)});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#loginDialog #errorDiv"));
            return;
        }
        $('#modalPelicula').modal('hide');
        resetPelicula();
    })();
}


//-----------------------------------------sala-------------------------------------------------
var sala = {idSala: "", asientos: ""};

function loadSala() {
    sala = Object.fromEntries((new FormData($("#forSala").get(0))).entries());
}

function resetSala() {
    sala = {idSala: "", asientos: ""};
    limpiar();
}

function Sala() {
    loadSala();
    console.log(sala);
    let request = new Request(url + 'api/admin/sala', {method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify(sala)});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#loginDialog #errorDiv"));
            return;
        }
        $('#modalSala').modal('hide');
        
       
        resetSala();
    })();
}


//-----------------------------------------Cartelera-------------------------------------------------
var cartelera = {fecha:"",Hinicio:"",Hfinal:"",IdpeliC:"",IdSalaC:""};

function loadCartelera(){
    cartelera = Object.fromEntries((new FormData($("#forCart").get(0))).entries());
}

function resetCartelera(){
    cartelera = {fecha:"",Hinicio:"",Hfinal:"",IdpeliC:"",IdSalaC:""};
}

function Cartelera() {
    loadCartelera();
    let request = new Request(url + 'api/admin/cartelera', {method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify(cartelera)});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#loginDialog #errorDiv"));
            return;
        }
        $('#modalCartelera').modal('hide');
        
        resetCartelera();
    })();
}






function addImagen() {
    loadPelicula();
    var imagenData = new FormData();
    imagenData.append("id_pelicula", pelicula.id_pelicula);
    imagenData.append("image", $("#imagen").get(0).files[0]);
    let request = new Request(url + 'api/admin/' + pelicula.idPelicula + "/image", {method: 'POST', body: imagenData});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#add-modal #errorDiv"));
            return;
        }
    })();
}






function load() {
    //let request = new Request(url+'index.html', {method: 'GET'});
//        (async ()=>{
//            const response = await fetch(request);
//            if (!response.ok) {errorMessage(response.status,$("#loginDialog #errorDiv"));return;}
//            content = await response.text();
//            //$('body').append(content); 
//            $("#pelicula").click(Pelicula);
//            $("#sala").click(sala);
//            $("#cartelera").click(cartelera);
//            console.log("LOAD ADMINISTRADOR");
//        })();  


    $("#peliculaRegister").click(Pelicula);
    $("#salaRegister").click(Sala);
    $("#cartRegister").click(Cartelera);
}

$(load);  
