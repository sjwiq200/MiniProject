
<%--
  Created by IntelliJ IDEA.
  User: JW
  Date: 2017. 11. 1.
  Time: AM 11:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=euc-kr" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>구매 목록조회</title>

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
        function fncGetUserList(currentPage) {
            document.getElementById("currentPage").value = currentPage;
            document.detailForm.submit();
        }
        
        $(function(){
        		$(".ct_list_pop td:nth-child(1)").on("click",function(){
        			var tranCode =$(this).html().trim().split("value=")[1].split('"')[1]; 
        			var tranNo = $(this).html().trim().split("value=")[2].split('"')[1];
        			//if(tranCode != 0){
        			 self.location="/purchase/getPurchase?tranNo="+tranNo;
        			//}
        		})
        		
        		$(".ct_list_pop td:nth-child(3)").on("click",function(){
        			var prodNo = $(this).html().trim().split('value=');
        			
        			self.location="/product/getProduct?prodNo="+prodNo[1].split('"')[1]+"&menu=search";
        		})
        		
        		$("td:nth-child(6)").on("click",function(){
        			var index = $("td:nth-child(6)").index(this);
        			
        			var tranNo = $($("td:nth-child(1) input")[index]).val();
        			
        			self.location="/purchase/updateTranCode?tranNo="+tranNo+"&tranCode=3";
        		})
        		        		
        })
    </script>
</head>

<body bgcolor="#ffffff" text="#000000">
	<jsp:include page="/layout/toolbar.jsp" />
	
	<div class="container">
	
	<div class="page-header text-info">
	       <h3>상품목록조회</h3>
	    </div>
		
		<div class="row">
		
			<div class="col-md-6 text-left">
			    	<p class="text-primary">
			    		전체  ${resultPage.totalCount } 건수, 현재 ${resultPage.currentPage}  페이지
			    	</p>
		    </div>
		    
		    <div class="col-md-6 text-right">
			    <form class="form-inline" name="detailForm">
			    
				  <div class="form-group">
				    <select class="form-control" name="searchCondition" >
						<c:if test="${menu == 'manage'}">
                            <option value="0" ${ ! empty search.searchCondition && search.searchCondition==0 ? "selected" : "" }>상품번호</option>
                        </c:if>
                        <option value="1" ${ ! empty search.searchCondition && search.searchCondition==1 ? "selected" : "" }>상품명</option>
                        <option value="2" ${ ! empty search.searchCondition && search.searchCondition==2 ? "selected" : ""}>상품가격</option>
                        <option value="3" ${ ! empty search.searchCondition && search.searchCondition==3 ? "selected" : ""}>가격낮은순</option>
                        <option value="4" ${ ! empty search.searchCondition && search.searchCondition==4 ? "selected" : ""}>가격높은순</option>
					</select>
				  </div>
				  
				  <div class="form-group">
				    <label class="sr-only" for="searchKeyword">검색어</label>
				    <input type="text" class="form-control" id="searchKeyword" name="searchKeyword"  placeholder="검색어"
				    			 value="${! empty search.searchKeyword ? search.searchKeyword : '' }"  >
				  </div>
				  
				  <button type="button" class="btn btn-default">검색</button>
				  
				  <!-- PageNavigation 선택 페이지 값을 보내는 부분 -->
				  <input type="hidden" id="currentPage" name="currentPage" value=""/>
				  
				</form>
	    		</div>
	    </div>
	    
	    <table class="table table-hover table-striped" >
      
        <thead>
          
          <tr>
                <th align="center">No</th>
                <th align="left">상품명</th>
                <th align="left">가격</th>
                <th align="left">구매개수</th>
                <th align="left">배송현황</th>
                <th align="left">정보수정</th>
                
            </tr>
            
        </thead>
       
		<tbody>
		
		<c:set var="i" value="0" />
        <c:forEach var="purchase" items="${list}">
        <c:set var="i" value="${ i+1 }" />
			
			<tr>
			  <td align="center">
			  	${ i }
			  	
                 <input type="hidden" value="${purchase.tranNo }">
			  </td>
			  <td align="left"  title="Click : 회원정보 확인">${purchase.purchaseProd.prodName}</td>
			  <td align="left">${purchase.purchaseProd.price}</td>
			  
              <td align="left">${purchase.purchaseCount }&nbsp;개</td>
              
              <td align="left" id="delivery">

                    <c:if test="${purchase.tranCode == '3'}">
                    배송완료되었습니다.
	                </c:if>
	                <c:if test="${purchase.tranCode == '1'}">
	                    배송중입니다
	                </c:if>
	                <c:if test="${purchase.tranCode == '0'}">
	                    구매완료상태입니다.
	                </c:if>
                </td>
			  
			  <td>
			  	<c:if test="${purchase.tranCode == '1'}">
                    <a href="#">
                        물건도착
                    </a>
                </c:if>
			  </td>
			  <td align="left"><input type="hidden" value="${purchase.purchaseProd.prodNo}"></td>
			  
			</tr>
          </c:forEach>
        
        </tbody>
      
      </table>
	  
 	</div>
 	
	<jsp:include page="../common/pageNavigator_new.jsp"/>
	
	</div><!-- END Container -->
	
	<jsp:include page="/layout/footer.jsp" />

</body>
</html>
