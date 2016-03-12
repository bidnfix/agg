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
								console.error('Error while creating dealer');
								return $q.reject(errResponse);
							});
				}

			};

		});
