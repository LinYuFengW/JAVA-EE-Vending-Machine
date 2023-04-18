<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="java.math.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
		
	<h4 class="text-center" style="color:#FF7878;">商品列表</h4>
	
	<table class="table table-striped">
	  <thead>
	    <tr>
	      <th scope="col">商品編號</th>
	      <th scope="col">商品名稱</th>
	      <th class="text-center" scope="col">商品預覽</th>
	      <th class="text-center" scope="col">商品價格</th>
	      <th class="text-center" scope="col">現有庫存</th>
	      <th class="text-center" scope="col">商品狀態</th>
	    </tr>
	  </thead>	  	  
	  
	  <tbody>
	  	<c:forEach items="${commoditys}" var="commodity" varStatus="status">
						
		    <tr>
		      <th scope="row">${commodity.goodsID}</th>
		      <th scope="row">${commodity.goodsName}</th>
		      
		      <td class="text-center">
		      ${commodity.goodsName}
		      <c:set var="goodsName" value="${commodity.goodsName}"/>

<%-- 			  <c:set var="goodsName" scope="request" value="${commodity.goodsName}"/> --%>
<%-- 			  <c:set var="imageName" scope="request" value="${commodity.imageName}"/>		      		      	 --%>
			      	<!-- 互動視窗Button trigger modal -->
				    <button type="button" class="btn btn-outline-secondary" data-toggle="modal" data-target="#exampleModalCenter_${status.index}" style="text-align:center;line-height:10px; width:60px; height:25px;">
				      	預覽
				    </button>
			      	
		      		<!--  互動視窗Modal -->
				    <div class="modal fade" id="exampleModalCenter_${status.index}" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
				      <div class="modal-dialog modal-dialog-centered" role="document">
				        <div class="modal-content">				        
				        
				          <div class="modal-header">
				            <h5 class="modal-title" id="exampleModalLongTitle">${goodsName}</h5>
				            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
				              <span aria-hidden="true">&times;</span>
				            </button>
				          </div>
				          <div class="modal-body">				           
				           <img border="0" src="<%=request.getContextPath()%>/DrinksImage/${commodity.imageName}" width="100" height="100" >
				          </div>
				          <div class="modal-footer">
				            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
<!-- 				            <button type="button" class="btn btn-primary">Save changes</button> -->
				          </div>
				          				        
				        </div>
				      </div>
				    </div>
		      
		      </td>
		      
		      <td class="text-center">${commodity.goodsPrice}</td>
		      <td class="text-center">${commodity.goodsQuantity}</td>
		      <td class="text-center">${commodity.status}</td>
		    </tr>
		</c:forEach>
	  </tbody>
	</table>
	
</body>
</html>