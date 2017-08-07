'use strict';

routingApp.factory('manufacturerService', function($http, $q, $window) {
			return {
				saveManufacturer : function(manfDO) {
					//alert(usageTier);
					showSpinner();
					return $http.post('/agg/saveManufacturer', manfDO).then(
							function(response) {
								//alert(response.data.status);
								if (response.data.status == 'success') {
									$('#machineSuccessMsg').html("OEM successfully added");
					            	$('#machineSuccessMsg').removeClass('hidden');
					            	window.setTimeout(function() {
					        			 $('#machineSuccessMsg').addClass('hidden');
					        		}, 3000);
								} else {
									alert('Error in adding OEM: '+response.data.errMessage)
									//$('#errMsg').html(response.data.errMessage);
								}
								hideSpinner();
							}, function(errResponse) {
								alert('Error while creating OEM');
								hideSpinner();
								return $q.reject(errResponse);
							});
				},
				editManufacturer : function(manfDO, $scope) {
					//alert("In service");
					showSpinner();
					return $http.post('/agg/editManufacturer', manfDO).then(
							function(response) {
								//alert("in save");
								if (response.data.status == 'success') {
									$window.location = '#/agg/manufacturers';
								} else {
									alert('Error in updating usageTier: '+response.data.errMessage)
								}
								hideSpinner();
							}, function(errResponse) {
								alert('Error while updating usageTier');
								hideSpinner();
								return $q.reject(errResponse);
							});
				}
			
			};
			
		});


