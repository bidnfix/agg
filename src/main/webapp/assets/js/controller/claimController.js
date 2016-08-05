'use strict';

routingApp.controller('claimController', function($scope, claimService, $location, $http) {
	$scope.claim={};
	$scope.submitClaim = function() {
		alert("In submitClaim");
		claimService.saveClaim($scope.claim);
    };
    

    
});