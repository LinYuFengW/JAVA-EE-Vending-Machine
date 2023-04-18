<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Language" content="zh-tw">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>Login</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/signin.css" rel="stylesheet">
    
    <!-- 背景圖片設定 -->
    <style>
        .bg {
            position: fixed;
            top: 0;
            left: 0;
            bottom: 0;
            right: 0;
            z-index: -999;
        }
        .bg img {
            min-height: 100%;
            width: 100%;
        }
    </style>
    
    <script type="text/javascript">
    
    function submitUserForm(){
    	var id = document.userForm.id.value;
    	var pwd = document.userForm.pwd.value;
    	if(id=="" || pwd==""){
    		alert("User Or Pwd are not allow empty!!");
    	}else{
    		document.userForm.submit();
    	}
    }
    
    </script>
    
</head>

<body class="text-center">	
	
	<!-- 背景圖片設定 -->
	<div class="bg">
        <img src="DrinksImage/Login background image.jpg">
    </div>

	<form class="form-signin" action="LoginAction.do" name="userForm" method="post">
      <img class="mb-4" src="DrinksImage/BootstrapLogo.png" alt="" width="110" height="110">
      <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
      
      <input type="hidden" name="action" value="login"/>
      <label for="inputAccount" class="sr-only">Account</label>
      <input type="text" name="id" class="form-control" placeholder="Account" required autofocus>
      
      <label for="inputPassword" class="sr-only">Password</label>
      <input type="password" name="pwd" class="form-control" placeholder="Password" required>
      
      <div class="checkbox mb-3">
        <label>
          <p style="color:#E69500;">${sessionScope.loginMsg}</p>
          <% session.removeAttribute("loginMsg"); %>          
        </label>
      </div>
      
<!--       <input class="btn btn-outline-primary" type="submit" value="[ ⇦ _¯_¯_¯_¯_ Sign in _¯_¯_¯_¯_ ⇨ ]"></> -->
      <input class="btn btn-outline-primary" type="button" value="[ ⇦ _¯_¯_¯_¯_ Sign in _¯_¯_¯_¯_ ⇨ ]" onclick="submitUserForm()"></>
      
      <p class="mt-5 mb-3 text-muted">&copy; 2021-2022</p>
    </form>

<!-- 舊程式 -->	
<!-- 	<form action="LoginAction.do" method="post"> -->
<!-- 		<input type="hidden" name="action" value="login"/> -->
<!-- 	    ID:<input type="text" name="id"/> <br/><br/> -->
<!-- 	    PWD:<input type="password" name="pwd"/> <br/><br/> -->
<!-- 	    <input type="submit"/> -->
<!-- 	</form> -->

</body>
</html>