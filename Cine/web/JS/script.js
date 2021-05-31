var selectedCounter = 0;

const cantidadAsientos = 24;//Valor tomado de la DB el cual define cuantos asientos tendra la sala
const totalColumnas = 8;
const totalFilas = parseInt(cantidadAsientos / totalColumnas);
const asientosUltimaFilaIncompleta = cantidadAsientos%totalColumnas;

function loadSeats() {
    $("#screen-seats-container").remove();
    var container = $("<div class='container' id='screen-seats-container'></div>");
    var screen = $('<div class = "screen"></div>');
    container.append(screen);
    for (i = 0; i < totalFilas; i++) {
        var newRow = $("<div class='row'></div>");
        for (j = 0; j < totalColumnas; j++) {
            var newSeat = $("<div class='seat'></div>");
            newRow.append(newSeat);
        }
        container.append(newRow);
    }
    if (asientosUltimaFilaIncompleta != 0){
        var newRow = $("<div class='row'></div>");
        for (i = 0; i < asientosUltimaFilaIncompleta; i++){
            var newSeat = $("<div class='seat'></div>");
            newRow.append(newSeat);
        }
        container.append(newRow);
    }
    
    
    $(".theather-container").append(container);


}

function addListeners() {
    $("#movie-link").click(loadSeats);
}
function mainLoader() {
    addListeners();
}

$(mainLoader);




