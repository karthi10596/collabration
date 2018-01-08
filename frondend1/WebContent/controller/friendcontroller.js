/**
 * 
 */

app.controller('FriendController',function($scope,$location,FriendService){
	function listOfSuggestedUsers(){
		FriendService.listOfSuggestedUsers().then(function(response){
			$scope.suggestedusers=response.data
		},function(response){
			if(response.status==401)
				$location.path('/login')
				console.log(response.status)
		})
	}
	/*pending list*/
	function listOfPendingRequests(){
		FriendService.listOfPendingRequests().then(function(response){
			$scope.pendingRequests=response.data;
		},function(response){
			if(response.status==401)
				$location.path('/login')
				console.log(response.status)
		})
	}
/*
 * toId=user.username
 */
	
	$scope.friendRequest=function(toId){
		FriendService.friendRequest(toId).then(function(response){
			alert("Request sent")
			listOfSuggestedUsers();
		$location.path('/suggesteduserslist')
	},function(response){
		if(response.status==401)
			$location.path('/login')
			console.log(response.status)
	})
	
	}
	
		
	
	
	$scope.updatePendingRequest=function(pendingRequest,status){
		/*pendingRequest.status='P'
		 * pendingRequest.status->assign A/D
		 */
		console.log(pendingRequest.status)
		pendingRequest.status=status
		/*pendingRequest is an object of type Friend
		 * id,fromId,toId and status(A/D)
		 */
		FriendService.updatePendingRequest(pendingRequest).then(function(response){
			if(status=='A')
			{
			alert('You have accepted the friend request. You are now friends !');
			$location.path('/pendingrequests')
			}
			else
			{
			alert('You have denied the friend request')
			$location.path('/getAllUsers')
			}
		},function(response){
			if(response.status==401)
				$location.path('/login')
				console.log(response.status)
		
	})
	
	}		

	function listOfFriends(){
	FriendService.listOfFriends().then(function(response){
		$scope.friends=response.data
		console.log(response.status)
	},function(response){
		if(response.status==401)
			$location.path('/login')
		console.log(response.status)
	})
	}
			
listOfSuggestedUsers()
listOfPendingRequests()
listOfFriends()
})


