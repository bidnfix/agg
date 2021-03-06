<div class="col-xs-12 agf1 main-login pad10-top">
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
 			<div class="col-md-6 no-pad pad10-right">
				<span class="ag-tab-title col-xs-12 no-pad marg10-bottom">Servicing Dealer
					Information</span> <br clear="all"> <br>
				<div class="form-group no-pad col-xs-12">
					<label>Dealer Name</label> 
					<p>{{claim.dealerName}}</p>
				</div>
				<div class="form-group col-xs-12 no-pad">
					<label>City</label> 
					<p>{{claim.dealerCity}}</p>
				</div>
				<div class="form-group col-xs-12 no-pad">
					<label>Zip</label> 
					<p>{{claim.dealerZip}}</p>
				</div>
				<div class="form-group col-xs-12 no-pad">
					<label>E-mail</label> 
					<p>{{claim.dealerEmail}}</p>
				</div>		
			</div>

			<div class="col-md-6 no-pad">
				<span
					class="ag-tab-title col-xs-12 no-pad marg10-bottom  pad20-left"
					style="margin-left: -10px !important;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
				<div class="col-xs-12 no-pad pad10-left border-left">
					<br clear="all">

					<div class="col-xs-12 no-pad">
						<div class="form-group col-xs-12 no-pad">
							<label>Address</label>
							<p>{{claim.dealerAddress}}</p>
						</div>
						<div class="form-group col-xs-12 no-pad">
							<label>State/Province</label>
						<p>{{claim.dealerState}}</p>
						</div>
						<div class="form-group col-xs-12 no-pad">
							<label>Phone Number</label>
						<p>{{claim.dealerPhone}}</p>
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
					<label>Serial Number</label> 
					{{contractInfoList.machineSerialNo}}
				</div>
				<div class="form-group col-xs-12 no-pad">
					<label>Manufacturer</label> 
					{{contractInfoList.manfactureName}}
				</div>
				<div class="form-group col-xs-12 no-pad">
					<label>Model Number</label> 
					{{contractInfoList.machineModel}}
				</div>
				<!-- <div class="form-group col-xs-12 no-pad">
					<label>Coverage Type</label>
					{{(contractInfoList.coverageType === 'PT')?"Powertrain":(contractInfoList.coverageType === 'PH')?"Powertrain + Hydraulic":(contractInfoList.coverageType === 'PL')?"Powertrain + Hydraulic + Platform":""}} 
				</div> -->
				<div class="form-group col-xs-12 no-pad">
					<label>Failure Date</label>
					{{claim.failDate | date:'yyyy-MM-dd'}}
				</div>
				<div class="form-group col-xs-12 no-pad">
					<label>Reported On</label>
					{{claim.reportDate | date:'yyyy-MM-dd'}}					
				</div>
				<div class="form-group col-xs-12 no-pad">
					<label>Work Order Number</label> 
					{{claim.workOrder}}
				</div>
				<div class="form-group col-xs-12 no-pad">
					<label>Hours @ Breakdown</label>
					{{claim.hoursBreakDown}}
				</div>
				<div class="form-group col-xs-12 no-pad">
					<label>Other Charge 1 ($)</label> 
					{{claim.requestedOtherCharges1 | number : 2}}
				</div>
				<div class="form-group col-xs-12 no-pad">
					<label>Other Charge 2 ($)</label> 
					{{claim.requestedOtherCharges2 | number : 2}}
				</div>
				<div class="form-group col-xs-12 no-pad">
					<label>Total Claim ($)</label>
					{{claim.totalClaimCost | number : 2}}
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
							<label>Dealer</label>
							{{(claim.dealerDO.name != null)?claim.dealerDO.name:"&nbsp;"}}
					  	</div>
					  	<div class="form-group col-xs-12 no-pad">
							<label>Dealer Email</label>
							{{(claim.dealerDO.invoiceEmail != null)?claim.dealerDO.invoiceEmail:"&nbsp;"}}
					  	</div>
                        <div class="form-group col-xs-12 no-pad">
                          <label>Dealer Contact</label>
                          {{(claim.createdUser != null)?claim.createdUser:"&nbsp;"}}
                        </div>
						<div class="form-group col-xs-12 no-pad">
							<label>Customer Complaint</label>
							{{claim.custComplaint}}
						</div>
						<div class="form-group col-xs-12 no-pad">
							<label>Cause of Failure</label>
							{{claim.causeFail}}
						</div>
						<div class="form-group col-xs-12 no-pad">
							<label>Corrective Action</label>
							{{claim.correctiveAction}}
						</div>
						<!-- <div class="form-group col-xs-12 no-pad">
							<label>Check#</label>
							{{claim.cheqNo}}
						</div>
						<div class="form-group col-xs-12 no-pad">
							<label>Paid Date</label>
							{{claim.paidDate |  date:"MM/dd/yyyy"}}
						</div> -->
						<div class="form-group col-xs-12 no-pad">
							<label>Adjusted Other Charge 1 ($) </label>
							{{claim.approvedOtherCharges1 | number : 2}}
						</div>
						<div class="form-group col-xs-12 no-pad">
							<label>Adjusted Other Charge 2 ($) </label>
							{{claim.approvedOtherCharges2 | number : 2}}
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
								<th class="col-sm-2">Description</th>
								<th class="col-sm-1">Quantity</th>
								<th class="col-sm-1">Adj Quantity</th>
								<th class="col-sm-1">Unit Price</th>
								<th class="col-sm-1">Adj Unit Price</th>
								<th class="t-r col-sm-2">Part Total</th>
								<th class="t-r col-sm-2">Adj Part Total</th>
							</tr>
						</thead>
						<tbody data-ng-repeat="claimPartVO in claim.claimPartVOList">
							<tr>
								<td>{{claimPartVO.partNo}}</td>
								<td>{{claimPartVO.partDescr}}</td>
								<td>{{claimPartVO.qty}}</td>
								<td>{{claimPartVO.adjQty}}</td>
								<td>{{claimPartVO.unitPrice | number : 2}}</td>
								<td>{{claimPartVO.adjUnitPrice | number : 2}}</td>
								<td class="t-r">{{claimPartVO.partsTotal | currency}}</td>
								<td class="t-r">{{claimPartVO.partsAdjTotal | currency}}</td>
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
							<div class="col-sm-8 no-pad">Total Adjusted Parts Cost</div>
							<div class="col-sm-4 t-r">
								<!-- <input type="text" class="form-control" name="" ng-model="claim.partsTotalCost" ng-readonly=true> -->
								{{claim.totalAdjustedPartsCost | currency}}
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
								<th class="col-sm-2">Description</th>
								<th class="col-sm-1">Hours</th>
								<th class="col-sm-1">Adj Hours</th>
								<th class="col-sm-1">Hourly Rate</th>
								<th class="col-sm-1">Adj Hourly Rate</th>
								<th class="t-r col-sm-2">Total</th>
								<th class="t-r col-sm-2">Adj Total</th>
							</tr>
						</thead>
						<tbody data-ng-repeat="claimLabourVO in claim.claimLabourVOList">
							<tr>
								<td>{{claimLabourVO.laborNo}}</td>
								<td>{{claimLabourVO.laborDescr}}</td>
								<td>{{claimLabourVO.laborHrs}}</td>
								<td>{{claimLabourVO.adjLaborHrs}}</td>
								<td>{{claimLabourVO.laborHourlyRate | number : 2}}</td>
								<td>{{claimLabourVO.adjRate | number : 2}}</td>
								<td class="t-r">{{claimLabourVO.labourTotal | currency}}</td>
								<td class="t-r">{{claimLabourVO.labourAdjTotal | currency}}</td>
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
                            <div class="col-sm-8 no-pad">
                              Total Adjusted Labor Cost
                            </div>
                            <div class="col-sm-4 t-r">
                              {{claim.totalAdjustmentLaborsCost | currency}}
                            </div>
                            <div class="col-sm-8 no-pad">
                              Total Requested Claim Cost
                            </div>
                            <div class="col-sm-4 t-r">
                                  {{claim.totalClaimCost | currency}}                         
                            </div>
                            <div class="col-sm-8 no-pad">
                              Total Adjusted Claim Cost
                            </div>
                            <div class="col-sm-4 t-r">
                                  {{claim.totalAdjustedClaimCost | currency}}                         
                            </div>
                            <div class="col-sm-8 no-pad">
                              Total Re-imbursed Amount
                            </div>
                            <div class="col-sm-4 t-r">
                                  {{claim.tra | currency}}                         
                            </div>
                            <div class="col-sm-8 no-pad">
                              Total Amount owed by the Customer
                            </div>
                            <div class="col-sm-4 t-r">
                                  {{claim.customerOwes | currency}}                         
                            </div> 
							
						</div>

					</div>
				</div>
				<br clear="all">
			</div>
		</div>
		
		<div class="col-sm-12">
          <span class="ag-tab-title col-xs-12 no-pad marg10-bottom">Payment Details</span>
          <br clear="all">
          <br>
          <table class="table table-bordered">
			<thead>
				<tr>
					<th class="col-sm-4">Check #</th>
					<th class="col-sm-4">Paid Date</th>
					<th class="col-sm-3">Check Amount</th>
				</tr>
			</thead>
			<tbody data-ng-repeat="checkDO in claim.checkDOList">
				<tr>
					<td>{{checkDO.checkNo}}</td>
					<td>{{checkDO.receivedDate |  date:"MM/dd/yyyy"}}</td>
					<td>{{checkDO.amount | currency}}</td>
				</tr>
			</tbody>
		 </table>
		 <div class="col-sm-12">
			<div class="col-sm-6"></div>
			<div class="col-sm-6">
				<div class="col-sm-6 no-pad">Total Check Amount</div>
				<div class="col-sm-6">
					{{claim.totalCheckAmount | currency}}
				</div>
			</div>
		 </div>
       </div>
		
	   <div class="col-xs-12 no-pad pad10-top" ng-if="claim.claimFileDO">
			<span class="ag-tab-title col-xs-12 no-pad marg10-bottom">Uploaded Documents</span>
                   <div class="col-sm-12">
					<table>
						<tbody data-ng-repeat="ufile in claim.claimFileDO">
							<tr><td><a href="/agg/claim/file/{{claim.claimId}}?filename={{ufile.fileName}}" target="_blank">{{ufile.fileName}}</a></td></tr>
						</tbody>
					</table>
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
                          </div>
                        </div>
	</div>
