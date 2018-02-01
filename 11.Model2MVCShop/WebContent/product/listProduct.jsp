<%@ page import="java.util.HashMap" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.lang.reflect.Array" %>

<%@ page import="java.sql.Connection" %>

<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="com.model2.mvc.common.Search" %>
<%@ page import="com.model2.mvc.service.domain.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="com.model2.mvc.common.Page" %>

<%@ page import="com.model2.mvc.service.domain.Purchase" %>
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
    <title>��ǰ �����ȸ</title>

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

        function fncGetUserList(currentPage){
            $("#currentPage").val(currentPage)
            $("form").attr("method","POST").attr("action","/product/listProduct?menu=${menu}").submit();
        };
        
        $(function(){
        		$("td:nth-child(2)").css("color","#74F05D");
        		
        		$("button.btn.btn-default").on("click",function(){
        			fncGetUserList(1);
        		});
        		
        		$("td:nth-child(7)").on("click",function(){
        			var index = $("td:nth-child(7)").index(this)
				var prodNoArr = $($("td:nth-child(8) input")[index]).val();
        			
        			self.location="/purchase/updateTranCode?prodNo="+prodNoArr+"&tranCode=1&menu=${menu}";
        		});
        		
        		
        			$("td:nth-child(2)").on("click",function(){
        				var index = $("td:nth-child(2)").index(this)
        				//var count = $(".ct_list_pop td:nth-child(5)").text().trim().split("��");
        				var count = $("td:nth-child(5)").text().trim().split("��");
        				
        				if(count[index] > 0){
	        				var prodNoArr = $($("td:nth-child(8) input")[index]).val();
	        				
	            			self.location="/product/getProduct?prodNo="+prodNoArr+"&menu=${menu}";
        				}
        			})
        			
        			/*
        			$(".ct_list_pop td:nth-child(3)").hover(function(){
        				var index = $(".ct_list_pop td:nth-child(3)").index(this);
        				var prodNo = $($("input:hidden")[index]).val()
        				
        				$.ajax(
        					{
        						url : "/product/json/getProduct/"+prodNo,
        						method : "GET",
        						dataType : "json",
        						headers :{
        							"Accept" : "application/json",
								"Content-Type" : "application/json"
        						},
        						success : function(JSONData, status){
        							var displayValue = "<br/><img class='hover' src='/images/uploadFiles/"+JSONData.fileName+"'/>";
        							$($($(".ct_list_pop td:nth-child(3)")[index])).append(displayValue);
        						}
        					}		
        				)
        				
        			},function(){
        				$(".hover").remove()
        			});
        			*/
        			
        			$(".ct_list_pop td:nth-child(3)").on(
        			{mouseenter:function(){
        				//alert("hello");
        				var index = $(".ct_list_pop td:nth-child(3)").index(this);
        				var prodNo = $($("input:hidden")[index]).val()
        				console.log($($(".ct_list_pop td:nth-child(11)")[index]));
        				
        				$.ajax(
            					{
            						url : "/product/json/getProduct/"+prodNo,
            						method : "GET",
            						dataType : "json",
            						headers :{
            							"Accept" : "application/json",
    								"Content-Type" : "application/json"
            						},
            						success : function(JSONData, status){
            							var displayValue = "<br/><img class='hover' src='/images/uploadFiles/"+JSONData.fileName+"'/>";
            							$($($(".ct_list_pop td:nth-child(3)")[index])).append(displayValue);
            						}
            					}		
            				)
        				
        			},mouseleave:function(){
        				$(".hover").remove();
        			}
        			});
        			
        		
        		
        });

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
          
          <c:if test="${menu == 'manage'}">
            <th align="center">No</th>
            <th align="left" >��ǰ��</th>
            <th align="left">����</th>
            <th align="left">�����</th>
            <th align="left">����</th>
            <th align="left">�������</th>
            <th align="left">��۰���</th>
            </c:if>
            
            <c:if test="${menu == 'search'}">
            <th align="center">No</th>
            <th align="left" >��ǰ��</th>
            <th align="left">����</th>
            <th align="left">�󼼼���</th>
            <th align="left">����</th>
            <th align="left">�������</th>
            </c:if>
            
          </tr>
        </thead>
       
		<tbody>
		
		<c:set var="i" value="0" />
        <c:forEach var="product" items="${list}">
        <c:set var="i" value="${ i+1 }" />
			
			<tr>
			  <td align="center">
			  ${ i }
			  <input type="hidden" value="${product.prodNo }">
			  </td>
			  <td align="left"  title="Click : ȸ������ Ȯ��">${product.prodName}</td>
			  <td align="left">${product.price}</td>
			  
			  <c:if test="${menu == 'manage'}">
			  <td align="left">${product.manuDate}</td>
			  </c:if>
			  
			  <c:if test="${menu == 'search'}">
              <td align="left">${product.prodDetail}</td>
              </c:if>
              
              <td align="left">${product.prodCount }&nbsp;��</td>
              
              <td align="left" id="delivery">

                    <c:if test="${product.prodCount > 0 }">
                            �Ǹ���
                    </c:if>
                    
                    <c:if test="${product.prodCount == 0 }">                    
                         �ǸſϷ�                        
                    </c:if>
                </td>
                
                <td align="left">
                <c:if test="${menu == 'manage'}">
                		<c:if test="${product.proTranCode == '0' || product.proTranCode == '2'}">
                        <c:if test="${menu == 'manage'}">
                            <a href="#">����ϱ�</a>
                        </c:if>
                    </c:if>
                </c:if>
                </td>
			  
			  
			  <td align="left"><input type="hidden" value="${product.prodNo}"></td>
			  
			</tr>
          </c:forEach>
        
        </tbody>
      
      </table>
	  
 	</div>
 	
	<jsp:include page="../common/pageNavigator_new.jsp"/>
	
	</div><!-- END Container -->
	
	<jsp:include page="/layout/footer.jsp" />
	
	

</html>

