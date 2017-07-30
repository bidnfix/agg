'use strict';

routingApp.factory('useOfEquipService', function($http, $q, $window) {
			return {
				saveEquipment : function(equipment) {
					//alert("--");
					showSpinner();
					return $http.post('/agg/saveEquipment', equipment).then(
							function(response) {
								alert(response.data.status);
								if (response.data.status == 'success') {
									$('#machineSuccessMsg').html("Equipment '<strong>"+equipment.equipName+"</strong>' successfully added");
					            	$('#machineSuccessMsg').removeClass('hidden');
					            	window.setTimeout(function() {
					        			 $('#machineSuccessMsg').addClass('hidden');
					        		}, 3000);
								} else {
									alert('Error in adding equipment: '+response.data.errMessage)
									//$('#errMsg').html(response.data.errMessage);
								}
								hideSpinner();
							}, function(errResponse) {
								alert('Error while creating machine');
								hideSpinner();
								return $q.reject(errResponse);
							});
				},

			
				editEquipment : function(equipment, $scope) {
					alert("In service");
					showSpinner();
					return $http.post('/agg/editEquipment', equipment).then(
							function(response) {
								//alert(response.data.status);
								if (response.data.status == 'success') {
									closePopup('UOEEditPopup');
									$window.location.reload();
								} else {
									alert('Error in updating equipment: '+response.data.errMessage)
								}
								hideSpinner();
							}, function(errResponse) {
								alert('Error while updating equipment');
								hideSpinner();
								return $q.reject(errResponse);
							});
				}
			
			};
			
		});


