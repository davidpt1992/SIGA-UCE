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
               	$('.myForm #role').val(user.roleId.rol);
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
		        $('.myForm #exampleModal').modal({show:true});
		    });
           
        } else {

        	$('.modal-body').load(href,function(){
		        $('.myForm #exampleModal').modal({show:true});
		    });
        }

    });

    $('.table .dBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $('#deleteModal #delHref').attr('href', href);
        $('#deleteModal').modal();


    });
    
    $('.lookCust').on('click',function(event){
		event.preventDefault();
		var href = $(this).attr('href');
		$('.modal-body').load(href,function(){
	        $('.myFormCust #exampleModal').modal({show:true});
	    });
    });
    
    $('.startAudit').on('click',function(event){
		event.preventDefault();
		var href = $(this).attr('href');
		$('.modal-body').load(href,function(){
	        $('.myFormQuestionnaire #exampleModal').modal({show:true});
	    });
    });
    
    $('.upFile').on('click',function(event){
		event.preventDefault();
		var href = $(this).attr('href');
		$('.modal-body').load(href,function(){
	        $('.myFormQuestionnaire #exampleModal').modal({show:true});
	    });
    });
    
    $('.audiCheckEvidence').on('click',function(event){
		event.preventDefault();
		var href = $(this).attr('href');
		$('.modal-body').load(href,function(){
	        $('.myFormQuestionnaire #exampleModal').modal({show:true});
	    });
    });
    
    $('.sendToCheck').on('click',function(event){
		event.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function (msg, status) {
			$('.modal-body').append('<p class="alert alert-danger">'+msg+'</p>');
		});
		$('.myFormSend #exampleModal').modal({show:true});
    });
    
    $('.next').on('click',function(event){
		event.preventDefault();
		$.ajax({
			  type: "POST",
			  url: "nextQuestion",
			  data: {
					id: $('#id').val(),
					usuario: $('#usuario').val(),
					codigo: $('#codigo').val()
				  },
			  enctype: 'multipart/form-data',
			  dataType: false,
			  success: function(mav){
				  $('.modal-body').html(mav ,function(){
					  $('.myFormQuestionnaire #exampleModal').modal({show:true});
				  });					  
			  }
			});		
    });
    
    $('.previous').on('click',function(event){
		event.preventDefault();
		$.ajax({
			  type: "POST",
			  url: "previousQuestion",
			  data: {
					id: $('#id').val(),
					usuario: $('#usuario').val(),
					codigo: $('#codigo').val()
				  },
			  enctype: 'multipart/form-data',
			  dataType: false,
			  success: function(mav){
				  $('.modal-body').html(mav ,function(){
					  $('.myFormQuestionnaire #exampleModal').modal({show:true});
				  });					  
			  }  
			});
    });
    
    $('.nextUploadFile').on('click',function(event){
		event.preventDefault();
		$.ajax({
			  type: "POST",
			  url: "nextQuestionUploadFile",
			  data: {
					id: $('#id').val(),
					usuario: $('#usuario').val(),
					codigo: $('#codigo').val()
				  },
			  enctype: 'multipart/form-data',
			  dataType: false,
			  success: function(mav){
				  $('.modal-body').html(mav ,function(){
					  $('.myFormQuestionnaire #exampleModal').modal({show:true});
				  });					  
			  }
			});		
    });
    
    $('.previousUploadFile').on('click',function(event){
		event.preventDefault();
		$.ajax({
			  type: "POST",
			  url: "previousQuestionUploadFile",
			  data: {
					id: $('#id').val(),
					usuario: $('#usuario').val(),
					codigo: $('#codigo').val()
				  },
			  enctype: 'multipart/form-data',
			  dataType: false,
			  success: function(mav){
				  $('.modal-body').html(mav ,function(){
					  $('.myFormQuestionnaire #exampleModal').modal({show:true});
				  });					  
			  }  
			});
    });
    
    $('.nextCheckFile').on('click',function(event){
		event.preventDefault();
		$.ajax({
			  type: "POST",
			  url: "nextQuestionCheckFile",
			  data: {
					id: $('#id').val(),
					usuario: $('#usuario').val(),
					codigo: $('#codigo').val()
				  },
			  enctype: 'multipart/form-data',
			  dataType: false,
			  success: function(mav){
				  $('.modal-body').html(mav ,function(){
					  $('.myFormQuestionnaire #exampleModal').modal({show:true});
				  });					  
			  }
			});		
    });
    
    $('.previousCheckFile').on('click',function(event){
		event.preventDefault();
		$.ajax({
			  type: "POST",
			  url: "previousQuestionCheckFile",
			  data: {
					id: $('#id').val(),
					usuario: $('#usuario').val(),
					codigo: $('#codigo').val()
				  },
			  enctype: 'multipart/form-data',
			  dataType: false,
			  success: function(mav){
				  $('.modal-body').html(mav ,function(){
					  $('.myFormQuestionnaire #exampleModal').modal({show:true});
				  });					  
			  }  
			});
    });
    
    $('.sendNC').on('click',function(event){
		event.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function (msg, status) {
			$('.modal-body').append('<p class="alert alert-danger">'+msg+'</p>');
		});
		$('.myFormConformities #exampleModal').modal({show:true});
    });
    
});