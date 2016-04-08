<div id="dealerEditPopup" class="agg_popup" style="z-index:10001; display:none; position:absolute;">
<!-- Article main content -->
<article class="col-md-9 maincontent popup">

	<!-- data table section -->

	<div class="inner-main">
		<a class="btn btn-primary pull-right fadeInLeftBig  hvr-pulse mar-right" onclick="closePopup('dealerEditPopup')">CLOSE</a>
		<form class="form-horizontal" role="form">
			<h2>Edit Dealer</h2>
			<div class="form-group">
				<label for="userName" class="col-sm-3 control-label">Username</label>
				<div class="col-sm-9">
					<input type="text" id="userName" name="userName" ng-model="dealer.userName" placeholder="User Name" class="form-control" required="required" value="{{dealer.userName}}" readonly="readonly">
				</div>
			</div>
			<div class="form-group">
				<label for="password" class="col-sm-3 control-label">Password</label>
				<div class="col-sm-9">
					<input type="text" id="password" name="password" ng-model="dealer.password" placeholder="password" class="form-control" required="required" value="{{dealer.password}}">
				</div>
			</div>
			<div class="form-group">
				<label for="companyName" class="col-sm-3 control-label">Company Name</label>
				<div class="col-sm-9">
					<input type="text" id="companyName" name="companyName" ng-model="dealer.companyName" placeholder="companyName" class="form-control" required="required" value="{{dealer.companyName}}">
				</div>
			</div>
			<div class="form-group">
				<label for="contact" class="col-sm-3 control-label">Contact</label>
				<div class="col-sm-9">
					<input type="text" id="contact" name="contact" ng-model="dealer.contact" class="form-control" required="required" value="{{dealer.contact}}">
				</div>
			</div>
			<div class="form-group">
				<label for="location" class="col-sm-3 control-label">Location</label>
				<div class="col-sm-9">
					<input type="text" id="location" name="location" ng-model="dealer.location" class="form-control" required="required" value="{{dealer.location}}">
				</div>
			</div>
			<div class="form-group">
				<label for="address1" class="col-sm-3 control-label">Address1</label>
				<div class="col-sm-9">
					<input type="text" id="address1" name="address1" ng-model="dealer.address1" class="form-control" required="required" value="{{dealer.address1}}">
				</div>
			</div>
			<div class="form-group">
				<label for="address2" class="col-sm-3 control-label">Address2</label>
				<div class="col-sm-9">
					<input type="text" id="address2" name="address2" ng-model="dealer.address2" class="form-control" required="required" value="{{dealer.address2}}">
				</div>
			</div>
			<div class="form-group">
				<label for="city" class="col-sm-3 control-label">City</label>
				<div class="col-sm-9">
					<input type="text" id="city" name="city" ng-model="dealer.city" class="form-control" required="required" value="{{dealer.city}}">
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
					<input type="text" id="zip" name="zip" ng-model="dealer.zip" class="form-control" required="required" value="{{dealer.zip}}">
				</div>
			</div>
			<div class="form-group">
				<label for="marketEmail" class="col-sm-3 control-label">Market Email</label>
				<div class="col-sm-9">
					<input type="text" id="marketEmail" name="marketEmail" ng-model="dealer.marketEmail" class="form-control" required="required" value="{{dealer.marketEmail}}">
				</div>
			</div>
			<div class="form-group">
				<label for="invoiceEmail" class="col-sm-3 control-label">Invoice Email</label>
				<div class="col-sm-9">
					<input type="text" id="invoiceEmail" name="invoiceEmail" ng-model="dealer.invoiceEmail" class="form-control" required="required" value="{{dealer.invoiceEmail}}">
				</div>
			</div>
			<div class="form-group">
				<label for="phone" class="col-sm-3 control-label">Phone</label>
				<div class="col-sm-9">
					<input type="text" id="phone" name="phone" ng-model="dealer.phone" class="form-control" required="required" value="{{dealer.phone}}">
				</div>
			</div>
			<div class="form-group">
				<label for="dealerUrl" class="col-sm-3 control-label">URL</label>
				<div class="col-sm-9">
					<input type="text" id="dealerUrl" name="dealerUrl" ng-model="dealer.dealerUrl" class="form-control" required="required" value="{{dealer.dealerUrl}}">
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
					<textarea id="notes" name="notes" ng-model="dealer.notes" class="form-control" rows="5" cols="5" required="required">{{dealer.notes}}</textarea>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-9 col-sm-offset-3">
					<button type="submit" class="btn btn-primary btn-block">Edit Dealer</button>
				</div>
			</div>
		</form>
		<!-- /form -->
	</div>
	<!--inner main-->

	<!-- end data table section -->

</article>
<!-- /Article -->

            </div>