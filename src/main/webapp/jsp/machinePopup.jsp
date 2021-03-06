<div id="machineEditPopup" class="agg_popup modal-dialog" style="z-index:10001; display:none; position:absolute;">
<!-- Article main content -->
<article class="modal-content new-modal-box popup">
	<!-- data table section -->
	
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" onclick="closePopup('machineEditPopup')"><span aria-hidden="true">�</span><span class="sr-only">Close</span></button>
		<h3 class="modal-title" id="lineModalLabel">Manage Machine</h3>
	</div>

	<div class="modal-body">
		<form class="form-horizontal" role="form" ng-submit="editSubmitMachine()">
			<!-- <h3>Edit Machine</h3> -->
			<div class="form-group">
				<label for="manufacturer" class="col-sm-3 control-label">Manufacturer</label>
				<div class="col-sm-9">
					<input type="text" id="manufacturer" name="manufacturer" ng-model="machine.manufacturerDO.name" placeholder="Manufacturer" class="form-control" required="required" readonly="readonly">
				</div>
			</div>
			<div class="form-group">
				<label for="machineType" class="col-sm-3 control-label">Machine Type</label>
				<div class="col-sm-9">
					<input type="text" id="machineType" name="machineType" ng-model="machine.machineTypeDO.name" placeholder="Machine Type" class="form-control" required="required" readonly="readonly">
				</div>
			</div>
			<div class="form-group">
				<label for="model" class="col-sm-3 control-label">Model</label>
				<div class="col-sm-9">
					<input type="text" id="model" name="model" ng-model="machine.model" placeholder="model" class="form-control" required="required">
				</div>
			</div>
			<div class="form-group">
				<label for="power" class="col-sm-3 control-label">Power</label>
				<div class="col-sm-9">
					<input type="text" id="power" name="power" ng-model="machine.enginePower" placeholder="power" class="form-control" required="required">
				</div>
			</div>
			
			<div class="form-group">
				<label for="model" class="col-sm-3 control-label">Adjustment Factor</label>
				<div class="col-sm-9">
					<input type="text" id="adjustmentFact" ng-model="machine.adjFactor" placeholder="Adjustment Factor" class="form-control">
				</div>
			</div>
			
			<div class="form-group">
				<label for="groupId" class="col-sm-3 control-label">Group Id</label>
				<div class="col-sm-9">
				<!-- <select name="groupId" ng-options="groupDO.groupId for groupDO in groupList track by groupDO.groupId" 
						ng-model="machine.groupDO" id="groupId" required="required"></select>-->
						<select name="groupId" ng-model="machine.groupId" id="groupId" required="required" class="form-control">
						<option data-toggle="tooltip" title="{{groupDO.tips}}" tooltip-placement="top" value="{{groupDO}}" 
						ng-repeat="groupDO in groupList" ng-value="groupDO.groupId">{{groupDO.groupId}}</option>
				</select>
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