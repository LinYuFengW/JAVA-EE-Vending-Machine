<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>

<script type="text/javascript" src="js/jquery-3.3.1.slim.min.js"></script>

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
<body>



送貨日期<input type="date" id="time" name="shipmentDate" min="" value=""
									style="height: 25px; width: 180px; font-size: 16px; text-align: center;" />

</body>
</html>