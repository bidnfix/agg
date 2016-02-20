'use strict';

App.controller('LoginController', ['$scope', 'LoginService', '$location', function($scope, LoginService, $location) {
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
                  console.log('login User', self.user);    
                  self.loginUser(self.user);
             // }
          };
              
      }]);
