'use strict';

App.factory('LoginService', [
		'$http',
		'$q',
		'$window',
		function($http, $q, $window) {

			return {
				loginUser : function(user) {
					return $http.post('/agg/login', user).then(
							function(response) {
								if (response.data.status == 'success') {
									$window.location = '/agg/home';
								} else {
									//alert('Invalid login '+response.data.errMessage)
									$('#errMsg').html(response.data.errMessage);
								}
								
							}, function(errResponse) {
								console.error('Error while creating user');
								return $q.reject(errResponse);
							});
				}

			};

		} ]);
