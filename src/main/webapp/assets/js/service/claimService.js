/**
 * htamada
 */

'use strict';

routingApp.factory('claimService', ['$http', '$q', '$window', '$timeout', '$filter', function($http, $q, $window, $timeout, $filter){
	var selectContract = function($scope, data){
			$scope.contractInfoList = data;
			hideContractList($scope);
			getContractCount($scope, initClaimAddForm);
		},
		getContractCount = function($scope, initFunc){
			$http.get("/agg/claims/count/" + $scope.contractInfoList.contractID)
			.then(function(response) {
				if(response.data.status === 'success'){
					$scope.contractInfoList.count =  parseInt(response.data.data.count) + 1;
					initFunc($scope);
				}
			});
		},
		initClaimAddForm = function($scope){
			$scope.isSubmitDisabled = false;
			$scope.claim={};
			$scope.claim.deductible = $scope.contractInfoList.deductible;
			$scope.claim.lol = $scope.contractInfoList.lol;
			$scope.claim.availabeLol = $scope.contractInfoList.availableLol;
			$scope.claim.contractId = $scope.contractInfoList.contractID;
			$scope.claim.serial = $scope.contractInfoList.machineSerialNo;
			$scope.claim.manf = $scope.contractInfoList.manfactureName;
			$scope.claim.model = $scope.contractInfoList.machineModel;
			$scope.claim.claimId = $scope.contractInfoList.contractID + '-' + $scope.contractInfoList.count;
			$scope.claim.claimPartVOList = [];
			$scope.claim.claimPartVOList.push({});
			$scope.claim.claimLabourVOList = [];
			$scope.claim.claimLabourVOList.push({});
			$scope.todayDate = new Date();
			$scope.failureDateValid = updateDate($scope.todayDate, -1);
			
			/*$scope.$watch('claim.laborHrs * claim.laborHourlyRate', function(value){
				$scope.claim.totalLaborCost = value;
			});*/
			
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
		calcTotalLabourLine = function(claim, index){
			if((claim.claimLabourVOList[index].laborHrs >= 0) && (claim.claimLabourVOList[index].laborHourlyRate >= 0)){
				claim.claimLabourVOList[index].labourTotal = claim.claimLabourVOList[index].laborHrs * claim.claimLabourVOList[index].laborHourlyRate;
				calcTotalLabourCost(claim);
			}
		},
		calcTotalLabourCost = function(claim){
			claim.totalLaborCost = 0;
			angular.forEach(claim.claimLabourVOList, function(claimLabourVO, index){
				if(claimLabourVO.labourTotal >= 0){
					claim.totalLaborCost += claimLabourVO.labourTotal;
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
							alert('Error in adding program: '+response.data.errMessage)
							//$('#errMsg').html(response.data.errMessage);
						}
						
					}, function(errResponse) {
						alert('Error while creating program');
						return $q.reject(errResponse);
					});
		},
		hideContractList = function($scope){
			$scope.showSearchClaim = false;
			$scope.showActiveContractDetails = false;
			$scope.showContractDetails = true;
		},
		showContractList = function($scope){
			$scope.showContractDetails = false;
			if($scope.contractDOList.length > 1){
				$scope.showActiveContractDetails = true;
			}
			$scope.showSearchClaim = true;
		},
		collectAttachments = function ($scope, $files) {
			$scope.attachments = $files || [];
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
		saveClaim : function($scope) {
			alert('in saveClaim');
			var claim = $scope.claim;
			claim.reportDate = $filter('date')(claim.reportDate, 'yyyy-MM-dd');
			claim.failDate = $filter('date')(claim.failDate, 'yyyy-MM-dd');
			claim.cStatus = $scope.newClaimClick;
			console.log(angular.toJson(claim));
			var fd = new FormData();
			fd.append('data', angular.toJson(claim));
			 angular.forEach($scope.attachments, function (value, key) {
				 fd.append('files', value);
		        });
			return $http({
		        method: 'POST',
		        url: '/agg/saveClaim',
		        headers: {'Content-Type': undefined},
		        data: fd,
		        transformRequest: angular.identity
		        })
		       .success(function(data, status) {
		    	   if(status === 200 && data.status === "success"){
		    		   alert("success");
		    		   $window.location.href = '#/agg/fileClaim';
		    	   }else{
		    		   alert("failed");
		    	   }
		        });
		},
		selectContract : selectContract,
		calcTotalPartLine : calcTotalPartLine,
		calcTotalPartCost : calcTotalPartCost,
		calcTotalLabourLine : calcTotalLabourLine,
		calcTotalLabourCost : calcTotalLabourCost,
		getPreAuthRequest : getPreAuthRequest,
		showContractList : showContractList,
		collectAttachments : collectAttachments
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
			preAuthClaim.totalLaborCost = 0;
			if(preAuthClaim.claimPartDO){
				for(var i in preAuthClaim.claimPartDO){
					preAuthClaim.claimPartDO[i].partsTotal = preAuthClaim.claimPartDO[i].qty * preAuthClaim.claimPartDO[i].unitPrice;
					preAuthClaim.totalPartCost += preAuthClaim.claimPartDO[i].partsTotal;
				}
			}
			if(preAuthClaim.claimLaborDO){
				for(var i in preAuthClaim.claimLaborDO){
					preAuthClaim.claimLaborDO[i].labourTotal = preAuthClaim.claimLaborDO[i].laborHrs * preAuthClaim.claimLaborDO[i].rate;
					preAuthClaim.totalLaborCost += preAuthClaim.claimLaborDO[i].labourTotal;
				}
			}
			preAuthClaim.totalClaimCost = preAuthClaim.totalLaborCost + preAuthClaim.totalPartCost
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
						alert('Error in adding program: '+response.data.errMessage)
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
	},
	showClaimList = function($scope){
		$scope.showPreAuthClaimList = true;
	};
	
	return {
		init : init,
		selectClaim : selectClaim,
		reqAuth : reqAuth,
		showClaimList : showClaimList
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
			adjudicateClaim.totalLaborCost = 0;
			if(adjudicateClaim.claimPartDO){
				for(var i in adjudicateClaim.claimPartDO){
					adjudicateClaim.claimPartDO[i].partsTotal = adjudicateClaim.claimPartDO[i].qty * adjudicateClaim.claimPartDO[i].unitPrice;
					adjudicateClaim.totalPartCost += adjudicateClaim.claimPartDO[i].partsTotal;
				}
				for(var i in adjudicateClaim.claimLaborDO){
					adjudicateClaim.claimLaborDO[i].laborsTotal = adjudicateClaim.claimLaborDO[i].laborHrs * adjudicateClaim.claimLaborDO[i].rate;
					adjudicateClaim.totalLaborCost += adjudicateClaim.claimLaborDO[i].laborsTotal;
				}
			}
			adjudicateClaim.totalClaimCost = adjudicateClaim.totalLaborCost  + adjudicateClaim.totalPartCost
				+ adjudicateClaim.requestedOtherCharges1 + adjudicateClaim.requestedOtherCharges2;
		}
	},
	calcAdjusmentCost = function(adjustment){
		if(adjustment){
			adjustment.totalAdjustmentPartsCost = 0;
			adjustment.totalAdjustmentLaborsCost = 0;
			if(adjustment.parts){
				for(var i in adjustment.parts){
					adjustment.parts[i].partsTotal = adjustment.parts[i].qty * adjustment.parts[i].unitPrice;
					adjustment.totalAdjustmentPartsCost += adjustment.parts[i].partsTotal;
				}
				for(var i in adjustment.labors){
					adjustment.labors[i].laborsTotal = adjustment.labors[i].laborHrs * adjustment.labors[i].rate;
					adjustment.totalAdjustmentLaborsCost += adjustment.labors[i].laborsTotal;
				}
			}
			adjustment.totalClaimCost = parseInt(adjustment.totalAdjustmentPartsCost)  + parseInt(adjustment.totalAdjustmentLaborsCost)
				+ parseInt(adjustment.requestedOtherCharges1) + parseInt(adjustment.requestedOtherCharges2);
		}
	},
	calReimburshedCost = function($scope){
		//var coveredUsageHours = 
		var contractDeductible = parseInt($scope.adjustments.totalClaimCost) - parseInt($scope.adjudicateClaim.contractDO.deductible);
		var deductibleTRA = parseInt($scope.adjudicateClaim.contractDO.availabeLol) - contractDeductible;
		if(deductibleTRA < 0){
			$scope.adjustments.tra = parseInt($scope.adjudicateClaim.contractDO.availabeLol);
		}else{
			$scope.adjustments.tra = parseInt($scope.adjustments.totalClaimCost);
		}
		
		if(parseInt($scope.adjustments.tra) === parseInt($scope.adjustments.totalClaimCost)){
			$scope.adjustments.customerOwes = parseInt($scope.adjudicateClaim.contractDO.availabeLol);
		}else{
			$scope.adjustments.customerOwes = parseInt($scope.adjustments.totalClaimCost) - parseInt($scope.adjustments.tra);
		}
	},
	selectClaim = function($scope, claim){
		$scope.showAdjudicateClaimList = false;
		$scope.adjudicateClaim = claim;
		$scope.adjustments = {};
		$scope.adjustments.requestedOtherCharges1 = $scope.adjudicateClaim.requestedOtherCharges1;
		$scope.adjustments.requestedOtherCharges2 = $scope.adjudicateClaim.requestedOtherCharges2;
		$scope.adjustments.parts = JSON.parse(JSON.stringify($scope.adjudicateClaim.claimPartDO));
    	$scope.adjustments.labors = JSON.parse(JSON.stringify($scope.adjudicateClaim.claimLaborDO));
		calcCost($scope.adjudicateClaim);
		$scope.adjustments.totalClaimCost = 0;
		calcAdjusmentCost($scope.adjustments);
	},
	submit = function($scope){
		$scope.adjustments.id = $scope.adjudicateClaim.id;
		var fd = new FormData();
		fd.append('data', angular.toJson($scope.adjustments));
		 angular.forEach($scope.attachments, function (value, key) {
			 fd.append('files', value);
	        });
		 return $http({
		        method: 'POST',
		        url: '/agg/adjudicateClaim',
		        headers: {'Content-Type': undefined},
		        data: fd,
		        transformRequest: angular.identity
		        })
		       .success(function(data, status) {
		             alert("success");
		             $window.location.href = '#/agg/fileClaim';
		        });
	},
	backToList = function($scope){
		$scope.showAdjudicateClaimList = true;
		$scope.adjudicateClaim = {};
	},
	calcAdjustmentsOnChange = function($scope){
		calcAdjusmentCost($scope.adjustments);
	},
	collectAttachments = function ($scope, $files) {
		$scope.attachments = $files || [];
    };
	
	return {
		init : init,
		selectClaim : selectClaim,
		submit : submit,
		backToList : backToList,
		calcAdjustmentsOnChange : calcAdjustmentsOnChange,
		collectAttachments : collectAttachments
	}
}]);