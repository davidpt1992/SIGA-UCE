$(document).ready(function () {

    $('.nBtn, .table .eBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        var text = $(this).text();

        if (text == '_') {


            $.get(href, function (user, status) {
            	$('.myForm #userId').val(user.userId);
                $('.myForm #firstName').val(user.nombre);
               	$('.myForm #lastName').val(user.apellido);
               	$('.myForm #inputEmail').val(user.correoElectronico);
				$('.myForm #inputAddress').val(user.direccion);
				$('.myForm #inputPhone1').val(user.numeroTelefono1);
				$('.myForm #inputPhone2').val(user.numeroTelefono2);
				$('.myForm #inputUsername').val(user.usuario);
				$('.myForm #inputPassword').val(user.clave);
				$('.myForm #enabled').val(user.enabled);
									
				if(user.enabled){
					$("#enabled").prop('checked', true);
					}
					else
					$("#enabled").prop('checked', false);


            });
            $('.modal-body').load('editAdmin',function(){
		        $('.myForm #exampleModal').modal('toggle');
		    });
           
        } else {
        	$('.myForm #firstName').val('');

        	$('.modal-body').load(href,function(){
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
    
    
    $('#tipoAuditoria').change(function(){
		$('#tipoAuditoria').removeAttr('disabled');
        $.get('sectionQuest',{tipoAuditoria : $('#tipoAuditoria').val()},function(obj) {
        	$(".section select").empty();
        	$(".section select").append('<option value="0" disabled selected>Select Questionnaire Type</option>');
        	$.each(obj, function(arregloId, seccion){
        		 $(".section select").append('<option value="'+seccion.seccionId+'">'+seccion.seccion+'</option>');
        		     });
                  }
        )
        
    } );
    
    $('#seccion').change(function(){
		$.get('questions',{tipoAuditoria : $('#tipoAuditoria').val(), seccion : $('#seccion').val()},function(obj) {
			$(".tQuestions tbody").empty();
        	$.each(obj, function(arregloId, quest){
        		$(".tQuestions tbody").append('<tr><td><a class="btn btn-outline-primary fas fa-pencil-alt eBtn" href="findQuest/(id=${'+quest.preguntasId+'})">_</a><a href="/deleteQuest/(adminId=${'+quest.preguntasId+'})" class="btn btn-outline-danger fas fa-trash-alt dBtn"></a></td><td>'+quest.preguntas+'</td></tr>');
        		     });
                  }
        )
        
    } );

});