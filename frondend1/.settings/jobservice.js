/**
 * 
 */
app.factory('JobService',function($http){
	var jobService={}
	
	jobService.saveJob=function(job){
	  return  $http.post("",job)
	}
	jobService.getAllJobs=function(){
		 return  $http.get("")
	}
	jobService.getJobDetails=function(id){
		return $http.get(""+id)
	}
	return jobService;
})