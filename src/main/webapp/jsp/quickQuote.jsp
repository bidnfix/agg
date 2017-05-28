<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Article main content -->
<%@include file="contractCreatePopup.jsp" %>
<article class="col-md-9 maincontent">
	<form class="form-horizontal" name="quoteInfoForm" id="quoteInfoForm" novalidate angular-validator>
	<header class="page-header">
          <div class="col-md-6 col-sm-12">
			<div class="sec-title">
                     
				<h3 class="wow animated bounceInLeft">Quote</h3>
				<p class="wow animated bounceInRight">Quote #: <b>{{quote.quoteId}}</b> &nbsp;&nbsp;&nbsp; Status: <b>{{quote.statusDesc}}</b></p>
			</div>
          </div>
          <div class="col-md-6 col-sm-12">
          	<div ng-if="quote.status != 6 && quote.isArchive == 0">
          		<c:choose>
          			<c:when test="${user.roleDO.accountType eq 'admin'}">
          				<button class="btn btn-primary pull-right mar-right btn-sm" ng-click="editQuote()">Edit</button>
			          	<button class="btn btn-primary pull-right mar-right btn-sm" ng-click="quoteInfoForm.$valid && updateQuote(quoteInfoForm)">Update</button>
			          	<button class="btn btn-primary pull-right mar-right btn-sm" ng-click="archiveQuote()">Archive</button>
          			</c:when>
          			<c:otherwise>
          				<div ng-hide="estPriceFlag">
          					<button class="btn btn-primary pull-right mar-right btn-sm" ng-click="editQuote()">Edit</button>
				          	<button class="btn btn-primary pull-right mar-right btn-sm" ng-click="quoteInfoForm.$valid && updateQuote(quoteInfoForm)">Update</button>
				          	<button class="btn btn-primary pull-right mar-right btn-sm" ng-click="archiveQuote()">Archive</button>
          				</div>
          			</c:otherwise>
          		</c:choose>
	          	
	          	<c:if test="${user.roleDO.accountType eq 'admin'}">
	           	<button class="btn btn-primary pull-right mar-right btn-sm" ng-click="quoteInfoForm.$valid && invoiceQuote(quoteInfoForm)" ng-disabled="purchaseRequested">Invoice</button>
	           	<button class="btn btn-primary pull-right mar-right btn-sm" ng-click="quoteInfoForm.$valid && createContract(quoteInfoForm)" ng-disabled="invoiced">Create Contract</button>
	          	</c:if>
          	</div>
          	<div ng-if="quote.status != 6 && quote.isArchive == 1">
          		<c:if test="${user.roleDO.accountType eq 'admin'}">
          			<button class="btn btn-primary pull-right mar-right btn-sm" ng-click="unArchiveQuote()">Un-Archive</button>
          		</c:if>
          	</div>
          	<div>
          		<button class="btn btn-primary pull-right mar-right btn-sm" ng-click="cancelQuote()">Cancel</button>
          	</div>
          </div>
	</header>
             
             
	 <!-- data table section -->
             
             <div class="inner-main">
                   <div class="col-xs-12 agf1 main-login pad10-top">
                   	  <div id="quoteSuccessMsg" class="alert alert-info text-center hidden"></div>
                     <div class="col-md-6 no-pad pad10-right" ng-hide="editableFlag">
                       <div class="form-group">
                         <label>Assign Dealer</label>
                         	<div ng-if="quote.program == null">
						        <select class="form-control" name="dealer" ng-model="quote.dealerDO" id="dealer" ng-options="dealer.name+' - '+dealer.city for dealer in dealerList track by dealer.id" ng-change="displayDealerText(quote.dealerDO)"  validate-on="dirty" required="required" ng-disabled="disabled">
									<option value="">Select Dealer</option>
								</select>
					   		</div>
					   		<div  ng-if="quote.program != null">
					        	<p>{{quote.dealerDO.name}}{{(quote.dealerDO.city != null)?' - '+quote.dealerDO.city:""}}</p>
					        </div>
						  
                       </div>
                       <div class="form-group">
                         <label>Manufacturer</label>
                         	<div ng-if="quote.program == null">
					         <select class="form-control" name="manufacturer" ng-model="quote.manufacturerDO" ng-options="manufacturerObj.name for manufacturerObj in manufacturerList track by manufacturerObj.id" ng-change="getMachineModel(quote.manufacturerDO)"  validate-on="dirty" required="required" ng-disabled="disabled">
								<option value="">Select Manufacturer</option>
		     				 </select>
					   		</div>
					   		<div ng-if="quote.program != null">
					        <p>{{quote.manufacturerDO.name}}</p>
						    </div>
                       </div>
                       <div class="form-group"  ng-if="quote.program == null">
                         <label>Machine Type</label>
                         <p>
                           {{quote.machineInfoDO.machineType}}
                         </p>
                       </div>
                       <div class="form-group">
                         <label>Model</label>
                         <div ng-if="quote.program == null">
					        <select class="form-control" name="machineModel" ng-model="quote.machineInfoDO" ng-options="machineModel.model group by machineModel.machineType for machineModel in machineModelList track by machineModel.machineId" ng-change="getCoverageDetails(quote.machineInfoDO)"  validate-on="dirty" required="required" ng-disabled="disabled">
								<option value="">Select Model</option>
							</select>
						 </div>
						 <div ng-if="quote.program != null">
						    <p>{{quote.machineInfoDO.model}}</p>
						 </div>
                       </div>
                       <div class="form-group">
                         <label>Horsepower</label>
                         <input type="number" id="horsePower" name="horsePower" ng-model="quote.horsePower" placeholder="Horse Power" class="form-control" ng-disabled="disabled">
                       </div>
                       <div class="form-group">
                         <label>Serial Number</label>
                         <input type="text" id="serialNumber" name="serialNumber" ng-model="quote.serialNumber" placeholder="Serial Number" class="form-control"  validate-on="dirty" ng-required="mandatoryFlag" ng-disabled="disabled">
                       </div>
                       <div class="form-group">
                         <label>Retail Price</label>
                         <input type="number" id="retailPrice" name="retailPrice" ng-model="quote.retailPrice" class="form-control" placeholder="Retail Price" ng-disabled="disabled">
                       </div>
                       <div class="form-group">
                         <label>Meter Hours</label>
                         <input type="number" id="meterHours" name="meterHours" ng-model="quote.meterHours" placeholder="Meter Hours" class="form-control"  validate-on="dirty" required="required" ng-blur="changeExpirationDate()" ng-disabled="disabled">
                       </div>
                       <div class="form-group">
                         <label>Model Year</label>
                         <input type="number" id="modelYear" name="modelYear" ng-model="quote.modelYear" placeholder="Model Year" class="form-control" ng-disabled="disabled">
                       </div>
                       <div class="form-group">
                         <label>Condition</label>
                         <p>{{machineCondition}}</p>
                       </div>
                       <div class="form-group">
                         <label>Use of Equipment</label>
                         <select name="equipment" ng-model="quote.useOfEquipmentDO" class="form-control" ng-options="equipmentObj.equipName for equipmentObj in useOfEquipmentDOList track by equipmentObj.id"  validate-on="dirty" required="required" ng-disabled="disabled">
							<option value="">Use of Equipment</option>
						 </select>
                       </div>
                       <div class="form-group">
                         <label>MFG End Date</label>
                         <!-- <input type="date" id="coverageEndDate" name="coverageEndDate" ng-model="quote.coverageEndDate" class="form-control" ng-disabled="disabled"> -->
                           <!-- <input type="text" class="form-control" aria-describedby="basic-addon2"> -->
                           <!-- <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span> -->
                           <div class="input-group">
	                           <input type="text" class="form-control"
	                           		   name="coverageEndDate" 
					                   datepicker-popup="MM/dd/yyyy"
					                   datepicker-options="dateOptions" 
					                   is-open="valuationDatePickerIsOpen" 
					                   ng-click="valuationDatePickerOpen()"
					                   ng-change="changeExpirationDate()"
					                   ng-model="quote.coverageEndDate" 
					                   ng-required="expirationFlag"/>
								<span class="input-group-btn">
					              <button type="button" class="btn btn-default" 
					                      ng-click="valuationDatePickerOpen($event)">
					                <i class="glyphicon glyphicon-calendar"></i>
					              </button>
					            </span>
					      </div>
                       </div>
                       <div class="checkbox" ng-if="quote.program == null">
                         <label>
                           <input type="checkbox" id="coverageExpired" name="coverageExpired" ng-model="quote.coverageExpired" ng-disabled="disabled" ng-change="getCoverageDetails(quote.machineInfoDO)"> Check here if the Manufacturer's Coverage has expired.
                         </label>
                       </div>
                      <!--  <div class="checkbox">
                         <label>
                           <input type="checkbox" id="coverageEndDateUnknown" name="coverageEndDateUnknown" ng-model="quote.coverageEndDateUnknown" ng-disabled="disabled">Check if Manufacturer's End Date unknown.
                         </label>
                       </div> -->
                       <div class="form-group">
                         <label>Deductible</label>
                         <div ng-if="quote.program == null">
						     <select name="deductiblePrice" ng-model="quote.deductiblePrice" class="form-control" ng-options="deductibleAmt for deductibleAmt in deductibleAmtList track by deductibleAmt"  validate-on="dirty" required="required" ng-disabled="disabled" ng-change="getCoveragePriceLevels('deductible')">
	                         	<option value="">Select Deductible</option>
							 </select>
						 </div>
						 <div ng-if="quote.program != null">
						     <p>{{quote.deductiblePrice | currency:"$":0}}</p>
						 </div>
                       </div>
                       <div class="form-group">
                         <label>Coverage Term</label>
                         <div ng-if="quote.program == null">
						     <select name="coverageTerm" ng-model="quote.coverageTerm" class="form-control" ng-options="coverageTermVal for coverageTermVal in coverageTermList track by coverageTermVal"  validate-on="dirty" required="required" ng-disabled="disabled" ng-change="getCoveragePriceLevels('coverageTerm'); changeExpirationDate()">
	                         	<option value="">Select Coverage Term</option>
							 </select>
						</div>
						<div ng-if="quote.program != null">
						     <p>{{(quote.coverageTerm != null)? quote.coverageTerm+"&nbsp;mos.":""}}</p>
						</div>
                       </div>
                       <div class="form-group">
                         <label>Covered Hours</label>
                         <div ng-if="quote.program == null">
						     <select name="coverageHours" ng-model="quote.coverageHours" class="form-control" ng-options="coverageLevelHour for coverageLevelHour in coverageLevelHoursList track by coverageLevelHour"  validate-on="dirty" required="required" ng-disabled="disabled" ng-change="getCoveragePriceLevels('coverageHours'); changeExpirationDate()">
	                         	<option value="">Select Covered Hours</option>
							 </select>
						</div>
						<div ng-if="quote.program != null">
						     <p>{{quote.coverageHours}}</p>
						</div>
                       </div>
                       <div class="form-group">
                         <label>Coverage Type</label>
                         <div ng-if="quote.program == null">
						     <select name="coverageType" ng-model="quote.coverageType" class="form-control"  validate-on="dirty" required="required" ng-disabled="disabled" ng-change="changeQuoteBasePrice(quote.coverageType); changeExpirationDate()">
	                         	<option value="">Select Coverage Type</option>
	                         	<option ng-repeat="ctype in quote.coverageTypeSet" value="{{ctype}}" ng-selected="{{quote.coverageType == ctype}}">
	                         		{{(ctype == 'PH')?"Powertrain + Hydraulic":(ctype == 'PT')?"Powertrain":(ctype == 'PL')?"Powertrain + Hydraulic + Platform":""}}
	                         	</option>
	                         	<!-- <option value="PT">Powertrain</option>
				                <option value="PH">Powertrain + Hydraulic</option>
				                <option value="PL">Powertrain + Hydraulic + Platform</option> -->
							 </select>
						</div>
						<div ng-if="quote.program != null">
						        <p>{{(quote.coverageType == 'PH')?"Powertrain + Hydraulic":(quote.coverageType == 'PT')?"Powertrain":(quote.coverageType == 'PL')?"Powertrain + Hydraulic + Platform":""}}</p>
						</div>
                       </div>
                       <div class="form-group">
                         <label>Limit of Liability</label>
                         <p>{{quote.machineInfoDO.lolToDisplay | currency:"$":0}}</p>
                       </div>
                       <div class="form-group">
                         <label>Estimated Sale Date</label>
                           <!-- <input type="date" id="estSaleDate" name="estSaleDate" ng-model="quote.estSaleDate" class="form-control"  validate-on="dirty" required="required" ng-disabled="disabled"> -->
                           <!-- <input type="text" class="form-control" aria-describedby="basic-addon2"> -->
                           <!-- <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span> -->
                           <div class="input-group">
	                           <input type="text" class="form-control"
	                           		   name="estSaleDate" 
					                   datepicker-popup="MM/dd/yyyy"
					                   datepicker-options="dateOptions" 
					                   is-open="estSaleDatePickerIsOpen" 
					                   ng-click="estSaleDatePickerOpen()"
					                   ng-model="quote.estSaleDate" 
					                   required="required"/>
								<span class="input-group-btn">
					              <button type="button" class="btn btn-default" 
					                      ng-click="estSaleDatePickerOpen($event)">
					                <i class="glyphicon glyphicon-calendar"></i>
					              </button>
					            </span>
					       </div>
                       </div>
                       <div class="form-group">
                         <label>Additional Unit Information</label>
                         <textarea class="form-control" placeholder="" ng-model="quote.otherProv" ng-disabled="disabled"></textarea>
                         <span class="f-r"><small>If MFG's coverage is not 2 years/2000 hours, please describe.</small></span>
                       </div>
                       <div class="form-group">
                         <label>Dealer Markup</label>
                         <input type="number" id="dealerMarkup" name="dealerMarkup" ng-model="quote.dealerMarkup" placeholder="Dealer Markup" class="form-control"  validate-on="dirty" required="required" ng-disabled="disabled" ng-blur="getDealerMarkupPrice()">
                       </div>
                       <div class="form-group">
                         <label>Markup Type</label>
                         <div class="agform-radio">
                         <label class="radio-inline">
                           <input type="radio" id="dealerMarkupVlaue" name="dealerMarkupVlaue" ng-model="quote.dealerMarkupType" value="price" class="" ng-disabled="disabled" ng-change="getDealerMarkupPrice()"> Price
                         </label>
                         <label class="radio-inline">
                           <input type="radio" id="dealerMarkupVlaue" name="dealerMarkupVlaue" ng-model="quote.dealerMarkupType" value="percent" class="" ng-disabled="disabled" ng-change="getDealerMarkupPrice()">  Percent
                         </label>
                         </div>
                       </div>
                       <div class="form-group">
						<label>Group Assignment</label>
						<p>{{(quote.groupId > 0)?quote.groupId:"&nbsp;"}}</p>
					  </div>
					  <div class="form-group">
						<label>Special Considerations</label>
						<textarea class="form-control" placeholder="" ng-model="quote.specialConsiderations" ng-disabled="disabled"></textarea>
					  </div>
					  <div class="form-group">
						<label>Conditions for Coverage</label>
						<textarea class="form-control" placeholder="" ng-model="quote.condsForCoverage" ng-disabled="disabled"></textarea>
					  </div>
					  <div class="form-group">
						<label>Deal History</label>
						<textarea class="form-control" placeholder="" ng-model="quote.dealHistory" ng-disabled="disabled"></textarea>
					  </div>

                     </div>
                     
                     <div class="col-md-6 no-pad pad10-right" ng-hide="readOnlyFlag">
                          <div class="form-group">
                            <label>Assign Dealer</label>
                            <p>
                              {{quote.dealerDO.name}}{{(quote.dealerDO.city != null)?' - '+quote.dealerDO.city:""}}
                            </p>
                          </div>
                          <div class="form-group">
                            <label>Manufacturer</label>
                            <p>
                              {{quote.manufacturerDO.name}}
                            </p>
                          </div>
                          <div class="form-group">
                            <label>Machine Type</label>
                            <p>
                              {{quote.machineInfoDO.machineType}}
                            </p>
                          </div>
                          <div class="form-group">
                            <label>Model</label>
                            <p>
                              {{quote.machineInfoDO.model}}
                            </p>
                          </div>
                          <div class="form-group">
                            <label>Horsepower</label>
                            <p>
                              {{quote.horsePower}}
                            </p>
                          </div>
                          <div class="form-group">
                            <label>Serial Number</label>
                            <p>{{quote.serialNumber}}</p>
                          </div>
                          <div class="form-group">
                            <label>Retail Price</label>
                            <p>{{quote.retailPrice}}</p>
                          </div>
                          <div class="form-group">
                            <label>Meter Hours</label>
                            <p>{{quote.meterHours}}</p>
                          </div>
                          <div class="form-group">
                            <label>Model Year</label>
                            <p>{{quote.modelYear}}</p>
                          </div>
                          <div class="form-group">
                            <label>Condition</label>
                            <p>{{machineCondition}}</p>
                          </div>
                          <div class="form-group">
                            <label>Use of Equipment</label>
                            <p>{{quote.useOfEquipmentDO.equipName}}</p>
                          </div>
                          <div class="form-group">
                            <label>MFG End Date</label>
                            <p>{{quote.coverageEndDate |  date:"MM/dd/yyyy"}}</p>
                          </div>
                          <div class="checkbox" ng-if="quote.program == null">
                            <label>
                              <input type="checkbox" id="coverageExpired" name="coverageExpired"
									 ng-model="quote.coverageExpired" value="true" ng-disabled="true"> Check here if the Manufacturer's Coverage has expired.
                            </label>
                          </div>
                          <!-- <div class="checkbox">
	                         <label>
	                           <input type="checkbox" id="coverageEndDateUnknown" name="coverageEndDateUnknown" ng-model="quote.coverageEndDateUnknown" value="true" ng-disabled="true">Check if Manufacturer's End Date unknown.
	                         </label>
	                      </div> -->
                          <div class="form-group">
                            <label>Deductible</label>
                             <p>{{quote.deductiblePrice | currency:"$":0}}</p>
                          </div>
                          <div class="form-group">
                            <label>Coverage Term</label>
                            <p>{{(quote.coverageTerm != null)? quote.coverageTerm+"&nbsp;mos.":""}}</p>
                          </div>
                          <div class="form-group">
                            <label>Covered Hours</label>
                            <p>{{quote.coverageHours}}</p>
                          </div>
                          <div class="form-group">
                            <label>Coverage Type</label>
                            <p>{{(quote.coverageType == 'PH')?"Powertrain + Hydraulic":(quote.coverageType == 'PT')?"Powertrain":(quote.coverageType == 'PL')?"Powertrain + Hydraulic + Platform":""}}</p>
                          </div>
                          <div class="form-group">
                            <label>Limit of Liability</label>
                            <p>{{quote.machineInfoDO.lolToDisplay | currency:"$":0}}</p>
                          </div>
                          <div class="form-group">
                            <label>Estimated Sale Date</label>
                            <p>{{quote.estSaleDate |  date:"MM/dd/yyyy"}}</p>
                          </div>
                          <div class="form-group">
                            <label>Additional Unit Information</label>
                            <p>{{quote.otherProv}}</p>
                          </div>
                          <div class="form-group">
                            <label>Dealer Markup</label>
                            <p>{{quote.dealerMarkup}}</p>
                          </div>
                          <div class="form-group">
                            <label>Markup Type</label>
                            <div class="agform-radio">
                             <label class="radio-inline">
	                           <input type="radio" id="dealerMarkupVlauee" name="dealerMarkupVlauee" ng-model="quote.dealerMarkupTypee" value="price" class="" ng-disabled="true"> Price
	                         </label>
	                         <label class="radio-inline">
	                           <input type="radio" id="dealerMarkupVlauee" name="dealerMarkupVlauee" ng-model="quote.dealerMarkupTypee" value="percent" class="" ng-disabled="true">  Percent
	                         </label>
                            </div>
                          </div>
                          <div class="form-group">
							<label>Group Assignment</label>
							<p>{{(quote.groupId > 0)?quote.groupId:"&nbsp;"}}</p>
						  </div>
						  <div class="form-group">
							<label>Special Considerations</label>
							<p>{{quote.specialConsiderations}}</p>
						  </div>
						  <div class="form-group">
							<label>Conditions for Coverage</label>
							<p>{{quote.condsForCoverage}}</p>
						  </div>
						  <div class="form-group">
							<label>Deal History</label>
							<p>{{quote.dealHistory}}</p>
						  </div>
                        </div>

                     <div class="col-md-6 no-pad pad10-left border-left">
                     <div class="col-xs-12 border-bottom pad10">
                       <div class="col-xs-12 no-pad clearfix">
                         <div class="col-xs-6 no-pad">
                           <b>Quote Price:</b>
                         </div>
                         <div class="col-xs-6 no-pad">
                           <b>{{quote.quoteBasePrice | currency:"$":0}}</b>
                         </div>
                       </div>
                       <div class="col-xs-12 no-pad clearfix">
                         <div class="col-xs-6 no-pad">
                           Dealer Markup:
                         </div>
                         <div class="col-xs-6 no-pad">
                           {{quote.dealerMarkupPrice | currency:"$":0}}
                         </div>
                       </div>
                       <div class="col-xs-12 no-pad clearfix">
                         <div class="col-xs-6 no-pad">
                           Customer Price:
                         </div>
                         <div class="col-xs-6 no-pad">
                            {{(quote.dealerMarkupPrice + quote.quoteBasePrice) | currency:"$":0}}
                         </div>
                       </div>
                       <div class="col-xs-12 no-pad clearfix" ng-hide="invoiced">
                         <div class="col-xs-6 no-pad">
                           Dealer Invoice Summary:
                         </div>
                         <div class="col-xs-6 no-pad">
                            <button class="btn btn-primary btn-xs mar-right" ng-click="printQuote('invoice')">View</button>
                         </div>
                       </div>
                       <div class="col-xs-12 no-pad clearfix">
                         <div class="col-xs-6 no-pad">
                           Dealer Quote Summary:
                         </div>
                         <div class="col-xs-6 no-pad">
                            <button class="btn btn-primary btn-xs mar-right" ng-click="printQuote('dealer')">View</button>
                         </div>
                       </div>
                        <div class="col-xs-12 no-pad clearfix">
                         <div class="col-xs-6 no-pad">
                           Customer Quote Summary:
                         </div>
                         <div class="col-xs-6 no-pad">
                            <button class="btn btn-primary btn-xs mar-right" ng-click="printQuote('customer')">View</button>
                         </div>
                       </div>
                       </div>
                       <br clear="all">
                        <br clear="all">
                       <h3>Customer Information</h3>
                     <div class="col-xs-12 border-bottom no-pad">
                       <div class="form-group">
                         <label>Name/Nickname</label>
                         <input type="text" id="dealerName" name="dealerName" ng-model="quote.dealerName" placeholder="Customer Name" class="form-control"  validate-on="dirty" required="required" ng-disabled="disabled">
                       </div>
                       <div class="form-group">
                         <label>Address</label>
                         <input type="text" id="dealerAddress" name="dealerAddress" ng-model="quote.dealerAddress" placeholder="Customer Address" class="form-control" validate-on="dirty" ng-required="mandatoryFlag" ng-disabled="disabled">
                       </div>
                       <div class="form-group">
                         <label>City</label>
                         <input type="text" id="dealerCity" name="dealerCity" ng-model="quote.dealerCity" placeholder="Customer City" class="form-control" ng-disabled="disabled">
                       </div>
                       <div class="form-group">
                         <label>State/Province</label>
                         <select class="form-control" name="dealerState" ng-model="quote.dealerState" id="dealerState" validate-on="dirty" ng-required="mandatoryFlag"  ng-disabled="disabled">
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
                       <div class="form-group">
                         <label>Zip</label>
                         <input type="text" id="dealerZip" name="dealerZip" ng-model="quote.dealerZip" placeholder="Zip" class="form-control" validate-on="dirty" ng-required="mandatoryFlag" ng-disabled="disabled">
                       </div>
                       <div class="form-group">
                         <label>Phone Number</label>
                         <input type="text" id="dealerPhone" name="dealerPhone" ng-model="quote.dealerPhone" placeholder="Phone Number" class="form-control" validate-on="dirty" ng-disabled="disabled">
                       </div>
                       <div class="form-group">
                         <label>Email</label>
                         <input type="text" id="dealerEmail" name="dealerEmail" ng-model="quote.dealerEmail" placeholder="Email" class="form-control" validate-on="dirty" ng-disabled="disabled">
                       </div>
                       <div class="checkbox">
                         <label>
                           <input type="checkbox" id="custUnderstandCoverage" name="custUnderstandCoverage" ng-model="quote.custUnderstandCoverage" ng-value="true" ng-required="mandatoryFlag"  validate-on="dirty" ng-disabled="disabled"> Customer understands coverage.
                         </label>
                       </div>
                       <div class="checkbox">
                         <label>
                           <input type="checkbox" id="custRemorsePeriod" name="custRemorsePeriod" ng-model="quote.custRemorsePeriod" ng-value="true" ng-required="mandatoryFlag"  validate-on="dirty" ng-disabled="disabled"> Customer is aware of 90-day remorse period.
                         </label>
                       </div>
                     </div>
                     <div class="col-xs-12 pad10-top">
							 <div class="form-group">
								<label>Program</label>
								<p>{{(quote.program != null)?quote.program:"N/A"}}</p>
							  </div>
							  <div class="form-group">
								<label>Base Price</label>
								<p>{{quote.quoteBasePriceToDisplay | currency:"$":0}}</p>
							  </div>
							  <c:choose>
							  <c:when test="${user.roleDO.accountType eq 'admin'}">
								  <div class="form-group">
									<label>Admin Adjusted Price</label>
									 <input type="number" id="adjustedBasePrice" name="adjustedBasePrice" ng-model="quote.adjustedBasePrice" class="form-control" ng-blur="updateAdjustedPrice(quote.adjustedBasePrice)" ng-disabled="disabled">
								  </div>
								  <div class="form-group">
									<label>Admin Adjusted Coverage Term</label>
									 <input type="number" id="adjustedcoverageTerm" name="adjustedcoverageTerm" ng-model="quote.adjustedcoverageTerm" class="form-control" ng-disabled="disabled" ng-blur="changeExpirationDate()">
								  </div>
								  <div class="form-group">
									<label>Admin Adjusted Coverage Hours</label>
									 <input type="number" id="adjustedCoverageHours" name="adjustedCoverageHours" ng-model="quote.adjustedCoverageHours" class="form-control" ng-disabled="disabled" ng-blur="changeExpirationDate()">
								  </div>
								  <div class="form-group">
			                         <label>Admin Adjusted Coverage Type</label>
								     <select name="adjustedCoverageType" ng-model="quote.adjustedCoverageType" class="form-control"  ng-disabled="disabled">
			                         	<option value="">Select Coverage Type</option>
			                         	<option value="PT">Powertrain</option>
						                <option value="PH">Powertrain + Hydraulic</option>
						                <option value="PL">Powertrain + Hydraulic + Platform</option>
									 </select>
			                      </div>
								  <div class="form-group" ng-hide="!mandatoryFlag">
									<label>Anticipated Inception Date</label>
									<div class="input-group">
										<input type="text" class="form-control" 
						                   datepicker-popup="MM/dd/yyyy"
						                   datepicker-options="dateOptions" 
						                   is-open="inceptionDatePickerIsOpen" 
						                   ng-click="inceptionDatePickerOpen()"
						                   ng-model="quote.inceptionDate"
						                   ng-blur="calExpirationDate()" 
						                   ng-required="mandatoryFlag"
						                   ng-disabled="disabled"/>
							            <span class="input-group-btn">
							              <button type="button" class="btn btn-default" 
							                      ng-click="inceptionDatePickerOpen($event)" ng-disabled="disabled">
							                <i class="glyphicon glyphicon-calendar"></i>
							              </button>
							            </span>
						            </div>
								</div>
								<div class="form-group" ng-hide="!mandatoryFlag">
									<label>Anticipated Expiration Date</label>
									<div class="input-group">
										<input type="text" class="form-control" 
						                   datepicker-popup="MM/dd/yyyy"
						                   datepicker-options="dateOptions" 
						                   is-open="expirationDatePickerIsOpen" 
						                   ng-click="expirationDatePickerOpen()"
						                   ng-model="quote.expirationDate"
						                   ng-required="mandatoryFlag"
						                   ng-disabled="disabled"/>
							            <span class="input-group-btn">
							              <button type="button" class="btn btn-default" 
							                      ng-click="expirationDatePickerOpen($event)" ng-disabled="disabled">
							                <i class="glyphicon glyphicon-calendar"></i>
							              </button>
							            </span>
						            </div>
								</div>
								<div class="form-group" ng-hide="!mandatoryFlag">
									<label>Anticipated Expiration Hours</label>
									<input type="number" id="expirationHours" name="expirationHours" ng-model="quote.expirationHours" class="form-control" ng-required="mandatoryFlag" ng-disabled="disabled">
								</div>
							  </c:when>
							  <c:otherwise>
							  	<div ng-if="quote.status > 4">
							  	  <div class="form-group">
									<label>Admin Adjusted Price</label>
									<p>{{quote.adjustedBasePrice | currency:"$":0}}</p>
								  </div>
								  <div class="form-group">
									<label>Admin Adjusted Coverage Term</label>
									<p>{{quote.adjustedcoverageTerm}}</p>
								  </div>
								  <div class="form-group">
									<label>Admin Adjusted Coverage Hours</label>
									<p>{{quote.adjustedCoverageHours}}</p>
								  </div>
								  <div class="form-group">
			                         <label>Admin Adjusted Coverage Type</label>
			                         <p>{{(quote.adjustedCoverageType === 'PT')?"Powertrain":(quote.adjustedCoverageType === 'PH')?"Powertrain + Hydraulic":(quote.adjustedCoverageType === 'PL')?"Powertrain + Hydraulic + Platform":""}}</p>
			                      </div>
								  <div class="form-group" ng-hide="!mandatoryFlag">
									<label>Anticipated Inception Date</label>
									<p>{{quote.inceptionDate | date:'MM/dd/yyyy'}}</p>
								  </div>
								  <div class="form-group" ng-hide="!mandatoryFlag">
									<label>Anticipated Expiration Date</label>
									<p>{{quote.expirationDate | date:'MM/dd/yyyy'}}</p>
								  </div>
								  <div class="form-group" ng-hide="!mandatoryFlag">
									<label>Anticipated Expiration Hours</label>
									<p>{{quote.expirationHours}}</p>
								  </div>
								</div>  
							  </c:otherwise>
							  </c:choose>
							  <div class="form-group">
								<label>Limit of Liability</label>
								<p>{{quote.machineInfoDO.lolToDisplay | currency:"$":0}}</p>
							  </div>
							  <c:if test="${user.roleDO.accountType eq 'admin'}">
								  <div class="form-group">
									<label>Admin Adjusted LOL</label>
									 <input type="text" id="adjustedLol" name="adjustedLol" ng-model="quote.adjustedLol" class="form-control" value="{{quote.machineInfoDO.lol}}" ng-value="quote.machineInfoDO.lol" ng-disabled="disabled">
								  </div>
							  </c:if>
							  <div class="form-group">
								<label>Current Status</label>
								<c:choose>
								 	<c:when test="${user.roleDO.accountType eq 'admin'}">
								 		<div ng-hide="quote.status===6">
									        <select class="form-control" name="status" ng-model="quote.status" convert-to-number id="status"  validate-on="dirty" required="required" ng-disabled="disabled" ng-change="changeMandatoryFlg(quote.status)">
											  <option value="1">Estimating Price</option>
											  <option value="4">Purchase Requested</option>
											  <option value="5">Invoiced</option>
											  <!-- <option value="6">Closed</option> -->
											</select>
										</div>
										<div ng-hide="quote.status<6">
											<p>{{quote.statusDesc}}</p>
										</div>
								    </c:when>
								    <c:otherwise>
								        <div ng-hide="estPriceFlag">
									        <select class="form-control" name="status" ng-model="quote.status" convert-to-number id="status"  validate-on="dirty" required="required" ng-disabled="disabled" ng-change="changeMandatoryFlg(quote.status)">
											  <option value="1">Estimating Price</option>
											  <option value="4">Purchase Requested</option>
											</select>
										</div>
										<div ng-hide="!estPriceFlag">
											<p>{{quote.statusDesc}}</p>
										</div>
								    </c:otherwise>
								</c:choose>
							  </div>
							  <div class="form-group">
								<label>Last Update</label>
								<p>{{quote.lastUpdate | date:'MM/dd/yyyy'}}</p>
							  </div>
						</div>
                   </div>
             	</div>
             </div>
			</form>
<!-- end data table section -->
             
</article>
<!-- /Article -->