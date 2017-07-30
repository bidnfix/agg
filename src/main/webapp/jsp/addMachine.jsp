<!-- Article main content -->
<article class="col-md-9 maincontent">
	<header class="page-header">
		<div class="col-md-6 col-sm-12">
			<div class="sec-title">

				<h3 class="wow animated bounceInLeft">Add Machine</h3>
				<!-- <p class="wow animated bounceInRight">The Key Features of our
					Tool</p> -->
			</div>
		</div>
		<div class="col-md-6 col-sm-12">
			<a
				class="btn btn-primary pull-right mar-right animated fadeInRightBig hvr-pulse"
				href="add-new.html">Add New</a>
		<!--<a	class="btn btn-primary pull-right animated fadeInLeftBig  hvr-pulse mar-right"
				href="#">Back</a> -->
		</div>
	</header>

	<!-- data table section -->
	<div class="inner-main" ng-controller="machineController">
		<form class="form-horizontal" role="form" ng-submit="submitMachine()">
		 <div id="machineSuccessMsg" class="alert alert-info text-center hidden"></div>
		<!-- 	<h2>Registration Form</h2> -->
			<div class="form-group">
				<label for="manufacturer" class="col-sm-3 control-label">Manufacturer</label>
				<div class="col-sm-9">
					<select name="machine" ng-model="machine.manufacturerDO" ng-options="machine.name for machine in manufacturerList | orderBy:'name'" class="form-control">
         				<!--  <option ng:repeat="machine in manufacturerList" value="{{machine.id}}">{{machine.name}}</option>-->
     				</select> 
				</div>
			</div>
			<div class="form-group">
				<label for="machine type" class="col-sm-3 control-label">Machine Type</label>
				<div class="col-sm-9">
					<select name="machineType" ng-model="machine.machineTypeDO" ng-options="machineType.name for machineType in machineTypeList | orderBy:'name'" class="form-control"></select> 
				</div>
			</div>
			<div class="form-group">
				<label for="model" class="col-sm-3 control-label">Model</label>
				<div class="col-sm-9">
					<input type="text" id="model" ng-model="machine.model" placeholder="Model Name" class="form-control">
				</div>
			</div>
			
			<div class="form-group">
				<label for="engne power" class="col-sm-3 control-label">Engine Power</label>
				<div class="col-sm-9">
					<input type="text" id="enginePower" ng-model="machine.enginePower" placeholder="Engine Power" class="form-control">
				</div>
			</div>
			<div class="form-group">
				<label for="model" class="col-sm-3 control-label">Adjustment Factor</label>
				<div class="col-sm-9">
					<input type="text" id="adjustmentFact" ng-model="machine.adjFactor" placeholder="Adjustment Factor" class="form-control">
				</div>
			</div>
			<div class="form-group">
				<label for="group id" class="col-sm-3 control-label">Group ID</label>
				<div class="col-sm-9">
				<select name="groupId" ng-model="machine.groupId" id="groupId" required="required" class="form-control">
				<!-- ng-options="groupDO.groupId for groupDO in groupList track by groupDO.groupId" data-toggle="tooltip" title="{{groupDO.tips}}" tooltip-placement="top" -->
				
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