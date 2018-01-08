/**
 * 
 */
app.factory('UserService',function($http){
	var userService={}; //instantiation
	var BASE_URL="http://localhost:8098/chatapprest/"
		
		userService.registerUser=function(user){
		return $http.post(BASE_URL+"/register",user)
	}
	
	userService.loginUser=function(user){
		return $http.post(BASE_URL+"/login",user)
	}
	userService.updateUser=function(user){
		return $http.put(BASE_URL + "/updateuser",user)
	}
	userService.getUser=function(){
		return $http.get(BASE_URL + "/getAllUsers")
	}
	userService.logout=function(){
		return $http.get(BASE_URL+"/logout")
	}
	return userService;
})