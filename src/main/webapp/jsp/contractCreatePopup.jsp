<div id="contractCreatePopup" class="agg_popup modal-dialog" style="z-index:10001; display:none; position:absolute;">
<!-- Article main content -->
<article class="modal-content new-modal-box popup">

	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" onclick="closePopup('contractCreatePopup')"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
		<h3 class="modal-title" id="lineModalLabel">Create Contract</h3>
	</div>

	<!-- data table section -->

	<div class="modal-body">
		<!-- <a class="btn btn-primary pull-right fadeInLeftBig  hvr-pulse mar-right" onclick="closePopup('dealerEditPopup')">CLOSE</a> -->
		<form class="form-horizontal" role="form" ng-submit="submitCreateContract()">
			<div class="agf1 form-group">
				<label for="contact" class="col-sm-3 control-label">Inception/Start Date</label>
				<div class="col-sm-9">
					<!-- <input type="date" id="inceptionDate" name="inceptionDate" ng-model="quote.inceptionDate" min="{{date | date:'yyyy-MM-dd'}}" class="form-control" required="required" ng-blur="updateExpirationDate()"> -->
					<div class="input-group">
						<input type="text" class="form-control" 
		                   datepicker-popup="MM/dd/yyyy"
		                   datepicker-options="dateOptions" 
		                   is-open="inceptionDatePickerIsOpen" 
		                   ng-click="inceptionDatePickerOpen()"
		                   min-date="date"
		                   ng-model="quote.inceptionDate"
		                   ng-blur="updateExpirationDate()" 
		                   required="required"/>
			            <span class="input-group-btn">
			              <button type="button" class="btn btn-default" 
			                      ng-click="inceptionDatePickerOpen($event)">
			                <i class="glyphicon glyphicon-calendar"></i>
			              </button>
			            </span>
		            </div>
				</div>
			</div>
			<div class="agf1 form-group">
				<label for="contact" class="col-sm-3 control-label">Expiration Date</label>
				<div class="col-sm-9">
					<!-- <input type="date" id="expirationDate" name="expirationDate" ng-model="quote.expirationDate" min="{{date | date:'yyyy-MM-dd'}}" class="form-control" required="required"> -->
					<div class="input-group">
						<input type="text" class="form-control" 
		                   datepicker-popup="MM/dd/yyyy"
		                   datepicker-options="dateOptions" 
		                   is-open="expirationDatePickerIsOpen" 
		                   ng-click="expirationDatePickerOpen()"
		                   min-date="date"
		                   ng-model="quote.expirationDate"
		                   required="required"/>
			            <span class="input-group-btn">
			              <button type="button" class="btn btn-default" 
			                      ng-click="expirationDatePickerOpen($event)">
			                <i class="glyphicon glyphicon-calendar"></i>
			              </button>
			            </span>
		            </div>
				</div>
			</div>
			<div class="form-group">
				<label for="address1" class="col-sm-3 control-label">Expiration Hours</label>
				<div class="col-sm-9">
					<input type="text" id="expirationHours" name="expirationHours" ng-model="quote.expirationHours" class="form-control" required="required">
				</div>
			</div>
			<div class="form-group">
				<label for="cheqNo" class="col-sm-3 control-label">Check Number</label>
				<div class="col-sm-9">
					<input type="text" id="cheqNo" name="cheqNo" ng-model="quote.cheqNo" class="form-control">
				</div>
			</div>
			<div class="agf1 form-group">
				<label for="receivedDate" class="col-sm-3 control-label">Received Date</label>
				<div class="col-sm-9">
					<!-- <input type="date" id="receivedDate" name="receivedDate" ng-model="quote.receivedDate" class="form-control"> -->
					<div class="input-group">
						<input type="text" class="form-control" 
		                   datepicker-popup="MM/dd/yyyy"
		                   datepicker-options="dateOptions" 
		                   is-open="receivedDatePickerIsOpen" 
		                   ng-click="receivedDatePickerOpen()"
		                   ng-model="quote.receivedDate"/>
			            <span class="input-group-btn">
			              <button type="button" class="btn btn-default" 
			                      ng-click="receivedDatePickerOpen($event)">
			                <i class="glyphicon glyphicon-calendar"></i>
			              </button>
			            </span>
		            </div>
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
					<button type="submit" class="btn btn-primary btn-block" ng-disabled="contractBtnFlag">Create Contract</button>
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