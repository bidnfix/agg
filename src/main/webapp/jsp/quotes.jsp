<!-- Article main content -->
<article class="col-md-9 maincontent">
	<header class="page-header">
             	<div class="col-md-8 col-sm-12">
		<div class="sec-title">
                     
				<h2 class="wow animated bounceInLeft">Quotes Info</h2>
				<!-- <p class="wow animated bounceInRight">The Key Features of our Tool</p> -->
			</div>
                     </div>
                     <div class="col-md-4 col-sm-12"><a class="btn btn-primary pull-right  hvr-pulse" href="#/agg/addQuote">Add New</a></div>
	</header>
             
             
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
                		<a ng-click="viewQuote(quote.id, quote.quoteId)"><img src="../assets/images/edit-icon.png" alt="Edit" title="Edit"/></a>
                	</div>
                </td>
            </tr>
        </tbody>
    </table>
			
<!-- end data table section -->
             
</article>
<!-- /Article -->