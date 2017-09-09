'use strict';

routingApp.controller('oemController', function($scope, $http, $timeout, oemService) {
	$http.get("/agg/getOEMWarrantyTiers")
    .then(function(response) {
        $scope.oemWarrantyTierLst = response.data.data.oemWarrantyTierLst;
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
	
	$scope.submitEditOEM = function() {
		//alert("In editEquipment");
		oemService.editOEM($scope.oem);
    }
	
	$scope.submitOEM = function() {
		//alert("In submitUsageTier"+$scope.usageTierDO);
		oemService.saveOEM($scope.oemDO);
    }
	
});

routingApp.controller('editOEMController', function($scope, $http, $timeout, $routeParams, $route, oemService) {
	//alert($routeParams.id);
	
	$http.get("/agg/getOEMWarrantyTier/"+$routeParams.id)
    .then(function(response) {
    	$scope.oemDO = response.data.data.oemWarrantyTierDO;

    });
	
	$scope.submitEditOEM = function() {
		//alert("In submitEditUsageTier"+$scope.usageTierDO.usageFrom);
		oemService.editOEM($scope.oemDO);
    }
	
	$scope.deleteOEM = function() {
		//alert($scope.oemDO.id);
		oemService.deleteOEM($scope.oemDO.id);
    }

   });
