'use strict';

routingApp.controller('dealerController', function($scope, dealerService, $location) {
	$scope.dealer={};
	$scope.submitDealer = function() {
		alert("in submitDealer");
		dealerService.saveDealer($scope.dealer);
    };
        
});