<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport"    content="width=device-width, initial-scale=1.0">
<meta name="description" content="Ag Guard">
<meta name="author" content="Ag Guard">
<title>Ag Guard</title>
<link rel="favicon" href="/assets/images/favicon.png">
<link rel="stylesheet" media="screen" href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,700">
<link rel="stylesheet" href="/assets/css/bootstrap.min.css">
<link rel="stylesheet" href="/assets/css/font-awesome.min.css">
<!-- Custom styles for our template -->
<link rel="stylesheet" href="/assets/css/bootstrap-theme.css" media="screen" >
<link rel="stylesheet" type="text/css" href="/assets/css/da-slider.css" />
<link rel="stylesheet" href="/assets/css/style.css">
<link href="/assets/css/animate.min.css" rel="stylesheet" />
<link rel="stylesheet" href="/assets/css/hover.css">

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
	<script src="/assets/js/html5shiv.js"></script>
	<script src="/assets/js/respond.min.js"></script>
	<![endif]-->
</head>
<body ng-app="aggApp">
<!-- Fixed navbar -->
<div id="loginDiv" class="navbar navbar-inverse navbar-fixed-top headroom" >
  <div class="container">
    <div class="navbar-header"> 
      <!-- Button for smallest screens -->
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse"><span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
      <a class="navbar-brand animated fadeInDownBig" href="/agg/home"><img src="/assets/images/logo.png" alt="vOne HTML5 template"></a> </div>
    <div class="navbar-collapse collapse">
      <ul class="nav navbar-nav pull-right">
        <li class="active"><a href="/agg/home">Home</a></li>
        <li><a href="/agg/register">Register</a></li>
        <li><a href="/agg/aboutRisk">About Risk</a></li>
        <li><a href="/agg/whoWeAre">Who We Are</a></li>
        
        
        <li><a href="/agg/momsPinkTractor">Mom's Pink Tractor</a></li>
      </ul>
    </div>
    <!--/.nav-collapse --> 
  </div>
</div>
<!-- /.navbar --> 

<!-- Header -->
<header id="head">
  <div class="container">
    <div class="banner-content">
      <div id="da-slider" class="da-slider">
        <div class="da-slide">
          <h2>WE FOCUS  </h2>
          <p>Our products protect you from risk.</p>
          <div class="da-img"></div>
        </div>
        <div class="da-slide">
         <h2>WE LISTEN  </h2>
          <p>Our customers are at the center of all that we do.</p>
          <div class="da-img"></div>
        </div>
        <div class="da-slide">
          <h2>WE PAY CLAIMS FAST  </h2>
          <p>Our customers measure claims payment in hours not weeks.</p>
          <div class="da-img"></div>
        </div> 
      </div>
    </div>
  </div>
</header>
<!-- /Header --> 

<!-- Intro -->
<section class="herotxt">
  <div class="container sectionBox home_width">
<div class="row">
                <div class="col-md-6 col-sm-12">
                    
                    <div class="linto">
                    <span><strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Our Core Values</strong></span>
                    	<ul>
                        	<li class="animated fadeInLeftBig "><a href="#">Claims Paid Quickly</a></li>
                            <li class="animated fadeInLeft"><a href="#">Ag Focused</a></li>
                            <li class="animated fadeInLeftBig"><a href="#">Experience with Integrity</a></li>
                            <li class="animated fadeInLeft"><a href="#">Fair and Flexible Terms</a></li>

                        </ul>
                    </div><!--sub nav-->
                    
                </div>            
                <div class="col-md-6 col-sm-12" ng-controller="LoginController as ctrl">
                 	 <form class="form-signin" ng-submit="ctrl.submit()">       
					      <h3 class="form-signin-heading">Please login</h3>
					      <p class="err-msg" id="errMsg"></p>
					      <input type="text" class="form-control" name="username" ng-model="ctrl.user.username" placeholder="Email Address" required="" autofocus="" />
					      <input type="password" class="form-control" name="password" ng-model="ctrl.user.password" placeholder="Password" required=""/>      
					      <label class="checkbox">
					        <input type="checkbox" value="remember-me" id="rememberMe" name="rememberMe"> Remember me
					      </label>
					      <button class="btn btn-lg btn-primary btn-block wobble" type="submit">Login</button>   
					  </form>		
                </div>
            </div>
          </div>
</section>
<!-- /Intro--> 

<!-- Highlights - jumbotron -->
 
<!-- container -->
<section class="sectionBox">
<div class="container"> 
    <div class="row">         
        <div class="col-md-12">
          <div class="heading">  
            <h2>About Us</h2>
            <br/>
          </div> 

<p>AgGuard products are designed for Ag users by Ag equipment experts who first determine your needs and then create products that work for you.</p>

<p>Because of our reputation for integrity and stream-lined processes, our pricing is half of what you see in other industries providing extended warranties.</p>

<p>We pay claims in hours not in weeks.</p>

<p>"Over 60% of farm machines purchases in North America over $10,000 would benefit significantly from an AgGuard extended service contract because of the positive impact to the enterprise derived from the risk mitigation and the equipment's increased trade-in value while covered." (2012 Actuaries Report provided by Wild Jay, Inc.) </p>
              
        </div>
        
    </div>
</div>
</section>
<footer id="footer" class="top-space">
  <div class="footer1">
    <div class="container">
      <div class="row">
        <!-- <div class="col-md-5 panel">
          <h3 class="panel-title">Lates News</h3>
          <div class="panel-body">
            <p>Lorem ipsum dolor amet, consectetur adipiscing elit. Aenean leo lectus sollicitudin convallis eget libero. Aliquam laoreet tellus ut libero semper, egestas velit malesuada. Sed non risus eget dolor amet vestibulum ullamcorper. Integer feugiat molestie.</p>
          </div>
        </div> -->
        <div class="col-md-4 panel contact">
          <h3 class="panel-title">
          Contact Info
          </h3>
          <div class="panel-body">
            <p>Ag Guard LLC, 21295 Hollingsworth Road, Tonganoxie, KS 66086.</p>
            <ul>
              <li><i class="fa fa-phone"></i>816-223-1978</li>
              <li><a href="#"><i class="fa fa-envelope-o"></i>info@agguard.com</a></li>
              
            </ul>
          </div>
        </div>
        <!-- <div class="col-md-3 panel">
          <h3 class="panel-title">Follow Us</h3>
          <div class="panel-body">
            <p class="follow-me-icons"> <a href=""><i class="fa fa-twitter fa-2"></i></a> <a href=""><i class="fa fa-dribbble fa-2"></i></a> <a href=""><i class="fa fa-github fa-2"></i></a> <a href=""><i class="fa fa-facebook fa-2"></i></a> <a href=""><i class="fa fa-youtube fa-2"></i></a> <a href=""><i class="fa fa-pinterest fa-2"></i></a> </p>
          </div>
        </div> -->
      </div>
      <!-- /row of panels --> 
    </div>
  </div>
  <div class="footer2">
    <div class="container">
      <div class="row">
        <div class="col-md-6 panel">
          <div class="panel-body">
<<<<<<< HEAD
            <p class="simplenav"> <a href="/agg/home">Home</a> | <a href="/agg/register">Register</a> | <a href="/agg/aboutRisk">About Risk</a> | <a href="#">Mom's Pink Tractor</a> </p>
=======
            <p class="simplenav"> <a href="/agg/home">Home</a> | <a href="/agg/whoWeAre">Who we are</a> | <a href="/agg/register">Register</a> | <a href="/agg/aboutRisk">About Risk</a> | <a href="/agg/momsPinkTractor">Mom's Pink Tractor</a> </p>
>>>>>>> refs/remotes/origin/maintenance
          </div>
        </div>
        <div class="col-md-6 panel">
          <div class="panel-body">
            <p class="text-right"> Copyright &copy; 2016 - 2017. agguard.com  </p>
          </div>
        </div>
      </div>
      <!-- /row of panels --> 
    </div>
  </div>
</footer>

<!-- JavaScript libs are placed at the end of the document so the pages load faster --> 
<script src="/assets/js/modernizr-latest.js"></script> 
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script> 
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script> 
<script src="/assets/js/jquery.cslider.js"></script> 
<script src="/assets/js/headroom.min.js"></script> 
<script src="/assets/js/jQuery.headroom.min.js"></script> 
<script src="/assets/js/custom.js"></script>
<script src="/assets/js/slider.js"></script>
<script src="/assets/js/angular.min.js"></script>
<script src="/assets/js/app.js"></script>
<script src="/assets/js/service/loginService.js"></script>
<script src="/assets/js/controller/loginController.js"></script>
</body>
</html>