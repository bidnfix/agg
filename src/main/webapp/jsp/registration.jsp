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
<div class="navbar navbar-inverse navbar-fixed-top headroom" >
  <div class="container">
    <div class="navbar-header"> 
      <!-- Button for smallest screens -->
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse"><span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
      <a class="navbar-brand animated fadeInDownBig" href="index.html"><img src="/assets/images/logo_bkp.png" alt="vOne HTML5 template"></a> </div>
    <div class="navbar-collapse collapse">
      <ul class="nav navbar-nav pull-right">
        <li class="active"><a href="/agg/home">Home</a></li>
        <li><a href="#">Register</a></li>
        <li><a href="#">About Risk</a></li>
        <li><a href="#">Who We Are</a></li>
        
        
        <li><a href="#">Mom's Pink Tractor</a></li>
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
          <p>on the consequences of riskand particularly the adverse consequences associated with the risk.</p>
          <div class="da-img"></div>
        </div>
        <div class="da-slide">
         <h2>WE FOCUS  </h2>
          <p>on the consequences of risk and particularly the adverse consequences associated with the risk.</p>
          <div class="da-img"></div>
        </div>
        <div class="da-slide">
          <h2>WE FOCUS  </h2>
          <p>on the consequences of risk and particularly the adverse consequences associated with the risk.</p>
          <div class="da-img"></div>
        </div> 
      </div>
    </div>
  </div>
</header>
<!-- /Header --> 

<!-- Intro -->
<section class="herotxt">
  <div class="container sectionBox">
<div class="row">
                <div class="col-md-6 col-sm-12">
                    
                    <div class="linto">
                    <span><strong >"Navigation"</strong> Headding Comes Here</span>
                    	<ul>
                        	<li class="animated fadeInLeftBig "><a href="#">Claims Paid Quickly</a></li>
                            <li class="animated fadeInLeft"><a href="#">Ag Focused</a></li>
                            <li class="animated fadeInLeftBig"><a href="#">Experience with Integrity</a></li>
                            <li class="animated fadeInLeft"><a href="#">Fair and Flexible Terms</a></li>

                        </ul>
                    </div><!--sub nav-->
                    
                </div>            
                <div class="col-md-6 col-sm-12" ng-controller="registrationController">
                 	 <form class="form-horizontal" role="form" ng-submit="submitDealer()">
						<h2>Dealer Registration</h2>
						<div class="form-group">
							<label for="userName" class="col-sm-3 control-label">Username</label>
							<div class="col-sm-9">
								<input type="text" id="userName" name="userName" ng-model="dealer.userName" placeholder="User Name" class="form-control" required="required">
							</div>
						</div>
						<div class="form-group">
							<label for="password" class="col-sm-3 control-label">Password</label>
							<div class="col-sm-9">
								<input type="text" id="password" name="password" ng-model="dealer.password" placeholder="password" class="form-control" required="required">
							</div>
						</div>
						<div class="form-group">
							<label for="contact" class="col-sm-3 control-label">Name</label>
							<div class="col-sm-9">
								<input type="text" id="name" name="name" ng-model="dealer.name" class="form-control" required="required">
							</div>
						</div>
						<div class="form-group">
							<label for="firstName" class="col-sm-3 control-label">First Name</label>
							<div class="col-sm-9">
								<input type="text" id="firstName" name="firstName" ng-model="dealer.firstName" placeholder="firstName" class="form-control" required="required">
							</div>
						</div>
						<div class="form-group">
							<label for="lastName" class="col-sm-3 control-label">Last Name</label>
							<div class="col-sm-9">
								<input type="text" id="lastName" name="lastName" ng-model="dealer.lastName" placeholder="lastName" class="form-control" required="required">
							</div>
						</div>
						<div class="form-group">
							<label for="address1" class="col-sm-3 control-label">Address1</label>
							<div class="col-sm-9">
								<input type="text" id="address1" name="address1" ng-model="dealer.address1" class="form-control" required="required">
							</div>
						</div>
						<div class="form-group">
							<label for="address2" class="col-sm-3 control-label">Address2</label>
							<div class="col-sm-9">
								<input type="text" id="address2" name="address2" ng-model="dealer.address2" class="form-control" required="required">
							</div>
						</div>
						<div class="form-group">
							<label for="city" class="col-sm-3 control-label">City</label>
							<div class="col-sm-9">
								<input type="text" id="city" name="city" ng-model="dealer.city" class="form-control" required="required">
							</div>
						</div>
						<div class="form-group">
							<label for="country" class="col-sm-3 control-label">State/Province</label>
							<div class="col-sm-9">
								<select class="form-control" name="state" ng-model="dealer.state" id="state" required="required">
									<option value="">Select State/Province</option>
									<option value="AL">Alabama</option>
									<option value="AK">Alaska</option>
									<option value="AZ">Arizona</option>
									<option value="AR">Arkansas</option>
									<option value="CA">California</option>
									<option value="CO">Colorado</option>
									<option value="CT">Connecticut</option>
									<option value="DE">Delaware</option>
									<option value="DC">District Of Columbia</option>
									<option value="FL">Florida</option>
									<option value="GA">Georgia</option>
									<option value="HI">Hawaii</option>
									<option value="ID">Idaho</option>
									<option value="IL">Illinois</option>
									<option value="IN">Indiana</option>
									<option value="IA">Iowa</option>
									<option value="KS">Kansas</option>
									<option value="KY">Kentucky</option>
									<option value="LA">Louisiana</option>
									<option value="ME">Maine</option>
									<option value="MD">Maryland</option>
									<option value="MA">Massachusetts</option>
									<option value="MI">Michigan</option>
									<option value="MN">Minnesota</option>
									<option value="MS">Mississippi</option>
									<option value="MO">Missouri</option>
									<option value="MT">Montana</option>
									<option value="NE">Nebraska</option>
									<option value="NV">Nevada</option>
									<option value="NH">New Hampshire</option>
									<option value="NJ">New Jersey</option>
									<option value="NM">New Mexico</option>
									<option value="NY">New York</option>
									<option value="NC">North Carolina</option>
									<option value="ND">North Dakota</option>
									<option value="OH">Ohio</option>
									<option value="OK">Oklahoma</option>
									<option value="OR">Oregon</option>
									<option value="PA">Pennsylvania</option>
									<option value="RI">Rhode Island</option>
									<option value="SC">South Carolina</option>
									<option value="SD">South Dakota</option>
									<option value="TN">Tennessee</option>
									<option value="TX">Texas</option>
									<option value="UT">Utah</option>
									<option value="VT">Vermont</option>
									<option value="VA">Virginia</option>
									<option value="WA">Washington</option>
									<option value="WV">West Virginia</option>
									<option value="WI">Wisconsin</option>
									<option value="WY">Wyoming</option>
									<option value="AB">Alberta</option>
									<option value="BC">British Columbia</option>
									<option value="MB">Manitoba</option>
									<option value="NB">New Brunswick</option>
									<option value="NL">Newfoundland and Labrador</option>
									<option value="NS">Nova Scotia</option>
									<option value="ON">Ontario</option>
									<option value="PE">Prince Edward Island</option>
									<option value="QC">Quebec</option>
									<option value="SK">Saskatchewan</option>
									<option value="NT">Northwest Territories</option>
									<option value="NU">Nunavut</option>
									<option value="YT">Yukon</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="zip" class="col-sm-3 control-label">Zip</label>
							<div class="col-sm-9">
								<input type="text" id="zip" name="zip" ng-model="dealer.zip" class="form-control" required="required">
							</div>
						</div>
						<div class="form-group">
							<label for="marketEmail" class="col-sm-3 control-label">Market Email</label>
							<div class="col-sm-9">
								<input type="text" id="marketEmail" name="marketEmail" ng-model="dealer.marketEmail" class="form-control" required="required">
							</div>
						</div>
						<div class="form-group">
							<label for="invoiceEmail" class="col-sm-3 control-label">Invoice Email</label>
							<div class="col-sm-9">
								<input type="text" id="invoiceEmail" name="invoiceEmail" ng-model="dealer.invoiceEmail" class="form-control" required="required">
							</div>
						</div>
						<div class="form-group">
							<label for="phone" class="col-sm-3 control-label">Phone</label>
							<div class="col-sm-9">
								<input type="text" id="phone" name="phone" ng-model="dealer.phone" class="form-control" required="required">
							</div>
						</div>
						<div class="form-group">
							<label for="dealerUrl" class="col-sm-3 control-label">URL</label>
							<div class="col-sm-9">
								<input type="text" id="dealerUrl" name="dealerUrl" ng-model="dealer.dealerUrl" class="form-control" required="required">
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-9 col-sm-offset-3">
								<button type="submit" class="btn btn-primary btn-block">Submit</button>
							</div>
						</div>
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
              <p>You have been selected to test drive the beta version of our new website.

If you have any problems or suggestions, please do not hesitate to contact us.

Thank you for helping us make our new website more powerful and user friendly.</p>

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
            <p class="simplenav"> <a href="index.html">Home</a> | <a href="#">Home</a> | <a href="#">Register</a> | <a href="#">About Risk</a> | <a href="#">Mom's Pink Tractor</a> </p>
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
<script src="/assets/js/angular.min.js"></script>
<script src="/assets/js/app.js"></script>
<script src="/assets/js/service/registrationService.js"></script>
<script src="/assets/js/controller/registrationController.js"></script>
</body>
</html>