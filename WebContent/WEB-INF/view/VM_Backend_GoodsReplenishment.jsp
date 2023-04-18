<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Language" content="zh-tw">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>

<title>販賣機-後臺</title>
	<script type="text/javascript">	
		//預先載入選單
		function commoditySelected(){
			document.updateCommodityForm.action.value = "modifyView";
			document.updateCommodityForm.submit();
		}
	</script>
	
	<script type="text/javascript">
	$(document).ready(function(){
		//做法二 form 表單使用 button ，透過 ajax 送出
		$("#accountSubmit").bind("click",function(){			
			// 也可一次送出From表單所有資料
			var fromData = $('#updateForm').serialize();
			//alert(fromData);
			$.ajax({
				  url: '<%=request.getContextPath()%>/BackendAction.do?action=updateGoods', // 指定要進行呼叫的位址
				  type: "POST", // 請求方式 POST/GET
				  data: fromData, // 傳送至 Server的請求資料(物件型式則為 Key/Value pairs)
				  dataType : 'json', // Server回傳的資料類型
				  async : true, // 是否同部請求
				  cache : false, // 從瀏覽器中抓 cache
				  success: function(updateData) { // 請求成功時執行函式					  	
					  $("#updateMessage").text(updateData.updateMessage);
					  $("#updatePrice").text(updateData.updatePrice);
					  $("#stockQuantity").text("目前數量：" + updateData.stockQuantity);
					  $("#updateQuantity").text("0");
					  $("#updateStatus").text("目前商品狀態：" + updateData.updateStatus);
					alert("更新成功!");
				  }, 
				  error: function() { // 請求發生錯誤時執行函式
				    alert("更新失敗!");
				  }
			});
		});	
	});
	</script>
	
</head>
<body>
	<%@ include file="VM_Backend_FunMenu.jsp" %>
	<br/>
		
	<h4 class="text-center" style="color:#FF7878;">商品維護作業</h4><br/>
	
	<div style="margin-left:25px;">
	<form name="updateCommodityForm" id="updateForm" action="BackendAction.do" method="post">
		<input type="hidden" name="action" value="updateGoods"/>
		<p><a class="navbar-brand" href="#" id="updateMessage">${sessionScope.modifyResultMessage}</a></p>
		<% session.removeAttribute("modifyResultMessage"); %>
		<p>
			飲料名稱：
			 <select name="goodsID" onchange="commoditySelected();">
			 	<option value="">----- 請選擇 -----</option>
			 		<c:forEach items="${commoditysList}" var="commoditys">
			 			<option <c:if test="${commoditys.goodsName eq modifyView.goodsName}">selected</c:if>
							value="${commoditys.goodsID}">
							${commoditys.goodsName}				
			 			</option>
			 		</c:forEach>				
			</select>
		</p>
					
		<p>
			目前及更改價格： 
			<input type="number" name="goodsPrice" id="updatePrice" size="5" value="${modifyView.goodsPrice}" min="0" max="1000">
		</p>
		
		<p><a class="navbar-brand" href="#" id="stockQuantity">目前數量：${modifyView.goodsQuantity}</a></p>
		<p>
			補貨數量：
			<input type="number" name="goodsQuantity" id="updateQuantity" size="5" value="0" min="0" max="1000">
		</p>
		
		<p><a class="navbar-brand" href="#" id="updateStatus">目前商品狀態：${modifyView.status}</a></p>
		<p>
			商品狀態：
			<select name="status">
				<option value="1">上架</option>
				<option value="0">下架</option>				
			</select>
		</p>		
		<p>
			<!-- 做法一 form 表單使用 submit 送出 -->
<!-- 			<input type="submit" value="送出"> -->
			
			<!-- 做法二 form 表單使用 button ，透過 ajax 送出 -->
			<input type="button" id="accountSubmit" value="送出">
		</p>
	</form>
	</div>
</body>
</html>