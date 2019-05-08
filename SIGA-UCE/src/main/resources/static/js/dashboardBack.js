$(document).ready(function () {

	$('.tRequests').on('click',function(event){
		event.preventDefault();
		var href = $(this).attr('href');
   		$("#seccionRecargarBack").load(href);
    });
	
	$('.tProsesing').on('click',function(event){
		event.preventDefault();
		var href = $(this).attr('href');
   		$("#seccionRecargarBack").load(href);
    });
	
	$('.tAudi').on('click',function(event){
		event.preventDefault();
   		$("#seccionRecargarBack").load("tableAudi");
    });
	
	$('.tCust').on('click',function(event){
		event.preventDefault();
   		$("#seccionRecargarBack").load("tableCust");
    });
	
	$('.tRole').on('click',function(event){
		event.preventDefault();
   		$("#seccionRecargarBack").load("tableRole");
    });
	
});