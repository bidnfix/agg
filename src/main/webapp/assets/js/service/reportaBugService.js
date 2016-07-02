'use strict';

routingApp.factory('reportaBugService', function($http, $q, $window) {
			return {
				saveBug : function(program) {
					return $http.post('/agg/postBug', bug).then(
							function(response) {
								alert(response.data.status);
								if (response.data.status == 'success') {
									$window.location = '#/agg/reportaBug';
								} else {
									alert('error in adding program: '+response.data.errMessage)
									//$('#errMsg').html(response.data.errMessage);
								}
								
							}, function(errResponse) {
								alert('Error while creating program');
								return $q.reject(errResponse);
							});
				}

			};

		});
