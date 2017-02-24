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
				</div>

				<div class="col-md-6 no-pad pad10-left border-left">
				<!-- <h3>Contract Details</h3> -->
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
					</div>
				</div>
			</div>
		</div>
	<!-- end data table section -->
</article>
<!-- /Article -->