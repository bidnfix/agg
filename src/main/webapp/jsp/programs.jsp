<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Article main content -->
<%@include file="programPopup.jsp" %>
<article class="col-md-9 maincontent">
	<header class="page-header">
             	<div class="col-md-8 col-sm-12">
		<div class="sec-title">
                     
				<h3 class="wow animated bounceInLeft">Programs Info</h3>
			</div>
                     </div>
                     <c:if test="${user.roleName eq 'admin'}">
                     	<div class="col-md-4 col-sm-12"><a class="btn btn-primary pull-right  hvr-pulse" href="#/agg/addPrograms">Add New</a></div>
                     </c:if>
	</header>
             
             
	 <!-- data table section -->
             
     <table id="table1" class="table table-striped table-bordered" cellspacing="0" width="100%">
        <thead>
            <tr>
            	<th>Program Name</th>
                <th>Dealer</th>
                <c:if test="${user.roleName eq 'admin'}">
                	<th>Tasks</th>
                	<th>Status</th>
                </c:if>
            </tr>
        </thead>
 
        <tfoot>
            <tr>
            	<th>Program Name</th>
                <th>Dealer</th>
                <c:if test="${user.roleName eq 'admin'}">
                	<th>Tasks</th>
                </c:if>
            </tr>
        </tfoot>
 
        <tbody>
            <tr ng-repeat="program in programsList">
            	<td>{{program.name}}</td>
                <td>{{program.dealerDO.name}}</td>
                <c:if test="${user.roleName eq 'admin'}">
	                <td><div class="manage-sec">
	                		<!-- <a ng-click="deleteProgram(program.prId)">
	                		<img src="../assets/images/delete-icon.png" alt="Delete" title="Delete"/></a> -->
	                		<a ng-click="editProgram(program.prId)">
	                		<img src="../assets/images/edit-pencil.png" alt="Edit" title="Edit"/></a>
	                	</div>
	                </td>
	                <td>{{(program.isActive === 1)?"Active":"In-Active"}}</td>
                </c:if>
            </tr>
        </tbody>
    </table>
			
<!-- end data table section -->
             
</article>
<!-- /Article -->