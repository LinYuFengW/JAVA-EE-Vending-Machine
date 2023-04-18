<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="java.math.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Language" content="zh-tw">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!-- Required meta tags -->
<meta charset="utf-8">

<title>販賣機-後臺</title>
	<script type="text/javascript">

	</script>
</head>
<body>
	<%@ include file="VM_Backend_FunMenu.jsp" %>
	<br/>
		
	<h4 class="text-center" style="color:#FF7878;">銷售報表</h4><br/>
	<div style="margin-left:25px;">
	<form action="BackendAction.do" method="get">
		<input type="hidden" name="action" value="querySalesReport"/>
		起 &nbsp; <input type="date" name="queryStartDate" value="${date.startDate}" style="height:25px;width:180px;font-size:16px;text-align:center;"/>
		&nbsp;
		迄 &nbsp; <input type="date" name="queryEndDate" value="${date.endDate}" style="height:25px;width:180px;font-size:16px;text-align:center;"/>	
		<input type="submit" value="查詢" style="margin-left:25px; width:50px;height:32px"/>
	</form>
	<br/>

	<table class="table table-striped">
	  <thead>
	    <tr>
	      <th class="text-center" scope="col">訂單編號</th>
	      <th scope="col">顧客姓名</th>
	      <th class="text-center" scope="col">購買日期</th>
	      <th class="text-center" scope="col">飲料名稱</th>
	      <th class="text-center" scope="col">購買單價</th>
	      <th class="text-center" scope="col">購買數量</th>
	      <th class="text-center" scope="col">購買金額</th>
	    </tr>
	  </thead>	  	  
	  
	  <tbody>
	  	<c:forEach items="${salesReports}" var="salesReport">						
		    <tr>
		      <th class="text-center" scope="row">${salesReport.orderID}</th>
		      <th scope="row">${salesReport.customerName}</th>
		      <td class="text-center">${salesReport.orderDATE}</td>
		      <td class="text-center">${salesReport.goodsName}</td>
		      <td class="text-center">${salesReport.goodsPrice}</td>
		      <td class="text-center">${salesReport.goodsQuantity}</td>
		      <td class="text-center">${salesReport.totalPrice}</td>
		    </tr>
		</c:forEach>
	  </tbody>
	</table>
	
</body>
</html>