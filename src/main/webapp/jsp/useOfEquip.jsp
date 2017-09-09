
<!-- Article main content -->
<%@include file="useOfEquipPopup.jsp" %>
<article class="col-md-9 maincontent">
	<header class="page-header">
             	<div class="col-md-8 col-sm-12">
		<div class="sec-title">
                     
				<h3 class="wow animated bounceInLeft">Use of Equipment</h3>
			</div>
                     </div>
                     <div class="col-md-4 col-sm-12"><a class="btn btn-primary pull-right  hvr-pulse" href="#/agg/addEquipment">Add New</a></div>
	</header>
             
             
	 <!-- data table section -->
             
     <table id="table1" class="table table-striped table-bordered" cellspacing="0" width="100%">
        <thead>
            <tr>
            	<th>ID</th>
                <th>Use of Equipment Desc</th>
                <th>Adjustment Factor</th>
                <th>Edit</th>
                
            </tr>
        </thead>
 
        <tfoot>
            <tr>
           		<th>ID</th>
                <th>Use of Equipment Desc</th>
                <th>Adjustment Factor</th>
                <th>Edit</th>
                
            </tr>
        </tfoot>
 
        <tbody>
            <tr ng-repeat="useOfEquip in useOfEquipLst">
            	<td>{{useOfEquip.id}}</td>
                <td>{{useOfEquip.equipName}}</td>
                <td>{{useOfEquip.discount | number : 2}}</td>
                <td>
                <div class="manage-sec"><!-- <a href="#"><img src="../assets/images/delete-icon.png" alt="Delete" title="Delete"/></a> -->
                		<a ng-click="editUseOfEquip(useOfEquip.id)"><img src="../assets/images/edit-pencil.png" alt="Edit" title="Edit"/></a>
                	</div>
                </td>
                
            </tr>
        </tbody>
    </table>
			
<!-- end data table section -->
             
</article>
<!-- /Article -->