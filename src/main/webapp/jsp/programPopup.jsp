<div id="programEditPopup" class="agg_popup modal-dialog" style="z-index:10001; display:none; position:absolute;">
<!-- Article main content -->
<article class="modal-content new-modal-box popup">

	<!-- data table section -->

	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" onclick="closePopup('programEditPopup')"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
		<h3 class="modal-title" id="lineModalLabel">Manage Programs</h3>
	</div>
	
		<div class="modal-body">
		<form class="form-horizontal" role="form" ng-submit="editProgramDetails()">
			<h2>Edit Program</h2>
			<div class="form-group">
				<label for="programName" class="col-sm-3 control-label">Program Name</label>
				<div class="col-sm-9">
					<input type="text" id="programName" name="programName" ng-model="program.name" placeholder="Program Name" class="form-control" required="required">
				</div>
			</div>
			<div class="form-group">
				<label for="description" class="col-sm-3 control-label">Description</label>
				<div class="col-sm-9">
					<input type="text" id="description" name="description" ng-model="program.desc" placeholder="description" class="form-control" required="required">
				</div>
			</div>
			<div class="form-group">
				<label for="dealerName" class="col-sm-3 control-label">Dealer Name</label>
				<div class="col-sm-9">
					<input type="text" id="dealerName" name="dealerName" ng-model="program.dealerDO.name" placeholder="Dealer name" class="form-control" required="required">
				</div>
			</div>
			
			<div class="form-group">
				<div class="col-sm-9 col-sm-offset-3">
					<button type="submit" class="btn btn-primary btn-block">Edit Program</button>
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