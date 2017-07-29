<form name="newClaimForm" ng-submit="onClickSubmitClaim()">
	<div class="col-xs-12 agf1 main-login pad10-top">
		<div id="claimErrMsg" class="alert alert-danger text-center hidden"></div>
		<div class="col-xs-12">If the repair estimate is over $1500 a pre-authorization claim must be submitted. If the diagnostic cost is estimated to exceed $500, the repair facility must contact Agguard before proceeding. Failure to notify us could result in a maximum of $500 being allowed for diagnostics.</div>
		<div class="col-xs-12 no-pad clearfix">
			<div class="col-md-6 no-pad pad10-right">
				<span class="ag-tab-title col-xs-12 no-pad marg10-bottom">Contract
					Information</span> <br clear="all"> <br>
				<div class="form-group no-pad col-xs-12">
					<label>Contract ID</label>
					<p>{{contractInfoList.contractID}}</p>
				</div>
				<div class="form-group col-xs-12 no-pad">
					<label>Manufacturer</label>
					<p>{{contractInfoList.manfactureName}}</p>
				</div>
				<div class="form-group col-xs-12 no-pad">
					<label>Contract Expiration Date</label>
					<p>{{contractInfoList.expirationDate |  date:"MM/dd/yyyy"}}</p>
				</div>
				<div class="form-group col-xs-12 no-pad">
					<label>Limit of Liability (LOL)</label>
					<p>{{contractInfoList.lol | currency:"$":0}}</p>
				</div>
				<div class="form-group col-xs-12 no-pad">
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

					<div class="col-xs-12 col-xs-12 no-pad">
						<div class="form-group">
							<label>Serial/VIN #</label>
							<p>{{contractInfoList.machineSerialNo}}</p>
						</div>
						<div class="form-group col-xs-12 no-pad">
							<label>Model</label>
							<p>{{contractInfoList.machineModel}}</p>
						</div>
						<div class="form-group col-xs-12 no-pad">
							<label>Coverage Type</label>
							<p>{{(contractInfoList.coverageType === 'PT')?"Powertrain":(contractInfoList.coverageType === 'PH')?"Powertrain + Hydraulic":(contractInfoList.coverageType === 'PL')?"Powertrain + Hydraulic + Platform":""}}</p>
						</div>
						<div class="form-group col-xs-12 no-pad">
							<label>Usage Hours covered</label>
							<p>{{contractInfoList.usageHoursCovered}}</p>
						</div>
						<div class="form-group col-xs-12 no-pad">
							<label>Available LOL</label>
							<p>{{contractInfoList.availableLol | currency}}</p>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="col-xs-12 no-pad clearfix">
			<span class="ag-tab-title col-xs-12 no-pad marg10-bottom">Servicing Dealer 
				(<b><font color="red" size="2">AgGuard will forward any payment or correspondence to this address</font></b>)</span> <br clear="all"> <br>
 			<div class="col-md-6 no-pad pad10-right">
				<div class="form-group no-pad col-xs-12">
					<label>Dealer Name</label> <input type="text"
						class="form-control" ng-model="claim.dealerName"
						required="required" ng-readonly="commentUpdateBtnFlag">
				</div>
				<div class="form-group col-xs-12 no-pad">
					<label>City</label> <input type="text" class="form-control"
						ng-model="claim.dealerCity" required="required" ng-readonly="commentUpdateBtnFlag">
				</div>
				<div class="form-group col-xs-12 no-pad">
					<label>Zip</label> <input type="text" class="form-control"
						ng-model="claim.dealerZip" required="required" ng-readonly="commentUpdateBtnFlag">
				</div>
				<div class="form-group col-xs-12 no-pad">
					<label>E-mail</label> <input type="text"
						class="form-control" ng-model="claim.dealerEmail"
						required="required" ng-readonly="commentUpdateBtnFlag">
				</div>		
			</div>

			<div class="col-md-6 no-pad">
				<div class="col-xs-12 no-pad pad10-left border-left">
					<div class="col-xs-12 no-pad">
						<div class="form-group col-xs-12 no-pad">
							<label>Address</label>
							<textarea class="form-control" rows="2"
								ng-model="claim.dealerAddress" required="required" ng-readonly="commentUpdateBtnFlag"></textarea>
						</div>
						<div class="form-group col-xs-12 no-pad">
							<label>State/Province</label>
							<!-- <input type="text"
						class="form-control" ng-model="claim.dealerState"
						required="required" ng-readonly="commentUpdateBtnFlag"> -->
						<select class="form-control" name="state" ng-model="claim.dealerState" id="state" required="required" ng-readonly="commentUpdateBtnFlag">
							<option value="">Select State/Province</option>
							<option value="AL">Alabama</option>
							<option value="AK">Alaska</option>
							<option value="AZ">Arizona</option>
							<option value="AR">Arkansas</option>
							<option value="CA">California</option>
							<option value="CO">Colorado</option>
							<option value="CT">Connecticut</option>
							<option value="DE">Delaware</option>
							<option value="DC">District Of Columbia</option>
							<option value="FL">Florida</option>
							<option value="GA">Georgia</option>
							<option value="HI">Hawaii</option>
							<option value="ID">Idaho</option>
							<option value="IL">Illinois</option>
							<option value="IN">Indiana</option>
							<option value="IA">Iowa</option>
							<option value="KS">Kansas</option>
							<option value="KY">Kentucky</option>
							<option value="LA">Louisiana</option>
							<option value="ME">Maine</option>
							<option value="MD">Maryland</option>
							<option value="MA">Massachusetts</option>
							<option value="MI">Michigan</option>
							<option value="MN">Minnesota</option>
							<option value="MS">Mississippi</option>
							<option value="MO">Missouri</option>
							<option value="MT">Montana</option>
							<option value="NE">Nebraska</option>
							<option value="NV">Nevada</option>
							<option value="NH">New Hampshire</option>
							<option value="NJ">New Jersey</option>
							<option value="NM">New Mexico</option>
							<option value="NY">New York</option>
							<option value="NC">North Carolina</option>
							<option value="ND">North Dakota</option>
							<option value="OH">Ohio</option>
							<option value="OK">Oklahoma</option>
							<option value="OR">Oregon</option>
							<option value="PA">Pennsylvania</option>
							<option value="RI">Rhode Island</option>
							<option value="SC">South Carolina</option>
							<option value="SD">South Dakota</option>
							<option value="TN">Tennessee</option>
							<option value="TX">Texas</option>
							<option value="UT">Utah</option>
							<option value="VT">Vermont</option>
							<option value="VA">Virginia</option>
							<option value="WA">Washington</option>
							<option value="WV">West Virginia</option>
							<option value="WI">Wisconsin</option>
							<option value="WY">Wyoming</option>
							<option value="AB">Alberta</option>
							<option value="BC">British Columbia</option>
							<option value="MB">Manitoba</option>
							<option value="NB">New Brunswick</option>
							<option value="NL">Newfoundland and Labrador</option>
							<option value="NS">Nova Scotia</option>
							<option value="ON">Ontario</option>
							<option value="PE">Prince Edward Island</option>
							<option value="QC">Quebec</option>
							<option value="SK">Saskatchewan</option>
							<option value="NT">Northwest Territories</option>
							<option value="NU">Nunavut</option>
							<option value="YT">Yukon</option>
					</select>
						</div>
						<div class="form-group col-xs-12 no-pad">
							<label>Phone Number</label>
							<input type="text"
						class="form-control" ng-model="claim.dealerPhone"
						required="required" ng-readonly="commentUpdateBtnFlag">
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="col-xs-12 no-pad clearfix">
			<div class="col-md-6 no-pad pad10-right">
				<span class="ag-tab-title col-xs-12 no-pad marg10-bottom">Claim
					Information</span> <br clear="all"> <br>
				<div class="form-group no-pad col-xs-12">
					<label>Serial Number</label> <input type="text"
						class="form-control" ng-model="contractInfoList.machineSerialNo"
						ng-readonly=true>
				</div>
				<div class="form-group col-xs-12 no-pad">
					<label>Manufacturer</label> <input type="text" class="form-control"
						ng-model="contractInfoList.manfactureName" ng-readonly=true>
				</div>
				<div class="form-group col-xs-12 no-pad">
					<label>Model Number</label> <input type="text" class="form-control"
						ng-model="contractInfoList.machineModel" ng-readonly=true>
				</div>
				<!-- <div class="form-group col-xs-12 no-pad">
					<label>Coverage Type</label> 
					{{(contractInfoList.coverageType === 'PT')?"Powertrain":(contractInfoList.coverageType === 'PH')?"Powertrain + Hydraulic":(contractInfoList.coverageType === 'PL')?"Powertrain + Hydraulic + Platform":""}}
				</div> -->
				<div class="form-group col-xs-12 no-pad">
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
				<div class="form-group col-xs-12 no-pad">
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
				<div class="form-group col-xs-12 no-pad">
					<label>Work Order Number</label> <input type="text"
						class="form-control" ng-model="claim.workOrder"
						ngRequired="required" ng-readonly="commentUpdateBtnFlag">
				</div>
				<div class="form-group col-xs-12 no-pad">
					<label>Hours @ Breakdown</label> <input type="number"
						class="form-control" ng-model="claim.hoursBreakDown"
						required="required" ng-readonly="commentUpdateBtnFlag">
				</div>
				<div class="form-group col-xs-12 no-pad">
					<label>Other Charge 1 ($)</label> <input type="number" step="0.01" 
						class="form-control" ng-model="claim.requestedOtherCharges1"
						required="required" ng-readonly="commentUpdateBtnFlag">
				</div>
				<div class="form-group col-xs-12 no-pad">
					<label>Other Charge 2 ($)</label> <input type="number" step="0.01"
						class="form-control" ng-model="claim.requestedOtherCharges2"
						required="required" ng-readonly="commentUpdateBtnFlag">
				</div>
				<div class="form-group col-xs-12 no-pad">
					<label>Total Claim ($)</label> <input type="text" 
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
						<div class="form-group col-xs-12 no-pad">
							<label>Customer Complaint</label>
							<textarea class="form-control" rows="2"
								ng-model="claim.custComplaint" required="required" ng-readonly="commentUpdateBtnFlag"></textarea>
						</div>
						<div class="form-group col-xs-12 no-pad">
							<label>Cause of Failure</label>
							<textarea class="form-control" rows="5"
								ng-model="claim.causeFail" required="required" ng-readonly="commentUpdateBtnFlag"></textarea>
						</div>
						<div class="form-group col-xs-12 no-pad">
							<label>Corrective Action</label>
							<textarea class="form-control" rows="2"
								ng-model="claim.correctiveAction" required="required" ng-readonly="commentUpdateBtnFlag"></textarea>
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
								<th class="col-sm-3">Description</th>
								<th class="col-sm-2">Quantity</th>
								<th class="col-sm-2">Unit Price</th>
								<th class="t-r col-sm-2">Part Total</th>
								<th class="col-sm-1"></th>
							</tr>
						</thead>
						<tbody data-ng-repeat="claimPartVO in claim.claimPartVOList">
							<tr>
								<td><input type="text" class="form-control" name=""
									ng-model="claimPartVO.partNo" required="required" ng-readonly="commentUpdateBtnFlag"></td>
								<td><input type="text" class="form-control" name=""
									ng-model="claimPartVO.partDescr" required="required" ng-readonly="commentUpdateBtnFlag"></td>
								<td><input type="number" step="0.01" class="form-control" name=""
									ng-model="claimPartVO.qty" required="required"
									ng-change="calcTotalPartLine($index)" ng-readonly="commentUpdateBtnFlag"></td>
								<td><input type="number" step="0.01" class="form-control" name=""
									ng-model="claimPartVO.unitPrice" required="required"
									ng-change="calcTotalPartLine($index)" ng-readonly="commentUpdateBtnFlag"></td>
								<td class="t-r">{{claimPartVO.partsTotal | currency}}</td>
								<td>
									<button ng-if="claim.claimPartVOList.length > 1" type="button" class="btn btn-primary btn-sm"
									ng-click="removeClaimPart(claimPartVO);calcTotalPartCost(claim)">
										<i class="fa fa-minus" ng-readonly="commentUpdateBtnFlag"></i>
									</button>
								</td>
							</tr>
						</tbody>
					</table>
					<div class="col-sm-12">
						<div class="col-sm-6">
							<button type="button" class="btn btn-primary btn-sm"
								ng-click="claim.claimPartVOList.push({})">
								<i class="fa fa-plus" ng-readonly="commentUpdateBtnFlag"></i> Add Part
							</button>
							<!-- <button ng-if="claim.claimPartVOList.length > 1" type="button" class="btn btn-primary btn-sm"
								ng-click="claim.claimPartVOList.pop();calcTotalPartCost(claim)">
								<i class="fa fa-minus" ng-readonly="commentUpdateBtnFlag"></i> Delete Part
							</button> -->
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
								<th class="col-sm-3">Description</th>
								<th class="col-sm-2">Hours</th>
								<th class="col-sm-2">Hourly Rate</th>
								<th class="t-r col-sm-2">Total</th>
								<th class="col-sm-1"></th>
							</tr>
						</thead>
						<tbody data-ng-repeat="claimLabourVO in claim.claimLabourVOList">
							<tr>
								<td><input type="text" class="form-control" name=""
									ng-model="claimLabourVO.laborNo" ng-readonly="commentUpdateBtnFlag"></td>
								<td><input type="text" class="form-control" name=""
									ng-model="claimLabourVO.laborDescr" required="required" ng-readonly="commentUpdateBtnFlag"></td>
								<td><input type="number" step="0.01" class="form-control" name=""
									ng-model="claimLabourVO.laborHrs" required="required"
									ng-change="calcTotalLabourLine($index)" ng-readonly="commentUpdateBtnFlag"></td>
								<td><input type="number" step="0.01" class="form-control" name=""
									ng-model="claimLabourVO.laborHourlyRate" required="required"
									ng-change="calcTotalLabourLine($index)" ng-readonly="commentUpdateBtnFlag"></td>
								<td class="t-r">{{claimLabourVO.labourTotal | currency}}</td>
								<td>
									<button ng-if="claim.claimLabourVOList.length > 1" type="button" class="btn btn-primary btn-sm"
										ng-click="removeClaimLabor(claimLabourVO);calcTotalLabourCost(claim)" ng-readonly="commentUpdateBtnFlag">
										<i class="fa fa-minus"></i>
									</button>
								</td>
							</tr>
						</tbody>
					</table>
					<div class="col-sm-12">
						<div class="col-sm-6">
							<button type="button" class="btn btn-primary btn-sm"
								ng-click="claim.claimLabourVOList.push({})" ng-readonly="commentUpdateBtnFlag">
								<i class="fa fa-plus"></i> Add Labor
							</button>
							<!-- <button ng-if="claim.claimLabourVOList.length > 1" type="button" class="btn btn-primary btn-sm"
								ng-click="claim.claimLabourVOList.pop();calcTotalLabourCost(claim)" ng-readonly="commentUpdateBtnFlag">
								<i class="fa fa-minus"></i> Delete Labor
							</button> -->
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
				<div class="col-xs-12 no-pad pad10-top">
					<span class="ag-tab-title col-xs-12 no-pad marg10-bottom">File
						Attachments</span> <br clear="all">
					<p>Please upload pdf versions of quotes and any pictures showing the damage</p>
					<br>
					<div class="col-sm-12">
						<div class="col-sm-6">
							<div class="form-group">
								<label>File</label> <input type="file" id="" name="files" multiple
									ng-files="getTheFiles($files)" ng-readonly="commentUpdateBtnFlag">
							</div>
						</div>
					</div>
					<div class="col-xs-12 no-pad pad10-top" ng-if="claim.claimFileDO">
						<span class="ag-tab-title col-xs-12 no-pad marg10-bottom">Uploaded Documents</span>
		                    <!-- <br clear="all">
		                      
		                    <br> -->
		                    <div class="col-sm-12">
								<table>
									<tbody data-ng-repeat="ufile in claim.claimFileDO">
										<tr><td><a href="/agg/claim/file/{{claim.claimId}}?filename={{ufile.fileName}}" target="_blank">{{ufile.fileName}}</a></td></tr>
									</tbody>
								</table>
							</div>
					</div>
				</div>
				
                <br clear="all">
				   <div class="col-xs-12 no-pad pad10-top">
                         <span class="ag-tab-title col-xs-12 no-pad marg10-bottom">External Comments</span>
                          <div class="col-sm-12">
                            <div class="form-group clearfix">
                                <div class="col-sm-9 no-pad border" ng-repeat="claimNote in claim.claimsNoteList">
                                  <!-- <textarea class="form-control" style="width:100% !important;" rows="3" ng-model="claim.extComment" ng-required="extCommentFlag" ng-trim=true></textarea>-->
                                  {{claimNote.lastUpdate |  date:"dd-MMM-yyyy"}} - {{claimNote.updatedBy}}: {{claimNote.notes}}
                                </div>
                            </div>
                            <span class="col-sm-3 no-pad">Enter Comments :</span>
                            <div class="col-sm-9 no-pad border">
                                  <textarea class="form-control" style="width:100% !important;" rows="3" ng-model="claim.extComments"  ng-trim=true></textarea>
                                  <!-- ng-required="extCommentFlag" removed this from above line -->
                             </div>
                          </div>
                        </div>
                        
			</div>
		</div>
		
	</div>
	<div class="col-sm-12 no-pad t-c marg10-top marg10-bottom" ng-show="saveBtnFlag">
		<button type="button" class="btn btn-primary" ng-click="saveAsDraft()" ng-show="saveClaimShow">
			Save Claim</button>
		<button type="submit" class="btn btn-primary" ng-click="reqAuth()"
			ng-show="isSubmitDisabled">Request Preauthorization</button>
		<button type="submit" class="btn btn-primary"
			ng-hide="isSubmitDisabled" ng-click="reqSubmit()">
			Submit Claim</button>
	</div>
	<div class="col-sm-12 no-pad t-c marg10-top marg10-bottom" ng-show="updateBtnFlag">
		<button type="submit" class="btn btn-primary" ng-click="updateClaim()">Update</button>
	</div>
	<div class="col-sm-12 no-pad t-c marg10-top marg10-bottom" ng-show="commentUpdateBtnFlag">
		<button type="submit" class="btn btn-primary" ng-click="updateCommentClaim()">Update</button>
	</div>
</form>
