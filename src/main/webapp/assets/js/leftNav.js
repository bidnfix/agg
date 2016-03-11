'use strict';

var routingApp = angular.module('aggRoutingApp',['ngRoute']);

routingApp.config(['$routeProvider',
                  function($routeProvider) {
                    $routeProvider.
                      when('/agg/addDealer', {
                    	  templateUrl: 'WEB-INF/jsp/addDealer.jsp',
                    	  controller: 'AddDealerController'
                      }).
                      otherwise({
                	redirectTo: '/agg/home'
                      });
                }]);

routingApp.controller('AddDealerController', function($scope) {
	alert(1);
});

routingApp.controller("activateTabCtrl", function($scope) {
    $scope.activateTab = function ($event) {
    	$('#leftTabs li a').each(function($event, $this){
    		//alert("#"+$this.id+"--"+$event.target);
    		if($this.id != null && $this.id !=""){
    			$("#"+$this.id).css({"background":"#fff", "color":"#205f9f"});
    		}
        });
    	var tabId = $event.target.id;
    	//alert(tabId);
    	$('#'+tabId).css({"background":"#205f9f", "color":"#fff"});
    }    
});