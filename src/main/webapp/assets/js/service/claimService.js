/**
 * htamada
 */

'use strict';

routingApp.factory('claimService', ['$http', '$q', '$window', '$timeout', function($http, $q, $window, $timeout){
	var selectContract = function($scope, data){
			$scope.contractInfoList = data;
			$scope.showActiveContractDetails = false;
			$scope.showSearchClaim = false;
			$scope.showContractDetails = true;
			initClaimAddForm($scope);
		},
		initClaimAddForm = function($scope){
			$scope.isSubmitDisabled = false;
			$scope.claim={};
			$scope.claim.deductible = $scope.contractInfoList.deductible;
			$scope.claim.lol = $scope.contractInfoList.lol;
			$scope.claim.availabeLol = $scope.contractInfoList.availabeLol;
			$scope.claim.contractId = $scope.contractInfoList.contractId;
			$scope.claim.serial = $scope.contractInfoList.machineSerialNo;
			$scope.claim.manf = $scope.contractInfoList.manufacturerDO.name;
			$scope.claim.model = $scope.contractInfoList.machineModel;
			$scope.claim.claimId = 'CL' + $scope.contractInfoList.quoteId;
			$scope.claim.claimPartVOList = [];
			$scope.claim.claimPartVOList.push({});
			$scope.todayDate = new Date();
			$scope.failureDateValid = updateDate($scope.todayDate, -1);
			
			$scope.$watch('claim.laborHrs * claim.laborHourlyRate', function(value){
				$scope.claim.totalLaborCost = value;
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
		},
		calcTotalPartLine = function(claim, index){
			if((claim.claimPartVOList[index].qty >= 0) && (claim.claimPartVOList[index].unitPrice >= 0)){
				claim.claimPartVOList[index].partsTotal = claim.claimPartVOList[index].qty * claim.claimPartVOList[index].unitPrice;
				calcTotalPartCost(claim);
			}
		},
		calcTotalPartCost = function(claim){
			claim.partsTotalCost = 0;
			angular.forEach(claim.claimPartVOList, function(claimPartVO, index){
				if(claimPartVO.partsTotal >= 0){
					claim.partsTotalCost += claimPartVO.partsTotal;
				}
			});
		},
		getPreAuthRequest = function(){
			return $http.post('/agg/saveClaim', claim).then(
					function(response) {
						//alert(response.data.status);
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
		saveClaim : function(claim, files, status) {
			//alert('in saveClaim');
			claim.cStatus = status;
			console.log(JSON.stringify(claim));
			/*return $http.post('/agg/saveClaim', claim).then(
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
					});*/
			var fd = new FormData();
			fd.append('data', angular.toJson(claim));
			fd.append('files', files);
			return $http({
		        method: 'POST',
		        url: '/agg/saveClaim',
		        headers: {'Content-Type': undefined},
		        data: fd,
		        transformRequest: angular.identity
		        })
		       .success(function(data, status) {
		             alert("success");
		        });
		},
		selectContract : selectContract,
		calcTotalPartLine : calcTotalPartLine,
		calcTotalPartCost : calcTotalPartCost,
		getPreAuthRequest : getPreAuthRequest
	}
}]);

routingApp.factory('claimPreAuthReqService', ['$http', '$q', '$window', '$timeout', function($http, $q, $window, $timeout){
	var init = function($scope){
		$http.get("/agg/preAuthClaimReq")
	    .then(function(response) {
	    	$scope.preAuthClaimList = response.data.data.preAuthClaimList;
	    });
	},
	calcCost = function(preAuthClaim){
		if(preAuthClaim){
			preAuthClaim.totalPartCost = 0;
			if(preAuthClaim.claimPartDO){
				for(var i in preAuthClaim.claimPartDO){
					preAuthClaim.claimPartDO[i].partsTotal = preAuthClaim.claimPartDO[i].qty * preAuthClaim.claimPartDO[i].unitPrice;
					preAuthClaim.totalPartCost += preAuthClaim.claimPartDO[i].partsTotal;
				}
			}
			preAuthClaim.totalClaimCost = (preAuthClaim.claimLaborDO.laborHrs * preAuthClaim.claimLaborDO.rate) + preAuthClaim.totalPartCost
				+ preAuthClaim.requestedOtherCharges1 + preAuthClaim.requestedOtherCharges2;
		}
	},
	selectClaim = function($scope, claim){
		$scope.showPreAuthClaimList = false;
		$scope.preAuthClaim = claim;
		$scope.preAuthClaim.totalLaborCost = $scope.preAuthClaim.claimLaborDO.laborHrs * $scope.preAuthClaim.claimLaborDO.rate;
		calcCost($scope.preAuthClaim);
		$scope.extCommentFlag = true;
	},
	submit = function($scope, status){
		var data = {};
		data.id = $scope.preAuthClaim.id;
		data.cStatus = status;
		if($scope.preAuthClaim.extComment){
			data.extComment = $scope.preAuthClaim.extComment;
		}
		$http.put('/agg/preAuthClaimReq', data).then(
				function(response) {
					//alert(response.data.status);
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
	reqAuth = function($scope, status){
		if(status === 'pre_authorized_approved_with_adjustments' || status === 'pre_authorized_rejected'){
			if($scope.preAuthClaim.extComment){
				submit($scope, status);
			}
		}
		if(status === 'pre_authorized_approved'){
			submit($scope, status);
		}
	};
	
	return {
		init : init,
		selectClaim : selectClaim,
		reqAuth : reqAuth
	}
}]);

routingApp.factory('claimsAdjudicateService', ['$http', '$q', '$window', '$timeout', function($http, $q, $window, $timeout){
	var init = function($scope){
		$http.get("/agg/adjudicateClaim")
	    .then(function(response) {
	    	$scope.adjudicateClaimList = response.data.data.preAuthClaimList;
	    });
	},
	calcCost = function(adjudicateClaim){
		if(adjudicateClaim){
			adjudicateClaim.totalPartCost = 0;
			if(adjudicateClaim.claimPartDO){
				for(var i in adjudicateClaim.claimPartDO){
					adjudicateClaim.claimPartDO[i].partsTotal = adjudicateClaim.claimPartDO[i].qty * adjudicateClaim.claimPartDO[i].unitPrice;
					adjudicateClaim.totalPartCost += adjudicateClaim.claimPartDO[i].partsTotal;
				}
			}
			adjudicateClaim.totalLaborCost = (adjudicateClaim.claimLaborDO.laborHrs * adjudicateClaim.claimLaborDO.rate);
			adjudicateClaim.totalClaimCost = adjudicateClaim.totalLaborCost  + adjudicateClaim.totalPartCost
				+ adjudicateClaim.requestedOtherCharges1 + adjudicateClaim.requestedOtherCharges2;
		}
	},
	selectClaim = function($scope, claim){
		$scope.showAdjudicateClaimList = false;
		$scope.adjudicateClaim = claim;
		calcCost($scope.adjudicateClaim);
		$scope.$watch('adjudicateClaim.claimLaborDO.adjustedLaborHrs * adjudicateClaim.claimLaborDO.adjustedRate', function(value){
			$scope.adjudicateClaim.adjustedTotalLaborCost = value;
		});
		
		$scope.$watch('adjudicateClaim.adjustedOther1Cost + adjudicateClaim.adjustedOther2Cost', function(value){
			$scope.adjudicateClaim.totalAdjustedOthersCost = value;
		});
		
		$scope.$watchCollection('[adjudicateClaim.adjustedTotalLaborCost, adjudicateClaim.totalAdjustedPartsCost, adjudicateClaim.totalAdjustedOthersCost]', function(newValues){
			$scope.adjudicateClaim.totalAdjustedClaimsCost = parseInt(newValues[0]) + parseInt(newValues[1]) + parseInt(newValues[2]);
		});
		
		$scope.$watch('adjudicateClaim.totalAdjustedClaimsCost', function(value){
			var reimbursedAmount = $scope.adjudicateClaim.contractDO.availabeLol - ($scope.adjudicateClaim.totalAdjustedClaimsCost - $scope.adjudicateClaim.contractDO.deductible);
			if(reimbursedAmount < 0){
				$scope.adjudicateClaim.totalReimbursedCost = $scope.adjudicateClaim.contractDO.availabeLol;
			}else{
				$scope.adjudicateClaim.totalReimbursedCost = $scope.adjudicateClaim.totalAdjustedClaimsCost;
			}
		});
	},
	submit = function($scope, status){
		var data = {};
		data.id = $scope.adjudicateClaim.id;
		data.cStatus = status;
		if($scope.adjudicateClaim.extComment){
			data.extComment = $scope.adjudicateClaim.extComment;
		}
		$http.put('/agg/preAuthClaimReq', data).then(
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
	reqAuth = function($scope, status){
		if(status === 'pre_authorized_approved_with_adjustments' || status === 'pre_authorized_rejected'){
			if($scope.adjudicateClaim.extComment){
				submit($scope, status);
			}
		}
		if(status === 'pre_authorized_approved'){
			submit($scope, status);
		}
	},
	backToList = function($scope){
		$scope.showAdjudicateClaimList = true;
		$scope.adjudicateClaim = {};
	};
	
	return {
		init : init,
		selectClaim : selectClaim,
		reqAuth : reqAuth,
		backToList : backToList
	}
}]);