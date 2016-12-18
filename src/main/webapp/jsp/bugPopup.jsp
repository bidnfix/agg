<div id="bugEditPopup" class="agg_popup modal-dialog" style="z-index:10001; display:none; position:absolute;">
<!-- Article main content -->
<article class="modal-content new-modal-box popup">
	<!-- data table section -->
	
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" onclick="closePopup('bugEditPopup')"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
		<h3 class="modal-title" id="lineModalLabel">Manage Bug</h3>
	</div>

	<div class="modal-body">
		<form class="form-horizontal" role="form" ng-submit="editSubmitBug()">
			<h3>Edit Bug</h3>
			<div class="form-group">
				<label for="ID" class="col-sm-3 control-label">ID</label>
				<div class="col-sm-9">
					<p class="help-block">{{bug.id}}</p>
				</div>
			</div>
			<div class="agf1 form-group">
				<label for="userName" class="col-sm-3 control-label">Discovered</label>
				<div class="col-sm-9">
					 <div class="input-group">
                          <input type="text" class="form-control" 
			                   datepicker-popup="MM/dd/yyyy"
			                   datepicker-options="dateOptions" 
			                   is-open="discDatePickerIsOpen" 
			                   ng-click="discDatePickerOpen()"
			                   ng-model="bug.discovered"/>
						<span class="input-group-btn">
			              <button type="button" class="btn btn-default" 
			                      ng-click="discDatePickerOpen($event)">
			                <i class="glyphicon glyphicon-calendar"></i>
			              </button>
			            </span>
			        </div>
				</div>
			</div>
			<div class="agf1 form-group">
				<label for="FixedBy" class="col-sm-3 control-label">Fixed by</label>
				<div class="col-sm-9">
					<div class="input-group">
                          <input type="text" class="form-control" 
			                   datepicker-popup="MM/dd/yyyy"
			                   datepicker-options="dateOptions" 
			                   is-open="fixByDatePickerIsOpen" 
			                   ng-click="fixByDatePickerOpen()"
			                   ng-model="bug.fixBy"/>
						<span class="input-group-btn">
			              <button type="button" class="btn btn-default" 
			                      ng-click="fixByDatePickerOpen($event)">
			                <i class="glyphicon glyphicon-calendar"></i>
			              </button>
			            </span>
			        </div>
				</div>
			</div>
			<div class="form-group">
				<label for="priority" class="col-sm-3 control-label">Priority</label>
				<div class="col-sm-9">
					<select class="form-control" name="Priority" ng-model="bug.priority" id="priority" convert-to-number required="required">
						<option value="">Select a Priority</option>
						<option value="1">Low</option>
						<option value="2">Medium</option>
						<option value="3">High</option>
						<option value="4">Wish</option>
						<option value="5">Project</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label for="status" class="col-sm-3 control-label">Status</label>
				<div class="col-sm-9">
					<select class="form-control" name="Status" ng-model="bug.status" convert-to-number id="status" required="required">
						<option value="">Status</option>
						<option value="1">New</option>
						<option value="2">Problem</option>
						<option value="3">Fixed</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label for="description" class="col-sm-3 control-label">Bug Description</label>
				<div class="col-sm-9">
					<input type="textarea" id="description" name="description" ng-model="bug.description" class="form-control" required="required">
				</div>
			</div>
			
			<div class="form-group">
				<label for="notes" class="col-sm-3 control-label">Notes</label>
				<div class="col-sm-9">
					<input type="textarea" id="notes" name="notes" ng-model="bug.notes" class="form-control" required="required">
				</div>
				</div>
				
				<div class="form-group">
				<label for="notes" class="col-sm-3 control-label">URL</label>
				<div class="col-sm-9">
					<input type="text" id="url" name="notes" ng-model="bug.url" class="form-control" required="required">
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