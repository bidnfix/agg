<form class="form-horizontal" name="coverageInfoForm" id="coverageInfoForm" angular-validator-submit="validateCoverageInfoForm()" novalidate angular-validator>
<div class="agf1 main-login col-xs-12 clearfix">
	<span class="ag-tab-title col-xs-12 no-pad">Determine Dealer
		Markup: Enter the Dealer Mark-Up You want calculated into this quote
		as either a percentage or specific dollar amount</span>
	<div class="col-xs-12 pad10">
		<div class="col-sm-5">Recommended Markup:</div>
		<div class="col-sm-7 no-pad">
			<div class="col-xs-12 no-pad">Your Dealership does not
				currently have a recommended markup.</div>
			<div class="col-xs-12 no-pad">
				<div class="col-sm-5 no-pad">
					<div class="form-group">
						<input type="number" id="dealerMarkup" name="dealerMarkup" ng-model="quote.dealerMarkup" placeholder="Dealer Markup" class="form-control" required="required">
					</div>
					<input type="radio" id="dealerMarkupVlaue" name="dealerMarkupVlaue" ng-model="quote.dealerMarkupType" value="price" class=""> Price 
					<input type="radio" id="dealerMarkupVlaue" name="dealerMarkupVlaue" ng-model="quote.dealerMarkupType" value="percent" class=""> Percentage
				</div>

			</div>

		</div>

	</div>
	<span class="ag-tab-title col-xs-12 no-pad">Choose one
		deductible amount and one coverage term: </span>

	<div class="col-xs-12 pad10">
		<div class="col-sm-12 no-pad">
			<table class="table">
				<thead>
					<tr>
						<th></th>
						<th></th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							<i class="fa fa-info-circle"
								data-toggle="tooltip" 
								tooltip-trigger tooltip-animation="false" 
					          	tooltip="Including a deductible can reduce the price of coverage, which is appealing on the front end.  However, customers can later regret a high deductible because it is assessed per failure, not per repair.  Therefore, if you repair a machine that has multiple failures that are being addressed at the same time, then the deductible can get large.  The deductible can also be a tool for reducing the number of small repairs, which can be an issue for some dealer's. Typically, AgGuard recommends no deductible on new machines; however, it may be appropriate in some cases."
					          	tooltip-placement="top"></i> Deductible:
					    </td>
						<td ng-repeat="deductibleAmt in deductibleAmtList">
							<input  type="radio" id="deductiblePrice" name="deductiblePrice" ng-model="quote.deductiblePrice" ng-click="getCoveragePriceLevels(quote.deductiblePrice, quote.coverageTerm)" ng-value="deductibleAmt" ng-init="$index==0?(quote.deductiblePrice=deductibleAmt):''">{{deductibleAmt | currency:"$":0}}
						</td>
					</tr>
					<tr>
						<td><i class="fa fa-info-circle"
								data-toggle="tooltip" 
								tooltip-trigger tooltip-animation="false" 
					          	tooltip="Coverage begins on the 'Contract Inception Date' and expires according to the 'Term' in months or machine hours, whichever occurs first.  On newer equipment, the time and hour limits of the Term starts the day the manufacturer's warranty starts and at zero (0) Hours.  Coverage expires when the length of time or accumulated hours of the Term selected is reached. For example, 48 months would be the same as 2 years of Manufacturer's coverage and 2 more years of extended coverage.  On used equipment, the term is typically expressed in terms of additional time and hours."
					          	tooltip-placement="top"></i> Coverage Term:</td>
						<td ng-repeat="coverageTermVal in coverageTermList">
							<input type="radio" id="coverageTerm" name="coverageTerm" ng-model="quote.coverageTerm" ng-click="getCoveragePriceLevels(quote.deductiblePrice, quote.coverageTerm)" class="" ng-value="coverageTermVal" ng-init="$index==0?(quote.coverageTerm=coverageTermVal):''">{{coverageTermVal}}&nbsp;mos.
						</td>
					</tr>
					<tr>
						<td><i class="fa fa-info-circle"
								data-toggle="tooltip" 
								tooltip-trigger tooltip-animation="false" 
					          	tooltip=""
					          	tooltip-placement="top"></i> Coverage Expires:</td>
						<td ng-repeat="coverageExpirationDate in coverageExpirationList">
							{{coverageExpirationDate |  date:"MM/dd/yyyy"}}
						</td>
					</tr>

				</tbody>
			</table>
		</div>
	</div>
	<span class="ag-tab-title col-xs-12 no-pad">Select the Desired
		Coverage Level and Hours for {{coverageTermSelected}} months and {{deductiblePriceSelected | currency:"$":0}} deductible. </span>

	<div class="col-xs-12 pad10">
		<div class="col-sm-12 no-pad">
			<div class="col-md-4">
			 	Current Meter Hours: &nbsp; {{quote.meterHours | number:0}}
			</div>
			<div class="col-md-4">
				Coverage Start Date: &nbsp; {{quote.estSaleDate |  date:"MM/dd/yyyy"}}
			</div>
			<table class="table">
				<thead>
					<tr>
						<th>Terms for {{coverageTermSelected}} months<br>(Hours)
						</th>
						<th>Coverage Expires<br>(Hours)
						</th>
						<th>Powertrain</th>
						<th>Powertrain<br>+ Hydraulic
						</th>
						<th>Powertrain<br>+ Hydraulic<br>+ Platform
						</th>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="pricingDO in pricingDOList">
						<td>{{pricingDO.coverageLevelHours | number:0}}</td>
						<td>{{pricingDO.coverageExpirationHours | number:0}}</td>
						<td ng-class="{'selectedcol':$index == selectedRow && selectedCloumn == 2, 'mouseovercol':$index == mouseoverRow && mouseoverCloumn == 2}"  ng-click="setClickedCloumn($index, 2)" ng-mouseover="setMouserCloumn($index, 2)" ng-mouseleave="resetMouseoverColumn()"><a>{{(pricingDO.ptBasePrice != -1)?(pricingDO.ptBasePrice | number:0):""}}</a></td>
					    <td ng-class="{'selectedcol':$index == selectedRow && selectedCloumn == 3, 'mouseovercol':$index == mouseoverRow && mouseoverCloumn == 3}"  ng-click="setClickedCloumn($index, 3)" ng-mouseover="setMouserCloumn($index, 3)" ng-mouseleave="resetMouseoverColumn()"><a>{{(pricingDO.phBasePrice != -1)?(pricingDO.phBasePrice | number:0):""}}</a></td>
					    <td ng-class="{'selectedcol':$index == selectedRow && selectedCloumn == 4, 'mouseovercol':$index == mouseoverRow && mouseoverCloumn == 4}"  ng-click="setClickedCloumn($index, 4)" ng-mouseover="setMouserCloumn($index, 4)" ng-mouseleave="resetMouseoverColumn()"><a>{{(pricingDO.plBasePrice != -1)?(pricingDO.plBasePrice | number:0):""}}</a></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>

	<span class="ag-tab-title col-xs-12 no-pad">Please enter the
		customer's information below Or assign a "Nickname" to this quote. 
		<button ng-click="getDealerInfo()" class="btn btn-primary btn-sm"> Dealer Info</button>
	</span>
	<div class="col-xs-12 pad10">
		<div class="col-sm-12 no-pad">

			<div class="col-sm-6">
				<div class="form-group">
					<label>*Name/Nickname</label> 
					<input type="text" id="dealerName" name="dealerName" ng-model="quote.dealerName" placeholder="Customer Name" class="form-control" required="required">
				</div>
				<div class="form-group">
					<label>Address</label> 
					<input type="text" id="dealerAddress" name="dealerAddress" ng-model="quote.dealerAddress" placeholder="Customer Address" class="form-control">
				</div>
				<div class="form-group">
					<label>City</label> 
					<input type="text" id="dealerCity" name="dealerCity" ng-model="quote.dealerCity" placeholder="Customer City" class="form-control">
				</div>
				<div class="form-group">
					<label>State/Province</label> 
					<select class="form-control" name="dealerState" ng-model="quote.dealerState" id="dealerState">
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
					<input type="text" id="dealerZip" name="dealerZip" ng-model="quote.dealerZip" placeholder="Zip" class="form-control">
				</div>
				<div class="form-group">
					<label>Phone Number</label> 
					<input type="text" id="dealerPhone" name="dealerPhone" ng-model="quote.dealerPhone" placeholder="Phone Number" class="form-control">
				</div>
				<div class="form-group">
					<label>Email Address</label> 
					<input type="text" id="dealerEmail" name="dealerEmail" ng-model="quote.dealerEmail" placeholder="Email" class="form-control">
				</div>
			</div>

			<div class="col-sm-6">
				<div class="col-xs-12 no-pad">
					<label> 
						<input type="checkbox" id="custUnderstandCoverage" name="custUnderstandCoverage" ng-model="quote.custUnderstandCoverage" ng-value="true" required="required"> Customer
						understands coverage.
					</label>
				</div>
				<div class="col-xs-12 no-pad">
					<label> 
						<input type="checkbox" id="custRemorsePeriod" name="custRemorsePeriod" ng-model="quote.custRemorsePeriod" ng-value="true" required="required"> Customer is
						aware of 90-day remorse period.
					</label>
				</div>
			</div>
		</div>
	</div>
</div>
<span class="col-xs-12 no-pad">If you cannot find the coverage
	you want, please select the Help Request form from the black menu bar
	at the top of the page and we will communicate with you soon to explore
	other options. </span>

<div class="col-sm-12 no-pad t-c marg10-top marg10-bottom">
	<button type="submit" class="btn btn-primary" ng-click="changeTab(3, coverageInfoForm)"> Continue to Quote Summary</button>
</div>
</form>