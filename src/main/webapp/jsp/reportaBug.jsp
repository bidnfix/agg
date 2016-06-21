
<!-- Article main content -->
<article class="col-md-9 maincontent">
	<header class="page-header">
		<div class="col-md-6 col-sm-12">
			<div class="sec-title">

				<h2 class="wow animated bounceInLeft">Report a Bug</h2>
				<p class="wow animated bounceInRight">&nbsp;</p>
			</div>
		</div>
		<div class="col-md-6 col-sm-12">
			<!-- <a
				class="btn btn-primary pull-right mar-right animated fadeInRightBig hvr-pulse"
				href="add-new.html">Add New</a><a
				class="btn btn-primary pull-right animated fadeInLeftBig  hvr-pulse mar-right"
				href="#">Back</a> -->
		</div>
	</header>


	<!-- data table section -->

	<div class="inner-main" ng-controller="ReportBugController">
		<form class="form-horizontal" role="form" ng-submit="submitBug()">
			<div class="form-group">
				<label for="ID" class="col-sm-3 control-label">ID</label>
				<div class="col-sm-9">
					<div class="col-sm-9">
					<label for="ID" class="col-sm-3 control-label">66</label>
				</div>
				</div>
			</div>
			<div class="form-group">
				<label for="userName" class="col-sm-3 control-label">Discovered</label>
				<div class="col-sm-9">
					 <input type="date" ng-model="currDate">
				</div>
			</div>
			<div class="form-group">
				<label for="FixedBy" class="col-sm-3 control-label">Fixed by</label>
				<div class="col-sm-9">
					<input type="date" ng-model="date" value="{{ date | date: 'yyyy-MM-dd' }}" />
				</div>
			</div>
			<div class="form-group">
				<label for="priority" class="col-sm-3 control-label">Priority</label>
				<div class="col-sm-9">
					<select class="form-control" name="Priority" ng-model="user.state" id="priority" required="required">
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
					<select class="form-control" name="Status" ng-model="user.state" id="status" required="required">
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
					<input type="textarea" id="description" name="description" ng-model="user.address1" class="form-control" required="required">
				</div>
			</div>
			
			<div class="form-group">
				<label for="notes" class="col-sm-3 control-label">Notes</label>
				<div class="col-sm-9">
					<input type="textarea" id="notes" name="notes" ng-model="user.address1" class="form-control" required="required">
				</div>
			</div>
		</form>
		<!-- /form -->
	</div>
	<!--inner main-->

	<!-- end data table section -->

</article>
<!-- /Article -->
