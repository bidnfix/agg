'use strict';

routingApp.factory('quoteService', function($http, $q, $window) {
	return {
		saveWarrantyInfo : function(quote, $scope) {
			return $http.post('/agg/quote/addQuote/warrantyInfo', quote).then(
					function(response) {
						//alert(response.data.status);
						if (response.data.status == 'success') {
							//alert(response.data.data.quoteId+" "+response.data.data.id);
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
							alert('Error in adding Quote Warranty Info: '+response.data.errMessage)
							//$('#errMsg').html(response.data.errMessage);
						}
						
					}, function(errResponse) {
						alert('Error while saving Quote');
						return $q.reject(errResponse);
					});
		},
		saveMachineInfo : function(quote, $scope) {
			return $http.post('/agg/quote/addQuote/machineInfo', quote).then(
					function(response) {
						//alert(response.data.status);
						if (response.data.status == 'success') {
							//alert(response.data.data.quoteId+" "+response.data.data.id);
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
							alert('Error in adding Quote Machine Info: '+response.data.errMessage)
							//$('#errMsg').html(response.data.errMessage);
						}
						
					}, function(errResponse) {
						alert('Error while saving Quote');
						return $q.reject(errResponse);
					});
		},
		saveCoverageInfo : function(quote, $scope) {
			return $http.post('/agg/quote/addQuote/coverageInfo', quote).then(
					function(response) {
						//alert(response.data.status);
						if (response.data.status == 'success') {
							//alert(response.data.data.quoteId+" "+response.data.data.id);
							//$scope.quote = response.data.data;
							$scope.quote.quoteId = response.data.data.quoteId;
							$scope.quote.id = response.data.data.id;
							$scope.quote.statusDesc = response.data.data.statusDesc;
							//$window.location.href = '#/agg/dealers';
						} else {
							alert('Error in adding Quote Coverage Info: '+response.data.errMessage)
							//$('#errMsg').html(response.data.errMessage);
						}
						
					}, function(errResponse) {
						alert('Error while saving Quote');
						return $q.reject(errResponse);
					});
		},
		savePurchanseInfo : function(quote, $scope) {
			showSpinner();
			return $http.post('/agg/quote/addQuote/purchaseInfo', quote).then(
					function(response) {
						//alert(response.data.status);
						if (response.data.status == 'success') {
							//alert(response.data.data.quoteId+" "+response.data.data.id);
							//$scope.quote = response.data.data;
							$scope.quote.quoteId = response.data.data.quoteId;
							$scope.quote.id = response.data.data.id;
							$scope.quote.statusDesc = response.data.data.statusDesc;
							$window.location.href = '#/agg/quotes';
						} else {
							alert('Error in adding Quote Coverage Info: '+response.data.errMessage)
							//$('#errMsg').html(response.data.errMessage);
						}
						hideSpinner();
						
					}, function(errResponse) {
						alert('Error while saving Quote');
						return $q.reject(errResponse);
					});
		},
		archiveQuote : function(quote, $scope) {
			showSpinner();
			return $http.post('/agg/quote/archiveQuote', quote).then(
					function(response) {
						//alert(response.data.status);
						if (response.data.status == 'success') {
							$window.location.href = '#/agg/quotes';
						} else {
							alert('error in archiving Quote: '+response.data.errMessage)
							//$('#errMsg').html(response.data.errMessage);
						}
						hideSpinner();
						
					}, function(errResponse) {
						alert('Error while archiving Quote');
						return $q.reject(errResponse);
					});
		},
		updateQuote : function(quote, $scope) {
			showSpinner();
			return $http.post('/agg/quote/updateQuote', quote).then(
					function(response) {
						//alert(response.data.status);
						if (response.data.status == 'success') {
							$window.location.href = '#/agg/quotes';
						} else {
							alert('error in updating Quote: '+response.data.errMessage)
							//$('#errMsg').html(response.data.errMessage);
						}
						hideSpinner();
						
					}, function(errResponse) {
						alert('Error while updating Quote');
						return $q.reject(errResponse);
					});
		},
		invoiceQuote : function(quote, $scope) {
			showSpinner();
			return $http.post('/agg/quote/invoiceQuote', quote).then(
					function(response) {
						//alert(response.data.status);
						if (response.data.status == 'success') {
							$window.location.href = '#/agg/quotes';
						} else {
							alert('error in invoicing Quote: '+response.data.errMessage)
							//$('#errMsg').html(response.data.errMessage);
						}
						hideSpinner();
						
					}, function(errResponse) {
						alert('Error while invoicing Quote');
						return $q.reject(errResponse);
					});
		},
		createContract : function(quote, $scope) {
			showSpinner();
			return $http.post('/agg/quote/createContract', quote).then(
					function(response) {
						//alert(response.data.status);
						if (response.data.status == 'success') {
							closePopup('contractCreatePopup');
							$window.location.href = '#/agg/contracts';
						} else {
							alert('error in creating Contract: '+response.data.errMessage)
							//$('#errMsg').html(response.data.errMessage);
						}
						hideSpinner();
						
					}, function(errResponse) {
						alert('Error while creating Contract');
						return $q.reject(errResponse);
					});
		}
	};

});

