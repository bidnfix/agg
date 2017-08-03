<!-- Article main content -->
<article class="col-md-9 maincontent">
	<header class="page-header">
		<div class="col-md-6 col-sm-12">
			<div class="sec-title">
				<h3 class="wow animated bounceInLeft">Edit Usage Tier</h3>
			</div>
		</div>
		<div class="col-md-6 col-sm-12">
			<a
				class="btn btn-primary pull-right mar-right animated fadeInRightBig hvr-pulse"
				href="add-new.html">Add New</a>
		</div>
	</header>

	<!-- data table section -->
	<div class="inner-main" ng-controller="editUsageTierController">
		<form class="form-horizontal" role="form" ng-submit="submitEditUsageTier()">
		 <div id="machineSuccessMsg" class="alert alert-info text-center hidden"></div>
		<!-- 	<h2>Registration Form</h2> -->
			<div class="form-group">
				<label for="machineType" class="col-sm-3 control-label">ID</label>
				<div class="col-sm-9">
					<input type="text" id="id" name="discount" ng-model="usageTierDO.id" class="form-control" required="required" readonly="readonly">
				</div>
			</div>
			
			<div class="form-group">
				<label for="machineType" class="col-sm-3 control-label">Usage From</label>
				<div class="col-sm-9">
					<input type="text" id="usageFrom" name="equipName" ng-model="usageTierDO.usageFrom" placeholder="Usage From" class="form-control" required="required" >
				</div>
			</div>
			<div class="form-group">
				<label for="machineType" class="col-sm-3 control-label">Usage To</label>
				<div class="col-sm-9">
					<input type="text" id="usageTo" name="discount" ng-model="usageTierDO.usageTo" placeholder="Usage To" class="form-control" required="required">
				</div>
			</div>
			
			<div class="form-group">
				<label for="machineType" class="col-sm-3 control-label">Adjustment Factor</label>
				<div class="col-sm-9">
					<input type="text" id="adjFactor" name="discount" ng-model="usageTierDO.adjFactor" placeholder="Adjustment Factor" class="form-control" required="required">
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