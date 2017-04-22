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
					<div class="form-group">
						<label>Dealer Markup</label>
						<p>{{contract.quoteDO.dealerMarkup | currency:"$":0}}</p>
					</div>
					<div class="form-group">
						<label>Quote Price</label>
						<p>{{contract.quoteDO.quoteBasePrice | currency:"$":0}}</p>
					</div>
					<div class="form-group">
						<label>Program</label>
				        <p>{{contract.quoteDO.program}}</p>
					</div>
					<div class="form-group">
						<label>Special Considerations</label>
				        <p>{{contract.quoteDO.specialConsiderations}}</p>
					</div>
					<div class="form-group">
						<label>Deal History</label>
				        <p>{{contract.quoteDO.dealHistory}}</p>
					</div>
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
						<div class="form-group">
							<label>Dealer Quote</label> 
							<p><button class="btn btn-primary btn-xs mar-right" ng-click="printQuote('dealer')">View</button></p>
						</div>
						<div class="form-group">
							<label>Dealer Invoice</label> 
							<p><button class="btn btn-primary btn-xs mar-right" ng-click="printQuote('invoice')">View</button></p>
						</div>
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
						<div class="form-group">
							<label>Markup Type</label>
							<p>{{contract.quoteDO.dealerMarkupType}}</p>
						</div>
						<div class="form-group">
							<label>Customer Price</label>
							<p>{{(contract.quoteDO.quoteBasePrice + contract.quoteDO.dealerMarkupPrice) | currency:"$":0}}</p>
						</div>
						<div class="form-group">
							<label>Markup Type</label>
							<p>{{contract.quoteDO.dealerMarkupType}}</p>
						</div>
						<div class="form-group">
							<label>Adjusted Base Price</label>
							<p>{{contract.quoteDO.quoteBasePrice}}</p>
						</div>
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
			
			<div class="col-xs-12 agf1 main-login pad10-top">
				<h4>Contract Details</h4>
				<div class="col-md-6 no-pad pad10-right">
					<div class="form-group">
						<label>Contract ID</label>
						<p>{{contract.contractId}}</p>
					</div>
					<div class="form-group">
						<label>Quote ID</label>
						<p>{{contract.quoteId}}</p>
					</div>
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
						<p>{{contract.expirationUsageHours}}</p>
					</div>
					<div class="form-group">
						<label for="cheqNo">Check Number</label>
						<input type="text" id="cheqNo" name="cheqNo" ng-model="contract.cheqNo" class="form-control">
					</div>
					<div class="form-group">
						<label for="receivedDate">Received Date</label>
						<!-- <input type="date" id="receivedDate" name="receivedDate" ng-model="contract.receivedDate" class="form-control"> -->
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
					</div>
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
				</div>

				<div class="col-md-6 no-pad pad10-left border-left">
                    <div class="col-xs-12 no-pad">
						<div class="form-group">
							<label>Machine Serial Number</label> 
							<p>{{contract.machineSerialNo}}</p>
						</div>
						<div class="form-group">
							<label>Coverage Type</label> 
							<p>{{contract.coverageType}}</p>
						</div>
						<div class="form-group">
							<label>Coverage Term</label> 
							<p>{{(contract.coverageTermMonths != null)?contract.coverageTermMonths+"&nbsp;mos.":""}}</p>
						</div>
						<div class="form-group">
							<label>Coverage Level Hours</label> 
							<p>{{contract.coverageLevelHours}}</p>
						</div>
						<div class="form-group">
							<label>Deductible</label>
							<p>{{contract.deductible | currency:"$":0}}</p>
						</div>
						<div class="form-group">
							<label>Limit of Liability</label> 
							<p>{{contract.lol | currency:"$":0}}</p>
						</div>
						<div class="form-group">
							<label>Last Updated date</label>
							<p>{{contract.lastUpdatedDate | date:'MM/dd/yyyy'}}</p>
						</div>
					</div>
				</div>
				<div class="col-sm-12 no-pad t-c marg10-bottom">
					<div class="col-md-6 col-centered">
							<button class="btn btn-primary btn-lg btn-block login-button"
								type="submit">Submit</button>
					</div>
				</div>
			</div>
		</div>
	</form>
	<!-- end data table section -->

</article>
<!-- /Article -->