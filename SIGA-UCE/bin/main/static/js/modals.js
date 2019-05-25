$(document).ready(function () {

    $('.nBtn, .eBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
            $('.modal-body').load(href, function(){
		        $('.myForm #exampleModal').modal('toggle');
		    });
    });

    $('.table .dBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $('#deleteModal #delHref').attr('href', href);
        $('#deleteModal').modal();
   });

});