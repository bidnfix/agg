'use strict';

App.controller('LoginController', ['$scope', 'LoginService', '$location', '$window', '$modal', '$timeout',  function($scope, LoginService, $location, $window, $modal, $timeout) {
		 
		  var objappVersion = $window.navigator.appVersion; 
		  var objAgent = $window.navigator.userAgent; 
		  var objbrowserName = $window.navigator.appName; 
		  var objfullVersion = ''+parseFloat($window.navigator.appVersion); 
		  var objBrMajorVersion = parseInt($window.navigator.appVersion,10); 
		  var objOffsetName,objOffsetVersion,ix; 

		  if ((objOffsetVersion=objAgent.indexOf("MSIE"))!=-1) { 
			  objbrowserName = "Microsoft Internet Explorer"; 
			  objfullVersion = objAgent.substring(objOffsetVersion+5);
			  objfullVersion = objfullVersion.split(';')[0];
			  
			  //alert(objbrowserName + " - "+objfullVersion.split(';')[0]);
			  if(objfullVersion < 10){
				  var modalInstance = $modal.open({
					  templateUrl: '../../jsp/popup.jsp',
					  controller: 'BrowserPopupController'
				  });
			  }
			  
		  }/*else{
			  var browserModel = $modal.open({
				  templateUrl: '../../jsp/popup.jsp',
				  controller: 'BrowserPopupController'
			  });
		  }*/
		  
		 
          var self = this;
          self.user={username:'',password:''};
          self.loginUser = function(user){
        	  //alert("loginUser")
              LoginService.loginUser(user)
		              .then($location.path('/login/home'));
          };

          self.submit = function() {
        	  //alert("submit");
              //if(self.user.id != null){
                 // console.log('login User', self.user);    
                  self.loginUser(self.user);
             // }
          };
          
      }]);

//controller for popup1.html view for close button
App.controller('BrowserPopupController',function ($scope, $modalInstance) {
	$scope.closeModal = function () {
		$modalInstance.dismiss('cancel');
	};
});