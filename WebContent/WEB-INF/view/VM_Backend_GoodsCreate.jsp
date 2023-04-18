<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Language" content="zh-tw">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">


<title>販賣機-後臺</title>
	<script type="text/javascript">

	</script>
</head>
<body>
	<%@ include file="VM_Backend_FunMenu.jsp" %>
	<br/>
	
	<h4 class="text-center" style="color:#FF7878;">商品新增上架</h4><br/>
	<div style="margin-left:25px;">
	<form action="BackendAction.do?action=addGoods" enctype="multipart/form-data" method="post">
		<p><a class="navbar-brand" href="#">${sessionScope.createResultMessage}</a></p>
		<p>
			飲料名稱：
			<input type="text" name="goodsName" size="10" placeholder="Coca-Cola"/>
		</p>
		<p>
			設定價格： 
			<input type="number" name="goodsPrice" size="5" value="0" min="0" max="1000"/>
		</p>
		<p>
			初始數量：
			<input type="number" name="goodsQuantity" size="5" value="0" min="0" max="1000"/>
		</p>
		<p>
			商品圖片：
			<input type="file" name="goodsImage"/>
		</p>
		<p>
			商品狀態：
			<select name="status">
				<option value="1">上架</option>
				<option value="0">下架</option>				
			</select>
		</p>
		<p>
			<input type="submit" value="送出">
		</p>
	</form>
	</div>
</body>
</html>