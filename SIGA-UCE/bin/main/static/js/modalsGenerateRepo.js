$(document).ready(function () {
    
    $('.genCertificate').on('click',function(event){
		event.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function (msg, status) {
			$('.modal-body').append('<p class="alert alert-danger">'+msg+'</p>');
		});
		$('.myFormReportGen #exampleModal').modal({show:true});
    });
    
});