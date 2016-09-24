'use strict';

routingApp.controller('dealerController', function($scope, dealerService, $location, $http) {
	$scope.dealer={};
	$scope.submitDealer = function() {
		//alert("in submitDealer");
		dealerService.saveDealer($scope.dealer);
    };
});

routingApp.controller('locationController', function($scope, locationService, $location) {
	$scope.location={};
	$scope.submitLocation = function() {
		//alert("in submitLocation");
		locationService.saveLocation($scope.location);
    };
        
});

routingApp.controller('userController', function($scope, userService, $location, $http) {
	$scope.user={};
	$scope.submitUser = function() {
		//alert("in submitUser");
		userService.saveUser($scope.user);
    };
    
   /* $scope.getLocation = function() {
    	 $http.get("/agg/locationInfo/"+$scope.user.dealerDO.id)
 	    .then(function(response) {
 	        $scope.locationList = response.data.data;
 	    });
    };*/
        
});