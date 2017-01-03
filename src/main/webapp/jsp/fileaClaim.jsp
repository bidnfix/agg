<div ng-controller="ClaimsController">
	<!-- Article main content -->
		<article class="col-md-9 maincontent" ng-show="showSearchClaim">
			<header class="page-header">
	              	<div class="col-md-6 col-sm-12">
						<div class="sec-title">
							<h3 class="wow animated bounceInLeft">File a Claim</h3>
						</div>
                    </div>
			</header>
			<!-- search serial/vin# form -->
			<div class="inner-main">
				<form class="form-horizontal" role="form" ng-submit="onClickSearchSerialNo()">
					<div class="form-group">
						<div class="col-sm-8">
							<input type="text" id="serial" ng-model="serialNo" placeholder="Serial/VIN #" class="form-control">
						</div>
						<div class="col-sm-4">
							<button type="submit" class="btn btn-primary btn-block">Search</button>
						</div>
					</div>
				</form>
			</div>
			<div class="inner-main" ng-if='showActiveContractDetails'>
				<table id="contractsTable" class="table table-striped table-bordered" cellspacing="0" width="100%">
					<thead>
			            <tr>
			            	<th>Contract ID</th>
			                <th>Serial/VIN #</th>
			                <th>Manufacturer</th>
			                <th>Model</th>
			                <th>Contract Exp Dt</th>
			                <th>Usage Hours coverage</th>
			                <th>LOL</th>
			                <th>Available LOL</th>
			                <th>Deductible</th>
			            </tr>
			        </thead>
					<tbody>
			            <tr ng-repeat="contract in contractDOList" ng-click="onClickSelectContract(contract)">
			                <td>{{contract.contractID}}</td>
			                <td>{{contract.machineSerialNo}}</td>
			                <td>{{contract.manfactureName}}</td>
			                <td>{{contract.machineModel}}</td>
			                <td>{{contract.expirationDate}}</td>
			                <td>{{contract.usageHoursCovered}}</td>
			                <td>{{contract.lol}}</td>
			                <td>{{contract.availableLol}}</td>
			                <td>{{contract.deductible}}</td>
			            </tr>
			        </tbody>
				</table>
			</div>
		</article>
	<!-- /Article -->
	<!-- Article main content -->
			<article class="col-md-9 maincontent" ng-show="showContractDetails">
				<header class="page-header">
                	<div class="col-md-9 col-sm-12">
					<div class="sec-title">
                        
							<h3 class="wow animated bounceInLeft">New Claim</h3>
							<p class="wow animated bounceInRight">
								Claim #: {{claim.claimId}}
								&nbsp;&nbsp;&nbsp;
								{{(claim.currStatus > 0)?((claim.currStatus === 1)?"Status: Open":(claim.currStatus === 2)?"Status: Pre-Auth Requested":(claim.currStatus === 3)?"Status: Submitted":
				                (claim.currStatus === 4)?"Status: Approved":(claim.currStatus === 5)?"Status: Pre-Auth Approved":(claim.currStatus === 6)?"Status: Pre-Auth Rejected":
				                (claim.currStatus === 7)?"Status: Approved with adjustment":(claim.currStatus === 8)?"Status: Pending":(claim.currStatus === 9)?"Status: Draft":
				                (claim.currStatus === 10)?"Status: Rejected":""):""}}
				                
							</p>
						</div>
                        </div>
                        <div class="col-md-3 col-sm-12"><a class="btn btn-primary pull-right btn-sm mar-right" ng-click="onClickBackToList()">Back</a></div>
				</header>
                
                
				 <!-- data table section -->
                
                <div class="inner-main" ng-show="!adminFlag">
                	<jsp:include page="saveClaimForm.jsp"></jsp:include>
                </div>
                <div class="inner-main" ng-show="adminFlag">
                	<jsp:include page="showclaim.jsp"></jsp:include>
                </div>
			
			<!-- end data table section -->
                
			</article>
			<!-- /Article -->
</div>