$(document).ready(function () {

    $('.nBtn, .table .eBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        alert(href);
        var text = $(this).text();
        var tAu = $('#tipoAuditoria').val();
        var sec = $('.seccion').val();


        if (text == '_') {


            $.get(href, function (user, status) {
            	$('.myForm #preguntas').val(user.preguntas);

            });
            $('.modal-body').load('editQuest',function(){
		        $('.myForm #exampleModal').modal('toggle');
		    });
           
        } else {

        	$('.modal-body').load('editQuest',function(){
		        $('.myForm #exampleModal').modal('toggle');
	        	$('.myForm #tAId').val(tAu);
	            $('.myForm #secId').val(sec);
		    });
        }

    });

    $('.table .dBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $('#deleteModal #delHref').attr('href', href);
        $('#deleteModal').modal('toggle');


    });
    
    
    $('#tipoAuditoria').change(function(){
		$('.seccion').removeAttr('disabled');
        $.get('sectionQuest',{tipoAuditoria : $('#tipoAuditoria').val()},function(obj) {
        	$(".seccion").empty();
        	$(".seccion").append('<option value="0" disabled selected>Select Questionnaire Type</option>');
        	$.each(obj, function(arregloId, seccion){
        		 $(".seccion").append('<option value="'+seccion.seccionId+'">'+seccion.seccion+'</option>');
        		     });
                  }
        )
        
    } );
    
    $('.seccion').change(function(){
		$.get('questions',{tipoAuditoria : $('#tipoAuditoria').val(), seccion : $('.seccion').val()},function(obj) {
			$(".tQuestions tbody").empty();
			$(".nBtn").removeAttr('disabled');
        	$.each(obj, function(arregloId, quest){
        		$(".tQuestions tbody").append('<tr><td><a class="btn btn-outline-primary fas fa-pencil-alt eBtn" href="findQuest/id=${'+quest.preguntasId+'}}" >_</a><a href="/deleteQuest/(adminId=${'+quest.preguntasId+'})" class="btn btn-outline-danger fas fa-trash-alt dBtn"></a></td><td>'+quest.preguntas+'</td></tr>');
        		     });
                  }
        )
        
    } );

});