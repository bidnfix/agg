'use strict';

routingApp.controller('machineController', function($scope, machineService, $location, $http) {
	$scope.machine={};
	$scope.submitMachine = function() {
		//alert("In submitMachine");
		machineService.saveMachineInfo($scope.machine);
    }
    
    $scope.editSubmitMachine = function() {
		//alert("In submitMachine");
		machineService.editMachineInfo($scope.machine);
    }
    
    $scope.getMachineType = function ()
	 {
		 //alert($scope.machine.manufacturerDO.id);
		 $http.get("/agg/machineType/"+$scope.machine.manufacturerDO.id)
		    .then(function(response) {
		        $scope.machineTypeList = response.data.data.machineTypeList;
		    });
	 }

})
.directive('tooltip', function () {
    return {
        restrict:'A',
        link: function(scope, element, attrs)
        {
            $(element)
                .attr('title',scope.$eval(attrs.tooltip))
                .tooltip({placement: "right"});
        }
    }
});