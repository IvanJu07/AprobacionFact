/*

CAMBIAR http://localhost:8080  POR  https://backend-dot-aprobacion-de-facturas.appspot.com
*/


function onLoad(){
	gapi.load('auth2,signin2', function() {
		var auth2 = gapi.auth2.init();
		auth2.then(function() {
			var isSignedIn = auth2.isSignedIn.get();
			var currentUser = auth2.currentUser.get().getBasicProfile();
			if (!isSignedIn) 
				window.location.href = '../index.html';
			var email = currentUser.getEmail();
			var name = currentUser.getGivenName();
			var picture = currentUser.getImageUrl();
			$("#luser").text(name);
	        $(".profile").attr("src",picture);
			//$.post( "https://frontend-dot-aprobacion-de-facturas.appspot.com/ServletUser",{ inputEmail: email},function(result)
			$.post( "https://backend-dot-aprobacion-de-facturas.appspot.com/ServletUser",{ inputEmail: email},function(result)
			{
				if($.parseJSON(result)){
					var Datos = $.parseJSON(result);
					var perfil = Datos[0]['perfil'];
					permisos(perfil)
				}
				else
					$(location).attr('href','../index.html');
			});
		});
	});
}

function permisos(autorizar)
{
	if(autorizar=="1")
	{
		$('#adminok').removeClass('hidden');
		$('#adminblock').addClass('hidden');
		$('#proveeok').removeClass('hidden');
		$('#proveeblock').addClass('hidden');
		$('#pmook').removeClass('hidden');
		$('#pmoblock').addClass('hidden');
		$('.letraprovee, .letramain').removeClass('hidden');
	}
	if(autorizar=="2")
	{
		$('.notadmin').addClass('hidden');
		$('#proveeok').removeClass('hidden');
		$('.letraprovee').removeClass('hidden');
		
	}
	if(autorizar=="3")
	{
		$('#adminok').addClass('hidden');
		$('#adminblock').removeClass('hidden');
		$('#proveeok').addClass('hidden');
		$('#proveeblock').removeClass('hidden');
		$('#pmook').removeClass('hidden');
		$('#pmoblock').addClass('hidden');
		$('.letraprovee, .letramain').removeClass('hidden');
		
	}
	$('html').removeClass('hidden');
}