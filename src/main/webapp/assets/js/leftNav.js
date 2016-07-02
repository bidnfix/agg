'use strict';

var routingApp = angular.module('aggRoutingApp',['ngRoute', 'ui.bootstrap']);

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
                      when('/agg/fileClaim', {
                    	  templateUrl: '../../jsp/fileClaim.jsp',
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
                      when('/agg/termsncond', {
                    	  templateUrl: '../../jsp/termsandconditions.jsp'
                    	  //,controller: 'GetUserController'
                      }).
                      when('/agg/reportaBug', {
                    	  templateUrl: '../../jsp/reportaBug.jsp',
                    	  controller: 'ReportBugController'
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

routingApp.controller('ClaimsController', ['$scope', 'claimService', '$http', '$timeout', function($scope, claimService, $http, $timeout) {
	/*$http.get("/agg/claimsInfo")
    .then(function(response) {
    	$scope.quoteDOList = response.data.data.quoteInfoList;
    	//alert(response.data.data.quoteInfoList)
        $timeout(function () {
        	$('#table1').DataTable();
        }, 500);
    });*/
	$scope.editClaim = function(claimId) {
		alert(claimId);
		$http.get("/agg/editClaim")
		.then(function(response) {
			$scope.quoteDO = response.data.data.quoteDO;
			alert($scope.quoteDO);
		});
    };
    $scope.onClickSearchSerialNo = function(){
    	if($scope.serialNo){
    		claimService.getSerialNumberInfo($scope);
    	}
    };
    $scope.onClickSelectQuote = function(data){
    	claimService.selectQuote($scope, data);
    }
}]);
routingApp.controller('QuoteController', function($scope, $http) {
	$scope.quote={};
	$scope.quote.powerTrainMonths = 24;
	$scope.quote.hydraulicsMonths = 24;
	$scope.quote.fullMachineMonths = 24;
	$scope.quote.powerTrainHours = 2000;
	$scope.quote.hydraulicsHours = 2000;
	$scope.quote.fullMachineHours = 2000;
	$scope.quote.estSaleDate = new Date();
	
	$scope.date = new Date();
	
	$http.get("/agg/quoteInfo")
	.then(function(response) {
		$scope.dealerList = response.data.data.dealerDOList;
		$scope.manufacturerList = response.data.data.manufacturerDOList;
		$scope.useOfEquipmentDOList = response.data.data.useOfEquipmentDOList;
	});  

	var myTabs = tabs({
	    el: '#quoteTabs',
	    tabNavigationLinks: '.c-tabs-nav__link',
	    tabContentContainers: '.c-tab'
	  });
  
	myTabs.init();
	
	$scope.changeTab = function(index){
		myTabs.goToTab(index);
		if(index == 2){
			var coverageExpired = ($scope.quote.coverageExpired != null && $scope.quoteBasePrice == true)?true:false;
			var machineId = $scope.quote.machineInfoDO.machineId;
			$http.get("/agg/quote/coverageDeductInfo/"+coverageExpired+"/"+machineId)
			.then(function(response) {
				$scope.deductibleAmtList = response.data.data.deductibleAmtList;
				$scope.coverageTermList = response.data.data.coverageTermList;
				$scope.pricingDOList = response.data.data.pricingDOList;
				
				$scope.coverageTermSelected = $scope.coverageTermList[0];
				$scope.deductiblePriceSelected = $scope.deductibleAmtList[0];
			});  
		}else if(index == 3){
			if($scope.quote.coverageExpired != null && $scope.quote.coverageExpired == true){
				$scope.machineCondition = 'Used';
			}else if($scope.quote.coverageEndDate != null && ($scope.quote.coverageEndDate > $scope.date)){
				$scope.machineCondition = 'New';
			}else{
				$scope.machineCondition = 'Used';
			}
			
			var rowIndex = $scope.selectedRow;
			var colIndex = $scope.selectedCloumn;
			var dealerMarkupVlaue = "";
			
			if(rowIndex != "" && colIndex != ""){
				$scope.coverageHours = $scope.pricingDOList[rowIndex].coverageLevelHours;
				$scope.quoteBasePrice = (colIndex === 1)?$scope.pricingDOList[rowIndex].ptBasePrice:(colIndex === 2)?$scope.pricingDOList[rowIndex].phBasePrice:$scope.pricingDOList[rowIndex].plBasePrice;
				$scope.coverageType = (colIndex === 1)?"PT":(colIndex === 2)?"PH":"PL";
			}
			
			if($scope.quote.dealerMarkupVlaue == 'dealerMarkupPrice'){
				dealerMarkupVlaue = $scope.quote.dealerMarkup;
			}else{
				dealerMarkupVlaue = (($scope.quoteBasePrice * $scope.quote.dealerMarkup)/100);
			}
			
			$scope.dealerMarkupPrice = dealerMarkupVlaue;
			$scope.totalCustPrice = dealerMarkupVlaue + $scope.quoteBasePrice;
			$scope.totalDealerPrice = $scope.quoteBasePrice;
			
		}
	}
	
	$scope.disableSelected = true;
	$scope.displayDealerText = function(dealerObj){
		if(dealerObj != null){
			$scope.dealerText = '"'+dealerObj.name+'"'+' has been assigned to quote . You may assign this quote to a different approved dealer by selecting from the list and pressing the "Proceed to Machine Info" button below.';
			$scope.disableSelected = false;
		}else{
			$scope.dealerText = "";
			$scope.disableSelected = true;
		}
	}
	
	$scope.getMachineModel = function(manufacturerDO){
		if(manufacturerDO != null){
			 $http.get("/agg/manfModel/"+manufacturerDO.id)
			    .then(function(response) {
			        $scope.machineModelList = response.data.data.machineModelList;
			    });
		}
	}
	
	$scope.setClickedCloumn = function(rowIndex, colIndex){
		var tbCellVal = (colIndex === 1)?$scope.pricingDOList[rowIndex].ptBasePrice:(colIndex === 2)?$scope.pricingDOList[rowIndex].phBasePrice:$scope.pricingDOList[rowIndex].plBasePrice;
		if(tbCellVal != "" && tbCellVal != -1){
			$scope.selectedRow = rowIndex;
			$scope.selectedCloumn = colIndex;
		}else{
			$scope.selectedRow = "";
			$scope.selectedCloumn = "";
		}
	}
	
	$scope.setMouserCloumn = function(rowIndex, colIndex){
		$scope.mouseoverRow = rowIndex;
		$scope.mouseoverCloumn = colIndex;
	}
	
	$scope.getCoveragePriceLevels = function(deductiblePrice, coverageTerm){
		$scope.selectedRow = "";
		$scope.selectedCloumn = "";
		$scope.mouseoverRow = "";
		$scope.mouseoverCloumn = "";
		var coverageExpired = ($scope.quote.coverageExpired != null && $scope.quoteBasePrice == true)?true:false;
		var machineId = $scope.quote.machineInfoDO.machineId;
		$scope.coverageTermSelected = coverageTerm;
		$scope.deductiblePriceSelected = deductiblePrice;
		$http.get("/agg/quote/coverageLevelPrice/"+coverageExpired+"/"+machineId+"/"+deductiblePrice+"/"+coverageTerm)
		.then(function(response) {
			$scope.pricingDOList = response.data.data;
		});  
	}
	
	$scope.resetMouseoverColumn = function(){
		$scope.mouseoverRow = "";
		$scope.mouseoverCloumn = "";
	}
	
	$scope.getDealerInfo = function(){
		$http.get("/agg/quote/userInfo")
		.then(function(response) {
			var dealrDO = response.data.data;
			$scope.quote.dealerName = dealrDO.name;
			$scope.quote.dealerAddress = dealrDO.address1;
			$scope.quote.dealerCity = dealrDO.city;
			$scope.quote.dealerState = dealrDO.state;
			$scope.quote.dealerZip = dealrDO.zip;
			$scope.quote.dealerPhone = dealrDO.phone;
			$scope.quote.dealerEmail = dealrDO.invoiceEmail;
		});  
	}
});

routingApp.controller('ReportBugController', function($scope, $http) {
	$http.get("/agg/reportaBug")
	.then(function(response) {
		$scope.dealerList = response.data.data.dealerDOList;
		$scope.manufacturerList = response.data.data.manufacturerDOList;
	});  

	function MyCtrl($scope, $filter) {
	    $scope.date = $filter("date")(Date.now(), 'yyyy-MM-dd');
	};
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



