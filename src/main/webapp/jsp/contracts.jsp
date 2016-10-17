<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Article main content -->
<article class="col-md-9 maincontent">
	<header class="page-header">
             	<div class="col-md-8 col-sm-12">
		<div class="sec-title">
                     
				<h3 class="wow animated bounceInLeft">Contracts Info</h3>
				<!-- <p class="wow animated bounceInRight">The Key Features of our Tool</p> -->
			</div>
                     </div>
                     <!-- <div class="col-md-4 col-sm-12"><a class="btn btn-primary pull-right  hvr-pulse" href="#">Add New</a></div> -->
	</header>
             
             
	 <!-- data table section -->
             
     <table id="contractsTbl" class="table table-striped table-bordered" cellspacing="0" width="100%">
        <thead>
            <tr>
            	<th>ID</th>
                <th>Serial</th>
                <th>LOL</th>
                <th>Inception Date</th>
                <th>Expiration Date</th>
                <th>Expiration Hours</th>
                <th>Status</th>
                <c:if test="${user.roleDO.accountType eq 'admin'}">
                <th></th>
                </c:if>
            </tr>
        </thead>
 
        <tfoot>
           <tr>
            	<th>ID</th>
                <th>Serial</th>
                <th>LOL</th>
                <th>Inception Date</th>
                <th>Expiration Date</th>
                <th>Expiration Hours</th>
                <th>Status</th>
                <c:if test="${user.roleDO.accountType eq 'admin'}">
                <th></th>
                </c:if>
            </tr>
        </tfoot>
 
        <tbody>
            <tr ng-repeat="contract in contractList">
            	<td>{{contract.contractId}}</td>
            	<td>{{contract.machineSerialNo}}</td>
                <td>{{contract.lol}}</td>
                <td>{{contract.inceptionDate |  date:"MM/dd/yyyy"}}</td>
                <td>{{contract.expirationDate |  date:"MM/dd/yyyy"}}</td>
                <td>{{contract.expirationUsageHours}}</td>
                <td>{{(contract.status == 1)?"Active":(contract.status == 2)?"Expired":(contract.status == 3)?"Cancelled":"Archived"}}</td>
                <c:if test="${user.roleDO.accountType eq 'admin'}">
	                <td>
	                	<div class="manage-sec"><!-- <a href="#"><img src="../assets/images/delete-icon.png" alt="Delete" title="Delete"/></a> -->
	                		<!-- <a ng-click="viewQuote(quote.id, quote.quoteId)"><img src="../assets/images/edit-icon.png" alt="Edit" title="Edit"/></a> -->
	                		<c:choose>
	                			<c:when test="${user.roleDO.accountType eq 'admin'}">
	                				<a href="#/agg/viewContract/{{contract.id}}/{{contract.contractId}}"><img src="../assets/images/edit-pencil.png" alt="View" title="View"/></a>
	                			</c:when>
	                			<c:otherwise>
	                				<img src="../assets/images/edit-pencil.png" alt="View" title="View"/>
	                			</c:otherwise>
	                		</c:choose>
	                	</div>
	                </td>
                </c:if>
            </tr>
        </tbody>
    </table>
			
<!-- end data table section -->
             
</article>
<!-- /Article -->