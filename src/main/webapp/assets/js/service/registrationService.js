'use strict';

App.factory('registrationService', function($http, $q, $window) {
	return {
		saveRegistration : function(dealer) {
			return $http.post('/agg/register', dealer).then(
					function(response) {
						alert(response.data.status);
						if (response.data.status == 'success') {
							$window.location = '/agg/home';
						} else {
							alert('error in registration dealer: '+response.data.errMessage)
							//$('#errMsg').html(response.data.errMessage);
						}
						
					}, function(errResponse) {
						alert('Error while registering dealer');
						return $q.reject(errResponse);
					});
		}
	};

});
