<form class="form-horizontal" name="warrantyInfoForm"
	id="warrantyInfoForm"
	angular-validator-submit="validateWarrantyInfoForm()" novalidate
	angular-validator>
	<div class="agf1 main-login col-xs-12 clearfix">
		<span class="ag-tab-title col-xs-12 no-pad">Start here to
			purchase extended coverage, get a quote, or adjust a previous quote.</span>
		<div class="col-xs-12 pad10">
			<div class="col-sm-5">What would you like to do?</div>
			<div class="col-sm-7 no-pad">
				<div class="col-xs-12 no-pad">
					<div class="col-sm-5 no-pad">
						<a class="btn btn-primary pull-left" href="#">New Quote</a>
					</div>
					<div class="col-sm-7 no-pad">
						<div class="input-group">
							<input type="text" class="form-control" aria-label="Find Quote">
							<span class="input-group-addon"><i class="fa fa-search"></i></span>
						</div>
					</div>
				</div>
				<br> <br clear="all">
				<div class="col-xs-12 no-pad marg10-top marg10-bottom clearfix">
					<select class="form-control col-sm-6" name="dealer"
						ng-model="quote.dealerDO" id="dealer"
						ng-options="dealer.name+' - '+dealer.city for dealer in dealerList track by dealer.id"
						ng-change="displayDealerText(quote.dealerDO)" required>
						<option value="">Select Dealer</option>
					</select>
				</div>
				<div class="col-xs-12 no-pad" ng-disabled="disableSelected"
					id="dealerTxt" ng-class="{'hided-div':disableSelected}">
					{{dealerText}}</div>
			</div>

		</div>
		<span class="ag-tab-title col-xs-12 no-pad">Manufacturer's
			Coverage Information</span>
		<div class="col-xs-12 pad10">
			<div class="col-sm-12 no-pad">
				<div class="col-sm-7 t-r">Check here if the Manufacturer's
					Coverage has expired:</div>
				<div class="col-sm-5">
					<input type="checkbox" id="coverageExpired" name="coverageExpired"
						ng-model="quote.coverageExpired" value="true">
				</div>
			</div>
			<div id="manfCoverageExp" ng-disabled="quote.coverageExpired"
				ng-class="{'hided-div':quote.coverageExpired}">
				<div class="col-sm-12 no-pad">
					<div class="col-sm-7 t-r">
						<i class="fa fa-info-circle" data-toggle="tooltip" tooltip-trigger
							tooltip-animation="false"
							tooltip="The 'End Date' is the last day that the Manufacturer's base (powertrain) warranty is in effect-the day it expires. This is vital information because the AgGuard extended service contract will not take effect until after the date you enter. If the date you provide is wrong, it can leave the machine without coverage or cause the coverage to cost more. Always double-check the date by confirming the warranty end date with the manufacturer."
							tooltip-placement="top"></i> End Date of Manufacturer's Base
						Coverage:
					</div>
					<div class="col-sm-5">
						<div class="input-group">
							<input type="date" id="coverageEndDate" name="coverageEndDate"
								min="{{date | date:'yyyy-MM-dd'}}"
								ng-model="quote.coverageEndDate" class="form-control"><!--  <span
								class="input-group-addon"><i
								class="glyphicon glyphicon-calendar"></i></span> -->
						</div>
						<input type="checkbox" id="coverageEndDateUnknown"
							name="coverageEndDateUnknown"
							ng-model="quote.coverageEndDateUnknown" value="true">
						Check if unknown
					</div>
				</div>
				<div class="col-sm-12 no-pad">
					<div class="col-sm-7 t-r">Check here if the End Date has been
						verified with Mfr.:</div>
					<div class="col-sm-5">
						<input type="checkbox" id="coverageEndDateVerified"
							name="coverageEndDateVerified"
							ng-model="quote.coverageEndDateVerified" value="true">
					</div>
				</div>
			</div>
		</div>
		<div id="manfCoverageExp" ng-disabled="quote.coverageExpired"
			ng-class="{'hided-div':quote.coverageExpired}">
			<span class="ag-tab-title col-xs-12 no-pad"><i
				class="fa fa-info-circle" data-toggle="tooltip" tooltip-trigger
				tooltip-animation="false"
				tooltip="Describe the manufacturer's coverage in terms of months and hours.  For example: a machine with 36 months and 3,000 hours of manufacture's coverage on the Powertrain,  but only  18 months and 1,000 hours on the Hydraulics and only 12 months and 500 hours on the Full Machine would be depicted as follows: "
				tooltip-placement="top"></i> Describe the Manufacturer's Coverage
				Terms:</span>
			<div class="col-xs-12 pad10">
				<div class="col-sm-12 no-pad">
					<table class="table">
						<thead>
							<tr>
								<th></th>
								<th>Month</th>
								<th>Hours</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>Power Train</td>
								<td><input type="text" id="powerTrainMonths"
									name="powerTrainMonths" ng-model="quote.powerTrainMonths"
									class="form-control" value="24"></td>
								<td><input type="text" id="powerTrainHours"
									name="powerTrainHours" ng-model="quote.powerTrainHours"
									class="form-control" value="2000"></td>
							</tr>
							<tr>
								<td>Hydraulics</td>
								<td><input type="text" id="hydraulicsMonths"
									name="hydraulicsMonths" ng-model="quote.hydraulicsMonths"
									class="form-control" value="24"></td>
								<td><input type="text" id="hydraulicsHours"
									name="hydraulicsHours" ng-model="quote.hydraulicsHours"
									class="form-control" value="2000"></td>
							</tr>
							<tr>
								<td>Full Machine</td>
								<td><input type="text" id="fullMachineMonths"
									name="fullMachineMonths" ng-model="quote.fullMachineMonths"
									class="form-control" value="24"></td>
								<td><input type="text" id="fullMachineHours"
									name="fullMachineHours" ng-model="quote.fullMachineHours"
									class="form-control" value="2000"></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div class="col-sm-12 no-pad t-c marg10-top">
		<button type="submit" class="btn btn-primary"
			ng-click="changeTab(1, warrantyInfoForm)">Continue to
			Machine Info</button>
	</div>
</form>