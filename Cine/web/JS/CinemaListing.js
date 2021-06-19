var url = "http://localhost:8080/Cine/";
var totalPagar = 0;
var seatsArray = [];
var picAdress = "Images/spiderman.jpg";
//var image = {    base64Image: ""};
var imageD = new Image();
var image = new Image();
var peliculas = new Array();
var carteleras = new Array();
var salas = new Array();
var tickets = new Array();
var pelicula = {id: "", nombre: "", duracion: "", descripcion: "", precio: ""};
var usuario;


function loadMoviesListing() { //Dentro de este metodo deberia ir el request al API para solicitar las peliculas de la cartelera

    var listaPeliculasContainer = $("#movie-cards-container");
    resetMoviesContainer();


    peliculas.forEach((item) => {
        var movieID = item.id;
        var movieName = item.nombre;
        var movieDuration = item.duracion;
        var movieDescripcion = item.descripcion;//"data:image/jpg;base64,${image.base64Image}"
        //var movieStatus = "1";//item.estado;

        var newListItem = $("<div />");
        var clientCard =
                `<div class="col">
                        <div class="card shadow-sm">
                            <img   src="` + url + `api/cartelera/` + movieID + `/imagen" class="card-img-top" alt="">
                            <div class="card-body">
                                <p class="card-text">`
                + movieDescripcion +
                `</p>
                                <div class="d-flex justify-content-between align-items-center" >
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-sm btn-outline-secondary" id="view-movie" style="background-color: #1d2185; color:white;">
                                            View
                                        </button>
                                    </div>
                                    <small class="text-muted">` + movieDuration + `</small>
                                </div>
                            </div>
                        </div>
                    </div>`;


        newListItem.html(clientCard);
        newListItem.find("#view-movie").on("click", () => {
            view(movieName, movieID, item.precio);
        });
        var btn = newListItem.find("#view-movie");
        btn.hide();
        newListItem.on("mouseover", () => {
            btn.show();
        });
        newListItem.on("mouseleave", () => {
            btn.hide();
        });
        listaPeliculasContainer.append(newListItem);
    });
}


function fetchAndListMovies() {
    resetPeliculas();
    resetMoviesContainer();
    let request = new Request(url + 'api/cartelera/peliculas', {method: 'GET', headers: {}});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#buscarDiv #errorDiv"));
            return;
        }
        peliculas = await response.json();
        fetchAndListCarteleras();
        fetchAndListSalas();
        fetchAndListTickets();
        setTimeout(() => {
            loadMoviesListing();
        }, 400);
    })();
}
function fetchAndListCarteleras() {
    resetCarteleras();
    let request = new Request(url + 'api/cartelera/carteleras', {method: 'GET', headers: {}});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#buscarDiv #errorDiv"));
            return;
        }
        carteleras = await response.json();
    })();
}
function fetchAndListSalas() {
    resetSalas();
    let request = new Request(url + 'api/cartelera/salas', {method: 'GET', headers: {}});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#buscarDiv #errorDiv"));
            return;
        }
        salas = await response.json();
    })();
}
function fetchAndListTickets() {
    resetTickets();
    //resetMoviesContainer();
    let request = new Request(url + 'api/cartelera/tickets', {method: 'GET', headers: {}});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#buscarDiv #errorDiv"));
            return;
        }
        tickets = await response.json();
        //loadMoviesListing();

    })();
}
function resetPeliculas() { //Esta funcion solo se utiliza para volver a poner el array donde se guardan los asientos 
    peliculas = [];
}
function resetCarteleras() { //Esta funcion solo se utiliza para volver a poner el array donde se guardan los asientos 
    carteleras = [];
}
function resetSalas() { //Esta funcion solo se utiliza para volver a poner el array donde se guardan los asientos 
    salas = [];
}
function resetTickets() { //Esta funcion solo se utiliza para volver a poner el array donde se guardan los asientos 
    tickets = [];
}
function resetSeatsArray() { //Esta funcion solo se utiliza para volver a poner el array donde se guardan los asientos 
    seatsArray = [];
}
function resetTotalPagar() { //Esta funcion solo se utiliza para volver a poner el array donde se guardan los asientos 
    totalPagar = 0;
}
function view(movieName, idPelicula, precio) {
    //Aqui va el request al API para que retorne los datos de la pelicula indicada en el ID
    listaHorariosJSON = carteleras;
    $("#modalLoginLabel").empty();
    $("#modalLoginLabel").text(movieName + Array(20).fill('\xa0').join('') + ' Horarios disponibles');
    var listaHorarios = $("#lista-horarios").empty();//Lista de horarios del modal se limpia //table

    listaHorariosJSON.forEach((item) => {
        if (idPelicula === item.pelicula) {
            var newListItem = $("<li></li>");
            newListItem.html('<a href="javascript:void(0);" id="horario">' + item.fecha_funcion
                    + ' Hora Inicio ' + item.hora_inicio + ' Hora fin ' + item.hora_fin + '</a>');
            newListItem.find('#horario').on('click', () => {
                butacas(movieName, item, precio);
            });
            listaHorarios.append(newListItem);
        }
    });
    $('#modalHorarios').modal('show');
}
var precioSeat = 0;
var seatSelected = 0;
function resetPrecioSeat() { //Esta funcion solo se utiliza para volver a poner el array donde se guardan los asientos 
    precioSeat = 0;
}
function resetSeatSelected() { //Esta funcion solo se utiliza para volver a poner el array donde se guardan los asientos 
    seatSelected = 0;
}
function butacas(movieName, movieCartelera, preciom) {
    resetSeatsArray(); //movieName
    resetPrecioSeat();
    resetSeatSelected();
    resetTotalPagar();
    $("#exampleModalToggleLabel2").empty();
    $("#exampleModalToggleLabel2").text(movieName + Array(20).fill('\xa0').join('') + '  Butacas disponibles');
    //exampleModalToggleLabel2
    var informacionButacasJSON = {
        idCartelera: "",
        cantidadAsientos: "",
        precio: "",
        ocupados: []
    };
    informacionButacasJSON.precio = preciom;
    precioSeat = parseInt(preciom);
    salas.forEach((itemS) => {
        if (movieCartelera.sala === itemS.sala) {
            informacionButacasJSON.cantidadAsientos = itemS.butacas;
            informacionButacasJSON.idCartelera = movieCartelera.id;
            //return;
        }
    });
    tickets.forEach((item) => {
        if (movieCartelera.id === item.cartelera) {
            var seat = [];
            seat = item.butaca.split(",");
            for (var i = 0; i < seat.length; i++) {
                var butaka = seat[i];
                informacionButacasJSON.ocupados.push(butaka);
            }
            //return;
        }
    });

    loadSeats(informacionButacasJSON);

    $('#modalButacas').modal('show');
}
function loadSeats(informacionButacasJSON) {//Recibe JSON con informacion necesaria

    resetSeats(); //Lo unico que hace es borrar el arreglo donde guardan los asientos seleccionados por el usuario
    $("#screen-seats-container").remove();//Borra el 'body' del modal para que este no se duplique cada vez que se abre la pestaña de butacas

    //Carga de variables desde JSON
    var cantidadAsientos = informacionButacasJSON.cantidadAsientos;
    precioSeat = informacionButacasJSON.precio;
    var ocupiedSeats = informacionButacasJSON.ocupados;

    var totalColumnas = 8; //Total de columnas de la sala
    const totalFilas = parseInt(cantidadAsientos / totalColumnas);
    const asientosUltimaFilaIncompleta = cantidadAsientos % totalColumnas;//Cuantos ascientos estan en la ultima fila

    var container = $("<div class='container' id='screen-seats-container'></div>");
    var screen = $('<div class = "screen"></div>');
    container.append(screen);

    for (i = 0; i < totalFilas; i++) {//Crea las filas y columnas de las butacas
        var newRow = $("<div class='row'></div>");
        for (j = 0; j < totalColumnas; j++) {
            var id = i + "-" + j;
            var newSeat = $("<div class='seat' id=" + id + "></div>");
            if (ocupiedSeats.includes(id)) {
                newSeat.addClass("occupied");
            }

            newRow.append(newSeat);
        }
        container.append(newRow);
    }

    if (asientosUltimaFilaIncompleta !== 0) {//En caso de que la ultima fila no este completamente llena de butacas, se ejecuta lo siguiente
        var newRow = $("<div class='row'></div>");
        for (i = 0; i < asientosUltimaFilaIncompleta; i++) {
            var newSeat = $("<div class='seat'></div>");
            if (ocupiedSeats.includes(id)) {
                newSeat.addClass("occupied");
            }
            newRow.append(newSeat);
        }
        container.append(newRow);
    }
    $('#modalButacas').find("#comprar").on("click", () => {
        comprar(informacionButacasJSON.idCartelera);//pase el id de la cartelera al cual se hace la compra

    });
    // $("#comprar").click(comprar(informacionButacasJSON.idCartelera));
    $(".theather-container").append(container);
    $(".seat:not(.occupied)").click(setSelected_Unselected);//Agrega listener para poder cambiar los seleccionados
}
function resetSeats() { //Esta funcion solo se utiliza para volver a poner el array donde se guardan los asientos 
    seatsArray = [];
}
function addSeat() {
    totalPagar += parseInt(precioSeat);
    seatSelected += 1;
    changeTicketInfo();
}
function removeSeat() {
    totalPagar -= parseInt(precioSeat);
    seatSelected -= 1;
    changeTicketInfo();
}
function changeTicketInfo() {
    $("#count").html(seatSelected);
    $("#total").html(totalPagar);
}
function addSeatToArray(id) {
    seatsArray.push(id);
    console.log(seatsArray);
}
function removeSeatFromArray(id) {
    var pos = seatsArray.indexOf(id);
    seatsArray.splice(pos, 1);
    console.log(seatsArray);
}
function setSelected_Unselected() {
    var classes = $(this).attr("class");
    var classList = classes.split(/\s+/);
    if (classList.includes("selected")) {
        var id = $(this).attr("id");
        $(this).removeClass("selected");
        removeSeat();
        removeSeatFromArray(id);

    } else {
        var id = $(this).attr("id");
        $(this).addClass("selected");
        addSeat();
        addSeatToArray(id);
    }
}
function comprar(idCartelera) {

    try {
        usuario = sessionStorage.getItem("Usuario");
    } catch (exception) {
        //console.log(exception);
    }

    if (usuario !== null) {
        // var ticket;
        let request = new Request(url + 'api/usuario/' + idCartelera + '/comprar', {method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify(seatsArray)});
        (async () => {
            const response = await fetch(request);
            if (!response.ok) {
                errorMessage(response.status, $("#loginDialog #errorDiv"));
                return;
            }
            ticket = await response.json();
            generatePDF(ticket[0], ticketHeaders, ticketKeys);

        })();
        setTimeout(() => {
            fetchAndListTickets();
            $('#modalButacas').find("#comprar").off('click');
            $('#modalButacas').modal('hide');
            $('#modalHorarios').modal('hide');
            //fetchAndListMovies();
        }, 1000);

        resetSeatsArray(); //movieName
        resetPrecioSeat();
        resetSeatSelected();
        resetTotalPagar();
    } else {
        $('#modalRegistro').modal('show');
        try {
            usuario = sessionStorage.getItem("Usuario");
        } catch (exception) {
        }
    }
}

function loadImage() { 
    console.log("imagenData");
    var imagenData = new FormData();
    var id = 1;//pelicula.idPelicula
    let request = new Request(url + 'api/admin/' + id + "/imagen", {method: 'GET', headers: {}});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#buscarDiv #errorDiv"));
            return;
        }
        image = await response.json();
    })();
    var fs = require('fs');
// string generated by canvas.toDataURL()
    var img = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABQAAAAUCAYAAACNiR0"
            + "NAAAAKElEQVQ4jWNgYGD4Twzu6FhFFGYYNXDUwGFpIAk2E4dHDRw1cDgaCAASFOffhEIO"
            + "3gAAAABJRU5ErkJggg==";
// strip off the data: url prefix to get just the base64-encoded bytes
    var data = img.replace(/^data:image\/\w+;base64,/, "");
    var buf = new Buffer(data, 'base64');
    fs.writeFile('image.png', buf);
}

function resetMoviesContainer() {//Simplemente borra lo que tiene el array del Json
    $("#movie-cards-container").empty();
}

function buscar() {

    try {
        usuario = sessionStorage.getItem("Usuario");
    } catch (exception) {
        
    }
    if(usuario === null || usuario.type === "CLIENTE"){

        var listaPeliculasContainer = $("#movie-cards-container");
        var x = $("#textoB").val();
        var low = x.toUpperCase();
        resetPrecioSeat();
        resetSeatSelected();
        resetSeatsArray();
        resetTotalPagar();
        resetMoviesContainer();
        peliculas.forEach((item) => {
            var a = item.nombre.toUpperCase();
            if (a.includes(low)) {

                var movieID = item.id;
                var movieName = item.nombre;

                var movieDuration = item.duracion;
                var movieDescripcion = item.descripcion;//"data:image/jpg;base64,${image.base64Image}"
                var newListItem = $("<div />");
                var clientCard = `<div class="col">
                        <div class="card shadow-sm">
                            
                            <img   src="` + url + `api/cartelera/` + movieID + `/imagen" class="card-img-top" alt="">
                            <div class="card-body">
                                <p class="card-text">`
                        + movieDescripcion +
                        `</p>
                                <div class="d-flex justify-content-between align-items-center" >
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-sm btn-outline-secondary" id="view-movie" style="background-color: #1d2185; color:white;">
                                            View
                                        </button>
                                        
                                    </div>
                                    <small class="text-muted">` + movieDuration + `</small>
                                </div>
                            </div>
                        </div>
                    </div>`;

                newListItem.html(clientCard);
                newListItem.find("#view-movie").on("click", () => {
                   view(movieName, movieID, item.precio);
                });
                listaPeliculasContainer.append(newListItem);
            } else if (x === "" || x === " ") {
                loadMoviesListing();
            }
        });
    }else if (usuario.type === "ADMINISTRADOR") {
        loadMoviesListingAdmin();
    } 
}

function load() {
    fetchAndListMovies();
    $("#buscaboton").click(buscar);

}
$(load);