<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Bootstrap後台管理</title>
        <link rel="stylesheet" href="css/custom.css">
        <link rel="stylesheet" href="css/v5.4.2.css.all.css" integrity="sha384-/rXc/GQVaYpyDdyxK+ecHPVYJSN9bmVFBvjA/9eOB+pb3F2w2N6fc5qB9Ew5yIns"crossorigin="anonymous">
        <link rel="stylesheet" href="css/4.1.1.bootstrap.min.css"integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
        <script src="js/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"crossorigin="anonymous"></script>
        <script src="js/1.14.3.popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"crossorigin="anonymous"></script>
        <script src="js/4.1.1.bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"crossorigin="anonymous"></script>
        <script src="js/2.7.0.Chart.bundle.min.js"></script>		
    </head>
    
    <style>
        body {
            font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", "Microsoft JhengHei", Roboto, "Helvetica Neue", Arial, sans-serif
        }
    </style>

    <body>
        <div class="container main-contant py-5">
            <h1 class="text-center mb-3 text-secondary">Bootstrap 交易結果</h1>
            <div class="form-row text-center">
                <div class="col">
                    <div class="alert alert-success alert-rounded" role="alert">交易成功!</div>
                </div>
            </div>
            <div class="row justify-content-center py-5">
                <div class="col-md-8">
                    <h3 class="text-center pb-2">購買資訊</h3>
                    <table class="table table-sm text-center">
                        <thead>
                        	
                            <th class="text-center">#</th>
                            <th class="text-center">商品圖片</th>
                            <th class="text-center">商品名稱</th>
                            <th width="100px" class="text-center">數量</th>
                            <th width="80px" class="text-center">小計</th>
                        </thead>
                        <tbody>
                        	<c:forEach items="${endShoppingCart}" var="shopping" varStatus="Status">
                            <tr>
                                <td class="align-middle">${Status.index + 1}</td>
                                <td class="align-middle">
                                    <img src="<%=request.getContextPath()%>/DrinksImage/${shopping.imageName}" width="50" height="50px">
                                </td>
                                <td class="align-middle">${shopping.goodsName}</td>
                                <td class="align-middle">${shopping.buyQuantity}</td>
                                <td class="align-middle text-right">${shopping.totalPrice}</td>
                            </tr>
                            </c:forEach>
                            
                            <tr>
                                <td colspan="4" class="text-right">總計</td>
                                <td class="align-middle text-right"><strong>${endTotalPoodsPrice}</strong></td>
                            </tr>
                        </tbody>
                    </table>
                    <h3 class="text-center pb-2 pt-2">購買人</h3>
                    <table class="table table-sm text-center">
                        <tbody>
                            <tr>
                                <th class="py-3">Email</th>
                                <td class="align-middle">${sessionScope.account.email}</td>
                            </tr>
                            <tr>
                                <th class="py-2">姓名</th>
                                <td class="align-middle">${sessionScope.account.name}</td>
                            </tr>
                            <tr>
                                <th class="py-3">電話</th>
                                <td class="align-middle">${sessionScope.account.phone}</td>
                            </tr>
                            <tr>
                                <th class="py-3">地址</th>
                                <td class="align-middle">${sessionScope.transactionComplete.completeAddress}</td>
                            </tr>
                            <tr>
                                <th class="py-3">超商取貨</th>
                                <td class="align-middle">${sessionScope.transactionComplete.supermarket}</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            
    		<div class="row justify-content-center py-5">
				<button type="button" class="btn btn-secondary" onclick="javascript:location.href='FrontendAction.do?action=homePageGoods'">回購物首頁</button>
			</div>
            
        </div>       
        
    </body>

</html>