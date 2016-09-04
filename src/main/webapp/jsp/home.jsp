
<!-- Article main content -->
<article class="col-md-9 maincontent" ng-controller="HomeController">
	<header class="page-header">
		<!-- <div class="col-md-6 col-sm-12">
			<div class="sec-title">

				<h2 class="wow animated bounceInLeft">Dashboard</h2>
				<p class="wow animated bounceInRight">The Key Features of our
					Tool</p>
			</div>
		</div>
		<div class="col-md-6 col-sm-12">
			<a
				class="btn btn-primary pull-right mar-right animated fadeInRightBig hvr-pulse"
				href="add-new.html">Add New</a><a
				class="btn btn-primary pull-right animated fadeInLeftBig  hvr-pulse mar-right"
				href="#">Back</a>
		</div> -->
	</header>
	<nav class="navbar navbar-findcond ">
    <!--<div class="container">-->
		
		<div id="navbar">
			<ul class="nav navbar-nav ">
				<li>
					<a href="#/agg/pendingDealers">Expired Contract<span class="badge">{{pendingDealers}}</span></a>
				</li>
                
                <li>
					<a href="#/agg/dealers">Active Contract<span class="badge">{{activeDealers}}</span></a>
				</li>
				
                
                <li>
					<a href="#/agg/estPriceQuotes">Estimating Price<span class="badge">{{estPrice}}</span></a>
				</li>
				<li>
					<a href="#/agg/pendingDealers">Invoiced<span class="badge">{{invoiced}}</span></a>
				</li>
                
                <li>
					<a href="#/agg/purchaseReqQuotes">Purchase Requested<span class="badge">{{purchaseReq}}</span></a>
				</li>
				
                
                <li>
					<a href="#/agg/dealers">Claims<span class="badge">{{terminatedDealers}}</span></a>
				</li>
                <!--
                <li>
					<a href="#"> Invoiced  <span class="badge">24</span></a>
				</li>
                
                <li>
					<a href="#"> Purchase Requested <span class="badge">6</span></a>
				</li>
                
                <li>
					<a href="#"> Claims <span class="badge">112</span></a>
				</li>
				-->
				
			</ul>
			
		</div>
		<!--</div>-->
	</nav>

	<!-- data table section -->

	<table id="quotesTbl" class="table table-striped table-bordered" cellspacing="0" width="100%">
        <thead>
            <tr>
            	<th>ID</th>
                <th>Dealership</th>
                <th>Sales Contact</th>
                <th>Model</th>
                <th>Est. Sale Date</th>
                <th>Status</th>
                <th></th>
            </tr>
        </thead>
 
        <tfoot>
           <tr>
            	<th>ID</th>
                <th>Dealership</th>
                <th>Sales Contact</th>
                <th>Model</th>
                <th>Est. Sale Date</th>
                <th>Status</th>
                <th></th>
            </tr>
        </tfoot>
 
        <tbody>
            <tr ng-repeat="quote in quoteList">
            	<td>{{quote.quoteId}}</td>
            	<td>{{quote.dealerDO.name}}</td>
                <td>{{quote.dealerName}}</td>
                <td>{{quote.machineInfoDO.model}}</td>
                <td>{{quote.estSaleDate |  date:"MM/dd/yyyy"}}</td>
                <td>{{quote.statusDesc}}</td>
                <td>
                	<div class="manage-sec"><!-- <a href="#"><img src="../assets/images/delete-icon.png" alt="Delete" title="Delete"/></a> -->
                		<!-- <a ng-click="viewQuote(quote.id, quote.quoteId)"><img src="../assets/images/edit-icon.png" alt="Edit" title="Edit"/></a> -->
                		<a href="#/agg/viewQuote/{{quote.id}}/{{quote.quoteId}}"><img src="../assets/images/edit-icon.png" alt="View" title="View"/></a>
                	</div>
                </td>
            </tr>
        </tbody>
    </table>
	<!--inner main-->

	<!-- end data table section -->

</article>
<!-- /Article -->
