'use strict';

routingApp.controller('ClaimsController', ['$scope', 'claimService', '$http', '$timeout', '$filter', '$route', function($scope, claimService, $http, $timeout, $filter, $route) {
	$scope.serialNo='';
	$scope.claim={};
	$scope.claim.attachments=[];
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
    	claimService.saveClaim($scope, $route);
    };
    
    $scope.calcTotalPartLine = function(index){
    	claimService.calcTotalPartLine($scope.claim, index);
    };
    
    $scope.calcTotalPartCost = function(){
    	claimService.calcTotalPartCost($scope.claim);
    };
    
    $scope.calcTotalLabourLine = function(index){
    	claimService.calcTotalLabourLine($scope.claim, index);
    };
    
    $scope.calcTotalLabourCost = function(){
    	claimService.calcTotalLabourCost($scope.claim);
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
    
    $scope.onClickBackToList = function(){
    	claimService.showContractList($scope);
	};
	
	$scope.getTheFiles = function ($files) {
    	claimService.collectAttachments($scope, $files);
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

routingApp.controller('ClaimsAdjudicateController', ['$scope', '$http', 'claimsAdjudicateService', 'ModalService', '$window', '$route', function($scope, $http, claimsAdjudicateService, ModalService, $window, $route){
	claimsAdjudicateService.init($scope);
	
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
    	$scope.click = 3;
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
    	claimsAdjudicateService.submit($scope);
	};
    
}]);

routingApp.controller('ModalController', function($scope, close) {
	  
	 $scope.close = function(result) {
	 	close(result, 500); // close, but give 500ms for bootstrap to animate
	 };

	});