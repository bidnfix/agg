'use strict';

routingApp.controller('manufacturerController', function($scope, $http, $timeout, manufacturerService) {
	$http.get("/agg/getManufacturers")
    .then(function(response) {
        $scope.manfLst = response.data.data.manfLst;
        //alert($scope.usageTierLst);
        $timeout(function () {
        	$('#table1').DataTable({"lengthMenu": [[10, 25, 50, 100, -1], [10, 25, 50, 100, "All"]]});
        }, 300);
    });
	
	/*$scope.editUseOfEquip = function(id) {
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
	};*/
	
	$scope.submitEditManufacturer = function() {
		//alert("In editEquipment");
		oemService.editOEM($scope.manf);
    }
	
	$scope.submitManufacturer = function() {
		//alert("In submitUsageTier"+$scope.usageTierDO);
		manufacturerService.saveManufacturer($scope.manfDO);
    }
	
});

routingApp.controller('editManufacturerController', function($scope, $http, $timeout, $routeParams, $route, oemService) {
	//alert($routeParams.id);
	
	$http.get("/agg/getOEMWarrantyTier/"+$routeParams.id)
    .then(function(response) {
    	$scope.oemDO = response.data.data.oemWarrantyTierDO;

    });
	
	$scope.submitEditOEM = function() {
		//alert("In submitEditUsageTier"+$scope.usageTierDO.usageFrom);
		oemService.editOEM($scope.oemDO);
    }

   });
