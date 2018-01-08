/**
 * 
 */
app.factory('MydetailService',function($http){
	var mydetailService={}
	var friendService={}
	friendService.getMyDetails=function(fromId){
		  return  $http.get("http://localhost:8098/chatapprest/getmydetails/"+fromId)
		}
	return mydetailService;
})
