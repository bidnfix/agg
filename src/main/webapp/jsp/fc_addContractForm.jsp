<form class="form-horizontal" role="form" ng-submit="onClickSubmitClaim()">
	<div class="form-group">
		<label for="claimNo" class="col-sm-3 control-label">Claim #</label>
		<div class="col-sm-9">
			<input type="text" ng-model="claim.claimId" id="claimNo"
				name="claimNo" class="form-control" required="required"
				ng-readonly=true>
		</div>
	</div>
	<div class="form-group">
		<label for="failureDate" class="col-sm-3 control-label">Failure
			Date</label>
		<div class="col-sm-9">
			<input type="date" ng-model="claim.failDate" id="failureDate"
				name="failureDate" value="{{claim.failDate | date:'yyyy-MM-dd'}}" 
				ngMax="{{failureDateValid}}" max="{{failureDateValid | date:'yyyy-MM-dd'}}"
				class="form-control" required="required">
		</div>
	</div>
	<div class="form-group">
		<label for="reportedDate" class="col-sm-3 control-label">Reported
			Date</label>
		<div class="col-sm-9">
			<input type="date" ng-model="claim.reportDate" id="reportedDate"
				name="reportedDate" value="{{claim.reportDate | date:'yyyy-MM-dd'}}"
				ngMax="{{todayDate}}" max="{{todayDate | date:'yyyy-MM-dd'}}"
					class="form-control" required="required"> 
		</div>
	</div>
	<div class="form-group">
		<label for="workOrderNo" class="col-sm-3 control-label">Work
			Order #</label>
		<div class="col-sm-9">
			<input type="text" ng-model="claim.workOrder" id="workOrderNo"
				name="workOrderNo" class="form-control" required="required">
		</div>
	</div>
	<div class="form-group">
		<label for="hoursBreakdown" class="col-sm-3 control-label">Hours
			@ Breakdown</label>
		<div class="col-sm-9">
			<input type="text" ng-model="claim.hoursBreakDown"
				id="hoursBreakdown" name="hoursBreakdown" class="form-control"
				required="required">
		</div>
	</div>
	<div class="form-group">
		<label for="laborHours" class="col-sm-3 control-label">Labor
			Hours</label>
		<div class="col-sm-9">
			<input type="text" ng-model="claim.labourHours" id="laborHours"
				name="laborHours" class="form-control" required="required">
		</div>
	</div>
	<div class="form-group">
		<label for="hourlyRate" class="col-sm-3 control-label">Hourly
			Rate</label>
		<div class="col-sm-9">
			<input type="text" ng-model="claim.hourlyRate" id="hourlyRate"
				name="hourlyRate" class="form-control" required="required">
		</div>
	</div>
	<div class="form-group">
		<label for="totalLaborCost" class="col-sm-3 control-label">Total
			Labor Cost</label>
		<div class="col-sm-9">
			<input type="text" ng-model="claim.totalLaborCost"
				id="totalLaborCost" name="totalLaborCost" class="form-control"
				ng-readonly=true>
		</div>
	</div>
	<div class="form-group">
		<label for="totalPartsCost" class="col-sm-3 control-label">Total
			Parts Cost</label>
		<div class="col-sm-9">
			<input type="text" ng-model="claim.partsTotal"
				id="totalPartsCost" name="totalPartsCost" class="form-control"
				required="required">
		</div>
	</div>
	<div class="form-group">
		<label for="totalOtherCharges1" class="col-sm-3 control-label">Total
			Other Charges (1)</label>
		<div class="col-sm-9">
			<input type="text" ng-model="claim.otherCharges1"
				id="totalOtherCharges1" name="totalOtherCharges1"
				class="form-control" required="required">
		</div>
	</div>
	<div class="form-group">
		<label for="totalOtherCharges2" class="col-sm-3 control-label">Total
			Other Charges (2)</label>
		<div class="col-sm-9">
			<input type="text" ng-model="claim.otherCharges2"
				id="totalOtherCharges2" name="totalOtherCharges2"
				class="form-control" required="required">
		</div>
	</div>
	<div class="form-group">
		<label for="totalClaimCost" class="col-sm-3 control-label">Total
			Claim Cost</label>
		<div class="col-sm-9">
			<input type="text" ng-model="claim.totalClaimCost"
				id="totalClaimCost" name="totalClaimCost" class="form-control"
				ng-readonly=true>
		</div>
	</div>
	<div class="form-group">
		<label for="customerComplaint" class="col-sm-3 control-label">Customer
			Complaint</label>
		<div class="col-sm-9">
			<input type="text" ng-model="claim.custComplaint"
				id="customerComplaint" name="customerComplaint" class="form-control"
				required="required">
		</div>
	</div>
	<div class="form-group">
		<label for="causeofFailure" class="col-sm-3 control-label">Cause
			of Failure</label>
		<div class="col-sm-9">
			<input type="text" ng-model="claim.causeFail"
				id="causeofFailure" name="causeofFailure" class="form-control"
				required="required">
		</div>
	</div>
	<div class="form-group">
		<label for="correctiveAction" class="col-sm-3 control-label">Corrective
			Action</label>
		<div class="col-sm-9">
			<input type="text" ng-model="claim.correctiveAction"
				id="correctiveAction" name="correctiveAction" class="form-control"
				required="required">
		</div>
	</div>
	<div class="form-group">
		<label for="attachments" class="col-sm-3 control-label">Attachments</label>
		<div class="col-sm-9">
			<input type="text" ng-model="claim.attachments" id="attachments"
				name="attachments" class="form-control" required="required">
		</div>
	</div>

	<div class="form-group">
		<div class="col-sm-9 col-sm-offset-3">
			<button type="button" class="btn btn-primary btn-block" onclick="saveAsDraft('model')">Save
				as Draft</button>
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-9 col-sm-offset-3">
			<button type="button" class="btn btn-primary btn-block" onclick="reqAuth('machineEditPopup')">Request
				for Authorization</button>
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-9 col-sm-offset-3">
			<button type="submit" class="btn btn-primary btn-block" ng-disabled="isSubmitDisabled">Submit Claim</button>
		</div>
	</div>

	<!-- </div>
	</div> -->

</form>