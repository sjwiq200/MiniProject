

<%--
  Created by IntelliJ IDEA.
  User: JW
  Date: 2017. 10. 30.
  Time: PM 12:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=euc-kr" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>상품 목록조회</title>
    
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
   
   
   <!-- jQuery UI toolTip 사용 CSS-->
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <!-- jQuery UI toolTip 사용 JS-->
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	
	<!--  ///////////////////////// CSS ////////////////////////// -->
	<style>
	  body {
            padding-top : 50px;
        }
    </style>

    <script type="text/javascript">

        function fncGetUserList(currentPage){
            document.getElementById("currentPage").value = currentPage;
            document.detailForm.action="/cart/listCart?userId=${user.userId}";
            document.detailForm.submit();
        }
        
        function cartPurchase(){
            document.detailForm.action="/cart/cartPurchase";
            document.detailForm.submit();
        }
        
       $(function(){
    	   	$("td:nth-child(3)").on("click",function(){
    	   		var index = $("td:nth-child(3)").index(this);
    	   		var prodNo = $($("td:nth-child(1) input")[index]).val();
    	   		self.location="/product/getProduct?prodNo="+prodNo+"&menu=${menu}";
    	   	})
    	   	
    	   	$("button").on("click",function(){
    	   		cartPurchase();
    	   	})
    	   	$("td:nth-child(7)").on("click",function(){
    	   		var index = $("td:nth-child(7)").index(this);
    	   		var prodNo = $($("td:nth-child(1) input")[index]).val();
    	   		//alert(prodNo);
    	   		
    	   		self.location="/cart/deleteCart?prodNo="+prodNo+"&userId=${user.userId}";
    	   	})
       })
        
        

    </script>
</head>

<body bgcolor="#ffffff" text="#000000">
	<jsp:include page="/layout/toolbar.jsp" />
	
	<div class="container">
	<form name="detailForm">
	<div class="page-header text-info">
	       <h3>장바구니 목록 조회</h3>
	    </div>
		
		<div class="row">
		
			<div class="col-md-6 text-left">
			    	<p class="text-primary">
			    		전체  ${resultPage.totalCount } 건수, 현재 ${resultPage.currentPage}  페이지
			    	</p>
		    </div>
		    
	    </div>
	    
	    <table class="table table-hover table-striped" >
      
        <thead>
          
          <tr>
          		<th align="center">check</th>
                <th align="center">No</th>
                <th align="left">상품명</th>
                <th align="left">가격</th>
                <th align="left">상세설명</th>
                <th align="left">구매개수</th>
                <th align="left">장바구니 삭제</th>
                
            </tr>
            
        </thead>
       
		<tbody>
		
		<c:set var="i" value="0" />
        <c:forEach var="product" items="${list}">
        <c:set var="i" value="${ i+1 }" />
			
			<tr>
			  <td align="left"><input type="checkbox" name="checkProduct" value="${product.prodNo}"></td>
			  <td>${ i }</td>
			  <td align="left"  title="Click : 회원정보 확인">${product.prodName }</td>
			  <td align="left">${product.price}</td>
			  
              <td align="left">${product.prodDetail }&nbsp;개</td>
              
              <td align="left" id="delivery">${product.prodCount}</td>
			  
			  <td><a href="#">장바구니 삭제</a></td>
			  
			</tr>
          </c:forEach>
        
        </tbody>
      
      </table>
      
      
	  		<div class="col-md-12 text-center ">
	  			<button type="button" class="btn btn-primary">일괄구매</button>
	  		</div>
	  		
	  
 	</div>
 	
	<jsp:include page="../common/pageNavigator_new.jsp"/>
	</form>
	</div><!-- END Container -->

</body>
</html>

