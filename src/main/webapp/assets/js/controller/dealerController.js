'use strict';

routingApp.controller('dealerController', function($scope, dealerService, $location, $http) {
	$scope.dealer={};
	$scope.isUserExists = false;
	$scope.dealer.adjustmentFactor = 0.00;
	$scope.submitDealer = function(dealerInfoForm) {
		//alert("in submitDealer");
		if(dealerInfoForm.$valid && !$scope.isUserExists){
			dealerService.saveDealer($scope.dealer);
		}
		
    };
    
    $scope.isUserNameExists = function(userName){
    	if(userName != null && userName != ""){
    		$http.post("/agg/isUserNameExists", userName)
            .then(function(response) {
            	//alert(response.data.data.programList);
            	$scope.isUserExists = response.data.data;
                if($scope.isUserExists){
                	//alert("Dealer with Username '"+userName+"' already exists!");
                	$('#dealerErrorMsg').html("Dealer with Username '<strong>"+userName+"</strong>' already exists!");
                	$('#dealerErrorMsg').removeClass('hidden');
                }else{
                	$('#dealerErrorMsg').addClass('hidden');
                }
            });
    	}else{
    		$scope.isUserExists = false;
    		$('#dealerErrorMsg').addClass('hidden');
    	}
    }
});

routingApp.controller('locationController', function($scope, locationService, $location) {
	$scope.location={};
	$scope.submitLocation = function() {
		//alert("in submitLocation");
		locationService.saveLocation($scope.location);
    };
        
});

routingApp.controller('userController', function($scope, userService, $location, $http, $routeParams) {
	$scope.user={};
	$scope.isUserExists = false;
	$scope.submitUser = function() {
		//alert("in submitUser");
		if(!$scope.isUserExists){
			var cond = false;
			if($routeParams.dealerId != null && $routeParams.dealerId > 0){
				 cond = true;
			}
			userService.saveUser($scope.user, cond);
		}
    };
    
    $http.get("/agg/dealerAndRoleInfo")
    .then(function(response) {
        $scope.dealerList = response.data.data.dealerList;
        $scope.roleList = response.data.data.roleList;
        var dealerId = $routeParams.dealerId;
        if(dealerId != null && dealerId > 0){
        	angular.forEach($scope.dealerList, function(dealer, key){
        		if(dealerId == dealer.id){
        			$scope.user.dealerDO = dealer;
        		}
    	    });
        }
    });
    
    $scope.isUserNameExists = function(userName){
    	$http.post("/agg/isUserNameExists", userName)
        .then(function(response) {
        	//alert(response.data.data.programList);
        	$scope.isUserExists = response.data.data;
            if($scope.isUserExists){
            	//alert("User with Username '"+userName+"' already exists!");
            	$('#userErrorMsg').html("User with Username '<strong>"+userName+"</strong>' already exists!");
            	$('#userErrorMsg').removeClass('hidden');
            }else{
            	$('#userErrorMsg').addClass('hidden');
            }
        });
    }
    
   /* $scope.getLocation = function() {
    	 $http.get("/agg/locationInfo/"+$scope.user.dealerDO.id)
 	    .then(function(response) {
 	        $scope.locationList = response.data.data;
 	    });
    };*/
        
});