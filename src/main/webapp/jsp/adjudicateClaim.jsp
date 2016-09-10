<article class="col-md-9 maincontent">
	<header class="page-header">
		<div class="col-md-8 col-sm-12">
			<div class="sec-title">
				<h2 class="wow animated bounceInLeft">Pre-authorization Request Claims</h2>
			</div>
		</div>
	</header>
	<div  ng-controller="ClaimsAdjudicateController" ng-init="showAdjudicateClaimList=true">
		<div ng-if='showAdjudicateClaimList'>
			<table id="adjudicateClaimsListTable" class="table table-striped table-bordered" cellspacing="0" width="100%">
				<thead>
		            <tr>
		            	<th>Claim ID</th>
		                <th>Dealer ID</th>
		                <th>Serial</th>
		            </tr>
		        </thead>
				<tbody>
		            <tr ng-repeat="claim in adjudicateClaimList" >
		                <td>{{claim.claimId}}</td>
		                <td>{{claim.dealerId}}</td>
		                <td>{{claim.serial}}</td>
		            </tr>
		        </tbody>
			</table>
		</div>
	<!-- 	<div ng-if='!showAdjudicateClaimList'>
			<form name="preAuthForm" class="form-horizontal" role="form">
				<div class="form-group">
					<label for="claimNo" class="col-sm-4 control-label">Claim #</label>
					<label class="col-sm-8 control-label">
						{{adjudicateClaim.claimId}}
					</label>
				</div>
				<div class="form-group">
					<label for="failureDate" class="col-sm-4 control-label">Failure
						Date</label>
					<label class="col-sm-8 control-label">
						{{adjudicateClaim.failDate}}
					</label>
				</div>
				<div class="form-group">
					<label for="reportedDate" class="col-sm-4 control-label">Reported
						Date</label>
					<label class="col-sm-8 control-label">
						{{adjudicateClaim.reportDate | date:'yyyy-MM-dd'}}
					</label>
				</div>
				<div class="form-group">
					<label for="workOrderNo" class="col-sm-4 control-label">Work
						Order #</label>
					<label class="col-sm-8 control-label">
						{{adjudicateClaim.workOrder}}
					</label>
				</div>
				<div class="form-group">
					<label for="hoursBreakdown" class="col-sm-4 control-label">Hours @ Breakdown</label>
					<label class="col-sm-8 control-label">
						{{adjudicateClaim.hoursBreakDown}}
					</label>
				</div>
				<div class="form-group">
					<label for="laborHours" class="col-sm-4 control-label">Labor
						Hours</label>
					<label class="col-sm-8 control-label">
						{{adjudicateClaim.claimLaborDO.laborHrs}}
					</label>
				</div>
				<div class="form-group">
					<label for="hourlyRate" class="col-sm-4 control-label">Hourly
						Rate</label>
					<label class="col-sm-8 control-label">
						{{adjudicateClaim.claimLaborDO.rate}}
					</label>
				</div>
				<div class="form-group">
					<label for="totalLaborCost" class="col-sm-4 control-label">Total
						Labor Cost</label>
					<label class="col-sm-8 control-label">
						{{adjudicateClaim.claimLaborDO.laborHrs * adjudicateClaim.claimLaborDO.rate}}
					</label>
				</div>
				<div data-ng-repeat="claimPartVO in adjudicateClaim.claimPartDO">
					<span>
						<div class="form-group">
							<label for="partNo" class="col-sm-4 control-label">Part#</label>
							<label class="col-sm-8 control-label">
								{{claimPartVO.partNo}}
							</label>
						</div>
						<div class="form-group">
							<label for="partDescr" class="col-sm-4 control-label">Part Description</label>
							<label class="col-sm-8 control-label">
								{{claimPartVO.partDescr}}
							</label>
						</div>
						<div class="form-group">
							<label for="partQty" class="col-sm-4 control-label">Quantity</label>
							<label class="col-sm-8 control-label">
								{{claimPartVO.qty}}
							</label>
						</div>
						<div class="form-group">
							<label for="partUnitprice" class="col-sm-4 control-label">Unit Price</label>
							<label class="col-sm-8 control-label">
								{{claimPartVO.unitPrice}}
							</label>
						</div>
						<div class="form-group">
							<label for="partTotal" class="col-sm-4 control-label" >Parts Total</label>
							<label class="col-sm-8 control-label">
								{{claimPartVO.qty * claimPartVO.unitPrice}}
							</label>
						</div>
					</span>
				</div>
				<div class="form-group">
					<label for="totalPartsCost" class="col-sm-4 control-label">Total
						Parts Cost</label>
					<label class="col-sm-8 control-label">
						{{adjudicateClaim.totalPartCost}}
					</label>
				</div>
				<div class="form-group">
					<label for="totalOtherCharges1" class="col-sm-4 control-label">Total
						Other Charges (1)</label>
					<label class="col-sm-8 control-label">
						{{adjudicateClaim.requestedOtherCharges1}}
					</label>
				</div>
				<div class="form-group">
					<label for="totalOtherCharges2" class="col-sm-4 control-label">Total
						Other Charges (2)</label>
					<label class="col-sm-8 control-label">
						{{adjudicateClaim.requestedOtherCharges2}}
					</label>
				</div>
				<div class="form-group">
					<label for="totalClaimCost" class="col-sm-4 control-label">Total
						Claim Cost</label>
					<label class="col-sm-8 control-label">
						{{adjudicateClaim.totalClaimCost}}
					</label>
				</div>
				<div class="form-group">
					<label for="customerComplaint" class="col-sm-4 control-label">Customer
						Complaint</label>
					<label class="col-sm-8 control-label">
						{{adjudicateClaim.custComplaint}}
					</label>
				</div>
				<div class="form-group">
					<label for="causeofFailure" class="col-sm-4 control-label">Cause
						of Failure</label>
					<label class="col-sm-8 control-label">
						{{adjudicateClaim.causeFail}}
					</label>
				</div>
				<div class="form-group">
					<label for="correctiveAction" class="col-sm-4 control-label">Corrective
						Action</label>
					<label class="col-sm-8 control-label">
						{{adjudicateClaim.correctiveAction}}
					</label>
				</div>
				<div class="form-group">
					<label for="extComment" class="col-sm-3 control-label">Comments</label>
					<div class="col-sm-9">
						<textarea ng-model="adjudicateClaim.extComment" id="extComment"
							name="extComment" class="form-control" ng-required="extCommentFlag" ng-trim=true></textarea>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-8 col-sm-offset-3">
						<button type="button" class="btn btn-primary btn-block" ng-click="reqAuth('pre_authorized_approved')">Pre-authorization Approved</button>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-8 col-sm-offset-3">
						<button type="button" class="btn btn-primary btn-block" ng-click="reqAuth('pre_authorized_approved_with_adjustments')">Pre-auth Approved with Adjustments</button>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-8 col-sm-offset-3">
						<button type="submit" class="btn btn-primary btn-block" ng-click="reqAuth('pre_authorized_rejected')">Pre-authorization Rejected</button>
					</div>
				</div>
			
				</form>
				</div> -->
				</div>
			
			
</article>