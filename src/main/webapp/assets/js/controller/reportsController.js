'use strict';

routingApp.controller('ReportsController', function($scope, $location, $http) {
	$http.get("/agg/report/contracts")
    .then(function(response) {
        $scope.contractData = response.data.data.contractData;
        $scope.checkData = response.data.data.checkData;
        
        var MONTHS = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
        var color = Chart.helpers.color;
        var yearData = [];
        var monthData = null;
        var monthArr = [];
        
        var chkYearData = [];
        var chkMonthArr = [];
        
        angular.forEach($scope.contractData, function(contract, key){
        	yearData.push(key);
        	monthData = new Array(12);
        	for(var i=0; i<12; i++){
        		monthData[i] = 0;
        	}
        	angular.forEach(contract, function(contractInfo, childKey){
        		monthData[contractInfo.month - 1] = contractInfo.total;
        	});
        	monthArr.push(monthData);
        });
        
        angular.forEach($scope.checkData, function(chk, key){
        	chkYearData.push(key);
        	monthData = new Array(12);
        	/*for(var i=0; i<12; i++){
        		monthData[i] = 0;
        	}*/
        	angular.forEach(chk, function(chkInfo, childKey){
        		monthData[chkInfo.month - 1] = chkInfo.checkAmount;
        	});
        	chkMonthArr.push(monthData);
        });
        
        window.chartColors = {
    		red: 'rgb(255, 99, 132)',
    		orange: 'rgb(255, 159, 64)',
    		yellow: 'rgb(255, 205, 86)',
    		green: 'rgb(75, 192, 192)',
    		blue: 'rgb(54, 162, 235)',
    		purple: 'rgb(153, 102, 255)',
    		grey: 'rgb(201, 203, 207)'
    	};
        
        var colorArr = [];
        colorArr.push(window.chartColors.red);
        colorArr.push(window.chartColors.blue);
        colorArr.push(window.chartColors.orange);
        colorArr.push(window.chartColors.yellow);
        colorArr.push(window.chartColors.green);
        colorArr.push(window.chartColors.purple);
        colorArr.push(window.chartColors.grey);
        
        var datasetData = [];
        for(var i = 0; i < yearData.length; i++){
        	datasetData.push({
				type: 'bar',
                label: yearData[i]+' (# Contracts)',
                backgroundColor: color(colorArr[i]).alpha(0.5).rgbString(),
                borderColor: colorArr[i],
                borderWidth: 1,
                data: monthArr[i],
				yAxisID: 'y-axis-2'
            })
        }
        var k = 0;
        for(var i = 0; i < chkYearData.length; i++){
        	k = 0;
        	for(var j = 0; j < yearData.length; j++){
        		if(chkYearData[i] == yearData[j]){
        			k = j;
        			break;
        		}
        	}
        	//to change the color of line graph.
        	if(k == 0){
        		k = colorArr.length - i;
        		if(k < 0){
        			k = i;
        		}
        	}
        	datasetData.push({
				type: 'line',
                label: chkYearData[i]+' (Total Chk Amount)',
                backgroundColor: color(colorArr[k]).alpha(0.5).rgbString(),
                borderColor: colorArr[k],
                borderWidth: 1,
                fill: false,
                data: chkMonthArr[i],
				yAxisID: 'y-axis-1'
            })
        }
        
        var barChartData = {
			labels: MONTHS,
            datasets: datasetData
        };
        
        var ctx = document.getElementById("contractReport").getContext("2d");
        window.myBar = new Chart(ctx, {
            type: 'bar',
            data: barChartData,
            options: {
                responsive: true,
                legend: {
                    position: 'top',
                },
                title: {
                    display: true,
                    text: '# of Active Contracts'
                },
				tooltips: {
					//enabled: false,
                    mode: 'index',
                    intersect: true
                }, 
				scales: {
					xAxes: [{
						display: true,
						gridLines: {
							display: false
						},
						labels: {
							show: true,
						}
					}],
					yAxes: [{
						type: "linear",
						display: true,
						position: "left",
						id: "y-axis-1",
						gridLines:{
							display: false
						},
						labels: {
							show:true,
							
						}
					}, {
						type: "linear",
						display: true,
						position: "right",
						id: "y-axis-2",
						gridLines:{
							display: false
						},
						labels: {
							show:true,
							
						}
					}]
				},
				animation: {
					
					/*onComplete: function () {
						var chartInstance = this.chart;
						var ctx = this.chart.ctx;
						ctx.font = Chart.helpers.fontString(Chart.defaults.global.defaultFontSize, Chart.defaults.global.defaultFontStyle, Chart.defaults.global.defaultFontFamily);
						ctx.fillStyle = '#000';
						ctx.textAlign = 'center';
						ctx.textBaseline = 'bottom';

						this.data.datasets.forEach(function(dataset, i) {
							var meta = chartInstance.controller.getDatasetMeta(i);
							meta.data.forEach(function(bar, index) {
								var data = dataset.data[index];
								ctx.fillText(data, bar._model.x, bar._model.y - 5);
							});
						});
					} */
				},
				hover: {
				  animationDuration: 0
				}
            }
			
        });
        
    });
});