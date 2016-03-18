'use strict';

routingApp.factory('dealerService', function($http, $q, $window) {
			return {
				saveDealer : function(dealer) {
					return $http.post('/agg/addDealer', dealer).then(
							function(response) {
								alert(response.data.status);
								if (response.data.status == 'success') {
									$window.location = '/agg/home';
								} else {
									alert('error in adding dealer: '+response.data.errMessage)
									//$('#errMsg').html(response.data.errMessage);
								}
								
							}, function(errResponse) {
								alert('Error while creating dealer');
								return $q.reject(errResponse);
							});
				}

			};

		});


routingApp.factory('locationService', function($http, $q, $window) {
	return {
		saveLocation : function(location) {
			return $http.post('/agg/addLocation', location).then(
					function(response) {
						alert(response.data.status);
						if (response.data.status == 'success') {
							$window.location = '/agg/home';
						} else {
							alert('error in adding location: '+response.data.errMessage)
							//$('#errMsg').html(response.data.errMessage);
						}
						
					}, function(errResponse) {
						alert('Error while creating location');
						return $q.reject(errResponse);
					});
		}

	};

});
