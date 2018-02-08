var googleUser = {};
var startApp = function() {
	gapi.load('auth2', function()
	{
		auth2 = gapi.auth2.init({
				client_id: '1074049112107-d7nbcbl7rvq42e7khml5ji22p9pu3dpd.apps.googleusercontent.com',
				cookiepolicy: 'single_host_origin',});
		attachSignin(document.getElementById('signin-button'));
	});
};