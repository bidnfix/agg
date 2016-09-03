
<!-- Article main content -->
<article class="col-md-9 maincontent" ng-controller="HomeController">
	<header class="page-header">
		<!-- <div class="col-md-6 col-sm-12">
			<div class="sec-title">

				<h2 class="wow animated bounceInLeft">Dashboard</h2>
				<p class="wow animated bounceInRight">The Key Features of our
					Tool</p>
			</div>
		</div>
		<div class="col-md-6 col-sm-12">
			<a
				class="btn btn-primary pull-right mar-right animated fadeInRightBig hvr-pulse"
				href="add-new.html">Add New</a><a
				class="btn btn-primary pull-right animated fadeInLeftBig  hvr-pulse mar-right"
				href="#">Back</a>
		</div> -->
	</header>
	<nav class="navbar navbar-findcond ">
    <!--<div class="container">-->
		
		<div id="navbar">
			<ul class="nav navbar-nav ">
				<li>
					<a href="#/agg/pendingDealers">Expired Contract<span class="badge">{{pendingDealers}}</span></a>
				</li>
                
                <li>
					<a href="#/agg/dealers">Active Contract<span class="badge">{{activeDealers}}</span></a>
				</li>
				
                
                <li>
					<a href="#/agg/estPriceQuotes">Estimating Price<span class="badge">{{estPrice}}</span></a>
				</li>
				<li>
					<a href="#/agg/pendingDealers">Invoiced<span class="badge">{{invoiced}}</span></a>
				</li>
                
                <li>
					<a href="#/agg/dealers">Purchase Requested<span class="badge">{{purchaseReq}}</span></a>
				</li>
				
                
                <li>
					<a href="#/agg/dealers">Claims<span class="badge">{{terminatedDealers}}</span></a>
				</li>
                <!--
                <li>
					<a href="#"> Invoiced  <span class="badge">24</span></a>
				</li>
                
                <li>
					<a href="#"> Purchase Requested <span class="badge">6</span></a>
				</li>
                
                <li>
					<a href="#"> Claims <span class="badge">112</span></a>
				</li>
				-->
				
			</ul>
			
		</div>
		<!--</div>-->
	</nav>

	<!-- data table section -->

	<div class="inner-main">
		<form class="form-horizontal" role="form">
			<h2>Registration Form</h2>
			<div class="form-group">
				<label for="firstName" class="col-sm-3 control-label">Full
					Name</label>
				<div class="col-sm-9">
					<input type="text" id="firstName" placeholder="Full Name"
						class="form-control" autofocus> <span class="help-block">Last
						Name, First Name, eg.: Smith, Harry</span>
				</div>
			</div>
			<div class="form-group">
				<label for="email" class="col-sm-3 control-label">Email</label>
				<div class="col-sm-9">
					<input type="email" id="email" placeholder="Email"
						class="form-control">
				</div>
			</div>
			<div class="form-group">
				<label for="password" class="col-sm-3 control-label">Password</label>
				<div class="col-sm-9">
					<input type="password" id="password" placeholder="Password"
						class="form-control">
				</div>
			</div>
			<div class="form-group">
				<label for="birthDate" class="col-sm-3 control-label">Date
					of Birth</label>
				<div class="col-sm-9">
					<input type="date" id="birthDate" class="form-control">
				</div>
			</div>
			<div class="form-group">
				<label for="country" class="col-sm-3 control-label">Country</label>
				<div class="col-sm-9">
					<select id="country" class="form-control">
						<option>Afghanistan</option>
						<option>Bahamas</option>
						<option>Cambodia</option>
						<option>Denmark</option>
						<option>Ecuador</option>
						<option>Fiji</option>
						<option>Gabon</option>
						<option>Haiti</option>
					</select>
				</div>
			</div>
			<!-- /.form-group -->
			<div class="form-group">
				<label class="control-label col-sm-3">Gender</label>
				<div class="col-sm-6">
					<div class="row">
						<div class="col-sm-4">
							<label class="radio-inline"> <input type="radio"
								id="femaleRadio" value="Female">Female
							</label>
						</div>
						<div class="col-sm-4">
							<label class="radio-inline"> <input type="radio"
								id="maleRadio" value="Male">Male
							</label>
						</div>
						<div class="col-sm-4">
							<label class="radio-inline"> <input type="radio"
								id="uncknownRadio" value="Unknown">Unknown
							</label>
						</div>
					</div>
				</div>
			</div>
			<!-- /.form-group -->
			<div class="form-group">
				<label class="control-label col-sm-3">Meal Preference</label>
				<div class="col-sm-9">
					<div class="checkbox">
						<label> <input type="checkbox" id="calorieCheckbox"
							value="Low calorie">Low calorie
						</label>
					</div>
					<div class="checkbox">
						<label> <input type="checkbox" id="saltCheckbox"
							value="Low salt">Low salt
						</label>
					</div>
				</div>
			</div>
			<!-- /.form-group -->
			<div class="form-group">
				<div class="col-sm-9 col-sm-offset-3">
					<div class="checkbox">
						<label> <input type="checkbox">I accept <a
							href="#">terms</a>
						</label>
					</div>
				</div>
			</div>
			<!-- /.form-group -->
			<div class="form-group">
				<div class="col-sm-9 col-sm-offset-3">
					<button type="submit" class="btn btn-primary btn-block">Add
						New Item</button>
				</div>
			</div>
		</form>
		<!-- /form -->
	</div>
	<!--inner main-->

	<!-- end data table section -->

</article>
<!-- /Article -->
