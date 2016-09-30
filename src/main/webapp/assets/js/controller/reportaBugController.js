'use strict';

routingApp.controller('claimController', function($scope, claimService, $location, $http) {
	$scope.report={};
	$scope.submitBug = function() {
		reportaBugService.saveBug($scope.report);
    };
    
});