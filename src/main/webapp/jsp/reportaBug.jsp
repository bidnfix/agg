
<!-- Article main content -->
<article class="col-md-9 maincontent">
	<header class="page-header">
		<div class="col-md-6 col-sm-12">
			<div class="sec-title">

				<h3 class="wow animated bounceInLeft">Report a Bug</h3>
				<p class="wow animated bounceInRight">&nbsp;</p>
			</div>
		</div>
		<div class="col-md-6 col-sm-12">
		</div>
		<div class="col-md-4 col-sm-12"><a class="btn btn-primary pull-right  hvr-pulse" href="#/agg/bugInfo">List View</a></div>
	</header>

	<!-- data table section -->

	<div class="inner-main" ng-controller="ReportBugController">
		<form class="form-horizontal" role="form" ng-submit="submitBug()">
			<div class="form-group">
				<label for="ID" class="col-sm-3 control-label">ID</label>
				<div class="col-sm-9">
					<p class="help-block">{{bugId}}</p>
				</div>
			</div>
			<div class="agf1 form-group">
				<label for="userName" class="col-sm-3 control-label">Discovered</label>
				<div class="col-sm-9">
					 <!-- <input type="date" ng-model="report.discDate" value="{{date | date:'yyyy-MM-dd'}}" class="form-control"> -->
					 <div class="input-group">
                          <input type="text" class="form-control" 
			                   datepicker-popup="MM/dd/yyyy"
			                   datepicker-options="dateOptions" 
			                   is-open="discDatePickerIsOpen" 
			                   ng-click="discDatePickerOpen()"
			                   ng-model="report.discDate"/>
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
					<!-- <input type="date" ng-model="report.FixByDate" value="{{date | date:'yyyy-MM-dd'}}" class="form-control"/> -->
					<div class="input-group">
                          <input type="text" class="form-control" 
			                   datepicker-popup="MM/dd/yyyy"
			                   datepicker-options="dateOptions" 
			                   is-open="fixByDatePickerIsOpen" 
			                   ng-click="fixByDatePickerOpen()"
			                   ng-model="report.FixByDate"/>
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
					<select class="form-control" name="Priority" ng-model="report.priority" id="priority" required="required">
						<option value="">Select a Priority</option>
						<option value="1">Low</option>
						<option value="2">Medium</option>
						<option value="3">High</option>
						<option value="3">Wish</option>
						<option value="3">Project</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label for="status" class="col-sm-3 control-label">Status</label>
				<div class="col-sm-9">
					<select class="form-control" name="Status" ng-model="report.status" id="status" required="required">
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
					<input type="textarea" id="description" name="description" ng-model="report.description" class="form-control" required="required">
				</div>
			</div>
			
			<div class="form-group">
				<label for="notes" class="col-sm-3 control-label">Notes</label>
				<div class="col-sm-9">
					<input type="textarea" id="notes" name="notes" ng-model="report.notes" class="form-control" required="required">
				</div>
				</div>
				
				<div class="form-group">
				<label for="notes" class="col-sm-3 control-label">URL</label>
				<div class="col-sm-9">
					<input type="text" id="url" name="notes" ng-model="report.url" class="form-control" required="required">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-9 col-sm-offset-3">
					<button type="submit" class="btn btn-primary btn-block">Submit</button>
				</div>
			</div>
		</form>
		<!-- /form -->
	</div>
	<!--inner main-->

	<!-- end data table section -->

</article>
<!-- /Article -->
