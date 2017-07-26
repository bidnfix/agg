'use strict';


routingApp.controller('UseOfEquipController', function($scope, $http, $timeout) {
	$http.get("/agg/useOfEquipInfo")
    .then(function(response) {
        $scope.useOfEquipLst = response.data.data.useOfEquipLst;
        //alert($scope.useOfEquipLst);
        $timeout(function () {
        	$('#table1').DataTable({"lengthMenu": [[10, 25, 50, 100, -1], [10, 25, 50, 100, "All"]]});
        }, 300);
    });
	
});

routingApp.controller('equipmentController', function($scope, useOfEquipService, $location, $http) {
	$scope.useOfEquipmentDO={};
	$scope.submitEquipment = function() {
		alert("In submitEquipment");
		useOfEquipService.saveEquipment($scope.useOfEquipmentDO);
    }
    
    $scope.submitEditEquipment = function() {
		alert("In editEquipment");
    	useOfEquipService.editEquipment($scope.equipment);
    }
    
    /*$scope.getMachineType = function ()
	 {
		 //alert($scope.machine.manufacturerDO.id);
		 $http.get("/agg/machineType/"+$scope.machine.manufacturerDO.id)
		    .then(function(response) {
		        $scope.machineTypeList = response.data.data.machineTypeList;
		    });
	 }*/

});
