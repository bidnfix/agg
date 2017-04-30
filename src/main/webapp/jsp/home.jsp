<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Article main content -->
<article class="col-md-9 maincontent" ng-controller="HomeController">
	<header class="page-header">
		<!-- <div class="col-md-6 col-sm-12">
			<div class="sec-title">

				<h3 class="wow animated bounceInLeft">Dashboard</h3>
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
	<nav class="navbar navbar-findcond marg10-bottom">
    <!--<div class="container">-->
		
		<div id="navbar">
			<ul class="nav navbar-nav ">
				<li>
					<a class="worksheet-no-marg" ng-click="getInvoicedQuotes()">Invoiced&nbsp;<span class="badge">{{invoiced}}</span></a>
				</li>
				
				 <li>
					<a class="worksheet-no-marg" ng-click="getReqQuotes()">Purchase Requested&nbsp;<span class="badge">{{purchaseReq}}</span></a>
				</li>
				
				<li>
					<a class="worksheet-no-marg" ng-click="getEstQuotes()">Estimating Price&nbsp;<span class="badge">{{estPrice}}</span></a>
				</li>
				
				<li>
					<a class="worksheet-no-marg" ng-click="getActiveContracts()">Active Contract&nbsp;<span class="badge">{{actContracts}}</span></a>
				</li>
				
				<li>
					<a class="worksheet-no-marg" ng-click="getInactiveContracts()">Expired Contract&nbsp;<span class="badge">{{expContracts}}</span></a>
				</li>
                
                <li>
					<a class="worksheet-no-marg"ng-click="getClaims()">Claims&nbsp;<span class="badge">{{claims}}</span></a>
				</li>
				
			</ul>
			
		</div>
		<!--</div>-->
	</nav>

	<!-- data table section -->
	<table id="quotesTbl" class="table table-striped table-bordered" cellspacing="0" width="100%" ng-hide="quotesFlag">
        <thead>
            <tr>
            	<th>ID</th>
                <th>Dealership</th>
                <th>Customer Contact</th>
                <th>Model</th>
                <th>Est. Sale Date</th>
                <th>Status</th>
                <th></th>
                <th></th>
            </tr>
        </thead>
 
        <tfoot>
           <tr>
            	<th>ID</th>
                <th>Dealership</th>
                <th>Customer Contact</th>
                <th>Model</th>
                <th>Est. Sale Date</th>
                <th>Status</th>
                <th></th>
                <th></th>
            </tr>
        </tfoot>
 
        <tbody>
            <tr ng-repeat="quote in quoteList">
            	<td>{{quote.quoteId}}</td>
            	<td>{{quote.dealerName}}</td>
                <td>{{quote.dealerCustName}}</td>
                <td>{{quote.machineModel}}</td>
                <td>{{quote.machineSaleDate |  date:"MM/dd/yyyy"}}</td>
                <td>{{quote.statusDesc}}</td>
                <td>{{quote.createDate |  date:"MM/dd/yyyy"}}</td>
                <td>
                	<div class="manage-sec"><!-- <a href="#"><img src="../assets/images/delete-icon.png" alt="Delete" title="Delete"/></a> -->
                		<!-- <a ng-click="viewQuote(quote.id, quote.quoteId)"><img src="../assets/images/edit-icon.png" alt="Edit" title="Edit"/></a> -->
                		<a href="#/agg/viewQuote/{{quote.id}}/{{quote.quoteId}}"><img src="../assets/images/edit-pencil.png" alt="View" title="View"/></a>
                	</div>
                </td>
            </tr>
        </tbody>
    </table>
	<!--inner main-->
	
	
	
	<table id="contractsTbl" class="table table-striped table-bordered" cellspacing="0" width="100%" ng-hide="contractsFlag">
        <thead>
            <tr>
            	<th>ID</th>
                <th>Serial</th>
                <th>LOL</th>
                <th>Inception Date</th>
                <th>Expiration Date</th>
                <th>Expiration Hours</th>
                <th>Status</th>
                <th></th>
                <th></th>
            </tr>
        </thead>
 
        <tfoot>
           <tr>
            	<th>ID</th>
                <th>Serial</th>
                <th>LOL</th>
                <th>Inception Date</th>
                <th>Expiration Date</th>
                <th>Expiration Hours</th>
                <th>Status</th>
                <th></th>
                <th></th>
            </tr>
        </tfoot>
 
        <!-- <tbody>
            <tr ng-repeat="contract in contractList">
            	<td>{{contract.contractId}}</td>
            	<td>{{contract.machineSerialNo}}</td>
                <td>{{contract.lol}}</td>
                <td>{{contract.inceptionDate |  date:"MM/dd/yyyy"}}</td>
                <td>{{contract.expirationDate |  date:"MM/dd/yyyy"}}</td>
                <td>{{contract.expirationUsageHours}}</td>
                <td>{{(contract.status == 1)?"Active":(contract.status == 2)?"Expired":(contract.status == 3)?"Cancelled":"Archived"}}</td>
                <td>{{contract.lastUpdatedDate |  date:"MM/dd/yyyy"}}</td>
                <td>
                	<div class="manage-sec">
                		<a href="#/agg/viewContractDetails/{{contract.id}}/{{contract.contractId}}"><img src="../assets/images/edit-pencil.png" alt="View" title="View"/></a>
                	</div>
                </td>
            </tr>
        </tbody>-->
    </table>
    
    <table id="claimsTbl" class="table table-striped table-bordered" cellspacing="0" width="100%" ng-hide="claimsFlag">
        <thead>
            <tr>
            	<th>ID</th>
                <th>Customer</th>
                <th>Dealer Contact</th>
                <th>Serial</th>
                <th>Manufacturer</th>
                <th>Model</th>
                <th>Status</th>
                <th></th>
                <th></th>
            </tr>
        </thead>
 
        <tfoot>
            <tr>
            	<th>ID</th>
                <th>Customer</th>
                <th>Dealer Contact</th>
                <th>Serial</th>
                <th>Manufacturer</th>
                <th>Model</th>
                <th>Status</th>
                <th></th>
                <th></th>
            </tr>
        </tfoot>
 
        <tbody>
            <tr ng-repeat="claimDO in claimDOList" >
                <td>{{claimDO.claimId}}</td>
                <td>{{claimDO.custName}}</td>
                <td>{{claimDO.dealerName}}</td>
                <td>{{claimDO.serial}}</td>
                <td>{{claimDO.manufacturer}}</td>
                <td>{{claimDO.machineModel}}</td>
                <td>{{(claimDO.cStatus === 1)?"Open":(claimDO.cStatus === 2)?"Pre-Auth Requested":(claimDO.cStatus === 3)?"Submitted":
                (claimDO.cStatus === 4)?"Closed":(claimDO.cStatus === 5)?"Pre-Auth Approved":(claimDO.cStatus === 6)?"Pre-Auth Rejected":
                (claimDO.cStatus === 7)?"Approved with adjustment":(claimDO.cStatus === 8)?"Pre-Auth Pending":"Draft"}}
                </td>
                <td>
                	<div class="manage-sec" ng-hide="printClaim"><!-- <a href="#"><img src="../assets/images/delete-icon.png" alt="Delete" title="Delete"/></a> -->
				    	<a ng-click="editClaimByDealer(claimDO.claimId, claimDO.cStatus)"><img src="../assets/images/edit-pencil.png" alt="Edit" title="Edit"/></a>
                	</div>
                	<div class="manage-sec" ng-show="printClaim">
				    	<a target="_blank" href="/agg/claim/report/{{claimDO.claimId}}"><img src="../assets/images/printer-icon.png" alt="Print" title="Print"/></a>
                	</div>
                </td>
                <td>{{claimDO.lastUpdate |  date:"MM/dd/yyyy"}}</td>
            </tr>
        </tbody>
    </table>
    
   	<!-- end data table section -->

</article>
<!-- /Article -->
