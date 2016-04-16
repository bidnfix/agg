'use strict';

routingApp.controller('programController', function($scope, programService, $location, $http) {
	$scope.program={};
	$scope.submitProgram = function() {
		alert("In submitProgram");
		programService.saveProgram($scope.program);
    };
    
    $scope.getPrograms = function ()
	 {
		 alert($scope.program.id);
		 $http.get("/agg/programs/"+$scope.machine.manufacturerDO.id)
		    .then(function(response) {
		        $scope.programsList = response.data.data.programs;
		    });
	 }
});