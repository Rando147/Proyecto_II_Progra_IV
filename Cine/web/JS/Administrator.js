
var url="http://localhost:8080/Cine/";


function Pelicula(){
    pelicula = {
        id: $("#idpeli").val(),
        nombre:$("#nombrepeli").val(),
        descripcion:$("#descripeli").val(),
        duracion:$("#duracionpeli").val(),
    };
    let request = new Request(url+'api/admin/pelicula', {method: 'POST', headers: { 'Content-Type': 'application/json'},body: JSON.stringify(pelicula)});
        (async ()=>{
            const response = await fetch(request);
            if (!response.ok) {errorMessage(response.status,$("#loginDialog #errorDiv"));return;}
            $('#modalPelicula').modal('hide');           
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
//            $("#sala").click(sala);
//            $("#cartelera").click(cartelera);
  }
  
  $(load);  