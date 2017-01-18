<div id="userEditPopup" class="agg_popup modal-dialog" style="z-index:10001; display:none; position:absolute;">
<!-- Article main content -->
<article class="modal-content new-modal-box popup">

	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" onclick="closePopup('userEditPopup')"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
		<h3 class="modal-title" id="lineModalLabel">Manage Dealer User</h3>
	</div>

	<!-- data table section -->

	<div class="modal-body">
		<!-- <a class="btn btn-primary pull-right fadeInLeftBig  hvr-pulse mar-right" onclick="closePopup('userEditPopup')">CLOSE</a> -->
		<form class="form-horizontal" role="form" ng-submit="submitEditUser()">
			<!-- <h2>Edit User</h2> -->
			<!-- <div class="form-group">
				<label for="dealer" class="col-sm-3 control-label">Dealer</label>
				<div class="col-sm-9">
					<select class="form-control" name="dealer" ng-model="user.dealerDO" id="dealer" ng-options="dealer.userName for dealer in dealerList" required="required" ng-change="getLocation(this.id)">
					<select class="form-control" name="dealer" ng-model="user.dealerDO" id="dealer" ng-options="dealer.userName for dealer in dealerList" required="required">
						<option value="">Select Dealer</option>
					</select>
				</div>
			</div> -->
			<!-- <div class="form-group">
				<label for="location" class="col-sm-3 control-label">Location</label>
				<div class="col-sm-9">
					<select class="form-control" name="location" ng-model="user.locationDO" id="location" ng-options="location.title for location in locationList" required="required">
					</select>
				</div>
			</div> -->
			<div class="form-group">
				<label for="dealerName" class="col-sm-3 control-label">Dealer</label>
				<div class="col-sm-9">
					<input type="text" id="dealerName" name="dealerName" ng-model="user.dealerDO.name" placeholder="Dealer Name" class="form-control" readonly="readonly">
				</div>
			</div>
			<div class="form-group">
				<label for="userName" class="col-sm-3 control-label">Username</label>
				<div class="col-sm-9">
					<input type="text" id="userName" name="userName" ng-model="user.userName" placeholder="User Name" class="form-control" readonly="readonly">
				</div>
			</div>
			<div class="form-group">
				<label for="password" class="col-sm-3 control-label">Password</label>
				<div class="col-sm-9">
					<input type="password" id="password" name="password" ng-model="user.password" placeholder="Password" class="form-control" required="required">
				</div>
			</div>
			<div class="form-group">
				<label for="firstName" class="col-sm-3 control-label">First Name</label>
				<div class="col-sm-9">
					<input type="text" id="firstName" name="firstName" ng-model="user.firstName" placeholder="First Name" class="form-control" required="required">
				</div>
			</div>
			<div class="form-group">
				<label for="lastName" class="col-sm-3 control-label">Last Name</label>
				<div class="col-sm-9">
					<input type="text" id="lastName" name="lastName" ng-model="user.lastName" placeholder="Last Name" class="form-control" required="required">
				</div>
			</div>
			<!-- <div class="form-group">
				<label for="address1" class="col-sm-3 control-label">Address1</label>
				<div class="col-sm-9">
					<input type="text" id="address1" name="address1" ng-model="user.address1" placeholder="address1" class="form-control" required="required">
				</div>
			</div>
			<div class="form-group">
				<label for="address2" class="col-sm-3 control-label">Address2</label>
				<div class="col-sm-9">
					<input type="text" id="address2" name="address2" ng-model="user.address2" placeholder="address2" class="form-control" required="required">
				</div>
			</div>
			<div class="form-group">
				<label for="city" class="col-sm-3 control-label">City</label>
				<div class="col-sm-9">
					<input type="text" id="city" name="city" ng-model="user.city" class="form-control" required="required">
				</div>
			</div>
			<div class="form-group">
				<label for="country" class="col-sm-3 control-label">State</label>
				<div class="col-sm-9">
					<select class="form-control" name="state" ng-model="user.state" id="state" required="required">
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
					<input type="text" id="zip" name="zip" ng-model="user.zip" class="form-control" required="required">
				</div>
			</div>
			<div class="form-group">
				<label for="email" class="col-sm-3 control-label">Email</label>
				<div class="col-sm-9">
					<input type="text" id="email" name="email" ng-model="user.email" class="form-control" required="required">
				</div>
			</div>
			<div class="form-group">
				<label for="phone" class="col-sm-3 control-label">Phone</label>
				<div class="col-sm-9">
					<input type="text" id="phone" name="phone" ng-model="user.phone" class="form-control" required="required">
				</div>
			</div>
			<div class="form-group">
				<label for="userUrl" class="col-sm-3 control-label">URL</label>
				<div class="col-sm-9">
					<input type="text" id="userUrl" name="userUrl" ng-model="user.url" class="form-control" required="required">
				</div>
			</div> -->
			<div class="form-group">
				<label for="role" class="col-sm-3 control-label">Role</label>
				<div class="col-sm-9">
					<select class="form-control" name="role" ng-model="user.roleDO" id="role" ng-options="role.name for role in roleList track by role.id" required="required">
						<option value="">Select Role</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label for="status" class="col-sm-3 control-label">Status</label>
				<div class="col-sm-9">
					<select class="form-control" name="status" id="status" ng-model="user.status" convert-to-number required="required">
						<option value="">Select Status</option>
						<option value="1">Active</option>
						<option value="0">Terminated</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-9 col-sm-offset-3">
					<button type="submit" class="btn btn-primary btn-block">Edit User</button>
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