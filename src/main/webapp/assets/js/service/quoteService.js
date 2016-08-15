'use strict';

routingApp.factory('quoteService', function($http, $q, $window) {
	return {
		saveWarrantyInfo : function(quote, $scope) {
			return $http.post('/agg/quote/addQuote/warrantyInfo', quote).then(
					function(response) {
						alert(response.data.status);
						if (response.data.status == 'success') {
							alert(response.data.data.quoteId+" "+response.data.data.id);
							$scope.quote.quoteId = response.data.data.quoteId;
							$scope.quote.id = response.data.data.id;
							$scope.quote.statusDesc = response.data.data.statusDesc;
							/*$scope.quote = response.data.data;
							if($scope.quote.horsePower == 0){
								$scope.quote.horsePower = "";
							}
							if($scope.quote.retailPrice == 0){
								$scope.quote.retailPrice = "";
							}
							if($scope.quote.meterHours == 0){
								$scope.quote.meterHours = "";
							}
							if($scope.quote.modelYear == 0){
								$scope.quote.modelYear = "";
							}
							if($scope.quote.dealerMarkup == 0){
								$scope.quote.dealerMarkup = "";
							}*/
							
							//$window.location.href = '#/agg/dealers';
						} else {
							alert('error in adding Quote Warranty Info: '+response.data.errMessage)
							//$('#errMsg').html(response.data.errMessage);
						}
						
					}, function(errResponse) {
						alert('Error while creating dealer');
						return $q.reject(errResponse);
					});
		},
		saveMachineInfo : function(quote, $scope) {
			return $http.post('/agg/quote/addQuote/machineInfo', quote).then(
					function(response) {
						alert(response.data.status);
						if (response.data.status == 'success') {
							alert(response.data.data.quoteId+" "+response.data.data.id);
							$scope.quote.quoteId = response.data.data.quoteId;
							$scope.quote.id = response.data.data.id;
							$scope.quote.statusDesc = response.data.data.statusDesc;
							/*$scope.quote = response.data.data;
							if($scope.quote.horsePower == 0){
								$scope.quote.horsePower = "";
							}
							if($scope.quote.retailPrice == 0){
								$scope.quote.retailPrice = "";
							}
							if($scope.quote.meterHours == 0){
								$scope.quote.meterHours = "";
							}
							if($scope.quote.modelYear == 0){
								$scope.quote.modelYear = "";
							}
							if($scope.quote.dealerMarkup == 0){
								$scope.quote.dealerMarkup = "";
							}*/
							
							
							//$window.location.href = '#/agg/dealers';
						} else {
							alert('error in adding Quote Machine Info: '+response.data.errMessage)
							//$('#errMsg').html(response.data.errMessage);
						}
						
					}, function(errResponse) {
						alert('Error while editing dealer');
						return $q.reject(errResponse);
					});
		},
		saveCoverageInfo : function(quote, $scope) {
			return $http.post('/agg/quote/addQuote/coverageInfo', quote).then(
					function(response) {
						alert(response.data.status);
						if (response.data.status == 'success') {
							alert(response.data.data.quoteId+" "+response.data.data.id);
							//$scope.quote = response.data.data;
							$scope.quote.quoteId = response.data.data.quoteId;
							$scope.quote.id = response.data.data.id;
							$scope.quote.statusDesc = response.data.data.statusDesc;
							//$window.location.href = '#/agg/dealers';
						} else {
							alert('error in adding Quote Coverage Info: '+response.data.errMessage)
							//$('#errMsg').html(response.data.errMessage);
						}
						
					}, function(errResponse) {
						alert('Error while editing dealer');
						return $q.reject(errResponse);
					});
		},
		savePurchanseInfo : function(quote, $scope) {
			return $http.post('/agg/quote/addQuote/purchaseInfo', quote).then(
					function(response) {
						alert(response.data.status);
						if (response.data.status == 'success') {
							alert(response.data.data.quoteId+" "+response.data.data.id);
							//$scope.quote = response.data.data;
							$scope.quote.quoteId = response.data.data.quoteId;
							$scope.quote.id = response.data.data.id;
							$scope.quote.statusDesc = response.data.data.statusDesc;
							//$window.location.href = '#/agg/dealers';
						} else {
							alert('error in adding Quote Coverage Info: '+response.data.errMessage)
							//$('#errMsg').html(response.data.errMessage);
						}
						
					}, function(errResponse) {
						alert('Error while editing dealer');
						return $q.reject(errResponse);
					});
		}
	};

});

