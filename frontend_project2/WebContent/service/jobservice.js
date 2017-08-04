/**
 * job service
 */
app.factory('JobService',function($http){
	var jobService={}
	jobService.saveJob=function(job){
		return $http.post("http://localhost:8081/backend_project2/saveJob",job)
	}
	jobService.getAllJobs=function(){
		return $http.get("http://localhost:8081/backend_project2/getalljobs")
	}
	jobService.getJobById=function(id){
		return $http.get("http://localhost:8081/backend_project2/getjobbyid/"+id)
	}
	return jobService;
})