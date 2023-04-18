<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/popper.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>

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

</head>

<body>

<!-- 背景圖片設定 -->
<div class="bg">
   <img src="DrinksImage/Backstage background 05.jpg">
</div>

<h3 style="color:#FFBD45;">Backend_GoodsCreate</h3>

    <nav class="navbar navbar-expand-lg navbar-light bg-light">
    
      <a class="navbar-brand" style="color:#5E5E5E;">[ 商品管理 ]</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarSupportedContent">
	        <ul class="navbar-nav mr-auto">
	        	  <li class="nav-item active">
		            <a class="nav-link" href="BackendAction.do?action=queryGoods" style="color:#5E5E5E;">商品列表 </a>
		          </li>
	        	  
		          <li class="nav-item active">
		            <a class="nav-link" href="BackendAction.do?action=modifyView" style="color:#5E5E5E;">商品維護作業 </a>
		          </li>
		          
		          <li class="nav-item active">
		            <a class="nav-link" href="BackendAction.do?action=createView" style="color:#5E5E5E;">商品新增上架</a>
		          </li>
		          
		          <li class="nav-item active">
		            <a class="nav-link" href="BackendAction.do?action=SalesReportView" style="color:#5E5E5E;">銷售報表</a>
		          </li>
		          		      		          
		          <li class="nav-item dropdown">
		            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		             	 商品區
		            </a>
		            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
		              <a class="dropdown-item" href="FrontendAction.do?action=homePageGoods">販賣機</a>
		              <a class="dropdown-item" href="Login.jsp">使用者登入</a>
		              <div class="dropdown-divider"></div>
		              <a class="dropdown-item">施工中</a>
		            </div>
		          </li>
		          
		          <li class="nav-item active">
		            <a class="nav-link" style="color:#6363FF;">管理員 : ${sessionScope.account.name} 先生/小姐您好!</a>
		          </li>		          
	        </ul>
	        
	        <form class="form-inline my-2 my-lg-0">
<!-- 	          <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search"> -->
<!-- 	          <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button> -->
				<a href="LoginAction.do?action=logout" align="left">(登出)</a>
	        </form>
        
      </div>      
    </nav>

</body>
</html>