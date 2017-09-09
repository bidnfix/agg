'use strict';


routingApp.controller('useOfEquipController', function($scope, $http, $timeout, useOfEquipService) {
	$http.get("/agg/useOfEquipInfo")
    .then(function(response) {
        $scope.useOfEquipLst = response.data.data.useOfEquipLst;
        //alert($scope.useOfEquipLst);
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
		//alert("In editEquipment");
    	useOfEquipService.editEquipment($scope.useOfEquip);
    }
	
	
});

routingApp.controller('equipmentController', function($scope, useOfEquipService, $location, $http) {
	$scope.useOfEquipmentDO={};
	$scope.submitEquipment = function() {
		//alert("In submitEquipment");
		useOfEquipService.saveEquipment($scope.useOfEquipmentDO);
    }
    
    /*$scope.submitEditEquipment = function() {
		alert("In editEquipment");
    	useOfEquipService.editEquipment($scope.equipment);
    }*/
    
});
