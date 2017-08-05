
<!-- Article main content -->

<article class="col-md-9 maincontent">
	<header class="page-header">
       <div class="col-md-8 col-sm-12">
		<div class="sec-title">
			<h3 class="wow animated bounceInLeft">OEM Warranty Period Tier</h3>
			</div>
               </div>
               <div class="col-md-4 col-sm-12"><a class="btn btn-primary pull-right  hvr-pulse" href="#/agg/addOEMWarrantyTier">Add New</a></div>
	</header>
             
	 <!-- data table section -->
             
     <table id="table1" class="table table-striped table-bordered" cellspacing="0" width="100%">
        <thead>
            <tr>
            	<th>ID</th>
                <th>Remaining Warranty Period From (months)</th>
                <th>Remaining Warranty Period To (months)</th>
                <th>Adjustment Factor</th>
                 <th></th>
            </tr>
        </thead>
 
        <tfoot>
           <tr>
            	<th>ID</th>
                <th>Remaining Warranty Period From (months)</th>
                <th>Remaining Warranty Period To (months)</th>
                <th>Adjustment Factor</th>
                 <th></th>
            </tr>
        </tfoot>
 
        <tbody>
            <tr ng-repeat="oem in oemWarrantyTierLst">
            	<td>{{oem.id}}</td>
                <td>{{oem.warrantyFrom}}</td>
                <td>{{oem.warrantyTo}}</td>
                <td>{{oem.adjFactor | number : 2}}</td>
                <td>
                <div class="manage-sec"><!-- <a href="#"><img src="../assets/images/delete-icon.png" alt="Delete" title="Delete"/></a> -->
                		<a href="#/agg/editOEMWarrantyTier/{{oem.id}}/"><img src="../assets/images/edit-pencil.png" alt="Edit" title="Edit"/></a>
                	</div>
                </td>
                </tr>
        </tbody>
    </table>
			
<!-- end data table section -->
             
</article>
<!-- /Article -->