<!-- Article main content -->
<article class="col-md-9 maincontent">
		<header class="page-header">
			<div class="col-md-6 col-sm-12">
				<div class="sec-title">
					<h3 class="wow animated bounceInLeft">Contract Details</h3>
				</div>
			</div>
			<div class="col-md-6 col-sm-12">
				<button class="btn btn-primary pull-right mar-right btn-sm" ng-click="cancelContract()">Cancel</button>
			</div>
		</header>

		<!-- data table section -->

		<div class="inner-main">
			<div class="col-xs-12 agf1 main-login pad10-top">
				<h4>Contract Details</h4>
				<div class="col-md-6 no-pad pad10-right">
					<div class="form-group col-xs-12 no-pad">
						<label>Contract ID</label>
						<p>{{contract.contractId}}</p>
					</div>
					<!-- <div class="form-group col-xs-12 no-pad">
						<label>Quote ID</label>
						<p>{{contract.quoteId}}</p>
					</div> -->
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
					<div class="form-group col-xs-12 no-pad">
						<label>Check Number</label>
						<p>{{contract.cheqNo}}</p>
					</div>
					<div class="form-group col-xs-12 no-pad">
						<label for="receivedDate">Received Date</label>
						<p>{{contract.receivedDate | date:'MM/dd/yyyy'}}</p>
					</div>
					<div class="form-group col-xs-12 no-pad">
						<label>Comments</label>
						<p>{{contract.comments}}</p>
					</div>
					<div class="form-group col-xs-12 no-pad">
						<label>Status</label>
						<p>{{(contract.status == 1)?"Active":(contract.status == 2)?"Expired":(contract.status == 3)?"Cancelled":(contract.status == 4)?"Archived":""}}</p>
					</div>
					<div class="form-group col-xs-12 no-pad">
						<label>Customer Name</label>
				        <p>{{contract.quoteDO.dealerName}}</p>
					</div>
					<div class="form-group col-xs-12 no-pad">
						<label>Customer Address</label>
				        <p>{{contract.quoteDO.dealerAddress}}</p>
					</div>
					<div class="form-group col-xs-12 no-pad">
						<label>Customer State/Province</label>
				        <p>{{contract.quoteDO.dealerState}}</p>
					</div>
					<div class="form-group col-xs-12 no-pad">
						<label>Customer Phone</label>
				        <p>{{contract.quoteDO.dealerPhone}}</p>
					</div>
				</div>

				<div class="col-md-6 no-pad pad10-left border-left">
                   <div class="col-xs-12 no-pad">
                        <div class="form-group col-xs-12 no-pad">
							<label>Contract Details</label> 
							<p><button class="btn btn-primary btn-xs mar-right" ng-click="printContract('contract')">View</button></p>
						</div>
						<div class="form-group col-xs-12 no-pad">
							<label>Coverage Details</label> 
							<p><button class="btn btn-primary btn-xs mar-right" ng-click="printContract('coverage')">View</button></p>
						</div>
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
						<div class="form-group col-xs-12 no-pad">
							<label>Adjusted Base Price</label>
							<p>{{contract.quoteDO.quoteBasePrice}}</p>
						</div>
						<div class="form-group col-xs-12 no-pad">
							<label>Customer City</label>
							<p>{{contract.quoteDO.dealerCity}}</p>
						</div>
						<div class="form-group col-xs-12 no-pad">
							<label>Customer Zip</label>
							<p>{{contract.quoteDO.dealerZip}}</p>
						</div>
						<div class="form-group col-xs-12 no-pad">
							<label>Customer Email</label>
							<p>{{contract.quoteDO.dealerEmail}}</p>
						</div>
					</div>
				</div>
				<div class="col-xs-12 no-pad table-responsive clearfix">
					<table class="table table-bordered">
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
					</table>
					<div class="col-sm-12">
						<div class="col-sm-6"></div>
						<div class="col-sm-6">
							<div class="col-sm-6 no-pad">Total Check Amount</div>
							<div class="col-sm-6">
								{{contract.totalCheckAmount | currency}}
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-xs-12 agf1 main-login pad10-top">
				<h4>Quote Details</h4>
				<div class="col-md-6 no-pad pad10-right">
					<div class="form-group col-xs-12 no-pad">
						<label>Quote ID</label>
						<p>{{contract.quoteDO.quoteId}}</p>
					</div>
					<div class="form-group col-xs-12 no-pad">
						<label>Dealer</label>
						<p>{{contract.quoteDO.dealerDO.name}}</p>
					</div>
					<div class="form-group col-xs-12 no-pad">
						<label>Manufacturer</label> 
						<p>{{contract.quoteDO.manufacturerDO.name}}</p>
					</div>
					<div class="form-group col-xs-12 no-pad">
						<label>Model</label> 
						<p>{{contract.quoteDO.machineInfoDO.model}}</p>
					</div>
					<div class="form-group col-xs-12 no-pad">
						<label>Meter Hours</label> 
						<p>{{contract.quoteDO.meterHours}}</p>
					</div>
					<div class="form-group col-xs-12 no-pad">
						<label>Condition</label>
						<p>{{(contract.quoteDO.coverageExpired === true)?"Used":"New"}}</p>
					</div>
					<div class="form-group col-xs-12 no-pad">
						<label>MFG End Date</label>
						<p>{{contract.quoteDO.coverageEndDate | date:'MM/dd/yyyy'}}</p>
					</div>
					<div class="form-group col-xs-12 no-pad">
						<label>Dealer Markup</label>
						<p>{{contract.quoteDO.dealerMarkup | currency:"$":0}}</p>
					</div>
					<div class="form-group col-xs-12 no-pad">
						<label>Quote Price</label>
						<p>{{contract.quoteDO.quoteBasePrice | currency:"$":0}}</p>
					</div>
					<div class="form-group col-xs-12 no-pad">
						<label>Program</label>
				        <p>{{contract.quoteDO.program}}</p>
					</div>
					<div class="form-group col-xs-12 no-pad">
						<label>Special Considerations</label>
				        <p>{{contract.quoteDO.specialConsiderations}}</p>
					</div>
					<div class="form-group col-xs-12 no-pad">
						<label>Deal History</label>
				        <p>{{contract.quoteDO.dealHistory}}</p>
					</div>
					<div class="form-group col-xs-12 no-pad">
						<label>Customer Name</label>
				        <p>{{contract.quoteDO.dealerName}}</p>
					</div>
					<div class="form-group col-xs-12 no-pad">
						<label>Customer Name</label>
				        <p>{{contract.quoteDO.dealerName}}</p>
					</div>
					<div class="form-group col-xs-12 no-pad">
						<label>Customer Address</label>
				        <p>{{contract.quoteDO.dealerAddress}}</p>
					</div>
					<div class="form-group col-xs-12 no-pad">
						<label>Customer State/Province</label>
				        <p>{{contract.quoteDO.dealerState}}</p>
					</div>
					<div class="form-group col-xs-12 no-pad">
						<label>Customer Phone</label>
				        <p>{{contract.quoteDO.dealerPhone}}</p>
					</div>
				</div>

				<div class="col-md-6 no-pad pad10-left border-left">
                    <div class="col-xs-12 no-pad">
						<div class="form-group col-xs-12 no-pad">
							<label>Dealer Quote</label> 
							<p><button class="btn btn-primary btn-xs mar-right" type="button" ng-click="printQuote('dealer')">View</button></p>
						</div>
						<div class="form-group col-xs-12 no-pad">
							<label>Dealer Invoice</label> 
							<p><button class="btn btn-primary btn-xs mar-right" type="button" ng-click="printQuote('invoice')">View</button></p>
						</div>
						<div class="form-group col-xs-12 no-pad">
							<label>Machine Type</label> 
							<p>{{contract.quoteDO.machineInfoDO.machineType}}</p>
						</div>
						<div class="form-group col-xs-12 no-pad">
							<label>Model Year</label> 
							<p>{{contract.quoteDO.machineInfoDO.modelYear}}</p>
						</div>
						<div class="form-group col-xs-12 no-pad">
							<label>Use of Equipment</label>
							<p>{{contract.quoteDO.useOfEquipmentDO.equipName}}</p>
						</div>
						<div class="form-group col-xs-12 no-pad">
							<label>Additional Unit Information</label> 
							<p>{{contract.quoteDO.otherProv}}</p>
						</div>
						<div class="form-group col-xs-12 no-pad">
							<label>Markup Type</label>
							<p>{{contract.quoteDO.dealerMarkupType}}</p>
						</div>
						<div class="form-group col-xs-12 no-pad">
							<label>Customer Price</label>
							<p>{{(contract.quoteDO.quoteBasePrice + contract.quoteDO.dealerMarkupPrice) | currency:"$":0}}</p>
						</div>
						<div class="form-group col-xs-12 no-pad">
							<label>Markup Type</label>
							<p>{{contract.quoteDO.dealerMarkupType}}</p>
						</div>
						<div class="form-group col-xs-12 no-pad">
							<label>Adjusted Base Price</label>
							<p>{{contract.quoteDO.quoteBasePrice}}</p>
						</div>
						<div class="form-group col-xs-12 no-pad">
							<label>Adjusted LOL</label>
							<p>{{contract.quoteDO.machineInfoDO.lol | currency:"$":0}}</p>
						</div>
						<div class="form-group col-xs-12 no-pad">
							<label>Customer City</label>
							<p>{{contract.quoteDO.dealerCity}}</p>
						</div>
						<div class="form-group col-xs-12 no-pad">
							<label>Customer Zip</label>
							<p>{{contract.quoteDO.dealerZip}}</p>
						</div>
						<div class="form-group col-xs-12 no-pad">
							<label>Customer Email</label>
							<p>{{contract.quoteDO.dealerEmail}}</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	<!-- end data table section -->
</article>
<!-- /Article -->