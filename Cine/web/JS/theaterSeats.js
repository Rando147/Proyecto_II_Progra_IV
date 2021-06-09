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
            "movie": "Spiderman",
            "schedule": "Martes 4pm",
            "sala": 01
        },
        {
            "movie": "Sapo Rene",
            "schedule": "Martes 5pm",
            "sala": 02
        },
        {
            "movie": "Gallo Claudio",
            "schedule": "Mates 6pm",
            "sala": 03
        }
    ];


function loadSeats() {
    resetArray();
    $("#screen-seats-container").remove();
    var container = $("<div class='container' id='screen-seats-container'></div>");
    var screen = $('<div class = "screen"></div>');
    container.append(screen);
    for (i = 0; i < totalFilas; i++) {
        var newRow = $("<div class='row'></div>");
        for (j = 0; j < totalColumnas; j++) {
            var id = i + "-" + j;
            var newSeat = $("<div class='seat' id=" + id + "></div>");
            if (ocupiedSeats.includes(id)){
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
            if (ocupiedSeats.includes(id)){
                newSeat.addClass("occupied");
            }
            newRow.append(newSeat);
        }
        container.append(newRow);
    }
    $(".theather-container").append(container);
    $(".seat:not(.occupied)").click(setSelected_Unselected);//Agrega listener para poder cambiar los seleccionados
}

function resetArray() { //-Esta funcion solo se utiliza para volver a poner el array donde se guardan los asientos 
    seatsArray = [];
}

function increaseSeat() {
    totalPagar += precioSeat;
    seatSelected += 1;
    changeInfo();
}

function decreaseSeat() {
    totalPagar -= precioSeat;
    seatSelected -= 1;
    changeInfo();
}

function changeInfo() {
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
        decreaseSeat();
        removeSeatFromArray(id);

    } else {
        var id = $(this).attr("id");
        $(this).addClass("selected");
        increaseSeat();
        addSeatToArray(id);
    }
}
function addListeners() {
    $("#movie-link").click(loadSeats);
    $("#view-movie").click(readJson);
}
function mainLoader() {
    addListeners();
}




    
function resetMoviesContainer(){//Simplemente borra lo que tiene el array del Json
    $("#lista-peliculas").empty();
}
    
function readJson(){
    
    var listaPeliculasContainer = $("#lista-peliculas");
    resetMoviesContainer();
    moviesJson.forEach((item)=>{
        
        var movieName = item.movie;
        var movieSchedule = item.schedule;
        var movieRoom = item.sala;

        var newListItem = "<li><a id='movie-link' href='#exampleModalToggle2' data-bs-toggle='modal' data-bs-dismiss='modal'>" +movieName+ " " + movieSchedule + "</a></li>";
        
        listaPeliculasContainer.append(newListItem);
        
    });
    
    mainLoader();
    
}

$(mainLoader);




