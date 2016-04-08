<!-- Article main content -->
<%@include file="dealerPopup.jsp" %>
<article class="col-md-9 maincontent">
	<header class="page-header">
             	<div class="col-md-8 col-sm-12">
		<div class="sec-title">
                     
				<h2 class="wow animated bounceInLeft">Dashboard</h2>
				<p class="wow animated bounceInRight">The Key Features of our Tool</p>
			</div>
                     </div>
                     <div class="col-md-4 col-sm-12"><a class="btn btn-primary pull-right  hvr-pulse" href="add-new.html">Add New</a></div>
	</header>
             
             
	 <!-- data table section -->
             
     <table ng-controller="EditDealerController" id="table1" class="table table-striped table-bordered" cellspacing="0" width="100%">
        <thead>
            <tr>
            	<th>User Name</th>
                <th>Location</th>
                <th>Address</th>
                <th>City</th>
                <th>State</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Role</th>
                <th>Status</th>
                <th></th>
            </tr>
        </thead>
 
        <tfoot>
            <tr>
            	<th>User Name</th>
                <th>Location</th>
                <th>Address</th>
                <th>City</th>
                <th>State</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Role</th>
                <th>Status</th>
                <th></th>
            </tr>
        </tfoot>
 
        <tbody>
            <tr ng-repeat="dealer in dealerList">
            	<td>{{dealer.userName}}</td>
                <td>{{dealer.location}}</td>
                <td>{{dealer.address1}}</td>
                <td>{{dealer.city}}</td>
                <td>{{dealer.state}}</td>
                <td>{{dealer.marketEmail}}</td>
                <td>{{dealer.phone}}</td>
                <td></td>
                <td>{{(dealer.status === 0)?"In-Active":"Active"}}</td>
                <td><div class="manage-sec"><!-- <a href="#"><img src="../assets/images/delete-icon.png" alt="Delete" title="Delete"/></a> -->
                		<a ng-click="editDealer(dealer.id)"><img src="../assets/images/edit-icon.png" alt="Edit" title="Edit"/></a>
                	</div>
                </td>
            </tr>
        </tbody>
    </table>
			
<!-- end data table section -->
             
</article>
<!-- /Article -->