
var url = "http://localhost:8080/Cine/";
var peliculas = [];


//-----------------------------------------pelicula------------------------------------------------------------------------------------------------------------------------------------------------------------
var pelicula = {id: "", nombre: "", duracion: "", descripcion: "", precio: ""};
//var image = {base64Image :"Images/spiderman.jpg"};

function loadPelicula() {
    pelicula = Object.fromEntries((new FormData($("#forPeli").get(0))).entries());
}
function resetPelicula() {
    pelicula = {id: "", nombre: "", duracion: "", descripcion: "", precio: ""};
    $('#nombrepeli').val('');
    $('#descripeli').val('');
    $('#duracionpeli').val('');
    $('#preciopeli').val('');

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
        resetPelicula();
        pelicula = await response.json();
        $('#modalPelicula').modal('hide');
        addImagen();
        resetPelicula();

    })();
    setTimeout(() => {
        fetchAndListMovies();
    }, 400);

}

function loadMoviesListingAdmin() { //Dentro de este metodo deberia ir el request al API para solicitar las peliculas de la cartelera

    var listaPeliculasContainer = $("#movie-cards-container");
    resetMoviesContainer();

    peliculas.forEach((item) => {
        var movieID = item.id;
        var movieName = item.nombre;
        var movieDuration = item.duracion;
        var movieDescripcion = item.descripcion;//"data:image/jpg;base64,${image.base64Image}"
        var movieStatus = item.estado;
        var buttonDelete = `<button id="admin-movie-action" type="button" class="btn btn-danger movie-action">Borrar</button>`;
        var buttonAdd = `<button id="admin-movie-action" type="button" class="btn btn-success movie-action">Agregar</button>`;
        var actionButton = (movieStatus === "0") ? buttonAdd : buttonDelete;
        var newListItem = $("<div />");
        var adminCard = `<div class="col">
                        <div class="card shadow-sm">
                            
                            <img  src="` + url + `api/cartelera/` + movieID + `/imagen" class="card-img-top" alt="">
                            <div class="card-body">
                                <p class="card-text">`
                + movieDescripcion +
                `</p>
                                <div class="d-flex justify-content-between align-items-center" >
                                    <div class="btn-group">`
                                        
                                        +actionButton+
                                    `</div>
                                    <small class="text-muted">` + movieDuration + `</small>
                                </div>
                            </div>
                        </div>
                    </div>`;
        newListItem.html(adminCard);

        if (movieStatus === "1") {
            newListItem.find('#admin-movie-action').on("click", () => {
                loadDeleteMovieModal(movieID, movieName);
            });
        }
        else{
            newListItem.find('#admin-movie-action').on("click", () => {
                loadActivateMovieModal(movieID, movieName);
            });
        }
        listaPeliculasContainer.append(newListItem);
    });
}
function fetchAndListMoviesAdmin() {
    peliculas = [];
    resetMoviesContainer();
    let request = new Request(url + 'api/admin/peliculas', {method: 'GET', headers: {}});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#buscarDiv #errorDiv"));
            return;
        }
        peliculas = await response.json();
        loadMoviesListingAdmin();

    })();
}


function loadDeleteMovieModal(idPelicula, movieName) { //Desactiva la pelicula para que esta no aparezca en cartelera
    $('#modalDeleteMovie').find('.modal-body').empty();//Borra el body del modal
    var modal = $('#modalDeleteMovie');
    var mensaje = "<p>Esta seguro que desea borrar la pelicula " + movieName + "?</p>";
    modal.find('.modal-body').append(mensaje); //Busca el modal-body y agrega el mensaje
    $('#modalDeleteMovie').modal('show');

    //Cargar listener para boton de aceptar
    var btnAceptar = modal.find('#action-movie-aceptar-btn');
    btnAceptar.on("click", () => {
        deleteMovie(idPelicula);
        btnAceptar.off("click");
        modal.modal("hide");
    });
}

function deleteMovie(id) {
    //Todo... ejecutar request para borrar pelicula
    let request = new Request(url + 'api/admin/borrar/' + id, {method: 'DELETE', headers: {}});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#buscarDiv #errorDiv"));
            return;
        }
        //resultado = await response.json();
        fetchAndListMoviesAdmin();
    })();
}

function loadActivateMovieModal(idPelicula, movieName) {//Activa la pelicula para que esta aparezca en cartelera
    $('#modalDeleteMovie').find('.modal-body').empty();//Borra el body del modal
    var modal = $('#modalDeleteMovie');
    var mensaje = "<p>Esta seguro que desea agregar la pelicula " + movieName + " a la cartelera?</p>";
    modal.find('.modal-body').append(mensaje); //Busca el modal-body y agrega el mensaje
    $('#modalDeleteMovie').modal('show');

    //Cargar listener para boton de aceptar
    var btnAceptar = modal.find('#action-movie-aceptar-btn');
    btnAceptar.on("click", () => {
        activateMovie(idPelicula);
        //Una vez que se ejecuta el comando anterior se ejecutan las siguientes dos acciones para quitar el listener y ademas ocultar el modal
        btnAceptar.off("click");
        modal.modal("hide");
    });
}

function activateMovie(id) {
    //Todo... ejecutar request para activar pelicula

    p = {idPelicula:id};
    pJson = JSON.stringify(p);
    let request = new Request(url + 'api/admin/activar', {method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify(p)});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#buscarDiv #errorDiv"));
            return;
        }
        //resultado = await response.json();
        fetchAndListMoviesAdmin();
    })();
}

function addImagen() {
    var imagenData = new FormData();
    imagenData.append("id_pelicula", pelicula.id);
    imagenData.append("image", $("#imagen").get(0).files[0]);
    let request = new Request(url + 'api/admin/' + pelicula.id + "/image", {method: 'POST', body: imagenData});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#add-modal #errorDiv"));
            return;
        }
    })();
    $('#imagen').val('');
}

function loadImage() {
    //'"+url+"api/personas/"+persona.cedula+"/imagen'  
    console.log("imagenData");
    var imagenData = new FormData();
    var id = 1;//pelicula.idPelicula
    let request = new Request(url + 'api/admin/' + id + "/imagen", {method: 'GET', headers: {}});
    //let request = new Request(url + '/api/admin/1/image' ,{ method: 'GET', headers: { }});
    (async () => {
        const response = await fetch(request);   //   /+"data:image/jpg;base64,${imagen.base64Image}" +
        if (!response.ok) {
            errorMessage(response.status, $("#buscarDiv #errorDiv"));
            return;
        }
        image = await response.json();
    })();

    //document.body.appendChild(image);
//    
//    // Get the form element withot jQuery
//var form = document.getElementById("myAwesomeForm");
//
//var ImageURL = "data:image/gif;base64,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";
//// Split the base64 string in data and contentType
//var block = ImageURL.split(";");
//// Get the content type of the image
//var contentType = block[0].split(":")[1];// In this case "image/gif"
//// get the real base64 content of the file
//var realData = block[1].split(",")[1];// In this case "R0lGODlhPQBEAPeoAJosM...."
//
//// Convert it to a blob to upload
//var blob = b64toBlob(realData, contentType);
//
//// Create a FormData and append the file with "image" as parameter name
//var formDataToUpload = new FormData(form);
//formDataToUpload.append("image", blob);
//    


    //'"+url+"api/personas/"+persona.cedula+"/imagen'  
    /*var imagenData = new FormData();
     let request = new Request(url + 'api/admin/' + pelicula.idPelicula + "/image",{method: 'GET', headers: { }});*/
    //let request = new Request(url + '/api/admin/1/image' ,{ method: 'GET', headers: { }});
    /*(async ()=>{
     const response = await fetch(request);      /+"data:image/jpg;base64,${imagen.base64Image}" +
     if (!response.ok) {errorMessage(response.status,$("#buscarDiv #errorDiv"));return;}
     imagenData = await response.MULTIPART_FORM_DATA();     
     console.log(imagenData);
     })();*/
}


//-----------------------------------------sala---------------------------------------------------------------------------------------------------------------------------------------------------------
var sala = {idSala: "", asientos: ""};

function loadSala() {
    sala = Object.fromEntries((new FormData($("#forSala").get(0))).entries());
}

function resetSala() {
    sala = {idSala: "", asientos: ""};
    $('#idsala').val('');
    $('#cantidadAsientos').val('');

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

//-----------------------------------------Cartelera--------------------------------------------------------------------------------------------------------------------------------------------------
var cartelera = {fecha: "", Hinicio: "", Hfinal: "", IdpeliC: "", IdSalaC: ""};

function loadCartelera() {
    cartelera = Object.fromEntries((new FormData($("#forCart").get(0))).entries());
}

function resetCartelera() {
    cartelera = {fecha: "", Hinicio: "", Hfinal: "", IdpeliC: "", IdSalaC: ""};
    $('#fecha').val('');
    $('#Hinicio').val('');
    $('#hfinal').val('');
    $('#idP').val('');
    $('#idS').val('');
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

//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


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
    //$("#peliculaRegister").click(loadImage);
    //$("#peliculaRegister").click(addImagen);
    $("#peliculaRegister").click(Pelicula);
    $("#salaRegister").click(Sala);
    $("#cartRegister").click(Cartelera);
}

$(load);