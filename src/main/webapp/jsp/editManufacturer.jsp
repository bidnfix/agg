<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div ng-controller="ClaimsController">
	<!-- Article main content -->
		<article class="col-md-9 maincontent">
			<header class="page-header">
	              	<div class="col-md-6 col-sm-12">
						<div class="sec-title">
							<h3 class="wow animated bounceInLeft">Manufacturer</h3>
						</div>
                    </div>
			</header>
			<!-- search serial/vin# form -->
			<div class="inner-main">
				<form class="form-horizontal" role="form" ng-submit="onClickSearchSerialNo()">
					<div class="form-group">
						
							<label for="machineType" class="col-sm-3 control-label">ID</label>
							<div class="col-sm-9">
								<input type="text" id="id" name="equipName" ng-model="manfDO.id" class="form-control" readonly="readonly" >
							</div>
						
							<label for="machineType" class="col-sm-3 control-label">Manufacturer</label>
							<div class="col-sm-9">
								<input type="text" id="manufacturer" name="manufacturerName" ng-model="manfDO.name" placeholder="Manufacturer" class="form-control" required="required" >
							</div>
						
							<label for="machineType" class="col-sm-3 control-label">Adjustment Factor</label>
							<div class="col-sm-9">
								<input type="text" id="adjFactor" name="adjFactor" ng-model="manfDO.adjFactor" placeholder="Adjustment Factor" class="form-control" required="required">
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
			        <tfoot>
			           <tr>
			            	<th>Machine Type</th>
			                <th>Model</th>
			                <th>Engine Power</th>
			                <th>Group ID</th>
			            </tr>
			        </tfoot>
					
			        <tbody>
			            <tr ng-repeat="machine in machineInfoDOLst">
			            	<td>{{machine.machineType}}</td>
			                <td>{{machine.model}}</td>
			                <td>{{machine.ePower}}</td>
			                <td>{{machine.groupId}}</td>
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