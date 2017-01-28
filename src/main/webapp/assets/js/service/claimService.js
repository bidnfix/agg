/**
 * htamada
 */

'use strict';

var commons = {
		renameJsonPropertyNameArray : function(data, propName, newPropName){
			data.forEach(function(e) {
				   if(e.hasOwnProperty(propName)){
					   e[newPropName] = e[propName];
					   delete e[propName];
				   }    
				});
		},
		renameJsonPropertyName : function(data, propName, newPropName){
		   if(data.hasOwnProperty(propName)){
			   data[newPropName] = data[propName];
			   delete data[propName];
		   }    
		}
}

routingApp.factory('claimService', ['$http', '$q', '$window', '$timeout', '$filter', function($http, $q, $window, $timeout, $filter){
	
	var draft = function($scope, claimId, adminFlag){
			$scope.fromDraftFlag = true;
			$scope.showSearchClaim = false;
			$scope.showActiveContractDetails = false;
			$scope.showContractDetails = false;
			$http.get("/agg/claims/" + claimId)
			.then(function(response) {
				if(response.data.status === 'success'){
					if(response.data.data.claim){
						//console.log(JSON.stringify(response.data.data.claim));
						var cStatus = response.data.data.claim.cStatus;
						//commented on Jan 3, 2017
						//$scope.adminFlag = adminFlag;
						$scope.adminFlag = !adminFlag; //new added line on Jan 3, 2017
						$scope.claim = {};
						var claim = response.data.data.claim;
						//commented on Jan 3, 2017
						/*if((cStatus === 9 || cStatus === 5 || cStatus === 7) && !adminFlag){//draft
							$scope.saveBtnFlag = true;
							$scope.updateBtnFlag = false;
							$scope.commentUpdateBtnFlag = false;
							$scope.draftBtnFlag = true;
							$scope.statusFlag = cStatus;
						}else if((cStatus === 4 || cStatus === 5 || cStatus === 10) && adminFlag){//update comments
							$scope.saveBtnFlag = false;
							$scope.updateBtnFlag = false;
							$scope.commentUpdateBtnFlag = true;
							$scope.claim.cStatusValue = cStatus;
							$scope.claim.extComment = response.data.data.claim.comments;
						}else if(adminFlag && (cStatus !== 9)){//update all claim info
							$scope.saveBtnFlag = false;
							$scope.updateBtnFlag = true;
							$scope.commentUpdateBtnFlag = false;
							$scope.claim.cStatusValue = cStatus;
						}*/
						
						if(adminFlag){
							$scope.saveBtnFlag = true;
							$scope.updateBtnFlag = false;
							$scope.commentUpdateBtnFlag = false;
							$scope.draftBtnFlag = true;
							$scope.statusFlag = cStatus;
						}else{
							$scope.saveBtnFlag = false;
							$scope.updateBtnFlag = true;
							$scope.commentUpdateBtnFlag = false;
							$scope.claim.cStatusValue = cStatus;
						}
						
						
						$scope.contractInfoList =  claim.contractDO;
						
						$scope.claim.dealerDO = claim.dealerDO;
						$scope.claim.createdUser = claim.createdUser;
						
						$scope.claim.id = claim.id;
						$scope.claim.currStatus = cStatus;
						$scope.claim.claimId = claim.claimId;
						$scope.claim.deductible = $scope.contractInfoList.deductible;
						$scope.claim.lol = $scope.contractInfoList.lol;
						$scope.claim.availabeLol = $scope.contractInfoList.availabeLol;
						$scope.claim.contractId = $scope.contractInfoList.contractId;
						$scope.claim.serial = $scope.contractInfoList.machineSerialNo;
						$scope.claim.manf = claim.manufacturer;
						$scope.claim.model = $scope.contractInfoList.machineModel;
						$scope.claim.failDate = claim.failDate;
						$scope.claim.reportDate = claim.reportDate;
						$scope.claim.workOrder = claim.workOrder;
						$scope.claim.hoursBreakDown = claim.hoursBreakDown,
						$scope.claim.custComplaint = claim.custComplaint,
						$scope.claim.causeFail = claim.causeFail,
						$scope.claim.correctiveAction = claim.correctiveAction,
						$scope.claim.requestedOtherCharges1 = claim.requestedOtherCharges1;
						$scope.claim.requestedOtherCharges2 = claim.requestedOtherCharges2;
						$scope.claim.approvedOtherCharges1 = claim.approvedOtherCharges1;
						$scope.claim.approvedOtherCharges2 = claim.approvedOtherCharges2;
						$scope.claim.totalAdjustmentLaborsCost = claim.totalAdjustedLaborCost;
						$scope.claim.totalAdjustedPartsCost = claim.totalAdjustedPartsCost;
						$scope.claim.totalAdjustedClaimCost = claim.approvedOtherCharges1 + claim.approvedOtherCharges2 + claim.totalAdjustedLaborCost + claim.totalAdjustedPartsCost;
						claim.claimPartDO = (claim.claimPartDO === null) ? [] : claim.claimPartDO;
						$scope.claim.claimPartVOList = claim.claimPartDO;
						$scope.claim.claimFileDO = claim.claimFileDO;
						$scope.claim.claimsNoteList = claim.claimsNoteList;
						
						$scope.claim.cheqNo = claim.cheqNo;
						$scope.claim.paidDate = claim.paidDate;
						
						claim.claimLaborDO = (claim.claimLaborDO === null) ? [] : claim.claimLaborDO;
						commons.renameJsonPropertyNameArray(claim.claimLaborDO, "rate", "laborHourlyRate");
						$scope.claim.claimLabourVOList = claim.claimLaborDO;
						$scope.contractInfoList.manfactureName = claim.manufacturer;
						commons.renameJsonPropertyName($scope.contractInfoList, 'contractId', 'contractID');
						commons.renameJsonPropertyName($scope.contractInfoList, 'coverageLevelHours', 'usageHoursCovered');
						commons.renameJsonPropertyName($scope.contractInfoList,  'availabeLol', 'availableLol');
						initClaimAddForm($scope, false);
					}
					//getContract($scope, $scope.claim.contractId);
				}
			});			
		},
		getContract = function($scope, contractid){
			$http.get("/agg/contracts/" + contractid)
			.then(function(response) {
				if(response.data.status === 'success'){
					$scope.contractInfoList =  response.data.data.contractDO;
					
				}
			});	
		},
		selectContract = function($scope, data){
			$scope.contractInfoList = data;
			hideContractList($scope);
			getContractCount($scope, initClaimAddForm);
		},
		getContractCount = function($scope, initFunc){
			$http.get("/agg/claims/count/" + $scope.contractInfoList.contractID)
			.then(function(response) {
				if(response.data.status === 'success'){
					$scope.contractInfoList.count =  parseInt(response.data.data.count) + 1;
					initFunc($scope, true);
				}
			});
		},
		initClaimAddForm = function($scope, flag){
			$scope.isSubmitDisabled = false;
			
			if(flag){
				$scope.saveBtnFlag = true;
				$scope.updateBtnFlag = false;
				$scope.commentUpdateBtnFlag = false;
				$scope.saveClaimShow = true;
				$scope.attachmentFlag = false;
				
				$scope.fromDraftFlag = false;
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
				$scope.claim.totalClaimCost = 0;
				$scope.claim.totalLaborCost = 0;
				$scope.claim.partsTotalCost = 0;
				$scope.claim.requestedOtherCharges1 = 0;
				$scope.claim.requestedOtherCharges2 = 0;
				$scope.claim.failDate = $scope.failureDateValid;
				$scope.claim.reportDate = $scope.todayDate;
				
				$scope.claim.cheqNo = $scope.claim.cheqNo;
				$scope.claim.paidDate = $scope.claim.paidDate;
				
			}else{
				if($scope.statusFlag === 9){
					$scope.saveClaimShow = true;
				}else{
					$scope.saveClaimShow = false;
				}
				$scope.claim.totalClaimCost = 0;
				$scope.claim.totalLaborCost = 0;
				$scope.claim.partsTotalCost = 0;
				angular.forEach($scope.claim.claimPartVOList, function(claimPartVO, index){
					calcTotalPartLine($scope.claim, index);
				});
				
				angular.forEach($scope.claim.claimLabourVOList, function(claimLaborVO, index){
					calcTotalLabourLine($scope.claim, index);
				});
				calcTotalPartCost($scope.claim);
				calcTotalLabourCost($scope.claim);
				$scope.claim.totalClaimCost = $scope.claim.totalLaborCost + $scope.claim.partsTotalCost + $scope.claim.requestedOtherCharges1 + $scope.claim.requestedOtherCharges2;
				calReimburshedCost($scope);
				hideContractList($scope);
			}
			
			$scope.$watchCollection('[claim.totalLaborCost, claim.partsTotalCost, claim.requestedOtherCharges1, claim.requestedOtherCharges2]', function(newValues){
				var totalClaimCosst = parseFloat(newValues[0]) + parseFloat(newValues[1]) + parseFloat(newValues[2]) + parseFloat(newValues[3]);
				$scope.claim.totalClaimCost = parseFloat(totalClaimCosst).toFixed(2);
				if(flag || ($scope.statusFlag === 9)){
					$scope.isSubmitDisabled = $scope.claim.totalClaimCost > 1500;
				}
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
		calReimburshedCost = function($scope){
			var contractDeductible = parseFloat($scope.claim.totalAdjustedClaimCost) - parseFloat($scope.contractInfoList.deductible);
			if(contractDeductible < 0){
				contractDeductible = 0;
			}
			var deductibleTRA = parseFloat($scope.contractInfoList.availabeLol) - contractDeductible;
			if(deductibleTRA < 0){
				$scope.claim.tra = parseFloat($scope.contractInfoList.availabeLol);
			}else{
				$scope.claim.tra = contractDeductible;
			}
			
			if(parseFloat($scope.claim.tra) === parseFloat($scope.claim.totalAdjustedClaimCost)){
				$scope.claim.customerOwes = parseFloat($scope.contractInfoList.deductible);
			}else{
				$scope.claim.customerOwes = parseFloat($scope.claim.totalAdjustedClaimCost) - parseFloat($scope.claim.tra);
			}
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
			showSpinner();
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
    						hideSpinner();
    						console.log('no records found');
    					}
    				}
    			}else{
    				$scope.showContractDetails = false;
    			}
    		});
			hideSpinner();
		},
		getAllContracts : function($scope){
			showSpinner();
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
    						hideSpinner();
    						console.log('no records found');
    					}
    				}
    			}else{
    				$scope.showContractDetails = false;
    			}
    		});
			hideSpinner();
		},
		saveClaim : function($scope, $route) {
			showSpinner();
			//alert('in saveClaim');
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
		    	   hideSpinner();
		    	   if(status === 200 && data.status === "success"){
		    		   //alert("success");
		    		   if(!$scope.fromDraftFlag){
		    			   $route.reload();
		    		   }else{
		    			   $window.location = '#/agg/fileClaim';
		    		   }
		    		   
		    	   }else{
		    		   alert("failed");
		    	   }
		        });
			hideSpinner();
		},
		updateClaim : function($scope, $route) {
			showSpinner();
			//alert('in saveClaim');
			var claim = $scope.claim;
			claim.reportDate = $filter('date')(claim.reportDate, 'yyyy-MM-dd');
			claim.failDate = $filter('date')(claim.failDate, 'yyyy-MM-dd');
			if(claim.hasOwnProperty('cStatus')){
				delete claim['cStatus'];
			}
			//claim.cStatus = $scope.newClaimClick;
			console.log(angular.toJson(claim));
			var fd = new FormData();
			fd.append('data', angular.toJson(claim));
			 angular.forEach($scope.attachments, function (value, key) {
				 fd.append('files', value);
		        });
			return $http({
		        method: 'POST',
		        url: '/agg/updateClaim',
		        headers: {'Content-Type': undefined},
		        data: fd,
		        transformRequest: angular.identity
		        })
		       .success(function(data, status) {
		    	   hideSpinner();
		    	   if(status === 200 && data.status === "success"){
		    		   //alert("success");
		    		   if(!$scope.fromDraftFlag){
		    			   $route.reload();
		    		   }else{
		    			   $window.location = '#/agg/claimsInfo';
		    		   }
		    		   
		    	   }else{
		    		   alert("failed");
		    	   }
		        });
			hideSpinner();
		},
		updateClaimComment : function($scope, $route) {
			showSpinner();
			var claim = {};
			claim.id = $scope.claim.id;
			claim.cStatusValue = $scope.claim.cStatusValue;
			claim.extComment = $scope.claim.extComment;
			return $http.put('/agg/updateClaimComment', claim).then(
					function(response) {
						//alert(response.data.status);
						if (response.data.status == 'success') {
							$window.location = '#/agg/claimsInfo';
						} else {
							alert('Error in adding program: '+response.data.errMessage)
							//$('#errMsg').html(response.data.errMessage);
						}
						
					}, function(errResponse) {
						alert('Error while creating program');
						return $q.reject(errResponse);
					});
			hideSpinner();
		},
		selectContract : selectContract,
		calcTotalPartLine : calcTotalPartLine,
		calcTotalPartCost : calcTotalPartCost,
		calcTotalLabourLine : calcTotalLabourLine,
		calcTotalLabourCost : calcTotalLabourCost,
		getPreAuthRequest : getPreAuthRequest,
		showContractList : showContractList,
		collectAttachments : collectAttachments,
		draft : draft
	}
}]);

routingApp.factory('claimPreAuthReqService', ['$http', '$q', '$window', '$timeout', function($http, $q, $window, $timeout){
	var init = function($scope){
		$http.get("/agg/preAuthClaimReq")
	    .then(function(response) {
	    	if(response.data.data){
	    		$scope.preAuthClaimList = response.data.data.preAuthClaimList;
	    		$timeout(function () {
		        	$('#preauthClaimsListTable').DataTable();
		        }, 300);
	    	}
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
		$scope.preAuthClaim.claimsNoteList = claim.claimsNoteList;
	},
	submit = function($scope, status){
		showSpinner();
		var data = {};
		data.id = $scope.preAuthClaim.id;
		data.cStatus = status;
		if($scope.preAuthClaim.extComment){
			data.extComment = $scope.preAuthClaim.extComment;
		}
		$http.put('/agg/preAuthClaimReq', data).then(
				function(response) {
					//alert(response.data.status);
					hideSpinner();
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
		hideSpinner();
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
		$http.get("/agg/currentUserRole")
	    .then(function(response) {
	    	//$scope.adjudicateClaimList = response.data.data.preAuthClaimList;
	    	console.log(angular.toJson(response));
	    	//$scope.editFlag = true;
	    	$scope.editFlag = (response.data.data.roleList.accountType === 'admin') ? false : true;
	    	$scope.commentFlag = true;
	    	$scope.cheqFlag = true;
	    });
		$http.get("/agg/adjudicateClaim")
	    .then(function(response) {
	    	$scope.adjudicateClaimList = response.data.data.preAuthClaimList;
	    	$timeout(function () {
	        	$('#preauthClaimsListTable').DataTable();
	        }, 300);
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
			//adjustment.totalAdjustmentPartsCost = 0;
			//adjustment.totalAdjustmentLaborsCost = 0;
			if(adjustment.parts && adjustment.totalAdjustmentPartsCost === 0){
				for(var i in adjustment.parts){
					adjustment.parts[i].partsTotal = adjustment.parts[i].qty * adjustment.parts[i].unitPrice;
					adjustment.totalAdjustmentPartsCost += adjustment.parts[i].partsTotal;
				}
				
				adjustment.totalAdjustmentPartsCost = parseFloat(adjustment.totalAdjustmentPartsCost).toFixed(2);
			}
			if(adjustment.labors && adjustment.totalAdjustmentLaborsCost === 0){
				for(var i in adjustment.labors){
					adjustment.labors[i].laborsTotal = adjustment.labors[i].laborHrs * adjustment.labors[i].rate;
					adjustment.totalAdjustmentLaborsCost += adjustment.labors[i].laborsTotal;
				}
				adjustment.totalAdjustmentLaborsCost = parseFloat(adjustment.totalAdjustmentLaborsCost).toFixed(2);
			}
			adjustment.totalAdjustedClaimCost = parseFloat(adjustment.totalAdjustmentPartsCost)  + parseFloat(adjustment.totalAdjustmentLaborsCost)
				+ parseFloat(adjustment.approvedOtherCharges1) + parseFloat(adjustment.approvedOtherCharges2);
		}
	},
	calReimburshedCost = function($scope){
		//var coveredUsageHours = 
		var contractDeductible = parseFloat($scope.adjustments.totalAdjustedClaimCost) - parseFloat($scope.adjudicateClaim.contractDO.deductible);
		if(contractDeductible < 0){
			contractDeductible = 0;
		}
		var deductibleTRA = parseFloat($scope.adjudicateClaim.contractDO.availabeLol) - contractDeductible;
		if(deductibleTRA < 0){
			$scope.adjustments.tra = parseFloat($scope.adjudicateClaim.contractDO.availabeLol);
		}else{
			$scope.adjustments.tra = contractDeductible;
		}
		
		if(parseFloat($scope.adjustments.tra) === parseFloat($scope.adjustments.totalAdjustedClaimCost)){
			$scope.adjustments.customerOwes = parseFloat($scope.adjudicateClaim.contractDO.deductible);
		}else{
			$scope.adjustments.customerOwes = parseFloat($scope.adjustments.totalAdjustedClaimCost) - parseFloat($scope.adjustments.tra);
		}
	},
	selectClaim = function($scope, claim){
		$scope.showAdjudicateClaimList = false;
		$scope.adjudicateClaim = claim;
		$scope.adjustments = {};
		$scope.adjustments.requestedOtherCharges1 = $scope.adjudicateClaim.requestedOtherCharges1;
		$scope.adjustments.requestedOtherCharges2 = $scope.adjudicateClaim.requestedOtherCharges2;
		$scope.adjustments.approvedOtherCharges1 = $scope.adjudicateClaim.approvedOtherCharges1;
		$scope.adjustments.approvedOtherCharges2 = $scope.adjudicateClaim.approvedOtherCharges2;
		$scope.adjustments.totalAdjustmentPartsCost = $scope.adjudicateClaim.totalAdjustedPartsCost;
		$scope.adjustments.totalAdjustmentLaborsCost = $scope.adjudicateClaim.totalAdjustedLaborCost;
		$scope.adjustments.parts = JSON.parse(JSON.stringify($scope.adjudicateClaim.claimPartDO));
    	$scope.adjustments.labors = JSON.parse(JSON.stringify($scope.adjudicateClaim.claimLaborDO));
    	$scope.adjustments.claimsNoteList = $scope.adjudicateClaim.claimsNoteList;
    	
    	$scope.adjustments.paidDate = new Date();
    	if($scope.adjustments.approvedOtherCharges1 === 0){
    		$scope.adjustments.approvedOtherCharges1 = $scope.adjustments.requestedOtherCharges1;
    	}
    	if($scope.adjustments.approvedOtherCharges2 === 0){
    		$scope.adjustments.approvedOtherCharges2 = $scope.adjustments.requestedOtherCharges2;
    	}
		calcCost($scope.adjudicateClaim);
		$scope.adjustments.totalAdjustedClaimCost = 0;
		$scope.adjustments.totalClaimCost = $scope.adjudicateClaim.totalClaimCost;
		calcAdjusmentCost($scope.adjustments);
		calReimburshedCost($scope);
	},
	updateClaimStatus = function(data){
		$http.put('/agg/preAuthClaimReq', data).then(
				function(response) {
					if (response.data.status == 'success') {
						$window.location = '#/agg/fileClaim';
					} else {
						alert('Error in adding program: '+response.data.errMessage)
					}
					
				}, function(errResponse) {
					alert('Error while creating program');
					return $q.reject(errResponse);
			});
	},
	submit = function($scope){
		if($scope.editFlag){
			var data = {};
			data.id = $scope.adjudicateClaim.id;
			
			if($scope.click === 3){
				$window.location.href = '#/agg/fileClaim';
			}else{
				if($scope.adjustments.extComment){
					data.extComment = $scope.adjustments.extComment;
				}
				if($scope.click === 2){
					data.cStatus = 'cancel';
					updateClaimStatus(data);
				}else if($scope.click === 1){
					data.cStatus = 'pre_authorized_approved_with_adjustments';
					updateClaimStatus(data);
				}
			}
		}else{
			showSpinner();
			if($scope.click === 4){
				var data = {};
				data.id = $scope.adjudicateClaim.id;
				if($scope.adjustments.extComment){
					data.extComment = $scope.adjustments.extComment;
				}
				data.cStatus = 'cancel';
				updateClaimStatus(data);
				
			}else{
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
				           //alert("success");
				    	   hideSpinner();
				           $window.location.href = '#/agg/fileClaim';
				        });
			}
			hideSpinner();
		}
	},
	backToList = function($scope){
		$scope.showAdjudicateClaimList = true;
		$scope.adjudicateClaim = {};
	},
	calcAdjustmentsOnChange = function($scope){
		calcAdjusmentCost($scope.adjustments);
		calReimburshedCost($scope);
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
			//alert(JSON.stringify($scope.draftsClaimList));
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
			
			$scope.claim.cheqNo = claim.cheqNo;
			$scope.claim.paidDate = claim.paidDate;
			
			if(!$scope.claim.claimPartVOList){
				$scope.claim.claimPartVOList = [];
				$scope.claim.claimPartVOList.push({});
			}
			 if(!$scope.claim.claimLabourVOList){
				 $scope.claim.claimLabourVOList = [];
					$scope.claim.claimLabourVOList.push({});
			 }
			commons.renameJsonPropertyNameArray($scope.claim.claimLabourVOList, "rate", "laborHourlyRate");
			$scope.todayDate = new Date();
			$scope.failureDateValid = updateDate($scope.todayDate, -1);
			calcCost($scope.claim);
			$scope.$watchCollection('[claim.totalLaborCost, claim.partsTotalCost, claim.requestedOtherCharges1, claim.requestedOtherCharges2]', function(newValues){
				if($scope.claim){
					var totalClaimCosst = parseFloat(newValues[0]) + parseFloat(newValues[1]) + parseFloat(newValues[2]) + parseFloat(newValues[3]);
					$scope.claim.totalClaimCost = parseFloat(totalClaimCosst).toFixed(2);
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
			showSpinner();
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
    						hideSpinner();
    						console.log('no records found');
    					}
    				}
    			}else{
    				$scope.showContractDetails = false;
    			}
    			hideSpinner();
    		});
		},
		saveClaim : function($scope) {
			showSpinner();
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
		    	   hideSpinner();
		    	   if(status === 200 && data.status === "success"){
		    		   //alert("success");
		    		   $window.location.href = '#/agg/fileClaim';
		    	   }else{
		    		   hideSpinner();
		    		   alert("failed");
		    	   }
		        });
			hideSpinner();
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