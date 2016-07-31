'use strict';

routingApp.factory('quoteService', function($http, $q, $window) {
	return {
		saveWarrantyInfo : function(quote, $scope) {
			return $http.post('/agg/quote/addQuote/warrantyInfo', quote).then(
					function(response) {
						alert(response.data.status);
						if (response.data.status == 'success') {
							$window.location.href = '#/agg/dealers';
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
							$window.location.href = '#/agg/dealers';
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
							$window.location.href = '#/agg/dealers';
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

