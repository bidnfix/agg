<!-- Article main content -->
<article class="col-md-9 maincontent">
	<header class="page-header">
		<div class="col-md-6 col-sm-12">
			<div class="sec-title">
				<h3 class="wow animated bounceInLeft">Edit OEM Warranty Period Tier</h3>
			</div>
		</div>
		<div class="col-md-6 col-sm-12">
			<a
				class="btn btn-primary pull-right mar-right animated fadeInRightBig hvr-pulse"
				href="#/agg/addOEMWarrantyTier">Add New</a>
		</div>
	</header>

	<!-- data table section -->
	<div class="inner-main" ng-controller="editOEMController">
		<form class="form-horizontal" role="form" ng-submit="submitEditOEM()">
		 <div id="machineSuccessMsg" class="alert alert-info text-center hidden"></div>
		<!-- 	<h2>Registration Form</h2> -->
			<div class="form-group">
				<label for="machineType" class="col-sm-3 control-label">ID</label>
				<div class="col-sm-9">
					<input type="text" id="id" name="discount" ng-model="oemDO.id" class="form-control" required="required" readonly="readonly">
				</div>
			</div>
			
			<div class="form-group">
				<label for="machineType" class="col-sm-3 control-label">Remaining Warranty Period From</label>
				<div class="col-sm-9">
					<input type="text" id="warrantyFrom" name="equipName" ng-model="oemDO.warrantyFrom" placeholder="warranty From" class="form-control" required="required" >
				</div>
			</div>
			<div class="form-group">
				<label for="machineType" class="col-sm-3 control-label">Remaining Warranty Period To</label>
				<div class="col-sm-9">
					<input type="text" id="warrantyTo" name="discount" ng-model="oemDO.warrantyTo" placeholder="warranty To" class="form-control" required="required">
				</div>
			</div>
			
			<div class="form-group">
				<label for="machineType" class="col-sm-3 control-label">Adjustment Factor(+value : Premium -value : Discount)</label>
				<div class="col-sm-9">
					<input type="text" id="adjFactor" name="discount" ng-model="oemDO.adjFactor" placeholder="Adjustment Factor" class="form-control" required="required">
				</div>
			</div>
			
			<div class="form-group">
				<!-- <div class="col-sm-9 col-sm-offset-3">
					<button type="submit" class="btn btn-primary btn-block">Save</button>
				</div> -->
				
				<div class="col-sm-4 col-sm-offset-3">
					<button type="submit" class="btn btn-primary btn-block">Save</button>
				</div>
				<div class="col-sm-5">
					<button type="button" class="btn btn-primary btn-block" ng-click="deleteOEM(oemDO.id)">Delete</button>
				</div>
				
			</div>
		</form>
		<!-- /form -->
	</div>
	<!--inner main-->

	<!-- end data table section -->

</article>
<!-- /Article -->