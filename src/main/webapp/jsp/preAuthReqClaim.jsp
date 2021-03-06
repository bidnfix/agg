	<div ng-init="showPreAuthClaimList=true">
		<!-- Article main content -->
		<article class="col-md-9 maincontent" ng-if='showPreAuthClaimList'>
			<header class="page-header">
	              	<div class="col-md-6 col-sm-12">
						<div class="sec-title">
							<h3 class="wow animated bounceInLeft">Pre-authorization Request Claims</h3>
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
			            <tr ng-repeat="claim in preAuthClaimList">
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
	<article class="col-md-9 maincontent" ng-if='!showPreAuthClaimList'>
				<header class="page-header">
                	<div class="col-md-6 col-sm-12">
					<div class="sec-title">
                        
							<h4 class="wow animated bounceInLeft">Pre-authorization Request Claims</h4>
							<div >
								Claim #: <b>{{preAuthClaim.claimId}}</b>
								<div style="display: inline-block;" ng-show="preAuthClaim.cStatus != null">
									&nbsp;&nbsp;&nbsp;Status: &nbsp;
									<b>
									{{(preAuthClaim.cStatus > 0)?((preAuthClaim.cStatus === 1)?"Open":(preAuthClaim.cStatus === 2)?"Pre-Auth Requested":(preAuthClaim.cStatus === 3)?"Submitted":
					                (preAuthClaim.cStatus === 4)?"Approved":(preAuthClaim.cStatus === 5)?"Pre-Auth Approved":(preAuthClaim.cStatus === 6)?"Pre-Auth Rejected":
					                (preAuthClaim.cStatus === 7)?"Pre-Auth Approved with Adjustments":(preAuthClaim.cStatus === 8)?"Pending":(preAuthClaim.cStatus === 9)?"Draft":
					                (preAuthClaim.cStatus === 10)?"Rejected":(preAuthClaim.cStatus === 11)?"Approved for Payment":""):""}}
					                </b>
								</div>
							</div>
						</div>
                        </div>
                        <div class="col-md-6 col-sm-12"><a class="btn btn-primary pull-right btn-sm mar-right" ng-click="onClickBackToList()">Back</a></div>
				</header>
                
                
				 <!-- data table section -->
                <form name="newClaimForm" ng-submit="onClickSubmitClaim()">
                <div class="inner-main">
                      <div class="col-xs-12 agf1 main-login pad10-top">
                      	<div id="claimErrMsg" class="alert alert-danger text-center hidden"></div>
                      	<div class="col-xs-12 no-pad clearfix">
							<div class="col-md-6 no-pad pad10-right">
								<span class="ag-tab-title col-xs-12 no-pad marg10-bottom">Contract
									Information</span> <br clear="all"> <br>
								<div class="form-group no-pad col-xs-12">
									<label>Contract ID</label>
									<p>{{preAuthClaim.contractDO.contractId}}</p>
								</div>
								<div class="form-group col-xs-12 no-pad">
									<label>Contract Expiration Date</label>
									<p>{{preAuthClaim.contractDO.expirationDate |  date:"MM/dd/yyyy"}}</p>
								</div>
								<div class="form-group col-xs-12 no-pad">
									<label>Limit of Liability (LOL)</label>
									<p>{{preAuthClaim.contractDO.lol | currency:"$":0}}</p>
								</div>
								<div class="form-group col-xs-12 no-pad">
									<label>Deductible</label>
									<p>{{preAuthClaim.contractDO.deductible | currency:"$":0}}</p>
								</div>
								<div class="form-group col-xs-12 no-pad">
		                            <label>Coverage Type</label>
		                            <p>{{(preAuthClaim.coverageType === 'PT')?"Powertrain":(preAuthClaim.coverageType === 'PH')?"Powertrain + Hydraulic":(preAuthClaim.coverageType === 'PL')?"Powertrain + Hydraulic + Platform":""}}</p>
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
											<p>{{preAuthClaim.contractDO.machineSerialNo}}</p>
										</div>
										<div class="form-group col-xs-12 no-pad">
											<label>Manufacturer</label>
											<p>{{preAuthClaim.manufacturer}}</p>
										</div>
										<div class="form-group col-xs-12 no-pad">
											<label>Model</label>
											<p>{{preAuthClaim.contractDO.machineModel}}</p>
										</div>
										<div class="form-group col-xs-12 no-pad">
											<label>Usage Hours covered</label>
											<p>{{preAuthClaim.contractDO.expirationUsageHours}}</p>
										</div>
										<div class="form-group col-xs-12 no-pad">
											<label>Available LOL</label>
											<p>{{preAuthClaim.contractDO.availabeLol | currency}}</p>
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
									<p>{{preAuthClaim.dealersName}}</p>
								</div>
								<div class="form-group col-xs-12 no-pad">
									<label>City</label> 
									<p>{{preAuthClaim.dealerCity}}</p>
								</div>
								<div class="form-group col-xs-12 no-pad">
									<label>Zip</label> 
									<p>{{preAuthClaim.dealerZip}}</p>
								</div>
								<div class="form-group col-xs-12 no-pad">
									<label>E-mail</label> 
									<p>{{preAuthClaim.dealerEmail}}</p>
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
											<p>{{preAuthClaim.dealerAddress}}</p>
										</div>
										<div class="form-group col-xs-12 no-pad">
											<label>State</label>
										<p>{{preAuthClaim.dealerState}}</p>
										</div>
										<div class="form-group col-xs-12 no-pad">
											<label>Phone</label>
										<p>{{preAuthClaim.dealerPhone}}</p>
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
                            <!-- <input type="text" class="form-control" ng-model="preAuthClaim.serial" ng-readonly=true> -->
                            {{preAuthClaim.serial}}
                          </div>
                          <div class="form-group col-xs-12 no-pad">
                            <label>Manufacturer</label>
                            <!-- <input type="text" class="form-control" ng-model="preAuthClaim.manufacturer" ng-readonly=true> -->
                            {{preAuthClaim.manufacturer}}
                          </div>
                          <div class="form-group col-xs-12 no-pad">
                            <label>Model Number</label>
                            <!-- <input type="text" class="form-control" ng-model="preAuthClaim.machineModel" ng-readonly=true> -->
                            {{preAuthClaim.machineModel}}
                          </div>
                          <!-- <div class="form-group col-xs-12 no-pad">
                            <label>Coverage Type</label>
                            <input type="text" class="form-control" ng-model="preAuthClaim.coverageType" ng-readonly=true>
                            {{preAuthClaim.coverageType}}
                          </div> -->
                          <div class="form-group col-xs-12 no-pad">
                            <label>Failure Date</label>
                            <!-- <div class="input-group">
                              <input type="date" class="form-control" aria-describedby="basic-addon2" ng-model="preAuthClaim.failDate" 
                              value="{{preAuthClaim.failDate | date:'yyyy-MM-dd'}}" ng-readonly=true>
                              <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
                            </div> -->
                            {{preAuthClaim.failDate | date:'yyyy-MM-dd'}}
                          </div>
                          <div class="form-group col-xs-12 no-pad">
                             <label>Reported On</label>
                            <!--<div class="input-group">
                              <input type="date" class="form-control" aria-describedby="basic-addon2" ng-model="preAuthClaim.reportDate" 
                              value="{{preAuthClaim.reportDate | date:'yyyy-MM-dd'}}" ng-readonly=true>
                              <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
                            </div> -->
                            {{preAuthClaim.reportDate | date:'yyyy-MM-dd'}}
                          </div>
                          <div class="form-group col-xs-12 no-pad">
                            <label>Work Order Number</label>
                            <!-- <input type="text" class="form-control" ng-model="preAuthClaim.workOrder" ng-readonly=true> -->
                            {{preAuthClaim.workOrder}}
                          </div>
                          <div class="form-group col-xs-12 no-pad">
                            <label>Hours @ Breakdown</label>
                            <!-- <input type="number" class="form-control" ng-model="preAuthClaim.hoursBreakDown" ng-readonly=true> -->
                            {{preAuthClaim.hoursBreakDown}}
                          </div>
                          <div class="form-group col-xs-12 no-pad">
                            <label>Other Charge 1 ($)</label>
                            <!-- <input type="number" class="form-control" ng-model="preAuthClaim.requestedOtherCharges1" ng-readonly=true> -->
                            {{preAuthClaim.requestedOtherCharges1 | number : 2}}
                          </div>
                          <div class="form-group col-xs-12 no-pad">
                            <label>Other Charge 2 ($)</label>
                            <!-- <input type="number" class="form-control" ng-model="preAuthClaim.requestedOtherCharges2" ng-readonly=true> -->
                            {{preAuthClaim.requestedOtherCharges2 | number : 2}}
                          </div>
                          <div class="form-group col-xs-12 no-pad">
                            <label>Total Claim ($)</label>
                            <!-- <input type="number" class="form-control" ng-model="preAuthClaim.totalClaimCost" ng-readonly=true> -->
                            {{preAuthClaim.totalClaimCost | number : 2}}
                          </div>
                        </div>



                        <div class="col-md-6 no-pad">
                        <span class="ag-tab-title col-xs-12 no-pad marg10-bottom  pad20-left" style="margin-left:-10px !important;">Details</span>
                        <div class="col-xs-12 no-pad pad10-left border-left">
                         <br clear="all">
                        
                        <div class="col-xs-12 no-pad">
                          <div class="form-group col-xs-12 no-pad">
							<label>Dealer</label>
							{{(preAuthClaim.dealerDO.name != null)?preAuthClaim.dealerDO.name:"&nbsp;"}}
						  </div>
						  <div class="form-group col-xs-12 no-pad">
							<label>Dealer Email</label>
							{{(preAuthClaim.dealerDO.invoiceEmail != null)?preAuthClaim.dealerDO.invoiceEmail:"&nbsp;"}}
						  </div>
                          <div class="form-group col-xs-12 no-pad">
                            <label>Dealer Contact</label>
                            {{(preAuthClaim.createdUser != null)?preAuthClaim.createdUser:"&nbsp;"}}
                          </div>
                          <div class="form-group col-xs-12 no-pad">
                            <label>Customer Complaint</label>
                            <!-- <textarea class="form-control" rows="2" ng-model="preAuthClaim.custComplaint" ng-readonly=true></textarea> -->
                            {{preAuthClaim.custComplaint}}
                          </div>
                          <div class="form-group col-xs-12 no-pad">
                            <label>Cause of Failure</label>
                            <!-- <textarea class="form-control" rows="5" ng-model="preAuthClaim.causeFail" ng-readonly=true></textarea> -->
                            {{preAuthClaim.causeFail}}
                          </div>
                          <div class="form-group col-xs-12 no-pad">
                            <label>Corrective Action</label>
                            <!-- <textarea class="form-control" rows="2" ng-model="preAuthClaim.correctiveAction" ng-readonly=true></textarea> -->
                            {{preAuthClaim.correctiveAction}}
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
                                  <th class="col-sm-4">Description</th>
                                  <th class="col-sm-1">Quantity</th>
                                  <th class="col-sm-2">Unit Price</th>
                                  <th class="t-r col-sm-2">Part Total</th>
                                </tr>
                              </thead>
                               <tbody data-ng-repeat="claimPartVO in preAuthClaim.claimPartDO">
                                <tr>
                                  <!-- <td><input type="text" class="form-control" name="" ng-model="claimPartVO.partNo" required="required"></td>
                                  <td><input type="text" class="form-control" name="" ng-model="claimPartVO.partDescr" required="required"></td>
                                  <td><input type="number" class="form-control" name="" ng-model="claimPartVO.qty" required="required"></td>
                                  <td><input type="number" class="form-control" name="" ng-model="claimPartVO.unitPrice" required="required"></td> -->
                                  <td>{{claimPartVO.partNo}}</td>
                                  <td>{{claimPartVO.partDescr}}</td>
                                  <td>{{claimPartVO.qty}}</td>
                                  <td>{{claimPartVO.unitPrice | number : 2}}</td>
                                  <td class="t-r">{{claimPartVO.partsTotal | currency}}</td>
                                </tr>
                              </tbody>
                            </table>
                            <div class="col-sm-12">
                            <div class="col-sm-6">
                              <!-- <button type="button" class="btn btn-primary btn-sm" ng-click="claim.claimPartVOList.push({})"><i class="fa fa-plus"></i> New Line </button> -->
                            </div>
                            <div class="col-sm-6">
                            <div class="col-sm-8 no-pad">
                              Total Requested Parts Cost
                            </div>
                            <div class="col-sm-4 t-r">
                              <!-- <input type="text" class="form-control" name="" ng-model="claim.partsTotalCost" ng-readonly=true> -->
                              {{preAuthClaim.totalPartCost | currency}}
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
                              <tbody data-ng-repeat="claimLabourVO in preAuthClaim.claimLaborDO">
                                <tr>
                                  <!-- <td><input type="text" class="form-control" name="" ng-model="claimLabourVO.laborNo" required="required"></td>
                                  <td><input type="text" class="form-control" name="" ng-model="claimLabourVO.laborDescr" required="required"></td>
                                  <td><input type="number" class="form-control" name="" ng-model="claimLabourVO.laborHrs" required="required"></td>
                                  <td><input type="number" class="form-control" name="" ng-model="claimLabourVO.rate" required="required"></td> -->
                                  <td>{{claimLabourVO.laborNo}}</td>
                                  <td>{{claimLabourVO.laborDescr}}</td>
                                  <td>{{claimLabourVO.laborHrs}}</td>
                                  <td>{{claimLabourVO.rate | number : 2}}</td>
                                  <td class="t-r">{{claimLabourVO.labourTotal | currency}}</td>
                                </tr>
                              </tbody>
                            </table>
                            <div class="col-sm-12">
                            <div class="col-sm-6"></div>
                            <div class="col-sm-6">
                            <div class="col-sm-8 no-pad">
                              Total Requested Labor Cost
                            </div>
                            <div class="col-sm-4 t-r">
                              <!-- <input type="text" class="form-control" name="" ng-model="claim.totalLaborCost" ng-readonly=true> -->
                              {{preAuthClaim.totalLaborCost | currency}}
                            </div>  
                            </div>
                              
                            </div>
                          </div>
                          <div class="col-xs-12 no-pad pad10-top" ng-if="preAuthClaim.claimFileDO">
							<span class="ag-tab-title col-xs-12 no-pad marg10-bottom">Uploaded Documents</span>
	                         <!-- <br clear="all">
	                        
	                         <br> -->
	                         <div class="col-sm-12">
								<table>
									<tbody data-ng-repeat="ufile in preAuthClaim.claimFileDO">
										<tr><td><a href="/agg/claim/file/{{preAuthClaim.claimId}}?filename={{ufile.fileName}}" target="_blank">{{ufile.fileName}}</a></td></tr>
									</tbody>
								</table>
								</div>
						  </div>	
						  <!-- 
                          <div class="col-xs-12 no-pad pad10-top">
                         <span class="ag-tab-title col-xs-12 no-pad marg10-bottom">Claim Comments</span>
                         <br clear="all">
                        
                         <br>
                         
                          <div class="col-sm-12">
                            <div class="form-group clearfix">
                              <span class="col-sm-3 no-pad">External Comments :</span> 
                                <div class="col-sm-9 no-pad border">
                                  <textarea class="form-control" style="width:100% !important;" rows="3" ng-model="preAuthClaim.extComment" ng-required="extCommentFlag" ng-trim=true></textarea>
                                </div>
                            </div>
                          </div>
                          -->
                          
                          
                   <br clear="all">
				   <div class="col-xs-12 no-pad pad10-top">
                         <span class="ag-tab-title col-xs-12 no-pad marg10-bottom">External Comments</span>
                          <div class="col-sm-12">
                            <div class="form-group clearfix">
                                <div class="col-sm-9 no-pad border" ng-repeat="claimNote in preAuthClaim.claimsNoteList">
                                  <!-- <textarea class="form-control" style="width:100% !important;" rows="3" ng-model="claim.extComment" ng-required="extCommentFlag" ng-trim=true></textarea>-->
                                  {{claimNote.lastUpdate |  date:"dd-MMM-yyyy"}} - {{claimNote.updatedBy}}: {{claimNote.notes}}
                                </div>
                            </div>
                            <span class="col-sm-3 no-pad"><b>Enter Comments :</b></span>
                            <div class="col-sm-9 no-pad border">
                                  <textarea class="form-control" style="width:100% !important;" rows="3" ng-model="preAuthClaim.extComment" ng-required="commentFlag" ng-trim=true></textarea>
                             </div>
                          </div>
                        </div>
                          
                          
                        </div>
                          </div>
                      </div>
                </div>
				<div class="col-sm-12 no-pad t-c marg10-top marg10-bottom">
		          <button type="button" class="btn btn-primary" ng-click="reqAuth('pre_authorized_approved')">Pre-auth Approved</button>
		          <button type="submit" class="btn btn-primary" ng-click="reqAuth('pre_authorized_approved_with_adjustments')">Pre-auth Approved with Adjustments</button>
		          <button type="submit" class="btn btn-primary" ng-click="reqAuth('pre_authorized_rejected')">Pre-auth Rejected</button>
	            </div>
	            </form>
				</div>
			
			<!-- end data table section -->
                
			</article>
	<!-- /Article -->
</div>