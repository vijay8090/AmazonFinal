<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="jquery-ui.css" rel="stylesheet">
 <script src="js/jquery.js"></script>
 <script src="jquery-ui.min.js" ></script>
 
 <script>
 
 
 var downloadTimeoutPeriod =  100;
	// downloadTimeoutPeriod = 1000*20;
	var ctxPath = "<%=request.getContextPath()%>";
	
	
	var OfflineReloadFunction = function(){	
	
	  
	var sUrl = "help.do?method=getOnlineDownloadLinks";
	var success = function(data){
		//alert(data);
		
	        try {	        
	            var notificationResponse = JSON.parse(data);
	            
	            
	        	if(notificationResponse && notificationResponse.length > 0){
	        	showNewMessage(notificationResponse);
	        	} else {
	        	$("#downloadLink").dialog('close');
	        	$("#allDownload").hide();
	        	}
	        	 
	        	 setTimeout(function(){OfflineReloadFunction();},2*1000*10);
	           } catch(x){
  				displayMessagePanel("Session expired, please login again.");
  			}
		
	}
	
	
	 $.ajax({
		  type: "POST",
		  url: "loginController?btn_action=getNotification",
		  data: null,
		  success: success
		});
	 
	 
	}
 
 
	function timedRefresh(downloadTimeoutPeriod) {
		setTimeout(function(){OfflineReloadFunction();},downloadTimeoutPeriod);
   	}   
   
   $(document).ready( function() {  
	   $("#allDownload").hide();
	   timedRefresh(downloadTimeoutPeriod);
	   
	   $("#downloadLink").dialog( {
			autoOpen :false,
			modal :true,
			width :"500px",
			height : "auto"
		});
   });
   

   function openDownloadDialog(){
	//   alert("openDownloadDialog");
   OfflineReloadFunction();
   $("#downloadLink").dialog('open');
   }
 
 
 function showNewMessage(notificationResponse){
	 try{
	 $("#downloadLink").html("");
	
	 } catch(e){
	 }

	 var sb = "";
	 sb+="<div id=dynamicDownload><table  cellspacing=2 cellpadding=2 width=100%><tr>      ";
	 sb+="<th> S.No </th>";
	 sb+="<th> Contents </th>";
	 sb+="</tr>";
	 for(var x= 0 ; x < notificationResponse.length ; x++){
	 var tempObj = notificationResponse[x];
	 		sb+="<tr> <td class= 'ptdmTd brownText'> "+ (x+1)+" </td>";
	 		sb+="<td > "+tempObj.notification+" </td>" ;
	 		
	 		sb+="</tr>";
	 	}

	 sb+="</table> </div>";
	 sb+="<br\>";
	 $("#downloadLink").html(sb.toString());


	  $("#allDownload").show();
	 }
 


 
 </script>
 
</head>
<body>

<nav id="myNavbar"
		class="navbar navbar-default navbar-inverse navbar-fixed-top"
		role="navigation">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#navbarCollapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Amazon Hackathon</a>
			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse" id="navbarCollapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="subscription.jsp">Home</a></li>
					<li><a href="productQuery.jsp">ProductQuery Demo</a></li>
					<li><a href="loginController?btn_action=logout">Logout</a></li>
				</ul>
				<input type=button id="allDownload" onClick="openDownloadDialog()" value=" Notification" />  &nbsp;&nbsp; <script> $( "#allDownload" ).button();  $("#allDownload").hide();</script>
			</div>
		</div>
	</nav>
<div id="downloadLink" title="User Notifications" style="display:none;">

</div>

</body>
</html>