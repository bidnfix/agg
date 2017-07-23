<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport"    content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author"      content="AgGuard">
	
	<title>Agguard</title>
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
<link rel="stylesheet" href="/assets/css/mask.css">
<link rel="stylesheet" href="/assets/css/tab-style.css" >
<link rel="stylesheet" href="/assets/css/spinner.css" >


<!--data table css-->
<link href="/assets/css/dataTables.bootstrap.min.css" rel="stylesheet">

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
	<script src="/assets/js/html5shiv.js"></script>
	<script src="/assets/js/respond.min.js"></script>
	<![endif]-->
	<script src="/assets/js/jquery.min.js"></script>
	<script src="/assets/js/jquery.dataTables.min.js"></script>
	<script src="/assets/js/dataTables.bootstrap.min.js"></script>
	<script src="/assets/js/angular.min.js"></script>
	<script src="/assets/js/angular-route.min.js"></script>
	<script src="/assets/js/angular-modal-service.min.js"></script>
	<script src="/assets/js/FileSaver.js"></script>
	<script src="/assets/js/FileSaver.min.js"></script>
	<script src="/assets/js/Chart.min.js"></script>
</head>
<body ng-app="aggRoutingApp">
	<div class="mask" id="popup_mask" style="z-index: 10001; display: none;"> </div>
	<jsp:include page="spinner.jsp"></jsp:include>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="container"> 
		<div class="row">
			<jsp:include page="leftNav.jsp"></jsp:include>
			<div ng-view></div>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
	<!-- JavaScript libs are placed at the end of the document so the pages load faster -->
	<script src="/assets/js/jquery.min.js"></script>
	<script src="/assets/js/bootstrap.min.js"></script>
     <!--side nav js-->
  	<script src="/assets/js/jquery.min.js"></script>
     <!--data table js-->
    
	<script src="/assets/js/jquery.dataTables.min.js"></script>
	<script src="/assets/js/dataTables.bootstrap.min.js"></script>
	<script src="/assets/js/ui-bootstrap-tpls.min-0.13.0.js"></script>
    
	<script src="/assets/js/headroom.min.js"></script>
	<script src="/assets/js/jQuery.headroom.min.js"></script>
	<script src="/assets/js/custom.js"></script>
	<script src="/assets/js/leftNav.js"></script>
	<script src="/assets/js/service/dealerService.js"></script>
	<script src="/assets/js/controller/dealerController.js"></script>
	<script src="/assets/js/service/machineService.js"></script>
	<script src="/assets/js/controller/machineController.js"></script>
	<script src="/assets/js/controller/programController.js"></script>
	<script src="/assets/js/service/programService.js"></script>
	<script src="/assets/js/service/claimService.js"></script>
	<script src="/assets/js/mask.js"></script>
	<script src="/assets/js/popup.js"></script>
	<script src="/assets/js/tabs.js"></script>
	<script src="/assets/js/navAccordion.min.js"></script>
	<script src="/assets/js/controller/reportaBugController.js"></script>
	<script src="/assets/js/service/reportaBugService.js"></script>
	<script src="/assets/js/controller/claimController.js"></script>
	<script src="/assets/js/service/claimService.js"></script>
	<script src="/assets/js/angular-validator.js"></script>
	<script src="/assets/js/service/quoteService.js"></script>
	<script src="/assets/js/service/contractService.js"></script>
	<script src="/assets/js/controller/reportsController.js"></script>
	<script src="/assets/js/controller/useOfEquipController.js"></script>
</body>
</html>