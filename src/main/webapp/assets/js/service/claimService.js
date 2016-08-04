/**
 * htamada
 */

'use strict';

routingApp.factory('claimService', ['$http', '$q', '$window', '$timeout', function($http, $q, $window, $timeout){
	var selectContract = function($scope, data){
			$scope.contractInfoList = data;
			$scope.showActiveContractDetails = false;
			$scope.showContractDetails = true;
			initClaimAddForm($scope);
		},
		saveClaim : function(claim) {
			alert('in saveClaim');
			return $http.post('/agg/saveClaim', claim).then(
					function(response) {
						alert(response.data.status);
						if (response.data.status == 'success') {
							$window.location = '#/agg/fileClaim';
						} else {
							alert('error in adding program: '+response.data.errMessage)
							//$('#errMsg').html(response.data.errMessage);
						}
						
					}, function(errResponse) {
						alert('Error while creating program');
						return $q.reject(errResponse);
					});
		},
		initClaimAddForm = function($scope){
			$scope.claim={};
			var initDatepicker = function(){
				var getMaxClaimFailureDate = function(){
					return $scope.claim.reportedDate - 1;
				};
				$scope.claim.reportedDate = new Date();
				$scope.dateOptions1 = {
				        maxDate: getMaxClaimFailureDate(),
				        startingDay: 1
				    };
				$scope.dateOptions2 = {
			        maxDate: new Date(),
			        startingDay: 1
			    };
				$scope.format = 'dd.MM.yyyy';
				$scope.fdtopen = function() {
			    	$scope.popup1.opened = true;
			    };
			      
				$scope.popup1 = {
					opened: false
				};
				$scope.rdtopen = function() {
			    	$scope.popup2.opened = true;
			    };
			      
				$scope.popup2 = {
					opened: false
				};
			};
			initDatepicker();
			
			$scope.claim.claimNo = 'CL555';
			$scope.$watch('claim.laborHours * claim.hourlyRate', function(value){
				$scope.claim.totalLaborCost = value;
			});
			$scope.$watchCollection('[claim.totalLaborCost, claim.totalPartsCost, claim.totalOtherCharges1, claim.totalOtherCharges2]', function(newValues){
				$scope.claim.totalClaimCost = parseInt(newValues[0]) + parseInt(newValues[1]) + parseInt(newValues[2]) + parseInt(newValues[3]);
			});
		};
		
	return {
		getSerialNumberInfo : function($scope){
			$scope.showContractDetails = false;
			$http.get("/agg/contracts/machineserialno/search/" + $scope.serialNo)
    		.then(function(response) {
    			if(response.data.status === 'success'){
    				if(response.data.data.contractDOList.length === 1){
    					selectContract($scope, response.data.data.contractDOList[0]);
    				}else {
    					$scope.showContractDetails = false;
    					if(response.data.data.contractDOList.length > 1){
    						$scope.showActiveContractDetails = true;
    						$scope.contractDOList = response.data.data.contractDOList;
    						 $timeout(function () {
    					        	$('#contractsTable').DataTable();
    						 }, 500);
    					}else{
    						console.log('no records found');
    					}
    				}
    			}else{
    				$scope.showContractDetails = false;
    			}
    		});
		},
		selectContract : selectContract
	}
}]);