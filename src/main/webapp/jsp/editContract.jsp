<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Article main content -->
<article class="col-md-9 maincontent">
	<form class="form-horizontal" name="contractInfoForm" id="contractInfoForm"
		ng-submit="contractInfoForm.$valid && updateContract(contractInfoForm)" novalidate angular-validator>
		<header class="page-header">
			<div class="col-md-6 col-sm-12">
				<div class="sec-title">

					<h3 class="wow animated bounceInLeft">Manage a Contract</h3>
				</div>
			</div>

		</header>

		<!-- data table section -->

		<div class="inner-main">
			<div class="col-xs-12 agf1 main-login pad10-top">
				<div id="contractSuccessMsg" class="alert alert-info text-center hidden"></div>
				<h4>Contract Details</h4>
				<div class="col-md-6 no-pad pad10-right">
					<div class="form-group">
						<label>Contract ID</label>
						<p>{{contract.contractId}}</p>
					</div>
					<div class="form-group">
						<label>Quote ID</label>
						<p>{{contract.quoteDO.quoteId}}</p>
					</div>
					<c:choose>
						<c:when test="${user.roleDO.accountType eq 'admin'}">
							<div class="form-group">
								<label>Available LOL</label> 
								<input type="text"
									ng-model="contract.availabeLol" placeholder="Available LOL"
									id="availabeLol" name="availabeLol" class="form-control"
									validate-on="dirty" required="required">
							</div>
							<div class="form-group">
								<label>Inception Date</label> 
								<!-- <input type="date"
									id="inceptionDate" name="inceptionDate" ng-model="contract.inceptionDate"
									placeholder="Inception Date" class="form-control"
									validate-on="dirty" required="required"> -->
								<div class="input-group">
									<input type="text" class="form-control" 
					                   datepicker-popup="MM/dd/yyyy"
					                   datepicker-options="dateOptions" 
					                   is-open="inceptionDatePickerIsOpen" 
					                   ng-click="inceptionDatePickerOpen()"
					                   ng-model="contract.inceptionDate"
					                   validate-on="dirty"
					                   required="required"/>
						            <span class="input-group-btn">
						              <button type="button" class="btn btn-default" 
						                      ng-click="inceptionDatePickerOpen($event)">
						                <i class="glyphicon glyphicon-calendar"></i>
						              </button>
						            </span>
				            	</div>
							</div>
							<div class="form-group">
								<label>Expiration Date</label> 
									<!-- <input type="date"
									id="expirationDate" name="expirationDate" ng-model="contract.expirationDate"
									placeholder="Expiration Date" class="form-control"
									validate-on="dirty" required="required"> -->
								<div class="input-group">
									<input type="text" class="form-control" 
					                   datepicker-popup="MM/dd/yyyy"
					                   datepicker-options="dateOptions" 
					                   is-open="expirationDatePickerIsOpen" 
					                   ng-click="expirationDatePickerOpen()"
					                   ng-model="contract.expirationDate"
					                   validate-on="dirty"
					                   required="required"/>
						            <span class="input-group-btn">
						              <button type="button" class="btn btn-default" 
						                      ng-click="expirationDatePickerOpen($event)">
						                <i class="glyphicon glyphicon-calendar"></i>
						              </button>
						            </span>
				            	</div>
							</div>
							<div class="form-group">
								<label>Expiration Usage Hours</label>
								<input type="text"
									ng-model="contract.expirationUsageHours" placeholder=""
									id="expirationUsageHours" name="expirationUsageHours" class="form-control"
									validate-on="dirty" required="required">
							</div>
							<!-- <div class="form-group">
								<label for="cheqNo">Check Number</label>
								<input type="text" id="cheqNo" name="cheqNo" ng-model="contract.cheqNo" class="form-control">
							</div>
							<div class="form-group">
								<label for="receivedDate">Received Date</label>
								<input type="date" id="receivedDate" name="receivedDate" ng-model="contract.receivedDate" class="form-control">
								<div class="input-group">
									<input type="text" class="form-control" 
					                   datepicker-popup="MM/dd/yyyy"
					                   datepicker-options="dateOptions" 
					                   is-open="receivedDatePickerIsOpen" 
					                   ng-click="receivedDatePickerOpen()"
					                   ng-model="contract.receivedDate"/>
						            <span class="input-group-btn">
						              <button type="button" class="btn btn-default" 
						                      ng-click="receivedDatePickerOpen($event)">
						                <i class="glyphicon glyphicon-calendar"></i>
						              </button>
						            </span>
					            </div>
							</div> -->
							<div class="form-group">
								<label>Comments</label>
								<textarea id="comments" name="comments" ng-model="contract.comments"
									placeholder="" class="form-control"></textarea>
							</div>
							<div class="form-group">
								<label>Status</label>
						        <select class="form-control" name="status" ng-model="contract.status" convert-to-number id="status"  validate-on="dirty" required="required">
								  <option value="1">Active</option>
								  <option value="2">Expired</option>
								  <option value="3">Cancelled</option>
								  <option value="4">Archived</option>
								</select>
							</div>
						</c:when>
						<c:otherwise>
							<div class="form-group col-xs-12 no-pad">
								<label>Available LOL</label>
								<p>{{contract.availabeLol | currency:"$":0}}</p> 
							</div>
							<div class="form-group col-xs-12 no-pad">
								<label>Inception Date</label>
								<p>{{contract.inceptionDate | date:'MM/dd/yyyy'}}</p>  
							</div>
							<div class="form-group col-xs-12 no-pad">
								<label>Expiration Date</label>
								<p>{{contract.expirationDate | date:'MM/dd/yyyy'}}</p> 
							</div>
							<div class="form-group col-xs-12 no-pad">
								<label>Expiration Usage Hours</label>
								<p>{{contract.expirationUsageHours}}</p>
							</div>
							<!-- <div class="form-group col-xs-12 no-pad">
								<label>Check Number</label>
								<p>{{contract.cheqNo}}</p>
							</div>
							<div class="form-group col-xs-12 no-pad">
								<label for="receivedDate">Received Date</label>
								<p>{{contract.receivedDate | date:'MM/dd/yyyy'}}</p>
							</div> -->
							<div class="form-group col-xs-12 no-pad">
								<label>Comments</label>
								<p>{{contract.comments}}</p>
							</div>
							<div class="form-group col-xs-12 no-pad">
								<label>Status</label>
								<p>{{(contract.status == 1)?"Active":(contract.status == 2)?"Expired":(contract.status == 3)?"Cancelled":(contract.status == 4)?"Archived":""}}</p>
							</div>
						</c:otherwise>
					</c:choose>
					<div class="form-group">
						<label>Servicing Dealer</label>
						<input type="text" ng-model="contract.servicingDealerDO" placeholder="" typeahead="dealerDO as dealerDO.name+' - '+dealerDO.city for dealerDO in dealerDOList | filter:{name:$viewValue} | limitTo:8" class="form-control" >
					</div>
					<div class="form-group">
						<label>Customer Name</label>
						<input type="text" id="custName" name="custName" ng-model="contract.quoteDO.dealerName" class="form-control" required="required">
					</div>
					<div class="form-group">
						<label>Customer Address</label>
						<input type="text" id="custAddress" name="custAddress" ng-model="contract.quoteDO.dealerAddress" class="form-control" required="required">
					</div>
					 <div class="form-group">
                         <label>Customer State/Province</label>
                         <select class="form-control" name="custState" ng-model="contract.quoteDO.dealerState" id="custState" required="required">
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
                         <label>Customer Phone</label>
                         <input type="text" id="custPhone" name="custPhone" ng-model="contract.quoteDO.dealerPhone" class="form-control">
                       </div>
				</div>

				<div class="col-md-6 no-pad pad10-left border-left">
                    <div class="col-xs-12 no-pad">
                    	<div class="form-group">
							<label>Contract Details</label> 
							<p><button class="btn btn-primary btn-xs mar-right" type="button" ng-click="printContract('contract')">View</button></p>
						</div>
						<div class="form-group col-xs-12 no-pad">
							<label>Coverage Details</label> 
							<p><button class="btn btn-primary btn-xs mar-right" type="button" ng-click="printContract('coverage')">View</button></p>
						</div>
						<c:choose>
							<c:when test="${user.roleDO.accountType eq 'admin'}">
								<div class="form-group">
			                        <label>Machine Serial Number</label>
			                        <input type="text" id="serialNumber" name="serialNumber" ng-model="contract.machineSerialNo" class="form-control" required="required">
		                       	</div>
								<div class="form-group">
			                         <label>Coverage Type</label>
								     <select name="adjustedCoverageType" ng-model="contract.coverageType" class="form-control"  required="required">
			                         	<option value="">Select Coverage Type</option>
			                         	<option value="PT">Powertrain</option>
						                <option value="PH">Powertrain + Hydraulic</option>
						                <option value="PL">Powertrain + Hydraulic + Platform</option>
									 </select>
			                     </div>
								<div class="form-group">
									<label>Coverage Term</label>
									<input type="number" id="coverageTermMonths" name="coverageTermMonths" ng-model="contract.coverageTermMonths" class="form-control" ng-blur="calExpirationDate()" required="required">
								</div>
								<div class="form-group">
									<label>Coverage Level Hours</label>
									<input type="number" id="coverageLevelHours" name="coverageLevelHours" ng-model="contract.coverageLevelHours" class="form-control" ng-blur="calExpirationDate()" required="required">
								</div>
								<div class="form-group">
									<label>Deductible</label>
									<input type="number" id="deductiblePrice" name="deductiblePrice" ng-model="contract.deductible" class="form-control" required="required">
								</div>
								<div class="form-group">
									<label>Limit of Liability</label> 
									<p>{{contract.lol | currency:"$":0}}</p>
								</div>
								<div class="form-group">
									<label>Last Updated date</label>
									<p>{{contract.lastUpdatedDate | date:'MM/dd/yyyy'}}</p>
								</div>
								<div class="form-group">
									<label>Adjusted Base Price</label>
									<input type="number" id="adjustedBasePrice" name="adjustedBasePrice" ng-model="contract.quoteDO.adjustedBasePrice" class="form-control" required="required">
								</div>
							</c:when>
							<c:otherwise>
								<div class="form-group col-xs-12 no-pad">
									<label>Machine Serial Number</label> 
									<p>{{contract.machineSerialNo}}</p>
								</div>
								<div class="form-group col-xs-12 no-pad">
									<label>Coverage Type</label> 
									<p>{{contract.coverageType}}</p>
								</div>
								<div class="form-group col-xs-12 no-pad">
									<label>Coverage Term</label> 
									<p>{{(contract.coverageTermMonths != null)?contract.coverageTermMonths+"&nbsp;mos.":""}}</p>
								</div>
								<div class="form-group col-xs-12 no-pad">
									<label>Coverage Level Hours</label> 
									<p>{{contract.coverageLevelHours}}</p>
								</div>
								<div class="form-group col-xs-12 no-pad">
									<label>Deductible</label>
									<p>{{contract.deductible | currency:"$":0}}</p>
								</div>
								<div class="form-group col-xs-12 no-pad">
									<label>Limit of Liability</label> 
									<p>{{contract.lol | currency:"$":0}}</p>
								</div>
								<div class="form-group col-xs-12 no-pad">
									<label>Last Updated date</label>
									<p>{{contract.lastUpdatedDate | date:'MM/dd/yyyy'}}</p>
								</div>
								<!-- <div class="form-group col-xs-12 no-pad">
									<label>Adjusted Base Price</label>
									<p>{{contract.quoteDO.adjustedBasePrice | currency:"$":0}}</p>
								</div> -->
							</c:otherwise>
						</c:choose>
						<div class="form-group">
	                         <label>Customer City</label>
	                         <input type="text" id="custCity" name="dcustCity" ng-model="contract.quoteDO.dealerCity" class="form-control">
	                     </div>
	                     <div class="form-group">
	                         <label>Customer Zip</label>
	                         <input type="text" id="custZip" name="custZip" ng-model="contract.quoteDO.dealerZip" class="form-control" required="required">
	                      </div>
	                      <div class="form-group">
	                         <label>Customer Email</label>
	                         <input type="text" id="custEmail" name="custEmail" ng-model="contract.quoteDO.dealerEmail" class="form-control">
	                       </div>
					</div>
				</div>
				<div class="col-xs-12 no-pad table-responsive clearfix">
					<c:choose>
						<c:when test="${user.roleDO.accountType eq 'admin'}">
							<table class="table table-bordered">
								<thead>
									<tr>
										<th class="col-sm-4">Check #</th>
										<th class="col-sm-4">Received Date</th>
										<th class="col-sm-3">Check Amount</th>
										<th class="col-sm-1">
											<button type="button" class="btn btn-primary btn-sm" ng-click="addCheck()">
											<i class="fa fa-plus"></i>
									</button>
										</th>
									</tr>
								</thead>
								<tbody data-ng-repeat="checkDO in contract.checkDOList">
									<tr>
										<td><input type="text" class="form-control" name="checkNo" ng-model="checkDO.checkNo" required="required"></td>
										<td>
											<div class="agf1 input-group">
												<input type="text" class="form-control"
												   name="receivedDate" 
								                   datepicker-popup="MM/dd/yyyy"
								                   datepicker-options="dateOptions" 
								                   is-open="checkDO.chkDatePickerIsOpen" 
								                   ng-click="checkDO.chkDatePickerIsOpen=true"
								                   ng-model="checkDO.receivedDate"/>
									            <span class="input-group-btn">
									              <button type="button" class="btn btn-default" 
									                      ng-click="chkDatePickerOpen($event, checkDO)">
									                <i class="glyphicon glyphicon-calendar"></i>
									              </button>
									            </span>
								            </div>
										</td>
										<td><input type="number" step="0.01" class="form-control" name="amount" ng-model="checkDO.amount" ng-change="calcCheckAmtTotal()" required="required"></td>
										<td>
											<button ng-if="contract.checkDOList.length > 1" type="button" class="btn btn-primary btn-sm" ng-click="removeCheck(checkDO);">
												<i class="fa fa-minus"></i>
											</button>
										</td>
									</tr>
								</tbody>
							</table>
						</c:when>
						<c:otherwise>
							<!-- <table class="table table-bordered">
							   <thead>
									<tr>
										<th class="col-sm-4">Check #</th>
										<th class="col-sm-4">Received Date</th>
										<th class="col-sm-3">Check Amount</th>
									</tr>
								</thead>
								<tbody data-ng-repeat="checkDO in contract.checkDOList">
									<tr>
										<td>{{checkDO.checkNo}}</td>
										<td>
											{{checkDO.receivedDate | date:'MM/dd/yyyy'}}
										</td>
										<td>{{checkDO.amount | currency}}</td>
									</tr>
								</tbody>
							</table> -->
						</c:otherwise>
					</c:choose>
					<c:if test="${user.roleDO.accountType eq 'admin'}">
						<div class="col-sm-12">
							<div class="col-sm-6"></div>
							<div class="col-sm-6">
								<div class="col-sm-6 no-pad">Total Check Amount</div>
								<div class="col-sm-6">
									{{contract.totalCheckAmount | currency}}
								</div>
							</div>
						</div>
					</c:if>
				</div>
				<div class="col-sm-12 no-pad t-c marg10-bottom">
					<div class="col-md-6 col-centered">
							<button class="btn btn-primary btn-lg btn-block login-button"
								type="submit">Update Contract</button>
					</div>
				</div>
			</div>
			<div class="col-xs-12 agf1 main-login pad10-top">
				<h4>Quote Details</h4>
				<div class="col-md-6 no-pad pad10-right">
					<div class="form-group">
						<label>Quote ID</label>
						<p>{{contract.quoteDO.quoteId}}</p>
					</div>
					<div class="form-group">
						<label>Dealer</label>
						<p>{{contract.quoteDO.dealerDO.name}}</p>
					</div>
					<div class="form-group">
						<label>Manufacturer</label> 
						<p>{{contract.quoteDO.manufacturerDO.name}}</p>
					</div>
					<div class="form-group">
						<label>Model</label> 
						<p>{{contract.quoteDO.machineInfoDO.model}}</p>
					</div>
					<div class="form-group">
						<label>Meter Hours</label> 
						<p>{{contract.quoteDO.meterHours}}</p>
					</div>
					<div class="form-group">
						<label>Condition</label>
						<p>{{(contract.quoteDO.coverageExpired === true)?"Used":"New"}}</p>
					</div>
					<div class="form-group">
						<label>MFG End Date</label>
						<p>{{contract.quoteDO.coverageEndDate | date:'MM/dd/yyyy'}}</p>
					</div>
					<c:if test="${user.roleDO.accountType eq 'admin'}">
						<div class="form-group">
							<label>Dealer Markup</label>
							<p>{{contract.quoteDO.dealerMarkup | currency:"$":0}}</p>
						</div>
						<div class="form-group">
							<label>Quote Price</label>
							<p>{{contract.quoteDO.quoteBasePrice | currency:"$":0}}</p>
						</div>
					</c:if>
					<div class="form-group">
						<label>Program</label>
				        <p>{{contract.quoteDO.program}}</p>
					</div>
					<div class="form-group">
						<label>Special Considerations</label>
				        <p>{{contract.quoteDO.specialConsiderations}}</p>
					</div>
					<c:if test="${user.roleDO.accountType eq 'admin'}">
						<div class="form-group">
							<label>Deal History</label>
					        <p>{{contract.quoteDO.dealHistory}}</p>
						</div>
					</c:if>
					<div class="form-group">
						<label>Customer Name</label>
				        <p>{{contract.quoteDO.dealerName}}</p>
					</div>
					<div class="form-group">
						<label>Customer Name</label>
				        <p>{{contract.quoteDO.dealerName}}</p>
					</div>
					<div class="form-group">
						<label>Customer Address</label>
				        <p>{{contract.quoteDO.dealerAddress}}</p>
					</div>
					<div class="form-group">
						<label>Customer State/Province</label>
				        <p>{{contract.quoteDO.dealerState}}</p>
					</div>
					<div class="form-group">
						<label>Customer Phone</label>
				        <p>{{contract.quoteDO.dealerPhone}}</p>
					</div>
				</div>

				<div class="col-md-6 no-pad pad10-left border-left">
                    <div class="col-xs-12 no-pad">
                    	<c:if test="${user.roleDO.accountType eq 'admin'}">
							<div class="form-group">
								<label>Dealer Quote</label> 
								<p><button class="btn btn-primary btn-xs mar-right" type="button" ng-click="printQuote('dealer')">View</button></p>
							</div>
							<div class="form-group">
								<label>Dealer Invoice</label> 
								<p><button class="btn btn-primary btn-xs mar-right" type="button" ng-click="printQuote('invoice')">View</button></p>
							</div>
						</c:if>
						<div class="form-group">
							<label>Machine Type</label> 
							<p>{{contract.quoteDO.machineInfoDO.machineType}}</p>
						</div>
						<div class="form-group">
							<label>Model Year</label> 
							<p>{{contract.quoteDO.machineInfoDO.modelYear}}</p>
						</div>
						<div class="form-group">
							<label>Use of Equipment</label>
							<p>{{contract.quoteDO.useOfEquipmentDO.equipName}}</p>
						</div>
						<div class="form-group">
							<label>Additional Unit Information</label> 
							<p>{{contract.quoteDO.otherProv}}</p>
						</div>
						<c:if test="${user.roleDO.accountType eq 'admin'}">
							<div class="form-group">
								<label>Markup Type</label>
								<p>{{contract.quoteDO.dealerMarkupType}}</p>
							</div>
							<div class="form-group">
								<label>Customer Price</label>
								<p>{{(contract.quoteDO.quoteBasePrice + contract.quoteDO.dealerMarkupPrice) | currency:"$":0}}</p>
							</div>
							<div class="form-group">
								<label>Adjusted Base Price</label>
								<p>{{contract.quoteDO.quoteBasePrice}}</p>
							</div>
						</c:if>
						<div class="form-group">
							<label>Adjusted LOL</label>
							<p>{{contract.quoteDO.machineInfoDO.lol | currency:"$":0}}</p>
						</div>
						<div class="form-group">
							<label>Customer City</label>
							<p>{{contract.quoteDO.dealerCity}}</p>
						</div>
						<div class="form-group">
							<label>Customer Zip</label>
							<p>{{contract.quoteDO.dealerZip}}</p>
						</div>
						<div class="form-group">
							<label>Customer Email</label>
							<p>{{contract.quoteDO.dealerEmail}}</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	<!-- end data table section -->

</article>
<!-- /Article -->