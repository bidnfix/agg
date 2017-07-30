'use strict';

routingApp.controller('usageTierController', function($scope, $http, $timeout, usageTierService) {
	$http.get("/agg/getUsageTiers")
    .then(function(response) {
        $scope.usageTierLst = response.data.data.usageTierLst;
        //alert($scope.usageTierLst);
        $timeout(function () {
        	$('#table1').DataTable({"lengthMenu": [[10, 25, 50, 100, -1], [10, 25, 50, 100, "All"]]});
        }, 300);
    });
	
	$scope.editUseOfEquip = function(id) {
		//alert(dealerId);
		$http.get("/agg/useOfEquip/"+id)
	    .then(function(response) {
	        $scope.useOfEquip = response.data.data.useOfEquip;
	    });
		
		var x = screen.width/4;
	    var y = screen.height/9;
	    showMask('popup_mask');
	    $('#UOEEditPopup').css("left", x+"px");
	    $('#UOEEditPopup').css("top", y+"px");
	    $('#UOEEditPopup').show();
	};
	
	$scope.submitEditEquipment = function() {
		alert("In editEquipment");
		usageTierService.editEquipment($scope.useOfEquip);
    }
	
	$scope.submitUsageTier = function() {
		alert("In submitUsageTier"+$scope.usageTierDO);
		usageTierService.saveUsageTier($scope.usageTierDO);
    }
	
});

routingApp.controller('editUsageTierController', function($scope, usageTierService, $location, $http) {
	$http.get("/agg/dealer/"+dealerId)
    .then(function(response) {
    	$scope.roleList = response.data.data.roleList;

    });
	
	
	$scope.submitEquipment = function() {
		alert("In submitEquipment");
		usageTierService.saveEquipment($scope.useOfEquipmentDO);
    }
    
});
