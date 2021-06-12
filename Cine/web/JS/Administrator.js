
var url="http://localhost:8080/Cine/";
var sala={sala:"", asientos:""};
var pelicula = {id:"",nombre:"",descripcion:"",duracion:""};


function loadPelicula(){
    pelicula = Object.fromEntries( (new FormData($("#forPeli").get(0))).entries());
}
function resetPelicula(){
    pelicula = {id:"",nombre:"",descripcion:"",duracion:""};
}

function loadSala(){
        sala = Object.fromEntries( (new FormData($("#forSala").get(0))).entries());
    }
function resetSala(){
    sala={sala:"", asientos:""};
}

function Pelicula(){
//    pelicula = {
//        id: $("#idpeli").val(),
//        nombre:$("#nombrepeli").val(),
//        descripcion:$("#descripeli").val(),
//        duracion:$("#duracionpeli").val(),
//    };
    loadPelicula();
    let request = new Request(url+'api/admin/pelicula', {method: 'POST', headers: { 'Content-Type': 'application/json'},body: JSON.stringify(pelicula)});
        (async ()=>{
            const response = await fetch(request);
            if (!response.ok) {errorMessage(response.status,$("#loginDialog #errorDiv"));return;}
            $('#modalPelicula').modal('hide'); 
            resetPelicula();
        })(); 
}

function Sala(){
    loadSala();
    console.log(sala);
    let request = new Request(url+'api/admin/sala', {method: 'POST', headers: { 'Content-Type': 'application/json'},body: JSON.stringify(sala)});
        (async ()=>{
            const response = await fetch(request);
            if (!response.ok) {errorMessage(response.status,$("#loginDialog #errorDiv"));return;}
            $('#modalSala').modal('hide');  
            resetSala();
        })(); 
}

function Cartelera(){
    cartelera = {
        fecha_funcion:$("#fecha").val(),
        hora_inicio:$("#Hinicio").val(),
        hora_fin:$("#Hfinal").val(),
        pelicula:$("#idP").val(),
        sala:$("#idS").val(),
    };
    let request = new Request(url+'api/admin/cartelera', {method: 'POST', headers: { 'Content-Type': 'application/json'},body: JSON.stringify(cartelera)});
        (async ()=>{
            const response = await fetch(request);
            if (!response.ok) {errorMessage(response.status,$("#loginDialog #errorDiv"));return;}
            $('#modalCartelera').modal('hide');           
        })(); 
}














function load(){
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