<%@include file="machinePopup.jsp" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
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
				<div class="col-xs-12 agf1 main-login pad10-top">
				<form class="form-horizontal" role="form" ng-submit="submitEditManufacturer()">
					<div class="col-md-10 no-pad pad10-right">
						
							<label for="machineType" class="col-sm-4 control-label">ID</label>
							<div class="col-sm-8">
								<input type="text" id="id" name="equipName" ng-model="manfDO.id" class="form-control" readonly="readonly" >
							</div>
						
							<label for="machineType" class="col-sm-4 control-label">Manufacturer</label>
							<div class="col-sm-8">
								<input type="text" id="manufacturer" name="manufacturerName" ng-model="manfDO.name" placeholder="Manufacturer" class="form-control" required="required" >
							</div>
						
							<label for="machineType" class="col-sm-4 control-label">Adjustment Factor</label>
							<div class="col-sm-8">
								<input type="text" id="adjFactor" name="adjFactor" ng-model="manfDO.adjFactor" placeholder="Adjustment Factor" class="form-control" required="required">
							</div>
							
							
								<div class="col-sm-6 col-sm-offset-3">
									<button type="submit" class="btn btn-primary btn-block">Update Manufacturer</button>
								</div>
								<div class="col-sm-3">
									<button type="button" class="btn btn-primary btn-block" ng-click="getManfInfo()">Cancel</button>
								</div>
							
							
						
						
						
				
					</div>
				</form>
				</div>
				
				<div class="col-xs-12 agf1 main-login pad10-top">
				<table id="editManfTable" class="table table-striped table-bordered" cellspacing="0" width="100%">
					<thead>
			            <tr>
			            	<th>Machine Type</th>
			                <th>Model</th>
			                <th>Adjustment Factor</th>
			                <th>Group ID</th>
			                <th></th>
			            </tr>
			        </thead>
			        <tfoot>
			           <tr>
			            	<th>Machine Type</th>
			                <th>Model</th>
			                <th>Adjustment Factor</th>
			                <th>Group ID</th>
			                <th></th>
			            </tr>
			        </tfoot>
			        
			        <tbody>
			            <tr ng-repeat="machine in manfDO.machineInfoDO">
			            	<td>{{machine.machineType}}</td>
			                <td>{{machine.model}}</td>
			                <td>{{machine.adjFactor | number : 2}}</td>
			                <td>{{machine.groupId}}</td>
			                <td><div class="manage-sec">
                					<a ng-click="editMachine(machine.machineId)"><img src="../assets/images/edit-pencil.png" alt="Edit" title="Edit"/></a>
                				</div>
                			</td>
			                </tr>
			        </tbody>
					
			        
				</table>
			</div>
				
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