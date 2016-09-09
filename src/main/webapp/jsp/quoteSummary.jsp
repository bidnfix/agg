<form class="form-horizontal" name="quoteSummaryForm" id="quoteSummaryForm" angular-validator-submit="validateQuoteSummaryForm()" novalidate angular-validator>
<div class="agf1 main-login col-xs-12 clearfix">
	<span class="ag-tab-title col-xs-12 no-pad">Please review your
		quote below. Click the edit button to modify.
		<button class="btn btn-primary btn-sm pull-right" ng-click="editQuoteInfo()"> Edit</button>
	</span>
	<div class="col-xs-12 pad10">

		<div class="col-sm-12 no-pad marg10-top marg10-bottom">
			Here is your current quote:<br> With {{coverageTypeDesc}} @
			{{coverageHours}} Hrs : <b>{{quoteBasePrice | currency:"$":0}}</b><br> Dealer markup : <b>{{dealerMarkupAmtPrice | currency:"$":0}}</b><br>
			Total : <b>{{totalCustPrice | currency:"$":0}}</b><br> <!-- <input type="checkbox" name="">
			Include markup?<br> -->
		</div>

		<div class="col-sm-12 no-pad">

			<div class="col-sm-6">
				<div class="form-group">
					<label>Quote ID</label>
					<p>{{quote.quoteId}}</p>
				</div>
			</div>

			<div class="col-sm-6">
				<div class="form-group">
					<label><i class="fa fa-info-circle"
								data-toggle="tooltip" 
								tooltip-trigger tooltip-animation="false" 
					          	tooltip="If you were unable to find the machine manufacturer in the list in step 2, then the name you entered manually will appear; however, we will not be able to provide you with a price automatically. Using the  information you have provided, we will  respond to you quickly with a quote.  We apologize for the inconvenience."
					          	tooltip-placement="top"></i> Manufacturer</label>
					<p>{{quote.manufacturerDO.name}}</p>
				</div>
			</div>

			<div class="col-sm-6">
				<div class="form-group">
					<label><i class="fa fa-info-circle"
								data-toggle="tooltip" 
								tooltip-trigger tooltip-animation="false" 
					          	tooltip="If you were unable to find the model in the list in step 2, the model you entered manually will appear and we will consider the explanation you provided in the 'unusual provisions' section in step one.   Similarly, we will look to the 'unusual provisions' section if you chose a best-fit model number.  If we are unable to provide a price automatically based on the model you entered, we will use the  information you provide to determine the cost and respond to you quickly with a quote.  We apologize for the inconvenience."
					          	tooltip-placement="top"></i> Model</label>
					<p>{{quote.machineInfoDO.model}}</p>
				</div>
			</div>
			<div class="col-sm-6">
				<div class="form-group">
					<label><i class="fa fa-info-circle"
								data-toggle="tooltip" 
								tooltip-trigger tooltip-animation="false" 
					          	tooltip="Please use the engine-rated horsepower. This is not the PTO power or peak power."
					          	tooltip-placement="top"></i> Horsepower</label>
					<p>{{quote.horsePower}}</p>
				</div>
			</div>
			<div class="col-sm-6">
				<div class="form-group">
					<label><i class="fa fa-info-circle"
								data-toggle="tooltip" 
								tooltip-trigger tooltip-animation="false" 
					          	tooltip="The Manufacturer's serial number for the machine. If pricing coverage for a machine you have ordered, but have not yet received, then  'unknown' is appropriate, but this field must be updated before you can complete the purchase of coverage."
					          	tooltip-placement="top"></i> Serial Number</label>
					<p>{{quote.serialNumber}}</p>
				</div>
			</div>
			<div class="col-sm-6">
				<div class="form-group">
					<label><i class="fa fa-info-circle"
								data-toggle="tooltip" 
								tooltip-trigger tooltip-animation="false" 
					          	tooltip="For our purposes, the 'Retail Price' is the advertised price of the machine not including any special deals or terms.  This is not necessarily the sales price.   We do not use the 'Retail Price'  to determine the price of coverage; however, we do use this information to compare coverage on certain machines and within certain price bands when we analyze our risk-so, it is important to report this accurately in order to help us keep our prices as low as possible."
					          	tooltip-placement="top"></i> Retail Price</label>
					<p>{{quote.retailPrice}}</p>
				</div>
			</div>
			<div class="col-sm-6">
				<div class="form-group">
					<label><i class="fa fa-info-circle"
								data-toggle="tooltip" 
								tooltip-trigger tooltip-animation="false" 
					          	tooltip="This should be the exact hours shown on the Machine's tachometer or hours gauge.  It is important that this information is recorded accurately to prevent lapses or denial of coverage."
					          	tooltip-placement="top"></i> Meter Hours</label>
					<p>{{quote.meterHours}}</p>
				</div>
			</div>
			<div class="col-sm-6">
				<div class="form-group">
					<label><i class="fa fa-info-circle"
								data-toggle="tooltip" 
								tooltip-trigger tooltip-animation="false" 
					          	tooltip="The 'model year' may not correlate with age of the machine, but it may effect the price.  Sometimes components change by model year and the change is not reflected in the model number.  These changes can effect our risk  (and our pricing) significantly.  Therefore, it is important to record the actual model year.  If you do not know the model year, leave blank and explain why in the 'unusual provisions' section in step one. "
					          	tooltip-placement="top"></i> Model Year</label>
					<p>{{quote.modelYear}}</p>
				</div>
			</div>
			<div class="col-sm-6">
				<div class="form-group">
					<label><i class="fa fa-info-circle"
								data-toggle="tooltip" 
								tooltip-trigger tooltip-animation="false" 
					          	tooltip="Based on information you provide, we will determine whether we consider the machine condition to be 'new or used'.  This determination depends on several factors and may not match your description of condition.  New machine pricing is better than used.  If we determine that the condition is 'new', that is good for you.  If, however, we determine it is 'used' and you believe it is 'new', then please contact us before purchasing coverage."
					          	tooltip-placement="top"></i> Condition (New/Used)</label>
					<p>{{machineCondition}}</p>
				</div>
			</div>
			<div class="col-sm-6">
				<div class="form-group">
					<label><i class="fa fa-info-circle"
								data-toggle="tooltip" 
								tooltip-trigger tooltip-animation="false" 
					          	tooltip="The primary use for which the equipment is being purchased to perform. The options are broad categories and you should choose the best fit. If you have concerns about an application, it is worth verifying how it might effect coverage before completing the purchase so that there are no surprises. How the machine will be used can effect pricing.  And if the machine is used in a materially different application than reported, it could compromise the coverage."
					          	tooltip-placement="top"></i> Use of Equipment</label>
					<p>{{quote.useOfEquipmentDO.equipName}}</p>
				</div>
			</div>
			<div class="col-sm-6">
				<div class="form-group">
					<label><i class="fa fa-info-circle"></i> MFG End Date</label>
					<p>{{quote.coverageEndDate |  date:"MM/dd/yyyy"}}</p>
				</div>
			</div>
			<div class="col-sm-6">
				<div class="form-group">
					<label><i class="fa fa-info-circle"></i> Deductible</label>
					<p>{{quote.deductiblePrice | currency:"$":0}}</p>
				</div>
			</div>
			<div class="col-sm-6">
				<div class="form-group">
					<label><i class="fa fa-info-circle"></i> Covered Hours</label>
					<p>{{coverageHours}}</p>
				</div>
			</div>
			<div class="col-sm-6">
				<div class="form-group">
					<label><i class="fa fa-info-circle"></i> Coverage Term</label>
					<p>{{quote.coverageTerm}} &nbsp;<span>mos.</span></p>
				</div>
			</div>
			<div class="col-sm-6">
				<div class="form-group">
					<label><i class="fa fa-info-circle"></i> Coverage Type (PT/PH/PL)</label>
					<p>{{coverageType}}</p>
				</div>
			</div>
			<div class="col-sm-6">
				<div class="form-group">
					<label><i class="fa fa-info-circle"></i> Limit of Liability</label>
					<p>{{quote.machineInfoDO.lol | currency:"$":0}}</p>
				</div>
			</div>
			<div class="col-sm-6">
				<div class="form-group">
					<label><i class="fa fa-info-circle"></i> Estimated Sale
						Date</label>
					<p>{{quote.estSaleDate | date:"MM/dd/yyyy"}}</p>
				</div>
			</div>
			<div class="col-sm-6">
				<div class="form-group">
					<label><i class="fa fa-info-circle"></i> Additional Unit
						Information</label>
					<p>{{addUnitInformation}}</p>
				</div>
			</div>

		</div>
	</div>
</div>


<div class="col-sm-12 no-pad t-c marg10-top marg10-bottom">
	<button type="submit" class="btn btn-primary" ng-click="changeTab(4, quoteSummaryForm)"> Continue to Purchase Info</button>
</div>
</form>