<form class="form-horizontal" role="form" ng-submit="submitClaim()">
	<div class="form-group">
		<label for="claimNo" class="col-sm-3 control-label">Claim #</label>
		<div class="col-sm-9">
			<input type="text" ng-model="claim.claimNo" id="claimNo" name="claimNo" class="form-control" required="required" value="{{claim.claimNo}}" ng-readonly=true>
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
			<input type="text" ng-model="claim.totalLaborCost" id="totalLaborCost" name="totalLaborCost" class="form-control" required="required" value={{claim.laborHours}} ng-readonly=true>
		</div>
	</div>
</form>