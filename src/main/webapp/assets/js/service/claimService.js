/**
 * htamada
 */

'use strict';

routingApp.factory('claimService', ['$http', '$q', '$window', '$timeout', function($http, $q, $window, $timeout){
	var selectQuote = function($scope, data){
			$scope.quoteInfoList = data;
			$scope.showActiveQuoteDetails = false;
			$scope.showContractDetails = true;
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