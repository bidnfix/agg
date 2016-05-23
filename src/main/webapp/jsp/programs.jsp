<!-- Article main content -->
<%@include file="programPopup.jsp" %>
<article class="col-md-9 maincontent">
	<header class="page-header">
             	<div class="col-md-8 col-sm-12">
		<div class="sec-title">
                     
				<h2 class="wow animated bounceInLeft">Dashboard</h2>
				<p class="wow animated bounceInRight">The Key Features of our Tool</p>
			</div>
                     </div>
                     <div class="col-md-4 col-sm-12"><a class="btn btn-primary pull-right  hvr-pulse" href="#/agg/addPrograms">Add New</a></div>
	</header>
             
             
	 <!-- data table section -->
             
     <table id="table1" class="table table-striped table-bordered" cellspacing="0" width="100%">
        <thead>
            <tr>
            	<th>Program Name</th>
                <th>Dealer</th>
                <th>Tasks</th>
            </tr>
        </thead>
 
        <tfoot>
            <tr>
            	<th>Program Name</th>
                <th>Dealer</th>
                <th>Tasks</th>
            </tr>
        </tfoot>
 
        <tbody>
            <tr ng-repeat="program in programsList">
            	<td>{{program.name}}</td>
                <td>{{program.dealerDO.name}}</td>
                <td><div class="manage-sec">
                		<!-- <a ng-click="deleteProgram(program.prId)">
                		<img src="../assets/images/delete-icon.png" alt="Delete" title="Delete"/></a> -->
                		<a ng-click="editProgram(program.prId)">
                		<img src="../assets/images/edit-icon.png" alt="Edit" title="Edit"/></a>
                	</div>
                </td>
            </tr>
        </tbody>
    </table>
			
<!-- end data table section -->
             
</article>
<!-- /Article -->