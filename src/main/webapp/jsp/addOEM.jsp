<!-- Article main content -->
<article class="col-md-9 maincontent">
	<header class="page-header">
		<div class="col-md-6 col-sm-12">
			<div class="sec-title">

				<h3 class="wow animated bounceInLeft">Add OEM Warranty Period Tier</h3>
				<!-- <p class="wow animated bounceInRight">The Key Features of our
					Tool</p> -->
			</div>
		</div>

	</header>

	<!-- data table section -->
	<div class="inner-main" ng-controller="OEMController">
		<form class="form-horizontal" role="form" ng-submit="submitOEM()">
		 <div id="machineSuccessMsg" class="alert alert-info text-center hidden"></div>
		<!-- 	<h2>Registration Form</h2> -->
			
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