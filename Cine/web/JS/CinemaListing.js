var totalPagar = 0;
var seatsArray = [];

function loadMoviesListing() { //Dentro de este metodo deberia ir el request al API para solicitar las peliculas de la cartelera

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
        },
        {
            "id_pelicula": "blackdynamite",
            "nombre": "Black Dynamite",
            "duracion": "2 horas",
            "descripcion": "Después de que un hombre mata a su hermano y envenena a una comunidad con licor adulterado, un super atleta (Michael Jai White) especializado en kung fu inicia una guerra sangrienta que lo lleva hasta la Casa Blanca de Richard Nixon.."
        },
        {
            "id_pelicula": "Conjuro1",
            "nombre": "El Conjuro 1",
            "duracion": "2.5 horas",
            "descripcion": "A principios de los años 70, Ed y Lorrain Warren, reputados investigadores de fenómenos paranormales, se enfrentan a una entidad demoníaca al intentar ayudar a una familia que está siendo aterrorizada por una presencia oscura en su aislada granja."
        }

    ];

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
                                        <button type="button" class="btn btn-sm btn-outline-secondary" id="view-movie">
                                            View
                                        </button>
                                        <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
                                    </div>
                                    <small class="text-muted">`+ movieDuration + `</small>
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

    //Aqui va el request al API para que retorne los datos de la pelicula indicada en el ID
    listaHorariosJSON = [
        {
            "Fecha": "Lunes 20 Junio",
            "Hora": "5:00pm",
            "sala": 01
        },
        {
            "Fecha": "Martes 21 Junio",
            "Hora": "5:00pm",
            "sala": 01
        },
        {
            "Fecha": "Miercoles 22 Junio",
            "Hora": "5:00pm",
            "sala": 02
        }
    ];

    var listaHorarios = $("#lista-horarios").empty();//Lista de horarios del modal se limpia

    listaHorariosJSON.forEach((item) => {
        var newListItem = $("<li></li>");
        newListItem.html('<a href="#" id="horario">' + item.Fecha + ' ' + item.Hora + '</a>');
        newListItem.find('#horario').on('click', () => {
            butacas(idPelicula, item);
        });
        listaHorarios.append(newListItem);
    });

    $('#modalHorarios').modal('show');
}
function butacas(idPelicula, fechaHora) {
    //Aqui se deberia hacer el request al server solicitando la informacion de los tickets ya vendidos, la sala, cantidad de asientos entre otra informacion necesaria aun no definida.

    var informacionButacasJSON = {
        "cantidadAsientos": 24,
        "precio": 1500,
        "ocupados": [
            "0-1",
            "0-2",
            "0-3",
            "0-4"
        ]
    }
    
    loadSeats(informacionButacasJSON);

    $('#modalButacas').modal('show');
}
function loadSeats(informacionButacasJSON) {//Recibe JSON con informacion necesaria

    resetSeats(); //Lo unico que hace es borrar el arreglo donde guardan los asientos seleccionados por el usuario
    $("#screen-seats-container").remove();//Borra el 'body' del modal para que este no se duplique cada vez que se abre la pestaña de butacas
    

    //Carga de variables desde JSON
    var cantidadAsientos = informacionButacasJSON.cantidadAsientos;
    var precioSeat = informacionButacasJSON.precio;
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

    $(".theather-container").append(container);
    $(".seat:not(.occupied)").click(setSelected_Unselected);//Agrega listener para poder cambiar los seleccionados
}
function resetSeats() { //Esta funcion solo se utiliza para volver a poner el array donde se guardan los asientos 
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
$(loadMoviesListing);




