	<div ng-controller="ClaimsAdjudicateController" ng-init="showAdjudicateClaimList=true">
		<!-- Article main content -->
		<article class="col-md-9 maincontent" ng-if='showAdjudicateClaimList'>
			<header class="page-header">
	              	<div class="col-md-6 col-sm-12">
						<div class="sec-title">
							<h3 class="wow animated bounceInLeft">Adjudicate a Claim</h3>
						</div>
                    </div>
			</header>
			<div>
				<table id="preauthClaimsListTable" class="table table-striped table-bordered" cellspacing="0" width="100%">
					<thead>
			            <tr>
			            	<th>Claim ID</th>
			                <th>Dealer ID</th>
			                <th>Serial</th>
			            </tr>
			        </thead>
					<tbody>
			            <tr ng-repeat="claim in adjudicateClaimList" ng-click="onClickSelectClaim(claim)">
			                <td>{{claim.claimId}}</td>
			                <td>{{claim.dealerId}}</td>
			                <td>{{claim.serial}}</td>
			            </tr>
			        </tbody>
				</table>
			</div>
		</article>
	<!-- /Article -->
	
	<!-- Article main content -->
		<article class="col-md-9 maincontent" ng-if='!showAdjudicateClaimList'>
			<header class="page-header">
              	<div class="col-md-6 col-sm-12">
					<div class="sec-title">
						<h2 class="wow animated bounceInLeft">Adjudicate a Claim</h2>
						<p class="wow animated bounceInRight">Contract #: {{adjudicateClaim.contractId}}</p>
					</div>
                </div>
                <div class="col-md-6 col-sm-12"><a class="btn btn-primary pull-right btn-sm mar-right" ng-click="onClickBackToList()">Back</a></div>
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
	                          <input type="text" id="_sno" name="_sno" class="form-control" ng-model="adjudicateClaim.serial" ng-readonly=true>
	                        </div>
	                        <!-- <div class="form-group">
	                          <label>Manufacturer</label>
	                          <input type="text" class="form-control" ng-model="preAuthClaim.manufacturerDO.name" ng-readonly=true>
	                        </div>
	                        <div class="form-group">
	                          <label>Model Number</label>
	                          <input type="text" class="form-control" ng-model="preAuthClaim.machineModel" ng-readonly=true>
	                        </div> -->
	                        <div class="form-group">
	                          <label>Coverage Type</label>
	                          <select class="form-control">
	                            <option>Select</option>
	                          </select>
	                        </div>
	                        <div class="form-group">
	                          <label>Failure Date</label>
	                          <div class="input-group">
	                            <input type="date" class="form-control" aria-describedby="basic-addon2" value="{{adjudicateClaim.failDate | date:'yyyy-MM-dd'}}" ng-readonly=true>
	                            <!-- <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span> -->
	                          </div>
	                        </div>
	                        <div class="form-group">
	                          <label>Reported On</label>
	                          <div class="input-group">
	                            <input type="date" class="form-control" aria-describedby="basic-addon2" value="{{adjudicateClaim.reportDate | date:'yyyy-MM-dd'}}" ng-readonly=true>
	                            <!-- <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span> -->
	                          </div>
	                        </div>
	                        <div class="form-group">
	                          <label for="_wo">Work Order Number</label>
	                          <input type="text" id="_wo" name="_wo" class="form-control" ng-model="adjudicateClaim.workOrder" ng-readonly=true>
	                        </div>
	                        <div class="form-group">
	                          <label>Hours @ Breakdown</label>
	                          <input type="number" class="form-control" ng-model="adjudicateClaim.hoursBreakDown" ng-readonly=true>
	                        </div>	                        
	                        <div class="form-group">
	                          <label>Labor Total Hours</label>
	                          <input type="number" class="form-control" ng-model="adjudicateClaim.claimLaborDO.laborHrs" ng-readonly=true>
	                        </div>
	                        <div class="form-group">
	                          <label>Hourly Rate ($)</label>
	                          <input type="number" class="form-control" ng-model="adjudicateClaim.claimLaborDO.rate" ng-readonly=true>
	                        </div>
	                        <div class="form-group">
	                          <label>Labor Cost($)</label>
	                          <input type="number" class="form-control" ng-model="adjudicateClaim.totalLaborCost" ng-readonly=true>
	                        </div>
	                        <div data-ng-repeat="claimPartVO in adjudicateClaim.claimPartDO">
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
	                          <label>Parts Total ($)</label>
	                          <input type="number" class="form-control" ng-model="adjudicateClaim.totalPartCost" ng-readonly=true>
	                        </div>
	                        <div class="form-group">
	                          <label>Other Charge 1 ($)</label>
	                          <input type="number" class="form-control" ng-model="adjudicateClaim.requestedOtherCharges1" ng-readonly=true>
	                        </div>
	                        <div class="form-group">
	                          <label>Other Charge 2 ($)</label>
	                          <input type="number" class="form-control" ng-model="adjudicateClaim.requestedOtherCharges2" ng-readonly=true>
	                        </div>
	                        <div class="form-group">
	                          <label>Total Claim ($)</label>
	                          <input type="number" class="form-control" ng-model="adjudicateClaim.totalClaimCost" ng-readonly=true>
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
	                          <textarea class="form-control" rows="2" ng-model="adjudicateClaim.custComplaint" ng-readonly=true></textarea>
	                        </div>
	                        <div class="form-group">
	                          <label>Cause of Failure</label>
	                          <textarea class="form-control" rows="5" ng-model="adjudicateClaim.causeFail" ng-readonly=true></textarea>
	                        </div>
	                        <div class="form-group">
	                          <label>Corrective Action</label>
	                          <textarea class="form-control" rows="2" ng-model="adjudicateClaim.correctiveAction" ng-readonly=true></textarea>
	                        </div>
	                        <div class="form-group">
	                          <label>Comments</label>
	                          <textarea class="form-control" rows="2" ng-model="adjudicateClaim.extComment" ng-required="extCommentFlag" ng-trim=true></textarea>
	                        </div>
	                        <div class="form-group">
	                          <label>Adjusted Labor Total Hours</label>
	                          <input type="number" class="form-control" ng-model="adjudicateClaim.claimLaborDO.adjustedLaborHrs">
	                        </div>
	                        <div class="form-group">
	                          <label>Adjusted Hourly Rate ($)</label>
	                          <input type="number" class="form-control" ng-model="adjudicateClaim.claimLaborDO.adjustedRate">
	                        </div>
	                        <div class="form-group">
	                          <label>Total Adj Labor Cost($)</label>
	                          <input type="number" class="form-control" ng-model="adjudicateClaim.adjustedTotalLaborCost" ng-readonly=true>
	                        </div>
	                        <div class="form-group">
	                          <label>Total Adj Parts Cost($)</label>
	                          <input type="number" class="form-control" ng-model="adjudicateClaim.totalAdjustedPartsCost">
	                        </div>
	                        <div class="form-group">
	                          <label>Adjusted Other(1) Cost ($)</label>
	                          <input type="number" class="form-control" ng-model="adjudicateClaim.adjustedOther1Cost">
	                        </div>
	                        <div class="form-group">
	                          <label>Adjusted Other(2) Cost ($)</label>
	                          <input type="number" class="form-control" ng-model="adjudicateClaim.adjustedOther2Cost">
	                        </div>
	                        <div class="form-group">
	                          <label>Total Adj Other Costs ($)</label>
	                          <input type="number" class="form-control" ng-model="adjudicateClaim.totalAdjustedOthersCost" ng-readonly=true>
	                        </div>
	                        <div class="form-group">
	                          <label>Total Adj Claim Costs ($)</label>
	                          <input type="number" class="form-control" ng-model="adjudicateClaim.totalAdjustedClaimsCost" ng-readonly=true>
	                        </div>
	                        <div class="form-group">
	                          <label>Total Re-imbursed Amount ($)</label>
	                          <input type="number" class="form-control" ng-model="adjudicateClaim.totalReimbursedCost" ng-readonly=true>
	                        </div>
	                      </div>
	                    </div>
	                    </div>
	              	</div>
					<div class="col-sm-12 no-pad t-c marg10-top marg10-bottom">
			          <button type="button" class="btn btn-primary" ng-click="reqAuth('pre_authorized_approved')">Pre-authorization Approved</button>
			          <button type="submit" class="btn btn-primary" ng-click="reqAuth('pre_authorized_approved_with_adjustments')">Pre-auth Approved with Adjustments</button>
			          <button type="submit" class="btn btn-primary" ng-click="reqAuth('pre_authorized_rejected')">Pre-authorization Rejected</button>
		        	</div>
		        	</form>
				</div>
		
		<!-- end data table section -->
	              
		</article>
	<!-- /Article -->
</div>