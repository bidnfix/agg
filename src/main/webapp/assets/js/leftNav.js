'use strict';

var routingApp = angular.module('aggRoutingApp',['ngRoute']);

routingApp.config(['$routeProvider',
                  function($routeProvider) {
                    $routeProvider.
                      when('/agg/dealers', {
	                  	  templateUrl: '../../jsp/dealers.jsp',
	                  	  controller: 'GetDealerController',
                  		  resolve: {
                  	         ctrlOptions: function () {
                  	            return {
                  	              getAllDealers: true,
                  	            };
                  	         }
                  	      }
	                  }).
                      when('/agg/addDealer', {
                    	  templateUrl: '../../jsp/addDealer.jsp',
                    	  controller: 'AddDealerController'	  
                      }).
                      when('/agg/machineInfo', {
                    	  templateUrl: '../../jsp/machineInfo.jsp',
                    	  controller: 'GetMachineInfoController'
                      }).
                      when('/agg/addMachine', {
                    	  templateUrl: '../../jsp/addMachine.jsp',
                    	  controller: 'AddMachineController'
                      }).
                      when('/agg/editMachine', {
                    	  templateUrl: '../../jsp/editMachine.jsp',
                    	  controller: 'AddMachineController'
                      }).
                      when('/agg/addUser', {
                    	  templateUrl: '../../jsp/addUser.jsp',
                    	  controller: 'AddUserController'
                      }).
                      when('/agg/home', {
                    	  templateUrl: '../../jsp/home.jsp',
                    	  controller: 'HomeController'
                      }).
                      when('/agg/programs', {
                    	  templateUrl: '../../jsp/programs.jsp',
                    	  controller: 'GetProgramsController'
                    	
                      }).
                      when('/agg/addPrograms', {
                    	  templateUrl: '../../jsp/addPrograms.jsp',
                    	  controller: 'AddProgramsController'
                      }).
                      when('/agg/claimsInfo', {
                    	  templateUrl: '../../jsp/claimsInfo.jsp',
                    	  controller: 'ClaimsController'
                      }).
                      when('/agg/editClaim', {
                    	  templateUrl: '../../jsp/fileaClaim.jsp',
                    	  controller: 'ClaimsController'
                      }).
                      when('/agg/addQuote', {
                    	  templateUrl: '../../jsp/addQuote.jsp',
                    	  controller: 'QuoteController'
                      }).
                      when('/agg/pendingDealers', {
                    	  templateUrl: '../../jsp/pendingDealers.jsp',
                    	  controller: 'GetDealerController',
                    	  resolve: {
                   	         ctrlOptions: function () {
                   	            return {
                   	              getAllDealers: false,
                   	            };
                   	         }
                   	      }
                      }).
                      when('/agg/users', {
                    	  templateUrl: '../../jsp/users.jsp',
                    	  controller: 'GetUserController'
                      }).
                      otherwise({
                    	  redirectTo: '/agg/home'
                      });
                }]);

routingApp.controller('GetDealerController', function($scope, dealerService, $http, $timeout, ctrlOptions) {
	$scope.dealer={};
	if(ctrlOptions.getAllDealers){
		$http.get("/agg/dealerInfo")
		.then(function(response) {
	        $scope.dealerList = response.data.data;
	        $timeout(function () {
	        	$('#dealerTbl').DataTable();
	        }, 300);
	    });
	}else{
		$http.get("/agg/pendingDealerInfo")
		.then(function(response) {
	        $scope.dealerList = response.data.data;
	        $timeout(function () {
	        	$('#dealerTbl').DataTable();
	        }, 300);
	    });
	}
	
	
	$scope.editDealer = function(dealerId) {
		//alert(dealerId);
		$http.get("/agg/dealer/"+dealerId)
	    .then(function(response) {
	    	$scope.roleList = response.data.data.roleList;
	        $scope.dealer = response.data.data.dealer;
	        $scope.parentDealerList = response.data.data.parentDealers;
	        //$scope.dealer.roleDO = {"id":5,"name":"Dealer Admin","accountTypeId":2};
	       /* $scope.dealer = {
	        	userName: $scope.dealerr.userName,	
	        	state: $scope.dealerr.state
	        };*/
	    });
		
		var x = screen.width/4;
	    var y = screen.height/9;
	    showMask('popup_mask');
	    $('#dealerEditPopup').css("left", x+"px");
	    $('#dealerEditPopup').css("top", y+"px");
	    $('#dealerEditPopup').show();
    };
    
    $scope.submitEditDealer = function(){
    	alert("in submitEditDealer");
    	dealerService.editDealer($scope.dealer, $scope, ctrlOptions.getAllDealers);
    }
})
.directive('convertToNumber', function() {
    return {
      require: 'ngModel',
      link: function(scope, element, attrs, ngModel) {
        ngModel.$parsers.push(function(val) {
          return parseInt(val, 10);
        });
        ngModel.$formatters.push(function(val) {
          return '' + val;
        });
      }
    };
 });

routingApp.controller('GetMachineInfoController', function($scope, machineService, $http, $timeout) {
	$http.get("/agg/machineInfo")
    .then(function(response) {
        $scope.machineInfoList = response.data.data.machineInfoList;
        $timeout(function () {
        	$('#table1').DataTable();
        }, 300);
    });
	
	$scope.editMachine = function(machineId) {
		//alert(dealerId);
		$http.get("/agg/machine/"+machineId)
	    .then(function(response) {
	    	$scope.groupList = response.data.data.groupList;
	        $scope.machine = response.data.data.machine;
	       /* $scope.dealer = {
	        	userName: $scope.dealerr.userName,	
	        	state: $scope.dealerr.state
	        };*/
	    });
		
		var x = screen.width/4;
	    var y = screen.height/9;
	    showMask('popup_mask');
	    $('#machineEditPopup').css("left", x+"px");
	    $('#machineEditPopup').css("top", y+"px");
	    $('#machineEditPopup').show();
	};
	
	$scope.editSubmitMachine = function() {
		//alert("In submitMachine");
		machineService.editMachineInfo($scope.machine, $scope);
    };
	
});

routingApp.controller('AddUserController', function($scope, $http) {
	$http.get("/agg/dealerAndRoleInfo")
    .then(function(response) {
        $scope.dealerList = response.data.data.dealerList;
        $scope.roleList = response.data.data.roleList;
    });
});

routingApp.controller('HomeController', function($scope, $http) {
	$http.get("/agg/dealerCountDetails")
    .then(function(response) {
        $scope.activeDealers = response.data.data.activeDealers;
        $scope.pendingDealers = response.data.data.pendingDealers;
        $scope.terminatedDealers = response.data.data.terminatedDealers;
    });
});


/*routingApp.controller('AddLocationController', function($scope, $http) {
	$http.get("/agg/dealerInfo")
    .then(function(response) {
        $scope.dealerList = response.data.data;
    });
});*/

routingApp.controller('AddMachineController', function($scope, $http) {
	 $http.get("/agg/addMachine")
	    .then(function(response) {
	        $scope.manufacturerList = response.data.data.manufacturerList;
	        $scope.machineTypeList = response.data.data.machineTypeList;
	        $scope.groupList = response.data.data.groupList;
	    });
});

routingApp.controller('AddDealerController', function($scope, $http) {
	 $http.get("/agg/dealerRoleInfo")
	    .then(function(response) {
	        $scope.roleList = response.data.data;
	    });
});

routingApp.controller('AddDealerController', function($scope, $http) {
	 $http.get("/agg/dealerRoleInfo")
	    .then(function(response) {
	        $scope.roleList = response.data.data;
	    });
});

routingApp.controller('GetProgramsController', function($scope, $http, $timeout, $window) {
	$http.get("/agg/programs")
    .then(function(response) {
        $scope.programsList = response.data.data.programs;
        $timeout(function () {
        	$('#table1').DataTable();
        }, 300);
    });
	
	$scope.editProgram = function(programId) {
		alert(programId);
		$http.get("/agg/programs/"+programId)
	    .then(function(response) {
	        $scope.program = response.data.data.program;
	        alert($scope.program.id);
	       /* $scope.dealer = {
	        	userName: $scope.dealerr.userName,	
	        	state: $scope.dealerr.state
	        };*/
	    });
		
		var x = screen.width/4;
	    var y = screen.height/9;
	    showMask('popup_mask');
	    $('#programEditPopup').css("left", x+"px");
	    $('#programEditPopup').css("top", y+"px");
	    $('#programEditPopup').show();
    };
    
	$scope.deleteProgram = function(programId) {
		//alert(dealerId);
		$http.delete("/agg/programs/"+programId)
	    .then(function(response) {
	        if (response.data.status == 'success') {
	        	$window.location.reload();
			}
	    });
    };
    

    
});

routingApp.controller('AddProgramsController', function($scope, $http) {
	$http.get("/agg/addPrograms")
    .then(function(response) {
        $scope.manufacturerList = response.data.data.manufacturerList;
        $scope.dealerList = response.data.data.dealerList;
    });
	
    
   
	
});

routingApp.controller('AddDealerController', function($scope, $http) {
	 $http.get("/agg/dealerRoleInfo")
	    .then(function(response) {
	        $scope.roleList = response.data.data;
	    });
});

routingApp.controller("activateTabCtrl", function($scope, $timeout) {
	
	$timeout(function () {
		 jQuery('.mainNav').navAccordion({
				expandButtonText: '<i class="fa fa-plus"></i>',  //Text inside of buttons can be HTML
				collapseButtonText: '<i class="fa fa-minus"></i>'
			});
     }, 200);
	
    $scope.activateTab = function ($event) {
    	$('#leftTabs li a').each(function($event, $this){
    		if($this.id != null && $this.id !=""){
    			$("#"+$this.id).css({"background":"#fff", "color":"#205f9f"});
    		}
        });
    	var tabId = $event.target.id;
    	$('#'+tabId).css({"background":"#205f9f", "color":"#fff"});
    }    
});

routingApp.controller('ClaimsController', function($scope, machineService, $http, $timeout) {
	$http.get("/agg/claimsInfo")
    .then(function(response) {
    	$scope.quoteDOList = response.data.data.quoteInfoList;
    	//alert(response.data.data.quoteInfoList)
        $timeout(function () {
        	$('#table1').DataTable();
        }, 500);
    });
	$scope.editClaim = function(claimId) {
		alert(claimId);
		$http.get("/agg/editClaim")
		.then(function(response) {
			$scope.quoteDO = response.data.data.quoteDO;
			alert($scope.quoteDO);
		});
    };
});
routingApp.controller('QuoteController', function($scope, $http) {
	$http.get("/agg/quoteInfo")
	.then(function(response) {
		$scope.dealerList = response.data.data.dealerDOList;
		$scope.manufacturerList = response.data.data.manufacturerDOList;
	});  

	var myTabs = tabs({
	    el: '#quoteTabs',
	    tabNavigationLinks: '.c-tabs-nav__link',
	    tabContentContainers: '.c-tab'
	  });
  
	myTabs.init();
});

routingApp.controller('GetUserController', function($scope, userService, $http, $timeout) {
	$scope.user={};
	$http.get("/agg/userInfo")
	.then(function(response) {
        $scope.userList = response.data.data;
        $timeout(function () {
        	$('#userTbl').DataTable();
        }, 300);
    });
	
	$scope.editUser = function(userId) {
		$http.get("/agg/user/"+userId)
	    .then(function(response) {
	    	$scope.roleList = response.data.data.roleList;
	        $scope.user = response.data.data.user;
	    });
		
		var x = screen.width/4;
	    var y = screen.height/9;
	    showMask('popup_mask');
	    $('#userEditPopup').css("left", x+"px");
	    $('#userEditPopup').css("top", y+"px");
	    $('#userEditPopup').show();
    };
    
    $scope.submitEditUser = function(){
    	alert("in submitEditUser");
    	userService.editUser($scope.user, $scope);
    }
})
.directive('convertToNumber', function() {
    return {
      require: 'ngModel',
      link: function(scope, element, attrs, ngModel) {
        ngModel.$parsers.push(function(val) {
          return parseInt(val, 10);
        });
        ngModel.$formatters.push(function(val) {
          return '' + val;
        });
      }
    };
 });

