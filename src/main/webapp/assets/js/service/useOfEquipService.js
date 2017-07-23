'use strict';

routingApp.factory('useOfEquipService', function($http, $q, $window) {
			return {
				saveMachineInfo : function(machine) {
					showSpinner();
					return $http.post('/agg/saveMachine', machine).then(
							function(response) {
								//alert(response.data.status);
								if (response.data.status == 'success') {
									//$window.location = '#/agg/machineInfo';
									$('#machineSuccessMsg').html("Machine '<strong>"+machine.model+"</strong>' successfully added");
					            	$('#machineSuccessMsg').removeClass('hidden');
					            	window.setTimeout(function() {
					        			 $('#machineSuccessMsg').addClass('hidden');
					        		}, 3000);
								} else {
									alert('Error in adding machine: '+response.data.errMessage)
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


