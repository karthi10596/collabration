/**
 * 
 */


app.factory('JobService',function($http){
	var jobService={}
	
	jobService.saveJob=function(job){
	  return  $http.post("http://localhost:8098/chatapprest/savejob",job)
	}
	jobService.getAllJobs=function(){
		 return  $http.get("http://localhost:8098/chatapprest/getalljobs")
	}
	jobService.getJobDetails=function(jobid){
		return $http.get("http://localhost:8098/chatapprest/getjobbyid/"+jobid)
	}
	return jobService;
})