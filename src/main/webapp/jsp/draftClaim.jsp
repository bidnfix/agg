<div ng-controller="ClaimsDraftsController"
	ng-init="showDraftsClaimList=true">
	<!-- Article main content -->
	<article class="col-md-9 maincontent" ng-if='showDraftsClaimList'>
		<header class="page-header">
			<div class="col-md-6 col-sm-12">
				<div class="sec-title">
					<h3 class="wow animated bounceInLeft">Saved as draft Claims</h3>
				</div>
			</div>
		</header>
		<div>
			<table id="draftsClaimsListTable"
				class="table table-striped table-bordered" cellspacing="0"
				width="100%">
				<thead>
					<tr>
						<th>Claim ID</th>
						<th>Dealer ID</th>
						<th>Serial</th>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="claim in draftsClaimList"
						ng-click="onClickSelectClaim(claim)">
						<td>{{claim.claimId}}</td>
						<td>{{claim.dealerId}}</td>
						<td>{{claim.serial}}</td>
					</tr>
				</tbody>
			</table>
		</div>
	</article>
	<!-- /Article -->

	<!-- Article main content -->
	<article class="col-md-9 maincontent" ng-if='!showDraftsClaimList'>
		<header class="page-header">
			<div class="col-md-6 col-sm-12">
				<div class="sec-title">

					<h2 class="wow animated bounceInLeft">Save a Claim</h2>
					<p class="wow animated bounceInRight">Claim #:
						{{claim.claimId}}</p>
				</div>
			</div>
			<div class="col-md-6 col-sm-12">
				<a class="btn btn-primary pull-right btn-sm mar-right"
					ng-click="onClickBackToList()">Back</a>
			</div>
		</header>


		<!-- data table section -->

		<div class="inner-main">
			<jsp:include page="saveClaimForm.jsp"></jsp:include>
		</div>

		<!-- end data table section -->
		<!-- The actual modal template, just a bit o bootstrap -->

	</article>
	<!-- /Article -->
</div>