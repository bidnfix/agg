
<!-- Article main content -->
<article class="col-md-9 maincontent">
	<header class="page-header">
		<div class="col-md-6 col-sm-12">
			<div class="sec-title">

				<h3 class="wow animated bounceInLeft">Dealer Management</h3>
				<p class="wow animated bounceInRight">&nbsp;</p>
			</div>
		</div>
		<div class="col-md-6 col-sm-12">
			<!-- <a
				class="btn btn-primary pull-right mar-right animated fadeInRightBig hvr-pulse"
				href="add-new.html">Add New</a><a
				class="btn btn-primary pull-right animated fadeInLeftBig  hvr-pulse mar-right"
				href="#">Back</a> -->
		</div>
	</header>


	<!-- data table section -->

	<div class="inner-main" ng-controller="dealerController">
		<form class="form-horizontal" role="form" ng-submit="submitDealer()">
			<h3 align="center">Add Dealer</h3>
			<div id="dealerErrorMsg" class="alert alert-danger text-center hidden"></div>
			<div class="form-group">
				<label for="userName" class="col-sm-3 control-label">Username</label>
				<div class="col-sm-9">
					<input type="text" id="userName" name="userName" ng-model="dealer.userName" placeholder="User Name" class="form-control" required="required" ng-blur="isUserNameExists(dealer.userName)">
				</div>
			</div>
			<div class="form-group">
				<label for="password" class="col-sm-3 control-label">Password</label>
				<div class="col-sm-9">
					<input type="password" id="password" name="password" ng-model="dealer.password" placeholder="password" class="form-control" required="required">
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
					<input type="text" id="firstName" name="firstName" ng-model="dealer.firstName" placeholder="First Name" class="form-control" required="required">
				</div>
			</div>
			<div class="form-group">
				<label for="lastName" class="col-sm-3 control-label">Last Name</label>
				<div class="col-sm-9">
					<input type="text" id="lastName" name="lastName" ng-model="dealer.lastName" placeholder="Last Name" class="form-control" required="required">
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
					<input type="text" id="address2" name="address2" ng-model="dealer.address2" class="form-control">
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
				<label for="dealerUrl" class="col-sm-3 control-label">Dealer Website</label>
				<div class="col-sm-9">
					<input type="text" id="dealerUrl" name="dealerUrl" ng-model="dealer.dealerUrl" class="form-control" required="required">
				</div>
			</div>
			<div class="form-group">
				<label for="role" class="col-sm-3 control-label">Role</label>
				<div class="col-sm-9">
					<select class="form-control" name="role" ng-model="dealer.roleDO" id="role" ng-options="role.name for role in roleList" required="required">
						<option value="">Select Role</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label for="notes" class="col-sm-3 control-label">Notes</label>
				<div class="col-sm-9">
					<textarea id="notes" name="notes" ng-model="dealer.notes" class="form-control" rows="5" cols="5"></textarea>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-9 col-sm-offset-3">
					<button type="submit" class="btn btn-primary btn-block">Add Dealer</button>
				</div>
			</div>
		</form>
		<!-- /form -->
	</div>
	<!--inner main-->

	<!-- end data table section -->

</article>
<!-- /Article -->
