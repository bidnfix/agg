'use strict';

var routingApp = angular.module('aggRoutingApp',['ngRoute']);

routingApp.config(['$routeProvider',
                  function($routeProvider) {
                    $routeProvider.
                      when('/agg/addDealer', {
                    	  templateUrl: '../../jsp/addDealer.jsp',
                    	  controller: 'AddDealerController'	  
                      }).
                      when('/agg/machineInfo', {
                    	  templateUrl: '../../jsp/machineInfo.jsp',
                    	  controller: 'AddMachineController'
                      }).
                      when('/agg/addLocation', {
                    	  templateUrl: '../../jsp/addLocation.jsp',
                    	  controller: 'AddLocationController'
                      }).
                      when('/agg/addUser', {
                    	  templateUrl: '../../jsp/addUser.jsp',
                    	  controller: 'AddUserController'
                      }).
                      when('/agg/home', {
                    	  templateUrl: '../../jsp/home.jsp'
                      }).
                      otherwise({
                    	  redirectTo: '/agg/home'
                      });
                }]);

routingApp.controller('AddUserController', function($scope, $http) {
	$http.get("/agg/dealerAndRoleInfo")
    .then(function(response) {
        $scope.dealerList = response.data.data.dealerList;
        $scope.roleList = response.data.data.roleList;
    });
});

routingApp.controller('AddLocationController', function($scope, $http) {
	$http.get("/agg/dealerInfo")
    .then(function(response) {
        $scope.dealerList = response.data.data;
    });
});

routingApp.controller('AddMachineController', function($scope, $http) {
	 $http.get("/agg/machineInfo")
	    .then(function(response) {
	        $scope.manufacturerList = response.data.data.manufacturerList;
	    });
});

routingApp.controller('AddDealerController', function($scope, $http) {
	 $http.get("/agg/dealerRoleInfo")
	    .then(function(response) {
	        $scope.roleList = response.data.data;
	    });
});

routingApp.controller("activateTabCtrl", function($scope) {
    $scope.activateTab = function ($event) {
    	$('#leftTabs li a').each(function($event, $this){
    		//alert("#"+$this.id+"--"+$event.target);
    		if($this.id != null && $this.id !=""){
    			$("#"+$this.id).css({"background":"#fff", "color":"#205f9f"});
    		}
        });
    	var tabId = $event.target.id;
    	//alert(tabId);
    	$('#'+tabId).css({"background":"#205f9f", "color":"#fff"});
    }    
});