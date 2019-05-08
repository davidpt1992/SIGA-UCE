$(document).ready(function () {

	$('.tAdmin').on('click',function(event){
		event.preventDefault();
		var href = $(this).attr('href');
   		$("#seccionRecargar").load(href);
    });
	
	$('.tBackOfc').on('click',function(event){
		event.preventDefault();
   		$("#seccionRecargar").load("tableBack");
    });
	
	$('.tAudi').on('click',function(event){
		event.preventDefault();
   		$("#seccionRecargar").load("tableAudi");
    });
	
	$('.tCust').on('click',function(event){
		event.preventDefault();
   		$("#seccionRecargar").load("tableCust");
    });
	
	$('.tRole').on('click',function(event){
		event.preventDefault();
   		$("#seccionRecargar").load("tableRole");
    });
	
	$('.tQuest').on('click',function(event){
		event.preventDefault();
   		$("#seccionRecargar").load("tableQuest");
    });
	
	$('.tAssig').on('click',function(event){
		event.preventDefault();
   		$("#seccionRecargar").load("tableAssig");
    });
	
	$('.tSche').on('click',function(event){
		event.preventDefault();
   		$("#seccionRecargar").load("tableSche");
    });

});