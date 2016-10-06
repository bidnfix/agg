'use strict';

routingApp.factory('contractService', function($http, $q, $window) {
	return {
		updateContract : function(contract) {
			showSpinner();
			return $http.post('/agg/updateContract', contract).then(
				function(response) {
					if (response.data.status == 'success') {
						$window.location.href = '#/agg/contracts';
					} else {
						alert('error in updating the Contract: '+response.data.errMessage)
						//$('#errMsg').html(response.data.errMessage);
					}
					hideSpinner();
				}, function(errResponse) {
					alert('Error while updating the Contract');
					hideSpinner();
					return $q.reject(errResponse);
				});
		}
	};

});

