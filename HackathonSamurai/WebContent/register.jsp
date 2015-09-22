
<head>

 <script src="js/jquery.js"></script>
    <link media="all" type="text/css" rel="stylesheet" href="css/fullscreen.css">
    <link media="all" type="text/css" rel="stylesheet" href="css/login.css">
     <link href="bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<style>



</style>

<script type="text/javascript">



</script>


</head>

<!--
    you can substitue the span of reauth email for a input with the email and
    include the remember me checkbox
    -->
    <div class="container">
        <div class="card card-container">
            <!-- <img class="profile-img-card" src="//lh3.googleusercontent.com/-6V8xOA6M7BA/AAAAAAAAAAI/AAAAAAAAAAA/rzlHcD0KYwo/photo.jpg?sz=120" alt="" /> -->
            <img id="profile-img" class="profile-img-card" src="images/avatar_2x.png" />
            <p id="profile-name" class="profile-name-card"></p>
            <form class="form-signin" action="loginController" method="POST">
                <span id="reauth-email" class="reauth-email"></span>
                <input type="email" name= "inputEmail" id="inputEmail" class="form-control" placeholder="Email address" required autofocus>
                <input type="password" name= "inputPassword"  id="inputPassword" class="form-control" placeholder="Password" required>
                
                <input type="hidden" name="btn_action" value="register" />
                <button name="submit" class="btn btn-lg btn-primary btn-block btn-signin" type="submit">Register</button>
            </form><!-- /form -->
           
        </div><!-- /card-container -->
    </div><!-- /container -->
