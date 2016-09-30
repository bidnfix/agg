'use strict';

App.controller('registrationController', function($scope, registrationService, $location, $http) {
	$scope.dealer={};
	$scope.submitDealer = function() {
		registrationService.saveRegistration($scope.dealer, $scope);
    };
});
