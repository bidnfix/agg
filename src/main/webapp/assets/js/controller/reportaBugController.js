'use strict';

routingApp.controller('bugController', function($scope, reportaBugService, $location, $http) {
	$scope.program={};
	$scope.submitBug = function() {
		alert("In submitBug");
		reportaBugService.saveBug($scope.bug);
    };
    

    
});