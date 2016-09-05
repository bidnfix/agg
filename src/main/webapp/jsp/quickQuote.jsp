<!-- Article main content -->
<%@include file="contractCreatePopup.jsp" %>
<article class="col-md-9 maincontent">
	<form class="form-horizontal" name="quoteInfoForm" id="quoteInfoForm" novalidate angular-validator>
	<header class="page-header">
             	<div class="col-md-6 col-sm-12">
		<div class="sec-title">
                     
				<h2 class="wow animated bounceInLeft">Quick Quote</h2>
				<p class="wow animated bounceInRight">Quote #: {{quote.quoteId}}</p>
			</div>
                     </div>
                     <div class="col-md-6 col-sm-12">
                     	<button class="btn btn-primary pull-right mar-right" ng-click="editQuote()">Edit</button>
                     	<button class="btn btn-primary pull-right mar-right" ng-click="quoteInfoForm.$valid && updateQuote(quoteInfoForm)">Update</button>
                     	<button class="btn btn-primary pull-right mar-right" ng-click="archiveQuote()">Archive</button>
                     	<button class="btn btn-primary pull-right mar-right" ng-click="quoteInfoForm.$valid && invoiceQuote(quoteInfoForm)" ng-disabled="purchaseRequested">Invoice</button>
                     	<button class="btn btn-primary pull-right mar-right" ng-click="printQuote('dealer')">Dealer Quote Summary</button>
                     	<button class="btn btn-primary pull-right mar-right" ng-click="printQuote('customer')">Customer Quote Summary</button>
                     	<button class="btn btn-primary pull-right mar-right" ng-click="quoteInfoForm.$valid && createContract(quoteInfoForm)" ng-disabled="invoiced">Create Contract</button>
                     </div>
	</header>
             
             
	 <!-- data table section -->
             
             <div class="inner-main">
                   
                   <div class="col-xs-12 agf1 main-login pad10-top">
                     <div class="col-md-6 no-pad pad10-right">
                       <div class="form-group">
                         <label>Assign Dealer</label>
                         <select class="form-control" name="dealer" ng-model="quote.dealerDO" id="dealer" ng-options="dealer.name for dealer in dealerList track by dealer.id" ng-change="displayDealerText(quote.dealerDO)"  validate-on="dirty" required="required" ng-disabled="disabled">
							<option value="">Select Dealer</option>
						 </select>
                       </div>
                       <div class="form-group">
                         <label>Manufacturer</label>
                         <select class="form-control" name="manufacturer" ng-model="quote.manufacturerDO" ng-options="manufacturerObj.name for manufacturerObj in manufacturerList track by manufacturerObj.id" ng-change="getMachineModel(quote.manufacturerDO)"  validate-on="dirty" required="required" ng-disabled="disabled">
							<option value="">Select Manufacturer</option>
	     				 </select> 
                       </div>
                       <div class="form-group">
                         <label>Model</label>
                         <select class="form-control" name="machineModel" ng-model="quote.machineInfoDO" ng-options="machineModel.model group by machineModel.machineType for machineModel in machineModelList track by machineModel.machineId" ng-change="getCoverageDetails(quote.machineInfoDO)"  validate-on="dirty" required="required" ng-disabled="disabled">
							<option value="">Select Model</option>
						</select>
                       </div>
                       <div class="form-group">
                         <label>Horsepower</label>
                         <input type="text" id="horsePower" name="horsePower" ng-model="quote.horsePower" placeholder="Horse Power" class="form-control" ng-disabled="disabled">
                       </div>
                       <div class="form-group">
                         <label>Serial Number</label>
                         <input type="text" id="serialNumber" name="serialNumber" ng-model="quote.serialNumber" placeholder="Serial Number" class="form-control"  validate-on="dirty" required="required" ng-disabled="disabled">
                       </div>
                       <div class="form-group">
                         <label>Retail Price</label>
                         <input type="text" id="retailPrice" name="retailPrice" ng-model="quote.retailPrice" class="form-control" placeholder="Retail Price" ng-disabled="disabled">
                       </div>
                       <div class="form-group">
                         <label>Meter Hours</label>
                         <input type="text" id="meterHours" name="meterHours" ng-model="quote.meterHours" placeholder="Meter Hours" class="form-control"  validate-on="dirty" required="required" ng-disabled="disabled">
                       </div>
                       <div class="form-group">
                         <label>Model Year</label>
                         <input type="text" id="modelYear" name="modelYear" ng-model="quote.modelYear" placeholder="Model Year" class="form-control" ng-disabled="disabled">
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
                         <div class="input-group">
                           <input type="date" id="coverageEndDate" name="coverageEndDate" ng-model="quote.coverageEndDate" class="form-control" ng-disabled="disabled">
                           <!-- <input type="text" class="form-control" aria-describedby="basic-addon2"> -->
                           <!-- <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span> -->
                         </div>
                       </div>
                       <div class="checkbox">
                         <label>
                           <input type="checkbox" id="coverageExpired" name="coverageExpired" ng-model="quote.coverageExpired" ng-disabled="disabled"> Check here if the Manufacturer's Coverage has expired.
                         </label>
                       </div>
                       <div class="form-group">
                         <label>Deductible</label>
                         <select name="deductiblePrice" ng-model="quote.deductiblePrice" class="form-control" ng-options="deductibleAmt for deductibleAmt in deductibleAmtList track by deductibleAmt"  validate-on="dirty" required="required" ng-disabled="disabled" ng-change="getCoveragePriceLevels()">
                         	<option value="">Select Deductible</option>
						 </select>
                       </div>
                       <div class="form-group">
                         <label>Coverage Term</label>
                         <select name="coverageTerm" ng-model="quote.coverageTerm" class="form-control" ng-options="coverageTermVal for coverageTermVal in coverageTermList track by coverageTermVal"  validate-on="dirty" required="required" ng-disabled="disabled" ng-change="getCoveragePriceLevels()">
                         	<option value="">Select Coverage Term</option>
						 </select>
                       </div>
                       <div class="form-group">
                         <label>Covered Hours</label>
                         <select name="coverageHours" ng-model="quote.coverageHours" class="form-control" ng-options="coverageLevelHour for coverageLevelHour in coverageLevelHoursList track by coverageLevelHour"  validate-on="dirty" required="required" ng-disabled="disabled" ng-change="getCoveragePriceLevels()">
                         	<option value="">Select Covered Hours</option>
						 </select>
                       </div>
                       <div class="form-group">
                         <label>Coverage Type</label>
                         <select name="coverageType" ng-model="quote.coverageType" class="form-control"  validate-on="dirty" required="required" ng-disabled="disabled">
                         	<option value="">Select Coverage Type</option>
                         	<option ng-repeat="ctype in quote.coverageTypeSet" value="{{ctype}}" ng-selected="{{quote.coverageType == ctype}}">
                         		{{(ctype == 'PH')?"Powertrain + Hydraulic":(ctype == 'PT')?"Powertrain":"Powertrain + Hydraulic + Platform"}}
                         	</option>
                         	<!-- <option value="PT">Powertrain</option>
			                <option value="PH">Powertrain + Hydraulic</option>
			                <option value="PL">Powertrain + Hydraulic + Platform</option> -->
						 </select>
                       </div>
                       <div class="form-group">
                         <label>Limit of Liability</label>
                         <p>{{quote.machineInfoDO.lol | currency:"$":0}}</p>
                       </div>
                       <div class="form-group">
                         <label>Estimated Sale Date</label>
                         <div class="input-group">
                           <input type="date" id="estSaleDate" name="estSaleDate" ng-model="quote.estSaleDate" class="form-control"  validate-on="dirty" required="required" ng-disabled="disabled">
                           <!-- <input type="text" class="form-control" aria-describedby="basic-addon2"> -->
                           <!-- <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span> -->
                         </div>
                       </div>
                       <div class="form-group">
                         <label>Additional Unit Information</label>
                         <textarea class="form-control" placeholder="" ng-model="quote.otherProv" ng-disabled="disabled"></textarea>
                         <span class="f-r"><small>If MFG's coverage is not 2 years/2000 hours, please describe.</small></span>
                       </div>
                       <div class="form-group">
                         <label>Dealer Markup</label>
                         <input type="text" id="dealerMarkup" name="dealerMarkup" ng-model="quote.dealerMarkup" placeholder="Dealer Markup" class="form-control"  validate-on="dirty" required="required" ng-disabled="disabled">
                       </div>
                       <div class="form-group">
                         <label>Markup Type</label>
                         <div class="agform-radio">
                         <label class="radio-inline">
                           <input type="radio" id="dealerMarkupVlaue" name="dealerMarkupVlaue" ng-model="quote.dealerMarkupType" value="price" class="" ng-disabled="disabled"> Price
                         </label>
                         <label class="radio-inline">
                           <input type="radio" id="dealerMarkupVlaue" name="dealerMarkupVlaue" ng-model="quote.dealerMarkupType" value="percent" class="" ng-disabled="disabled">  Percent
                         </label>
                         </div>
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
                       </div>
                       <br clear="all">
                        <br clear="all">
                       <h3>Customer Information</h3>
                     <div class="col-xs-12 border-bottom no-pad">
                       <div class="form-group">
                         <label>Name/Nickname</label>
                         <input type="text" id="dealerName" name="dealerName" ng-model="quote.dealerName" placeholder="Dealer Name" class="form-control"  validate-on="dirty" required="required" ng-disabled="disabled">
                       </div>
                       <div class="form-group">
                         <label>Address</label>
                         <input type="text" id="dealerAddress" name="dealerAddress" ng-model="quote.dealerAddress" placeholder="Dealer Address" class="form-control" ng-disabled="disabled">
                       </div>
                       <div class="form-group">
                         <label>City</label>
                         <input type="text" id="dealerCity" name="dealerCity" ng-model="quote.dealerCity" placeholder="Dealer City" class="form-control" ng-disabled="disabled">
                       </div>
                       <div class="form-group">
                         <label>State/Province</label>
                         <select class="form-control" name="dealerState" ng-model="quote.dealerState" id="dealerState"  validate-on="dirty" required="required" ng-disabled="disabled">
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
                         <input type="text" id="dealerZip" name="dealerZip" ng-model="quote.dealerZip" placeholder="Zip" class="form-control"  validate-on="dirty" required="required" ng-disabled="disabled">
                       </div>
                       <div class="form-group">
                         <label>Phone Number</label>
                         <input type="text" id="dealerPhone" name="dealerPhone" ng-model="quote.dealerPhone" placeholder="Phone Number" class="form-control"  validate-on="dirty" required="required" ng-disabled="disabled">
                       </div>
                       <div class="form-group">
                         <label>Email</label>
                         <input type="text" id="dealerEmail" name="dealerEmail" ng-model="quote.dealerEmail" placeholder="Email" class="form-control"  validate-on="dirty" required="required" ng-disabled="disabled">
                       </div>
                       <div class="checkbox">
                         <label>
                           <input type="checkbox" id="custUnderstandCoverage" name="custUnderstandCoverage" ng-model="quote.custUnderstandCoverage" ng-value="true" ng-checked="true" required="required"  validate-on="dirty" ng-disabled="disabled"> Customer understands coverage.
                         </label>
                       </div>
                       <div class="checkbox">
                         <label>
                           <input type="checkbox" id="custRemorsePeriod" name="custRemorsePeriod" ng-model="quote.custRemorsePeriod" ng-value="true" ng-checked="true" required="required"  validate-on="dirty" ng-disabled="disabled"> Customer is aware of 90-day remorse period.
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
								<p>{{quote.quoteBasePrice | currency:"$":0}}</p>
							  </div>
							  <div class="form-group">
								<label>Admin Adjusted Price</label>
								 <input type="text" id="adjustedBasePrice" name="adjustedBasePrice" ng-model="quote.adjustedBasePrice" class="form-control">
							  </div>
							  <div class="form-group">
								<label>Limit of Liability</label>
								<p>{{quote.machineInfoDO.lol | currency:"$":0}}</p>
							  </div>
							  <div class="form-group">
								<label>Admin Adjusted LOL</label>
								 <input type="text" id="adjustedLol" name="adjustedLol" ng-model="quote.adjustedLol" class="form-control">
							  </div>
							  <div class="form-group">
								<label>Current Status</label>
								<select class="form-control" name="status" ng-model="quote.status" convert-to-number id="status"  validate-on="dirty" required="required">
								  <option value="1">Estimating Price</option>
								  <option value="4">Purchase Requested</option>
								  <option value="5">Invoiced</option>
								</select>
							  </div>
							  <div class="form-group">
								<label>Last Update</label>
								<p>{{quote.lastUpdate | date:'MM/dd/yyyy'}}</p>
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
						</div>
                   </div>
             	</div>
             </div>
			</form>
<!-- end data table section -->
             
</article>
<!-- /Article -->