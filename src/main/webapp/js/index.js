/*

CAMBIAR http://localhost:8080  POR  https://backend-dot-aprobacion-de-facturas.appspot.com
*/

function attachSignin(element) 
{
    auth2.attachClickHandler(element, {},
    function(googleUser) {  
		$('#load').removeClass('hidden')
        var email = googleUser.getBasicProfile().getEmail();
        //$.post( "https://frontend-dot-aprobacion-de-facturas.appspot.com/ServletUser",{ inputEmail: email},function(result)
		$.post( "https://backend-dot-aprobacion-de-facturas.appspot.com/ServletUser",{ inputEmail: email},function(result)
        {
            if(!$.parseJSON(result)){
				$.confirm({
					icon: 'fa',
					theme: 'modern',
					title: 'Acceso denegado',
					content: 'Contacte con su administrador',
					closeIcon: false,
					animation: 'scale',
					type: 'blue',
					buttons: {
						ok: {
							text: 'Ok',
							btnClass: 'btn-blue',
							action: function () {
								$('#load').addClass('hidden')	
							}
						}
					}
				});
			}
            else
                $(location).attr('href','../main.html');
        });
    },function(error) {
    	console.log(JSON.stringify(error, undefined, 2));
    });
}