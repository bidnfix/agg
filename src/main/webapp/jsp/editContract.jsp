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
				<h3>Contract Details</h3>
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