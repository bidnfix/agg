<!-- Article main content -->
<article class="col-md-9 maincontent">
	<form class="form-horizontal" name="quoteInfoForm" id="quoteInfoForm"
		ng-submit="submitProgramAsDel()" novalidate angular-validator>
		<header class="page-header">
			<div class="col-md-6 col-sm-12">
				<div class="sec-title">
					<h3 class="wow animated bounceInLeft">Enroll a Machine</h3>
				</div>
			</div>

		</header>

		<!-- data table section -->

		<div class="inner-main">

			<div class="col-xs-12 agf1 main-login pad10-top">
				<div class="col-md-6 no-pad pad10-right">
					<div class="form-group">
						<label>Select a Program</label> <select class="form-control"
							name="programName" ng-model="program.programDO"
							ng-options="program.name for program in programList | orderBy:'name'"
							ng-change="getProgDetails(program.programDO)" validate-on="dirty"
							required="required" ng-disabled="disabled" class="form-control">
							<option value="">Select Program</option>
						</select>
					</div>
					<div class="form-group">
						<label>Dealer</label>
						<p>&nbsp;&nbsp;&nbsp;{{program.dealerDO.name}}</p>
					</div>
					<div class="form-group">
						<label>Manufacturer</label> <input type="text"
							ng-model="program.manufacturerDO.name" placeholder="Manufacturer"
							id="manfName" name="manfName" class="form-control"
							validate-on="dirty" required="required" ng-disabled="disabled">
					</div>
					<div class="form-group">
						<label>Model</label> <select class="form-control" name="modelName"
							ng-model="program.machineInfoDO"
							ng-options="machineInfooDO.model for machineInfooDO in program.machineInfoDOList | orderBy:'model'"
							ng-change="getRemProgDetails(program.machineInfoDO)"
							validate-on="dirty" required="required" ng-disabled="disabled" class="form-control">
							<option value="">Select Model</option>
						</select>
					</div>
					<div class="form-group">
						<label>Model Year</label> <input type="text" id="modelYear"
							name="modelYear" ng-model="program.modelYear"
							placeholder="Model Year" class="form-control" validate-on="dirty"
							required="required" ng-disabled="disabled">
					</div>
					<div class="form-group">
						<label>Serial Number</label> <input type="text" id="modelYear"
							name="serialNumber" ng-model="program.serialNumber"
							placeholder="Serial Number" class="form-control" validate-on="dirty"
							required="required" ng-disabled="disabled">
					</div>
					<div class="form-group">
						<label>Hours at Enrollment</label> <input type="text"
							id="machineMeterHours" name="machineMeterHours"
							ng-model="program.machineMeterHours" placeholder="Hours at Enrollment"
							class="form-control" ng-disabled="disabled">
					</div>
					<div class="form-group" ng-hide="mfgCoverageDisabled">
						<label>MFG Coverage Ends</label> 
						<!-- <input type="date"
							id="coverage" name="coverage" ng-model="program.coverageEndDate"
							placeholder="Manufacturer Coverage end date" class="form-control"
							validate-on="dirty" required="required" ng-disabled="disabled"> -->
						<div class="input-group">
                           <input type="text" class="form-control" 
				                   datepicker-popup="MM/dd/yyyy"
				                   datepicker-options="dateOptions" 
				                   is-open="coverageEndDatePickerIsOpen" 
				                   ng-click="coverageEndDatePickerOpen()"
				                   ng-model="program.coverageEndDate"
				                   validate-on="dirty" 
				                   required="required"/>
							<span class="input-group-btn">
				              <button type="button" class="btn btn-default" 
				                      ng-click="coverageEndDatePickerOpen($event)">
				                <i class="glyphicon glyphicon-calendar"></i>
				              </button>
				            </span>
				        </div>
					</div>
					
					<div class="form-group" ng-hide="coverageStartDateDisabled">
						<label>Start Date of Coverage</label> 
							<!-- <input type="date"
							id="estSaleDate" name="estSaleDate" ng-model="program.estSaleDate"
							placeholder="Start Date of Coverage" class="form-control"
							validate-on="dirty" required="required" ng-disabled="disabled"> -->
						
						<div class="input-group">
                           <input type="text" class="form-control" 
				                   datepicker-popup="MM/dd/yyyy"
				                   datepicker-options="dateOptions" 
				                   is-open="estSaleDatePickerIsOpen" 
				                   ng-click="estSaleDatePickerOpen()"
				                   ng-model="program.estSaleDate"
				                   validate-on="dirty"
				                   required="required"/>
							<span class="input-group-btn">
				              <button type="button" class="btn btn-default" 
				                      ng-click="estSaleDatePickerOpen($event)">
				                <i class="glyphicon glyphicon-calendar"></i>
				              </button>
				            </span>
				       </div>
					</div>
					
					<div class="form-group">
						<label>Unusual Provisions</label> <input type="textarea"
							id="provisions" name="provisions" ng-model="program.provisions"
							placeholder="Unusual Provisions" class="form-control"
							ng-disabled="disabled">
					</div>
					<div class="form-group ">
						<button class="btn btn-primary btn-lg btn-block login-button"
							type="submit">Submit</button>
					</div>
				</div>



				<div class="col-md-6 no-pad pad10-left border-left">
				<h3>Program Details</h3>
                        <div class="col-xs-12 border-bottom no-pad">
						<div class="form-group">
							<label>Condition</label> 
							<p>{{(program.condition == 1)?"New":(program.condition == 0)?"Used":""}}</p>
						</div>
						<div class="form-group">
							<label>Type of Coverage</label> 
						<p>{{(program.cType=="PT")?"Powertrain":(program.cType=="PH")?"Powertrain + Hydraulic":(program.cType=="PL")?"Powertrain + Hydraulic + Platform":""}}</p>
						</div>
						<div class="form-group">
							<label>Coverage Term</label> <p>{{program.cTerm}}</p>
						</div>
						<div class="form-group">
							<label>Hours Covered</label> <p>{{program.cHours}}</p>
						</div>
						<div class="form-group">
							<label>Deductible</label><p>{{program.deductible | currency:"$":0}}</p>
						</div>
						<div class="form-group">
							<label>Limit of Liability</label> <p>{{program.lol | currency:"$":0}}</p>
						</div>
						<div class="form-group">
							<label>Cost</label> <p>{{program.cost | currency:"$":0}}</p>
						</div>
						<div class="form-group">
							<label>Program Description</label>
							<p>{{program.desc}}</p>
						</div>

					</div>
					<br clear="all"> <br clear="all">
					<h3>Customer Information</h3>
					<div class="col-xs-12 no-pad">
						<div class="form-group">
							<label>Name</label> <input type="text" id="custName" name="custName"
													ng-model="program.customerInfoDO.name" placeholder="Name" class="form-control"
								validate-on="dirty" required="required" ng-disabled="disabled">
						</div>
						<div class="form-group">
							<label>Address</label> <input type="text" id="address" name="address"
													ng-model="program.customerInfoDO.address"
													placeholder="Address" class="form-control"
								ng-disabled="disabled">
						</div>
						<div class="form-group">
							<label>City</label> <input type="text" id="city" name="city"
													ng-model="program.customerInfoDO.city" placeholder="City" class="form-control"
								ng-disabled="disabled">
						</div>
						<div class="form-group">
							<label>State/Province</label> <select class="form-control"
								name="state" ng-model="program.customerInfoDO.state" id="state"
								ng-disabled="disabled">
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
							<label>Zip</label> <input type="text" id="Zip"
								name="Zip" ng-model="program.customerInfoDO.zip" placeholder="Zip"
								class="form-control" 
								ng-disabled="disabled">
						</div>
						<div class="form-group">
							<label>Phone Number</label> <input type="text" id="phone" name="phone"
													ng-model="program.customerInfoDO.phone" placeholder="Phone" class="form-control"
								 ng-disabled="disabled">
						</div>
						<div class="form-group">
							<label>Email</label> <input type="text" d="email" name="email"
													ng-model="program.customerInfoDO.email" placeholder="Email" class="form-control"
								ng-disabled="disabled">
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	<!-- end data table section -->

</article>
<!-- /Article -->