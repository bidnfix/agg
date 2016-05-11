'use strict';

routingApp.factory('machineService', function($http, $q, $window) {
			return {
				saveMachineInfo : function(machine) {
					return $http.post('/agg/saveMachine', machine).then(
							function(response) {
								//alert(response.data.status);
								if (response.data.status == 'success') {
									$window.location = '/agg/home';
								} else {
									alert('error in adding machine: '+response.data.errMessage)
									//$('#errMsg').html(response.data.errMessage);
								}
								
							}, function(errResponse) {
								alert('Error while creating machine');
								return $q.reject(errResponse);
							});
				}

			};

		});
