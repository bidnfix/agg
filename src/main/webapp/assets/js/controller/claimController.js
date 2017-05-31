'use strict';

routingApp.controller('ClaimsController', ['$scope', 'claimService', '$http', '$timeout', '$filter', '$route', '$routeParams', '$rootScope', '$window',
                                           function($scope, claimService, $http, $timeout, $filter, $route, $routeParams, $rootScope, $window) {
	$scope.serialNo='';
	$scope.claim={};
	$scope.claim.attachments=[];
	if($routeParams.claimId){
		/*console.log($rootScope.userType);
		if(!$rootScope.userType){
			$http.get("/agg/currentUserRole")
			   .then(function(response) {
			   	if(response.data.data.roleList){
			   		$rootScope.userType = response.data.data.roleList.accountType;
			   		claimService.draft($scope, $routeParams.claimId, $rootScope.userType === 'admin' ? true : false);
			   	}
		   });			
		}else{
			claimService.draft($scope, $routeParams.claimId, $rootScope.userType === 'admin' ? true : false);
		}*/
		console.log($rootScope.draftClaimsFlag);
		claimService.draft($scope, $routeParams.claimId, $rootScope.draftClaimsFlag);
		
	}else{
		$scope.showSearchClaim=true;
		$scope.showContractDetails=false; 
		$scope.showActiveContractDetails=false;
	}
	//datepicker changes
	$scope.failureDatePickerIsOpen = false;
	$scope.opens = [];
	
	$scope.failureDatePickerOpen = function ($event) {
      if ($event) {
          $event.preventDefault();
          $event.stopPropagation(); // This is the magic
      }
      $scope.failureDatePickerIsOpen = true;
    };
    
    $scope.reportDatePickerIsOpen = false;
    $scope.reportDatePickerOpen = function ($event) {
        if ($event) {
            $event.preventDefault();
            $event.stopPropagation(); // This is the magic
        }
        $scope.reportDatePickerIsOpen = true;
    };
    
    $scope.dateOptions = {
		format: "MM/dd/yyyy",
	    showWeeks: false
	};
	
	$scope.submitClaim = function() {
		claimService.saveClaim($scope);
    };
	$scope.edit = function(claimId) {
		$http.get("/agg/editClaim")
		.then(function(response) {
			$scope.quoteDO = response.data.data.quoteDO;
		});
    };
    $scope.onClickSearchSerialNo = function(){
    	if($scope.serialNo){
    		claimService.getSerialNumberInfo($scope);
    	}else{
    		claimService.getAllContracts($scope);
    	}
    };
    $scope.onClickSelectContract = function(data){
    	claimService.selectContract($scope, data);
    };
    $scope.onClickSubmitClaim = function(){
    	if($scope.updateBtnFlag){
    		claimService.updateClaim($scope, $route);
    	}else if($scope.commentUpdateBtnFlag){
    		claimService.updateClaimComment($scope, $route);
    	}else{
    		claimService.saveClaim($scope, $route);
    	}    	
    };
    
    $scope.calcTotalPartLine = function(index){
    	claimService.calcTotalPartLine($scope.claim, index);
    };
    
    $scope.calcTotalPartCost = function(){
    	claimService.calcTotalPartCost($scope.claim);
    };
    
    $scope.removeClaimPart = function(claimPart){
    	var index = $scope.claim.claimPartVOList.indexOf(claimPart);
		$scope.claim.claimPartVOList.splice(index, 1);
    };
    
    $scope.removeClaimLabor = function(claimLabor){
    	var index = $scope.claim.claimLabourVOList.indexOf(claimLabor);
		$scope.claim.claimLabourVOList.splice(index, 1);
    };
    
    $scope.calcTotalLabourLine = function(index){
    	claimService.calcTotalLabourLine($scope.claim, index);
    };
    
    $scope.calcTotalLabourCost = function(){
    	claimService.calcTotalLabourCost($scope.claim);
    };
    
    $scope.saveAsDraft = function(){
    	$scope.newClaimClick='Draft'; 
    	$scope.attachmentFlag = false;   	
    	claimService.saveClaim($scope, $route);
    };
    
    $scope.reqAuth = function(){
    	$scope.newClaimClick='pre_authorized_requested';
    	$scope.attachmentFlag = false;
    };
    
    $scope.reqSubmit = function(){
    $scope.attachmentFlag = true;
    	$scope.newClaimClick='Pending';
    };
    
    $scope.updateClaim = function(){
    	
    };
    $scope.updateCommentClaim = function(){
    	
    };
    $scope.addAttachment = function (e) {
        $scope.$apply(function () {
        	$scope.attachments = $scope.claim.attachments || [];
            for (var i = 0; i < e.files.length; i++) {
            	$scope.attachments.push(e.files[i])
            }
        });
    };
    
    $scope.onClickBackToList = function(){
    	if($scope.updateBtnFlag || $scope.commentUpdateBtnFlag || $scope.draftBtnFlag){
    		$window.location = '#/agg/claimsInfo';
    	}else{
    		claimService.showContractList($scope);
    	}
	};
	
	$scope.getTheFiles = function ($files) {
    	claimService.collectAttachments($scope, $files);
    };
    
    $scope.changeStatus = function(status){
    	if(status === 4 || status === 5 || status === 6 || status === 10){
    		claimService.changeStatus($scope, status, $route);
    	}
	};
    
}]);

routingApp.controller('ClaimsDraftsController', function($scope, $http, claimDraftService, $window){
	claimDraftService.getDraftClaims($scope);
	
	$scope.onClickBackToList = function(){
		claimDraftService.showClaimList($scope);
	};
	
	$scope.onClickSelectClaim = function(claim){
		claimDraftService.selectClaim($scope, claim);
	};
	
	$scope.calcTotalPartLine = function(index){
		claimDraftService.calcTotalPartLine($scope.claim, index);
    };
    
    $scope.calcTotalPartCost = function(){
    	claimDraftService.calcTotalPartCost($scope.claim);
    };
    
    $scope.calcTotalLabourLine = function(index){
    	claimDraftService.calcTotalLabourLine($scope.claim, index);
    };
    
    $scope.calcTotalLabourCost = function(){
    	claimDraftService.calcTotalLabourCost($scope.claim);
    };
    
    $scope.saveAsDraft = function(){
    	$scope.newClaimClick='Draft';
    };
    
    $scope.reqAuth = function(){
    	$scope.newClaimClick='pre_authorized_requested';
    };
    
    $scope.reqSubmit = function(){
    	$scope.newClaimClick='Pending';
    };
    
    $scope.addAttachment = function (e) {
        $scope.$apply(function () {
        	$scope.attachments = $scope.claim.attachments || [];
            for (var i = 0; i < e.files.length; i++) {
            	$scope.attachments.push(e.files[i])
            }
        });
    };
    
    $scope.onClickSubmitClaim = function(){
    	claimDraftService.saveClaim($scope);
    };
});

routingApp.controller('ClaimsPreAuthController', function($scope, $http, claimPreAuthReqService, $window){
	claimPreAuthReqService.init($scope);
	
	$scope.onClickSelectClaim = function(claim){
		claimPreAuthReqService.selectClaim($scope, claim);
	};
	
	$scope.reqAuth = function(status){
		claimPreAuthReqService.reqAuth($scope, status);
	};
	
	$scope.onClickBackToList = function(){
		claimPreAuthReqService.showClaimList($scope);
	};
});

routingApp.controller('ClaimsAdjudicateController', ['$scope', '$http', 'claimsAdjudicateService', 'ModalService', '$window', '$route', '$filter', 'claimType', '$routeParams', function($scope, $http, claimsAdjudicateService, ModalService, $window, $route, $filter, claimType, $routeParams){
	if($routeParams.claimId){
		claimsAdjudicateService.getClaim($scope, $routeParams.claimId);
	}else{
		claimsAdjudicateService.init($scope, claimType);
	}
	
	
	//datepicker changes
	$scope.paidDatePickerIsOpen = false;
	$scope.opens = [];
	$scope.dateOptions = {
		format: "MM/dd/yyyy",
	    showWeeks: false
	};
	
	$scope.paidDatePickerOpen = function ($event) {
      if ($event) {
          $event.preventDefault();
          $event.stopPropagation(); // This is the magic
      }
      $scope.paidDatePickerIsOpen = true;
    };
    
    $scope.chkDatePickerOpen = function ($event, checkDO) {
	  checkDO.chkDatePickerIsOpen = false;
	    if ($event) {
	        $event.preventDefault();
	        $event.stopPropagation(); // This is the magic
	    }
	    checkDO.chkDatePickerIsOpen = true;
	};
  	
  	$scope.addCheck = function(){
  		$scope.calcCheckAmtTotal();
  		var totalAmount =  parseFloat(parseFloat($scope.adjustments.tra).toFixed(2)) - parseFloat(parseFloat($scope.adjustments.totalCheckAmount).toFixed(2));
  		if(totalAmount > 0){
  			totalAmount = parseFloat(parseFloat(totalAmount).toFixed(2));
  			$scope.adjustments.checkDOList.push({receivedDate:new Date(), amount:totalAmount});
  		}
  		$scope.calcCheckAmtTotal();
  	};
  	
  	$scope.removeCheck = function(checkDO){
  		var index = $scope.adjustments.checkDOList.indexOf(checkDO);
  		$scope.adjustments.checkDOList.splice(index, 1);
  		$scope.calcCheckAmtTotal();
    };
      
    $scope.calcCheckAmtTotal = function(){
		$scope.adjustments.totalCheckAmount = 0;
		angular.forEach($scope.adjustments.checkDOList, function(checkDO, index){
			$scope.adjustments.totalCheckAmount += checkDO.amount;
		});
  	};
	
	$scope.onClickSelectClaim = function(claim){
		claim.attachments=[];
		claimsAdjudicateService.selectClaim($scope, claim);
	};
	
	$scope.onClickBackToList = function(){
		claimsAdjudicateService.backToList($scope);
	};
	
	$scope.reqAuth = function(status){
		claimsAdjudicateService.reqAuth($scope, status);
	};
	
	$scope.calcOnChange = function(){
		claimsAdjudicateService.calcAdjustmentsOnChange($scope);
    };
    
    $scope.getTheFiles = function ($files) {
    	claimsAdjudicateService.collectAttachments($scope, $files);
    };
    
    $scope.onClickClose = function(){
    	$scope.commentFlag = false;
    	$scope.cheqFlag = true;
    	$scope.click = 3;
    };
    
    $scope.onClickApprovedForPayment = function(){
    	$scope.commentFlag = false;
    	$scope.cheqFlag = true;
    	$scope.click = 5;
    };
    
    $scope.onClickReject = function(){
    	$scope.commentFlag = true;
    	$scope.cheqFlag = false;
    	$scope.click = 4;
    };
    
    $scope.onClickUpdate = function(){
    	$scope.commentFlag = true;
    	$scope.click = 1;
    };
    
    $scope.onClickCancel = function(){
    	$scope.commentFlag = false;
    	$scope.click = 2;
    };
    
    $scope.onClickSubmitClaim = function(){
    	if($scope.click === 3){
    		var chkCond = true;
    		if($scope.adjustments.tra > 0 && $scope.adjustments.checkDOList.length == 0){
    			chkCond = false;
    			alert("Please enter check details before approving the claim.");
    		}
    		
    		if(chkCond && $window.confirm('You are about to pay the dealer claim '+$scope.adjudicateClaim.claimId+', an amount of '+$filter('currency')($scope.adjustments.tra, "$")+'. This action cannot be reversed. Are you sure you want to continue?')) {
    			claimsAdjudicateService.submit($scope);
    		}
    	}else{
    		claimsAdjudicateService.submit($scope);
    	}
	};
    
}]);

routingApp.controller('ModalController', function($scope, close) {
	  
	 $scope.close = function(result) {
	 	close(result, 500); // close, but give 500ms for bootstrap to animate
	 };

	});