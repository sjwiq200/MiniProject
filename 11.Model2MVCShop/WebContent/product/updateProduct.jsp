
<%@ page import="com.model2.mvc.service.domain.Product" %><%--
  Created by IntelliJ IDEA.
  User: JW
  Date: 2017. 11. 1.
  Time: PM 11:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=euc-kr" language="java" %>


<%--
<%
    Product product = (Product)request.getAttribute("product");
%>
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<html>
<head>

    <!-- 참조 : http://getbootstrap.com/css/   참조 -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
	<!--  ///////////////////////// Bootstrap, jQuery CDN ////////////////////////// -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
	
	<!-- Bootstrap Dropdown Hover CSS -->
   <link href="/css/animate.min.css" rel="stylesheet">
   <link href="/css/bootstrap-dropdownhover.min.css" rel="stylesheet">
   
    <!-- Bootstrap Dropdown Hover JS -->
   <script src="/javascript/bootstrap-dropdownhover.min.js"></script>
	
	<!--  ///////////////////////// CSS ////////////////////////// -->
	<style>
 		body {
            padding-top : 50px;
        }
     </style>

	<script type="text/javascript">
		$(function(){
			$("button").on("click",function(){
				self.location = "/product/listProduct?menu=manage";
			})
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
   		<h1 class="bg-primary text-center">상 품 수 정</h1>
   		
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
			    		<img src="/images/uploadFiles/"+${product.fileName}+'"'/>
			      	<img src="/images/uploadFiles/../../images/empty.GIF"/>
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
		    </div>
		  </div>
		  
   		</form>
   	</div>


</body>
</html>
