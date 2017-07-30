var app = angular.module('app', []);

var theTeamId = "";

app.controller('temCtrl', function($scope, $http, $location) {
	
	$scope.mytem = "";
	
	$scope.createTeam = function() {
		$http.get("/getAllDepartment").then(function (response) {
	        $scope.departmentList = response.data;
	    });
		$("#chartId").html("");
	}
	
	$scope.otherTeam = function() {
		$http.get("/getAllTeam").then(function (response) {
	        $scope.teamList = response.data;
	    });
		$("#chartId").html("");
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
		$("#chartId").html("");
	}
	
	$scope.teamInfo = function($event) {
		var teamid = $event.target.getAttribute("teamid");
		var url = '/getTeamUser/'+teamid;
		$scope.mytem = "view/teamInfo.html";
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
		$("#chartId").html("");
	}
	
	$scope.captainTable = function() {
		var register_name = $('#register_name').text();
//		alert(register_name);
		$http.get("/getMyTeam/" + register_name).then(function(response) {
			$scope.playerList = response.data;
		});
		$("#chartId").html("");
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
		$("#chartId").html("");
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
		$("#chartId").html("");
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
		$("#chartId").html("");
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
	
	function chart_1() {
		
		var myChart = echarts.init(document.getElementById("chartId"));
		
		option = {
			    angleAxis: {
			        type: 'category',
			        data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日'],
			        z: 10
			    },
			    radiusAxis: {
			    },
			    polar: {
			    },
			    series: [{
			        type: 'bar',
			        data: [1, 2, 3, 4, 3, 5, 1],
			        coordinateSystem: 'polar',
			        name: '不可参赛',
			        stack: 'a'
			    }, {
			        type: 'bar',
			        data: [1, 2, 3, 4, 1, 2, 5],
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
		
		var myChart = echarts.init(document.getElementById("chartId2"));
		
		option = {
			    angleAxis: {
			        type: 'category',
			        data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日'],
			        z: 10
			    },
			    radiusAxis: {
			    },
			    polar: {
			    },
			    series: [{
			        type: 'bar',
			        data: [1, 2, 3, 4, 3, 5, 1],
			        coordinateSystem: 'polar',
			        name: '不可参赛',
			        stack: 'a'
			    }, {
			        type: 'bar',
			        data: [1, 2, 3, 4, 1, 2, 5],
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
	
	$scope.chartTable = function() {
		$("#chartId").attr("style","width: 800px;height:800px;");
		$("#chartId").attr("_echarts_instance_","");
		
		chart_1();
		chart_2();
	}
	
	
	
});

