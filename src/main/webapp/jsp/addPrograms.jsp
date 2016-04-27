
<!-- Article main content -->
<article class="col-md-9 maincontent">
	<header class="page-header">
		<div class="col-md-6 col-sm-12">
			<div class="sec-title">

				<h2 class="wow animated bounceInLeft">Program Management</h2>
				<p class="wow animated bounceInRight">&nbsp;</p>
			</div>
		</div>
		<div class="col-md-6 col-sm-12">
			<a
				class="btn btn-primary pull-right mar-right animated fadeInRightBig hvr-pulse"
				href="add-new.html">Add New</a><a
				class="btn btn-primary pull-right animated fadeInLeftBig  hvr-pulse mar-right"
				href="#">Back</a>
		</div>
	</header>


	<!-- data table section -->

	<div class="inner-main" ng-controller="programController">
		<form class="form-horizontal" role="form" ng-submit="submitProgram()">
			<h2>Add Program</h2>
			<div class="form-group">
				<label for="name" class="col-sm-3 control-label">Assign a Dealer</label>
				<div class="col-sm-9">
					<select name="dealer" ng-model="program.dealerName" ng-options="dealer.userName for dealer in dealerList"">
         				 <option ng:repeat="machine in manufacturerList" value="{{dealer.id}}">{{dealer.userName}}</option>
     				</select> 
				</div>
			</div>
			<div class="form-group">
				<label for="name" class="col-sm-3 control-label">Name</label>
				<div class="col-sm-9">
					<input type="text" ng-model="program.name" id="name" name="name" placeholder="Program Name" class="form-control" required="required">
				</div>
			</div>
			<div class="form-group">
				<label for="name" class="col-sm-3 control-label">Group</label>
				<div class="col-sm-9">
					<input type="text" ng-model="program.group" id="group" name="group" class="form-control" required="required" value="{{group}}" ng-readonly=true>
				</div>
			</div>
			<div class="form-group">
				<label for="description" class="col-sm-3 control-label">Description</label>
				<div class="col-sm-9">
					<textarea id="description" ng-model="program.description" name="description" class="form-control" rows="5" cols="5" required="required"></textarea>
				</div>
			</div>
			<div class="form-group">
				<label for="hours" class="col-sm-3 control-label">Hours</label>
				<div class="col-sm-9">
					<input type="text" id="hours" name="hours" ng-model="program.hours" placeholder="hours" class="form-control" required="required">
				</div>
			</div>
			<div class="form-group">
				<label for="term" class="col-sm-3 control-label">Term</label>
				<div class="col-sm-9">
					<input type="text" id="term" name="term" ng-model="program.term" class="form-control" required="required">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-3">Coverage Type</label>
				<div class="col-sm-6">
					<div class="row">
						<div class="col-sm-4">
							<label class="radio-inline"> <input type="radio"
								id="PT" value="PT">PT
							</label>
						</div>
						<div class="col-sm-4">
							<label class="radio-inline"> <input type="radio"
								id="PH" value="PH">PH
							</label>
						</div>
						<div class="col-sm-4">
							<label class="radio-inline"> <input type="radio"
								id="PF" value="PF">PF
							</label>
						</div>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label for="cost" class="col-sm-3 control-label">Cost</label>
				<div class="col-sm-9">
					<input type="text" id="cost" name="cost" class="form-control" required="required">
				</div>
			</div>
			<div class="form-group">
				<label for="group" class="col-sm-3 control-label">Group</label>
				<div class="col-sm-9">
					<input type="text" id="group" name="group" class="form-control" required="required">
				</div>
			</div>
			<div class="form-group">
				<label for="prLol" class="col-sm-3 control-label">Limit of Liability</label>
				<div class="col-sm-9">
					<input type="text" id="prLol" name="prLol" class="form-control" required="required">
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-3">Active?</label>
				<div class="col-sm-6">
					<div class="row">
						<div class="col-sm-4">
							<label class="radio-inline"> <input type="radio"
								id="active" value="1">Active
							</label>
						</div>
						<div class="col-sm-4">
							<label class="radio-inline"> <input type="radio"
								id="inActive" value="0">Inactive
							</label>
						</div>
					</div>
				</div>
			</div>
			
			
			<div class="form-group">
				<div class="col-sm-9 col-sm-offset-3">
					<button type="submit" class="btn btn-primary btn-block">Add Program</button>
				</div>
			</div>
		</form>
		<!-- /form -->
	</div>
	<!--inner main-->

	<!-- end data table section -->

</article>
<!-- /Article -->
