$(document).ready(function () {

    $('.nBtn, .table .eBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        var text = $(this).text();

        if (text == '_') {


            $.get(href, function (user, status) {
            	$('.myForm #seccion').val(user.seccion);
									
            });
            $('.modal-body').load('editSection',function(){
		        $('.myForm #exampleModal').modal('toggle');
		    });
           
        } else {
        	

        	$('.modal-body').load('editSection',function(){
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