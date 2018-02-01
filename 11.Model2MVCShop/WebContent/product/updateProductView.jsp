
<%@ page import="com.model2.mvc.service.domain.Product" %><%--
  Created by IntelliJ IDEA.
  User: JW
  Date: 2017. 10. 30.
  Time: PM 5:02
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
    <title>��ǰ��������</title>
    
    <!-- ���� : http://getbootstrap.com/css/   ���� -->
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

    <script type="text/javascript" src="../javascript/calendar.js">
    </script>
    
    <script type="text/javascript">
        
        function fncAddProduct(){
            //Form ��ȿ�� ����
            //var name = document.detailForm.prodName.value;
            var name = $("input[name='prodName']").val();
            //var detail = document.detailForm.prodDetail.value;
            var detail = $("input[name='prodDetail']").val()
            //var manuDate = document.detailForm.manuDate.value;
            var manuDate = $("input[name='manuDate']").val();
            //var price = document.detailForm.price.value;
            var price = $("input[name='price']").val();

            if(name == null || name.length<1){
                alert("��ǰ���� �ݵ�� �Է��Ͽ��� �մϴ�.");
                return;
            }
            if(detail == null || detail.length<1){
                alert("��ǰ�������� �ݵ�� �Է��Ͽ��� �մϴ�.");
                return;
            }
            if(manuDate == null || manuDate.length<1){
                alert("�������ڴ� �ݵ�� �Է��ϼž� �մϴ�.");
                return;
            }
            if(price == null || price.length<1){
                alert("������ �ݵ�� �Է��ϼž� �մϴ�.");
                return;
            }
            
            $("form").attr("enctype","multipart/form-data").attr("method","post").attr("action","/product/updateProduct").submit();
        }
        
        
        $(function(){
        		$("button").on("click",function(){
        			fncAddProduct();
        		})
        		
        		$("a").on("click",function(){
        			history.go(-1);
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
   		<h1 class="bg-primary text-center">�� ǰ �� ��</h1>
   		
   		<form class="form-horizontal">
   			<div class="form-group">
   				<label for="userId" class="col-sm-offset-1 col-sm-3 control-label">�� ǰ ��</label>
	   			<div class="col-sm-4">
	   				<input type="text" class="form-control" name="prodName" value="${product.prodName}" >
				</div>
			</div>
			
			<div class="form-group">
		    		<label for="password" class="col-sm-offset-1 col-sm-3 control-label">��ǰ������</label>
			    <div class="col-sm-4">
			      	<input type="text" class="form-control" name="prodDetail" value="${product.prodDetail }">
			    </div>
		  </div>
		  
		  <div class="form-group">
			    <label for="password2" class="col-sm-offset-1 col-sm-3 control-label">��������</label>
			    <div class="col-sm-4">
			      	<input type="date" class="form-control" name="manuDate" value="${product.manuDate }">
			    </div>
		  </div>
		  
		  <div class="form-group">
			    <label for="password2" class="col-sm-offset-1 col-sm-3 control-label">��ǰ �̹���</label>
			    <div class="col-sm-4">
			      	<input multiple="multiple" type="file" class="form-control" name="files">
			    </div>
		  </div>
		  
		  
		  <div class="form-group">
			    <label for="password2" class="col-sm-offset-1 col-sm-3 control-label">�� ��</label>
			    <div class="col-sm-3">
			      	<input type="text" class="form-control" name="price" value="${product.price }">  
			    </div>
			    <div class="col-sm-1">��</div>
		  </div>
		  
		  <div class="form-group">
			    <label for="password2" class="col-sm-offset-1 col-sm-3 control-label">��ǰ ����</label>
			    <div class="col-sm-3">
			      	<input type="text" class="form-control" name="prodCount" value="${product.prodCount }"> 
			    </div>
			    <div class="col-sm-1">��</div>
		  </div>
		  
		  <div class="form-group">
		    <div class="col-sm-offset-4  col-sm-4 text-center">
		      <button type="button" class="btn btn-primary"  >�� &nbsp;��</button>
			  <a class="btn btn-primary btn" href="#" role="button">��&nbsp;��</a>
		    </div>
		  </div>
		  
   		</form>
   	</div>

</body>
 
</html>