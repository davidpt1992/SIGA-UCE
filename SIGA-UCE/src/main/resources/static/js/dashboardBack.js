$(document).ready(function () {

	$('.requests').on('click',function(event){
		event.preventDefault();
		var href = $(this).attr('href');
   		$("#seccionRecargarBack").load(href);
    });
	
	$('.tAudits').on('click',function(event){
		event.preventDefault();
		var href = $(this).attr('href');
   		$("#seccionRecargarCust").load(href);
    });
	
});