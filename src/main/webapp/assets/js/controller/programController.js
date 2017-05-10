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
    
    $scope.getModelByCode = function ()
	 {
    	var code = $scope.program.code;
    	
    	code = code.replace(/\s/g, "");
    	alert(code);
    	
    	if(code != null && /^[\d,]+$/.test(code))
    	{
		 $http.get("/agg/modelByCode/"+code)
		    .then(function(response) {
		    	if(response.data.data == null)
	    		{
					$('#programErrorMsg').html("No Model found or Model IDs does not belong to same Manufacturer");
	            	$('#programErrorMsg').removeClass('hidden');
	            	window.setTimeout(function() {
	        			 $('#programErrorMsg').addClass('hidden');
	        		}, 3000);
	    			//alert("No Model found or Model IDs does not belong to same Manufacturer");
	    		}
		    	
		    	 //alert(response.data.data.machineModelList[0].manufacturerDO.name);
		    	
		    	 $scope.program.manufacturerDO=response.data.data.machineModelList[0].manufacturerDO;
		         //$scope.machineModelList = response.data.data.machineModelList;
		    	 $scope.program.machineModelList = response.data.data.machineModelList;
		         
		         
		         /*angular.forEach(response.data.data.machineModelList, function(val){
		            $scope.program.machineInfoDOList.push(val);
		        });*/
		        
		       
		    });
    	}
    	else
    		{
	    		$('#programErrorMsg').html("Please enter only comma seperated numeric value");
	        	$('#programErrorMsg').removeClass('hidden');
	        	window.setTimeout(function() {
	    			 $('#programErrorMsg').addClass('hidden');
	    		}, 3000);
    			//alert("Please enter only comma seperated numeric value");
    		}
	 }
    
});

