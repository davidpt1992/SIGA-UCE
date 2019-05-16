$(document).ready(function () {

    $('.nBtn, .table .eBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        var text = $(this).text();

        if (text == '_') {


            $.get(href, function (user, status) {
            	$('.myForm #tipoAuditoria').val(user.tipoAuditoria);
									
            });
            $('.modal-body').load('editTypeQuestionnaire',function(){
		        $('.myForm #exampleModal').modal('toggle');
		    });
           
        } else {
        	

        	$('.modal-body').load('editTypeQuestionnaire',function(){
		        $('.myForm #exampleModal').modal('toggle');
		    });
        }

    });

    $('.table .dBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $('#deleteModal #delHref').attr('href', href);
        $('#deleteModal').modal('toggle');


    });
    
    

});