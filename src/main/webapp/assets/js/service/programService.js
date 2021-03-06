'use strict';

routingApp.factory('programService', function($http, $q, $window) {
			return {
				saveProgram : function(program) {
					showSpinner();
					return $http.post('/agg/postPrograms', program).then(
							function(response) {
								//alert(response.data.status);
								if (response.data.status == 'success') {
									$window.location = '#/agg/programs';
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
			
				editProgram : function(program, $scope) {
					showSpinner();
					return $http.post('/agg/editProgram', program).then(
							function(response) {
								//alert(response.data.status);
								if (response.data.status == 'success') {
									closePopup('programEditPopup');
									//$window.location.href = '#/agg/dealers';
									var objects = $scope.programsList;
							        for (var i = 0; i < objects.length; i++) {
							            if (objects[i].id === program.prId) {
							                objects[i] = program;
							                break;
							            }
							        }
								} else {
									alert('Error in adding program: '+response.data.errMessage)
									//$('#errMsg').html(response.data.errMessage);
								}
								hideSpinner();
							}, function(errResponse) {
								alert('Error while creating machine');
								hideSpinner();
								return $q.reject(errResponse);
							});
				},
				
				submitProgramAsDealr : function(program) {
					showSpinner();
					return $http.post('/agg/saveProgramsAsDealr', program).then(
							function(response) {
								//alert(response.data.status);
								if (response.data.status == 'success') {
									//alert(response.data.data.id);
									//alert(response.data.data.quoteId);
									$window.location = '#/agg/viewQuote/'+response.data.data.id+'/'+response.data.data.quoteId;
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
				}

			};

		});
