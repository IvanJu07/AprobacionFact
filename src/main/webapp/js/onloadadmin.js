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
			$('html').removeClass('hidden');
		});
	});
}