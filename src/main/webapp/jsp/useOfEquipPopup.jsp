<div id="UOEEditPopup" class="agg_popup modal-dialog" style="z-index:10001; display:none; position:absolute;">
<!-- Article main content -->
<article class="modal-content new-modal-box popup">
	<!-- data table section -->
	
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" onclick="closePopup('UOEEditPopup')"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
		<h3 class="modal-title" id="lineModalLabel">Manage Use Of Equipment</h3>
	</div>

	<div class="modal-body">
		<form class="form-horizontal" role="form" ng-submit="submitEditEquipment()">
			<!-- <h3>Edit Machine</h3> -->
			<div class="form-group">
				<label for="ID" class="col-sm-3 control-label">ID</label>
				<div class="col-sm-9">
					<input type="text" id="id" name="id" ng-model="useOfEquip.id" placeholder="" class="form-control" required="required"  readonly="readonly">
				</div>
			</div>
			<div class="form-group">
				<label for="machineType" class="col-sm-3 control-label">Equipment Name</label>
				<div class="col-sm-9">
					<input type="text" id="equipName" name="equipName" ng-model="useOfEquip.equipName" placeholder="" class="form-control" required="required">
				</div>
			</div>
			<div class="form-group">
				<label for="machineType" class="col-sm-3 control-label">Discount</label>
				<div class="col-sm-9">
					<input type="text" id="discount" name="discount" ng-model="useOfEquip.discount" placeholder="" class="form-control" required="required">
				</div>
			</div>
			
			<div class="form-group">
				<div class="col-sm-9 col-sm-offset-3">
					<button type="submit" class="btn btn-primary btn-block">Save</button>
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