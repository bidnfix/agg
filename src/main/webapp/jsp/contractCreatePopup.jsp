<div id="contractCreatePopup" class="agg_popup modal-dialog" style="z-index:10001; display:none; position:absolute;">
<!-- Article main content -->
<article class="modal-content new-modal-box popup">

	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" onclick="closePopup('contractCreatePopup')"><span aria-hidden="true">�</span><span class="sr-only">Close</span></button>
		<h3 class="modal-title" id="lineModalLabel">Create Contract</h3>
	</div>

	<!-- data table section -->

	<div class="modal-body">
		<!-- <a class="btn btn-primary pull-right fadeInLeftBig  hvr-pulse mar-right" onclick="closePopup('dealerEditPopup')">CLOSE</a> -->
		<form class="form-horizontal" role="form" ng-submit="submitCreateContract()">
			<div class="form-group">
				<label for="contact" class="col-sm-3 control-label">Inception/Start Date</label>
				<div class="col-sm-9">
					<input type="date" id="inceptionDate" name="inceptionDate" ng-model="quote.inceptionDate" min="{{date | date:'yyyy-MM-dd'}}" class="form-control" required="required">
				</div>
			</div>
			<div class="form-group">
				<label for="contact" class="col-sm-3 control-label">Expiration Date</label>
				<div class="col-sm-9">
					<input type="date" id="expirationDate" name="expirationDate" ng-model="quote.expirationDate" min="{{date | date:'yyyy-MM-dd'}}" class="form-control" required="required">
				</div>
			</div>
			<div class="form-group">
				<label for="address1" class="col-sm-3 control-label">Expiration Hours</label>
				<div class="col-sm-9">
					<input type="text" id="expirationHours" name="expirationHours" ng-model="quote.expirationHours" class="form-control" required="required">
				</div>
			</div>
			<div class="form-group">
				<label for="address2" class="col-sm-3 control-label">Deal History</label>
				<div class="col-sm-9">
					<textarea class="form-control" placeholder="" ng-model="quote.dealHistory"></textarea>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-9 col-sm-offset-3">
					<button type="submit" class="btn btn-primary btn-block">Create Contract</button>
				</div>
			</div>
		</form>
		<!-- /form -->
	</div>
	<!--inner main-->

	<!-- end data table section -->

</article>
<!-- /Article -->

            </div>