<!-- Article main content -->
<article class="col-md-9 maincontent">
	<header class="page-header">
		<div class="col-md-6 col-sm-12">
			<div class="sec-title">

				<h3 class="wow animated bounceInLeft">Add Use Of Equipment</h3>
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
	<div class="inner-main" ng-controller="equipmentController">
		<form class="form-horizontal" role="form" ng-submit="submitEquipment()">
		 <div id="machineSuccessMsg" class="alert alert-info text-center hidden"></div>
		<!-- 	<h2>Registration Form</h2> -->
			
			<div class="form-group">
				<label for="machineType" class="col-sm-3 control-label">Equipment Name</label>
				<div class="col-sm-9">
					<input type="text" id="equipName" name="equipName" ng-model="useOfEquip.equipName" placeholder="Equipment Name" class="form-control" required="required" >
				</div>
			</div>
			<div class="form-group">
				<label for="machineType" class="col-sm-3 control-label">Discount</label>
				<div class="col-sm-9">
					<input type="text" id="discount" name="discount" ng-model="useOfEquip.discount" placeholder="Discount" class="form-control" required="required">
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