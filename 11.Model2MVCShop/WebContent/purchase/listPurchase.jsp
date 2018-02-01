
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
    <title>���� �����ȸ</title>

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
   
   
   <!-- jQuery UI toolTip ��� CSS-->
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <!-- jQuery UI toolTip ��� JS-->
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
	       <h3>��ǰ�����ȸ</h3>
	    </div>
		
		<div class="row">
		
			<div class="col-md-6 text-left">
			    	<p class="text-primary">
			    		��ü  ${resultPage.totalCount } �Ǽ�, ���� ${resultPage.currentPage}  ������
			    	</p>
		    </div>
		    
		    <div class="col-md-6 text-right">
			    <form class="form-inline" name="detailForm">
			    
				  <div class="form-group">
				    <select class="form-control" name="searchCondition" >
						<c:if test="${menu == 'manage'}">
                            <option value="0" ${ ! empty search.searchCondition && search.searchCondition==0 ? "selected" : "" }>��ǰ��ȣ</option>
                        </c:if>
                        <option value="1" ${ ! empty search.searchCondition && search.searchCondition==1 ? "selected" : "" }>��ǰ��</option>
                        <option value="2" ${ ! empty search.searchCondition && search.searchCondition==2 ? "selected" : ""}>��ǰ����</option>
                        <option value="3" ${ ! empty search.searchCondition && search.searchCondition==3 ? "selected" : ""}>���ݳ�����</option>
                        <option value="4" ${ ! empty search.searchCondition && search.searchCondition==4 ? "selected" : ""}>���ݳ�����</option>
					</select>
				  </div>
				  
				  <div class="form-group">
				    <label class="sr-only" for="searchKeyword">�˻���</label>
				    <input type="text" class="form-control" id="searchKeyword" name="searchKeyword"  placeholder="�˻���"
				    			 value="${! empty search.searchKeyword ? search.searchKeyword : '' }"  >
				  </div>
				  
				  <button type="button" class="btn btn-default">�˻�</button>
				  
				  <!-- PageNavigation ���� ������ ���� ������ �κ� -->
				  <input type="hidden" id="currentPage" name="currentPage" value=""/>
				  
				</form>
	    		</div>
	    </div>
	    
	    <table class="table table-hover table-striped" >
      
        <thead>
          
          <tr>
                <th align="center">No</th>
                <th align="left">��ǰ��</th>
                <th align="left">����</th>
                <th align="left">���Ű���</th>
                <th align="left">�����Ȳ</th>
                <th align="left">��������</th>
                
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
			  <td align="left"  title="Click : ȸ������ Ȯ��">${purchase.purchaseProd.prodName}</td>
			  <td align="left">${purchase.purchaseProd.price}</td>
			  
              <td align="left">${purchase.purchaseCount }&nbsp;��</td>
              
              <td align="left" id="delivery">

                    <c:if test="${purchase.tranCode == '3'}">
                    ��ۿϷ�Ǿ����ϴ�.
	                </c:if>
	                <c:if test="${purchase.tranCode == '1'}">
	                    ������Դϴ�
	                </c:if>
	                <c:if test="${purchase.tranCode == '0'}">
	                    ���ſϷ�����Դϴ�.
	                </c:if>
                </td>
			  
			  <td>
			  	<c:if test="${purchase.tranCode == '1'}">
                    <a href="#">
                        ���ǵ���
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
