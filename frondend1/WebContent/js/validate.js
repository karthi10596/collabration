/**
 * 
 */
function registerUser(){
	 var emailRegex = /^[A-Za-z0-9._]*\@[A-Za-z]*\.[A-Za-z]{2,5}$/;
	 var fname = form.firstname.value,
	  lname = form.lastname.value,
	  femail = form.emailid.value,
	  fpnumber =form.phonenumber.value,
	  funame=form.username.value,
	  fpword =form.password.value,
	  funame =form.username.value;
	  
	   
	 if( fname == "" )
	   {
	     form.firstname.focus() ;
	  getElementById("errorBox").innerHTML = "enter the first name";
	     return false;
	   }
	 if( lname == "" )
	   {
	     form.lastname.focus() ;
	   getElementById("errorBox").innerHTML = "enter the last name";
	     return false;
	   }
	 if( uname == "" )
	   {
	     form.username.focus() ;
	  getElementById("errorBox").innerHTML = "enter the first name";
	     return false;
	   }
	    
	   if (femail == "" )
	 {
	  form.emailid.focus();
	  getElementById("errorBox").innerHTML = "enter the email";
	  return false;
	  }
	  
	 if(fpword == "")
	  {
	   form.password.focus();
	   getElementById("errorBox").innerHTML = "enter the password";
	   return false;
	  }
	  if(document.form.radiobutton[0].checked == false && document.form.radiobutton[1].checked == false){
	    document.getElementById("errorBox").innerHTML = "select your role";
	    return false;
	   }
	  if(fname != '' && lname != '' && femail != '' && fpword != '' && funame != '' ){
	   document.getElementById("errorBox").innerHTML = "form submitted successfully";
	   }
	     
	}