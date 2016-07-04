/**
 * htamada
 */

'use strict';

routingApp.factory('claimService', ['$http', '$q', '$window', '$timeout', function($http, $q, $window, $timeout){
	var selectQuote = function($scope, data){
			$scope.quoteInfoList = data;
			$scope.showActiveQuoteDetails = false;
			$scope.showContractDetails = true;
			initClaimAddForm($scope);
		},
		initClaimAddForm = function($scope){
			$scope.claim={};
			var initDatepicker = function(){
				var getMaxClaimFailureDate = function(){
					return $scope.claim.reportedDate - 1;
				};
				$scope.claim.reportedDate = new Date();
				$scope.dateOptions1 = {
				        maxDate: getMaxClaimFailureDate(),
				        startingDay: 1
				    };
				$scope.dateOptions2 = {
			        maxDate: new Date(),
			        startingDay: 1
			    };
				$scope.format = 'dd.MM.yyyy';
				$scope.fdtopen = function() {
			    	$scope.popup1.opened = true;
			    };
			      
				$scope.popup1 = {
					opened: false
				};
				$scope.rdtopen = function() {
			    	$scope.popup2.opened = true;
			    };
			      
				$scope.popup2 = {
					opened: false
				};
			};
			initDatepicker();
			
			$scope.claim.claimNo = 'CL555';
			$scope.$watch('claim.laborHours * claim.hourlyRate', function(value){
				$scope.claim.totalLaborCost = value;
			});
			$scope.$watchCollection('[claim.totalLaborCost, claim.totalPartsCost, claim.totalOtherCharges1, claim.totalOtherCharges2]', function(newValues){
				$scope.claim.totalClaimCost = parseInt(newValues[0]) + parseInt(newValues[1]) + parseInt(newValues[2]) + parseInt(newValues[3]);
			});
		};
		
	return {
		getSerialNumberInfo : function($scope){
			$scope.showContractDetails = false;
			$http.get("/agg/searchClaim/" + $scope.serialNo)
    		.then(function(response) {
    			if(response.data.status === 'success'){
    				if(response.data.data.quoteInfoList.length == 1){
    					selectQuote($scope, response.data.data.quoteInfoList[0]);
    				}else {
    					$scope.showContractDetails = false;
    					if(response.data.data.quoteInfoList.length > 1){
    						$scope.showActiveQuoteDetails = true;
    						$scope.quoteInfoList = response.data.data.quoteInfoList;
    						 $timeout(function () {
    					        	$('#quotesTable').DataTable();
    						 }, 500);
    					}else{
    						console.log('no records found');
    					}
    				}
    			}else{
    				$scope.showContractDetails = false;
    			}
    		});
		},
		selectQuote : selectQuote
	}
}]);