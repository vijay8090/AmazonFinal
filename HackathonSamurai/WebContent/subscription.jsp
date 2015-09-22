<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Bootstrap 3 Fluid Layout Example</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	
	<script>
	
	function performSubmit(){
		
		 var frm = $(document.subscriptionForm);
		 var data = frm.serializeArray();
		 
		
		
		var finalData ={};
		
		for(var i=0;i<data.length;i++){
			
			var obj = data[i];
			
			if(obj.name != 'conditionStr'){
				
				
				finalData[obj.name] = obj.value;
				
				
			}
			
			
		}
		
		var strData =JSON.stringify(finalData);
		alert(strData);
		 
		 $("conditionStr").val(strData);
		 
		 
		 
		 $.ajax({
			  type: "POST",
			  url: "loginController?btn_action=saveSubscription",
			  data: "&conditionStr="+strData,
			  success: function( data ) {
				  alert( "Data Loaded: " + data );
			  }
			});
		 
		 
		// frm.action="loginController?btn_action=saveSubscription&conditionStr"+strData;
		//frm.submit();
	}
	
	</script>
	
</head>
<body>
	<jsp:include page="nav.jsp"></jsp:include>
	<div class="jumbotron">
		<div class="container">
			<p>&nbsp;</p>
			<p>Please specify your conditions : For Demo Purpose</p>
			<form class="form-signin" name="subscriptionForm" action="loginController?btn_action=saveSubscription" method="POST">
				<table width="50%">

					<tr>
						<td>List Price</td>
						<td><input type="text" name="listPrice" id="listPrice"
							class="form-control" placeholder="List Price Eg: >=10 && <=20" required autofocus></td>
					</tr>
					<tr>
						<td>Publisher</td>
						<td><input type="text" name="publisher" id="publisher"
							class="form-control" placeholder="Publisher" required autofocus>
						</td>
						</tr>
					<tr>
						<td>release date</td>
						<td><input type="text" name="releasedate" id="releasedate"
							class="form-control" placeholder="Release Date" required autofocus>
						</td>
						</tr>
					<tr>
						<td>authors</td>
						<td><input type="text" name="authors" id="authors"
							class="form-control" placeholder="Authors" required autofocus>
						</td>
						</tr>
					<tr>
						<td>Title</td>
						<td><input type="text" name="title" id="title"
							class="form-control" placeholder="Title" required autofocus>
						</td>
					</tr>

				</table>

<p>&nbsp;</p>
<input type="hidden" name="conditionStr" id="conditionStr" value=""/>
				
				<button class="btn btn-lg btn-primary" onClick="performSubmit()" type="button">Subscribe</button>

			</form>

		</div>
	</div>

</body>
</html>
