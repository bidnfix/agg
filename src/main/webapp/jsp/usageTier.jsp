
<!-- Article main content -->

<article class="col-md-9 maincontent">
	<header class="page-header">
       <div class="col-md-8 col-sm-12">
		<div class="sec-title">
			<h3 class="wow animated bounceInLeft">Usage Tier</h3>
			</div>
               </div>
               <div class="col-md-4 col-sm-12"><a class="btn btn-primary pull-right  hvr-pulse" href="#/agg/addUsageTier">Add New</a></div>
	</header>
             
	 <!-- data table section -->
             
     <table id="table1" class="table table-striped table-bordered" cellspacing="0" width="100%">
        <thead>
            <tr>
            	<th>ID</th>
                <th>Usage From</th>
                <th>Usage To</th>
                <th>Adjustment Factor</th>
                 <th></th>
            </tr>
        </thead>
 
        <tfoot>
           <tr>
            	<th>ID</th>
                <th>Usage From</th>
                <th>Usage To</th>
                <th>Adjustment Factor</th>
                 <th></th>
            </tr>
        </tfoot>
 
        <tbody>
            <tr ng-repeat="usageTier in usageTierLst">
            	<td>{{usageTier.id}}</td>
                <td>{{usageTier.usageFrom}}</td>
                <td>{{usageTier.usageTo}}</td>
                <td>{{usageTier.adjFactor | number : 2}}</td>
                <td>
                <div class="manage-sec"><!-- <a href="#"><img src="../assets/images/delete-icon.png" alt="Delete" title="Delete"/></a> -->
                		<a href="#/agg/editUsageTier/?{{usageTier.id}}"><img src="../assets/images/edit-pencil.png" alt="Edit" title="Edit"/></a>
                	</div>
                </td>
                </tr>
        </tbody>
    </table>
			
<!-- end data table section -->
             
</article>
<!-- /Article -->