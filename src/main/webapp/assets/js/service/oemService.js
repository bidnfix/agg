'use strict';

routingApp.factory('oemService', function($http, $q, $window) {
			return {
				saveOEM : function(oem) {
					//alert(usageTier);
					showSpinner();
					return $http.post('/agg/saveOEMWarrantyTier', oem).then(
							function(response) {
								//alert(response.data.status);
								if (response.data.status == 'success') {
									$('#machineSuccessMsg').html("OEM Warranty Period Tier successfully added");
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
				editOEM : function(oemDO, $scope) {
					//alert("In service");
					showSpinner();
					return $http.post('/agg/editOEMWarrantyTier', oemDO).then(
							function(response) {
								//alert("in save");
								if (response.data.status == 'success') {
									$window.location = '#/agg/oemWarrantyTier';
								} else {
									alert('Error in updating OEM: '+response.data.errMessage)
								}
								hideSpinner();
							}, function(errResponse) {
								alert('Error while updating OEM');
								hideSpinner();
								return $q.reject(errResponse);
							});
				},
				
				deleteOEM : function(id, $scope) {
					//alert("In service");
					showSpinner();
					return $http.post('/agg/delOEMWarrantyTier', id).then(
							function(response) {
								//alert("in save"+usageTierDO.usageFrom);
								if (response.data.status == 'success') {
									$window.location = '#/agg/oemWarrantyTier';
								} else {
									alert('Error in updating OEM: '+response.data.errMessage)
								}
								hideSpinner();
							}, function(errResponse) {
								alert('Error while updating OEM');
								hideSpinner();
								return $q.reject(errResponse);
							});
				}
			
			};
			
		});


