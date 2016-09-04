'use strict';

var routingApp = angular.module('aggRoutingApp',['ngRoute', 'ui.bootstrap', 'angularValidator']);

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
                      when('/agg/programAsDealer', {
                    	  templateUrl: '../../jsp/programsAsDealer.jsp',
                    	  controller: 'ProgramAsDealerController'
                    	
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
                      when('/agg/preauthClaim', {
                    	  templateUrl: '../../jsp/preAuthReqClaim.jsp',
                    	  controller: 'ClaimsPreAuthController'
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
                      when('/agg/quotes', {
                    	  templateUrl: '../../jsp/quotes.jsp',
                    	  controller: 'QuotesDetailController'
                      }).
                      when('/agg/viewQuote/:quoteId/:quoteCode', {
                    	  templateUrl: '../../jsp/quickQuote.jsp',
                    	  controller: 'QuoteDetailController'
                      }).
                      when('/agg/estPriceQuotes', {
                    	  templateUrl: '../../jsp/quotes.jsp',
                    	  controller: 'EstPriceQuotesController'
                      }).
                      when('/agg/purchaseReqQuotes', {
                    	  templateUrl: '../../jsp/quotes.jsp',
                    	  controller: 'PurReqQuotesController'
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
	$http.get("/agg/worklist")
    .then(function(response) {
        //$scope.activeDealers = response.data.data.activeDealers;
        //$scope.pendingDealers = response.data.data.pendingDealers;
        //$scope.terminatedDealers = response.data.data.terminatedDealers;
    	$scope.estPrice = response.data.data.worklistDO.estPrice;
    	$scope.invoiced = response.data.data.worklistDO.invoiced;
    	$scope.purchaseReq = response.data.data.worklistDO.purchaseReq;
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

routingApp.controller('GetProgramsController', function($scope, programService, $http, $timeout) {
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
	        alert($scope.program.prId);
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
    
    $scope.editProgramDetails = function() {
		alert("In editProgram");
		programService.editProgram($scope.program, $scope);
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

routingApp.controller('ProgramAsDealerController', function($scope, programService, $http) {
	//alert("in ProgramAsDealerController");
	$http.get("/agg/programAsDealer")
    .then(function(response) {
    	//alert(response.data.data.programList);
        $scope.programList = response.data.data.programList;
    });
	
	$scope.getProgDetails = function (programDO)
	 {
		 //alert(programDO.manufacturerDO.name);
   		 $scope.program.manufacturerDO = $scope.program.manufacturerDO || {};
		 $scope.program.manufacturerDO.name = programDO.manufacturerDO.name;
		 $scope.program.manufacturerDO.id = programDO.manufacturerDO.id;
		 $scope.program.machineInfoDOList = programDO.machineInfoDOList;
		 $scope.program.condition = programDO.condition;
		 $scope.program.cType = programDO.cType;
		 $scope.program.coverage = programDO.coverage;
		 $scope.program.cTerm = programDO.cTerm;
		 $scope.program.cHours = programDO.cHours;
		 $scope.program.deductible = programDO.deductible;
		 $scope.program.lol = programDO.lol;
		 $scope.program.cost = programDO.cost;
		 $scope.program.desc = programDO.desc;
		 $scope.program.dealerDO = $scope.program.dealerDO || {};
		 $scope.program.dealerDO.id = programDO.dealerDO.id;
		 $scope.program.dealerDO.name = programDO.dealerDO.name;
		 

	 }
   
   $scope.submitProgramAsDel = function() {
		alert("In submitProgramAsDel");
		programService.submitProgramAsDealr($scope.program, $scope);
   };
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

routingApp.controller('QuoteController', function($scope, $http, quoteService, $window) {
	$scope.quote={};
	$scope.quote.powerTrainMonths = 24;
	$scope.quote.hydraulicsMonths = 24;
	$scope.quote.fullMachineMonths = 24;
	$scope.quote.powerTrainHours = 2000;
	$scope.quote.hydraulicsHours = 2000;
	$scope.quote.fullMachineHours = 2000;
	$scope.quote.estSaleDate = new Date();
	$scope.quote.dealerMarkupType = "price";
	$scope.quote.custRemorsePeriod = true;
	$scope.quote.custUnderstandCoverage = true;
	
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
	
	$scope.changeTab = function(index, tabForm){
		//alert(tabForm.$valid);
		if(tabForm.$valid){
			if(index !=5){
				myTabs.goToTab(index);
			}
			if(index == 1){
				quoteService.saveWarrantyInfo($scope.quote, $scope);
			}else if(index == 2){
				//saveing machineInfo
				quoteService.saveMachineInfo($scope.quote, $scope);
				
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
				
				$scope.quote.machineCondition = $scope.machineCondition;
				
				var rowIndex = $scope.selectedRow;
				var colIndex = $scope.selectedCloumn;
				
				if(rowIndex >= 0 && colIndex > 0){
					$scope.coverageHours = $scope.pricingDOList[rowIndex].coverageLevelHours;
					$scope.quoteBasePrice = (colIndex === 1)?$scope.pricingDOList[rowIndex].ptBasePrice:(colIndex === 2)?$scope.pricingDOList[rowIndex].phBasePrice:$scope.pricingDOList[rowIndex].plBasePrice;
					$scope.coverageType = (colIndex === 1)?"PT":(colIndex === 2)?"PH":"PL";
					$scope.coverageTypeDesc = (colIndex === 1)?"Powertrain":(colIndex === 2)?"Powertrain + Hydraulic":"Powertrain + Hydraulic + Platform";
				}
				
				if($scope.quote.dealerMarkupType == 'price'){
					$scope.dealerMarkupAmtPrice = parseInt($scope.quote.dealerMarkup);
				}else{
					$scope.dealerMarkupAmtPrice = (($scope.quoteBasePrice * parseInt($scope.quote.dealerMarkup))/100);
				}
				
				$scope.totalCustPrice = $scope.dealerMarkupAmtPrice + $scope.quoteBasePrice;
				$scope.totalDealerPrice = $scope.quoteBasePrice;
				
				$scope.quote.coverageHours = $scope.coverageHours;
				$scope.quote.quoteBasePrice = $scope.quoteBasePrice;
				$scope.quote.coverageType = $scope.coverageType;
				$scope.quote.coverageTypeDesc = $scope.coverageTypeDesc;
				$scope.quote.customerPrice = $scope.totalCustPrice;
				$scope.quote.dealerMarkupPrice = $scope.dealerMarkupAmtPrice;
				
				//saving coverage information
				quoteService.saveCoverageInfo($scope.quote, $scope);
				
			}else if(index == 5){
				//saving coverage information
				quoteService.savePurchanseInfo($scope.quote, $scope);
			}
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
	
	$scope.printQuote = function(quotePrintType){
		if(quotePrintType == 'dealer'){
			$window.open('/agg/quote/report/dealer/'+$scope.quote.quoteId);
		}else if(quotePrintType == 'customer'){
			$window.open('/agg/quote/report/customer/'+$scope.quote.quoteId);
		}
	}
	
	$scope.validateWarrantyInfoForm = function(){
		//alert(1);
	}
	
	$scope.validateMachineInfoForm = function(){
		//alert(2);
	}
	
	$scope.validateCoverageInfoForm = function(){
		//alert(3);
	}
	
	$scope.validateQuoteSummaryForm = function(){
		//alert(4);
	}
});

routingApp.controller('ReportBugController', function($scope, $http) {
	$scope.report={};
	$scope.date = new Date()
	
	$http.get("/agg/bugId")
    .then(function(response) {
        $scope.bugId = response.data.data.bugId;
    });
	
	/*
	$http.get("/agg/reportaBug")
	.then(function(response) {
		$scope.dealerList = response.data.data.dealerDOList;
		$scope.manufacturerList = response.data.data.manufacturerDOList;
	});  

	function MyCtrl($scope, $filter) {
	    $scope.date = $filter("date")(Date.now(), 'yyyy-MM-dd');
	};*/
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

routingApp.controller('QuotesDetailController', function($scope, $http, $timeout) {
	$http.get("/agg/quotesInfo")
	.then(function(response) {
        $scope.quoteList = response.data.data;
        $timeout(function () {
        	$('#quotesTbl').DataTable();
        }, 300);
    });
	
	/*$scope.viewQuote = function(id, quoteId){
		alert(id+" "+quoteId);
		return '../../jsp/pendingDealers.jsp';
	}*/
})

routingApp.controller('EstPriceQuotesController', function($scope, $http, $timeout) {
	$http.get("/agg/estimatedPriceQuotes")
	.then(function(response) {
        $scope.quoteList = response.data.data;
        $timeout(function () {
        	$('#quotesTbl').DataTable();
        }, 300);
    });
	
	/*$scope.viewQuote = function(id, quoteId){
		alert(id+" "+quoteId);
		return '../../jsp/pendingDealers.jsp';
	}*/
})

routingApp.controller('PurReqQuotesController', function($scope, $http, $timeout) {
	$http.get("/agg/purchaseRequestedQuotes")
	.then(function(response) {
        $scope.quoteList = response.data.data;
        $timeout(function () {
        	$('#quotesTbl').DataTable();
        }, 300);
    });
	
	/*$scope.viewQuote = function(id, quoteId){
		alert(id+" "+quoteId);
		return '../../jsp/pendingDealers.jsp';
	}*/
})


routingApp.controller('QuoteDetailController', function($scope, $http, $timeout, $routeParams, $route, $window, quoteService) {
	//alert($routeParams.quoteId+" - "+$routeParams.quoteCode);
	$scope.quote = {};
	$scope.date = new Date();
	$scope.disabled= true;
	$http.get("/agg/quoteInfo/"+$routeParams.quoteId+"/"+$routeParams.quoteCode)
	.then(function(response) {
        $scope.dealerList = response.data.data.dealerDOList;
		$scope.manufacturerList = response.data.data.manufacturerDOList;
		$scope.useOfEquipmentDOList = response.data.data.useOfEquipmentDOList;
		$scope.machineModelList = response.data.data.machineModelList;
		$scope.deductibleAmtList = response.data.data.deductibleAmtList;
		$scope.coverageTermList = response.data.data.coverageTermList;
		$scope.pricingDOList = response.data.data.pricingDOList;
		$scope.coverageLevelHoursList = response.data.data.coverageLevelHoursList;
		$scope.quote = response.data.data.quote;
		
		$scope.quote.coverageEndDate = new Date($scope.quote.coverageEndDate);
		$scope.quote.estSaleDate = new Date($scope.quote.estSaleDate);
		$scope.quote.lastUpdate = new Date($scope.quote.lastUpdate);
		
        if($scope.quote.coverageExpired != null && $scope.quote.coverageExpired == true){
    		$scope.machineCondition = 'Used';
    	}else if($scope.quote.coverageEndDate != null && ($scope.quote.coverageEndDate > $scope.date)){
    		$scope.machineCondition = 'New';
    	}else{
    		$scope.machineCondition = 'Used';
    	}
    });
	
	$scope.getMachineModel = function(manufacturerDO){
		if(manufacturerDO != null){
			 $http.get("/agg/manfModel/"+manufacturerDO.id)
			    .then(function(response) {
			        $scope.machineModelList = response.data.data.machineModelList;
			    });
		}
	}
	
	$scope.editQuote = function(){
		$scope.disabled= false;
	}
	
	$scope.updateQuote = function(quoteForm){
		alert(quoteForm.$valid);
		if(quoteForm.$valid){
			quoteService.updateQuote($scope.quote, $scope);
		}
	}
	
	$scope.archiveQuote = function(){
		if($window.confirm('Are you sure you want to archive this record?')) {
			if($scope.quote != null){
				quoteService.archiveQuote($scope.quote, $scope);
			}
		}
	}
	
	$scope.invoiceQuote = function(quoteForm){
		if(quoteForm.$valid){
			quoteService.invoiceQuote($scope.quote, $scope);
		}
	}
	
	$scope.createContract = function(quoteForm){
		if(quoteForm.$valid){
			
		}
	}
	
	$scope.printQuote = function(quotePrintType){
		if(quotePrintType == 'dealer'){
			$window.open('/agg/quote/report/dealer/'+$scope.quote.quoteId);
		}else if(quotePrintType == 'customer'){
			$window.open('/agg/quote/report/customer/'+$scope.quote.quoteId);
		}
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


