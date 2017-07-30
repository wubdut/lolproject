var app = angular.module('app', []);

var theTeamId = "";

app.controller('temCtrl', function($scope, $http, $location) {
	
	$scope.mytem = "";
	
	$scope.createTeam = function() {
		$http.get("/getAllDepartment").then(function (response) {
	        $scope.departmentList = response.data;
	    });
		chartRefresh();
	}
	
	$scope.otherTeam = function() {
		$http.get("/getAllTeam").then(function (response) {
	        $scope.teamList = response.data;
	    });
		chartRefresh();
	}
	
	$scope.formData = {};
	
	$scope.teamSubmit = function() {
		
		var register_name = $('#register_name').text();
		
		var data = {
			name : $scope.formData.teamName,
			department_id : $scope.formData.depa.id,
			register_name : register_name
		}
		$http.post('/createTeam', data).success(function(response) {
			alert(response);
		});
		chartRefresh();
	}
	
	$scope.teamInfo = function($event) {
		var teamid = $event.target.getAttribute("teamid");
		var url = '/getTeamUser/'+teamid;
		$scope.mytem = "view/teamInfo.html";
		$http.get(url).then(function(response) {
			$scope.playerList = response.data;
		});
	}
	
	$scope.teamDelete = function($event) {
		var teamid = $event.target.getAttribute("teamid");
		var url = '/getTeamDelete/'+teamid;
		alert(url);
		$scope.mytem = "view/teamTable2.html";
		$http.get(url).then(function(response) {
			$scope.playerList = response.data;
		});
	}
	
	$scope.getMyTeam = function() {
		var register_name = $('#register_name').text();
//		alert(register_name);
		$http.get("/getMyTeam/" + register_name).then(function(response) {
			$scope.playerList = response.data;
		});
		chartRefresh();
	}
	
	$scope.captainTable = function() {
		var register_name = $('#register_name').text();
//		alert(register_name);
		$http.get("/getMyTeam/" + register_name).then(function(response) {
			$scope.playerList = response.data;
		});
		chartRefresh();
	}
	
	$scope.captainEdit = function($event) {
		var userId = $event.target.getAttribute("userId");
//		alert(tar.parent());
//		$http.get("/captainEdit/"+userId).then(function(response) {
//			$scope.playerList = response.data;
//		});
	}
	
	$scope.captainDelete = function($event) {
		var userId = $event.target.getAttribute("userId");
		$http.get("/captainDelete/"+userId).then(function(response) {
			$scope.playerList = response.data;
		});
	}
	
	$scope.checkTable = function() {
		$http.get("/getAllDepartment").then(function (response) {
	        $scope.departmentList = response.data;
	    });
		chartRefresh();
	}
	
	$scope.checkData = {depa : "事业部", name : "姓名"};
	$scope.checkSubmit = function() {
		
		var x = 0;
		var y = 0;
		
		var data = {
			name : $scope.checkData.name,
			department_id : $scope.checkData.depa.id,
		}
		if (data.department_id == null) x = 0;
		else x = 1;
		
		if (data.name === "姓名") y = 0;
		else y = 1;
		
		if (2*x+y == 0) {
			$http.get('/getAllUser').then(function(response) {
				$scope.playerList = response.data;
			});
		} else if (2*x+y == 2) {
//			alert(data.department_id);
			$http.get('/getCheckDepa/' + data.department_id).then(function(response) {
				$scope.playerList = response.data;
			});
		} else if (2*x+y == 1) {
			$http.get('/getCheckName/' + data.name).then(function(response) {
				$scope.playerList = response.data;
			});
		} else {
			$http.get('/getCheckDepaAndName/' + data.department_id + "&" + data.name).then(function(response) {
				$scope.playerList = response.data;
			});
		}
	}
	
	$scope.addTable = function() {
		$http.get('/getAddTable').then(function(response) {
			$scope.registerList = response.data;
		});
		chartRefresh();
	}
	
	$scope.addPlayer = function($event) {
		var name = $('#register_name').text();
		var id = $event.target.getAttribute("registerId");
		$http.get('/getAddPlayer/'+name+"&"+id).then(function(response) {
			$scope.playerList = response.data;
			alert("添加成功");
		});
	}
	
	
	
	$scope.matchTable = function() {
		chartRefresh();
	}
	
	$scope.arrange = function() {
		
		$http.get('/createPlay').then(function(response) {
			$scope.matchList = response.data;
			alert("已经安排");
		});
	}
	
	$scope.matchData = {
			name : "战队名",
			date : "比赛时间"
	}
	
	$scope.matchSubmit = function() {
		var data = $scope.matchData;
		if (data.name === "战队名")
			data.name = "";
		if (data.date === "比赛时间")
			data.date = "";
		$http.get('/getMatchTable/'+data.name+"&"+data.date).then(function(response) {
			$scope.matchList = response.data;
		});
		data.name = "战队名";
		data.date = "比赛时间"
	}
	
	function chartRefresh() {
		$("#chartId").html("");
		$("#chartId2").html("");
		$("#chartId3").html("");
	}
	
	function chart_1() {
		$("#chartId").attr("style","width: 600px;height:600px;");
		$("#chartId").attr("_echarts_instance_","");
		var myChart = echarts.init(document.getElementById("chartId"));
		
		option = {
			    angleAxis: {
			        type: 'category',
			        data: ['基础软件','东软遍在','东软熙康','通企','IT服务','网络安全','汽车电子','东软睿道'],
			        z: 10
			    },
			    radiusAxis: {
			    },
			    polar: {
			    },
			    series: [{
			        type: 'bar',
			        data: [6, 8, 4, 5, 14, 13, 23, 0],
			        coordinateSystem: 'polar',
			        name: '不可参赛',
			        stack: 'a'
			    }, {
			        type: 'bar',
			        data: [8, 20, 12, 24, 14, 25, 5, 5],
			        coordinateSystem: 'polar',
			        name: '可参赛',
			        stack: 'a'
			    }],
			    legend: {
			        show: true,
			        data: ['不可参赛', '可参赛']
			    }
			};
		
		myChart.setOption(option);
	}
	
	function chart_2() {
		$("#chartId2").attr("style","width: 600px;height:600px;");
		$("#chartId2").attr("_echarts_instance_","");
		var myChart = echarts.init(document.getElementById("chartId2"));
		

		var data = [
		    [[28604,77,17096869,'Australia',1990],[31163,77.4,27662440,'Canada',1990],[1516,68,1154605773,'China',1990],[13670,74.7,10582082,'Cuba',1990],[28599,75,4986705,'Finland',1990],[29476,77.1,56943299,'France',1990],[31476,75.4,78958237,'Germany',1990],[28666,78.1,254830,'Iceland',1990],[1777,57.7,870601776,'India',1990],[29550,79.1,122249285,'Japan',1990],[2076,67.9,20194354,'North Korea',1990],[12087,72,42972254,'South Korea',1990],[24021,75.4,3397534,'New Zealand',1990],[43296,76.8,4240375,'Norway',1990],[10088,70.8,38195258,'Poland',1990],[19349,69.6,147568552,'Russia',1990],[10670,67.3,53994605,'Turkey',1990],[26424,75.7,57110117,'United Kingdom',1990],[37062,75.4,252847810,'United States',1990]],
		    [[44056,81.8,23968973,'Australia',2015],[43294,81.7,35939927,'Canada',2015],[13334,76.9,1376048943,'China',2015],[21291,78.5,11389562,'Cuba',2015],[38923,80.8,5503457,'Finland',2015],[37599,81.9,64395345,'France',2015],[44053,81.1,80688545,'Germany',2015],[42182,82.8,329425,'Iceland',2015],[5903,66.8,1311050527,'India',2015],[36162,83.5,126573481,'Japan',2015],[1390,71.4,25155317,'North Korea',2015],[34644,80.7,50293439,'South Korea',2015],[34186,80.6,4528526,'New Zealand',2015],[64304,81.6,5210967,'Norway',2015],[24787,77.3,38611794,'Poland',2015],[23038,73.13,143456918,'Russia',2015],[19360,76.5,78665830,'Turkey',2015],[38225,81.4,64715810,'United Kingdom',2015],[53354,79.1,321773631,'United States',2015]]
		];

		var data = [
		    [
		    	[168,14,'基础软件'],
		    	[200,28,'东软遍在'],
		    	[500,16,'东软熙康'],
		    	[982,29,'通企'],
		    	[2037,28,'IT服务'],
		    	[409,37,'网络安全'],
		    	[987,28,'汽车电子'],
		    	[232,5,'东软睿道']
		    ]
		];

		var cnt = 0;

		option = {
		    backgroundColor: new echarts.graphic.RadialGradient(0, 0, 0, [{
		        offset: 0,
		        color: '#f7f8fa'
		    }, {
		        offset: 1,
		        color: '#cdd0d5'
		    }]),
		    title: {
		        text: '部门人数  与  部门队数'
		    },
		    xAxis: {
		        splitLine: {
		            lineStyle: {
		                type: 'dashed'
		            }
		        }
		    },
		    yAxis: {
		        splitLine: {
		            lineStyle: {
		                type: 'dashed'
		            }
		        },
		        scale: true
		    },
		    series: [{
		        name: '部门',
		        data: data[0],
		        type: 'scatter',
		        symbolSize: function (data) {
		            return Math.sqrt(data[1]/data[0])*100;
		        },
		        itemStyle: {
		            normal: {
		                shadowBlur: 10,
		                shadowColor: 'rgba(120, 36, 50, 0.5)',
		                shadowOffsetY: 5,
		                color: new echarts.graphic.RadialGradient(0.4, 0.3, 1, [{
		                    offset: 0,
		                    color: 'rgb(251, 118, 123)'
		                }, {
		                    offset: 1,
		                    color: 'rgb(204, 46, 72)'
		                }]),
		                label: {
		                     show: true,
		                     position:'top',
		                     formatter:function(){
		                     	cnt = cnt+1;
		                        return data[0][(cnt-1)/2];
		                     }
		                }
		            }
		        }
		    }]
		};
		
		myChart.setOption(option);
	}
	
	function chart_3() {
		$("#chartId3").attr("style","width: 1400px;height:200px;");
		$("#chartId3").attr("_echarts_instance_","");
		var myChart = echarts.init(document.getElementById("chartId3"));
		var hours = ['1st', '2nd', 'rd', '4th', '5th', '6th', '7th',
	        '8th', '9th', '10th','11th','12th',
	        '13th', '14th', '15th', '16th', '17th', '18th',
	        '19th', '20th', '21th', '22th', '23th', '24th', '25th',
	        '26th', '27th', '28th', '29th', '30th', '31th'];
		var days = ['August'];
	
		var data = [[0,0,5],[0,1,1],[0,2,5],[0,3,3],[0,4,8],[0,5,6],[0,6,0],[0,7,0],[0,8,0],[0,9,0],[0,10,0],[0,11,2],[0,12,4],[0,13,1],[0,14,1],[0,15,3],[0,16,4],[0,17,6],[0,18,4],[0,19,4],[0,20,3],[0,21,3],[0,22,2],[0,23,5],[0,24,6],[0,25,4],[0,26,4],[0,27,3],[0,28,3],[0,29,2],[0,30,5]];
	
		option = {
		    tooltip: {
		        position: 'top'
		    },
		    title: [],
		    singleAxis: [],
		    series: []
		};
	
		echarts.util.each(days, function (day, idx) {
		    option.title.push({
		        textBaseline: 'middle',
		        top: (idx + 0.5) * 100 / 7 + '%',
		        text: day
		    });
		    option.singleAxis.push({
		        left: 150,
		        type: 'category',
		        boundaryGap: false,
		        data: hours,
		        top: (idx * 100 / 7 + 5) + '%',
		        height: (100 / 7 - 10) + '%',
		        axisLabel: {
		            interval: 2
		        }
		    });
		    option.series.push({
		        singleAxisIndex: idx,
		        coordinateSystem: 'singleAxis',
		        type: 'scatter',
		        data: [],
		        symbolSize: function (dataItem) {
		            return dataItem[1] * 4;
		        }
		    });
		});
		
		echarts.util.each(data, function (dataItem) {
		    option.series[dataItem[0]].data.push([dataItem[1], dataItem[2]]);
		});
		
		myChart.setOption(option);
	}
	
	$scope.chartTable = function() {
		chart_1();
		chart_2();
		chart_3();
	}
	
	function tech_1() {
		$("#chartId").attr("style","width: 600px;height:600px;");
		$("#chartId").attr("_echarts_instance_","");
		var myChart = echarts.init(document.getElementById("chartId"));
		
		option = {
				title: {
			        text: '技术统计'
			    },
			    tooltip: {},
			    legend: {
			        data: ['个人','队内平均']
			    },
			    radar: {
			        // shape: 'circle',
			        indicator: [
			           { name: '补兵', max: 6500},
			           { name: '伤害', max: 16000},
			           { name: '死亡', max: 30000},
			           { name: '助攻', max: 38000},
			           { name: '击杀', max: 52000},
			           { name: '金钱', max: 25000}
			        ]
			    },
			    series: [{
			        name: 'VS',
			        type: 'radar',
			        // areaStyle: {normal: {}},
			        data : [
			            {
			                value : [4300, 10000, 28000, 35000, 50000, 19000],
			                name : '个人'
			            },
			             {
			                value : [5000, 14000, 28000, 31000, 42000, 21000],
			                name : '队内平均'
			            }
			        ]
			    }]
			};
			myChart.setOption(option);
	}
	
	function tech_2() {
		$("#chartId2").attr("style","width: 600px;height:600px;");
		$("#chartId2").attr("_echarts_instance_","");
		var myChart = echarts.init(document.getElementById("chartId2"));
		

		option = {
			    title: {
			        text: '胜率'
			    },
			    tooltip: {},
			    legend: {
			        data: ['部门队伍','部门平均']
			    },
			    radar: {
			        // shape: 'circle',
			        indicator: [
			           { name: '球王', max: 10},
			           { name: '大圣', max: 10},
			           { name: '指导', max: 10},
			           { name: '楼主', max: 10},
			           { name: '首胖', max: 10},
			           { name: '日全', max: 10}
			        ]
			    },
			    series: [{
			        name: 'VS',
			        type: 'radar',
			        // areaStyle: {normal: {}},
			        data : [
			            {
			                value : [5, 8, 4, 7, 3, 9],
			                name : '部门队伍'
			            },
			             {
			                value : [6, 6, 6, 6, 6, 6],
			                name : '部门平均'
			            }
			        ]
			    }]
			};
		
		myChart.setOption(option);
	}
	
	$scope.techTable = function() {
		tech_1();
		tech_2();
	}
	
	
	
});

