'use strict';

routingApp.factory('usageTierService', function($http, $q, $window) {
			return {
				saveUsageTier : function(usageTier) {
					//alert(usageTier);
					showSpinner();
					return $http.post('/agg/saveUsageTier', usageTier).then(
							function(response) {
								//alert(response.data.status);
								if (response.data.status == 'success') {
									$('#machineSuccessMsg').html("Usage Tier successfully added");
					            	$('#machineSuccessMsg').removeClass('hidden');
					            	window.setTimeout(function() {
					        			 $('#machineSuccessMsg').addClass('hidden');
					        		}, 3000);
								} else {
									alert('Error in adding usge tier: '+response.data.errMessage)
									//$('#errMsg').html(response.data.errMessage);
								}
								hideSpinner();
							}, function(errResponse) {
								alert('Error while creating usage tier');
								hideSpinner();
								return $q.reject(errResponse);
							});
				},

			
				editUsageTier : function(usageTierDO, $scope) {
					//alert("In service");
					showSpinner();
					return $http.post('/agg/editUsageTier', usageTierDO).then(
							function(response) {
								//alert("in save"+usageTierDO.usageFrom);
								if (response.data.status == 'success') {
									$window.location = '#/agg/usageTier';
								} else {
									alert('Error in updating usageTier: '+response.data.errMessage)
								}
								hideSpinner();
							}, function(errResponse) {
								alert('Error while updating usageTier');
								hideSpinner();
								return $q.reject(errResponse);
							});
				},
				
				deleteUsageTier : function(id, $scope) {
					//alert("In service");
					showSpinner();
					return $http.post('/agg/deleteUsageTier', id).then(
							function(response) {
								//alert("in save"+usageTierDO.usageFrom);
								if (response.data.status == 'success') {
									$window.location = '#/agg/usageTier';
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


