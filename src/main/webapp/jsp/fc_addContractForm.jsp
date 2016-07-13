<form class="form-horizontal" role="form" ng-submit="submitClaim()">
	<div class="form-group">
		<label for="claimNo" class="col-sm-3 control-label">Claim #</label>
		<div class="col-sm-9">
			<input type="text" ng-model="claim.claimNo" id="claimNo" name="claimNo" class="form-control" required="required" ng-readonly=true>
		</div>
	</div>
	<div class="form-group">
		<label for="failureDate" class="col-sm-3 control-label">Failure Date</label>
		<div class="col-sm-9">
			<input type="text" ng-model="claim.failureDate" id="failureDate" name="failureDate" class="form-control" required="required">
		</div>
	</div>
	<div class="form-group">
		<label for="reportedDate" class="col-sm-3 control-label">Reported Date</label>
		<div class="col-sm-9">
			<input type="text" ng-model="claim.reportedDate" id="reportedDate" name="reportedDate" class="form-control" required="required">
		</div>
	</div>
	<div class="form-group">
		<label for="workOrderNo" class="col-sm-3 control-label">Work Order #</label>
		<div class="col-sm-9">
			<input type="text" ng-model="claim.workOrderNo" id="workOrderNo" name="workOrderNo" class="form-control" required="required">
		</div>
	</div>
	<div class="form-group">
		<label for="hoursBreakdown" class="col-sm-3 control-label">Hours @ Breakdown</label>
		<div class="col-sm-9">
			<input type="text" ng-model="claim.hoursBreakdown" id="hoursBreakdown" name="hoursBreakdown" class="form-control" required="required">
		</div>
	</div>
	<div class="form-group">
		<label for="laborHours" class="col-sm-3 control-label">Labor Hours</label>
		<div class="col-sm-9">
			<input type="text" ng-model="claim.laborHours" id="laborHours" name="laborHours" class="form-control" required="required">
		</div>
	</div>
	<div class="form-group">
		<label for="hourlyRate" class="col-sm-3 control-label">Hourly Rate</label>
		<div class="col-sm-9">
			<input type="text" ng-model="claim.hourlyRate" id="hourlyRate" name="hourlyRate" class="form-control" required="required">
		</div>
	</div>
	<div class="form-group">
		<label for="totalLaborCost" class="col-sm-3 control-label">Total Labor Cost</label>
		<div class="col-sm-9">
			<input type="text" ng-model="claim.totalLaborCost" id="totalLaborCost" name="totalLaborCost" class="form-control" ng-readonly=true>
		</div>
	</div>
	<div class="form-group">
		<label for="totalPartsCost" class="col-sm-3 control-label">Total Parts Cost</label>
		<div class="col-sm-9">
			<input type="text" ng-model="claim.totalPartsCost" id="totalPartsCost" name="totalPartsCost" class="form-control" required="required">
		</div>
	</div>
	<div class="form-group">
		<label for="totalOtherCharges1" class="col-sm-3 control-label">Total Other Charges (1)</label>
		<div class="col-sm-9">
			<input type="text" ng-model="claim.totalOtherCharges1" id="totalOtherCharges1" name="totalOtherCharges1" class="form-control" required="required">
		</div>
	</div>
	<div class="form-group">
		<label for="totalOtherCharges2" class="col-sm-3 control-label">Total Other Charges (2)</label>
		<div class="col-sm-9">
			<input type="text" ng-model="claim.totalOtherCharges2" id="totalOtherCharges2" name="totalOtherCharges2" class="form-control" required="required">
		</div>
	</div>
	<div class="form-group">
		<label for="totalClaimCost" class="col-sm-3 control-label">Total Claim Cost</label>
		<div class="col-sm-9">
			<input type="text" ng-model="claim.totalClaimCost" id="totalClaimCost" name="totalClaimCost" class="form-control" ng-readonly=true>
		</div>
	</div>
	<div class="form-group">
		<label for="customerComplaint" class="col-sm-3 control-label">Customer Complaint</label>
		<div class="col-sm-9">
			<input type="text" ng-model="claim.customerComplaint" id="customerComplaint" name="customerComplaint" class="form-control" required="required">
		</div>
	</div>
	<div class="form-group">
		<label for="causeofFailure" class="col-sm-3 control-label">Cause of Failure</label>
		<div class="col-sm-9">
			<input type="text" ng-model="claim.causeofFailure" id="causeofFailure" name="causeofFailure" class="form-control" required="required">
		</div>
	</div>
	<div class="form-group">
		<label for="correctiveAction" class="col-sm-3 control-label">Corrective Action</label>
		<div class="col-sm-9">
			<input type="text" ng-model="claim.correctiveAction" id="correctiveAction" name="correctiveAction" class="form-control" required="required">
		</div>
	</div>
	<div class="form-group">
		<label for="attachments" class="col-sm-3 control-label">Attachments</label>
		<div class="col-sm-9">
			<input type="text" ng-model="claim.attachments" id="attachments" name="attachments" class="form-control" required="required">
		</div>
	</div>
</form>