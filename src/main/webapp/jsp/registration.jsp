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

<!--data table css-->
<link href="https://cdn.datatables.net/1.10.9/css/dataTables.bootstrap.min.css" rel="stylesheet">

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
      <a class="navbar-brand animated fadeInDownBig" href="index.html"><img src="/assets/images/logo.png" alt="AgGuard"></a> </div>
    <div class="navbar-collapse collapse">
      <ul class="nav navbar-nav pull-right">
        <li><a href="/agg/home">Home</a></li>
        <li class="active"><a href="#">Register</a></li>
        <li><a href="#">About Risk</a></li>
        <li><a href="#">Who We Are</a></li>
        
        
        <li><a href="#">Mom's Pink Tractor</a></li>
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
				
                
                
				 <!-- data table section -->
                
                <div class="inner-main">
                	
                    <div class="main-login main-center" ng-controller="registrationController">
                    <h2 class="mar-bot">Dealer Registration</h2>
                    <p class="success-msg" id="successMsg" hidden="true"></p>
                    <p class="err-msg" id="errMsg" hidden="true"></p>
                    <form role="form" ng-submit="submitDealer()">
					<div class="col-md-6">
                    
                    <div class="form-group">
							<label class="cols-sm-2 control-label" for="userName">Username</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"><i aria-hidden="true" class="fa fa-user fa"></i></span>
									<input type="text" ng-model="dealer.userName" placeholder="User Name" id="userName" name="userName" class="form-control" required="required">
								</div>
							</div>
						</div>
                        
                        
                        <div class="form-group">
							<label class="cols-sm-2 control-label" for="password">Password</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"><i aria-hidden="true" class="fa fa-lock fa-lg"></i></span>
									<input type="password" ng-model="dealer.password" placeholder="Password" id="password" name="password" class="form-control" required="required">
								</div>
							</div>
						</div>
                        
                        
                        <div class="form-group">
							<label class="cols-sm-2 control-label" for="name">Name</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"><i aria-hidden="true" class="fa fa-user fa"></i></span>
									<input type="text" ng-model="dealer.name" placeholder="Dealer Name" id="name" name="name" class="form-control" required="required">
								</div>
							</div>
						</div>
                        
                        <div class="form-group">
							<label class="cols-sm-2 control-label" for="firstName">First Name</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"><i aria-hidden="true" class="fa fa-user fa"></i></span>
									<input type="text" id="firstName" name="firstName" ng-model="dealer.firstName" placeholder="First Name" class="form-control" required="required">
								</div>
							</div>
						</div>
                        
                        
                        <div class="form-group">
							<label class="cols-sm-2 control-label" for="lastName">Last Name</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"><i aria-hidden="true" class="fa fa-user fa"></i></span>
									<input type="text" id="lastName" name="lastName" ng-model="dealer.lastName" placeholder="Last Name" class="form-control" required="required">
								</div>
							</div>
						</div>
                        
                        <div class="form-group">
							<label class="cols-sm-2 control-label" for="address1">Address1</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"><i aria-hidden="true" class="fa fa-book fa"></i></span>
									<input type="text" id="address1" name="address1" ng-model="dealer.address1" placeholder="Address 1" class="form-control" required="required">
								</div>
							</div>
						</div>
                        
                        
                        <div class="form-group">
							<label class="cols-sm-2 control-label" for="address2">Address2</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"><i aria-hidden="true" class="fa fa-book fa"></i></span>
									<input type="text" id="address2" name="address2" ng-model="dealer.address2" placeholder="Address 2" class="form-control">
								</div>
							</div>
						</div>
                    
                    </div>
                    
                    
                    
                    <div class="col-md-6">

						<div class="form-group">
							<label class="cols-sm-2 control-label" for="city">City</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"><i aria-hidden="true" class="fa fa-home fa"></i></span>
									<input type="text" id="city" name="city" ng-model="dealer.city" placeholder="City" class="form-control" required="required">
								</div>
							</div>
						</div>

						<div class="form-group">
							<label class="cols-sm-2 control-label" for="state">State/Province</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"><i aria-hidden="true" class="fa fa-users fa"></i></span>
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
						</div>
                        
                        
                        
                  <div class="form-group">
							<label class="cols-sm-2 control-label" for="zip">Zip</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"><i aria-hidden="true" class="fa fa-file-archive-o"></i></span>
									<input type="text" id="zip" name="zip" ng-model="dealer.zip" placeholder="Zip Code" class="form-control" required="required">
								</div>
							</div>
						</div>      
                        
                        
                        
            		<div class="form-group">
							<label class="cols-sm-2 control-label" for="marketEmail">Market Email</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"><i aria-hidden="true" class="fa-envelope fa"></i></span>
									<input type="text" id="marketEmail" name="marketEmail" ng-model="dealer.marketEmail" placeholder="Market Email" class="form-control" required="required">
								</div>
							</div>
						</div>
						
					<div class="form-group">
							<label class="cols-sm-2 control-label" for="invoiceEmail">Invoice Email</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"><i aria-hidden="true" class="fa-envelope fa"></i></span>
									<input type="text" id="invoiceEmail" name="invoiceEmail" ng-model="dealer.invoiceEmail" placeholder="Invoice Email" class="form-control" required="required">
								</div>
							</div>
						</div>            
                        
              		<div class="form-group">
							<label class="cols-sm-2 control-label" for="phone">Phone</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"><i aria-hidden="true" class="fa-phone fa"></i></span>
									<input type="number" id="phone" name="phone" ng-model="dealer.phone" placeholder="Phone Number" class="form-control" required="required">
								</div>
							</div>
						</div>          
                        

					<div class="form-group">
							<label class="cols-sm-2 control-label" for="dealerUrl">URL</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"><i aria-hidden="true" class="fa fa-globe fa"></i></span>
									<input type="text" id="dealerUrl" name="dealerUrl" ng-model="dealer.dealerUrl" placeholder="URL" class="form-control" required="required">
								</div>
							</div>
						</div>	
                        
                       <div class="form-group ">
							<button class="btn btn-primary btn-lg btn-block login-button" type="submit">Register</button>
						</div> 
                        
</div>
	</form>
				</div>
                    
                    
                </div><!--inner main-->
			
			<!-- end data table section -->
                
			</article>
			<!-- /Article -->

		</div>
	</div>	<!-- /container -->
	
	

		

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


		

	<!-- JavaScript libs are placed at the end of the document so the pages load faster -->
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
     <!--side nav js-->
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
  	<script src="/assets/js/navAccordion.min.js"></script>
	<script src="/assets/js/headroom.min.js"></script>
	<script src="/assets/js/jQuery.headroom.min.js"></script>
	<script src="/assets/js/custom.js"></script>
	<script src="/assets/js/angular.min.js"></script>
	<script src="/assets/js/app.js"></script>
	<script src="/assets/js/service/registrationService.js"></script>
	<script src="/assets/js/controller/registrationController.js"></script>

</body>
</html>