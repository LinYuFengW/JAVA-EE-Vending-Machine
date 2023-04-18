<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>Checkout Form</title>

<!--     <link rel="canonical" href="https://getbootstrap.com/docs/4.0/examples/checkout/"> -->

<!-- Bootstrap core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="css/form-validation.css" rel="stylesheet">

<!-- Bootstrap core JavaScript
    ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="js/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<!--     <script>window.jQuery || document.write('<script src="js/jquery-3.2.1.slim.min.js"><\/script>')</script> -->
<script src="js/popper.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/holder.min.js"></script>

<script type="text/javascript" src="js/jquery-1.4.4.js"></script>

<!--選擇縣市區城-->
<script type="text/javascript">
	$(document).ready(function(){				
		$("#area").bind("change",function(){
			var areaVal = $("#area option:selected").val();
			if("" != areaVal){
				var areaObj = {
					area : areaVal
				};

				$.ajax({
					  url: '<%=request.getContextPath()%>/AreaAction.do?action=countyCity', // 指定要進行呼叫的位址
					  type: "GET", // 請求方式 POST/GET
					  data: areaObj, // 傳送至 Server的請求資料(物件型式則為 Key/Value pairs)
					  dataType : 'json', // Server回傳的資料類型
					  async : true, // 是否非同部請求
					  cache : false, // 從瀏覽器中抓 cache
					  success: function(queryResult) { // 請求成功時執行函式
						  // 移除區域全部項目 
						  $("#areaList option").remove();
						  $("#areaList").append($("<option></option>").attr("value","").text("請選擇縣市"));
						  if(queryResult.queryStatus == true){
						  	$.each(queryResult.areaList, function(key, value){
						  		$("#areaList").append($("<option></option>").attr("value", value).text(value));
						  	});
						  }else{
						  	alert(queryResult.queryMessage);
						  }
					  }, 
					  error: function() { // 請求發生錯誤時執行函式
					    alert("Ajax Error!");
					  }
				});
			} else {
			  	// 移除區域全部項目 
			  	$("#areaList option").remove();			  	
				$("#areaList").append($("<option></option>").attr("value","").text("請選擇縣市"));
			}
		});					
	});
</script>


<!--選擇商店門市-->
<script type="text/javascript">
	$(document).ready(function(){				
		$("#shopArea").bind("change",function(){
			var areaVal = $("#shopArea option:selected").val();
			if("" != areaVal){
				var areaObj = {
					shopArea : areaVal
				};

				$.ajax({
					  url: '<%=request.getContextPath()%>/AreaAction.do?action=convenienceStore', // 指定要進行呼叫的位址
					  type: "GET", // 請求方式 POST/GET
					  data: areaObj, // 傳送至 Server的請求資料(物件型式則為 Key/Value pairs)
					  dataType : 'json', // Server回傳的資料類型
					  async : true, // 是否非同部請求
					  cache : false, // 從瀏覽器中抓 cache
					  success: function(queryResult) { // 請求成功時執行函式
						  // 移除區域全部項目 
						  $("#strongholdArea option").remove();
						  $("#strongholdArea").append($("<option></option>").attr("value","").text("營業門市"));
						  if(queryResult.queryStatus == true){
						  	$.each(queryResult.areaList, function(key, value){
						  		$("#strongholdArea").append($("<option></option>").attr("value", value).text(value));
						  	});
						  }else{
						  	alert(queryResult.queryMessage);
						  }
					  }, 
					  error: function() { // 請求發生錯誤時執行函式
					    alert("Ajax Error!");
					  }
				});
			} else {
			  	// 移除區域全部項目 
			  	$("#strongholdArea option").remove();			  	
				$("#strongholdArea").append($("<option></option>").attr("value","").text("營業門市"));
			}
		});					
	});
</script>


<!--選擇付款方式出現輸入框-->
<script type="text/javascript">
	$(document).ready(function(){
		$("#pay").bind("change",function(){
			var pay = $("#pay option:selected").val();
			if("0" == pay){
				$('#homePayDiv').show();
				$('#outsideFrame').show();
				$('#shopPayDiv').hide();
				$('#creditCardNo').hide();
				$('#creditCardDate').hide();
				$('#securityCode').hide();
			}
			if("1" == pay){
				$('#shopPayDiv').show();
				$('#outsideFrame').show();
				$('#homePayDiv').hide();
				$('#creditCardNo').hide();
				$('#creditCardDate').hide();
				$('#securityCode').hide();
			}
			if("2" == pay){
				$('#creditCardNo').show();
				$('#creditCardDate').show();
				$('#securityCode').show();
				$('#outsideFrame').show();
				$('#homePayDiv').hide();
				$('#shopPayDiv').hide();
			}
			if("" == pay){				
				$('#outsideFrame').hide();
			}
		});
	});	
</script>

<!--送貨日期設定，今天之後日期-->
<script type="text/javascript">
$(document).ready(function(){
	var date = new Date();
	var year = date.getFullYear();
	var month = ('0'+ (date.getMonth() + 1)).slice(-2);
	var day = ('0' + date.getDate()).slice(-2);
	var time = year +'-'+ month +'-'+ day;
	document.getElementById('time').setAttribute('min', time);
});
</script>

</head>

<body class="bg-light">

	<div class="container">
		<div class="py-5 text-center">
			<img class="d-block mx-auto mb-4" src="DrinksImage/BootstrapLogo.png"
				alt="" width="110" height="110">
			<h2>Checkout form</h2>
			<!--         <p class="lead">Bootstrap's form controls.</p> -->
		</div>

		<form class="needs-validation" action="CheckoutAction.do?action=checkout" method="post">
			<div class="row">
				<div class="col-md-4 order-md-2 mb-4">
					<h4 class="d-flex justify-content-between align-items-center mb-3">
						<span class="text-muted">購物車</span> <span
							class="badge badge-secondary badge-pill">${number}</span>
					</h4>

					<ul class="list-group mb-3">

						<c:forEach items="${shoppingCart}" var="shopping">
							<li
								class="list-group-item d-flex justify-content-between lh-condensed">

								<img class="rounded-circle"
								src="<%=request.getContextPath()%>/DrinksImage/${shopping.imageName}"
								alt="Generic placeholder image" width="30" height="30">

								<div>
									<small class="text-muted">${shopping.goodsName}</small><br /> <small
										class="text-muted">購買數量:${shopping.buyQuantity}
										單價:${shopping.goodsPrice}</small>
								</div> <span class="text-muted">${shopping.totalPrice}</span>
							</li>
						</c:forEach>

						<li class="list-group-item d-flex justify-content-between"><span>Total
								(TWD)</span> <strong>$ ${totalGoodsPrice}</strong></li>

						<li class="list-group-item d-flex justify-content-between">
							<div class="card p-2">

								<select id="pay" name="pay" required>									
									<option value="">選擇付款方式</option>
									<option value="0">貨到付款</option>
									<option value="1">超商取貨</option>
									<option value="2">信用卡繳費</option>
								</select>

							</div>
						</li>

					</ul>


					<div class="card p-2" id="outsideFrame" style="display: none;">
						<div class="input-group">

							<div class="input-group-append" id="homePayDiv"
								style="display: none;">
								<!-- 日期+時間，type="datetime-local" -->								
								送貨日期<input type="date" id="time" name="shipmentDate" min="" value=""
									style="height: 25px; width: 180px; font-size: 16px; text-align: center;" />
							</div>

							<div class="input-group-append" id="shopPayDiv"
								style="display: none;">

								<select class="custom-select d-block w-100" id="shopArea"
									name="shopArea">
									<option value="">選擇商店</option>
									<option value="seven">7-11便利商店</option>
									<option value="family">全家便利商店</option>
								</select> <select class="custom-select d-block w-100" id="strongholdArea"
									name="strongholdArea">
									<option value="">營業門市</option>
								</select>

							</div>



							<div class="input-group-append" id="creditCardNo"
								style="display: none;">
								<div class="list-group-item d-flex justify-content-between">
									<div class="row">
										<div class="col">卡號</div>
										<div>
											<input type="text" name="cardNo" placeholder=""
												style="height: 25px; width: 190px; font-size: 10px; text-align: center;" />
										</div>
									</div>
								</div>
							</div>

							<div class="input-group-append" id="creditCardDate"
								style="display: none;">
								<div class="list-group-item d-flex justify-content-between">
									<div class="row">
										<div class="col">有效日期</div>
										<div>
											<input type="text" name="month" placeholder="月份"
												style="height: 25px; width: 79px; font-size: 10px; text-align: center;" />
										</div>
										<div>
											<input type="text" name="years" placeholder="年份"
												style="height: 25px; width: 79px; font-size: 10px; text-align: center;" />
										</div>
									</div>
								</div>
							</div>

							<div class="input-group-append" id="securityCode"
								style="display: none;">
								<div class="list-group-item d-flex justify-content-between">
									<div class="row">
										<div class="col">安全碼</div>
										<div>
											<input type="text" name="code" placeholder=""
												style="height: 25px; width: 174px; font-size: 10px; text-align: center;" />
										</div>
									</div>
								</div>
							</div>

						</div>
					</div>


					<div class="card p-2">
						<button type="button" class="btn btn-secondary"
							onclick="javascript:location.href='FrontendAction.do?action=homePageGoods'">回購物首頁</button>
					</div>

				</div>

				<div class="col-md-8 order-md-1">
					<h4 class="mb-3">收貨地址</h4>

					<div class="row">

						<div class="col-md-6 mb-3">
							<label for="firstName">購買人姓名</label> <input type="text"
								class="form-control" id="firstName" placeholder=""
								value="${sessionScope.account.name}" required>
						</div>

						<div class="col-md-6 mb-3">
							<label for="lastName">收貨人姓名</label> <input type="text"
								class="form-control" id="lastName" placeholder=""
								value="${sessionScope.account.name}" required>
						</div>
					</div>

					<div class="mb-3">
						<label for="username">Username</label> <input type="text"
							class="form-control" id="username" placeholder=""
							value="${sessionScope.account.id}" required>
					</div>

					<div class="mb-3">
						<label for="username">Phone</label> <input type="number"
							class="form-control" id="username" placeholder=""
							value="${sessionScope.account.phone}" required>
					</div>

					<div class="mb-3">
						<label for="email">Email<span class="text-muted">(Optional)</span></label>
						<div class="input-group">
							<div class="input-group-prepend">
								<span class="input-group-text">@</span>
							</div>
							<input type="email" class="form-control" id="email"
								placeholder="" value="${sessionScope.account.email}" required>
						</div>
					</div>

					<div class="row">
						<div class="col-md-5 mb-3">
							<label for="country">縣市</label> <select
								class="custom-select d-block w-100" id="area" name="area">
								<option value="">Choose...</option>
								<option value="台北市">台北市</option>
								<option value="新北市">新北市</option>
								<option value="台中市">台中市</option>
								<option value="高雄市">高雄市</option>
							</select>
						</div>

						<div class="col-md-4 mb-3">
							<label for="state">區域</label>
							<select
								class="custom-select d-block w-100" id="areaList"
								name="areaList" required>
								<option value="">Choose...</option>
							</select>
						</div>

						<div class="col-md-3 mb-3">
							<label for="zip">郵政號碼</label> <input type="text"
								class="form-control" id="areaListZip" name="areaListZip"
								placeholder="" required>
						</div>
					</div>

					<div class="mb-3">
						<label for="address">地址</label> <input type="text"
							class="form-control" id="address" name="address"
							placeholder="XX里(鄉.路)XX段XX巷XX弄XX號" required>
					</div>

					<hr class="mb-4">
					<button class="btn btn-primary btn-lg btn-block" type="submit">購買結帳</button>

				</div>
			</div>
		</form>

		<footer class="my-5 pt-5 text-muted text-center text-small">
		<p class="mb-1">&copy; 2017-2018</p>
		</footer>
	</div>


</body>
</html>