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
		initClaimAddForm = function($scope){
			$scope.isSubmitDisabled = false;
			$scope.claim={};
			$scope.todayDate = new Date();
			$scope.claim.reportedDate = $scope.todayDate;
			$scope.claim.claimNo = 'CL555';
			$scope.$watch('claim.laborHours * claim.hourlyRate', function(value){
				$scope.claim.totalLaborCost = value;
			});
			$scope.$watchCollection('[claim.totalLaborCost, claim.totalPartsCost, claim.totalOtherCharges1, claim.totalOtherCharges2]', function(newValues){
				$scope.claim.totalClaimCost = parseInt(newValues[0]) + parseInt(newValues[1]) + parseInt(newValues[2]) + parseInt(newValues[3]);
				$scope.isSubmitDisabled = $scope.claim.totalClaimCost > 1500;
			});
			$scope.$watch('claim.reportedDate', function(newValues){
				$scope.failureDateValid = updateDate($scope.claim.reportedDate, -1);
				$scope.claim.failureDate = $scope.failureDateValid;
			});
		},
		updateDate = function(date, days){
			if(!date){
				date = new Date();
			}
			return new Date(date.getTime()  + (days*24*60*60*1000));
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
		selectContract : selectContract
	}
}]);