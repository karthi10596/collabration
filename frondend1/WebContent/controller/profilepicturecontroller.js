/**
 * 
 */
app.controller('profilepicturecontroller',function($location,$scope){
	
	function getProfilePicture(){
		//profilepictureService.getProfilePicture().then(function(response){
			//$scope.profilePicture=response.data;
		//console.log("----------------------->"+$scope.profilePicture)
		$location.path('/profilepic')
	
	}
	
})