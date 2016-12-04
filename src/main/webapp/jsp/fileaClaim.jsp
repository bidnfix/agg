<div ng-controller="ClaimsController" ng-init="showSearchClaim=true;showContractDetails=false; showActiveContractDetails=false">
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
                	<div class="col-md-6 col-sm-12">
					<div class="sec-title">
                        
							<h3 class="wow animated bounceInLeft">New Claim</h3>
							<p class="wow animated bounceInRight">Claim #: {{claim.claimId}}</p>
						</div>
                        </div>
                        <div class="col-md-6 col-sm-12"><a class="btn btn-primary pull-right btn-sm mar-right" ng-click="onClickBackToList()">Back</a></div>
				</header>
                
                
				 <!-- data table section -->
                
                <div class="inner-main">
                	<jsp:include page="saveClaimForm.jsp"></jsp:include>
                </div>
			
			<!-- end data table section -->
                
			</article>
			<!-- /Article -->
</div>