<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Article main content -->
<article class="col-md-9 maincontent">
	<header class="page-header">
             	<div class="col-md-8 col-sm-12">
		<div class="sec-title">
                     
				<h3 class="wow animated bounceInLeft">{{reportName}}</h3>
				<!-- <p class="wow animated bounceInRight">The Key Features of our Tool</p> -->
			</div>
                     </div>
                     <!-- <div class="col-md-4 col-sm-12"><a class="btn btn-primary pull-right  hvr-pulse" href="#">Add New</a></div> -->
	</header>
             
    <div id="container" style="{{widthStyle}}">
        <canvas id="graphReport"></canvas>
    </div>         
	
             
</article>
<!-- /Article -->