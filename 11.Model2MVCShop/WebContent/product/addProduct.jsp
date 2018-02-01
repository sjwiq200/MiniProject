
<%@ page import="com.model2.mvc.service.domain.Product" %><%--
  Created by IntelliJ IDEA.
  User: JW
  Date: 2017. 10. 30.
  Time: AM 11:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=euc-kr" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>




<html>
<head>
    <title>상품등록</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
	<!--  ///////////////////////// Bootstrap, jQuery CDN ////////////////////////// -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
	
	<!--  ///////////////////////// CSS ////////////////////////// -->
	<style>
       body > div.container{
        	border: 3px solid #D6CDB7;
            margin-top: 10px;
        }
    </style>
    
    <script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
	<script type="text/javascript">
		$(function(){
			$("button.btn.btn-primary").on("click",function() {
				self.location = "/product/listProduct?menu=manage";
			})
			$("a[href='#' ]").on("click" , function() {
				self.location = "../product/addProductView.jsp";
			});
			
		})
	</script>

</head>

<body bgcolor="#ffffff" text="#000000">
	<div class="navbar  navbar-default">
        <div class="container">
        	<a class="navbar-brand" href="/index.jsp">Model2 MVC Shop</a>
   		</div>
   	</div>
   	
   	<div class="container">
   		<h1 class="bg-primary text-center">상 품 등 록</h1>
   		
   		<form class="form-horizontal">
   			<div class="form-group">
   				<label for="userId" class="col-sm-offset-1 col-sm-3 control-label">상 품 명</label>
	   			<div class="col-sm-4">
	   				<input type="text" class="form-control" placeholder="${product.prodName}" readonly >
	   				
				</div>
			</div>
			
			<div class="form-group">
		    		<label for="password" class="col-sm-offset-1 col-sm-3 control-label">상품상세정보</label>
			    <div class="col-sm-4">
			    		<input type="text" class="form-control" placeholder="${product.prodDetail}" readonly>
			      	
			    </div>
		  </div>
		  
		  <div class="form-group">
			    <label for="password2" class="col-sm-offset-1 col-sm-3 control-label">제조일자</label>
			    <div class="col-sm-4">
			    		<input type="datetime" class="form-control" placeholder="${product.manuDate}">
			      	
			    </div>
		  </div>
		  
		  <div class="form-group">
			    <label for="password2" class="col-sm-offset-1 col-sm-3 control-label">상품 이미지</label>
			    <div class="col-sm-4">
			    		<script>
			    		</script>

			    		<img src="/images/uploadFiles/${product.fileName}" width="400" height:auto/>
			    </div>
		  </div>
		  
		  
		  <div class="form-group">
			    <label for="password2" class="col-sm-offset-1 col-sm-3 control-label">가 격</label>
			    <div class="col-sm-3">
			    		<input type="text" class="form-control" placeholder="${product.price}" readonly>
			      	  
			    </div>
			    <div class="col-sm-1">원</div>
		  </div>
		  
		  <div class="form-group">
			    <label for="password2" class="col-sm-offset-1 col-sm-3 control-label">상품 개수</label>
			    <div class="col-sm-3">
			    		<input type="text" class="form-control" placeholder="${product.prodCount}" readonly>
			      	
			    </div>
			    <div class="col-sm-1">개</div>
		  </div>
		  
		  <div class="form-group">
		    <div class="col-sm-offset-4  col-sm-4 text-center">
		      <button type="button" class="btn btn-primary"  >확 &nbsp;인</button>
			  <a class="btn btn-primary btn" href="#" role="button">추가등록</a>
		    </div>
		  </div>
		  
   		</form>
   	</div>


</body>
</html>
