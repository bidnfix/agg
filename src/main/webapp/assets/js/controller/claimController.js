'use strict';

routingApp.controller('ClaimsController', ['$scope', 'claimService', '$http', '$timeout', function($scope, claimService, $http, $timeout) {
	$scope.serialNo='';
	$scope.claim={};
	$scope.submitClaim = function() {
		claimService.saveClaim($scope.claim);
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
    	claimService.saveClaim($scope.claim, $scope.newClaimClick);
    };
    
    $scope.calcTotalPartLine = function(index){
    	claimService.calcTotalPartLine($scope.claim, index);
    };
    
    $scope.calcTotalPartCost = function(){
    	claimService.calcTotalPartCost($scope.claim);
    };
    
    $scope.saveAsDraft = function(){
    	//claimService.saveClaim($scope.claim, 'Draft');
    	$scope.newClaimClick='Draft';
    };
    
    $scope.reqAuth = function(){
    	//claimService.saveClaim($scope.claim, 'pre_authorized_requested');
    	$scope.newClaimClick='pre_authorized_requested';
    };
    
    $scope.reqSubmit = function(){
    	//claimService.saveClaim($scope.claim, 'pre_authorized_requested');
    	$scope.newClaimClick='Pending';
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
});

routingApp.controller('ClaimsAdjudicateController', function($scope, $http, claimsAdjudicateService, $window){
	claimsAdjudicateService.init($scope);
	
	$scope.onClickSelectClaim = function(claim){
		claimsAdjudicateService.selectClaim($scope, claim);
	};
	
	$scope.reqAuth = function(status){
		claimsAdjudicateService.reqAuth($scope, status);
	};
});