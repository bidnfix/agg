
<!-- Article main content -->
<%@include file="machinePopup.jsp" %>
<article class="col-md-9 maincontent">
	<header class="page-header">
             	<div class="col-md-8 col-sm-12">
		<div class="sec-title">
                     
				<h2 class="wow animated bounceInLeft">Machine Information</h2>
			</div>
                     </div>
                     <div class="col-md-4 col-sm-12"><a class="btn btn-primary pull-right  hvr-pulse" href="#/agg/addMachine">Add New</a></div>
	</header>
             
             
	 <!-- data table section -->
             
     <table id="table1" class="table table-striped table-bordered" cellspacing="0" width="100%">
        <thead>
            <tr>
            	<th>Manufacturer</th>
                <th>Machine Type</th>
                <th>Model</th>
                <th>Engine Power</th>
                <th>Group ID</th>
                <th></th>
            </tr>
        </thead>
 
        <tfoot>
            <tr>
            	<th>Manufacturer</th>
                <th>Machine Type</th>
                <th>Model</th>
                <th>Engine Power</th>
                <th>Group ID</th>
                <th></th>
            </tr>
        </tfoot>
 
        <tbody>
            <tr ng-repeat="machine in machineInfoList">
            	<td>{{machine.manufacturerDO.name}}</td>
                <td>{{machine.machineTypeDO.name}}</td>
                <td>{{machine.model}}</td>
                <td>{{machine.enginePower}}</td>
                <td>{{machine.groupDO.groupId}}</td>
                <td><div class="manage-sec"><!-- <a href="#"><img src="../assets/images/delete-icon.png" alt="Delete" title="Delete"/></a> -->
                		<a ng-click="editMachine(machine.machineId)"><img src="../assets/images/edit-icon.png" alt="Edit" title="Edit"/></a>
                	</div>
                </td>
            </tr>
        </tbody>
    </table>
			
<!-- end data table section -->
             
</article>
<!-- /Article -->