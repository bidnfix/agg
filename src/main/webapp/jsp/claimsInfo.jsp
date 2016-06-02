<!-- Article main content -->
<%@include file="machinePopup.jsp" %>
<article class="col-md-9 maincontent">
	<header class="page-header">
             	<div class="col-md-8 col-sm-12">
		<div class="sec-title">
                     
				<h2 class="wow animated bounceInLeft">Claims Information</h2>
			</div>
                     </div>
                     <div class="col-md-4 col-sm-12"><a class="btn btn-primary pull-right  hvr-pulse" href="#/agg/addMachine">Add New</a></div>
	</header>
             
             
	 <!-- data table section -->
             
     <table id="table1" class="table table-striped table-bordered" cellspacing="0" width="100%">
        <thead>
            <tr>
            	<th>ID</th>
                <th>Manufacturer</th>
                <th>Model</th>
                <th>Serial</th>
                <th>Coverage Type</th>
            </tr>
        </thead>
 
        <tfoot>
            <tr>
            	<th>ID</th>
                <th>Manufacturer</th>
                <th>Model</th>
                <th>Serial</th>
                <th>Coverage Type</th>
            </tr>
        </tfoot>
 
        <tbody>
            <tr ng-repeat="quote in quoteDOList" >
            	<div> <a ng-click="editMachine(machine.machineId)">
                	<td>{{quote.id.quoteId}}</td></a>
                </div>
                <td>{{quote.manufacturerDO.name}}</td>
                <td>{{quote.machineModel}}</td>
                <td>{{quote.machineSerial}}</td>
                <td>{{quote.coverageTerm}}</td>
            </tr>
        </tbody>
    </table>
			
<!-- end data table section -->
             
</article>
<!-- /Article -->