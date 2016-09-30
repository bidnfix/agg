'use strict';

routingApp.controller('programController', function($scope, programService, $location, $http) {
	$scope.program={};
	$scope.submitProgram = function() {
		programService.saveProgram($scope.program);
    };
    
    $scope.getPrograms = function ()
	 {
		 $http.get("/agg/programs/"+$scope.machine.manufacturerDO.id)
		    .then(function(response) {
		        $scope.programsList = response.data.data.programs;
		    });
	 }
    
    $scope.getManfModel = function ()
	 {
		 $http.get("/agg/manfModel/"+$scope.program.manufacturerDO.id)
		    .then(function(response) {
		        $scope.machineModelList = response.data.data.machineModelList;
		    });
	 }
    
});

