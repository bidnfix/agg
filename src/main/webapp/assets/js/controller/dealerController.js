'use strict';

routingApp.controller('dealerController', function($scope, dealerService, $location) {
	$scope.dealer={};
	$scope.submitDealer = function() {
		alert("in submitDealer");
		dealerService.saveDealer($scope.dealer);
    };
        
});

routingApp.controller('locationController', function($scope, locationService, $location) {
	$scope.location={};
	$scope.submitLocation = function() {
		alert("in submitLocation");
		locationService.saveLocation($scope.location);
    };
        
});