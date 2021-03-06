
<!-- Article main content -->
<article class="col-md-9 maincontent">
	<header class="page-header">
		<div class="col-md-6 col-sm-12">
			<div class="sec-title">

				<h3 class="wow animated bounceInLeft">Add Location</h3>
				<p class="wow animated bounceInRight">&nbsp;</p>
			</div>
		</div>
		<div class="col-md-6 col-sm-12">
			<a
				class="btn btn-primary pull-right mar-right animated fadeInRightBig hvr-pulse"
				href="add-new.html">Add New</a><a
				class="btn btn-primary pull-right animated fadeInLeftBig  hvr-pulse mar-right"
				href="#">Back</a>
		</div>
	</header>


	<!-- data table section -->

	<div class="inner-main" ng-controller="locationController">
		<form class="form-horizontal" role="form" ng-submit="submitLocation()">
			<h2>Add Location</h2>
			<div class="form-group">
				<label class="control-label col-sm-3"></label>
				<div class="col-sm-9">
					<div class="checkbox">
						<label> <input type="checkbox" id="calorieCheckbox" ng-model="location.headQuarter">
								Check here if this location is a headquarters
						</label>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label for="dealer" class="col-sm-3 control-label">Dealer</label>
				<div class="col-sm-9">
					<select class="form-control" name="dealer" ng-model="location.dealerDO" id="dealer" ng-options="dealer.userName for dealer in dealerList" required="required">
						<!-- <option ng:repeat="dealer in dealerList" value="{{dealer.id}}">{{dealer.userName}}</option> -->
					</select>
				</div>
			</div>
			<div class="form-group">
				<label for="title" class="col-sm-3 control-label">Title</label>
				<div class="col-sm-9">
					<input type="text" id="title" name="title" ng-model="location.title" placeholder="title" class="form-control" required="required">
				</div>
			</div>
			<div class="form-group">
				<label for="address1" class="col-sm-3 control-label">Address1</label>
				<div class="col-sm-9">
					<input type="text" id="address1" name="address1" ng-model="location.address1" placeholder="address1" class="form-control" required="required">
				</div>
			</div>
			<div class="form-group">
				<label for="address2" class="col-sm-3 control-label">Address2</label>
				<div class="col-sm-9">
					<input type="text" id="address2" name="address2" ng-model="location.address2" placeholder="address2" class="form-control" required="required">
				</div>
			</div>
			<div class="form-group">
				<label for="city" class="col-sm-3 control-label">City</label>
				<div class="col-sm-9">
					<input type="text" id="city" name="city" ng-model="location.city" class="form-control" required="required">
				</div>
			</div>
			<div class="form-group">
				<label for="country" class="col-sm-3 control-label">State</label>
				<div class="col-sm-9">
					<select class="form-control" name="state" ng-model="location.state" id="state" required="required">
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
					<input type="text" id="zip" name="zip" ng-model="location.zip" class="form-control" required="required">
				</div>
			</div>
			<div class="form-group">
				<label for="email" class="col-sm-3 control-label">Email</label>
				<div class="col-sm-9">
					<input type="text" id="email" name="email" ng-model="location.email" class="form-control" required="required">
				</div>
			</div>
			<div class="form-group">
				<label for="phone" class="col-sm-3 control-label">Phone</label>
				<div class="col-sm-9">
					<input type="text" id="phone" name="phone" ng-model="location.phone" class="form-control" required="required">
				</div>
			</div>
			<div class="form-group">
				<label for="dealerUrl" class="col-sm-3 control-label">URL</label>
				<div class="col-sm-9">
					<input type="text" id="locationUrl" name="locationUrl" ng-model="location.locationUrl" class="form-control" required="required">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-9 col-sm-offset-3">
					<button type="submit" class="btn btn-primary btn-block">Add Location</button>
				</div>
			</div>
		</form>
		<!-- /form -->
	</div>
	<!--inner main-->

	<!-- end data table section -->

</article>
<!-- /Article -->
