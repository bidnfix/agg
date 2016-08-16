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
			$scope.claim.claimPartVO = $scope.claim.claimPartVO || {};
			$scope.claim.contractId = $scope.contractInfoList.contractId;
			$scope.claim.serial = $scope.contractInfoList.machineSerialNo;
			$scope.claim.manf = $scope.contractInfoList.manufacturerDO.name;
			$scope.claim.model = $scope.contractInfoList.machineModel;
			$scope.claim.claimId = 'CL' + $scope.contractInfoList.quoteId;
			
			$scope.todayDate = new Date();
			$scope.failureDateValid = updateDate($scope.todayDate, -1);
			
			$scope.$watch('claim.laborHrs * claim.laborHourlyRate', function(value){
				$scope.claim.totalLaborCost = value;
			});
			$scope.$watch('claim.claimPartVO.qty * claim.claimPartVO.unitPrice', function(value){
				$scope.claim.claimPartVO.partsTotal = value;
				$scope.claim.partsTotalCost = $scope.claim.claimPartVO.partsTotal;
			});
			$scope.$watchCollection('[claim.totalLaborCost, claim.partsTotalCost, claim.requestedOtherCharges1, claim.requestedOtherCharges2]', function(newValues){
				$scope.claim.totalClaimCost = parseInt(newValues[0]) + parseInt(newValues[1]) + parseInt(newValues[2]) + parseInt(newValues[3]);
				$scope.isSubmitDisabled = $scope.claim.totalClaimCost > 1500;
			});
			$scope.$watch('claim.reportDate', function(newValues){
				$scope.failureDateValid = updateDate($scope.claim.reportDate, -1);
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
			claim.cStatus = "Pending";
			console.log(JSON.stringify(claim));
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