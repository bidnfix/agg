'use strict';

routingApp.factory('reportaBugService', function($http, $q, $window) {
			return {
				saveBug : function(bug) {
					showSpinner();
					return $http.post('/agg/postBug', bug).then(
							function(response) {
								//alert(response.data.status);
								if (response.data.status == 'success') {
									$window.location = '#/agg/reportaBug';
								} else {
									alert('Error in adding program: '+response.data.errMessage)
									//$('#errMsg').html(response.data.errMessage);
								}
								hideSpinner();
							}, function(errResponse) {
								alert('Error while creating program');
								hideSpinner();
								return $q.reject(errResponse);
							});
				},
			
			editBugInfo : function(bug, $scope) {
				//alert('in edit bug');
				showSpinner();
				return $http.post('/agg/editBug', bug).then(
						function(response) {
							//alert(response.data.status);
							if (response.data.status == 'success') {
								closePopup('bugEditPopup');
								//$window.location.href = '#/agg/dealers';
								var objects = $scope.bugDOList;
						        for (var i = 0; i < objects.length; i++) {
						            if (objects[i].id === bug.id) {
						                objects[i] = bug;
						                break;
						            }
						        }
							} else {
								alert('Error in updating bug: '+response.data.errMessage)
								//$('#errMsg').html(response.data.errMessage);
							}
							hideSpinner();
						}, function(errResponse) {
							alert('Error while updating bug');
							hideSpinner();
							return $q.reject(errResponse);
						});
			}

			};

		});
