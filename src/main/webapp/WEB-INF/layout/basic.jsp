
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport"    content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author"      content="AgGuard">
	
	<title><tiles:getAsString name="title" /></title>
<link rel="favicon" href="/assets/images/favicon.png">
<link rel="stylesheet" media="screen" href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,700">
<link rel="stylesheet" href="/assets/css/bootstrap.min.css">
<link rel="stylesheet" href="/assets/css/font-awesome.min.css">
<!-- Custom styles for our template -->
<link rel="stylesheet" href="/assets/css/bootstrap-theme.css" media="screen" >
<link rel="stylesheet" type="text/css" href="/assets/css/da-slider.css" />
<link rel="stylesheet" href="/assets/css/style.css">
<link href="/assets/css/animate.min.css" rel="stylesheet" />
<link rel="stylesheet" href="/assets/css/hover.css">

<!--data table css-->
<link href="https://cdn.datatables.net/1.10.9/css/dataTables.bootstrap.min.css" rel="stylesheet">

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
	<script src="/assets/js/html5shiv.js"></script>
	<script src="/assets/js/respond.min.js"></script>
	<![endif]-->
<script src="/assets/js/angular.min.js"></script>
</head>
<body ng-app="aggRoutingApp">
	<tiles:insertAttribute name="header" />
	<div class="container"> 
	<div class="row">
	<tiles:insertAttribute name="leftNav" />
	<tiles:insertAttribute name="body" />
	<div ng-view></div>
	</div>
	</div>
	<tiles:insertAttribute name="footer" />
	<!-- JavaScript libs are placed at the end of the document so the pages load faster -->
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
     <!--side nav js-->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	<script src="/assets/js/navAccordion.min.js"></script>
	<script>
		jQuery(document).ready(function(){
		
			//Accordion Nav
			jQuery('.mainNav').navAccordion({
				expandButtonText: '<i class="fa fa-plus"></i>',  //Text inside of buttons can be HTML
				collapseButtonText: '<i class="fa fa-minus"></i>'
			}, 
			function(){
				console.log('Callback')
			});
			
		});
	</script> 
    
     <!--data table js-->
    
	<script src="https://cdn.datatables.net/1.10.9/js/jquery.dataTables.min.js"></script>
	<script src="https://cdn.datatables.net/1.10.9/js/dataTables.bootstrap.min.js"></script>
    
    <!--data table plugin-->
    <script>
	$(document).ready(function() {
    $('#table1').DataTable();
} );
</script><!--data table plugin end-->
    
	<script src="/assets/js/headroom.min.js"></script>
	<script src="/assets/js/jQuery.headroom.min.js"></script>
	<script src="/assets/js/custom.js"></script>
	<script src="/assets/js/leftNav.js"></script>
</body>
</html>