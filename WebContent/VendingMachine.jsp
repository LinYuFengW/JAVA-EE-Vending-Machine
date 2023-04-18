<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>

<meta http-equiv="Content-Language" content="zh-tw">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="js/popper.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/holder.min.js"></script>

<!-- Bootstrap core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="css/carousel.css" rel="stylesheet">


<style type="text/css">
	.page{display:inline-block;padding-left: 10px;}
</style>


<!-- 背景圖片設定  -->
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

<!-- 購物車設定01  -->
<script type="text/javascript">
	function buildCarGoods(carGoods){
		$("#totalPoodsPrice").text("總金額:" + carGoods.totalGoodsPrice);
		$("#carNumber").text("購物車" + "(" + carGoods.number + ")");
		
		$.each(carGoods.shoppingCart, function(index, carCommodity){
			var NO = index+1
			var carEach = "<tr>"
				carEach += "<td>" + NO + "</td>"
				carEach += "<td>" + "<img src='<%=request.getContextPath()%>/DrinksImage/" + carCommodity.imageName + "' width='30' height='30'/>" + "</td>"				
				carEach += "<td>" + carCommodity.goodsName + "</td>"
				carEach += "<td>" + carCommodity.goodsPrice + "</td>"
				carEach += "<td>" + carCommodity.buyQuantity + "</td>"
				carEach += "<td>" + carCommodity.totalPrice + "</td>"
				carEach += "</tr>"
				
			$("#carForEach").append(carEach);

	  	});

	}
</script>

<!-- 購物車設定02  -->
<script type="text/javascript">
	function shoppingCartFunction(goodsID){
		// 購物車商品資料(商品編號、購買數量)AJAX送到後端存入HttpSession
		var carJoinGoods = {
				goodsID : goodsID,
				buyQuantity : $("#buyQuantitys_" + goodsID).val()
		};
		// 數量不可為0
		if(carJoinGoods.buyQuantity > 0){
			$.ajax({
				  url: '<%=request.getContextPath()%>/FrontendAction.do?action=shoppingCart', // 指定要進行呼叫的位址
				  type: "POST", // 請求方式 POST/GET
				  data: carJoinGoods, // 傳送至 Server的請求資料(物件型式則為 Key/Value pairs)
				  dataType : 'json', // Server回傳的資料類型
				  success: function(carGoods) { // 請求成功時執行函式
					  buildCarGoods(carGoods);
				  	  if(goodsID != ''){alert("加入購物車成功!");}
				  	  //畫面自動刷新 
					  window.location.reload();
				  }, 
				  error: function(error) { // 請求發生錯誤時執行函式
				    alert("Ajax Error!");
				  }
			});
		} else {
			alert("數量不可為0");
		}
		
    };
</script>
	
<title>販賣機</title>
	
</head>

<body>

<!-- 背景圖片設定 -->
<!-- <div class="bg"> -->
<!--    <img src="DrinksImage/Send_background.jpg"> -->
<!-- </div> -->

    <header>
      
      <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
      
        <a class="navbar-brand" href="#">${sessionScope.account.name} 先生/小姐您好!</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        
        <div class="collapse navbar-collapse" id="navbarCollapse">
        
          <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
              <a class="nav-link" href="BackendAction.do?action=queryGoods" align="left">後臺頁面</a>
            </li>
            <li class="nav-item active">
              <a class="nav-link" href="LoginAction.do?action=logout" align="left">登出</a>
            </li>
            <li class="nav-item active">
              <a class="nav-link">&nbsp;</a>
            </li>
            <li class="nav-item active">
              <!-- 互動視窗Button trigger modal -->
			    <button type="button" id="carNumber" class="btn btn-outline-success my-2 my-sm-0" data-toggle="modal" data-target="#exampleModalCenter" style="text-align:center;line-height:10px; width:100px; height:40px;">
					購物車(${number})
			    </button>	
            </li>
                        
          </ul>
         			          
          <form class="form-inline mt-2 mt-md-0" action="FrontendAction.do" method="get">
          	<input type="hidden" name="action" value="homePageGoods"/>
		    <input type="hidden" name="pageNo" value="1"/>            
            <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search" name="searchKeyword">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">商品查詢</button>
          </form>
                    
        </div>
      </nav>
      
    </header>
        
<main role="main">


<!--  購物車互動視窗Modal -->
<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">				        
    
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">購物車清單</h5>
        <span class="text-muted" id="totalPoodsPrice">總金額:${totalGoodsPrice}</span>
      </div>
      
      
      <div class="modal-body">
	      <table class="table table-striped">
		      <thead>
			    <tr>
			      <th scope="col">#</th>
			      <th scope="col">圖片</th>
			      <th scope="col">商品名稱</th>
			      <th scope="col">單價</th>
			      <th scope="col">數量</th>
			      <th scope="col">合計</th>
			    </tr>
			  </thead>
			  
			  <tbody id="carForEach"></tbody>
			  
			  <tbody>
				  <c:forEach items="${shoppingCart}" var="shopping" varStatus="Status">
					<tr>
					  <th scope="row">${Status.index + 1}</th>
					  <td><img class="rounded-circle" src="<%=request.getContextPath()%>/DrinksImage/${shopping.imageName}" alt="Generic placeholder image" width="30" height="30"/></td>
					  <td>${shopping.goodsName}</td>
					  <td>${shopping.goodsPrice}</td>
					  <td>${shopping.buyQuantity}</td>
					  <td>${shopping.totalPrice}</td>
					</tr>
				  </c:forEach>
			  </tbody>      
		      
	      </table>
      </div>
      
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" onclick="location.href='<%=request.getContextPath()%>/CheckoutForm.jsp'">填寫購買資料</button>
        <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>        
      </div>
      				        
    </div>
  </div>
</div>

<!--  輪播圖片 -->
<!-- 	<div class="container marketing">	 -->
      		
<!-- 		 <div id="myCarousel" class="carousel slide" data-ride="carousel"> -->
<!-- 	        <ol class="carousel-indicators"> -->
<!-- 	          <li data-target="#myCarousel" data-slide-to="0" class="active"></li> -->
<!-- 	          <li data-target="#myCarousel" data-slide-to="1"></li> -->
<!-- 	          <li data-target="#myCarousel" data-slide-to="2"></li> -->
<!-- 	        </ol> -->
<!-- 	        <div class="carousel-inner"> -->
<!-- 	          <div class="carousel-item active"> -->
<!-- 	            <img class="first-slide" src="DrinksImage/coca_cola_advertise.png" alt="First slide"> -->
<!-- 	          </div> -->
	          
<!-- 	          <div class="carousel-item"> -->
<!-- 	            <img class="second-slide" src="DrinksImage/Coffee_advertise.png" alt="Second slide"> -->
<!-- 	          </div> -->
	          
<!-- 	          <div class="carousel-item"> -->
<!-- 	            <img class="third-slide" src="DrinksImage/Tea_advertise.png" alt="Third slide"> -->
<!-- 	          </div> -->
<!-- 	        </div> -->
	        
<!-- 		        <a class="carousel-control-prev" href="#myCarousel" role="button" data-slide="prev"> -->
<!-- 		          <span class="carousel-control-prev-icon" aria-hidden="true"></span> -->
<!-- 		          <span class="sr-only">Previous</span> -->
<!-- 		        </a> -->
<!-- 		        <a class="carousel-control-next" href="#myCarousel" role="button" data-slide="next"> -->
<!-- 		          <span class="carousel-control-next-icon" aria-hidden="true"></span> -->
<!-- 		          <span class="sr-only">Next</span> -->
<!-- 		        </a> -->
<!-- 	      </div> -->
      
<!--     </div> -->
				
<!-- 	<hr class="featurette-divider"> -->
	<br/>	
	<div class="container marketing">
		
	<form name="formName" action="FrontendAction.do?action=buyGoods" method="post">
	<input type="hidden" name="customerID" value="A124243295"/>
	<input type="hidden" name="action" value="buyGoods"/>
		
		<div class="row">
		
		<c:forEach items="${homePageGoods}" var="homePageGood">
          <div class="col-lg-4">          
            <img class="rounded-circle" src="<%=request.getContextPath()%>/DrinksImage/${homePageGood.imageName}" alt="Generic placeholder image" width="140" height="140">
            <h4>${homePageGood.goodsName}</h4>
            <p>${homePageGood.goodsPrice} 元/罐</p>
            <input type="hidden" name="goodsID" value="${homePageGood.goodsID}">
            <input type="hidden" name="goodsPrice" value="${homePageGood.goodsPrice}">
            <input type="hidden" name="goodsName" value="${homePageGood.goodsName}">
            <input type="hidden" name="imageName" value="${homePageGood.imageName}">
			<!--設定最多不能買大於庫存數量-->
			購買<input type="number" id="buyQuantitys_${homePageGood.goodsID}" name="buyQuantity" min="0" max="${homePageGood.goodsQuantity}" size="5" value="0">罐
			<br/>
			<small class="text-muted">(庫存 ${homePageGood.goodsQuantity} 罐)</small>
			<br/>
			<button type="button" class="btn btn-outline-warning" value="加入購物車" onClick="shoppingCartFunction(${homePageGood.goodsID})" style="text-align:center;line-height:10px; width:45px; height:35px;">🛒</button>
          </div>
        </c:forEach>
          
        </div>
		
		<!-- 購買資訊 -->
		 <div class="row featurette">
	          <div class="col-md-7">
	          	<div class="text-center">
	            <h2 class="featurette-heading">Consumer details</h2><br/>
	            <c:forEach items="${viewList}" var="viewList">
	            <p class="lead">${viewList}</p>
	            </c:forEach>
	            <% session.removeAttribute("viewList"); %> 
	          	</div>
	          </div>
	          <div class="col-md-5">
	          		<div class="text-center">
<!-- 		            <img border="0" src="DrinksImage/coffee.jpg" width="100" height="100" > -->
		            <h4 class="featurette-heading">Vending Machine</h4><br/>
		            <h4>⇩  Coin slot ⇩</h4>
		            <font face="微軟正黑體" size="4">
						<b>投入⇨  </b>
						<input type="number" name="inputMoney" max="100000" min="0"  size="5" value="0">
						<b> ⇦元整</b><br/><br/>		
						<b><input type="submit" value="現場購買"></b>	<!--<input type="button" value="宅配購物" onClick="submitFunction()">-->																			
					</font>
					</div>
	          </div>
        </div>
				
	<hr class="featurette-divider">		
	</form>		
	</div>

	<!-- 總頁數int的值，c:set到var -->
	<c:set var="forEachPageNO" value="${forEachPageNO}"/>
	<!-- 分頁功能表 -->
	<div class="container marketing">
		<ul class="pagination">
		      <li class="page-item">
		        <a class="page-link" href="<c:url value="FrontendAction.do?action=homePageGoods&pageNo=1"/>">首頁</a>
		      </li>
		      
			  <!-- 分頁等於1 ，&laquo; 上一頁箭頭符號--> 
		      <c:if test="${pageNo eq 1}">
		      <li class="page-item disabled">
		        <a class="page-link">&laquo;</a>
		      </li>
		      </c:if>
		      
		      <!-- 分頁不等於1 -->
		      <c:if test="${pageNo ne 1}">
		      <li class="page-item">
		        <a class="page-link" href="<c:url value="FrontendAction.do?action=homePageGoods&pageNo=${pageNo-1>1?pageNo-1:1}"/>">&laquo;</a>
		      </li>
		      </c:if>
	      	  
	      	  <!-- page-item active ，當前分頁底色亮起 -->
		      <c:forEach items="${forEachPageNO}" var="pageloop" varStatus="loop">
		      	<c:set var="active" value="${pageloop==pageNo?'page-item active':'page-item'}"/>
			      	<li class="${active}">			      	
			      		<a class="page-link" href="FrontendAction.do?action=homePageGoods&pageNo=${pageloop}">${pageloop}</a>
			      	</li>
			  </c:forEach>
		      
		      <!-- &raquo; 下一頁箭頭符號--> 
		      <c:if test="${pageNo eq fn:length(totalPagesNO)}">
		      <li class="page-item disabled">
		      	<a class="page-link">&raquo;</a></li>
		      </c:if>
		      
		      <c:if test="${pageNo ne fn:length(totalPagesNO)}">
		      <li class="page-item">
		      	<a class="page-link" href="FrontendAction.do?action=homePageGoods&pageNo=${pageNo+1>1?pageNo+1:1}">&raquo;</a></li>
		      </c:if>
		      
		      <li class="page-item">
		        <a class="page-link" href="FrontendAction.do?action=homePageGoods&pageNo=${fn:length(totalPagesNO)}">最終頁</a>
		      </li>
		      
	    </ul>
        
        <p class="float-right"><a href="#">Back to top</a></p>        
    </div>
				
</main>
</body>

</html>