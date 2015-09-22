<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Bootstrap 3 Fluid Layout Example</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="nav.jsp"></jsp:include>
<div class="jumbotron">
    <div class="container-fluid">
        <p>Search Product </p>
          <form  class="form-signin"  action="loginController?btn_action=productQuery" method="POST">
          
          <input type="text" name= "productId" id="productId"  placeholder="ProductId" required autofocus>
          
           <input type="hidden" name="btn_action" value="productQuery" />
             <button class="btn btn-lg btn-primary" type="submit">Fetch</button>
         
         </form>
         
         <% java.util.LinkedHashMap<String,String> productMap = (java.util.LinkedHashMap<String,String>)request.getAttribute("productMap");
        		
        		if(productMap != null){
        		
        		for ( String key : productMap.keySet() ) { %>
        			<p><%=key%> : <%= productMap.get(key)%> </p>
        			
        			 <% 
        		}
        		}
         %>
         
    </div>
</div>

</body>
</html>                                		
