$(document).ready(function () {

	$('.profile').on('click',function(event){
		event.preventDefault();
   		$("#seccionRecargarCust").load("profileCust");
    });
	
	$('.tAudits').on('click',function(event){
		event.preventDefault();
		var href = $(this).attr('href');
   		$("#seccionRecargarCust").load(href);
    });
	
});