'use strict';

var routingApp = angular.module('aggRoutingApp',['ngRoute', 'ui.bootstrap', 'angularValidator', 'angularModalService']);

routingApp.config(['$httpProvider', function ($httpProvider) {
    $httpProvider.interceptors.push(['$q', function ($q) {
        return {
            'responseError': function (response) {
                if (response.status === 440) {//440 to indicate the user is timeout.
                   window.location.href = "/agg/login"; //redirect to your login page
                }
                return $q.reject(response);
            }
        };
    }]);
}]);

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
	                  when('/agg/dealer/:dealerId', {
	                  	  templateUrl: '../../jsp/editDealer.jsp',
	                  	  controller: 'GetDealerDetailsController'
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
                    	  controller: 'userController'
                      }).
                      when('/agg/addUser/:dealerId', {
                    	  templateUrl: '../../jsp/addUser.jsp',
                    	  controller: 'userController'
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
                    	  controller: 'ClaimsInfoController',
                		  resolve: {
	        		         claimsType: function () {
	        		           return 'All';
	        		         }
	        		      }
                      }).
                      when('/agg/approvedClaims', {
                    	  templateUrl: '../../jsp/claimsInfo.jsp',
                    	  controller: 'ClaimsInfoController',
                		  resolve: {
 	        		         claimsType: function () {
 	        		           return 'Approved';
 	        		         }
 	        		      }
                      }).
                      when('/agg/rejectedClaims', {
                    	  templateUrl: '../../jsp/claimsInfo.jsp',
                    	  controller: 'ClaimsInfoController',
                    	  resolve: {
  	        		         claimsType: function () {
  	        		           return 'Rejected';
  	        		         }
  	        		      }
                      }).
                      when('/agg/draftClaims', {
                    	  templateUrl: '../../jsp/claimsInfo.jsp',
                    	  controller: 'ClaimsInfoController',
                    	  resolve: {
  	        		         claimsType: function () {
  	        		           return 'Draft';
  	        		         }
  	        		      }
                      }).
                      when('/agg/printClaims', {
                    	  templateUrl: '../../jsp/claimsInfo.jsp',
                    	  controller: 'ClaimsInfoController',
                    	  resolve: {
  	        		         claimsType: function () {
  	        		           return 'Print';
  	        		         }
  	        		      }
                      }).
                      when('/agg/fileClaim', {
                    	  templateUrl: '../../jsp/fileaClaim.jsp',
                    	  controller: 'ClaimsController'
                      }).
                      when('/agg/fileClaim/:claimId', {
                    	  templateUrl: '../../jsp/fileaClaim.jsp',
                    	  controller: 'ClaimsController'
                      }).
                      when('/agg/adjudicateClaim/:claimId', {
                    	  templateUrl: '../../jsp/adjudicateClaim.jsp',
                    	  controller: 'ClaimsAdjudicateController',
                    	  resolve: {
	        		         claimType: function () {
	        		           return 'claimDetails';
	        		         }
	        		      }
                      }).
                      when('/agg/preauthClaim', {
                    	  templateUrl: '../../jsp/preAuthReqClaim.jsp',
                    	  controller: 'ClaimsPreAuthController'
                      }).
                      when('/agg/adjudicateClaim', {
                    	  templateUrl: '../../jsp/adjudicateClaim.jsp',
                    	  controller: 'ClaimsAdjudicateController',
                		  resolve: {
   	        		         claimType: function () {
   	        		           return 'pending';
   	        		         }
   	        		      }
                      }).
                      when('/agg/approvedForPaymentClaims', {
                    	  templateUrl: '../../jsp/adjudicateClaim.jsp',
                    	  controller: 'ClaimsAdjudicateController',
                    	  resolve: {
  	        		         claimType: function () {
  	        		           return 'approvedForPayment';
  	        		         }
  	        		      }
                      }).
                      when('/agg/editClaim', {
                    	  templateUrl: '../../jsp/fileaClaim.jsp',
                    	  controller: 'ClaimsController'
                      }).
                      when('/agg/draftClaim', {
                    	  templateUrl: '../../jsp/draftClaim.jsp',
                    	  controller: 'ClaimsDraftsController'
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
                      when('/agg/bugInfo', {
                    	  templateUrl: '../../jsp/bugInfo.jsp',
                    	  controller: 'BugInfoController'
                      }).
                      when('/agg/quotes', {
                    	  templateUrl: '../../jsp/quotes.jsp',
                    	  controller: 'QuotesDetailController',
                    	  resolve: {
   	        		         quoteType: function () {
   	        		           return 'All';
   	        		         }
   	        		      }
                      }).
                      when('/agg/archivedQuotes', {
                    	  templateUrl: '../../jsp/archivedQuotes.jsp',
                    	  controller: 'QuotesDetailController',
                    	  resolve: {
	        		         quoteType: function () {
	        		           return 'Archive';
	        		         }
	        		      }
                      }).
                      when('/agg/viewQuote/:quoteId/:quoteCode', {
                    	  templateUrl: '../../jsp/quickQuote.jsp',
                    	  controller: 'QuoteDetailController'
                      }).
                      when('/agg/contracts', {
                    	  templateUrl: '../../jsp/contracts.jsp',
                    	  controller: 'ContractsController'
                      }).
                      when('/agg/viewContract/:contractId/:contractCode', {
                    	  templateUrl: '../../jsp/editContract.jsp',
                    	  controller: 'ContractDetailController'
                      }).
                      when('/agg/viewContractDetails/:contractId/:contractCode', {
                    	  templateUrl: '../../jsp/viewContract.jsp',
                    	  controller: 'ContractViewDetailController'
                      }).
                      when('/agg/reports', {
                    	  templateUrl: '../../jsp/excelReports.jsp',
                    	  controller: 'GenerateClaimReportController'
                      }).
                      when('/agg/reports/contracts', {
                    	  templateUrl: '../../jsp/graphsReport.jsp',
                    	  controller: 'ReportsController'
                      }).
                      when('/agg/reports/topClaims', {
                    	  templateUrl: '../../jsp/graphsReport.jsp',
                    	  controller: 'TopClaimsReportController'
                      }).
                      when('/agg/useOfEquipment', {
                    	  templateUrl: '../../jsp/useOfEquip.jsp',
                    	  controller: 'useOfEquipController'
                      }).
                      when('/agg/addEquipment', {
                    	  templateUrl: '../../jsp/addUseOfEquip.jsp',
                    	  controller: 'useOfEquipController'
                      }).
                      when('/agg/usageTier', {
                    	  templateUrl: '../../jsp/usageTier.jsp',
                    	  controller: 'usageTierController'
                      }).
                      when('/agg/addUsageTier', {
                    	  templateUrl: '../../jsp/addUsageTier.jsp',
                    	  controller: 'usageTierController'
                      }).
                      when('/agg/editUsageTier/:id', {
                    	  templateUrl: '../../jsp/editUsageTier.jsp',
                    	  controller: 'editUsageTierController'
                      }).
                      when('/agg/oemWarrantyTier', {
                    	  templateUrl: '../../jsp/oemWarranty.jsp',
                    	  controller: 'oemController'
                      }).
                      when('/agg/addOEMWarrantyTier', {
                    	  templateUrl: '../../jsp/addOEM.jsp',
                    	  controller: 'oemController'
                      }).
                      when('/agg/editOEMWarrantyTier/:id', {
                    	  templateUrl: '../../jsp/editOEM.jsp',
                    	  controller: 'editOEMController'
                      }).
                      when('/agg/manufacturers', {
                    	  templateUrl: '../../jsp/manufacturerInfo.jsp',
                    	  controller: 'manufacturerController'
                      }).
                      when('/agg/addManufacturer', {
                    	  templateUrl: '../../jsp/addManufacturer.jsp',
                    	  controller: 'manufacturerController'
                      }).
                      when('/agg/editManufacturer/:id', {
                    	  templateUrl: '../../jsp/editManufacturer.jsp',
                    	  controller: 'editManufacturerController'
                      }).
                      otherwise({
                    	  redirectTo: '/agg/home'
                      });
                }]);

routingApp.run( function($rootScope, $location, $http) {
    // register listener to watch route changes
    $rootScope.$on( "$routeChangeStart", function(event, next, current) {
    	$http.get("/agg/isLoggedIn")
		.then(function(response) {
			if(response.data.data === false){
				window.location.href = "/agg/login";
			}
		});
    });
 });

//Fileupload directive
routingApp.directive('ngFiles', ['$parse', function ($parse) {
    function fn_link(scope, element, attrs) {
        var onChange = $parse(attrs.ngFiles);
        element.on('change', function (event) {
            onChange(scope, { $files: event.target.files });
        });
    };

    return {
        link: fn_link
    }
}]);

routingApp.controller('GetDealerController', function($scope, dealerService, $http, $timeout, ctrlOptions, $routeParams, $route) {
	$scope.dealer={};
	if(ctrlOptions.getAllDealers){
		var dealerUrl = "/agg/dealerInfo";
		if($routeParams.dealerId != null){
			dealerUrl = dealerUrl+"/"+$routeParams.dealerId;
		}
		$http.get(dealerUrl)
		.then(function(response) {
	        $scope.dealerList = response.data.data.dealerDOList;
	        if(response.data.data.dealerName != null){
	        	$('#dealerAddInfoMsg').html("Dealer '<strong>"+response.data.data.dealerName+"</strong>' successfully added");
            	$('#dealerAddInfoMsg').removeClass('hidden');
            	window.setTimeout(function() {
        		  $("#dealerAddInfoMsg").fadeTo(500, 0).slideUp(500, function(){
        		    $(this).remove(); 
        		  });
        		}, 3000);
	        }
	        $timeout(function () {
	        	$('#dealerTbl').DataTable({"lengthMenu": [[10, 25, 50, 100, -1], [10, 25, 50, 100, "All"]]});
	        }, 300);
	    });
		
	}else{
		$http.get("/agg/pendingDealerInfo")
		.then(function(response) {
	        $scope.dealerList = response.data.data;
	        $timeout(function () {
	        	$('#dealerTbl').DataTable({"lengthMenu": [[10, 25, 50, 100, -1], [10, 25, 50, 100, "All"]]});
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

routingApp.controller('GetDealerDetailsController', function($scope, dealerService, $http, $timeout, $routeParams, $route, userService) {
	$scope.dealer={};
	$http.get("/agg/dealer/"+$routeParams.dealerId)
	.then(function(response) {
		$scope.roleList = response.data.data.roleList;
		$scope.parentDealerList = response.data.data.parentDealers;
        $scope.dealer = response.data.data.dealer;
        $scope.userList = response.data.data.dealerUsers;
        $timeout(function () {
        	$('#userTbl').DataTable({"lengthMenu": [[10, 25, 50, 100, -1], [10, 25, 50, 100, "All"]]});
        }, 300);
        if(response.data.data.dealerName != null){
        	$('#dealerAddInfoMsg').html("Dealer '<strong>"+response.data.data.dealerName+"</strong>' successfully added");
        	$('#dealerAddInfoMsg').removeClass('hidden');
        	window.setTimeout(function() {
    		  $("#dealerAddInfoMsg").fadeTo(500, 0).slideUp(500, function(){
    		    $(this).remove(); 
    		  });
    		}, 3000);
        }
    });
    
    $scope.submitEditDealer = function(dealerInfoForm){
    	if(dealerInfoForm.$valid){
    		dealerService.editDealer($scope.dealer, $scope);
    	}
    }
    
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

routingApp.controller('GetMachineInfoController', function($scope, machineService, $http, $timeout) {
	$http.get("/agg/machineInfo")
    .then(function(response) {
        $scope.machineInfoList = response.data.data.machineInfoList;
        $timeout(function () {
        	$('#table1').DataTable({"lengthMenu": [[10, 25, 50, 100, -1], [10, 25, 50, 100, "All"]]});
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

routingApp.controller('AddUserController', function($scope, $http, $routeParams) {
	$http.get("/agg/dealerAndRoleInfo")
    .then(function(response) {
    	$routeParams.dealerId
        $scope.dealerList = response.data.data.dealerList;
        $scope.roleList = response.data.data.roleList;
        var dealerId = $routeParams.dealerId;
        if(dealerId != null && dealerId > 0){
        	$scope.user = {};
        	$scope.user.dealerDO = {};
        	angular.forEach($scope.dealerList, function(dealer, key){
        		if(dealerId == dealer.id){
        			$scope.user.dealerDO = dealer;
        		}
    	    });
        }
    });
});

routingApp.controller('HomeController', function($scope, $http, $timeout, $window, $filter) {
	$scope.contractsFlag = true;
	$scope.quotesFlag = true;
	$scope.claimsFlag = true;
	$scope.quotesInvoiceFlag = true;
	$scope.approvedForPaymentClaimsFlag = true;
	
	$('#navbar > ul.nav li a').click(function(e) {
	    var $this = $(this);
	    $this.parent().siblings().removeClass('worklist-menu-selected').end().addClass('worklist-menu-selected');
	    e.preventDefault();
	});
	
	$http.get("/agg/worklist")
    .then(function(response) {
    	$scope.worklistDO = $scope.worklistDO || {};
    	$scope.expContracts = response.data.data.worklistDO.expContracts;
    	$scope.actContracts = response.data.data.worklistDO.actContracts;
    	$scope.estPrice = response.data.data.worklistDO.estPrice;
    	$scope.invoiced = response.data.data.worklistDO.invoiced;
    	$scope.purchaseReq = response.data.data.worklistDO.purchaseReq;
    	$scope.claims = response.data.data.worklistDO.claims;
    	$scope.approvedForPaymentClaims = response.data.data.worklistDO.approvedForPaymentClaims;
    	$scope.quoteList = response.data.data.quoteList;
    	
    	//if($scope.quoteList != null){
    		$('#quotesTbl').dataTable().fnClearTable();
    		$('#quotesTbl').dataTable().fnDestroy();
    		$('#quotesTbl').parents('div.dataTables_wrapper').first().show();
    		$('#contractsTbl').parents('div.dataTables_wrapper').first().hide();
    		$('#claimsTbl').parents('div.dataTables_wrapper').first().hide();
    		$('#quotesInvoiceTbl').parents('div.dataTables_wrapper').first().hide();
    		$('#approvedForPaymentClaimsTbl').parents('div.dataTables_wrapper').first().hide();
    		$scope.contractsFlag = true;
    		$scope.quotesFlag = false;
    		$scope.claimsFlag = true;
    		$scope.quotesInvoiceFlag = true;
    		$scope.approvedForPaymentClaimsFlag = true;
    	//}
    	
    	$timeout(function () {
        	$('#quotesTbl').DataTable({
        		"aaSorting": [[ 6, "desc" ]],
        		"lengthMenu": [[-1, 10, 25, 50, 100], ["All", 10, 25, 50, 100]],
        		"bDestroy": true,
        		columnDefs: [{ targets: 6, visible: false },{ width: "12%", targets: 0 }]
        	});
        }, 300);
    });
	
	$scope.getEstQuotes = function(){
		$http.get("/agg/estimatedPriceQuotes")
		.then(function(response) {
	        $scope.quoteList = response.data.data;
	        //if($scope.quoteList != null){
	        	$('#quotesTbl').dataTable().fnClearTable();
	        	$('#quotesTbl').dataTable().fnDestroy();
	    		$scope.contractsFlag = true;
	    		$scope.quotesFlag = false;
	    		$scope.claimsFlag = true;
	    		$scope.quotesInvoiceFlag = true;
	    		$scope.approvedForPaymentClaimsFlag = true;
				$('#quotesTbl').parents('div.dataTables_wrapper').first().show();
	    		$('#contractsTbl').parents('div.dataTables_wrapper').first().hide();
	    		$('#claimsTbl').parents('div.dataTables_wrapper').first().hide();
	    		$('#quotesInvoiceTbl').parents('div.dataTables_wrapper').first().hide();
	    		$('#approvedForPaymentClaimsTbl').parents('div.dataTables_wrapper').first().hide();
	    	//}
	        $timeout(function () {
	        	$('#quotesTbl').DataTable({
	        		"aaSorting": [[ 6, "desc" ]],
	        		columnDefs: [{ targets: 6, visible: false }, { width: "12%", targets: 0 }],
	        		"lengthMenu": [[10, 25, 50, 100, -1], [10, 25, 50, 100, "All"]], 
	        		"bDestroy": true
	        	});
	        }, 300);
	    });
	}
	
	$scope.getInvoicedQuotes = function(){
		$http.get("/agg/invoicedQuotes")
		.then(function(response) {
	        $scope.quoteList = response.data.data;
	        //if($scope.quoteList != null){
	        	$('#quotesInvoiceTbl').dataTable().fnClearTable();
	        	$('#quotesInvoiceTbl').dataTable().fnDestroy();
	    		$scope.contractsFlag = true;
	    		$scope.quotesFlag = true;
	    		$scope.claimsFlag = true;
	    		$scope.approvedForPaymentClaimsFlag = true;
	    		$scope.quotesInvoiceFlag = false;
				$('#quotesTbl').parents('div.dataTables_wrapper').first().hide();
	    		$('#contractsTbl').parents('div.dataTables_wrapper').first().hide();
	    		$('#claimsTbl').parents('div.dataTables_wrapper').first().hide();
	    		$('#approvedForPaymentClaimsTbl').parents('div.dataTables_wrapper').first().hide();
	    		$('#quotesInvoiceTbl').parents('div.dataTables_wrapper').first().show();
	    	//}
	        $timeout(function () {
	        	$('#quotesInvoiceTbl').DataTable({
	        		"footerCallback": function ( row, data, start, end, display ) {
	                    var api = this.api(), data;
	         
	                    // Remove the formatting to get integer data for summation
	                    var intVal = function ( i ) {
	                        return typeof i === 'string' ?
	                            i.replace(/[\$,]/g, '')*1 :
	                            typeof i === 'number' ?
	                                i : 0;
	                    };
	         
	                    // Total over all pages
	                    var total = api
	                        .column(3)
	                        .data()
	                        .reduce( function (a, b) {
	                            return intVal(a) + intVal(b);
	                        }, 0 );
	         
	                    // Total over this page
	                    var pageTotal = api
	                        .column( 3, { page: 'current'} )
	                        .data()
	                        .reduce( function (a, b) {
	                            return intVal(a) + intVal(b);
	                        }, 0 );
	         
	                    // Update footer
	                    /*$( api.column( 3 ).footer() ).html(
	                        '$'+pageTotal +' ( $'+ total +' total)'
	                    );*/
	                    
	                    $("#invoiceTotal").html(
	                        'Page total: '+ $filter('currency')(pageTotal, "$", 2) +' ( '+ $filter('currency')(total, "$", 2) +' Grand total )'
	                    );
	                },
	        		"aaSorting": [[ 7, "desc" ]],
	        		columnDefs: [{ targets: 7, visible: false }, { width: "12%", targets: 0 }],
	        		"lengthMenu": [[-1, 10, 25, 50, 100], ["All", 10, 25, 50, 100]], 
	        		"bDestroy": true
	        	});
	        }, 300);
	    });
	}
	
	$scope.getReqQuotes = function(){
		$http.get("/agg/purchaseRequestedQuotes")
		.then(function(response) {
	        $scope.quoteList = response.data.data;
	        //if($scope.quoteList != null){
	        	$('#quotesTbl').dataTable().fnClearTable();
	        	$('#quotesTbl').dataTable().fnDestroy();
	    		$scope.contractsFlag = true;
	    		$scope.quotesFlag = false;
	    		$scope.claimsFlag = true;
	    		$scope.quotesInvoiceFlag = true;
	    		$scope.approvedForPaymentClaimsFlag = true;
				$('#quotesTbl').parents('div.dataTables_wrapper').first().show();
	    		$('#contractsTbl').parents('div.dataTables_wrapper').first().hide();
	    		$('#claimsTbl').parents('div.dataTables_wrapper').first().hide();
	    		$('#quotesInvoiceTbl').parents('div.dataTables_wrapper').first().hide();
	    		$('#approvedForPaymentClaimsTbl').parents('div.dataTables_wrapper').first().hide();
	    	//}
	        $timeout(function () {
	        	$('#quotesTbl').DataTable({
	        		"aaSorting": [[ 6, "desc" ]],
	        		columnDefs: [{ targets: 6, visible: false }, { width: "12%", targets: 0 }],
	        		"lengthMenu": [[-1, 10, 25, 50, 100], ["All", 10, 25, 50, 100]], 
	        		"bDestroy": true
	        	});
	        }, 300);
	    });
	}
	
	$scope.getActiveContracts = function()
	{
		$http.get("/agg/activeContracts")
		.then(function(response) {
			$scope.contractList = response.data.data.contractDOList;
			//if($scope.contractList != null){
				$('#contractsTbl').dataTable().fnClearTable();
	    		$('#contractsTbl').dataTable().fnDestroy();
	    		$scope.contractsFlag = false;
	    		$scope.quotesFlag = true;
	    		$scope.claimsFlag = true;
	    		$scope.quotesInvoiceFlag = true;
	    		$scope.approvedForPaymentClaimsFlag = true;
				$('#contractsTbl').parents('div.dataTables_wrapper').first().show();
	    		$('#quotesTbl').parents('div.dataTables_wrapper').first().hide();
	    		$('#claimsTbl').parents('div.dataTables_wrapper').first().hide();
	    		$('#quotesInvoiceTbl').parents('div.dataTables_wrapper').first().hide();
	    		$('#approvedForPaymentClaimsTbl').parents('div.dataTables_wrapper').first().hide();
	    	//}
	        $timeout(function () {
	        	$('#contractsTbl').DataTable({
	        		"aaSorting": [[ 7, "desc" ]],
	        		columnDefs: [{ targets: 7, visible: false }, { width: "12%", targets: 0 }],
	        		"lengthMenu": [[10, 25, 50, 100, -1], [10, 25, 50, 100, "All"]], 
	        		"bDestroy": true
	        	});
	        }, 300);
	    });
	}
	
/*$scope.getActiveContracts = function() {
		
		$(document).ready(function() {
			$('#contractsTbl').dataTable().fnClearTable();
    		$('#contractsTbl').dataTable().fnDestroy();
    		$scope.contractsFlag = false;
    		$scope.quotesFlag = true;
    		$scope.claimsFlag = true;
			$('#contractsTbl').parents('div.dataTables_wrapper').first().show();
    		$('#quotesTbl').parents('div.dataTables_wrapper').first().hide();
    		$('#claimsTbl').parents('div.dataTables_wrapper').first().hide();
    		$('#contractsTbl').DataTable( {
		        "processing": true,
		        "serverSide": true,
		        ajax: {
		            url: '/agg/activeContracts',
		            dataFilter: function(data){
		                var json = jQuery.parseJSON( data );
		                json.recordsTotal = json.recordsTotal;
			            json.recordsFiltered = json.recordsFiltered;
			            json.data = json.data;
			            
			            for ( var i=0, len=json.data.length; i<len ; i++ ) {
			            	json.data[i].status = json.data[i].status ==  '1' ? "Active" : json.data[i].status == '2' ? "Expired" : json.data[i].status == '3' ? "Cancelled" : "Archived";
	        				json.data[i].viewDetails = '<div class="manage-sec"><a href="#/agg/viewContract/'+json.data[i].id+'/'+json.data[i].contractId+'"><img src="../assets/images/edit-pencil.png" alt="View" title="View"/></a></div>';
	        				 
	        			 }
		                return JSON.stringify( json );
		            }},
		            "order": [[ 7, "desc" ]],
		            "lengthMenu": [[10, 25, 50, 100, -1], [10, 25, 50, 100, "All"]],
		            columnDefs: [ { targets: 7, visible: false },
		                { width: "12%", targets: 0 }
		            ],
		          "columns": [
	        		    { "data": "contractId" },
	        		    { "data": "machineSerialNo" },
	        		    { "data": "lol" },
	        		    { "data": "inceptionDate",
	        		    	"type": "date ",
	        		    	"render": function (data) {
	        		            if (data !== null) {
	        		                var date = new Date(data);
	        		                date = date.getMonth() + 1 + "/" + date.getDate() + "/" + date.getFullYear();
	        		                return date;
	        		            } else {
	        		                return "";
	        		            }
	        		        }},
	        		    
	        		    { "data": "expirationDate","type": "date ",
	            		    	"render": function (data) {
	            		            if (data !== null) {
	            		                var date = new Date(data);
	            		                date = date.getMonth() + 1 + "/" + date.getDate() + "/" + date.getFullYear();
	            		                return date;
	            		            } else {
	            		                return "";
	            		            }
	            		        } },
	        		    { "data": "expirationUsageHours" },
	        		    { "data": "status" },
	        		    { "data": "lastUpdatedDate","type": "date ",
	        		    	"render": function (data) {
	        		            if (data !== null) {
	        		                var date = new Date(data);
	        		                date = date.getMonth() + 1 + "/" + date.getDate() + "/" + date.getFullYear();
	        		                return date;
	        		            } else {
	        		                return "";
	        		            }
	        		        } },
	        		    { "data": "viewDetails" }
	        		  ]	
		   } );
    		

		} );

	}*/
	
	$scope.getClaims = function(){
		$http.get("/agg/getClaimsInfo")
		.then(function(response) {
			$scope.claimDOList = response.data.data.claimDOList;
	        //if($scope.quoteList != null){
				$('#claimsTbl').dataTable().fnClearTable();
				$('#claimsTbl').dataTable().fnDestroy();
	    		$scope.contractsFlag = true;
	    		$scope.quotesFlag = true;
	    		$scope.claimsFlag = false;
	    		$scope.quotesInvoiceFlag = true;
	    		$scope.approvedForPaymentClaimsFlag = true;
				$('#claimsTbl').parents('div.dataTables_wrapper').first().show();
	    		$('#contractsTbl').parents('div.dataTables_wrapper').first().hide();
	    		$('#quotesTbl').parents('div.dataTables_wrapper').first().hide();
	    		$('#quotesInvoiceTbl').parents('div.dataTables_wrapper').first().hide();
	    		$('#approvedForPaymentClaimsTbl').parents('div.dataTables_wrapper').first().hide();
	    	//}
	        $timeout(function () {
	        	$('#claimsTbl').DataTable({
	        		"aaSorting": [[ 8, "desc" ]],
	        		columnDefs: [{ targets: 8, visible: false }, { width: "12%", targets: 0 }],
	        		"lengthMenu": [[10, 25, 50, 100, -1], [10, 25, 50, 100, "All"]], 
	        		"bDestroy": true
	        	});
	        }, 300);
	    });
	}
	
	$scope.getInactiveContracts = function()
	{
		$http.get("/agg/inactiveContracts")
		.then(function(response) {
			$scope.contractList = response.data.data.contractDOList;
			//if($scope.contractList != null){
				$('#contractsTbl').dataTable().fnClearTable();
				$('#contractsTbl').dataTable().fnDestroy();
	    		$scope.contractsFlag = false;
	    		$scope.quotesFlag = true;
	    		$scope.claimsFlag = true;
	    		$scope.quotesInvoiceFlag = true;
	    		$scope.approvedForPaymentClaimsFlag = true;
				$('#contractsTbl').parents('div.dataTables_wrapper').first().show();
	    		$('#quotesTbl').parents('div.dataTables_wrapper').first().hide();
	    		$('#claimsTbl').parents('div.dataTables_wrapper').first().hide();
	    		$('#quotesInvoiceTbl').parents('div.dataTables_wrapper').first().hide();
	    		$('#approvedForPaymentClaimsTbl').parents('div.dataTables_wrapper').first().hide();
	    	//}
	        $timeout(function () {
	        	$('#contractsTbl').DataTable({
	        		"aaSorting": [[ 7, "desc" ]],
	        		columnDefs: [{ targets: 7, visible: false }, { width: "12%", targets: 0 }],
	        		"lengthMenu": [[10, 25, 50, 100, -1], [10, 25, 50, 100, "All"]], 
	        		"bDestroy": true
	        	});
	        }, 300);
	    });
	}
	
	$scope.getApprovedForPaymentClaims = function(){
		$http.get("/agg/getApprovedForPaymentClaims")
		.then(function(response) {
			$scope.claimDOList = response.data.data.claimDOList;
	        //if($scope.quoteList != null){
				$('#approvedForPaymentClaimsTbl').dataTable().fnClearTable();
				$('#approvedForPaymentClaimsTbl').dataTable().fnDestroy();
	    		$scope.contractsFlag = true;
	    		$scope.quotesFlag = true;
	    		$scope.claimsFlag = true;
	    		$scope.quotesInvoiceFlag = true;
	    		$scope.approvedForPaymentClaimsFlag = false;
	    		$('#approvedForPaymentClaimsTbl').parents('div.dataTables_wrapper').first().show();
				$('#claimsTbl').parents('div.dataTables_wrapper').first().hide();
	    		$('#contractsTbl').parents('div.dataTables_wrapper').first().hide();
	    		$('#quotesTbl').parents('div.dataTables_wrapper').first().hide();
	    		$('#quotesInvoiceTbl').parents('div.dataTables_wrapper').first().hide();
	    	//}
	        $timeout(function () {
	        	$('#approvedForPaymentClaimsTbl').DataTable({
	        		"aaSorting": [[ 8, "desc" ]],
	        		columnDefs: [{ targets: 8, visible: false }, { width: "12%", targets: 0 }],
	        		"lengthMenu": [[10, 25, 50, 100, -1], [10, 25, 50, 100, "All"]], 
	        		"bDestroy": true
	        	});
	        }, 300);
	    });
	}
	
	$scope.editClaimByDealer = function(claimId, status){
		$window.location = '#/agg/fileClaim/' + claimId;
		
	};
	
	$scope.adjudicateClaim = function(claimId, status){
		$window.location = '#/agg/adjudicateClaim/' + claimId;
	};
	
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

routingApp.controller('GetProgramsController', function($scope, programService, $http, $timeout) {
	$http.get("/agg/programs")
    .then(function(response) {
        $scope.programsList = response.data.data.programs;
        $timeout(function () {
        	$('#table1').DataTable({"lengthMenu": [[10, 25, 50, 100, -1], [10, 25, 50, 100, "All"]]});
        }, 300);
    });
	
	$scope.editProgram = function(programId) {
		//alert(programId);
		$scope.program = {};
		$scope.machineModelList = null;
		$http.get("/agg/programs/"+programId)
	    .then(function(response) {
	        $scope.program = response.data.data.program;
	        $scope.manufacturerList = response.data.data.manufacturerList;
	        $scope.dealerList = response.data.data.dealerList;
	        $scope.program.machineModelList = response.data.data.machineInfoList;
	        //alert($scope.program.prId);
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
		//alert("In editProgram");
		programService.editProgram($scope.program, $scope);
    };
    $scope.getModelByCode = function ()
	 {
   	var code = $scope.program.code;
   	
   	code = code.replace(/\s/g, "");
   	//alert(code);
   	
   	if(code != null && /^[\d,]+$/.test(code))
   	{
		 $http.get("/agg/modelByCode/"+code)
		    .then(function(response) {
		    	if(response.data.data == null)
	    		{
		    		$scope.program.manufacturerDO.name=null;
	    			$scope.program.machineModelList=null;
					$('#programErrorMsg').html("No Model found or Model IDs does not belong to same Manufacturer");
	            	$('#programErrorMsg').removeClass('hidden');
	            	window.setTimeout(function() {
	        			 $('#programErrorMsg').addClass('hidden');
	        		}, 3000);
	    			//alert("No Model found or Model IDs does not belong to same Manufacturer");
	    		}
		    	
		    	 //alert(response.data.data.machineModelList[0].manufacturerDO.name);
		    	
		    	 $scope.program.manufacturerDO=response.data.data.machineModelList[0].manufacturerDO;
		         //$scope.machineModelList = response.data.data.machineModelList;
		    	 
		    	 var objects = response.data.data.machineModelList;
			        for (var i = 0; i < objects.length; i++) {
			        	
			        	//alert($scope.program.machineModelList.indexOf(objects[i]));
			        	
			        	var objects2 = $scope.program.machineModelList;
			        	for (var j = 0; j < objects2.length; j++) {
			        		
			        		//alert(objects[i].machineId);
			        		//alert(objects2[j].machineId);
			        		if(objects[i].machineId === objects2[j].machineId)
			        		{
			        			return;
			        		}
			        	
			        	}
			        	$scope.program.machineModelList.push(objects[i]) ;
			        	
			        }
		    	 
		    	 //$scope.program.machineModelList = response.data.data.machineModelList;
		         
		       
		    });
   	}
   	else
   		{
		   		$scope.program.manufacturerDO.name=null;
				$scope.program.machineModelList=null;
	    		$('#programErrorMsg').html("Please enter only comma seperated numeric value");
	        	$('#programErrorMsg').removeClass('hidden');
	        	window.setTimeout(function() {
	    			 $('#programErrorMsg').addClass('hidden');
	    		}, 3000);
   			//alert("Please enter only comma seperated numeric value");
   		}
	 }
    
	$scope.deleteProgram = function(programId) {
		//alert(dealerId);
		$http.delete("/agg/programs/"+programId)
	    .then(function(response) {
	        if (response.data.status == 'success') {
	        	$window.location.reload();
			}
	    });
    };
    
    
})


routingApp.controller('AddProgramsController', function($scope, $http) {
	$http.get("/agg/addPrograms")
    .then(function(response) {
        $scope.manufacturerList = response.data.data.manufacturerList;
        $scope.dealerList = response.data.data.dealerList;
    });
	
});

routingApp.controller('ProgramAsDealerController', function($scope, programService, $http) {
	//alert("in ProgramAsDealerController");
	
	$scope.coverageStartDateDisabled = true;
	$scope.mfgCoverageDisabled = true;
	$scope.coverageStartDateRequired = false;
	$scope.mfgCoverageRequired = false;
	
	
	
	//datepicker changes
	$scope.opens = [];
	$scope.dateOptions = {
		format: "MM/dd/yyyy",
	    showWeeks: false
	};
	
	$scope.coverageEndDatePickerIsOpen = false;
	$scope.coverageEndDatePickerOpen = function ($event) {
      if ($event) {
          $event.preventDefault();
          $event.stopPropagation(); // This is the magic
      }
      $scope.coverageEndDatePickerIsOpen = true;
    };
    
    $scope.estSaleDatePickerIsOpen = false;
    $scope.estSaleDatePickerOpen = function ($event) {
        if ($event) {
            $event.preventDefault();
            $event.stopPropagation(); // This is the magic
        }
        $scope.estSaleDatePickerIsOpen = true;
    };
	
	$http.get("/agg/programAsDealer")
    .then(function(response) {
    	//alert(response.data.data.programList);
        $scope.programList = response.data.data.programList;
        $scope.useOfEquipmentDOList = response.data.data.useOfEquipmentDOList;
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
		 
		 if(programDO.condition == 0){
			$scope.coverageStartDateDisabled = false;
			$scope.mfgCoverageDisabled = true;
			$scope.coverageStartDateRequired = true;
			$scope.mfgCoverageRequired = false;
		 }else if(programDO.condition == 1){
			$scope.coverageStartDateDisabled = true;
			$scope.mfgCoverageDisabled = false;
			$scope.coverageStartDateRequired = false;
			$scope.mfgCoverageRequired = true;
		 }

	 }
   
   $scope.submitProgramAsDel = function(programForm) {
	   if(programForm.$valid){
		   programService.submitProgramAsDealr($scope.program, $scope);
	   }
   };
});

/*routingApp.controller('GenerateClaimReportController', function($scope, $http) {
	 $http.get("/agg/generateClaimReport")
	    .then(function(response) {
	       //$scope.roleList = response.data.data;
	        //var reportData = response.data.data;
	       // var b = new Blob([reportData], { type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" });
	        //saveAs(b,"ReportFile.xls");
	    	
	    	var result = response.data.data;
	    	  if(result !== undefined || result !== '') {
	    		    var blob = new Blob([result], {
	    		      type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'
	    		    });
	    		    var objectUrl = URL.createObjectURL(blob);
	    		    saveAs(blob, 'Trek-Results.xlsx');
	    		  }
	    	
	    });
});

routingApp.controller('GenerateQuoteReportController', function($scope, $http) {
	 $http.post("/agg/generateQuoteReport")
	    .then(function(response) {
	       //$scope.roleList = response.data.data;
	        //var reportData = response.data.data;
	       // var b = new Blob([reportData], { type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" });
	        //saveAs(b,"ReportFile.xls");
	    	
	    	var result = response.data.data;
	    	  if(result !== undefined || result !== '') {
	    		    var blob = new Blob([result], {
	    		      type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'
	    		    });
	    		    var objectUrl = URL.createObjectURL(blob);
	    		    saveAs(blob, 'QuoteReport.xlsx');
	    		  }
	    	
	    });
});
*/
/*routingApp.controller('GenerateClaimReportController', function($scope, $http) {
	 $http({
		    url: '/agg/generateClaimReport',
		    method: 'POST',
		    responseType: 'arraybuffer',
		    data: json, //this is your json data string
		    headers: {
		        'Content-type': 'application/json',
		        'Accept': 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'
		    }
		})
	    .then(function(response) {
	       //$scope.roleList = response.data.data;
	        //var reportData = response.data.data;
	       // var b = new Blob([reportData], { type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" });
	        //saveAs(b,"ReportFile.xls");
	    	
	    	var result = response.data.data;
	    	  if(result !== undefined || result !== '') {
	    		    var blob = new Blob([result], {
	    		      type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'
	    		    });
	    		    var objectUrl = URL.createObjectURL(blob);
	    		    saveAs(blob, 'Trek-Results.xlsx');
	    		  }
	    	
	    });
});*/


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
    	
    	//TODO
    	/*$('#menu > ul.nav li a').click(function(e) {
    	    var $this = $(this);
    	    $this.parent().siblings().removeClass('active').end().addClass('active');
    	    e.preventDefault();
    	});*/
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
	//$scope.quote.custRemorsePeriod = true;
	//$scope.quote.custUnderstandCoverage = true;
	$scope.machineSerialFlag = true;
	$scope.machineModelChanged = true;
	
	var zeroHourExpirationHours = 2000;
	var maxExpirationHours = 5000;
	
	$scope.date = new Date();
	
	//datepicker changes
	
	$scope.valuationDatePickerIsOpen = false;
	$scope.opens = [];
	
	$scope.valuationDatePickerOpen = function ($event) {
      if ($event) {
          $event.preventDefault();
          $event.stopPropagation(); // This is the magic
      }
      $scope.valuationDatePickerIsOpen = true;
    };
    
    $scope.dateOptions = {
		format: "MM/dd/yyyy",
	    showWeeks: false
	};
	
	$http.get("/agg/quoteInfo")
	.then(function(response) {
		$scope.dealerList = response.data.data.dealerDOList;
		$scope.manufacturerList = response.data.data.manufacturerDOList;
		$scope.useOfEquipmentDOList = response.data.data.useOfEquipmentDOList;
		//$scope.machineTypeDOList = response.data.data.machineTypeDOList;
		
		if($scope.dealerList.length == 1){
			$scope.quote.dealerDO = $scope.dealerList[0];
		}
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
			if(index == 3){
				var rowIndex = $scope.selectedRow;
				var colIndex = $scope.selectedCloumn;
				if(rowIndex == null || colIndex == null || colIndex == 0 || colIndex == 1){
					alert("Please select at least one coverage level hours");
				}else{
					myTabs.goToTab(index);
				}
			}/*else if(index == 4){
				var cond = $scope.validateQuoteData();
				if(cond){
					myTabs.goToTab(index);
				}
			}*/else if(index != 5){
				myTabs.goToTab(index);
			}
			if(index == 1){
				quoteService.saveWarrantyInfo($scope.quote, $scope);
			}else if(index == 2){
				//saving machineInfo
				quoteService.saveMachineInfo($scope.quote, $scope);
				
				if($scope.machineModelChanged){
					$scope.machineModelChanged = false;
					//var coverageExpired = true;
					var coverageExpired = false;
					if($scope.quote.coverageExpired != null && $scope.quote.coverageExpired == true){
						coverageExpired = true;
					}/*else if($scope.quote.coverageEndDateUnknown != null && $scope.quote.coverageEndDateUnknown == true){
						coverageExpired = false;
					}else if($scope.quote.coverageEndDate != null && ($scope.quote.coverageEndDate > $scope.date)){
						coverageExpired = false;
					}else{
						coverageExpired = true;
					}*/
					
					var usageTierAdjFactor = 0;
					var oemWarrantyAdjFactor = 0;
					if(coverageExpired){
						if($scope.quote.meterHours != null && $scope.quote.meterHours > 0){
							$http.get("/agg/usagetier/adjfactor/"+$scope.quote.meterHours)
							.then(function(response) {
								usageTierAdjFactor = response.data.data.usageTierAdjFactor;
								$scope.coverageDetails(coverageExpired, usageTierAdjFactor);
							});
						}
					}else{
						var coverageEndDate = $scope.quote.coverageEndDate;
						var currDate = new Date();
						var months = $scope.monthDiff(currDate, coverageEndDate);
						$http.get("/agg/oemwarrantytier/adjfactor/"+months)
						.then(function(response) {
							oemWarrantyAdjFactor = response.data.data.oemWarrantyAdjFactor;
							$scope.coverageDetails(coverageExpired, oemWarrantyAdjFactor);
						});
					}
				}
				
			}else if(index == 3 || index == 4){
				if($scope.quote.coverageExpired != null && $scope.quote.coverageExpired == true){
					$scope.machineCondition = 'Used';
				}/*else if($scope.quote.coverageEndDateUnknown != null && $scope.quote.coverageEndDateUnknown == true){
					$scope.machineCondition = 'New';
				}else if($scope.quote.coverageEndDate != null && ($scope.quote.coverageEndDate > $scope.date)){
					$scope.machineCondition = 'New';
				}*/else{
					//$scope.machineCondition = 'Used';
					$scope.machineCondition = 'New';
				}
				
				$scope.quote.machineCondition = $scope.machineCondition;
				
				var rowIndex = $scope.selectedRow;
				var colIndex = $scope.selectedCloumn;
				
				if(rowIndex >= 0 && colIndex > 0){
					$scope.coverageHours = $scope.pricingDOList[rowIndex].coverageLevelHours;
					$scope.quoteBasePrice = (colIndex === 2)?$scope.pricingDOList[rowIndex].ptBasePrice:(colIndex === 3)?$scope.pricingDOList[rowIndex].phBasePrice:$scope.pricingDOList[rowIndex].plBasePrice;
					$scope.coverageType = (colIndex === 2)?"PT":(colIndex === 3)?"PH":"PL";
					$scope.coverageTypeDesc = (colIndex === 2)?"Powertrain":(colIndex === 3)?"Powertrain + Hydraulic":"Powertrain + Hydraulic + Platform";
				}else{
					$scope.coverageHours = 0;
					$scope.quoteBasePrice = 0;
					$scope.coverageType = null;
					$scope.coverageTypeDesc = null;
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
				
			}/*else if(index == 4){
				//saving Additional Unit information
				if($scope.quote.otherProv != null){
					quoteService.saveQuoteSummaryInfo($scope.quote, $scope);
				}
			}*/else if(index == 5){
				var cond = $scope.validateQuoteData();
				if(cond){
					//saving coverage information
					quoteService.savePurchanseInfo($scope.quote, $scope);
				}
			}
		}
		
	}
	
	$scope.coverageDetails = function(coverageExpired, adjustmentFactor){
		//var coverageExpired = ($scope.quote.coverageExpired != null && $scope.quote.coverageExpired == true)?true:false;
		var machineId = $scope.quote.machineInfoDO.machineId;
		$http.get("/agg/quote/coverageDeductInfo/"+coverageExpired+"/"+machineId)
		.then(function(response) {
			$scope.deductibleAmtList = response.data.data.deductibleAmtList;
			$scope.coverageTermList = response.data.data.coverageTermList;
			$scope.pricingDOList = response.data.data.pricingDOList;
			
			$scope.coverageTermSelected = $scope.coverageTermList[0];
			$scope.deductiblePriceSelected = $scope.deductibleAmtList[0];
			
			var dealerAdjFactor = $scope.quote.dealerDO.adjustmentFactor;
			var uoeAdjFactor = $scope.quote.useOfEquipmentDO.discount;
			var machineModelAdjFactor = $scope.quote.machineInfoDO.adjFactor;
			
			var adjFactor = parseFloat(dealerAdjFactor + uoeAdjFactor + machineModelAdjFactor + adjustmentFactor);
			
			
			//updating the coverage Expiration details.
			$scope.coverageExpirationList = [];
			if($scope.quote.estSaleDate != null){
				//var covrageStartDate = new Date($scope.quote.estSaleDate);
				angular.forEach($scope.coverageTermList, function(coverageTerm, key){
					//covrageStartDate = new Date($scope.quote.estSaleDate);
					//$scope.coverageExpirationList.push(new Date(new Date(covrageStartDate).setMonth(covrageStartDate.getMonth()+coverageTerm)));
					$scope.coverageExpirationList.push($scope.calExpirationDate(coverageExpired, coverageTerm));
			    });
			}
			
			if($scope.pricingDOList != null){
				angular.forEach($scope.pricingDOList, function(pricingDO, key){
					if(pricingDO.ptBasePrice > 0){
						pricingDO.ptBasePrice += (pricingDO.ptBasePrice * adjFactor);
					}
					if(pricingDO.phBasePrice > 0){
						pricingDO.phBasePrice += (pricingDO.phBasePrice * adjFactor);
					}
					if(pricingDO.plBasePrice > 0){
						pricingDO.plBasePrice += (pricingDO.plBasePrice * adjFactor);
					}
					if(coverageExpired){
						if(pricingDO.coverageLevelHours == 0){
							//Zero Hour Program setting as 2000
							pricingDO.coverageExpirationHours = zeroHourExpirationHours;
						}else{
							var totalExpHours = (parseInt($scope.quote.meterHours) + pricingDO.coverageLevelHours);
							pricingDO.coverageExpirationHours = (totalExpHours > maxExpirationHours)?maxExpirationHours:totalExpHours;
						}
					}else{
						pricingDO.coverageExpirationHours = (pricingDO.coverageLevelHours > maxExpirationHours)?maxExpirationHours:pricingDO.coverageLevelHours;
					}
					
			    });
			}
			
			/*if(($scope.quote.coverageTerm != null && ($scope.coverageTermSelected != $scope.quote.coverageTerm)) 
					|| ($scope.quote.deductiblePrice != null && ($scope.deductiblePriceSelected != $scope.quote.deductiblePrice))){
				$scope.getCoveragePriceLevels($scope.quote.deductiblePrice, $scope.quote.coverageTerm)
				
				var colIndex = 0;
				if($scope.coverageType != null){
					if($scope.coverageType == 'PT'){
						colIndex = 1;
					}else if($scope.coverageType == 'PH'){
						colIndex = 2;
					}else if($scope.coverageType == 'PL'){
						colIndex = 3;
					} 
				}
				var rowIndex = -1;
				angular.forEach($scope.pricingDOList, function(pricingDO, key){
					if($scope.coverageHours == pricingDO.coverageLevelHours){
						rowIndex = key;
					}
				});
				
				if(rowIndex >= 0 && colIndex > 0){
					$scope.setClickedCloumn(rowIndex, colIndex);
				}
				
			}*/
			
		});
	}
	
	$scope.calExpirationDate = function(coverageExpired, coverageTerm){
		var expDate = null;
		if(!coverageExpired){//New machine
			var manfCoverageTerm = 24;
			var finalCoverageTerm = (coverageTerm - manfCoverageTerm);
			expDate = $scope.quote.coverageEndDate;
			if(expDate != null){
				expDate = new Date($scope.quote.coverageEndDate);
				expDate = new Date(new Date(expDate).setMonth(expDate.getMonth()+finalCoverageTerm));
			}
		}else{//Old machine
			expDate = $scope.quote.estSaleDate;
			if(expDate != null){
				expDate = new Date($scope.quote.estSaleDate);
				expDate = new Date(new Date(expDate).setMonth(expDate.getMonth()+coverageTerm));
			}
		}
		
		return expDate;
	}
	
	$scope.monthDiff = function(d1, d2) {
	    var months;
	    months = (d2.getFullYear() - d1.getFullYear()) * 12;
	    months -= d1.getMonth() + 1;
	    months += d2.getMonth() + 1;
	    return months <= 0 ? 0 : months;
	}
	
	$scope.validateQuoteData = function(){
		var errorMsg = "";
		var serialNoFlag = false;
		var customerInfoFlag = false;
		var endDateFlag = false;
		if(!$scope.warrantyInfoForm.dealer.$valid){
			if(errorMsg == ""){
				errorMsg = "Please select a dealer \n";
			}else{
				errorMsg += "Please select a dealer \n";
			}
		}
		if($scope.quote.coverageExpired == null || ($scope.quote.coverageExpired != null && $scope.quote.coverageExpired == false)){
			if($scope.quote.coverageEndDate == null || $scope.quote.coverageEndDate == ""){
				endDateFlag = true;
				if(errorMsg == ""){
					errorMsg = "Please select a End Date of Manufacturer's Base Coverage \n";
				}else{
					errorMsg += "Please select a End Date of Manufacturer's Base Coverage \n";
				}
			}
		}
		
		if(!$scope.machineInfoForm.manufacturer.$valid){
			if(errorMsg == ""){
				errorMsg = "Please select a manufacturer \n";
			}else{
				errorMsg += "Please select a manufacturer \n";
			}
		}
		if(!$scope.machineInfoForm.machineType.$valid){
			if(errorMsg == ""){
				errorMsg = "Please select a machine type \n";
			}else{
				errorMsg += "Please select a machine type \n";
			}
		}
		if(!$scope.machineInfoForm.machineModel.$valid){
			if(errorMsg == ""){
				errorMsg = "Please select a machine model \n";
			}else{
				errorMsg += "Please select a machine model \n";
			}
		}
		serialNoFlag = false;
		if($scope.quote.serialNumber == null || $scope.quote.serialNumber == ""){
			serialNoFlag = true;
			if(errorMsg == ""){
				errorMsg = "Please select a serial number \n";
			}else{
				errorMsg += "Please select a serial number \n";
			}
		}else{
			serialNoFlag = false;
		}
		if(!$scope.machineInfoForm.meterHours.$valid){
			if(errorMsg == ""){
				errorMsg = "Please select a meter hours \n";
			}else{
				errorMsg += "Please select a meter hours \n";
			}
		}
		if(!$scope.machineInfoForm.equipment.$valid){
			if(errorMsg == ""){
				errorMsg = "Please select a use of equipment \n";
			}else{
				errorMsg += "Please select a use of equipment \n";
			}
		}
		if(!$scope.machineInfoForm.estSaleDate.$valid){
			if(errorMsg == ""){
				errorMsg = "Please select a estimated sale date \n";
			}else{
				errorMsg += "Please select a estimated sale date \n";
			}
		}
		if(!$scope.coverageInfoForm.dealerMarkup.$valid){
			if(errorMsg == ""){
				errorMsg = "Please select a dealer markup \n";
			}else{
				errorMsg += "Please select a dealer markup \n";
			}
		}
		var rowIndex = $scope.selectedRow;
		var colIndex = $scope.selectedCloumn;
		if(rowIndex == null || colIndex == null || colIndex == 0 || colIndex == 1){
			customerInfoFlag = true;
			if(errorMsg == ""){
				errorMsg = "Please select at least one coverage level hours \n";
			}else{
				errorMsg += "Please select at least one coverage level hours \n";
			}
		}
		if(!$scope.coverageInfoForm.dealerName.$valid){
			if(errorMsg == ""){
				errorMsg = "Please select a customer name \n";
			}else{
				errorMsg += "Please select a customer name \n";
			}
			
		}
		//customerInfoFlag = false;
		if($scope.quote.dealerAddress == null || $scope.quote.dealerAddress == ""){
			customerInfoFlag = true;
			if(errorMsg == ""){
				errorMsg = "Please select a customer address \n";
			}else{
				errorMsg += "Please select a customer address \n";
			}
		}
		if($scope.quote.dealerState == null || $scope.quote.dealerState == ""){
			customerInfoFlag = true;
			if(errorMsg == ""){
				errorMsg = "Please select a customer state \n";
			}else{
				errorMsg += "Please select a customer state \n";
			}
		}
		if($scope.quote.dealerZip == null || $scope.quote.dealerZip == ""){
			customerInfoFlag = true;
			if(errorMsg == ""){
				errorMsg = "Please select a customer zip \n";
			}else{
				errorMsg += "Please select a customer zip \n";
			}
		}
		/*if($scope.quote.dealerPhone == null || $scope.quote.dealerPhone == ""){
			customerInfoFlag = true;
			if(errorMsg == ""){
				errorMsg = "Please select a customer phone \n";
			}else{
				errorMsg += "Please select a customer phone \n";
			}
		}
		if($scope.quote.dealerEmail == null || $scope.quote.dealerEmail == ""){
			customerInfoFlag = true;
			if(errorMsg == ""){
				errorMsg = "Please select a customer email \n";
			}else{
				errorMsg += "Please select a customer email \n";
			}
		}*/
		if(!$scope.coverageInfoForm.custUnderstandCoverage.$valid){
			if(errorMsg == ""){
				errorMsg = "Please select a Customer understands coverage \n";
			}else{
				errorMsg += "Please select a Customer understands coverage \n";
			}
		}
		if(!$scope.coverageInfoForm.custRemorsePeriod.$valid){
			if(errorMsg == ""){
				errorMsg = "Please select a Customer is aware of 90-day remorse period \n";
			}else{
				errorMsg += "Please select a Customer is aware of 90-day remorse period \n";
			}
		}
		
		if(errorMsg != ""){
			if($window.confirm(errorMsg)) {
				if(!$scope.warrantyInfoForm.$valid || endDateFlag == true){
					myTabs.goToTab(0);
				}else if(!$scope.machineInfoForm.$valid || serialNoFlag == true){
					myTabs.goToTab(1);
				}else if(!$scope.coverageInfoForm.$valid || customerInfoFlag == true){
					//myTabs.goToTab(2);
					$scope.changeTab(2, $scope.machineInfoForm);
				}
			}
		}
		
		if(!$scope.warrantyInfoForm.$valid || !$scope.machineInfoForm.$valid || !$scope.coverageInfoForm.$valid || serialNoFlag == true || customerInfoFlag == true || endDateFlag == true){
			return false;
		}else{
			return true;
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
	
	$scope.getMachineType = function(manufacturerDO, machineTypeDO){
		//making coverage term values to empty on manufacturer change.
		//TODO need check whether need to use machineTypefield used in case of machineType changes
		$scope.machineModelChanged = true;
		$scope.coverageHours = 0;
		$scope.quoteBasePrice = 0;
		$scope.coverageType = null;
		$scope.coverageTypeDesc = null;
		$scope.selectedRow = null;
		$scope.selectedCloumn = null;
		$scope.machineTypeDOList = null;
		$scope.machineModelList = null;
		
		if(manufacturerDO != null){
			 $http.get("/agg/machineType/"+manufacturerDO.id)
			    .then(function(response) {
			    	$scope.machineTypeDOList = response.data.data.machineTypeDOList;
			    });
		}
	}
	
	$scope.getMachineModel = function(manufacturerDO, machineTypeDO){
		//making coverage term values to empty on manufacturer change.
		$scope.machineModelChanged = true;
		$scope.coverageHours = 0;
		$scope.quoteBasePrice = 0;
		$scope.coverageType = null;
		$scope.coverageTypeDesc = null;
		$scope.selectedRow = null;
		$scope.selectedCloumn = null;
		
		if(manufacturerDO != null && machineTypeDO != null){
			 $http.get("/agg/machineModel/"+manufacturerDO.id+"/"+machineTypeDO.id)
			    .then(function(response) {
			        $scope.machineModelList = response.data.data.machineModelList;
			    });
		}
	}
	
	$scope.changeMachineModelStatus = function(){
		//making coverage term values to empty on model change and expiration change.
		$scope.machineModelChanged = true;
		$scope.coverageHours = 0;
		$scope.quoteBasePrice = 0;
		$scope.coverageType = null;
		$scope.coverageTypeDesc = null;
		$scope.selectedRow = null;
		$scope.selectedCloumn = null;
	}
	
	$scope.setClickedCloumn = function(rowIndex, colIndex){
		var tbCellVal = (colIndex === 2)?$scope.pricingDOList[rowIndex].ptBasePrice:(colIndex === 3)?$scope.pricingDOList[rowIndex].phBasePrice:$scope.pricingDOList[rowIndex].plBasePrice;
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
		//var coverageExpired = ($scope.quote.coverageExpired != null && $scope.quoteBasePrice == true)?true:false;
		//var coverageExpired = true;
		var coverageExpired = false;
		if($scope.quote.coverageExpired != null && $scope.quote.coverageExpired == true){
			coverageExpired = true;
		}/*else if($scope.quote.coverageEndDateUnknown != null && $scope.quote.coverageEndDateUnknown == true){
			coverageExpired = false;
		}else if($scope.quote.coverageEndDate != null && ($scope.quote.coverageEndDate > $scope.date)){
			coverageExpired = false;
		}else{
			coverageExpired = true;
		}*/
		var machineId = $scope.quote.machineInfoDO.machineId;
		$scope.coverageTermSelected = coverageTerm;
		$scope.deductiblePriceSelected = deductiblePrice;
		
		var usageTierAdjFactor = 0;
		var oemWarrantyAdjFactor = 0;
		if(coverageExpired){
			if($scope.quote.meterHours != null && $scope.quote.meterHours > 0){
				$http.get("/agg/usagetier/adjfactor/"+$scope.quote.meterHours)
				.then(function(response) {
					usageTierAdjFactor = response.data.data.usageTierAdjFactor;
					$scope.coverageLevelPriceDetails(coverageExpired, machineId, deductiblePrice, coverageTerm, usageTierAdjFactor);
				});
			}
		}else{
			var coverageEndDate = $scope.quote.coverageEndDate;
			var currDate = new Date();
			var months = $scope.monthDiff(currDate, coverageEndDate);
			$http.get("/agg/oemwarrantytier/adjfactor/"+months)
			.then(function(response) {
				oemWarrantyAdjFactor = response.data.data.oemWarrantyAdjFactor;
				$scope.coverageLevelPriceDetails(coverageExpired, machineId, deductiblePrice, coverageTerm, oemWarrantyAdjFactor);
			});
		}
		  
	}
	
	$scope.coverageLevelPriceDetails = function(coverageExpired, machineId, deductiblePrice, coverageTerm, adjustmentFactor){
		
		$http.get("/agg/quote/coverageLevelPrice/"+coverageExpired+"/"+machineId+"/"+deductiblePrice+"/"+coverageTerm+"/-1")
		.then(function(response) {
			$scope.pricingDOList = response.data.data.pricingDOList;
			
			var dealerAdjFactor = $scope.quote.dealerDO.adjustmentFactor;
			var uoeAdjFactor = $scope.quote.useOfEquipmentDO.discount;
			var machineModelAdjFactor = $scope.quote.machineInfoDO.adjFactor;
			
			var adjFactor = parseFloat(dealerAdjFactor + uoeAdjFactor + machineModelAdjFactor + adjustmentFactor);
			
			if($scope.pricingDOList != null){
				angular.forEach($scope.pricingDOList, function(pricingDO, key){
					if(pricingDO.ptBasePrice > 0){
						pricingDO.ptBasePrice += (pricingDO.ptBasePrice * adjFactor);
					}
					if(pricingDO.phBasePrice > 0){
						pricingDO.phBasePrice += (pricingDO.phBasePrice * adjFactor);
					}
					if(pricingDO.plBasePrice > 0){
						pricingDO.plBasePrice += (pricingDO.plBasePrice * adjFactor);
					}
					
					if(coverageExpired){
						if(pricingDO.coverageLevelHours == 0){
							//Zero Hour Program setting as 2000
							pricingDO.coverageExpirationHours = zeroHourExpirationHours;
						}else{
							var totalExpHours = (parseInt($scope.quote.meterHours) + pricingDO.coverageLevelHours);
							pricingDO.coverageExpirationHours = (totalExpHours > maxExpirationHours)?maxExpirationHours:totalExpHours;
						}
					}else{
						pricingDO.coverageExpirationHours = (pricingDO.coverageLevelHours > maxExpirationHours)?maxExpirationHours:pricingDO.coverageLevelHours;
					}
					
			    });
			}
		});
	}
	
	$scope.resetMouseoverColumn = function(){
		$scope.mouseoverRow = "";
		$scope.mouseoverCloumn = "";
	}
	
	$scope.getDealerInfo = function(){
		$http.get("/agg/quote/userInfo/"+$scope.quote.dealerDO.id)
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
	
	$scope.changeMachineSerialFlag = function(serialNumberUnknown){
		if(serialNumberUnknown){
			$scope.machineSerialFlag = false;
		}else{
			$scope.machineSerialFlag = true;
		}
	}
});

routingApp.controller('ReportBugController', function($scope, $http, reportaBugService) {
	$scope.report={};
	$scope.date = new Date();
	$scope.report.discDate = new Date();
	$scope.report.FixByDate = new Date();
	
	//datepicker changes
	$scope.opens = [];
	$scope.dateOptions = {
		format: "MM/dd/yyyy",
	    showWeeks: false
	};
	
	$scope.discDatePickerIsOpen = false;
	$scope.discDatePickerOpen = function ($event) {
      if ($event) {
          $event.preventDefault();
          $event.stopPropagation(); // This is the magic
      }
      $scope.discDatePickerIsOpen = true;
    };
    
    $scope.fixByDatePickerIsOpen = false;
	$scope.fixByDatePickerOpen = function ($event) {
      if ($event) {
          $event.preventDefault();
          $event.stopPropagation(); // This is the magic
      }
      $scope.fixByDatePickerIsOpen = true;
    };
    
	$http.get("/agg/bugId")
    .then(function(response) {
    	//alert(response.data.data.bugId);
        $scope.bugId = response.data.data.bugId;
    });
	
	$scope.submitBug = function() {
		reportaBugService.saveBug($scope.report);
    };
	
});

routingApp.controller('BugInfoController', function($scope, $http, reportaBugService, $timeout) {
	
	//datepicker changes
	$scope.opens = [];
	$scope.dateOptions = {
		format: "MM/dd/yyyy",
	    showWeeks: false
	};
	
	$scope.discDatePickerIsOpen = false;
	$scope.discDatePickerOpen = function ($event) {
      if ($event) {
          $event.preventDefault();
          $event.stopPropagation(); // This is the magic
      }
      $scope.discDatePickerIsOpen = true;
    };
    
    $scope.fixByDatePickerIsOpen = false;
	$scope.fixByDatePickerOpen = function ($event) {
      if ($event) {
          $event.preventDefault();
          $event.stopPropagation(); // This is the magic
      }
      $scope.fixByDatePickerIsOpen = true;
    };
	
	 $http.get("/agg/getBugInfo")
	    .then(function(response) {
	        $scope.bugDOList = response.data.data;
	        $timeout(function () {
	        	$('#userTbl').DataTable({"lengthMenu": [[10, 25, 50, 100, -1], [10, 25, 50, 100, "All"]]});
	        }, 300);
	    });
	 
	 $scope.editBug = function(bugId) {
			//alert(bugId);
			$http.get("/agg/bug/"+bugId)
		    .then(function(response) {
		        $scope.bug = response.data.data.bug;
		        //alert(response.data.data.bug.description);
		    });
			
			
			
			//$scope.date = new Date();
			//$scope.bug.discDate = new Date($scope.bug.discDate);
			//$scope.bug.FixByDate = new Date($scope.bug.FixByDate);
			
			var x = screen.width/4;
		    var y = screen.height/9;
		    showMask('popup_mask');
		    $('#bugEditPopup').css("left", x+"px");
		    $('#bugEditPopup').css("top", y+"px");
		    $('#bugEditPopup').show();

		    
		    
		};
		
		$scope.editSubmitBug = function() {
			//alert("In submitMachine");
			reportaBugService.editBugInfo($scope.bug, $scope);
	    };
	    
	    
}).directive('convertToNumber', function() {
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

routingApp.controller('GetUserController', function($scope, userService, $http, $timeout) {
	$scope.user={};
	$http.get("/agg/userInfo")
	.then(function(response) {
        $scope.userList = response.data.data;
        $timeout(function () {
        	$('#userTbl').DataTable({"lengthMenu": [[10, 25, 50, 100, -1], [10, 25, 50, 100, "All"]]});
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

routingApp.controller('QuotesDetailController', function($scope, $http, $timeout, quoteType) {
	if(quoteType == 'All'){
		/*$http.get("/agg/quotesInfo")
		.then(function(response) {
	        $scope.quoteList = response.data.data;
	        $timeout(function () {
	        	$('#quotesTbl').DataTable({
	        		"aaSorting": [[ 6, "desc" ]],
	        		"lengthMenu": [[10, 25, 50, 100, -1], [10, 25, 50, 100, "All"]],
	     	       	columnDefs: [{ targets: 6, visible: false }],
	     	       "bDestroy": true
	     	    });
	        }, 300);
	    });*/
		$(document).ready(function() {
		    $('#quotesTbl').DataTable( {
		        "processing": true,
		        "serverSide": true,
		        "ajax": {
		            "url": "/agg/quotesInfo",
		            "type": "GET",
		            dataFilter: function(data) {
		                var json = jQuery.parseJSON( data );
		                json.recordsTotal = json.recordsTotal;
			            json.recordsFiltered = json.recordsFiltered;
			            json.data = json.data;
			            for ( var i=0, len=json.data.length; i<len ; i++ ) {
	        				json.data[i].viewDetails = '<div class="manage-sec"><a href="#/agg/viewQuote/'+json.data[i].id+'/'+json.data[i].quoteId+'"><img src="../assets/images/edit-pencil.png" alt="View" title="View"/></a></div>';	        				 
	        			 }
		                return JSON.stringify( json );
		            }},
		            "order": [[ 6, "desc" ]],
		            "lengthMenu": [[10, 25, 50, 100, -1], [10, 25, 50, 100, "All"]],
		        columnDefs: [{ targets: 6, visible: false },
	                { width: "12%", targets: 0 }
	            ],
	            "columns": [
        		    { "data": "quoteId" },
        		    { "data": "dealerName" },
        		    { "data": "dealerCustName" },
        		    { "data": "machineModel"},
        		    { "data": "machineSaleDate",
        		    	"type": "date ",
        		    	"render": function (data) {
        		            if (data !== null) {
        		                var date = new Date(data);
        		                date = date.getMonth() + 1 + "/" + date.getDate() + "/" + date.getFullYear();
        		                return date;
        		            } else {
        		                return "";
        		            }
        		        }},
        		    
             		{ "data": "statusDesc" },
        		    { "data": "createDate",
             			"type": "date ",
        		    	"render": function (data) {
        		            if (data !== null) {
        		                var date = new Date(data);
        		                date = date.getMonth() + 1 + "/" + date.getDate() + "/" + date.getFullYear();
        		                return date;
        		            } else {
        		                return "";
        		            }
        		        } },
        		    { "data": "viewDetails" }
        		  ]
		    } );
		} );
	}else if(quoteType == 'Archive'){
		$http.get("/agg/archivedQuotesInfo")
		.then(function(response) {
	        $scope.quoteList = response.data.data;
	        $timeout(function () {
	        	$('#quotesTbl').DataTable({
	        		"aaSorting": [[ 6, "desc" ]],
	        		"lengthMenu": [[10, 25, 50, 100, -1], [10, 25, 50, 100, "All"]],
	     	       	columnDefs: [{ targets: 6, visible: false }],
	     	       "bDestroy": true
	     	    });
	        }, 300);
	    });
	}
	
	
	/*$scope.viewQuote = function(id, quoteId){
		//alert(id+" "+quoteId);
		return '../../jsp/pendingDealers.jsp';
	}*/
})


/*routingApp.controller('ActiveContractController', function($scope, $http, $timeout) {
	$http.get("/agg/activeContracts")
	.then(function(response) {
		$scope.contractList = response.data.data.contractDOList;
        $timeout(function () {
        	$('#quotesTbl').DataTable();
        }, 300);
    });
	
})

routingApp.controller('InactiveContractController', function($scope, $http, $timeout) {
	$http.get("/agg/inactiveContracts")
	.then(function(response) {
		$scope.contractList = response.data.data.contractDOList;
        $timeout(function () {
        	$('#quotesTbl').DataTable();
        }, 300);
    });
	
})*/

routingApp.controller('ClaimsInfoController', function($scope, $http, $timeout, $window, $rootScope, claimsType) {
	//commented on Jan 3, 2017
	/*var getUser = function(func, claimId, status){
		$http.get("/agg/currentUserRole")
		   .then(function(response) {
		   	if(response.data.data.roleList){
		   		$rootScope.userType = response.data.data.roleList.accountType;
		   		if(typeof func === "function"){
		   			func(claimId, status);
		   		}
		   	}
	   });
	},
	redirectToEdit = function(claimId, status){
		if($rootScope.userType && $rootScope.userType === 'dealer' && (status === 5 || status === 7 || status === 9)){
			if(status === 5 || status === 7 || status === 9){
				$window.location = '#/agg/fileClaim/' + claimId;
			}
		}else if($rootScope.userType && $rootScope.userType === 'admin' && status !== 9 && status !== 1 && status !== 3){
			$window.location = '#/agg/fileClaim/' + claimId;
		}
	};*/
	
	$rootScope.draftClaimsFlag = false;
	if(claimsType === 'All' || claimsType === 'Print'){
		if(claimsType === 'Print'){
			$scope.printClaim = true;
		}
		$http.get("/agg/getClaimsInfo")
		.then(function(response) {
			showSpinner();
			$scope.claimDOList = response.data.data.claimDOList;
	        $timeout(function () {
	        	$('#claimsTbl').DataTable({
	        		"aaSorting": [[ 10, "desc" ]],
	        		"lengthMenu": [[10, 25, 50, 100, -1], [10, 25, 50, 100, "All"]],
	        	       columnDefs: [
	        	           { targets: 10, visible: false }    
	        	       ]
	        	    });
	        }, 300);
	        hideSpinner();
	    });
	}else if(claimsType === 'Approved'){
		$http.get("/agg/approvedClaims")
		.then(function(response) {
			showSpinner();
			 $scope.claimDOList = response.data.data.claimDOList;
	        $timeout(function () {
	        	$('#claimsTbl').DataTable({
	        		"aaSorting": [[ 10, "desc" ]],
	        		"lengthMenu": [[10, 25, 50, 100, -1], [10, 25, 50, 100, "All"]],
	        	       columnDefs: [
	        	           { targets: 10, visible: false }    
	        	       ]
	        	    });
	        }, 300);
	        hideSpinner();
	    });
	}else if(claimsType === 'Rejected'){
		$http.get("/agg/rejectedClaims")
		.then(function(response) {
			showSpinner();
			 $scope.claimDOList = response.data.data.claimDOList;
	        $timeout(function () {
	        	$('#claimsTbl').DataTable({
	        		"aaSorting": [[ 10, "desc" ]],
	        		"lengthMenu": [[10, 25, 50, 100, -1], [10, 25, 50, 100, "All"]],
	        	       columnDefs: [
	        	           { targets: 10, visible: false }    
	        	       ]
	        	    });
	        }, 300);
	        hideSpinner();
	    });
	}else if(claimsType === 'Draft'){
		$http.get("/agg/draftClaims")
		.then(function(response) {
			showSpinner();
			$scope.claimDOList = response.data.data.claimDOList;
			$rootScope.draftClaimsFlag = true;
	        $timeout(function () {
	        	$('#claimsTbl').DataTable({
	        		"aaSorting": [[ 10, "desc" ]],
	        		"lengthMenu": [[10, 25, 50, 100, -1], [10, 25, 50, 100, "All"]],
	        	       columnDefs: [
	        	           { targets: 10, visible: false }    
	        	       ]
	        	    });
	        }, 300);
	        hideSpinner();
	    });
	}
	
	
	//getUser();

	$scope.editClaimByDealer = function(claimId, status){
		//commented on Jan 3, 2017
/*//		if(!$scope.adminFlag){
//			if(status === 9 || status === 5 || status === 7){
//				$window.location = '#/agg/fileClaim/' + claimId;
//			}
//		}else{
//			if(status === 2 || status === 4 || status === 5 || status === 6 || status === 7 || status === 8 || status === 10){
//				$window.location = '#/agg/fileClaim/' + claimId;
//			}
//		}
		if($rootScope.userType){
			redirectToEdit(claimId, status);
		}else{
			getUser(redirectToEdit, claimId, status);
		}*/
		
		//added on Jan 3, 2017
		$window.location = '#/agg/fileClaim/' + claimId;
		
	};
			
	})

//commented on Jan 3, 2017
/*routingApp.controller('ApprovedClaimsController', function($scope, $http, $timeout, $window) {
	$http.get("/agg/approvedClaims")
	.then(function(response) {
		showSpinner();
		 $scope.claimDOList = response.data.data.claimDOList;
       $timeout(function () {
       	$('#claimsTbl').DataTable();
       }, 300);
       hideSpinner();
    });
})

routingApp.controller('RejectedClaimsController', function($scope, $http, $timeout, $window) {
	$http.get("/agg/rejectedClaims")
	.then(function(response) {
		showSpinner();
		 $scope.claimDOList = response.data.data.claimDOList;
       $timeout(function () {
       	$('#claimsTbl').DataTable();
       }, 300);
       hideSpinner();
    });
})*/
	
	


routingApp.controller('QuoteDetailController', function($scope, $http, $timeout, $routeParams, $route, $window, quoteService) {
	//alert($routeParams.quoteId+" - "+$routeParams.quoteCode);
	$scope.quote = {};
	$scope.date = new Date();
	$scope.disabled= true;
	$scope.purchaseRequested = true;
	$scope.invoiced = true;
	$scope.readOnlyFlag = false;
	$scope.editableFlag = true;
	$scope.contractBtnFlag = false;
	$scope.estPriceFlag = true;
	$scope.mandatoryFlag = true;
	$scope.expirationFlag = true;
	
	var zeroHourExpirationHours = 2000;
	var maxExpirationHours = 5000;
	
	//datepicker changes

	$scope.opens = [];
	
	$scope.dateOptions = {
		format: "MM/dd/yyyy",
	    showWeeks: false
	};
	
	$scope.valuationDatePickerIsOpen = false;
	$scope.valuationDatePickerOpen = function ($event) {
      if ($event) {
          $event.preventDefault();
          $event.stopPropagation(); // This is the magic
      }
      $scope.valuationDatePickerIsOpen = true;
    };
    
    $scope.estSaleDatePickerIsOpen = false;
    $scope.estSaleDatePickerOpen = function ($event) {
        if ($event) {
            $event.preventDefault();
            $event.stopPropagation(); // This is the magic
        }
        $scope.estSaleDatePickerIsOpen = true;
    };
    
    $scope.inceptionDatePickerIsOpen = false;
    $scope.inceptionDatePickerOpen = function ($event) {
        if ($event) {
            $event.preventDefault();
            $event.stopPropagation(); // This is the magic
        }
        $scope.inceptionDatePickerIsOpen = true;
    };
    
    $scope.expirationDatePickerIsOpen = false;
    $scope.expirationDatePickerOpen = function ($event) {
        if ($event) {
            $event.preventDefault();
            $event.stopPropagation(); // This is the magic
        }
        $scope.expirationDatePickerIsOpen = true;
    };
    
    $scope.receivedDatePickerIsOpen = false;
    $scope.receivedDatePickerOpen = function ($event) {
        if ($event) {
            $event.preventDefault();
            $event.stopPropagation(); // This is the magic
        }
        $scope.receivedDatePickerIsOpen = true;
    };
    
    $scope.contractInceptionDatePickerIsOpen = false;
    $scope.contractInceptionDatePickerOpen = function ($event) {
        if ($event) {
            $event.preventDefault();
            $event.stopPropagation(); // This is the magic
        }
        $scope.contractInceptionDatePickerIsOpen = true;
    };
    
    $scope.contractExpirationDatePickerIsOpen = false;
    $scope.contractExpirationDatePickerOpen = function ($event) {
        if ($event) {
            $event.preventDefault();
            $event.stopPropagation(); // This is the magic
        }
        $scope.contractExpirationDatePickerIsOpen = true;
    };
	
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
		if( $scope.quote.machineInfoDO != null){
			$scope.quote.adjustedLol = $scope.quote.machineInfoDO.lol;
		}
		$scope.quote.adjustedBasePrice = $scope.quote.quoteBasePrice;
		
		
		$scope.quote.dealerMarkupTypee = $scope.quote.dealerMarkupType;
		if($scope.quote.coverageEndDate != null){
			$scope.quote.coverageEndDate = new Date($scope.quote.coverageEndDate);
		}
		if($scope.quote.estSaleDate != null){
			$scope.quote.estSaleDate = new Date($scope.quote.estSaleDate);
		}
		
		$scope.quote.lastUpdate = new Date($scope.quote.lastUpdate);
		
		if($scope.quote.status == 4){
			$scope.purchaseRequested = false;
		}else if($scope.quote.status == 5){
			$scope.invoiced = false;
		}else if($scope.quote.status == 1){
			$scope.estPriceFlag = false;
			$scope.mandatoryFlag = false;
			$scope.expirationFlag = false;
		}
		
		if($scope.quote.dealerMarkupType == null){
			$scope.quote.dealerMarkupType = "price";
			$scope.quote.dealerMarkupTypee = "price";
		}
		
		$scope.getDealerMarkupPrice();
		
        if($scope.quote.coverageExpired != null && $scope.quote.coverageExpired == true){
    		$scope.machineCondition = 'Used';
    		$scope.expirationFlag = false;
    	}/*else if($scope.quote.coverageEndDateUnknown != null && $scope.quote.coverageEndDateUnknown == true){
    		$scope.machineCondition = 'New';
		}else if($scope.quote.coverageEndDate != null && ($scope.quote.coverageEndDate > $scope.date)){
    		$scope.machineCondition = 'New';
    	}*/else{
    		//$scope.machineCondition = 'Used';
    		$scope.machineCondition = 'New';
    		if($scope.quote.status == 1){
    			$scope.expirationFlag = false;
    		}else{
    			$scope.expirationFlag = true;
    		}
    	}
        
        /*$scope.quote.inceptionDate = new Date();
        if($scope.quote.status > 1 && $scope.quote.admin){
        	$scope.calExpirationDate();
        }*/
        
        if($scope.quote.inceptionDate != null){
        	$scope.quote.inceptionDate = new Date($scope.quote.inceptionDate);
        }
        
        if($scope.quote.expirationDate != null){
        	$scope.quote.expirationDate = new Date($scope.quote.expirationDate);
        }
    });
	
	$scope.calExpirationDate = function(){
		if($scope.machineCondition == "New"){
			var manfCoverageTerm = 0;
			if($scope.quote.coverageType == 'PT'){
				manfCoverageTerm = parseInt($scope.quote.powerTrainMonths);
			}else if($scope.quote.coverageType == 'PH'){
				manfCoverageTerm = parseInt($scope.quote.hydraulicsMonths);
			}else if($scope.quote.coverageType == 'PL'){
				manfCoverageTerm = parseInt($scope.quote.fullMachineMonths);
			}
			var finalCoverageTerm = (parseInt($scope.quote.adjustedcoverageTerm) - manfCoverageTerm);
			var expDate = $scope.quote.coverageEndDate;
			if(expDate != null){
				expDate = new Date($scope.quote.coverageEndDate);
				expDate = new Date(new Date(expDate).setMonth(expDate.getMonth()+finalCoverageTerm));
			}
			$scope.quote.expirationDate = expDate;
			var expirationHours = parseInt($scope.quote.adjustedCoverageHours);
			if(expirationHours > maxExpirationHours){
				$scope.quote.expirationHours = maxExpirationHours;
			}else{
				$scope.quote.expirationHours = expirationHours;
			}
		}else{
			var expDate = new Date();
			if($scope.quote.inceptionDate != null){
				expDate = new Date($scope.quote.inceptionDate);
			}
			expDate = new Date(new Date(expDate).setMonth(expDate.getMonth()+parseInt($scope.quote.adjustedcoverageTerm)));
			$scope.quote.expirationDate = expDate;
			var coverageHours = parseInt($scope.quote.adjustedCoverageHours);
			if(coverageHours == 0){
				$scope.quote.expirationHours = zeroHourExpirationHours;
			}else{
				var expirationHours = parseInt($scope.quote.meterHours) + coverageHours;
				if(expirationHours > maxExpirationHours){
					$scope.quote.expirationHours = maxExpirationHours;
				}else{
					$scope.quote.expirationHours = expirationHours;
				}
			}
			
		}
	}
	
	$scope.changeMandatoryFlg = function(status){
		if(status == 1){
			$scope.mandatoryFlag = false;
			$scope.expirationFlag = false;
		}else{
			$scope.mandatoryFlag = true;
			if($scope.quote.coverageExpired != null && $scope.quote.coverageExpired == true){
				$scope.expirationFlag = false;
			}else{
				$scope.expirationFlag = true;
			}
			/*if($scope.quote.admin){
				$scope.calExpirationDate();
			}*/
			
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
	
	$scope.getCoverageDetails = function (machineInfoDO){
		//var coverageExpired = true;
		var coverageExpired = false;
		$scope.machineCondition = 'New';
		if($scope.quote.coverageExpired != null && $scope.quote.coverageExpired == true){
			coverageExpired = true;
			$scope.machineCondition = 'Used';
			$scope.expirationFlag = false;
		}else{
			if($scope.quote.status == 1){
				$scope.expirationFlag = false;
			}else{
				$scope.expirationFlag = true;
			}
		}
		
		/*else if($scope.quote.coverageEndDate != null && ($scope.quote.coverageEndDate > $scope.date)){
			coverageExpired = false;
		}else{
			coverageExpired = true;
		}*/
		
		if($scope.quote.status > 1 && $scope.quote.admin){
			$scope.calExpirationDate();
		}
		
		$scope.quote.groupId = machineInfoDO.groupId;
		$scope.quote.adjustedLol = machineInfoDO.lol;
		$scope.quote.machineInfoDO.lolToDisplay = machineInfoDO.lol;
		
		var machineId = machineInfoDO.machineId;
		$http.get("/agg/quote/coverageDeductInfo/"+coverageExpired+"/"+machineId)
		.then(function(response) {
			$scope.deductibleAmtList = response.data.data.deductibleAmtList;
			$scope.coverageTermList = response.data.data.coverageTermList;
			$scope.pricingDOList = response.data.data.pricingDOList;
			$scope.coverageLevelHoursList = response.data.data.coverageLevelHoursList;
			
			$scope.quote.coverageTerm = $scope.coverageTermList[0];
			$scope.quote.deductiblePrice = $scope.deductibleAmtList[0];
			$scope.quote.coverageHours = "";
			$scope.quote.coverageType = "";
		});
	}
	
	$scope.getCoveragePriceLevels = function(type){
		//var coverageExpired = true;
		var coverageExpired = false;
		if($scope.quote.coverageExpired != null && $scope.quote.coverageExpired == true){
			coverageExpired = true;
		}/*else if($scope.quote.coverageEndDate != null && ($scope.quote.coverageEndDate > $scope.date)){
			coverageExpired = false;
		}else{
			coverageExpired = true;
		}*/
		var machineId = $scope.quote.machineInfoDO.machineId;
		var deductiblePrice = $scope.quote.deductiblePrice;
		var coverageTerm = $scope.quote.coverageTerm;
		if(coverageTerm != null && coverageTerm != ""){
			$scope.quote.adjustedcoverageTerm = coverageTerm;
			if($scope.quote.status > 1 && $scope.quote.admin){
				$scope.calExpirationDate();
			}
		}
		var coverageHrs = -1;
		if(type == 'coverageHours' && $scope.quote.coverageHours != null && $scope.quote.coverageHours != ""){
			coverageHrs = $scope.quote.coverageHours;
			$scope.quote.adjustedCoverageHours = $scope.quote.coverageHours;
			if($scope.quote.status > 1 && $scope.quote.admin){
				$scope.calExpirationDate();
			}
		}
		$scope.quote.coverageTypeSet = [];
		$scope.quote.coverageType = "";
		if(coverageHrs == -1){
			$scope.quote.coverageHours = "";
		}
		
		var usageTierAdjFactor = 0;
		var oemWarrantyAdjFactor = 0;
		if(coverageExpired){
			if($scope.quote.meterHours != null && $scope.quote.meterHours > 0){
				$http.get("/agg/usagetier/adjfactor/"+$scope.quote.meterHours)
				.then(function(response) {
					usageTierAdjFactor = response.data.data.usageTierAdjFactor;
					$scope.coverageLevelPriceDetails(coverageExpired, machineId, deductiblePrice, coverageTerm, coverageHrs, usageTierAdjFactor);
				});
			}
		}else{
			var coverageEndDate = $scope.quote.coverageEndDate;
			var currDate = new Date();
			var months = $scope.monthDiff(currDate, coverageEndDate);
			$http.get("/agg/oemwarrantytier/adjfactor/"+months)
			.then(function(response) {
				oemWarrantyAdjFactor = response.data.data.oemWarrantyAdjFactor;
				$scope.coverageLevelPriceDetails(coverageExpired, machineId, deductiblePrice, coverageTerm, coverageHrs, oemWarrantyAdjFactor);
			});
		}
		
	}
	
	$scope.monthDiff = function(d1, d2) {
	    var months;
	    months = (d2.getFullYear() - d1.getFullYear()) * 12;
	    months -= d1.getMonth() + 1;
	    months += d2.getMonth() + 1;
	    return months <= 0 ? 0 : months;
	}
	
	$scope.coverageLevelPriceDetails = function(coverageExpired, machineId, deductiblePrice, coverageTerm, coverageHrs, adjustmentFactor){
		$http.get("/agg/quote/coverageLevelPrice/"+coverageExpired+"/"+machineId+"/"+deductiblePrice+"/"+coverageTerm+"/"+coverageHrs)
		.then(function(response) {
			$scope.pricingDOList = response.data.data.pricingDOList;
			if(coverageHrs == -1){
				$scope.coverageLevelHoursList = response.data.data.coverageLevelHoursList;
			}
			
			var dealerAdjFactor = $scope.quote.dealerDO.adjustmentFactor;
			var uoeAdjFactor = $scope.quote.useOfEquipmentDO.discount;
			var machineModelAdjFactor = $scope.quote.machineInfoDO.adjFactor;
			
			var adjFactor = parseFloat(dealerAdjFactor + uoeAdjFactor + machineModelAdjFactor + adjustmentFactor);
			
			var phBasePriceCond = true;
			var plBasePriceCond = true;
			var ptBasePriceCond = true;
			angular.forEach($scope.pricingDOList, function(pricingDO, key){
				if(pricingDO.phBasePrice > 0 && phBasePriceCond){
					$scope.quote.coverageTypeSet.push("PH");
					pricingDO.phBasePrice += (pricingDO.phBasePrice * adjFactor);
					phBasePriceCond = false;
				}
				if(pricingDO.plBasePrice > 0 && plBasePriceCond){
					$scope.quote.coverageTypeSet.push("PL");
					pricingDO.plBasePrice += (pricingDO.plBasePrice * adjFactor);
					plBasePriceCond = false;
				}
				if(pricingDO.ptBasePrice > 0 && ptBasePriceCond){
					$scope.quote.coverageTypeSet.push("PT");
					pricingDO.ptBasePrice += (pricingDO.ptBasePrice * adjFactor);
					ptBasePriceCond = false;
				}
		    });
		});  
	}
	
	$scope.changeExpirationDate = function(){
		if($scope.quote.status > 1 && $scope.quote.admin){
			$scope.calExpirationDate();
		}
	}
	
	$scope.changeQuoteBasePrice = function(coverageType){
		angular.forEach($scope.pricingDOList, function(pricingDO, key){
			if(coverageType == "PH"){
				$scope.quote.quoteBasePrice = pricingDO.phBasePrice;
			}else if(coverageType == "PL"){
				$scope.quote.quoteBasePrice = pricingDO.plBasePrice;
			}else if(coverageType == "PT"){
				$scope.quote.quoteBasePrice = pricingDO.ptBasePrice;
			}
			$scope.quote.quoteBasePriceToDisplay = $scope.quote.quoteBasePrice;
			$scope.quote.adjustedBasePrice = $scope.quote.quoteBasePrice;
		});
		
		$scope.quote.adjustedCoverageType = coverageType;
		
		$scope.getDealerMarkupPrice();
	}
	
	$scope.getDealerMarkupPrice = function(){
		if($scope.quote.dealerMarkupType == 'price'){
			$scope.quote.dealerMarkupPrice = parseInt($scope.quote.dealerMarkup);
		}else{
			$scope.quote.dealerMarkupPrice = parseInt((($scope.quote.quoteBasePrice * parseInt($scope.quote.dealerMarkup))/100));
		}
	}
	
	$scope.editQuote = function(){
		$scope.disabled= false;
		$scope.readOnlyFlag = true;
		$scope.editableFlag = false;
	}
	
	$scope.updateQuote = function(quoteForm){
		//alert(quoteForm.$valid);
		$timeout(function () {
			if(quoteForm.$valid){
				quoteService.updateQuote($scope.quote, $scope);
			}
		}, 10);
	}
	
	$scope.changePurchaseMandatoryFlg = function(){
		//changing purchase request mandatory flag
		$scope.changeMandatoryFlg(4);
		
		return $scope.quoteInfoForm.$valid;
	}
	
	$scope.changeEstMandatoryFlg = function(){
		//changing mandatory flag
		$scope.changeMandatoryFlg($scope.quote.status);
		
		return $scope.quoteInfoForm.$valid;
	}
	
	$scope.purchaseQuote = function(quoteForm){
		$timeout(function () {
			if(quoteForm.$valid){
				$scope.quote.status=4;
				quoteService.updateQuote($scope.quote, $scope);
			}
		}, 10);
	}
	
	$scope.cancelQuote = function(){
		$window.location.href = '#/agg/quotes';
	}
	
	$scope.archiveQuote = function(){
		if($window.confirm('Are you sure you want to archive this record?')) {
			if($scope.quote != null){
				quoteService.archiveQuote($scope.quote, $scope);
			}
		}
	}
	
	$scope.unArchiveQuote = function(){
		if($window.confirm('Are you sure you want to un-archive this record?')) {
			if($scope.quote != null){
				quoteService.unArchiveQuote($scope.quote, $scope);
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
			$scope.quote.contractInceptionDate = new Date($scope.quote.inceptionDate);
			$scope.quote.contractExpirationDate = new Date($scope.quote.expirationDate);
			$scope.quote.contractExpirationHours = $scope.quote.expirationHours;
			
			$scope.quote.checkDOList = [];
			$scope.quote.checkDOList.push({});
			
			var x = screen.width/4;
		    var y = screen.height/9;
		    showMask('popup_mask');
		    $('#contractCreatePopup').css("left", x+"px");
		    $('#contractCreatePopup').css("top", y+"px");
		    $('#contractCreatePopup').show();
		}
	}
	
	$scope.chkDatePickerOpen = function ($event, checkDO) {
	  checkDO.chkDatePickerIsOpen = false;
      if ($event) {
          $event.preventDefault();
          $event.stopPropagation(); // This is the magic
      }
      checkDO.chkDatePickerIsOpen = true;
    }
	
	$scope.addCheck = function(){
		$scope.quote.checkDOList.push({});
	}
	
	$scope.removeCheck = function(checkDO){
		var index = $scope.quote.checkDOList.indexOf(checkDO);
		$scope.quote.checkDOList.splice(index, 1);
		$scope.calcCheckAmtTotal();
    }
    
    $scope.calcCheckAmtTotal = function(){
		$scope.quote.totalCheckAmount = 0;
		angular.forEach($scope.quote.checkDOList, function(checkDO, index){
			$scope.quote.totalCheckAmount += checkDO.amount;
		});
	}
	
	$scope.updateContractExpirationDate = function(){
		if($scope.machineCondition == "New"){
			var manfCoverageTerm = 0;
			if($scope.quote.coverageType == 'PT'){
				manfCoverageTerm = parseInt($scope.quote.powerTrainMonths);
			}else if($scope.quote.coverageType == 'PH'){
				manfCoverageTerm = parseInt($scope.quote.hydraulicsMonths);
			}else if($scope.quote.coverageType == 'PL'){
				manfCoverageTerm = parseInt($scope.quote.fullMachineMonths);
			}
			var finalCoverageTerm = (parseInt($scope.quote.adjustedcoverageTerm) - manfCoverageTerm);
			var expDate = $scope.quote.coverageEndDate;
			if(expDate != null){
				expDate = new Date($scope.quote.coverageEndDate);
				expDate = new Date(new Date(expDate).setMonth(expDate.getMonth()+finalCoverageTerm));
			}
			
			$scope.quote.contractExpirationDate = expDate;
			var expirationHours = parseInt($scope.quote.adjustedCoverageHours);
			if(expirationHours > maxExpirationHours){
				$scope.quote.contractExpirationHours = maxExpirationHours;
			}else{
				$scope.quote.contractExpirationHours = expirationHours;
			}
		}else{
			var expDate = new Date();
			if($scope.quote.contractInceptionDate != null){
				expDate = new Date($scope.quote.contractInceptionDate);
			}
			expDate = new Date(new Date(expDate).setMonth(expDate.getMonth()+parseInt($scope.quote.adjustedcoverageTerm)));
			$scope.quote.contractExpirationDate = expDate;
			var coverageHours = parseInt($scope.quote.adjustedCoverageHours);
			if(coverageHours == 0){
				$scope.quote.contractExpirationHours = zeroHourExpirationHours;
			}else{
				var expirationHours = parseInt($scope.quote.meterHours) + coverageHours;
				if(expirationHours > maxExpirationHours){
					$scope.quote.contractExpirationHours = maxExpirationHours;
				}else{
					$scope.quote.contractExpirationHours = expirationHours;
				}
			}
		}
	}
	
	$scope.printQuote = function(quotePrintType){
		if(quotePrintType == 'dealer'){
			$window.open('/agg/quote/report/dealer/'+$scope.quote.quoteId);
		}else if(quotePrintType == 'customer'){
			$window.open('/agg/quote/report/customer/'+$scope.quote.quoteId);
		}else if(quotePrintType == 'invoice'){
			$window.open('/agg/quote/report/invoice/'+$scope.quote.quoteId);
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
	
    $scope.submitCreateContract = function(){
    	//alert("in submitCreateContract");
    	quoteService.createContract($scope.quote, $scope);
    }
    
    $scope.updateAdjustedPrice = function(adjustedPrice){
    	$scope.quote.quoteBasePrice = parseInt(adjustedPrice);
    	$scope.getDealerMarkupPrice();
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

routingApp.controller('ContractsController', function($scope, $http, $timeout, $window) {
	$http.get("/agg/contractsInfo")
	.then(function(response) {
        $scope.contractList = response.data.data.contractDOList;
        $timeout(function () {
        	$('#contractsTbl').DataTable({
        		"aaSorting": [[ 9, "desc" ]],
        		 "lengthMenu": [[10, 25, 50, 100, -1], [10, 25, 50, 100, "All"]],
	     	     columnDefs: [{ targets: 9, visible: false }],
	     	     "bDestroy": true
     	    });
        }, 300);
    });
})

routingApp.controller('ContractDetailController', function($scope, $http, $timeout, $window, $routeParams, contractService) {
	$scope.contract = {};
	
	//datepicker changes
	
	$scope.opens = [];
    
    $scope.dateOptions = {
		format: "MM/dd/yyyy",
	    showWeeks: false
	};
    
    var maxExpirationHours = 5000;
    var zeroHourExpirationHours = 2000;
    
    $scope.inceptionDatePickerIsOpen = false;
    $scope.inceptionDatePickerOpen = function ($event) {
        if ($event) {
            $event.preventDefault();
            $event.stopPropagation(); // This is the magic
        }
        $scope.inceptionDatePickerIsOpen = true;
    };
    
    $scope.expirationDatePickerIsOpen = false;
    $scope.expirationDatePickerOpen = function ($event) {
        if ($event) {
            $event.preventDefault();
            $event.stopPropagation(); // This is the magic
        }
        $scope.expirationDatePickerIsOpen = true;
    };
    
    $scope.receivedDatePickerIsOpen = false;
    $scope.receivedDatePickerOpen = function ($event) {
        if ($event) {
            $event.preventDefault();
            $event.stopPropagation(); // This is the magic
        }
        $scope.receivedDatePickerIsOpen = true;
    };
	
	$http.get("/agg/contractInfo/"+$routeParams.contractId+"/"+$routeParams.contractCode)
	.then(function(response) {
        $scope.contract = response.data.data.contractDO;
        $scope.dealerDOList = response.data.data.dealerDOList;
        $scope.contract.inceptionDate = new Date($scope.contract.inceptionDate);
        $scope.contract.expirationDate = new Date($scope.contract.expirationDate);
        $scope.calcCheckAmtTotal();
        if($scope.contract.checkDOList == null){
        	$scope.contract.checkDOList = [];
        }
    });
	
	$scope.updateContract = function(contractInfoForm){
		if(contractInfoForm.$valid){
			contractService.updateContract($scope.contract);
		}
	}
	
	$scope.validateExpirationUsageHours = function(expUsageHrs){
		if(expUsageHrs > maxExpirationHours){
			$scope.contract.expirationUsageHours = maxExpirationHours;
		}
	}
	
	$scope.printQuote = function(quotePrintType){
		if(quotePrintType == 'dealer'){
			$window.open('/agg/quote/report/dealer/'+$scope.contract.quoteDO.quoteId);
		}else if(quotePrintType == 'customer'){
			$window.open('/agg/quote/report/customer/'+$scope.contract.quoteDO.quoteId);
		}else if(quotePrintType == 'invoice'){
			$window.open('/agg/quote/report/invoice/'+$scope.contract.quoteDO.quoteId);
		}
	}
	
	$scope.printContract = function(contractPrintType){
		if(contractPrintType == 'contract'){
			$window.open('/agg/contract/report/contract/'+$scope.contract.id+'/'+$scope.contract.contractId);
		}else if(contractPrintType == 'coverage'){
			$window.open('/agg/contract/report/coverage/'+$scope.contract.id+'/'+$scope.contract.contractId);
		}
	}
	
	$scope.chkDatePickerOpen = function ($event, checkDO) {
	  checkDO.chkDatePickerIsOpen = false;
      if ($event) {
          $event.preventDefault();
          $event.stopPropagation(); // This is the magic
      }
      checkDO.chkDatePickerIsOpen = true;
    }
	
	$scope.addCheck = function(){
		$scope.contract.checkDOList.push({});
	}
	
	$scope.removeCheck = function(checkDO){
		var index = $scope.contract.checkDOList.indexOf(checkDO);
		$scope.contract.checkDOList.splice(index, 1);
		$scope.calcCheckAmtTotal();
    }
    
    $scope.calcCheckAmtTotal = function(){
		$scope.contract.totalCheckAmount = 0;
		angular.forEach($scope.contract.checkDOList, function(checkDO, index){
			$scope.contract.totalCheckAmount += checkDO.amount;
		});
	}
    
    $scope.calExpirationDate = function(){
		if($scope.contract.quoteDO.coverageExpired === false){
			var manfCoverageTerm = 0;
			if($scope.contract.coverageType == 'PT'){
				manfCoverageTerm = parseInt($scope.contract.quoteDO.powerTrainMonths);
			}else if($scope.contract.coverageType == 'PH'){
				manfCoverageTerm = parseInt($scope.contract.quoteDO.hydraulicsMonths);
			}else if($scope.contract.coverageType == 'PL'){
				manfCoverageTerm = parseInt($scope.contract.quoteDO.fullMachineMonths);
			}
			var finalCoverageTerm = (parseInt($scope.contract.coverageTermMonths) - manfCoverageTerm);
			var expDate = $scope.quote.coverageEndDate;
			if(expDate != null){
				expDate = new Date($scope.quote.coverageEndDate);
				expDate = new Date(new Date(expDate).setMonth(expDate.getMonth()+finalCoverageTerm));
			}
			$scope.contract.expirationDate = expDate;
			var expirationHours = parseInt($scope.contract.coverageLevelHours);
			if(expirationHours > maxExpirationHours){
				$scope.contract.expirationUsageHours = maxExpirationHours;
			}else{
				$scope.contract.expirationUsageHours = expirationHours;
			}
			
		}else{
			var expDate = new Date();
			if($scope.contract.inceptionDate != null){
				expDate = new Date($scope.contract.inceptionDate);
			}
			expDate = new Date(new Date(expDate).setMonth(expDate.getMonth()+parseInt($scope.contract.coverageTermMonths)));
			$scope.contract.expirationDate = expDate;
			var coverageHours = parseInt($scope.contract.coverageLevelHours);
			if(coverageHours == 0){
				$scope.contract.expirationUsageHours = zeroHourExpirationHours;
			}else{
				var expirationHours = parseInt($scope.contract.quoteDO.meterHours) + coverageHours;
				if(expirationHours > maxExpirationHours){
					$scope.contract.expirationUsageHours = maxExpirationHours;
				}else{
					$scope.contract.expirationUsageHours = expirationHours;
				}
			}
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



routingApp.controller('ContractViewDetailController', function($scope, $http, $timeout, $window, $routeParams, contractService) {
	$http.get("/agg/contractInfo/"+$routeParams.contractId+"/"+$routeParams.contractCode)
	.then(function(response) {
        $scope.contract = response.data.data.contractDO;
        $scope.calcCheckAmtTotal();
    });
	
	$scope.cancelContract = function(){
		$window.location.href = '#/agg/contracts';
	}
	
	$scope.printContract = function(contractPrintType){
		if(contractPrintType == 'contract'){
			$window.open('/agg/contract/report/contract/'+$scope.contract.id+'/'+$scope.contract.contractId);
		}else if(contractPrintType == 'coverage'){
			$window.open('/agg/contract/report/coverage/'+$scope.contract.id+'/'+$scope.contract.contractId);
		}
	}
	
	$scope.printQuote = function(quotePrintType){
		if(quotePrintType == 'dealer'){
			$window.open('/agg/quote/report/dealer/'+$scope.contract.quoteDO.quoteId);
		}else if(quotePrintType == 'customer'){
			$window.open('/agg/quote/report/customer/'+$scope.contract.quoteDO.quoteId);
		}else if(quotePrintType == 'invoice'){
			$window.open('/agg/quote/report/invoice/'+$scope.contract.quoteDO.quoteId);
		}
	}
	
	$scope.calcCheckAmtTotal = function(){
		$scope.contract.totalCheckAmount = 0;
		angular.forEach($scope.contract.checkDOList, function(checkDO, index){
			$scope.contract.totalCheckAmount += checkDO.amount;
		});
	}
	
})