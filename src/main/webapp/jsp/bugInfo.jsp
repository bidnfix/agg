
<!-- Article main content -->
<%@include file="bugPopup.jsp" %>
<article class="col-md-9 maincontent">
	<header class="page-header">
             	<div class="col-md-8 col-sm-12">
		<div class="sec-title">
                     
				<h3 class="wow animated bounceInLeft">My Bugs</h3>
			</div>
                     </div>
                     <div class="col-md-4 col-sm-12"><a class="btn btn-primary pull-right  hvr-pulse" href="#/agg/reportaBug">Add New</a></div>
	</header>
             
             
	 <!-- data table section -->
             
     <table id="table1" class="table table-striped table-bordered" cellspacing="0" width="100%">
        <thead>
            <tr>
            	<th>ID</th>
                <th>Discovered</th>
                <th>Fix By</th>
                <th>Description</th>
                <th>Status</th>
                <th></th>
            </tr>
        </thead>
 
        <tfoot>
            <tr>
            	<th>ID</th>
                <th>Discovered</th>
                <th>Fix By</th>
                <th>Description</th>
                <th>Status</th>
                <th></th>
            </tr>
        </tfoot>
 
        <tbody>
            <tr ng-repeat="bug in bugDOList">
            	<td>{{bug.id}}</td>
                <td>{{bug.discovered}}</td>
                <td>{{bug.fixBy}}</td>
                <td>{{bug.description}}</td>
                <td>{{bug.status}}</td>
                <td><div class="manage-sec"><!-- <a href="#"><img src="../assets/images/delete-icon.png" alt="Delete" title="Delete"/></a> -->
                		<a ng-click="editBug(bug.id)"><img src="../assets/images/edit-pencil.png" alt="Edit" title="Edit"/></a>
                	</div>
                </td>
            </tr>
        </tbody>
    </table>
			
<!-- end data table section -->
             
</article>
<!-- /Article -->