app.controller('JobController',function(JobService,$scope,$location){
	$scope.showJobDetails=false;
	$scope.job={}
	function getAllJobs(){
		JobService.getAllJobs().then(function(response){
			$scope.jobs=response.data;
		console.log("----------------------->"+$scope.jobs.jobid)
		},function(response){
			$location.path('/login')
		})
	}
	
	$scope.saveJob=function(){
		JobService.saveJob($scope.job).then(function(response){
			$location.path('/getalljobs')
		},function(response){
			console.log(response.status)
			if(response.status==401){
				$scope.error=response.data
				$location.path('/login')
			}
			if(response.status==500){
				$scope.error=response.data
				$location.path('/savejob')
			}
			$location.path('/home')
		})
	}
	
	$scope.getJobDetails=function(jobid)
	{
		$scope.showJobDetails=true
		JobService.getJobDetails(jobid).then(function(response) {
			$scope.job=response.data
		},function(response){
			console.log(response.data)
			$location.path('/login')
		})
	}
getAllJobs()		
})

