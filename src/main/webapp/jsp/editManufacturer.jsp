<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div ng-controller="ClaimsController">
	<!-- Article main content -->
		<article class="col-md-9 maincontent" ng-show="showSearchClaim">
			<header class="page-header">
	              	<div class="col-md-6 col-sm-12">
						<div class="sec-title">
							<h3 class="wow animated bounceInLeft">File a Claim</h3>
						</div>
						<div>
							<h4 class="wow animated bounceInLeft">Please submit one failure per claim</h4>
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
			<div class="inner-main">
				<table id="contractsTable" class="table table-striped table-bordered" cellspacing="0" width="100%">
					<thead>
			            <tr>
			            	<th>Machine Type</th>
			                <th>Model</th>
			                <th>Engine Power</th>
			                <th>Group ID</th>
			            </tr>
			        </thead>
					<tbody>
			            <tr ng-repeat="machine in machineInfoList">
			                <td>{{machine.contractID}}</td>
			                <td>{{contract.machineSerialNo}}</td>
			                <td>{{contract.manfactureName}}</td>
			                <td>{{contract.machineModel}}</td>
			            </tr>
			        </tbody>
				</table>
			</div>
		</article>
	<!-- /Article -->
	<!-- Article main content -->
			
				</header>
                
                
				 <!-- data table section -->
                
                			
			<!-- end data table section -->
                
			</article>
			<!-- /Article -->
</div>