'use strict';

var routingApp = angular.module('aggRoutingApp',['ngRoute']);

routingApp.config(['$routeProvider',
                  function($routeProvider) {
                    $routeProvider.
                      when('/agg/addDealer', {
                    	  templateUrl: '../../jsp/addDealer.jsp'
                      }).
                      when('/agg/machineInfo', {
                    	  templateUrl: '../../jsp/machineInfo.jsp',
                    	  controller: 'AddMachineController'
                      }).
                      when('/agg/addLocation', {
                    	  templateUrl: '../../jsp/addLocation.jsp',
                    	  controller: 'AddLocationController'
                      }).
                      when('/agg/home', {
                    	  templateUrl: '../../jsp/home.jsp'
                      }).
                      otherwise({
                    	  redirectTo: '/agg/home'
                      });
                }]);

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
	 $scope.getMachineType = function ()
	 {
		 alert($scope.manufacturer.id);
		 $http.get("/agg/machineType/"+$scope.manufacturer.id)
		    .then(function(response) {
		        $scope.machineTypeList = response.data.data.machineTypeList;
		    });
	 }
	 $scope.getMachineModel = function ()
	 {
		 alert($scope.machineType.id);
		 $http.get("/agg/machineModel/"+$scope.machineType.id)
		    .then(function(response) {
		        $scope.machineModelList = response.data.data.machineModelList;
		    });
	 }
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