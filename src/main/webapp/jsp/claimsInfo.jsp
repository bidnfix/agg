<!-- Article main content -->
<article class="col-md-9 maincontent">
	<header class="page-header">
             	<div class="col-md-8 col-sm-12">
		<div class="sec-title">
                     
				<h2 class="wow animated bounceInLeft">Claims Information</h2>
			</div>
                     </div>
                     <div class="col-md-4 col-sm-12"><a class="btn btn-primary pull-right  hvr-pulse" href="#/agg/fileClaim">Add New</a></div>
	</header>
             
             
	 <!-- data table section -->
             
     <table id="claimsTbl" class="table table-striped table-bordered" cellspacing="0" width="100%">
        <thead>
            <tr>
            	<th>ID</th>
                <th>Customer</th>
                <th>Dealer Contact</th>
                <th>Serial</th>
                <th>Manufacturer</th>
                <th>Model</th>
                <th>Status</th>
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
                <td>{{claimDO.cStatus}}</td>
            </tr>
        </tbody>
    </table>
			
<!-- end data table section -->
             
</article>
<!-- /Article -->