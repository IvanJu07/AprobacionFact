function signOut()
{
	var auth2 = gapi.auth2.getAuthInstance();
	auth2.signOut().then(function(){window.location.href = '../index.html'});
}
$("#out").click(function(){signOut()});
function alertmensaje(titulo,mensaje)
{
	$.confirm({
		
		theme: 'modern',
		title: titulo,
		content: mensaje,
		closeIcon: false,
		animation: 'scale',
		type: 'blue',
		buttons: {
			ok: {
			text: 'Ok',
			btnClass: 'btn-blue'
			}
		}
	});
}
function mensajewarning(titulo,mensaje)
{
	$.confirm({
		
		closeIcon: true,
		theme: 'modern',
		title: titulo,
		content: mensaje,
		
		animation: 'scale',
		type: 'blue',
		buttons: {
			ok: {
			text: 'Ok',
			btnClass: 'btn-blue'
			}
		}
	});
}
$("#home").click(function()
{
	window.location.href = 'main.html'
});
