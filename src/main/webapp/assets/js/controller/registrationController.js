'use strict';

App.controller('registrationController', function($scope, registrationService, $location, $http) {
	$scope.dealer={};
	$scope.submitDealer = function() {
		alert("in submitDealer");
		registrationService.saveRegistration($scope.dealer);
    };
});
