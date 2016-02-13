'use strict';

App.factory('LoginService', ['$http', '$q', '$window', function($http, $q, $window){

	return {
			loginUser: function(user){
					return $http.post('/agg/login', user).success(function(data){
						$window.location='/login/homepage';
				    });
							/*.then(
									function(response){
										alert(response.data);
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while creating user');
										return $q.reject(errResponse);
									}
							);*/
		    }
		
	};

}]);
