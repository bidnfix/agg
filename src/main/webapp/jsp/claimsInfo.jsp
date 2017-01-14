<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Article main content -->
<article class="col-md-9 maincontent">
	<header class="page-header">
             	<div class="col-md-8 col-sm-12">
		<div class="sec-title">
                     
				<h3 class="wow animated bounceInLeft">Claims Information</h3>
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
                <th></th>
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
                <th></th>
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
                <td>{{(claimDO.cStatus === 1)?"Open":(claimDO.cStatus === 2)?"Pre-Auth Requested":(claimDO.cStatus === 3)?"Submitted":
                (claimDO.cStatus === 4)?"Approved":(claimDO.cStatus === 5)?"Pre-Auth Approved":(claimDO.cStatus === 6)?"Pre-Auth Rejected":
                (claimDO.cStatus === 7)?"Approved with adjustment":(claimDO.cStatus === 8)?"Pending":(claimDO.cStatus === 9)?"Draft":
                (claimDO.cStatus === 10)?"Rejected":""}}</td>
                <td>
                	<div class="manage-sec" ng-hide="printClaim"><!-- <a href="#"><img src="../assets/images/delete-icon.png" alt="Delete" title="Delete"/></a> -->
                		<!-- commented on Jan 3, 2017 -->
                		<%-- <c:choose>
				    		<c:when test="${user.roleDO.name eq 'admin'}">
				    			<span ng-if="claimDO.cStatus != 1 && claimDO.cStatus != 3 && claimDO.cStatus != 9">
				    				<a ng-click="editClaimByDealer(claimDO.claimId, claimDO.cStatus)"><img src="../assets/images/edit-pencil.png" alt="Edit" title="Edit"/></a>
				    			</span>
				    		</c:when>
				    		<c:otherwise>
				    			<span ng-if="claimDO.cStatus == 5 || claimDO.cStatus == 7 || claimDO.cStatus == 9">
				    				<a ng-click="editClaimByDealer(claimDO.claimId, claimDO.cStatus)"><img src="../assets/images/edit-pencil.png" alt="Edit" title="Edit"/></a>
				    			</span>
				    		</c:otherwise>
				    	</c:choose> --%>
				    	<!-- added Jan 3, 2017 -->
				    	<a ng-click="editClaimByDealer(claimDO.claimId, claimDO.cStatus)"><img src="../assets/images/edit-pencil.png" alt="Edit" title="Edit"/></a>
                	</div>
                	<div class="manage-sec" ng-show="printClaim">
				    	<a target="_blank" href="/agg/claim/report/{{claimDO.claimId}}"><img src="../assets/images/printer-icon.png" alt="Print" title="Print"/></a>
                	</div>
                </td>
                
            </tr>
        </tbody>
    </table>
			
<!-- end data table section -->
             
</article>
<!-- /Article -->