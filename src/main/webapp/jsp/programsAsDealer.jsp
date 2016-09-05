<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Article main content -->
<article class="col-md-9 maincontent">
	<header class="page-header">
		<div class="col-md-8 col-sm-12">
			<div class="sec-title">

				<h2 class="wow animated bounceInLeft">Programs Info</h2>
			</div>
		</div>
		<c:if test="${user.roleName eq 'admin'}">
			<div class="col-md-4 col-sm-12">
				<a class="btn btn-primary pull-right  hvr-pulse"
					href="#/agg/addPrograms">Add New</a>
			</div>
		</c:if>
	</header>

	<!-- container -->
	<div class="container">
		<div class="row">
			<!-- Article main content -->
			<article class="col-md-12 maincontent">
				<!-- data table section -->

				<div class="inner-main">
					<div class="main-login main-center"
						ng-controller="ProgramAsDealerController">
						<h2 class="mar-bot">Vew as Dealer</h2>
						<p class="success-msg" id="successMsg" hidden="true"></p>
						<p class="err-msg" id="errMsg" hidden="true"></p>
						<form role="form" ng-submit="submitProgramAsDel()">
							<div class="col-md-6">
								<div class="form-group">
									<label class="cols-sm-2 control-label" for="userName">Select
										a Program</label>
									<div class="cols-sm-10">
										<div class="input-group">
											<select name="programName" ng-model="program.programDO"
												ng-options="program.name for program in programList"
												ng-change="getProgDetails(program.programDO)">
											</select>
										</div>
									</div>
								</div>

								<div class="form-group">
									<label class="cols-sm-2 control-label" for="password">Dealer</label>
									<div class="cols-sm-10">
										<div class="input-group">{{program.dealerDO.name}}</div>
									</div>
								</div>

								<div class="form-group">
									<label class="cols-sm-2 control-label" for="password">Manufacturer</label>
									<div class="cols-sm-10">
										<div class="input-group">
											<input type="text" ng-model="program.manufacturerDO.name"
												placeholder="Manufacturer" id="manfName" name="manfName"
												class="form-control" required="required">
										</div>
									</div>
								</div>

								<div class="form-group">
									<label class="cols-sm-2 control-label" for="password">Manufacturer</label>
									<div class="cols-sm-10">
										<div class="input-group">
											<input type="text" ng-model="program.manufacturerDO.name"
												placeholder="Manufacturer" id="manfName" name="manfName"
												class="form-control" required="required">
										</div>
									</div>
								</div>


								<div class="form-group">
									<label for="name" class="col-sm-3 control-label">Model</label>
									<div class="col-sm-9">
										<!--  <select name="machine" ng-model="program.modelDO" ng-options="machine.name for machine in modelList">
		     				</select> -->
										<div class="input-group">
											<select name="modelName" ng-model="program.machineInfoDO"
												ng-options="machineInfooDO.model for machineInfooDO in program.machineInfoDOList"
												ng-change="getRemProgDetails(program.machineInfoDO)">
											</select>
										</div>
									</div>
								</div>

								<div class="form-group">
									<label class="cols-sm-2 control-label">Model Year</label>
									<div class="cols-sm-10">
										<div class="input-group">
											<input type="text" id="modelYear" name="modelYear"
												ng-model="program2.modelYear" placeholder="Model Year"
												class="form-control" required="required">
										</div>
									</div>
								</div>


								<div class="form-group">
									<label class="cols-sm-2 control-label">Serial Number</label>
									<div class="cols-sm-10">
										<div class="input-group">
											<input type="text" id="serialNum" name="serialNum"
												ng-model="program.machineSerial" placeholder="Serial Number"
												class="form-control" required="required">
										</div>
									</div>
								</div>

								<div class="form-group">
									<label class="cols-sm-2 control-label" for="enrollment">Hours
										at Enrollment</label>
									<div class="cols-sm-10">
										<div class="input-group">
											<input type="text" id="enrollment" name="addenrollmentress1"
												ng-model="program.enrolement"
												placeholder="Hours at Enrollment" class="form-control"
												required="required">
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="cols-sm-2 control-label" for="coverage">Start
										Date of Coverage</label>
									<div class="cols-sm-10">
										<div class="input-group">
											<input type="date" id="coverage" name="coverage"
												ng-model="program.coverage"
												placeholder="Start Date of Coverage" class="form-control"
												required="required">
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="cols-sm-2 control-label" for="provisions">Unusual
										Provisions</label>
									<div class="cols-sm-10">
										<div class="input-group">
											<input type="textarea" id="provisions" name="provisions"
												ng-model="program.provisions"
												placeholder="Unusual Provisions" class="form-control"
												required="required">
										</div>
									</div>
								</div>

								<div class="form-group ">
									<button class="btn btn-primary btn-lg btn-block login-button"
										type="submit">Submit</button>
								</div>
							</div>



							<div class="col-md-6">


								<div class="form-group">
									<div class="cols-sm-10">
										<h3 class="mar-bot">Program Details</h3>

									</div>
									<div class="cols-sm-10">
										<label class="cols-sm-2 control-label" for="city">Condition</label>
										<div class="cols-sm-10">
											<div class="input-group">
												<!-- <input type="text" id="condition" name="condition" ng-model="program.condition" class="form-control" required="required"> -->
												{{program.condition}}
											</div>
										</div>
									</div>

									<div class="form-group">
										<label class="cols-sm-2 control-label" for="state">Type
											of Coverage</label>
										<div class="cols-sm-10">
											<div class="input-group">
												<input type="text" id="cType" name="cType"
													ng-model="program.cType" placeholder="Coverage type"
													class="form-control" required="required">
											</div>
										</div>
									</div>



									<div class="form-group">
										<label class="cols-sm-2 control-label" for="zip">Coverage
											Term</label>
										<div class="cols-sm-10">
											<div class="input-group">
												<input type="text" id="cTerm" name="cTerm"
													ng-model="program.cTerm" class="form-control"
													required="required">
											</div>
										</div>
									</div>



									<div class="form-group">
										<label class="cols-sm-2 control-label" for="marketEmail">Hours
											Covered</label>
										<div class="cols-sm-10">
											<div class="input-group">
												<input type="number" id="cHours" name="cHours"
													ng-model="program.cHours" class="form-control"
													required="required">
											</div>
										</div>
									</div>

									<div class="form-group">
										<label class="cols-sm-2 control-label" for="invoiceEmail">Deductible</label>
										<div class="cols-sm-10">
											<div class="input-group">
												<input type="number" id="deductible" name="deductible"
													ng-model="program.deductible" class="form-control"
													required="required">
											</div>
										</div>
									</div>

									<div class="form-group">
										<label class="cols-sm-2 control-label" for="phone">Limit
											of Liability</label>
										<div class="cols-sm-10">
											<div class="input-group">
												<input type="number" id="lol" name="lol"
													ng-model="program.lol" class="form-control"
													required="required">
											</div>
										</div>
									</div>


									<div class="form-group">
										<label class="cols-sm-2 control-label" for="dealerUrl">Cost</label>
										<div class="cols-sm-10">
											<div class="input-group">
												<input type="number" id="cost" name="cost"
													ng-model="program.cost" class="form-control"
													required="required">
											</div>
										</div>
									</div>

									<div class="form-group">
										<label class="cols-sm-2 control-label" for="dealerUrl">Program
											Description</label>
										<div class="cols-sm-10">
											<div class="input-group">
												<input type="text" id="desc" name="desc"
													ng-model="program.desc" class="form-control"
													required="required">
											</div>
										</div>
									</div>

									<br /> <br />
									<div class="cols-sm-10">
										<h4 class="mar-bot">Customer Information</h4>

									</div>

									<div class="form-group">
										<label class="cols-sm-2 control-label">Name</label>
										<div class="cols-sm-10">
											<div class="input-group">
												<input type="text" id="custName" name="custName"
													ng-model="program.customerInfoDO.name" placeholder="Name"
													class="form-control" required="required">
											</div>
										</div>
									</div>

									<div class="form-group">
										<label class="cols-sm-2 control-label">Address</label>
										<div class="cols-sm-10">
											<div class="input-group">
												<input type="text" id="address" name="address"
													ng-model="program.customerInfoDO.address"
													placeholder="Address" class="form-control"
													required="required">
											</div>
										</div>
									</div>

									<div class="form-group">
										<label class="cols-sm-2 control-label">City</label>
										<div class="cols-sm-10">
											<div class="input-group">
												<input type="text" id="city" name="city"
													ng-model="program.customerInfoDO.city" placeholder="City"
													class="form-control" required="required">
											</div>
										</div>
									</div>

									<div class="form-group">
										<label class="cols-sm-2 control-label">State/Province</label>
										<div class="cols-sm-10">
											<div class="input-group">
												<select class="form-control" name="state"
													ng-model="program.customerInfoDO.state" id="state"
													required="required">
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
										<label class="cols-sm-2 control-label">Zip</label>
										<div class="cols-sm-10">
											<div class="input-group">
												<input type="text" id="zip" name="zip"
													ng-model="program.customerInfoDO.zip" placeholder="Zip"
													class="form-control" required="required">
											</div>
										</div>
									</div>

									<div class="form-group">
										<label class="cols-sm-2 control-label">Phone Number</label>
										<div class="cols-sm-10">
											<div class="input-group">
												<input type="text" id="phone" name="phone"
													ng-model="program.customerInfoDO.phone" placeholder="Phone"
													class="form-control" required="required">
											</div>
										</div>
									</div>

									<div class="form-group">
										<label class="cols-sm-2 control-label">Email</label>
										<div class="cols-sm-10">
											<div class="input-group">
												<input type="text" id="email" name="email"
													ng-model="program.customerInfoDO.email" placeholder="Email"
													class="form-control" required="required">
											</div>
										</div>
									</div>



								</div>
						</form>
					</div>


				</div>
				<!--inner main-->

				<!-- end data table section -->

			</article>
			<!-- /Article -->

		</div>
	</div>
	<!-- /container -->