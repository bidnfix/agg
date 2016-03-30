'use strict';

routingApp.controller('machineController', function($scope, machineService, $location, $http) {
	$scope.machine={};
	$scope.submitMachine = function() {
		alert("in submitMachine");
		machineService.saveMachineInfo($scope.machine);
    };
    
    $scope.getMachineType = function ()
	 {
		 alert($scope.machine.manufacturerDO.id);
		 $http.get("/agg/machineType/"+$scope.machine.manufacturerDO.id)
		    .then(function(response) {
		        $scope.machineTypeList = response.data.data.machineTypeList;
		    });
	 }
     
    $scope.getMachineModel = function ()
	 {
		 alert($scope.machine.machineTypeDO.id);
		 $http.get("/agg/machineModel/"+$scope.machine.machineTypeDO.id)
		    .then(function(response) {
		        $scope.machineModelList = response.data.data.machineModelList;
		    });
	 }
});