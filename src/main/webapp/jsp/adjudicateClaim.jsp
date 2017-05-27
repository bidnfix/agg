	<div>
		<!-- Article main content -->
		<article class="col-md-9 maincontent" ng-if='showAdjudicateClaimList'>
			<header class="page-header">
	              	<div class="col-md-6 col-sm-12">
						<div class="sec-title">
							<h3 class="wow animated bounceInLeft">{{(claimTypeDesc === 'approvedForPayment')?"Approved for Payment Claims":"Pending Claims"}}</h3>
						</div>
                    </div>
			</header>
			<div>
				<table id="preauthClaimsListTable" class="table table-striped table-bordered" cellspacing="0" width="100%">
					<thead>
			            <tr>
			            	<th>Claim #</th>
			                <th>Dealer Name</th>
			                <th>Dealer Contact Name</th>
			                <th>Serial #</th>
			                <th></th>
			                <th></th>
			            </tr>
			        </thead>
			        <tfoot>
			            <tr>
			            	<th>Claim #</th>
			                <th>Dealer Name</th>
			                <th>Dealer Contact Name</th>
			                <th>Serial #</th>
			                <th></th>
			                <th></th>
			            </tr>
			        </tfoot>
					<tbody>
			            <tr ng-repeat="claim in adjudicateClaimList">
			                <td>{{claim.claimId}}</td>
			                <td>{{claim.dealerDO.name}}</td>
			                <td>{{claim.createdUser}}</td>
			                <td>{{claim.serial}}</td>
			                <td>
			                	<div class="manage-sec">
			                		<a ng-click="onClickSelectClaim(claim)"><img src="../assets/images/edit-pencil.png" alt="Edit" title="Edit"/></a>
			                	</div>
			                </td>
			                <td>{{claim.lastUpdate}}</td>
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
                        
							<h4 class="wow animated bounceInLeft">Adjudicate a Claim</h4>
							<div >
								Claim #: <b>{{adjudicateClaim.claimId}}</b>
								<div style="display: inline-block;" ng-show="adjudicateClaim.cStatus != null">
									&nbsp;&nbsp;&nbsp;Status: &nbsp;
									<b>
									{{(adjudicateClaim.cStatus > 0)?((adjudicateClaim.cStatus === 1)?"Open":(adjudicateClaim.cStatus === 2)?"Pre-Auth Requested":(adjudicateClaim.cStatus === 3)?"Submitted":
					                (adjudicateClaim.cStatus === 4)?"Approved":(adjudicateClaim.cStatus === 5)?"Pre-Auth Approved":(adjudicateClaim.cStatus === 6)?"Pre-Auth Rejected":
					                (adjudicateClaim.cStatus === 7)?"Approved with adjustment":(adjudicateClaim.cStatus === 8)?"Pending":(adjudicateClaim.cStatus === 9)?"Draft":
					                (adjudicateClaim.cStatus === 10)?"Rejected":(adjudicateClaim.cStatus === 11)?"Approved for Payment":""):""}}
					                </b>
								</div>
							</div>
						</div>
                        </div>
                        <div class="col-md-6 col-sm-12"><a class="btn btn-primary pull-right btn-sm mar-right" ng-click="onClickBackToList()">Back</a></div>
				</header>
                
                
				 <!-- data table section -->
                
                <div class="inner-main">
                     <form name="newClaimForm" ng-submit="onClickSubmitClaim()">   
                      <div class="col-xs-12 agf1 main-login pad10-top">
                      	<div id="claimErrMsg" class="alert alert-danger text-center hidden"></div>
                      	<div class="col-xs-12 no-pad clearfix">
							<div class="col-md-6 no-pad pad10-right">
								<span class="ag-tab-title col-xs-12 no-pad marg10-bottom">Contract
									Information</span> <br clear="all"> <br>
								<div class="form-group no-pad col-xs-12">
									<label>Contract ID</label>
									<p>{{adjudicateClaim.contractDO.contractId}}</p>
								</div>
								<div class="form-group col-xs-12 no-pad">
									<label>Contract Expiration Date</label>
									<p>{{adjudicateClaim.contractDO.expirationDate |  date:"MM/dd/yyyy"}}</p>
								</div>
								<div class="form-group col-xs-12 no-pad">
									<label>Limit of Liability (LOL)</label>
									<p>{{adjudicateClaim.contractDO.lol | currency:"$":0}}</p>
								</div>
								<div class="form-group col-xs-12 no-pad">
									<label>Deductible</label>
									<p>{{adjudicateClaim.contractDO.deductible | currency:"$":0}}</p>
								</div>
							</div>
					
							<div class="col-md-6 no-pad">
								<span
									class="ag-tab-title col-xs-12 no-pad marg10-bottom  pad20-left"
									style="margin-left: -10px !important;">&nbsp;</span>
								<div class="col-xs-12 no-pad pad10-left">
									<br clear="all">
					
									<div class="col-xs-12 no-pad">
										<div class="form-group col-xs-12 no-pad">
											<label>Serial/VIN #</label>
											<p>{{adjudicateClaim.contractDO.machineSerialNo}}</p>
										</div>
										<div class="form-group col-xs-12 no-pad">
											<label>Manufacturer</label>
											<p>{{adjudicateClaim.manufacturer}}</p>
										</div>
										<div class="form-group col-xs-12 no-pad">
											<label>Model</label>
											<p>{{adjudicateClaim.contractDO.machineModel}}</p>
										</div>
										<div class="form-group col-xs-12 no-pad">
											<label>Usage Hours covered</label>
											<p>{{adjudicateClaim.contractDO.expirationUsageHours}}</p>
										</div>
										<div class="form-group col-xs-12 no-pad">
											<label>Available LOL</label>
											<p>{{adjudicateClaim.contractDO.availabeLol | currency}}</p>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-xs-12 no-pad clearfix">
				 			<div class="col-md-6 no-pad pad10-right">
								<span class="ag-tab-title col-xs-12 no-pad marg10-bottom">Dealer
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
											<label>State</label>
										<p>{{claim.dealerState}}</p>
										</div>
										<div class="form-group col-xs-12 no-pad">
											<label>Phone</label>
										<p>{{claim.dealerPhone}}</p>
										</div>
									</div>
								</div>
							</div>
						</div>
                        <div class="col-xs-12 no-pad clearfix">
                        <div class="col-md-6 no-pad pad10-right">
                         <span class="ag-tab-title col-xs-12 no-pad marg10-bottom">Claim Information</span>
                         <br clear="all">
                         <br>
                          <div class="form-group no-pad col-xs-12">
                            <label>Serial Number</label>
                            {{adjudicateClaim.serial}}
                          </div>
                          <!-- <div class="form-group">
                            <label>Manufacturer</label>
                            <input type="text" class="form-control" ng-model="contractInfoList.manfactureName" ng-readonly=true>
                          </div>
                          <div class="form-group">
                            <label>Model Number</label>
                            <input type="text" class="form-control" ng-model="contractInfoList.machineModel" ng-readonly=true>
                          </div>
                          <div class="form-group">
                            <label>Coverage Type</label>
                            <input type="text" class="form-control" ng-model="contractInfoList.coverageType" ng-readonly=true>
                          </div> -->
                          <div class="form-group col-xs-12 no-pad">
                            <label>Failure Date</label>
                            <div class="input-group">
                            	{{adjudicateClaim.failDate | date:'MM/dd/yyyy'}}
                            </div>
                          </div>
                          <div class="form-group col-xs-12 no-pad">
                            <label>Reported On</label>
                            <div class="input-group">
                            	{{adjudicateClaim.reportDate | date:'MM/dd/yyyy'}}
                            </div>
                          </div>
                          <div class="form-group col-xs-12 no-pad">
                            <label>Work Order Number</label>
                            {{adjudicateClaim.workOrder}}
                          </div>
                          <div class="form-group col-xs-12 no-pad">
                            <label>Hours @ Breakdown</label>
                            {{adjudicateClaim.hoursBreakDown}}
                          </div>
                          <div class="form-group col-xs-12 no-pad">
                            <label>Requested Other Charge 1 ($)</label>
                            {{adjustments.requestedOtherCharges1 | number : 2}}
                            <!-- <input type="text" class="form-control" ng-model="adjustments.requestedOtherCharges1" required="required" ng-readonly= "{{editFlag}}" ng-change="calcOnChange()"> -->
                          </div>
                          <div class="form-group col-xs-12 no-pad">
                            <label>Requested Other Charge 2 ($)</label>
                            {{adjustments.requestedOtherCharges2 | number : 2}}
                            <!-- <input type="text" class="form-control" ng-model="adjustments.requestedOtherCharges2" required="required" ng-readonly= "{{editFlag}}" ng-change="calcOnChange()"> -->
                          </div>
                          <div class="form-group col-xs-12 no-pad">
                            <label>Total Claim ($)</label>
                            {{adjustments.totalClaimCost | number : 2}}
                          </div>
                        </div>

						


                        <div class="col-md-6 no-pad">
                        <span class="ag-tab-title col-xs-12 no-pad marg10-bottom  pad20-left" style="margin-left:-10px !important;">Details</span>
                        <div class="col-xs-12 no-pad pad10-left border-left">
                         <br clear="all">
                        
                        <div class="col-xs-12 no-pad">
                          <div class="form-group col-xs-12 no-pad">
							<label>Dealer</label>
							{{(adjudicateClaim.dealerDO.name != null)?adjudicateClaim.dealerDO.name:"&nbsp;"}}
						  </div>
						  <div class="form-group col-xs-12 no-pad">
							<label>Dealer Email</label>
							{{(adjudicateClaim.dealerDO.invoiceEmail != null)?adjudicateClaim.dealerDO.invoiceEmail:"&nbsp;"}}
						  </div>
                          <div class="form-group col-xs-12 no-pad">
                            <label>Dealer Contact</label>
                            {{(adjudicateClaim.createdUser != null)?adjudicateClaim.createdUser:"&nbsp;"}}
                          </div>
                          <div class="form-group col-xs-12 no-pad">
                            <label>Customer Complaint</label>
                            {{adjudicateClaim.custComplaint}}
                          </div>
                          <div class="form-group col-xs-12 no-pad">
                            <label>Cause of Failure</label>
                            {{adjudicateClaim.causeFail}}
                          </div>
                          <div class="form-group col-xs-12 no-pad">
                            <label>Corrective Action</label>
                            {{adjudicateClaim.correctiveAction}}
                          </div>
                          <div class="form-group col-xs-12 no-pad">
                            <label>Adjusted Other Charge 1 ($)</label>
                            <input type="text" class="form-control" ng-model="adjustments.approvedOtherCharges1" required="required" ng-readonly= "{{editFlag}}" ng-change="calcOnChange()">
                          </div>
                          <div class="form-group col-xs-12 no-pad">
                            <label>Adjusted Other Charge 2 ($)</label>
                            <input type="text" class="form-control" ng-model="adjustments.approvedOtherCharges2" required="required" ng-readonly= "{{editFlag}}" ng-change="calcOnChange()">
                          </div>
                        </div>
                      </div>
                      </div>
                      </div>

                      <div class="col-xs-12 no-pad pad10-top">
                         <span class="ag-tab-title col-xs-12 no-pad marg10-bottom">Claim Details</span>
                         <br clear="all">
                        
                         <br>
                          <div class="col-sm-12">
                          <div class="col-xs-12 no-pad table-responsive clearfix">
                            <table class="table table-bordered">
                              <thead>
                                <tr>
                                  <th class="col-sm-2">Part #</th>
                                  <th class="col-sm-2">Description</th>
                                  <th class="col-sm-1">Quantity</th>
                                  <th class="col-sm-2">Adj Quantity</th>
                                  <th class="col-sm-1">Unit Price</th>
                                  <th class="col-sm-2">Adj Unit Price</th>
                                  <th class="t-r col-sm-1">Part Total</th>
                                  <th class="t-r col-sm-1">Adj Part Total</th>
                                </tr>
                              </thead>
                               <tbody data-ng-repeat="claimPartVO in adjustments.parts">
                                <tr>
                                  <td>{{claimPartVO.partNo}}<!-- <input type="text" class="form-control" name="" ng-model="claimPartVO.partNo" ng-readonly=true> --></td>
                                  <td>{{claimPartVO.partDescr}}<!-- <input type="text" class="form-control" name="" ng-model="claimPartVO.partDescr" ng-readonly=true> --></td>
                                  <td>{{claimPartVO.qty}}<!-- <input type="number" class="form-control" name="" ng-model="claimPartVO.qty" required="required" ng-readonly= "true" ng-change="calcOnChange()"> --></td>
                                  <td><input type="number" step="0.01" class="form-control" name="" ng-model="claimPartVO.adjQty" required="required" ng-change="calcOnChange()"></td>
                                  <td>{{claimPartVO.unitPrice | number : 2}}<!-- <input type="number" class="form-control" name="" ng-model="claimPartVO.unitPrice" required="required" ng-readonly= "true" ng-change="calcOnChange()"> --></td>
                                  <td><input type="number" step="0.01" class="form-control" name="" ng-model="claimPartVO.adjUnitPrice" required="required" ng-change="calcOnChange()"></td>
                                  <td class="t-r">{{claimPartVO.partsTotal | currency}}</td>
                                  <td class="t-r">{{claimPartVO.partsAdjTotal | currency}}</td>
                                </tr>
                              </tbody>
                            </table>
                            <div class="col-sm-12">
                            <div class="col-sm-6">
                            </div>
                             <div class="col-sm-6">
                            <div class="col-sm-8 no-pad">
                              Total Requested Parts Cost
                            </div>
                            <div class="col-sm-4 t-r">
                              {{adjudicateClaim.totalPartCost | currency}}
                            </div>
                            <div class="col-sm-8 no-pad">
                              Total Adjusted Parts Cost
                            </div>
                            <div class="col-sm-4 t-r">
                              {{adjustments.totalAdjustmentPartsCost | currency}}
                              <!-- <input type="text" class="form-control" style="text-align: right;" ng-model="adjustments.totalAdjustmentPartsCost" required="required" ng-readonly= "{{editFlag}}" ng-change="calcOnChange()"> -->
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
                                  <th class="col-sm-2">Adj Hours</th>
                                  <th class="col-sm-1">Hourly Rate</th>
                                  <th class="col-sm-2">Adj Hourly Rate</th>
                                  <th class="t-r col-sm-1">Total</th>
                                  <th class="t-r col-sm-1">Adj Total</th>
                                </tr>
                              </thead>
                              <tbody data-ng-repeat="claimLabourVO in adjustments.labors">
                                <tr>
                                  <td>{{claimLabourVO.laborNo}}<!-- <input type="text" class="form-control" name="" ng-model="claimLabourVO.laborNo" ng-readonly=true> --></td>
                                  <td>{{claimLabourVO.laborDescr}}<!-- <input type="text" class="form-control" name="" ng-model="claimLabourVO.laborDescr" ng-readonly=true> --></td>
                                  <td>{{claimLabourVO.laborHrs}}<!-- <input type="number" class="form-control" name="" ng-model="claimLabourVO.laborHrs" required="required" ng-readonly= "true" ng-change="calcOnChange()"> --></td>
                                  <td><input type="number" step="0.01" class="form-control" name="" ng-model="claimLabourVO.adjLaborHrs" required="required" ng-change="calcOnChange()"></td>
                                  <td>{{claimLabourVO.rate | number : 2}}<!-- <input type="number" class="form-control" name="" ng-model="claimLabourVO.rate" required="required" ng-readonly= "true" ng-change="calcOnChange()"> --></td>
                                  <td><input type="number" step="0.01" class="form-control" name="" ng-model="claimLabourVO.adjRate" required="required" ng-change="calcOnChange()"></td>
                                  <td class="t-r">{{claimLabourVO.laborsTotal | currency}}</td>
                                  <td class="t-r">{{claimLabourVO.laborsAdjTotal | currency}}</td>
                                </tr>
                              </tbody>
                            </table>
                            <div class="col-sm-12">
                            <div class="col-sm-6">
                            </div>
                            <div class="col-sm-6">
                            <div class="col-sm-8 no-pad">
                              Total Requested Labor Cost
                            </div>
                            <div class="col-sm-4 t-r">
                              {{adjudicateClaim.totalLaborCost | currency}}
                            </div>
                            <div class="col-sm-8 no-pad">
                              Total Adjusted Labor Cost
                            </div>
                            <div class="col-sm-4 t-r">
                            	{{adjustments.totalAdjustmentLaborsCost | currency}}
                            	<!-- <input type="text" style="text-align: right;" class="form-control" ng-model="adjustments.totalAdjustmentLaborsCost" required="required" ng-readonly= "{{editFlag}}" ng-change="calcOnChange()"> -->
                            </div>
                            <div class="col-sm-8 no-pad">
                              Total Requested Claim Cost
                            </div>
                            <div class="col-sm-4 t-r">
                                  {{adjustments.totalClaimCost | currency}}                         
                            </div>
                            <div class="col-sm-8 no-pad">
                              Total Adjusted Claim Cost
                            </div>
                            <div class="col-sm-4 t-r">
                                  {{adjustments.totalAdjustedClaimCost | currency}}                         
                            </div>
                            <div class="col-sm-8 no-pad">
                              Total Re-imbursed Amount
                            </div>
                            <div class="col-sm-4 t-r">
                                  {{adjustments.tra | currency}}                         
                            </div>
                            <div class="col-sm-8 no-pad">
                              Total Amount owed by the Customer
                            </div>
                            <div class="col-sm-4 t-r">
                                  {{adjustments.customerOwes | currency}}                         
                            </div>  
                            </div>
                              
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
										<th class="col-sm-1">
											<button type="button" class="btn btn-primary btn-sm" ng-click="addCheck()">
											<i class="fa fa-plus"></i>
									</button>
										</th>
									</tr>
								</thead>
								<tbody data-ng-repeat="checkDO in adjustments.checkDOList">
									<tr>
										<td><input type="text" class="form-control" name="checkNo" ng-model="checkDO.checkNo" required="required"></td>
										<td>
											<div class="agf1 input-group">
												<input type="text" class="form-control"
												   name="receivedDate" 
								                   datepicker-popup="MM/dd/yyyy"
								                   datepicker-options="dateOptions" 
								                   is-open="checkDO.chkDatePickerIsOpen" 
								                   ng-click="checkDO.chkDatePickerIsOpen=true"
								                   ng-model="checkDO.receivedDate"/>
									            <span class="input-group-btn">
									              <button type="button" class="btn btn-default" 
									                      ng-click="chkDatePickerOpen($event, checkDO)">
									                <i class="glyphicon glyphicon-calendar"></i>
									              </button>
									            </span>
								            </div>
										</td>
										<td><input type="number" step="0.01" class="form-control" name="amount" ng-model="checkDO.amount" ng-change="calcCheckAmtTotal()" required="required"></td>
										<td>
											<button ng-if="adjustments.checkDOList.length > 1" type="button" class="btn btn-primary btn-sm" ng-click="removeCheck(checkDO);">
												<i class="fa fa-minus"></i>
											</button>
										</td>
									</tr>
								</tbody>
							</table>
							<div class="col-sm-12">
								<div class="col-sm-6"></div>
								<div class="col-sm-6">
									<div class="col-sm-6 no-pad">Total Check Amount</div>
									<div class="col-sm-6">
										{{adjustments.totalCheckAmount | currency}}
									</div>
								</div>
							</div>
                           <!-- <div class="form-group clearfix col-xs-12 no-pad">
                              <span class="col-sm-3 no-pad">Check#  :</span> 
                                <div class="col-sm-6 no-pad">
                               		<input type="text" class="form-control" ng-model="adjustments.cheqNo" ng-required="cheqFlag" ng-readonly= "{{editFlag}}">
                                </div>
                            </div>
                            <div class="form-group clearfix col-xs-12 no-pad">
                              <span class="col-sm-3 no-pad">Paid Date  :</span> 
                                <div class="col-sm-3 input-group no-pad">
                               		<input type="text" class="form-control" 
					                   datepicker-popup="MM/dd/yyyy"
					                   datepicker-options="dateOptions" 
					                   is-open="paidDatePickerIsOpen" 
					                   ng-click="paidDatePickerOpen()"
					                   ng-model="adjustments.paidDate" 
					                   ng-required="cheqFlag"/>
										<span class="input-group-btn">
							              <button type="button" class="btn btn-default" 
							                      ng-click="paidDatePickerOpen($event)">
							                <i class="glyphicon glyphicon-calendar"></i>
							              </button>
							            </span>
                                </div>
                            </div> -->
                          </div>
                          <div class="col-xs-12 no-pad pad10-top" ng-if="!editFlag">
	                         <span class="ag-tab-title col-xs-12 no-pad marg10-bottom">File Attachments</span>
	                         <br clear="all">
	                         <p>Please upload pdf version of the Work Order and/or pictures</p>
	                         <br>
	                          <div class="col-sm-12">
	                          <div class="col-sm-6">
	                            <div class="form-group">
	                            <label>File</label>
	                            <input type="file" id="" name="files" multiple ng-files="getTheFiles($files)">
	                            </div>
	                          </div>
	                          </div>
	                          <div class="col-xs-12 no-pad pad10-top" ng-if="adjudicateClaim.claimFileDO">
								<span class="ag-tab-title col-xs-12 no-pad marg10-bottom">Uploaded Documents</span>
				                    <!-- <br clear="all">
				                      
				                    <br> -->
				                    <div class="col-sm-12">
										<table>
											<tbody data-ng-repeat="ufile in adjudicateClaim.claimFileDO">
												<tr><td><a href="/agg/claim/file/{{adjudicateClaim.claimId}}?filename={{ufile.fileName}}" target="_blank">{{ufile.fileName}}</a></td></tr>
											</tbody>
										</table>
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
                          <br>
                          <!-- 
                          <div class="col-sm-12">
                           <span class="ag-tab-title col-xs-12 no-pad marg10-bottom">Claim Comments</span>
                           <br clear="all">
                            <div class="form-group clearfix">
                              <span class="col-sm-3 no-pad">External Comments :</span> 
                                <div class="col-sm-9 no-pad border">
                                  <textarea class="form-control" style="width:100% !important;" rows="3" ng-model="adjustments.extComment" ng-required="commentFlag" ng-trim=true></textarea>
                                </div>
                            </div>
                          </div>
                          -->
                          
                          
                           <br clear="all">
				   <div class="col-xs-12 no-pad pad10-top">
                         <span class="ag-tab-title col-xs-12 no-pad marg10-bottom">External Comments</span>
                          <div class="col-sm-12">
                            <div class="form-group clearfix">
                                <div class="col-sm-9 no-pad border" ng-repeat="claimNote in adjustments.claimsNoteList">
                                  <!-- <textarea class="form-control" style="width:100% !important;" rows="3" ng-model="claim.extComment" ng-required="extCommentFlag" ng-trim=true></textarea>-->
                                  {{claimNote.lastUpdate |  date:"dd-MMM-yyyy"}} - {{claimNote.updatedBy}}: {{claimNote.notes}}
                                </div>
                            </div>
                            <span class="col-sm-3 no-pad"><b>Enter Comments :</b></span>
                            <div class="col-sm-9 no-pad border">
                                  <textarea class="form-control" style="width:100% !important;" rows="3" ng-model="adjustments.extComment" ng-required="commentFlag" ng-trim=true></textarea>
                             </div>
                          </div>
                        </div>
                          
                          
                          </div>
                      </div>
                </div>
				<div class="col-sm-12 no-pad t-c marg10-top marg10-bottom">
					<button type="submit" class="btn btn-primary" ng-click="onClickUpdate()" ng-if="editFlag">Update Claim</button>
					<button type="submit" class="btn btn-primary" ng-click="onClickCancel()" ng-if="editFlag">Cancel Claim</button>
		        	<button type="submit" class="btn btn-primary" ng-click="onClickClose()">Approve</button>
		        	<button type="submit" class="btn btn-primary" ng-click="onClickReject()">Reject</button>
		        	<button type="submit" class="btn btn-primary" ng-click="onClickApprovedForPayment()">Approved for Payment</button>
	            </div>
	            </form>
				</div>
			
			<!-- end data table section -->
			<!-- The actual modal template, just a bit o bootstrap -->
     <script type="text/ng-template" id="modal.html">
         <div class="modal fade">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" ng-click="close('No')" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title"></h4>
              </div>
              <div class="modal-body">
                <p>You are about to pay the dealer claim {{adjudicateClaim.claimId}}, an amount of {{adjustments.tra}}. This action cannot be reversed. Are you sure you want to continue?</p>
              </div>
              <div class="modal-footer">
                <button type="button" ng-click="close('No')" class="btn btn-default" data-dismiss="modal">Cancel</button>
                <button type="button" ng-click="close('Yes')" class="btn btn-primary" data-dismiss="modal">Confirm</button>
              </div>
            </div>
          </div>
        </div>
     </script>
                
			</article>
	<!-- /Article -->
</div>