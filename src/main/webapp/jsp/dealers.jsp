<!-- Article main content -->
<%@include file="dealerPopup.jsp" %>
<article class="col-md-9 maincontent">
	<header class="page-header">
             	<div class="col-md-8 col-sm-12">
			<div class="sec-title">
				<h3 class="wow animated bounceInLeft">Dealer Info</h3>
				<!-- <p class="wow animated bounceInRight">The Key Features of our Tool</p> -->
			</div>
			<div id="dealerAddInfoMsg" class="alert alert-info text-center hidden"></div>
                     </div>
                     <div class="col-md-4 col-sm-12"><a class="btn btn-primary pull-right  hvr-pulse" href="#/agg/addDealer">Add New</a></div>
	</header>
             
             
	 <!-- data table section -->
             
     <table id="dealerTbl" class="table table-striped table-bordered" cellspacing="0" width="100%">
        <thead>
            <tr>
            	<th>Code</th>
                <th>Name</th>
                <th>State</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Parent</th>
               <!--  <th>Role</th> -->
                <th>Status</th>
                <th></th>
            </tr>
        </thead>
 
        <tfoot>
            <tr>
            	<th>Code</th>
                <th>Name</th>
                <th>State</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Parent</th>
                <!-- <th>Role</th> -->
                <th>Status</th>
                <th></th>
            </tr>
        </tfoot>
 
        <tbody>
            <tr ng-repeat="dealer in dealerList">
            	<td>{{dealer.code}}</td>
            	<td>{{dealer.name}}</td>
                <td>{{dealer.state}}</td>
                <td>{{dealer.invoiceEmail}}</td>
                <td>{{dealer.phone}}</td>
                <td>{{dealer.parentCode}}</td>
                <!-- <td>{{dealer.roleName}}</td> -->
                <td>{{(dealer.status === 0)?"Terminated":(dealer.status === 1)?"Active":"Pending"}}</td>
                <td>
                	<div class="manage-sec"><!-- <a href="#"><img src="../assets/images/delete-icon.png" alt="Delete" title="Delete"/></a> -->
                		<a href="#/agg/dealer/{{dealer.id}}"><img src="../assets/images/edit-pencil.png" alt="Edit" title="Edit"/></a>
                	</div>
                </td>
            </tr>
        </tbody>
    </table>
			
<!-- end data table section -->
             
</article>
<!-- /Article -->