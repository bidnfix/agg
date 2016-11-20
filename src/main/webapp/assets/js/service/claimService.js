/**
 * htamada
 */

'use strict';

var commons = {
		renameJsonPropertyName : function(data, propName, newPropName){
			data.forEach(function(e) {
				   if(e.hasOwnProperty(propName)){
					   e[newPropName] = e[propName];
					   delete e[propName];
				   }    
				});
		}
}

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
			}else {
				date = new Date(date);
			}
			
			console.log(typeof date);
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
		showContractList = function($scope, $route){
			$scope.showContractDetails = false;
			if($scope.contractDOList.length > 1){
				$scope.showActiveContractDetails = true;
				$scope.showSearchClaim = true;
			}else{
				$route.reload();
			}
			
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
		getAllContracts : function($scope){
			$scope.showContractDetails = false;
			$http.get("/agg/contracts")
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
		saveClaim : function($scope, $route) {
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
		    		   $route.reload();
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

routingApp.factory('claimDraftService', ['$http', '$q', '$window', '$timeout', '$filter', function($http, $q, $window, $timeout, $filter){
	var  init = function($scope){
			console.log('draft init service');
			$http.get("/agg/draftClaim")
		    .then(function(response) {
		    	$scope.draftsClaimList = response.data.data.draftClaimList;
		    });
		},
		showClaimList = function($scope){
			alert(JSON.stringify($scope.draftsClaimList));
			$scope.showDraftsClaimList = false;
		},
		selectClaim = function($scope, claim){
			$scope.showDraftsClaimList = false;
			$scope.contractInfoList = {};
			$scope.claim = {};
			$scope.claim.deductible = 200;
			$scope.claim.lol = 42500;
			$scope.claim.availabeLol = 42500;
			$scope.claim.contractId = claim.contractId;
			$scope.claim.serial = claim.serial;
			$scope.claim.manf = claim.manufacturer;
			$scope.claim.model = claim.machineModel;
			$scope.claim.claimId = claim.claimId;
			$scope.claim.workOrder = claim.workOrder;
			$scope.claim.hoursBreakDown = claim.hoursBreakDown;
			$scope.claim.requestedOtherCharges1 = claim.requestedOtherCharges1;
			$scope.claim.requestedOtherCharges2 = claim.requestedOtherCharges2;
			$scope.claim.custComplaint = claim.custComplaint;
			$scope.claim.causeFail = claim.causeFail;
			$scope.claim.correctiveAction = claim.correctiveAction;
			$scope.todayDate = new Date();
			$scope.failureDateValid = updateDate($scope.todayDate, -1);
			$scope.contractInfoList.machineSerialNo = claim.serial;
			$scope.contractInfoList.manfactureName = claim.manufacturer;
			$scope.contractInfoList.machineModel = claim.machineModel;
			$scope.contractInfoList.coverageType = claim.coverageType;
			$scope.claim.claimPartVOList = claim.claimPartDO;
			$scope.claim.claimLabourVOList = claim.claimLaborDO;
			$scope.claim.failDate = new Date(claim.failDate);
			$scope.claim.reportDate = new Date(claim.reportDate);
			if(!$scope.claim.claimPartVOList){
				$scope.claim.claimPartVOList = [];
				$scope.claim.claimPartVOList.push({});
			}
			 if(!$scope.claim.claimLabourVOList){
				 $scope.claim.claimLabourVOList = [];
					$scope.claim.claimLabourVOList.push({});
			 }
			commons.renameJsonPropertyName($scope.claim.claimLabourVOList, "rate", "laborHourlyRate");
			$scope.todayDate = new Date();
			$scope.failureDateValid = updateDate($scope.todayDate, -1);
			calcCost($scope.claim);
			$scope.$watchCollection('[claim.totalLaborCost, claim.partsTotalCost, claim.requestedOtherCharges1, claim.requestedOtherCharges2]', function(newValues){
				if($scope.claim){
					$scope.claim.totalClaimCost = parseInt(newValues[0]) + parseInt(newValues[1]) + parseInt(newValues[2]) + parseInt(newValues[3]);
					$scope.isSubmitDisabled = $scope.claim.totalClaimCost > 1500;
				}
			});
			$scope.$watch('claim.reportDate', function(newValues){
				if($scope.claim){
					$scope.failureDateValid = updateDate($scope.claim.reportDate, -1);
				}
			});
			//$scope.claim.totalLaborCost = $scope.claim.claimLaborDO.laborHrs * $scope.claim.claimLaborDO.rate;
			$scope.extCommentFlag = true;
			
		},
		updateDate = function(date, days){
			if(!date){
				date = new Date();
			}else {
				date = new Date(date);
			}
			
			console.log(typeof date);
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
		showClaimList = function($scope){
			$scope.showDraftsClaimList = true;
			$scope.claim = undefined;
		},
		collectAttachments = function ($scope, $files) {
			$scope.attachments = $files || [];
	    },
	    calcCost = function(claim){
			if(claim){
				claim.partsTotalCost = 0;
				claim.totalLaborCost = 0;
				if(claim.claimPartVOList){
					for(var i in claim.claimPartVOList){
						claim.claimPartVOList[i].partsTotal = claim.claimPartVOList[i].qty * claim.claimPartVOList[i].unitPrice;
						claim.partsTotalCost += claim.claimPartVOList[i].partsTotal;
					}
				}
				if(claim.claimLabourVOList){
					for(var i in claim.claimLabourVOList){
						claim.claimLabourVOList[i].labourTotal = claim.claimLabourVOList[i].laborHrs * claim.claimLabourVOList[i].laborHourlyRate;
						claim.totalLaborCost += claim.claimLabourVOList[i].labourTotal;
					}
				}
				claim.totalClaimCost = claim.totalLaborCost + claim.partsTotalCost
					+ claim.requestedOtherCharges1 + claim.requestedOtherCharges2;
			}
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
		calcTotalPartLine : calcTotalPartLine,
		calcTotalPartCost : calcTotalPartCost,
		calcTotalLabourLine : calcTotalLabourLine,
		calcTotalLabourCost : calcTotalLabourCost,
		getPreAuthRequest : getPreAuthRequest,
		showClaimList : showClaimList,
		collectAttachments : collectAttachments,
		selectClaim : selectClaim,
		getDraftClaims : init
	}
}]);