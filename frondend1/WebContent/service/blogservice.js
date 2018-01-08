app.factory('BlogPostService',function($http){
	var blogPostService={};
	
	blogPostService.addBlogPost=function(blogPost){
		return $http.post("http://localhost:8098/chatapprest/saveblogpost",blogPost)
	}
	
	blogPostService.getBlogPostsWaitingForApproval=function(){
		return $http.get("http://localhost:8098/chatapprest/getblogposts/"+0)
	}
	
	blogPostService.getBlogPostsApproved=function(){
		return $http.get("http://localhost:8098/chatapprest/getblogposts/"+1)
	}
	blogPostService.getBlogPostById=function(id){
		return $http.get("http://localhost:8098/chatapprest/getblogpostbyid/"+id);
	}
	blogPostService.updateBlogPost=function(blogPost){
		return $http.put("http://localhost:8098/chatapprest/updateblogpost",blogPost);
	}
	blogPostService.addComment=function(blogComment){
		return $http.post("http://localhost:8098/chatapprest/addblogcomment",blogComment);
	}
	
	
	blogPostService.getBlogComments=function(blogPostId)
	{
		//console.log('getBlogComments() in BlogPostService')
		return $http.get("http://localhost:8098/chatapprest/getblogcomments/"+blogPostId)
	}
	
	
	
	return blogPostService;
})