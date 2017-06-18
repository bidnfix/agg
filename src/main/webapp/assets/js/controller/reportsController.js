'use strict';

routingApp.controller('ReportsController', function($scope, $location, $http) {
	$http.get("/agg/report/contracts")
    .then(function(response) {
        $scope.reportData = response.data.data.reportData;
    });
});