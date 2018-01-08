/**
 * 
 */
app.factory('FriendService',function($http){
	var friendService={}
	
	friendService.listOfSuggestedUsers=function(){
	  return  $http.get("http://localhost:8098/chatapprest/getsuggestedusers")
	}
	friendService.friendRequest=function(toId){
		  return  $http.post("http://localhost:8098/chatapprest/friendRequest/"+toId)
		}
	
	friendService.listOfPendingRequests=function(toId){
		  return  $http.get("http://localhost:8098/chatapprest/getpendingrequests")
		}
	friendService.getUserDetails=function(fromId){
		  return  $http.get("http://localhost:8098/chatapprest/getuserdetails/"+fromId)
		}
	
	friendService.updatePendingRequest=function(pendingRequest){
		  return  $http.put("http://localhost:8098/chatapprest/updatependingrequest",pendingRequest)
		}
	friendService.listOfFriends=function(){
		return $http.get("http://localhost:8098/chatapprest/friendslist")
	}
	
	return friendService;
})
