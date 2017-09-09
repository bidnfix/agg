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
									$('#machineSuccessMsg').html("Manufacturer successfully added");
					            	$('#machineSuccessMsg').removeClass('hidden');
					            	window.setTimeout(function() {
					        			 $('#machineSuccessMsg').addClass('hidden');
					        		}, 3000);
								} else {
									alert('Error in adding Manufacturer: '+response.data.errMessage)
									//$('#errMsg').html(response.data.errMessage);
								}
								hideSpinner();
							}, function(errResponse) {
								alert('Error while creating Manufacturer');
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
									alert('Error in updating Manufacturer: '+response.data.errMessage)
								}
								hideSpinner();
							}, function(errResponse) {
								alert('Error while updating Manufacturer');
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
									alert('Error in updating Manufacturer: '+response.data.errMessage)
									//$('#errMsg').html(response.data.errMessage);
								}
								hideSpinner();
							}, function(errResponse) {
								alert('Error while updating Manufacturer');
								hideSpinner();
								return $q.reject(errResponse);
							});
				}
				
				
			
			};
			
		});


