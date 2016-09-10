<!-- Article main content -->
<article class="col-md-9 maincontent">
	<header class="page-header">
		<div class="col-md-8 col-sm-12">
			<div class="sec-title">
				<h2 class="wow animated bounceInLeft">File a Claim</h2>
			</div>
		</div>
	</header>
	<div  ng-controller="ClaimsController" ng-init="showContractDetails=false; showActiveContractDetails=false">
		<div class="inner-main">
			<!-- search serial/vin# form -->
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
		
		<div ng-if='showContractDetails'>
			<div class="inner-main">
				<!-- show quote info -->
				<jsp:include page="fc_quoteinfo.jsp"></jsp:include>
			</div>
			<div class="inner-main">
				<!-- show add contract form -->
				<jsp:include page="fc_addContractForm.jsp"></jsp:include>
			</div>
		</div>
		<div ng-if='showActiveContractDetails'>
			<table id="contractsTable" class="table table-striped table-bordered" cellspacing="0" width="100%">
				<thead>
		            <tr>
		            	<th>ID</th>
		                <th>Manufacturer</th>
		                <th>Model</th>
		                <th>Serial</th>
		            </tr>
		        </thead>
				<tbody>
		            <tr ng-repeat="contract in contractDOList" ng-click="onClickSelectContract(contract)">
		                <td>{{contract.contractId}}</td>
		                <td>{{contract.manufacturerDO.name}}</td>
		                <td>{{contract.machineModel}}</td>
		                <td>{{contract.machineSerialNo}}</td>
		            </tr>
		        </tbody>
			</table>
		</div>
	</div>
</article>