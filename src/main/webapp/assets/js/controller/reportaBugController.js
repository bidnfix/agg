'use strict';

routingApp.controller('bugController', function($scope, reportaBugService, $location, $http) {
	$scope.report={};
	$scope.submitBug = function() {
		alert("In submitBug");
		reportaBugService.saveBug($scope.report);
    };
    

    
});