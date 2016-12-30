	<div class="col-xs-12 agf1 main-login pad10-top">
		<div class="col-xs-12">If the repair estimate is over $1,000 or
			if diagnostic cost is over $500, then the repair facility must
			contact AgGuard before proceeding with the repair-unless a higher
			amount for that has been authorized by AgGuard. Failure to pre-notify
			us could disqualify the Claim.</div>
		<div class="col-xs-12 no-pad clearfix">
			<div class="col-md-6 no-pad pad10-right">
				<span class="ag-tab-title col-xs-12 no-pad marg10-bottom">Contract
					Information</span> <br clear="all"> <br>
				<div class="form-group pad10-top">
					<label>Contract ID</label>
					<p>{{contractInfoList.contractID}}</p>
				</div>
				<div class="form-group">
					<label>Manufacturer</label>
					<p>{{contractInfoList.manfactureName}}</p>
				</div>
				<div class="form-group">
					<label>Contract Expiration Date</label>
					<p>{{contractInfoList.expirationDate |  date:"MM/dd/yyyy"}}</p>
				</div>
				<div class="form-group">
					<label>Limit of Liability (LOL)</label>
					<p>{{contractInfoList.lol | currency:"$":0}}</p>
				</div>
				<div class="form-group">
					<label>Deductible</label>
					<p>{{contractInfoList.deductible | currency:"$":0}}</p>
				</div>
			</div>

			<div class="col-md-6 no-pad">
				<span
					class="ag-tab-title col-xs-12 no-pad marg10-bottom  pad20-left"
					style="margin-left: -10px !important;">&nbsp;</span>
				<div class="col-xs-12 no-pad pad10-left">
					<br clear="all">

					<div class="col-xs-12 no-pad">
						<div class="form-group">
							<label>Serial/VIN #</label>
							<p>{{contractInfoList.machineSerialNo}}</p>
						</div>
						<div class="form-group">
							<label>Model</label>
							<p>{{contractInfoList.machineModel}}</p>
						</div>
						<div class="form-group">
							<label>Usage Hours covered</label>
							<p>{{contractInfoList.usageHoursCovered}}</p>
						</div>
						<div class="form-group">
							<label>Available LOL</label>
							<p>{{contractInfoList.availableLol | currency:"$":0}}</p>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="col-xs-12 no-pad clearfix">
			<div class="col-md-6 no-pad pad10-right">
				<span class="ag-tab-title col-xs-12 no-pad marg10-bottom">Claim
					Information</span> <br clear="all"> <br>
				<div class="form-group pad10-top">
					<label>Serial Number</label> 
					{{contractInfoList.machineSerialNo}}
				</div>
				<div class="form-group">
					<label>Manufacturer</label> 
					{{contractInfoList.manfactureName}}
				</div>
				<div class="form-group">
					<label>Model Number</label> 
					{{contractInfoList.machineModel}}
				</div>
				<div class="form-group">
					<label>Coverage Type</label> 
					{{contractInfoList.coverageType}}
				</div>
				<div class="form-group">
					<label>Failure Date</label>
					{{claim.failDate | date:'yyyy-MM-dd'}}
				</div>
				<div class="form-group">
					<label>Reported On</label>
					{{claim.reportDate | date:'yyyy-MM-dd'}}					
				</div>
				<div class="form-group">
					<label>Work Order Number</label> 
					{{claim.workOrder}}
				</div>
				<div class="form-group">
					<label>Hours @ Breakdown</label>
					{{claim.hoursBreakDown}}
				</div>
				<div class="form-group">
					<label>Other Charge 1 ($)</label> 
					{{claim.requestedOtherCharges1}}
				</div>
				<div class="form-group">
					<label>Other Charge 2 ($)</label> 
					{{claim.requestedOtherCharges2}}
				</div>
				<div class="form-group">
					<label>Total Claim ($)</label>
					{{claim.totalClaimCost}}
				</div>
			</div>



			<div class="col-md-6 no-pad">
				<span
					class="ag-tab-title col-xs-12 no-pad marg10-bottom  pad20-left"
					style="margin-left: -10px !important;">Details</span>
				<div class="col-xs-12 no-pad pad10-left border-left">
					<br clear="all">

					<div class="col-xs-12 no-pad">
						<div class="form-group">
							<label>Customer Complaint</label>
							{{claim.custComplaint}}
						</div>
						<div class="form-group">
							<label>Cause of Failure</label>
							{{claim.causeFail}}
						</div>
						<div class="form-group">
							<label>Corrective Action</label>
							{{claim.correctiveAction}}
						</div>
					</div>
				</div>
			</div>
		</div>


		<div class="col-xs-12 no-pad pad10-top">
			<span class="ag-tab-title col-xs-12 no-pad marg10-bottom">Claim
				Details</span> <br clear="all"> <br>
			<div class="col-sm-12">
				<div class="col-xs-12 no-pad table-responsive clearfix">
					<table class="table table-bordered">
						<thead>
							<tr>
								<th class="col-sm-2">Part #</th>
								<th class="col-sm-4">Description</th>
								<th class="col-sm-1">Quantity</th>
								<th class="col-sm-2">Unit Price</th>
								<th class="t-r col-sm-2">Part Total</th>
							</tr>
						</thead>
						<tbody data-ng-repeat="claimPartVO in claim.claimPartVOList">
							<tr>
								<td>{{claimPartVO.partNo}}</td>
								<td>{{claimPartVO.partDescr}}</td>
								<td>{{claimPartVO.qty}}</td>
								<td>{{claimPartVO.unitPrice}}</td>
								<td class="t-r">{{claimPartVO.partsTotal | currency}}</td>
							</tr>
						</tbody>
					</table>
					<div class="col-sm-12">
						<div class="col-sm-6">
						</div>
						<div class="col-sm-6">
							<div class="col-sm-8 no-pad">Total Requested Parts Cost</div>
							<div class="col-sm-4 t-r">
								<!-- <input type="text" class="form-control" name="" ng-model="claim.partsTotalCost" ng-readonly=true> -->
								{{claim.partsTotalCost | currency}}
							</div>
						</div>

					</div>
				</div>
				<br clear="all">
				<div class="col-xs-12 no-pad table-responsive clearfix">
					<table class="table table-bordered">
						<thead>
							<tr>
								<th class="col-sm-2">Labor #</th>
								<th class="col-sm-4">Description</th>
								<th class="col-sm-1">Hours</th>
								<th class="col-sm-2">Hourly Rate</th>
								<th class="t-r col-sm-2">Total</th>
							</tr>
						</thead>
						<tbody data-ng-repeat="claimLabourVO in claim.claimLabourVOList">
							<tr>
								<td>{{claimLabourVO.laborNo}}</td>
								<td>{{claimLabourVO.laborDescr}}</td>
								<td>{{claimLabourVO.laborHrs}}</td>
								<td>{{claimLabourVO.laborHourlyRate}}</td>
								<td class="t-r">{{claimLabourVO.labourTotal | currency}}</td>
							</tr>
						</tbody>
					</table>
					<div class="col-sm-12">
						<div class="col-sm-6">
						</div>
						<div class="col-sm-6">
							<div class="col-sm-8 no-pad">Total Requested Labor Cost</div>
							<div class="col-sm-4 t-r">
								{{claim.totalLaborCost | currency}}
							</div>
						</div>

					</div>
				</div>
				<br clear="all">
			</div>
		</div>
	</div>
