const cantidadAsientos = 24;//Valor tomado de la DB el cual define cuantos asientos tendra la sala
const totalColumnas = 8;
const totalFilas = parseInt(cantidadAsientos / totalColumnas);
const asientosUltimaFilaIncompleta = cantidadAsientos % totalColumnas;//Cuantos ascientos estan en la ultima fila
const precioSeat = 1500;
var totalPagar = 0;
var seatSelected = 0;
var seatsArray = [];
var ocupiedSeats = ["0-1", "0-2", "0-3"];

const moviesJson =
        [
            {
                "id_pelicula": "Spiderman01",
                "nombre": "Spiderman",
                "duracion": "2.5 horas",
                "descripcion": "Luego de sufrir la picadura de una araña genéticamente modificada, un estudiante de secundaria tímido y torpe adquiere increíbles capacidades como arácnido. Pronto comprenderá que su misión es utilizarlas para luchar contra el mal y defender a sus vecinos."
            },
            {
                "id_pelicula": "Conjuro1",
                "nombre": "El Conjuro 1",
                "duracion": "2.5 horas",
                "descripcion": "A principios de los años 70, Ed y Lorrain Warren, reputados investigadores de fenómenos paranormales, se enfrentan a una entidad demoníaca al intentar ayudar a una familia que está siendo aterrorizada por una presencia oscura en su aislada granja."
            }
        ];



function loadSeats() {
    resetSeats();
    $("#screen-seats-container").remove();
    var container = $("<div class='container' id='screen-seats-container'></div>");
    var screen = $('<div class = "screen"></div>');
    container.append(screen);
    for (i = 0; i < totalFilas; i++) {
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
    if (asientosUltimaFilaIncompleta !== 0) {
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
    $(".theather-container").append(container);
    $(".seat:not(.occupied)").click(setSelected_Unselected);//Agrega listener para poder cambiar los seleccionados
}

function resetSeats() { //-Esta funcion solo se utiliza para volver a poner el array donde se guardan los asientos 
    seatsArray = [];
}

function addSeat() {
    totalPagar += precioSeat;
    seatSelected += 1;
    changeTicketInfo();
}

function removeSeat() {
    totalPagar -= precioSeat;
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



function resetMoviesContainer() {//Simplemente borra lo que tiene el array del Json
    $("#lista-peliculas").empty();
}

function readMoviesListingJson() {

    var listaPeliculasContainer = $("#movie-cards-container");
    //concole.log(listaPeliculasContainer);
    resetMoviesContainer();
    moviesJson.forEach((item) => {
        var movieID = item.id_pelicula;
        var movieName = item.nombre;
        var movieDuration = item.duracion;
        var movieDescripcion = item.descripcion;
        var newListItem = $("<div />");
        newListItem.html(
                `<div class="col">
                        <div class="card shadow-sm">
                            <svg class="bd-placeholder-img card-img-top" width="100%" height="0px"
                                xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail"
                                preserveAspectRatio="xMidYMid slice" focusable="false">
                                <title>Placeholder</title>
                                <img x="50%" y="50%" dy=".3em" src="Images/spiderman.jpg" class="card-img-top" alt="">
                            </svg>
                            <div class="card-body">
                                <p class="card-text">`
                + movieDescripcion +
                `</p>
                                <div class="d-flex justify-content-between align-items-center">
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-sm btn-outline-secondary"
                                            
                                            id="view-movie">View
                                        </button>
                                        <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
                                    </div>
                                    <small class="text-muted">9 mins</small>
                                </div>
                            </div>
                        </div>
                    </div>`
                );
        newListItem.find("#view-movie").on("click", () => {
            view(movieID);
        });
        listaPeliculasContainer.append(newListItem);
    });
}
function view(idPelicula) {
    alert(idPelicula);
}

function loadMoviesListing() {

}



function addListeners() {
    //$("#movie-link").click(loadSeats);
    //$("#view-movie").click(readMovieJson);
}
function mainLoader() {
    readMoviesListingJson();
}



$(readMoviesListingJson);




