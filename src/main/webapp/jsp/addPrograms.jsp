
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
					<select name="dealer" ng-model="program.dealerDO" ng-options="dealer.name for dealer in dealerList">
         				 <!-- <option ng:repeat="machine in manufacturerList" value="{{dealer.id}}">{{dealer.userName}}</option> -->
     				</select> 
				</div>
			</div>
			<div class="form-group">
				<label for="name" class="col-sm-3 control-label">Manufacturer</label>
				<div class="col-sm-9">
					<select name="machine" ng-model="program.manufacturerDO" ng-options="machine.name for machine in manufacturerList" 
					ng-change="getManfModel()">
     				</select> 
				</div>
			</div>
			
			<div class="form-group">
				<label for="name" class="col-sm-3 control-label">Model</label>
				<div class="col-sm-9">
					<!--  <select name="machine" ng-model="program.modelDO" ng-options="machine.name for machine in modelList">
     				</select> -->
     				<select size="5" id="myselection" multiple ng-multiple="true"
			        ng-model="program.machineInfoDOList" ng-options="machine.model for machine in machineModelList">
			      </select> 
				</div>
			</div>
			<div class="form-group">
				<label for="name" class="col-sm-3 control-label">Program</label>
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
					<label for="coverageTerm" class="col-sm-3 control-label">Active?</label>
					<div class="col-sm-4">
						<input type="radio" id="active" name="active" ng-model="program.isActive" class="" value="1"> Yes
						<input type="radio" id="inactive" name="inactive" ng-model="program.isActive" class="" value="0"> No
					</div>
				</div>
				<div class="form-group">
					<label for="coverageTerm" class="col-sm-3 control-label">Allow for Servicing Dealer?</label>
					<div class="col-sm-4">
						<input type="radio" id="allow" name="allow" ng-model="program.aServicing" class="" value="1"> Yes
						<input type="radio" id="donotallow" name="donotallow" ng-model="program.aServicing" class="" value="0"> No
					</div>
				</div>
				<div class="form-group">
					<label for="coverageTerm" class="col-sm-3 control-label">Condition</label>
					<div class="col-sm-4">
						<input type="radio" id="new" name="new" ng-model="program.condition" class="" value="1"> New
						<input type="radio" id="used" name="used" ng-model="program.condition" class="" value="0"> Used
					</div>
				</div>
				<div class="form-group">
					<label for="coverageTerm" class="col-sm-3 control-label">Coverage Type</label>
				<div class="col-sm-4">
					<input type="radio" id="PT" name="PT" ng-model="program.cType" class="" value="PT"> PT
					<input type="radio" id="PH" name="PH" ng-model="program.cType" class="" value="PH"> PH
					<input type="radio" id="PF" name="PF" ng-model="program.cType" class="" value="PF"> PF
				</div>
				</div>
				
			<div class="form-group">
				<label for="term" class="col-sm-3 control-label">Coverage Term</label>
				<div class="col-sm-9">
					<input type="text" id="term" name="term" ng-model="program.cTerm" class="form-control" required="required">
				</div>
			</div>
			
			<div class="form-group">
				<label for="hours" class="col-sm-3 control-label">Hours Covered</label>
				<div class="col-sm-9">
					<input type="text" id="hours" name="hours" ng-model="program.cHours" placeholder="hours" class="form-control" required="required">
				</div>
			</div>
			
			<div class="form-group">
				<label for="description" class="col-sm-3 control-label">Deductible</label>
				<div class="col-sm-9">
					<input id="text" ng-model="program.deductible" name="description" class="form-control" required="required"></input>
				</div>
			</div>
			
			
			<div class="form-group">
				<label for="prLol" class="col-sm-3 control-label">Limit of Liability</label>
				<div class="col-sm-9">
					<input type="text" id="prLol" name="prLol" ng-model="program.lol" class="form-control" required="required">
				</div>
			</div>
			
			<div class="form-group">
				<label for="cost" class="col-sm-3 control-label">Cost</label>
				<div class="col-sm-9">
					<input type="text" id="cost" name="cost" ng-model="program.cost" class="form-control" required="required">
				</div>
			</div>
			
			
			<div class="form-group">
				<label for="description" class="col-sm-3 control-label">Program Description</label>
				<div class="col-sm-9">
					<textarea id="description" ng-model="program.desc" name="description" class="form-control" rows="5" cols="5" required="required"></textarea>
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
