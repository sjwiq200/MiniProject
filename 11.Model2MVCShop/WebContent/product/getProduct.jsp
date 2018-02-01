<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.model2.mvc.service.domain.Product" %><%--
  Created by IntelliJ IDEA.
  User: JW
  Date: 2017. 10. 30.
  Time: PM 2:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=euc-kr" language="java" %>
<%--
<%
    Product product = (Product)request.getAttribute("product");
    String menu = (String)request.getAttribute("menu");
%>
--%>


<html>
<head>

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

    <title>��ǰ������</title>
    
	<script type="text/javascript">
		$(function(){
			$("button:contains('����')").on("click",function(){
				self.location = "/purchase/addPurchase?prod_no=${product.prodNo}";
			})
			
			$("button:contains('����')").on("click",function(){
				history.go(-1);
			})
			
			$("button:contains('��ٱ��� ���')").on("click",function(){
				//self.location = "/cart/addCart?prod_no=${product.prodNo}&userId=${user.userId}&menu=${menu}";
				$("form").attr("method","post").attr("action","/cart/addCart?prod_no=${product.prodNo}&userId=${user.userId}&menu=${menu}").submit();
			})
		})
	</script>
</head>

<body bgcolor="#ffffff" text="#000000">
	<jsp:include page="/layout/toolbar.jsp" />
	
	
	<div class="container">
	<form class="form-inline" name="detailForm">
		<div class="page-header">
	       <h3 class=" text-info">��ǰ����ȸ</h3>
	       
	    </div>
	    
	    <div class="row">
	  		<div class="col-xs-4 col-md-2"><strong>��ǰ��ȣ</strong></div>
			<div class="col-xs-8 col-md-4">${product.prodNo}</div>
		</div>
		
		<hr/>
		
		<div class="row">
	  		<div class="col-xs-4 col-md-2 "><strong>��ǰ��</strong></div>
			<div class="col-xs-8 col-md-4">${product.prodName}</div>
		</div>
		
		<hr/>
		
		<div class="row">
	  		<div class="col-xs-4 col-md-2 "><strong>��ǰ�̹���</strong></div>
			<div class="col-xs-8 col-md-4">
				<c:if test="${filesNames != ''}">
                    <c:forEach var="fileName" items="${fileNames}">
                            <img src="/images/uploadFiles/${fileName}" alt="ttttttt">
                    </c:forEach>
                </c:if>
                 
                <c:if test="${empty fileNames}">
                        <c:if test="${!empty product.fileName}">
                            <img src="/images/uploadFiles/${product.fileName}" alt="ttttttt">
                        </c:if>
                </c:if>
			</div>
		</div>
		
		<hr/>
		
		<div class="row">
	  		<div class="col-xs-4 col-md-2 "><strong>��ǰ������</strong></div>
			<div class="col-xs-8 col-md-4">${product.prodDetail}</div>
		</div>
		
		<hr/>
		
		<div class="row">
	  		<div class="col-xs-4 col-md-2"><strong>��������</strong></div>
			<div class="col-xs-8 col-md-4">${product.manuDate}</div>
		</div>
		
		<hr/>
		
		<div class="row">
	  		<div class="col-xs-4 col-md-2 "><strong>����</strong></div>
			<div class="col-xs-8 col-md-4">${product.price}</div>
		</div>
		
		<hr/>
		
		<div class="row">
	  		<div class="col-xs-4 col-md-2 "><strong>����</strong></div>
			<div class="col-xs-8 col-md-4">${product.prodCount}</div>
		</div>
		
		<hr/>
		
		<div class="row">
	  		<div class="col-xs-4 col-md-2 "><strong>��ٱ��Ͽ� ���� ����</strong></div>
			<div class="col-xs-8 col-md-4">
				<select name="purchaseNumber">
                    <c:forEach var="i" begin="1" end="${product.prodCount}" step="1">
                        <option value="${i}">${i}</option>
                    </c:forEach>
                </select>
			</div>
		</div>
		
		<hr/>
		
		<div class="row">
	  		<div class="col-xs-4 col-md-2 "><strong>�������</strong></div>
			<div class="col-xs-8 col-md-4">${product.regDate}</div>
		</div>
		
		<hr/>
		
		<div class="row">
	  		<div class="col-md-12 text-center ">
	  			<button type="button" class="btn btn-primary">����</button>
	  			<button type="button" class="btn btn-primary">����</button>
	  			<button type="button" class="btn btn-primary">��ٱ��� ���</button>
	  		</div>
		</div>
		
		<br/>
	</form>
	</div>
	
	<jsp:include page="/layout/footer.jsp" />


</body>
</html>