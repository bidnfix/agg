'use strict';

routingApp.controller('claimController', function($scope, claimService, $location, $http) {
	$scope.report={};
	$scope.submitBug = function() {
		alert("In submitBug");
		reportaBugService.saveBug($scope.report);
    };
    
});