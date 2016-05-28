'use strict';

App.factory('registrationService', function($http, $q, $window) {
	return {
		saveRegistration : function(dealer, $scope) {
			return $http.post('/agg/register', dealer).then(
					function(response) {
						alert(response.data.status);
						if (response.data.status == 'success') {
							//$window.location = '/agg/home';
							$scope.dealer={};
							$('#errMsg').hide();
							$('#successMsg').show();
							$('#errMsg').html("");
							$('#successMsg').html("Thank you for submitting your information. We will be in contact shortly.");
						} else {
							//alert('error in registration dealer: '+response.data.errMessage)
							$('#successMsg').hide();
							$('#errMsg').show();
							$('#successMsg').html("");
							$('#errMsg').html(response.data.errMessage);
						}
						
					}, function(errResponse) {
						alert('Error while registering dealer');
						return $q.reject(errResponse);
					});
		}
	};

});
