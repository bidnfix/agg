'use strict';

routingApp.controller('ClaimsController', ['$scope', 'claimService', '$http', '$timeout', '$filter', function($scope, claimService, $http, $timeout, $filter) {
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
    	}
    };
    $scope.onClickSelectContract = function(data){
    	claimService.selectContract($scope, data);
    };
    $scope.onClickSubmitClaim = function(){
    	claimService.saveClaim($scope);
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

routingApp.controller('ClaimsAdjudicateController', function($scope, $http, claimsAdjudicateService, ModalService, $window){
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
    	ModalService.showModal({
            templateUrl: 'modal.html',
            controller: "ModalController"
        }).then(function(modal) {
            modal.element.show();
            modal.close.then(function(result) {
                if(result === 'Yes'){
                	claimsAdjudicateService.submit($scope);
                }
            });
        });
    	claimsAdjudicateService.submit($scope);
    };
    
});

routingApp.controller('ModalController', function($scope, close) {
	  
	 $scope.close = function(result) {
	 	close(result, 500); // close, but give 500ms for bootstrap to animate
	 };

	});