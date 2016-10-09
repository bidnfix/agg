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
			<h3>Edit Program</h3>
			<div class="form-group">
				<label for="name" class="col-sm-3 control-label">Assign a Dealer</label>
				<div class="col-sm-9">
					<select name="dealer" ng-model="program.dealerDO" ng-options="dealer.name+' - '+dealer.city group by dealer.parentDealerDO.name+' - '+dealer.parentDealerDO.city for dealer in dealerList | orderBy:'name' track by dealer.id" class="form-control">
         				 <!-- <option ng:repeat="machine in manufacturerList" value="{{dealer.id}}">{{dealer.userName}}</option> -->
     				</select> 
				</div>
			</div>
			<div class="form-group">
				<label for="name" class="col-sm-3 control-label">Manufacturer</label>
				<div class="col-sm-9">
					<select name="machine" ng-model="program.manufacturerDO" ng-options="machine.name for machine in manufacturerList | orderBy:'name' track by machine.machineId" 
					ng-change="getManfModel()" class="form-control">
     				</select> 
				</div>
			</div>
			
			<div class="form-group">
				<label for="name" class="col-sm-3 control-label">Model</label>
				<div class="col-sm-9">
					<!--  <select name="machine" ng-model="program.modelDO" ng-options="machine.name for machine in modelList">
     				</select> -->
     				<select size="5" id="myselection" multiple ng-multiple="true"
			        ng-model="program.machineInfoDOList" ng-options="machine.model for machine in machineModelList | orderBy:'model'" class="form-control">
			      </select> 
				</div>
			</div>
			<div class="form-group">
				<label for="name" class="col-sm-3 control-label">Program</label>
				<div class="col-sm-9">
					<input type="text" ng-model="program.name" id="name" name="name" placeholder="Program Name" class="form-control" required="required">
				</div>
			</div>
			<!-- <div class="form-group">
				<label for="name" class="col-sm-3 control-label">Group</label>
				<div class="col-sm-9">
					<input type="text" ng-model="program.group" id="group" name="group" class="form-control" required="required" value="{{group}}" ng-readonly=true>
				</div>
			</div>
			 -->
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
				<div class="col-sm-8">
				<dl>
					<input type="radio" id="PT" name="PT" ng-model="program.cType" class="" value="PT"> Powertrain
				</dl>
				<dl>
					<input type="radio" id="PH" name="PH" ng-model="program.cType" class="" value="PH"> Powertrain + Hydraulic
				</dl>
				<dl>
					<input type="radio" id="PL" name="PF" ng-model="program.cType" class="" value="PL"> Powertrain + Hydraulic + Platform
				</dl>
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