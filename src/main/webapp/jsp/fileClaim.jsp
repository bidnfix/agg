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
		</article>
	<!-- /Article -->
	
	<!-- Article main content -->
		<article class="col-md-9 maincontent" ng-if='showContractDetails'>
			<header class="page-header">
              	<div class="col-md-6 col-sm-12">
					<div class="sec-title">
						<h2 class="wow animated bounceInLeft">New Claim</h2>
						<p class="wow animated bounceInRight">Contract #: {{contractInfoList.contractId}}</p>
					</div>
                </div>
                <div class="col-md-6 col-sm-12"><a class="btn btn-primary pull-right mar-right btn-sm" href="add-new.html">Add New</a><a class="btn btn-primary pull-right btn-sm mar-right" href="#">Back</a></div>
			</header>

			 <!-- data table section -->
             	<div class="inner-main">
             		<form name="newClaimForm" ng-submit="onClickSubmitClaim()">
                    <div class="col-xs-12 agf1 main-login pad10-top">
	                    <div class="col-xs-12">
	                      If the repair estimate is over $1,000 or if diagnostic cost is over $500, then the repair facility must contact AgGuard before proceeding with the repair-unless a higher amount for that has been authorized by AgGuard. Failure to pre-notify us could disqualify the Claim.
	                    </div>
                    	<div class="col-xs-12 no-pad clearfix">
	                      <div class="col-md-6 no-pad pad10-right">
	                       <span class="ag-tab-title col-xs-12 no-pad marg10-bottom">Claim Information</span>
	                       <br clear="all">
	                       <br>
	                        <div class="form-group pad10-top">
	                          <label for="_sno">Serial Number</label>
	                          <input type="text" id="_sno" name="_sno" class="form-control" ng-model="contractInfoList.machineSerialNo" ng-readonly=true>
	                        </div>
	                        <div class="form-group">
	                          <label>Manufacturer</label>
	                          <input type="text" class="form-control" ng-model="contractInfoList.manufacturerDO.name" ng-readonly=true>
	                        </div>
	                        <div class="form-group">
	                          <label>Model Number</label>
	                          <input type="text" class="form-control" ng-model="contractInfoList.machineModel" ng-readonly=true>
	                        </div>
	                        <div class="form-group">
	                          <label>Coverage Type</label>
	                          <select class="form-control">
	                            <option>Select</option>
	                          </select>
	                        </div>
	                        <div class="form-group">
	                          <label>Failure Date</label>
	                          <div class="input-group">
	                            <input type="date" class="form-control" aria-describedby="basic-addon2" ng-model="claim.failDate" value="{{claim.failDate | date:'yyyy-MM-dd'}}" ngMax="{{failureDateValid}}" max="{{failureDateValid | date:'yyyy-MM-dd'}}" required="required">
	                            <!-- <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span> -->
	                          </div>
	                        </div>
	                        <div class="form-group">
	                          <label>Reported On</label>
	                          <div class="input-group">
	                            <input type="date" class="form-control" aria-describedby="basic-addon2" ng-model="claim.reportDate" value="{{claim.reportDate | date:'yyyy-MM-dd'}}"
				ngMax="{{todayDate}}" max="{{todayDate | date:'yyyy-MM-dd'}}" required="required">
	                            <!-- <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span> -->
	                          </div>
	                        </div>
	                        <div class="form-group">
	                          <label for="_wo">Work Order Number</label>
	                          <input type="text" id="_wo" name="_wo" class="form-control" ng-model="claim.workOrder" ngRequired="required">
	                        </div>
	                        <div class="form-group">
	                          <label>Hours @ Breakdown</label>
	                          <input type="number" class="form-control" ng-model="claim.hoursBreakDown" required="required">
	                        </div>	                        
	                        <div class="form-group">
	                          <label>Labor Total Hours</label>
	                          <input type="number" class="form-control" ng-model="claim.laborHrs" required="required">
	                        </div>
	                        <div class="form-group">
	                          <label>Hourly Rate ($)</label>
	                          <input type="number" class="form-control" ng-model="claim.laborHourlyRate" required="required">
	                        </div>
	                        <div class="form-group">
	                          <label>Labor Cost($)</label>
	                          <input type="number" class="form-control" ng-model="claim.totalLaborCost" ng-readonly=true>
	                        </div>
	                        <div data-ng-repeat="claimPartVO in claim.claimPartVOList">
	                        	<div class="form-group">
		                          <label>Part #</label>
		                          <input type="text" class="form-control" ng-model="claimPartVO.partNo" required="required">
		                        </div>
		                        <div class="form-group">
		                          <label>Part Description</label>
		                          <input type="text" class="form-control" ng-model="claimPartVO.partDescr" required="required">
		                        </div>
		                        <div class="form-group">
		                          <label>Quantity</label>
		                          <input type="number" class="form-control" ng-model="claimPartVO.qty" required="required" ng-change="calcTotalPartLine($index)">
		                        </div>
		                        <div class="form-group">
		                          <label>Unit Price ($)</label>
		                          <input type="number" class="form-control" ng-model="claimPartVO.unitPrice" required="required" ng-change="calcTotalPartLine($index)">
		                        </div>
		                        <div class="form-group">
		                          <label>Part Total ($)</label>
		                          <input type="number" class="form-control" ng-model="claimPartVO.partsTotal" ng-readonly=true>
		                        </div>
	                        </div>
	                        <div class="form-group">
	                         <button class="btn btn-primary btn-block" ng-click="claim.claimPartVOList.push({})">Add Part Line</button>
	                        </div>
	                        <div class="form-group">
	                          <label>Parts Total ($)</label>
	                          <input type="number" class="form-control" ng-model="claim.partsTotalCost" ng-readonly=true>
	                        </div>
	                        <div class="form-group">
	                          <label>Other Charge 1 ($)</label>
	                          <input type="number" class="form-control" ng-model="claim.requestedOtherCharges1" required="required">
	                        </div>
	                        <div class="form-group">
	                          <label>Other Charge 2 ($)</label>
	                          <input type="number" class="form-control" ng-model="claim.requestedOtherCharges2" required="required">
	                        </div>
	                        <div class="form-group">
	                          <label>Total Claim ($)</label>
	                          <input type="number" class="form-control" ng-model="claim.totalClaimCost" ng-readonly=true>
	                        </div>
	                      </div>
	
	
	
	                      <div class="col-md-6 no-pad pad10-left border-left">
	                      <span class="ag-tab-title col-xs-12 no-pad marg10-bottom" style="margin-left:-10px !important;">Details</span>
	                       <br clear="all">
	                       <br>
	                        <br clear="all">
	                      
	                      <div class="col-xs-12 no-pad">
	                        <div class="form-group">
	                          <label>Customer Complaint</label>
	                          <textarea class="form-control" rows="2" ng-model="claim.custComplaint" required="required"></textarea>
	                        </div>
	                        <div class="form-group">
	                          <label>Cause of Failure</label>
	                          <textarea class="form-control" rows="5" ng-model="claim.causeFail" required="required"></textarea>
	                        </div>
	                        <div class="form-group">
	                          <label>Corrective Action</label>
	                          <textarea class="form-control" rows="2" ng-model="claim.correctiveAction" required="required"></textarea>
	                        </div>
	                      </div>
	                    </div>
	                    </div>
	                    <div class="col-xs-12 no-pad pad10-top">
	                       <span class="ag-tab-title col-xs-12 no-pad marg10-bottom">File Attachments</span>
	                       <br clear="all">
	                       <p>Please upload pdf versions of quotes and any pictures showing the damage.</p>
	                       <br>
	                        <div class="col-sm-12">
		                        <div class="col-sm-6">
		                          <div class="form-group">
		                          <label>File</label>
		                          	<input type="file" id="" name="files" multiple onchange="angular.element(this).scope().addAttachment(this)">
		                          </div>
		                        </div>
	                        </div>
	                        <div class="col-sm-12">
	                        	<ul>
							        <li ng-repeat="file in attachments">{{file.name}}</li>
							    </ul>
	                        </div>
	                        
	                        <!-- <div class="col-sm-12 marg10-bottom">
	                        	<button class="btn btn-primary btn-sm" valur="more"></button>
	                        </div> -->
	                    </div>
	
	              	</div>
					<div class="col-sm-12 no-pad t-c marg10-top marg10-bottom">
			          <button type="submit" class="btn btn-primary" ng-click="saveAsDraft()"> Save Claim</button>
			          <button type="submit" class="btn btn-primary" ng-click="reqAuth()"> Request Preauthorization</button>
			          <button type="submit" class="btn btn-primary" ng-disabled="isSubmitDisabled" ng-click="reqSubmit()"> Submit Claim</button>
		        	</div>
		        	</form>
				</div>
		
		<!-- end data table section -->
	              
		</article>
	<!-- /Article -->
</div>