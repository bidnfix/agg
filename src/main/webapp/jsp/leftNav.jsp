<!-- Sidebar -->
			<aside class="col-md-3 sidebar sidebar-left">

				<!-- Navigation -->
	<nav class="mainNav" ng-controller="activateTabCtrl">
      <h3>Headding Comes Here</h3>
		<ul id="leftTabs">
			<li class="animated fadeInLeft"><a id="worklist" href="#" ng-click="activateTab($event);">Worklist</a></li>
            <li class="animated fadeInLeft"><a id="serviceDesk" href="#" ng-click="activateTab($event);">Service Drive</a></li>
            <li class="animated fadeInLeft"><a id="userTrack" href="#" ng-click="activateTab($event);">User Track</a></li>
            <li class="animated fadeInLeft"><a id="archivedQuotes" href="#" ng-click="activateTab($event);">Archived Quotes</a></li>
            <li class="animated fadeInLeft"><a id="adminSearch" href="#" ng-click="activateTab($event);">Admin Search</a></li>
            <li class="animated fadeInLeft"><a id="machineSearch" href="#/agg/machineInfo" ng-click="activateTab($event);">Machine Search</a></li>
            <li class="animated fadeInLeftBig"><a id="machineSearch" href="#" ng-click="activateTab($event);">Machine Management</a>
				<ul>
					<li><a id="manageLocations" href="#/agg/machineInfo" ng-click="activateTab($event);">Add Machine</a></li>
					<li><a id="manageUsers" href="#/agg/editMachine" ng-click="activateTab($event);">Edit Machine</a></li>
				</ul>
			</li>
            <li class="animated fadeInLeft"><a id="quoteSearch" href="#" ng-click="activateTab($event);">Quote Search</a></li>
            <li class="animated fadeInLeftBig"><a id="dealerManagement" href="#" ng-click="activateTab($event);">Dealer Management</a>
				<ul>
					<li><a id="manageLocations" href="#/agg/addLocation" ng-click="activateTab($event);">Manage Locations</a></li>
					<li><a id="manageUsers" href="#/agg/addUser" ng-click="activateTab($event);">Manage Users</a></li>
					<li><a id="manageDealers" href="#/agg/addDealer" ng-click="activateTab($event);">Manage Dealers</a></li>
					<li><a id="pendingDealers" href="#" ng-click="activateTab($event);">Pending Dealers</a></li>
					<li><a id="dealerSearch" href="#" ng-click="activateTab($event);">Dealer Search</a></li>
				</ul>
			</li>
			<li class="animated fadeInLeftBig"><a id="claims" href="#" ng-click="activateTab($event);">Claims</a>
				<ul>
					<li><a id="fileClaim" href="#" ng-click="activateTab($event);">File a Claim</a></li>
					<li><a id="myClaims" href="#" ng-click="activateTab($event);">My Claims</a></li>
					<li><a id="archivedClaims" href="#/agg/addDealer" ng-click="activateTab($event);">Archived Claims</a></li>
					<li><a id="fileClaimHelp" href="#" ng-click="activateTab($event);">How to File a Claim</a></li>
				</ul>
			</li>
            <li class="animated fadeInLeft"><a id="termsConditions" href="#" ng-click="activateTab($event);">Terms and Conditions</a></li>
            <li class="animated fadeInLeft"><a id="support" href="#" ng-click="activateTab($event);">Support</a></li>
            <li class="animated fadeInLeft"><a id="quotes" href="#" ng-click="activateTab($event);">Quotes</a></li>
            <li class="animated fadeInLeft"><a id="reports" href="#" ng-click="activateTab($event);">Reports</a></li>
            <li class="animated fadeInLeft"><a href="/agg/logout">Logout</a></li>
            <li class="gree animated fadeInLeft"><a id="help" href="#" ng-click="activateTab($event);">Help</a></li>
		</ul>
	</nav>
				
				

			</aside>
			<!-- /Sidebar -->