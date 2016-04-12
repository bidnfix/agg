'use strict';

routingApp.factory('programService', function($http, $q, $window) {
			return {
				saveProgram : function(program) {
					return $http.post('/agg/postPrograms', program).then(
							function(response) {
								alert(response.data.status);
								if (response.data.status == 'success') {
									$window.location = '#/agg/programs';
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
