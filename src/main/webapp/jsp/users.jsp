<!-- Article main content -->
<%@include file="userPopup.jsp" %>
<article class="col-md-9 maincontent">
	<header class="page-header">
             	<div class="col-md-8 col-sm-12">
		<div class="sec-title">
                     
				<h3 class="wow animated bounceInLeft">User Info</h3>
				<!-- <p class="wow animated bounceInRight">The Key Features of our Tool</p> -->
			</div>
                     </div>
                     <div class="col-md-4 col-sm-12"><a class="btn btn-primary pull-right  hvr-pulse" href="#/agg/addUser">Add New</a></div>
	</header>
             
             
	 <!-- data table section -->
             
     <table id="userTbl" class="table table-striped table-bordered" cellspacing="0" width="100%">
        <thead>
            <tr>
            	<th>User Name</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>User Type</th>
                <th>Role</th>
                <th>Dealer Name</th>
                <th>Status</th>
                <th></th>
            </tr>
        </thead>
 
        <tfoot>
            <tr>
            	<th>User Name</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>User Type</th>
                <th>Role</th>
                <th>Dealer Name</th>
                <th>Status</th>
                <th></th>
            </tr>
        </tfoot>
 
        <tbody>
            <tr ng-repeat="user in userList">
            	<td>{{user.userName}}</td>
            	<td>{{user.firstName}}</td>
                <td>{{user.lastName}}</td>
                <td>{{user.userType}}</td>
                <td>{{user.roleName}}</td>
                <td>{{user.dealerName}}</td>
                <td>{{(user.status === 0)?"Terminated":(user.status === 1)?"Active":"Pending"}}</td>
                <td>
                	<div class="manage-sec"><!-- <a href="#"><img src="../assets/images/delete-icon.png" alt="Delete" title="Delete"/></a> -->
                		<a ng-click="editUser(user.id)"><img src="../assets/images/edit-pencil.png" alt="Edit" title="Edit"/></a>
                	</div>
                </td>
            </tr>
        </tbody>
    </table>
			
<!-- end data table section -->
             
</article>
<!-- /Article -->