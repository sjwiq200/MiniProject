<%@ page contentType="text/html; charset=EUC-KR" %>
<%@ page pageEncoding="EUC-KR"%>

<!--  ///////////////////////// JSTL  ////////////////////////// -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!-- ToolBar Start /////////////////////////////////////-->
<div class="navbar  navbar-inverse navbar-bottom">
	
	<div class="container">
	       
		<a class="navbar-brand" href="/index.jsp">Model2 MVC Shop</a>
		
		<!-- toolBar Button Start //////////////////////// -->
		<div class="navbar-header">
		    <button class="navbar-toggle collapsed" data-toggle="collapse" data-target="#targetFooter">
		        <span class="sr-only">Toggle navigation</span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		    </button>
		</div>
		<!-- toolBar Button End //////////////////////// -->
		
	    <!--  dropdown hover Start -->
		<div 	class="collapse navbar-collapse" id="targetFooter" 
	       			data-hover="dropdown" data-animations="fadeInDownNew fadeInRightNew fadeInUpNew fadeInLeftNew">
	         
	         	<!-- Tool Bar 를 다양하게 사용하면.... -->
	             <ul class="nav navbar-nav">
		              <!--  회원관리 DrowDown -->
		              <li class="dropdown">
		                     <a  href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
		                         <span >회사위치</span>
		                     </a>
		              </li>
                 </ul>
                 
                 <ul class="nav navbar-nav">
		              <!--  회원관리 DrowDown -->
		              <li class="dropdown">
		                     <a  href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
		                         <span >실시간 채팅</span>
		                     </a>
		              </li>
                 </ul>
                 
                 <form class="navbar-form navbar-left">
			        <div class="form-group">
			          <input type="text" class="form-control" id="translate" name="translate" placeholder="한국어">
			        </div>
			        <a  type="button" id="papa" class="btn btn-default">translate</a>
		      	 </form>
		      	 
		      	 <form class="navbar-form navbar-center">
			        <a  type="button" id="pdf" class="btn btn-default">htmlToPDF</a>
		      	 </form>
		      	 
		      	 <form class="navbar-form navbar-right">
			        <label for="result" class="label label-success">Result</label></li>
			        <div class="form-group">
			          <input type="text" class="form-control" id="result" readonly>
			        </div>
			        
		      	 </form>
		      	  
		</div>
		<!-- dropdown hover END -->	       
	    
	</div>
		<!-- ToolBar End /////////////////////////////////////-->
 	
   	<!-- pdf Lib -->
   	<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.2.61/jspdf.min.js"></script>
   	 <script src="//cdnjs.cloudflare.com/ajax/libs/jspdf/0.9.0rc1/jspdf.min.js"></script>

  <script src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/0.4.1/html2canvas.min.js"></script>
   	<script type="text/javascript">
   	
		
		//=============  개인정보조회회 Event  처리 =============	
	 	$( "a:contains('회사위치')" ).on("click" , function() {
	 		//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
			
	 		$(self.location).attr("href","/googlemap.jsp");
		});
		
	 	$( "a:contains('htmlToPDF')" ).on("click" , function() {
	 		//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
			
			var doc = new jsPDF();

			var specialElementHandlers = { 
			
			    "body": function (element, renderer) { 
			
			        return true; 
			
			    }
			
			}	
			
			html2canvas($("body"),{
			
			    useCORS: true,
			
			    allowTaint: true,
			
			    onrendered:function(canvas){
			
			        var imgData = canvas.toDataURL('image/jpeg');
			
			        var doc = new jsPDF("p","mm");
			
					console.log(imgData);
			
			        doc.addImage(imgData,'JPEG',0,0);
			
			        doc.save('test.pdf');
			
			    }
			
			});
			
	 		
	 		/*
	 		$(self.location).attr("href","/pdf/htmlToPDF");
	 		
	 		console.log($("html").html());
	 		console.log($("body > *").html());
	 		
	 		$.ajax({
	 			url : "/pdf/htmlToPDF",
	 			method : "POST",
	 			data : $("body > *").html(),
	 			contentType : "application/json; charset=UTF-8",
	 			dataType : "json",
	 			headers : {
	 				"Accept" : "application/json;charset=UTF-8",
					"Content-Type" : "application/json;charset=UTF-8"
	 			}
	 			
	 		})
	 		*/
		});
		
	 	$( "a:contains('실시간 채팅')" ).on("click" , function() {
	 		//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
			
	 		//$(self.location).attr("href","http://192.168.0.10:3000");
	 		window.open("http://192.168.0.36:3000?id=${sessionScope.user.userId}","Chat","location=no");
		});
		
		$("#papa").on("click",function(){
			$.ajax({
				url : "/papago/json/papagoTranslate",
				method : "POST",
				data : $("#translate").val(),
				contentType: "application/x-www-form-urlencoded; charset=UTF-8",
				dataType : "json",
				headers : {
					"Accept" : "application/json;charset=UTF-8",
					"Content-Type" : "application/json;charset=UTF-8"
				},
				success : function(JSONData,status){
					
					var displayValue = JSONData.message.result.translatedText;
					
					$("#result").val(displayValue);
					
				}
			})
			//alert("thank you");
			//$("form").attr("method","post").attr("action","/papago/json/papagoTranslate").submit();
		});
		
		
		
	 	
		
	</script>  