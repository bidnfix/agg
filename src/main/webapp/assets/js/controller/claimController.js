'use strict';

routingApp.controller('bugController', function($scope, reportaBugService, $location, $http) {
	$scope.claim={};
	$scope.submitClaim = function() {
		alert("In submitClaim");
		claimService.saveClaim($scope.claim);
    };
    

    
});