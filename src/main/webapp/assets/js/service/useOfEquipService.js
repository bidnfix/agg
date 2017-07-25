'use strict';

routingApp.factory('useOfEquipService', function($http, $q, $window) {
			return {
				saveEquipment : function(equipment) {
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

			
				editMachineInfo : function(machine, $scope) {
					showSpinner();
					return $http.post('/agg/editMachine', machine).then(
							function(response) {
								//alert(response.data.status);
								if (response.data.status == 'success') {
									closePopup('machineEditPopup');
									$window.location.reload();
									//$window.location.href = '#/agg/dealers';
									/*var objects = $scope.machineInfoList;
							        for (var i = 0; i < objects.length; i++) {
							            if (objects[i].id === machine.id) {
							                objects[i] = machine;
							                break;
							            }
							        }*/
								} else {
									alert('Error in updating machine: '+response.data.errMessage)
									//$('#errMsg').html(response.data.errMessage);
								}
								hideSpinner();
							}, function(errResponse) {
								alert('Error while updating machine');
								hideSpinner();
								return $q.reject(errResponse);
							});
				}
			
			};
			
		});


