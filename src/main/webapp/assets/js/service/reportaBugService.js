'use strict';

routingApp.factory('reportaBugService', function($http, $q, $window) {
			return {
				saveBug : function(bug) {
					showSpinner();
					return $http.post('/agg/postBug', bug).then(
							function(response) {
								alert(response.data.status);
								if (response.data.status == 'success') {
									$window.location = '#/agg/reportaBug';
								} else {
									alert('error in adding program: '+response.data.errMessage)
									//$('#errMsg').html(response.data.errMessage);
								}
								hideSpinner();
							}, function(errResponse) {
								alert('Error while creating program');
								hideSpinner();
								return $q.reject(errResponse);
							});
				}

			};

		});
