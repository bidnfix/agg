	<div ng-controller="ClaimsAdjudicateController" ng-init="showAdjudicateClaimList=true">
		<!-- Article main content -->
		<article class="col-md-9 maincontent" ng-if='showAdjudicateClaimList'>
			<header class="page-header">
	              	<div class="col-md-6 col-sm-12">
						<div class="sec-title">
							<h3 class="wow animated bounceInLeft">Adjudicate a Claim</h3>
						</div>
                    </div>
			</header>
			<div>
				<table id="preauthClaimsListTable" class="table table-striped table-bordered" cellspacing="0" width="100%">
					<thead>
			            <tr>
			            	<th>Claim ID</th>
			                <th>Dealer ID</th>
			                <th>Serial</th>
			            </tr>
			        </thead>
					<tbody>
			            <tr ng-repeat="claim in adjudicateClaimList" ng-click="onClickSelectClaim(claim)">
			                <td>{{claim.claimId}}</td>
			                <td>{{claim.dealerId}}</td>
			                <td>{{claim.serial}}</td>
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
                        
							<h2 class="wow animated bounceInLeft">Adjudicate a Claim</h2>
							<p class="wow animated bounceInRight">Contract #: {{adjudicateClaim.contractId}}</p>
						</div>
                        </div>
                        <div class="col-md-6 col-sm-12"><a class="btn btn-primary pull-right btn-sm mar-right" ng-click="onClickBackToList()">Back</a></div>
				</header>
                
                
				 <!-- data table section -->
                
                <div class="inner-main">
                     <form name="newClaimForm" ng-submit="onClickSubmitClaim()">   
                      <div class="col-xs-12 agf1 main-login pad10-top">
                        <div class="col-xs-12 no-pad clearfix">
                        <div class="col-md-6 no-pad pad10-right">
                         <span class="ag-tab-title col-xs-12 no-pad marg10-bottom">Claim Information</span>
                         <br clear="all">
                         <br>
                          <div class="form-group pad10-top">
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
                          <div class="form-group">
                            <label>Failure Date</label>
                            <div class="input-group">
                            	{{adjudicateClaim.failDate | date:'yyyy-MM-dd'}}
                            </div>
                          </div>
                          <div class="form-group">
                            <label>Reported On</label>
                            <div class="input-group">
                            	{{adjudicateClaim.reportDate | date:'yyyy-MM-dd'}}
                            </div>
                          </div>
                          <div class="form-group">
                            <label>Work Order Number</label>
                            {{adjudicateClaim.workOrder}}
                          </div>
                          <div class="form-group">
                            <label>Hours @ Breakdown</label>
                            {{adjudicateClaim.hoursBreakDown}}
                          </div>
                          <div class="form-group">
                            <label>Other Charge 1 ($)</label>
                            <input type="text" class="form-control" ng-model="adjustments.requestedOtherCharges1" required="required" ng-readonly= "{{editFlag}}" ng-change="calcOnChange()">
                          </div>
                          <div class="form-group">
                            <label>Other Charge 2 ($)</label>
                            <input type="text" class="form-control" ng-model="adjustments.requestedOtherCharges2" required="required" ng-readonly= "{{editFlag}}" ng-change="calcOnChange()">
                          </div>
                          <div class="form-group">
                            <label>Total Claim ($)</label>
                            {{adjustments.totalClaimCost}}
                          </div>
                        </div>

						


                        <div class="col-md-6 no-pad">
                        <span class="ag-tab-title col-xs-12 no-pad marg10-bottom  pad20-left" style="margin-left:-10px !important;">Details</span>
                        <div class="col-xs-12 no-pad pad10-left border-left">
                         <br clear="all">
                        
                        <div class="col-xs-12 no-pad">
                          <div class="form-group">
                            <label>Customer Complaint</label>
                            {{adjudicateClaim.custComplaint}}
                          </div>
                          <div class="form-group">
                            <label>Cause of Failure</label>
                            {{adjudicateClaim.causeFail}}
                          </div>
                          <div class="form-group">
                            <label>Corrective Action</label>
                            {{adjudicateClaim.correctiveAction}}
                          </div>
                        </div>
                      </div>
                      </div>
                      </div>
					<div class="col-xs-12 no-pad pad10-top" ng-if="!editFlag">
                         <span class="ag-tab-title col-xs-12 no-pad marg10-bottom">File Attachments</span>
                         <br clear="all">
                         <p>Please upload pdf versions of quotes and any pictures showing the damage.</p>
                         <br>
                          <div class="col-sm-12">
                          <div class="col-sm-6">
                            <div class="form-group">
                            <label>File</label>
                            <input type="file" id="" name="files" multiple ng-files="getTheFiles($files)">
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
                               <tbody data-ng-repeat="claimPartVO in adjustments.parts">
                                <tr>
                                  <td><input type="text" class="form-control" name="" ng-model="claimPartVO.partNo" ng-readonly=true></td>
                                  <td><input type="text" class="form-control" name="" ng-model="claimPartVO.partDescr" ng-readonly=true></td>
                                  <td><input type="number" class="form-control" name="" ng-model="claimPartVO.qty" required="required" ng-readonly= "{{editFlag}}" ng-change="calcOnChange()"></td>
                                  <td><input type="number" class="form-control" name="" ng-model="claimPartVO.unitPrice" required="required" ng-readonly= "{{editFlag}}" ng-change="calcOnChange()"></td>
                                  <td class="t-r">{{claimPartVO.partsTotal | currency}}</td>
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
                              Total Adjusted Labor Cost
                            </div>
                            <div class="col-sm-4 t-r">
                              {{adjustments.totalAdjustmentPartsCost | currency}}
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
                              <tbody data-ng-repeat="claimLabourVO in adjustments.labors">
                                <tr>
                                  <td><input type="text" class="form-control" name="" ng-model="claimLabourVO.laborNo" ng-readonly=true></td>
                                  <td><input type="text" class="form-control" name="" ng-model="claimLabourVO.laborDescr" ng-readonly=true></td>
                                  <td><input type="number" class="form-control" name="" ng-model="claimLabourVO.laborHrs" required="required" ng-readonly= "{{editFlag}}" ng-change="calcOnChange()"></td>
                                  <td><input type="number" class="form-control" name="" ng-model="claimLabourVO.rate" required="required" ng-readonly= "{{editFlag}}" ng-change="calcOnChange()"></td>
                                  <td class="t-r">{{claimLabourVO.laborsTotal | currency}}</td>
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
                            </div>  
                            </div>
                              
                            </div>
                          </div>
                          <br clear="all">
                          <br>
                          <div class="col-sm-12">
                           <!--  <div class="form-group clearfix">
                              <label>Customer Complaint</label>
                              <p>{{preAuthClaim.custComplaint}}</p>
                            </div>
                            <div class="form-group clearfix">
                              <label>Corrective Actions</label>
                              <p>{{preAuthClaim.causeFail}}</p>
                            </div>
                            <div class="form-group clearfix">
                              <label>Cause of Failure</label>
                              <p>{{preAuthClaim.correctiveAction}}</p>
                            </div> -->

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
		        	<button type="submit" class="btn btn-primary" ng-click="onClickClose()"> Close Claim</button>
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