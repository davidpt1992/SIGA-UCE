$(document).ready(function () {

	$('.tAAA').on('click',function(event){
		event.preventDefault();
		var href = $(this).attr('href');
   		$("#seccionRecargarAuditoriasAsignadas").load(href);
    });
	$('.tANC').on('click',function(event){
		event.preventDefault();
		var href = $(this).attr('href');
   		$("#seccionRecargarAuditoriasAsignadas").load(href);
    });
	$('.tACH').on('click',function(event){
		event.preventDefault();
		var href = $(this).attr('href');
   		$("#seccionRecargarAuditoriasAsignadas").load(href);
    });
	$('.tAH').on('click',function(event){
		event.preventDefault();
		var href = $(this).attr('href');
		$("#startAudit").prop('disabled', false);
   		$("#seccionRecargarAuditoriasAsignadas").load(href);
    });
	
});