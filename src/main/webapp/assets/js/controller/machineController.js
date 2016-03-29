'use strict';

routingApp.controller('machineController', function($scope, machineService, $location) {
	$scope.machine={};
	$scope.submitMachine = function() {
		alert("in submitMachine");
		machineService.saveMachineInfo($scope.machine);
    };
    
    $scope.getMachineType = function ()
	 {
		 alert($scope.manufacturerDO.id);
		 $http.get("/agg/machineType/"+$scope.manufacturerDO.id)
		    .then(function(response) {
		        $scope.machineTypeList = response.data.data.machineTypeList;
		    });
	 }
	 $scope.getMachineModel = function ()
	 {
		 alert($scope.machineTypeDO.id);
		 $http.get("/agg/machineModel/"+$scope.machineTypeDO.id)
		    .then(function(response) {
		        $scope.machineModelList = response.data.data.machineModelList;
		    });
	 }
        
});