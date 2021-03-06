<!-- Article main content -->
<article class="col-md-9 maincontent">
	<header class="page-header">
		<div class="col-md-6 col-sm-12">
			<div class="sec-title">

				<h3 class="wow animated bounceInLeft">Edit Machine</h3>
				<!-- <p class="wow animated bounceInRight">The Key Features of our
					Tool</p> -->
			</div>
		</div>
	</header>

	<!-- data table section -->
	<div class="inner-main" ng-controller="machineController">
		<form class="form-horizontal" role="form" ng-submit="submitMachine()">
		<!-- 	<h2>Registration Form</h2> -->
			<div class="form-group">
				<label for="manufacturer" class="col-sm-3 control-label">Manufacturer</label>
				<div class="col-sm-9">
					<select name="machine" ng-model="machine.manufacturerDO" 
					ng-options="machine.name for machine in manufacturerList" ng-change="getMachineType()">
         				<!--  <option ng:repeat="machine in manufacturerList" value="{{machine.id}}">{{machine.name}}</option>-->
     				</select> 
				</div>
			</div>
			<div class="form-group">
				<label for="machine type" class="col-sm-3 control-label">Machine Type</label>
				<div class="col-sm-9">
					<select name="machineType" ng-model="machine.machineTypeDO" 
					ng-options="machineType.name for machineType in machineTypeList" ng-change="getMachineModel()"></select> 
				</div>
			</div>
			<div class="form-group">
				<label for="password" class="col-sm-3 control-label">Model</label>
				<div class="col-sm-9">
					<select name="machineModel" ng-model="machineModel" ng-options="machineModel.modelName for machineModel in machineModelList" 
					ng-change="getEngineModel()"></select> 
				</div>
			</div>
			<div class="form-group">
				<label for="engne power" class="col-sm-3 control-label">Engine Power</label>
				<div class="col-sm-9">
					<input type="text" id="enginePower" ng-model="machine.enginePower" placeholder="Engine Power" class="form-control">
				</div>
			</div>
			<div class="form-group">
				<label for="group id" class="col-sm-3 control-label">Group ID</label>
				<div class="col-sm-9">
					<select id="groupId" class="form-control" ng-model="machine.groupId">
						<option>1</option>
						<option>2</option>
						<option>3</option>
						<option>4</option>
						<option>5</option>
						<option>6</option>
						<option>7</option>
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