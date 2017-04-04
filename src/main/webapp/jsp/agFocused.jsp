<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"    content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author"      content="AgGuard">
	
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
<link rel="stylesheet" href="/assets/css/mask.css">
<link rel="stylesheet" href="/assets/css/spinner.css" >

<!--data table css-->
<link href="https://cdn.datatables.net/1.10.9/css/dataTables.bootstrap.min.css" rel="stylesheet">

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
	<script src="/assets/js/html5shiv.js"></script>
	<script src="/assets/js/respond.min.js"></script>
	<![endif]-->
</head>

<body ng-app="aggApp">
    <div class="mask" id="popup_mask" style="z-index: 10001; display: none;"> </div>
	<jsp:include page="spinner.jsp"></jsp:include>
	<!-- Fixed navbar -->
	<div class="navbar navbar-inverse navbar-fixed-top headroom" >
  <div class="container">
    <div class="navbar-header"> 
      <!-- Button for smallest screens -->
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse"><span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
      <a class="navbar-brand animated fadeInDownBig" href="/agg/home"><img src="/assets/images/logo.png" alt="AgGuard"></a> </div>
    <div class="navbar-collapse collapse">
      <ul class="nav navbar-nav pull-right">
        <li><a href="/agg/home">Home</a></li>
        <li class="active"><a href="/agg/register">Register</a></li>
        <li><a href="/agg/aboutRisk">About Risk</a></li>
        <li><a href="/agg/whoWeAre">Who We Are</a></li>
        
        
        <li><a href="/agg/momsPinkTractor">Mom's Pink Tractor</a></li>
      </ul>
    </div>
    <!--/.nav-collapse --> 
  </div>
</div>
<!-- /.navbar --> 

	<header id="head" class="secondary"></header>

	<!-- container -->
	<div class="container"> 

		<div class="row">
			
			<!-- Article main content -->
			<article class="col-md-12 maincontent">

	<div class="inner-main">
		
		<div class="main-login main-center registration_overflow">
                    <h3 class="mar-bot">Ag Focused</h3>
		<p>The Ag industry is changing, and the speed of change seems to be accelerating.  So, we focus on what we know best.</p>
			<p>Because of the experience of our team and the broad exposure we have to the industry, we can be a trusted partner and source for advice and insight.</p>
			
			<p>Whether it is how to diagnose a failure, source a part or understand the reliability characteristics of a particular model, we can help.</p>
			
			
			</div>
			
	</div>
	<!--inner main-->


</article>
<!-- /Article -->

<footer id="footer" class="top-space">
  <div class="footer1">
    <div class="container">
      <div class="row">
        <div class="col-md-5 panel">
          <h3 class="panel-title">Lates News</h3>
          <div class="panel-body">
            <p>Lorem ipsum dolor amet, consectetur adipiscing elit. Aenean leo lectus sollicitudin convallis eget libero. Aliquam laoreet tellus ut libero semper, egestas velit malesuada. Sed non risus eget dolor amet vestibulum ullamcorper. Integer feugiat molestie.</p>
          </div>
        </div>
        <div class="col-md-4 panel contact">
          <h3 class="panel-title">
          Contact Info
          </h3>
          <div class="panel-body">
            <p>Ag Guard LLC, 21295 Hollingsworth Road, Tonganoxie, KS 66086.</p>
            <ul>
              <li><i class="fa fa-phone"></i>816-223-1978</li>
              <li><a href="#"><i class="fa fa-envelope-o"></i> contact@agguard.com</a></li>
              
            </ul>
          </div>
        </div>
        <div class="col-md-3 panel">
          <h3 class="panel-title">Follow Us</h3>
          <div class="panel-body">
            <p class="follow-me-icons"> <a href=""><i class="fa fa-twitter fa-2"></i></a> <a href=""><i class="fa fa-dribbble fa-2"></i></a> <a href=""><i class="fa fa-github fa-2"></i></a> <a href=""><i class="fa fa-facebook fa-2"></i></a> <a href=""><i class="fa fa-youtube fa-2"></i></a> <a href=""><i class="fa fa-pinterest fa-2"></i></a> </p>
          </div>
        </div>
      </div>
      <!-- /row of panels --> 
    </div>
  </div>
  <div class="footer2">
    <div class="container">
      <div class="row">
        <div class="col-md-6 panel">
          <div class="panel-body">
            <p class="simplenav"> <a href="index.html">Home</a> | <a href="#">Who we are</a> | <a href="#">Register</a> | <a href="#">About Risk</a> | <a href="#">Mom's Pink Tractor</a> </p>
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
