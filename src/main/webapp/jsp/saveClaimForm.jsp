<form name="newClaimForm" ng-submit="onClickSubmitClaim()">
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
					<label>Serial Number</label> <input type="text"
						class="form-control" ng-model="contractInfoList.machineSerialNo"
						ng-readonly=true>
				</div>
				<div class="form-group">
					<label>Manufacturer</label> <input type="text" class="form-control"
						ng-model="contractInfoList.manfactureName" ng-readonly=true>
				</div>
				<div class="form-group">
					<label>Model Number</label> <input type="text" class="form-control"
						ng-model="contractInfoList.machineModel" ng-readonly=true>
				</div>
				<div class="form-group">
					<label>Coverage Type</label> <input type="text"
						class="form-control" ng-model="contractInfoList.coverageType"
						ng-readonly=true>
				</div>
				<div class="form-group">
					<label>Failure Date</label>
					<div class="input-group">
						<!-- <input type="date" class="form-control"
							aria-describedby="basic-addon2" ng-model="claim.failDate"
							value="{{claim.failDate | date:'yyyy-MM-dd'}}"
							ngMax="{{failureDateValid}}"
							max="{{failureDateValid | date:'yyyy-MM-dd'}}"
							required="required"> -->
						<!-- <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span> -->
						<input type="text" class="form-control" 
			                   datepicker-popup="MM/dd/yyyy"
			                   datepicker-options="dateOptions" 
			                   is-open="failureDatePickerIsOpen" 
			                   ng-click="failureDatePickerOpen()"
			                   ng-model="claim.failDate" 
			                   max-date="failureDateValid"
			                   required="required"
			                   value="{{claim.failDate | date:'yyyy-MM-dd'}}" ng-readonly="commentUpdateBtnFlag"/>
						<span class="input-group-btn">
			              <button type="button" class="btn btn-default" 
			                      ng-click="failureDatePickerOpen($event)" ng-disabled="commentUpdateBtnFlag">
			                <i class="glyphicon glyphicon-calendar"></i>
			              </button>
			            </span>
					</div>
				</div>
				<div class="form-group">
					<label>Reported On</label>
					<div class="input-group">
						<!-- <input type="date" class="form-control"
							aria-describedby="basic-addon2" ng-model="claim.reportDate"
							value="{{claim.reportDate | date:'yyyy-MM-dd'}}"
							ngMax="{{todayDate}}" max="{{todayDate | date:'yyyy-MM-dd'}}"
							required="required"> -->
						<!-- <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span> -->
						<input type="text" class="form-control" 
			                   datepicker-popup="MM/dd/yyyy"
			                   datepicker-options="dateOptions" 
			                   is-open="reportDatePickerIsOpen" 
			                   ng-click="reportDatePickerOpen()"
			                   ng-model="claim.reportDate" 
			                   max-date="todayDate"
			                   required="required"
			                   value="{{claim.reportDate | date:'yyyy-MM-dd'}}" ng-readonly="commentUpdateBtnFlag"/>
						<span class="input-group-btn">
			              <button type="button" class="btn btn-default" 
			                      ng-click="reportDatePickerOpen($event)" ng-disabled="commentUpdateBtnFlag">
			                <i class="glyphicon glyphicon-calendar"></i>
			              </button>
			            </span>
					</div>
				</div>
				<div class="form-group">
					<label>Work Order Number</label> <input type="text"
						class="form-control" ng-model="claim.workOrder"
						ngRequired="required" ng-readonly="commentUpdateBtnFlag">
				</div>
				<div class="form-group">
					<label>Hours @ Breakdown</label> <input type="number"
						class="form-control" ng-model="claim.hoursBreakDown"
						required="required" ng-readonly="commentUpdateBtnFlag">
				</div>
				<div class="form-group">
					<label>Other Charge 1 ($)</label> <input type="number"
						class="form-control" ng-model="claim.requestedOtherCharges1"
						required="required" ng-readonly="commentUpdateBtnFlag">
				</div>
				<div class="form-group">
					<label>Other Charge 2 ($)</label> <input type="number"
						class="form-control" ng-model="claim.requestedOtherCharges2"
						required="required" ng-readonly="commentUpdateBtnFlag">
				</div>
				<div class="form-group">
					<label>Total Claim ($)</label> <input type="number"
						class="form-control" ng-model="claim.totalClaimCost"
						ng-readonly=true>
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
							<textarea class="form-control" rows="2"
								ng-model="claim.custComplaint" required="required" ng-readonly="commentUpdateBtnFlag"></textarea>
						</div>
						<div class="form-group">
							<label>Cause of Failure</label>
							<textarea class="form-control" rows="5"
								ng-model="claim.causeFail" required="required" ng-readonly="commentUpdateBtnFlag"></textarea>
						</div>
						<div class="form-group">
							<label>Corrective Action</label>
							<textarea class="form-control" rows="2"
								ng-model="claim.correctiveAction" required="required" ng-readonly="commentUpdateBtnFlag"></textarea>
						</div>
					</div>
				</div>
			</div>
		</div>


		<div class="col-xs-12 no-pad pad10-top">
			<span class="ag-tab-title col-xs-12 no-pad marg10-bottom">File
				Attachments</span> <br clear="all">
			<p>Please upload pdf versions of quotes and any pictures showing
				the damage.</p>
			<br>
			<div class="col-sm-12">
				<div class="col-sm-6">
					<div class="form-group">
						<label>File</label> <input type="file" id="" name="files" multiple
							ng-files="getTheFiles($files)" required="required" ng-readonly="commentUpdateBtnFlag">
					</div>
				</div>
			</div>
			<!--    <div class="col-sm-12">
                       <div class="col-sm-6">
                         <div class="form-group">
                         <label>File</label>
                         <input type="file" id="">
                         </div>
                       </div>
                       </div>
                       <div class="col-sm-12">
                       <div class="col-sm-6">
                         <div class="form-group">
                         <label>File</label>
                         <input type="file" id="">
                         </div>
                       </div>
                       </div>
                       <div class="col-sm-12 marg10-bottom">
                       <a class="btn btn-primary btn-sm" href="#"> more</a>
                       </div> -->
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
								<td><input type="text" class="form-control" name=""
									ng-model="claimPartVO.partNo" required="required" ng-readonly="commentUpdateBtnFlag"></td>
								<td><input type="text" class="form-control" name=""
									ng-model="claimPartVO.partDescr" required="required" ng-readonly="commentUpdateBtnFlag"></td>
								<td><input type="number" class="form-control" name=""
									ng-model="claimPartVO.qty" required="required"
									ng-change="calcTotalPartLine($index)" ng-readonly="commentUpdateBtnFlag"></td>
								<td><input type="number" class="form-control" name=""
									ng-model="claimPartVO.unitPrice" required="required"
									ng-change="calcTotalPartLine($index)" ng-readonly="commentUpdateBtnFlag"></td>
								<td class="t-r">{{claimPartVO.partsTotal | currency}}</td>
							</tr>
						</tbody>
					</table>
					<div class="col-sm-12">
						<div class="col-sm-6">
							<button type="button" class="btn btn-primary btn-sm"
								ng-click="claim.claimPartVOList.push({})">
								<i class="fa fa-plus" ng-readonly="commentUpdateBtnFlag"></i> New Line
							</button>
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
								<td><input type="text" class="form-control" name=""
									ng-model="claimLabourVO.laborNo" required="required" ng-readonly="commentUpdateBtnFlag"></td>
								<td><input type="text" class="form-control" name=""
									ng-model="claimLabourVO.laborDescr" required="required" ng-readonly="commentUpdateBtnFlag"></td>
								<td><input type="number" class="form-control" name=""
									ng-model="claimLabourVO.laborHrs" required="required"
									ng-change="calcTotalLabourLine($index)" ng-readonly="commentUpdateBtnFlag"></td>
								<td><input type="number" class="form-control" name=""
									ng-model="claimLabourVO.laborHourlyRate" required="required"
									ng-change="calcTotalLabourLine($index)" ng-readonly="commentUpdateBtnFlag"></td>
								<td class="t-r">{{claimLabourVO.labourTotal | currency}}</td>
							</tr>
						</tbody>
					</table>
					<div class="col-sm-12">
						<div class="col-sm-6">
							<button type="button" class="btn btn-primary btn-sm"
								ng-click="claim.claimLabourVOList.push({})" ng-readonly="commentUpdateBtnFlag">
								<i class="fa fa-plus"></i> New Line
							</button>
						</div>
						<div class="col-sm-6">
							<div class="col-sm-8 no-pad">Total Requested Labor Cost</div>
							<div class="col-sm-4 t-r">
								<!-- <input type="text" class="form-control" name="" ng-model="claim.totalLaborCost" ng-readonly=true> -->
								{{claim.totalLaborCost | currency}}
							</div>
						</div>

					</div>
				</div>
				<br clear="all">
				   <div class="col-xs-12 no-pad pad10-top" ng-show="commentUpdateBtnFlag">
                         <span class="ag-tab-title col-xs-12 no-pad marg10-bottom">Claim Comments</span>
                         <br clear="all">
                        
                         <br>
                          <div class="col-sm-12">
                            <div class="form-group clearfix">
                              <span class="col-sm-3 no-pad">External Comments :</span> 
                                <div class="col-sm-9 no-pad border">
                                <div class="col-sm-4">
                                  <b>Date/Time</b><br>
                                  June 21 2016
                                </div>
                                <div class="col-sm-8">
                                  <b>Comment</b><br>
                                  Text comes here..
                                </div>
                                  <textarea class="form-control" style="width:100% !important;" rows="3" ng-model="claim.extComment" ng-required="extCommentFlag" ng-trim=true></textarea>
                                </div>
                            </div>
                          </div>
                        </div>
			</div>
		</div>
	</div>
	<div class="col-sm-12 no-pad t-c marg10-top marg10-bottom" ng-show="saveBtnFlag">
		<button type="button" class="btn btn-primary" ng-click="saveAsDraft()">
			Save Claim</button>
		<button type="submit" class="btn btn-primary" ng-click="reqAuth()"
			ng-show="isSubmitDisabled">Request Preauthorization</button>
		<button type="submit" class="btn btn-primary"
			ng-disabled="isSubmitDisabled" ng-click="reqSubmit()">
			Submit Claim</button>
	</div>
	<div class="col-sm-12 no-pad t-c marg10-top marg10-bottom" ng-show="updateBtnFlag">
		<button type="submit" class="btn btn-primary" ng-click="updateClaim()">Update</button>
	</div>
	<div class="col-sm-12 no-pad t-c marg10-top marg10-bottom" ng-show="commentUpdateBtnFlag">
		<button type="submit" class="btn btn-primary" ng-click="updateCommentClaim()">Update</button>
	</div>
</form>
