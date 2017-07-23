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
	
	/*$scope.editMachine = function(machineId) {
		//alert(dealerId);
		$http.get("/agg/machine/"+machineId)
	    .then(function(response) {
	    	$scope.groupList = response.data.data.groupList;
	        $scope.machine = response.data.data.machine;
	        $scope.dealer = {
	        	userName: $scope.dealerr.userName,	
	        	state: $scope.dealerr.state
	        };
	    });
		
		var x = screen.width/4;
	    var y = screen.height/9;
	    showMask('popup_mask');
	    $('#machineEditPopup').css("left", x+"px");
	    $('#machineEditPopup').css("top", y+"px");
	    $('#machineEditPopup').show();
	};
	
	$scope.editSubmitMachine = function() {
		//alert("In submitMachine");
		machineService.editMachineInfo($scope.machine, $scope);
    };*/
	
});
