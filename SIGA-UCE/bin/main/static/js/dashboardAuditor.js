$(document).ready(function () {

	$('.tAAA').on('click',function(event){
		event.preventDefault();
		var href = $(this).attr('href');
   		$("#seccionRecargarAuditoriasAsignadas").load(href);
    });
	$('.tAH').on('click',function(event){
		event.preventDefault();
		var href = $(this).attr('href');
   		$("#seccionRecargarAuditoriasAsignadas").load(href);
    });
	
});