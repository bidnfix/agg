'use strict';

var routingApp = angular.module('aggRoutingApp',['ngRoute']);

routingApp.config(['$routeProvider',
                  function($routeProvider) {
                    $routeProvider.
                      when('/agg/dealers', {
	                  	  templateUrl: '../../jsp/dealers.jsp',
	                  	  controller: 'GetDealerController'	  
	                  }).
                      when('/agg/addDealer', {
                    	  templateUrl: '../../jsp/addDealer.jsp',
                    	  controller: 'AddDealerController'	  
                      }).
                      when('/agg/machineInfo', {
                    	  templateUrl: '../../jsp/machineInfo.jsp',
                    	  controller: 'GetMachineInfoController'
                      }).
                      when('/agg/addMachine', {
                    	  templateUrl: '../../jsp/addMachine.jsp',
                    	  controller: 'AddMachineController'
                      }).
                      when('/agg/editMachine', {
                    	  templateUrl: '../../jsp/editMachine.jsp',
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

routingApp.controller('GetDealerController', function($scope, $http, $timeout) {
	$http.get("/agg/dealerInfo")
    .then(function(response) {
        $scope.dealerList = response.data.data;
        $timeout(function () {
        	$('#table1').DataTable();
        }, 300);
    });
	
	$scope.editDealer = function(dealerId) {
		//alert(dealerId);
		$http.get("/agg/dealer/"+dealerId)
	    .then(function(response) {
	        $scope.dealer = response.data.data;
	       /* $scope.dealer = {
	        	userName: $scope.dealerr.userName,	
	        	state: $scope.dealerr.state
	        };*/
	    });
		
		var x = screen.width/4;
	    var y = screen.height/9;
	    showMask('popup_mask');
	    $('#dealerEditPopup').css("left", x+"px");
	    $('#dealerEditPopup').css("top", y+"px");
	    $('#dealerEditPopup').show();
    };
});



routingApp.controller('GetMachineInfoController', function($scope, $http, $timeout) {
	$http.get("/agg/machineInfo")
    .then(function(response) {
        $scope.machineInfoList = response.data.data.machineInfoList;
        $timeout(function () {
        	$('#table1').DataTable();
        }, 300);
    });
	
	$scope.editMachine = function(machineId) {
		//alert(dealerId);
		$http.get("/agg/machine/"+machineId)
	    .then(function(response) {
	        $scope.machine = response.data.data;
	       /* $scope.dealer = {
	        	userName: $scope.dealerr.userName,	
	        	state: $scope.dealerr.state
	        };*/
	    });
		
		var x = screen.width/4;
	    var y = screen.height/9;
	    showMask('popup_mask');
	    $('#machineEditPopup').css("left", x+"px");
	    $('#machineEditPopup').css("top", y+"px");
	    $('#machineEditPopup').show();
	};
	
});

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
    		if($this.id != null && $this.id !=""){
    			$("#"+$this.id).css({"background":"#fff", "color":"#205f9f"});
    		}
        });
    	var tabId = $event.target.id;
    	$('#'+tabId).css({"background":"#205f9f", "color":"#fff"});
    }    
});