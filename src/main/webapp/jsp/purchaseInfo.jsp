<form class="form-horizontal" name="purchaseInfoForm" id="purchaseInfoForm" angular-validator-submit="validatePurchaseInfoForm()" novalidate angular-validator>
<div class="agf1 main-login col-xs-12 clearfix">

	<span class="ag-tab-title col-xs-12 no-pad">Quote <b>{{quote.quoteId}}</b>
		has been saved. If you ever have problems with a Quote or Purchase
		please call <b>816-223-1978.</b>
	</span>
	<div class="col-sm-6 pad10">

		<div class="col-sm-12 no-pad marg10-top marg10-bottom">
			Here is your current quote:<br> With {{coverageTypeDesc}} @
			{{coverageHours}} Hrs : <b>{{quoteBasePrice | currency:"$":0}}</b><br> Dealer markup : <b>{{dealerMarkupAmtPrice | currency:"$":0}}</b><br>
			Total : <b>{{totalCustPrice | currency:"$":0}}</b><br> <!-- <input type="checkbox" name="">
			Include markup?<br> -->
		</div>

	</div>
	<br>
	<div class="col-sm-6">
		<div class="col-xs-12 no-pad marg10 border">
			<div class="col-xs-12 clearfix t-c">

				<p>You can print, save as a PDF, or email the following
					documents.</p>
			</div>
			<div class="col-xs-12 clearfix t-c marg10-bottom">
				<button class="btn btn-primary btn-sm marg10-bottom" ng-click="printQuote('dealer')"> View Dealer Quote Summary </button>
				<button class="btn marg10-lef btn-primary btn-sm" ng-click="printQuote('customer')"> View Customer Quote Summary </button>
			</div>
		</div>
	</div>
	<div class="col-xs-12 clearfix t-c marg10-bottom">
		<p>To request an invoice for this quote, please click the Purchase
			Now button below. You will be taken to the purchase verification and
			customer info screen.</p>
	</div>
</div>
<div class="col-sm-12 no-pad t-c marg10-top marg10-bottom">
	<button type="submit" class="btn btn-primary" ng-click="changeTab(5, purchaseInfoForm)"> Purchase Now</button>
</div>
</form>