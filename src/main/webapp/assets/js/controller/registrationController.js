'use strict';

App.controller('registrationController', function($scope, registrationService, $location, $http) {
	$scope.dealer={};
	$scope.isUserExists = false;
	$scope.submitDealer = function() {
		if(!$scope.isUserExists){
			registrationService.saveRegistration($scope.dealer, $scope);
		}
    };
    
    $scope.isUserNameExists = function(userName){
    	$http.post("/agg/isDealerUserNameExists", userName)
        .then(function(response) {
        	//alert(response.data.data.programList);
        	$scope.isUserExists = response.data.data;
            if($scope.isUserExists){
            	$('#successMsg').addClass('hidden');
            	$('#successMsg').html("");
				$('#errMsg').removeClass('hidden');
				$('#errMsg').html("Dealer with Username '<strong>"+userName+"</strong>' already exists!");
            }else{
            	$('#successMsg').addClass('hidden');
				$('#errMsg').addClass('hidden');
				$('#successMsg').html("");
				$('#errMsg').html("");
            }
        });
    }
});
