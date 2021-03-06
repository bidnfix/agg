<!-- Article main content -->
<article class="col-md-9 maincontent">
		<header class="page-header">
			<div class="col-md-6 col-sm-12">
				<div class="sec-title">
					<h3 class="wow animated bounceInLeft">Add Dealer</h3>
				</div>
			</div>

		</header>

		<!-- data table section -->

		<div class="inner-main" ng-controller="dealerController">
			<form class="form-horizontal" name="dealerInfoForm" id="dealerInfoForm" ng-submit="submitDealer(dealerInfoForm)" novalidate angular-validator>
			<div class="col-xs-12 agf1 main-login pad10-top">
				<div id="dealerErrorMsg" class="alert alert-danger text-center hidden"></div>
				<div class="col-md-6 no-pad pad10-right">
					<div class="form-group">
						<label>Username</label>
						<input type="text" id="userName" name="userName" ng-model="dealer.userName" placeholder="User Name" class="form-control" validate-on="dirty" required="required" ng-blur="isUserNameExists(dealer.userName)">
					</div>
					<div class="form-group">
						<label>Password</label>
						<input type="password" id="password" name="password" ng-model="dealer.password" placeholder="password" class="form-control" validate-on="dirty" required="required">
					</div>
					<div class="form-group">
						<label>Name</label>
						<input type="text" id="dealerName" name="dealerName" ng-model="dealer.name" class="form-control" required="required" validate-on="dirty">
					</div>
					<div class="form-group">
						<label>First Name</label>
						<input type="text" id="firstName" name="firstName" ng-model="dealer.firstName" placeholder="First Name" class="form-control" required="required" validate-on="dirty">
					</div>
					<div class="form-group">
						<label>Last Name</label>
						<input type="text" id="lastName" name="lastName" ng-model="dealer.lastName" placeholder="Last Name" class="form-control" required="required" validate-on="dirty">
					</div>
					<div class="form-group">
                      <label>Address1</label>
                      <input type="text" id="address1" name="address1" ng-model="dealer.address1" class="form-control" required="required" validate-on="dirty">
                    </div>
                    <div class="form-group">
                      <label>Address2</label>
                      <input type="text" id="address2" name="address2" ng-model="dealer.address2" class="form-control">
                    </div>
                    <div class="form-group">
                      <label>Adjustment Factor</label>
                      <input type="number" step="0.01" id="adjustmentFactor" name="adjustmentFactor" ng-model="dealer.adjustmentFactor" class="form-control" required="required" validate-on="dirty">
                    </div>
				</div>

				<div class="col-md-6 no-pad pad10-left border-left">
                    <div class="col-xs-12 no-pad">
                    	<div class="form-group">
							<label>City</label> 
							<input type="text" id="city" name="city" ng-model="dealer.city" class="form-control" required="required" validate-on="dirty">
						</div>
						<div class="form-group col-xs-12 no-pad">
							<label>State/Province</label> 
							<select class="form-control" name="state" ng-model="dealer.state" id="state" required="required" validate-on="dirty">
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
						<div class="form-group">
	                         <label>Zip</label>
	                         <input type="text" id="zip" name="zip" ng-model="dealer.zip" class="form-control" required="required" validate-on="dirty">
	                    </div>
	                    <div class="form-group">
	                         <label>Market Email</label>
	                         <input type="text" id="marketEmail" name="marketEmail" ng-model="dealer.marketEmail" class="form-control" required="required" validate-on="dirty">
	                    </div>
	                    <div class="form-group">
	                         <label>Invoice Email</label>
	                         <input type="text" id="invoiceEmail" name="invoiceEmail" ng-model="dealer.invoiceEmail" class="form-control" required="required" validate-on="dirty">
	                    </div>
	                    <div class="form-group">
	                         <label>Phone</label>
	                         <input type="text" id="phone" name="phone" ng-model="dealer.phone" class="form-control" required="required" validate-on="dirty">
	                    </div>
	                    <div class="form-group">
	                         <label>Dealer Website</label>
	                         <input type="text" id="dealerUrl" name="dealerUrl" ng-model="dealer.dealerUrl" class="form-control" required="required" validate-on="dirty">
	                    </div>
	                    <div class="form-group">
	                         <label>Role</label>
	                         <select class="form-control" name="role" ng-model="dealer.roleDO" id="role" ng-options="role.name for role in roleList" required="required" validate-on="dirty">
								<option value="">Select Role</option>
							 </select>
	                    </div>
	                    <div class="form-group">
	                         <label>Notes</label>
	                         <textarea id="notes" name="notes" ng-model="dealer.notes" class="form-control" rows="3" cols="5"></textarea>
	                    </div>
					</div>
				</div>
				
				<div class="col-sm-12 no-pad t-c marg10-bottom">
					<div class="col-md-6 col-centered">
							<button class="btn btn-primary btn-lg btn-block login-button" type="submit">Add Dealer</button>
					</div>
				</div>
			</div>
			</form>
		</div>
	<!-- end data table section -->

</article>
<!-- /Article -->