$(document).ready(function () {

	$('.tAdmin').on('click',function(event){
		event.preventDefault();
   		$("#seccionRecargar").load("tableAdmin");
    });
	
	$('.tBackOfc').on('click',function(event){
		event.preventDefault();
   		$("#seccionRecargar").load("tableBackOfc");
    });
	
	$('.tAud').on('click',function(event){
		event.preventDefault();
   		$("#seccionRecargar").load("tableAuditor");
    });
	
	$('.tCust').on('click',function(event){
		event.preventDefault();
   		$("#seccionRecargar").load("tableCustomer");
    });

});