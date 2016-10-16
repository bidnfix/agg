	<!-- Article main content -->
			<article id="addQuoteDiv" class="col-md-9 maincontent">
      <div class="col-xs-12 no-pad marg10-top border clearfix">
        <div class="col-sm-3">
          Dealer: <br><span style="color:#B8C159">{{(quote.dealerDO.name != null && quote.dealerDO.name != "")?quote.dealerDO.name:""}}</span>
        </div>
        <div class="col-sm-2 no-pad">
          Quote ID: <br><span style="color:#B8C159">{{(quote.quoteId != null && quote.quoteId != "")?quote.quoteId:""}}</span>
        </div>
        <div class="col-sm-2 no-pad">
          Serial#: <br><span style="color:#B8C159">{{(quote.serialNumber != null && quote.serialNumber != "")?quote.serialNumber:""}}</span>
        </div>
        <div class="col-sm-3 no-pad">
          Quote Status: <br><span style="color:#B8C159">{{(quote.statusDesc != null && quote.statusDesc != "")?quote.statusDesc:""}}</span>
        </div>
      </div>
				<header class="page-header col-xs-12 no-pad clearfix">
                	<div class="o-container">

    <div class="o-section">
      <div id="quoteTabs" class="c-tabs no-js">
        <div class="c-tabs-nav">
          <a href="#" class="c-tabs-nav__link is-active">
            <i class="fa fa-shield"></i>
            <span>Warranty Info</span>
          </a>
          <a href="#" class="c-tabs-nav__link" ng-click="changeTab(1, warrantyInfoForm)">
            <i class="fa fa-gears"></i>
            <span>Machine Info</span>
          </a>
          <a href="#" class="c-tabs-nav__link" ng-click="changeTab(2, machineInfoForm)">
            <i class="fa fa-info-circle"></i>
            <span>Coverage Info</span>
          </a>
          <a href="#" class="c-tabs-nav__link" ng-click="changeTab(3, coverageInfoForm)">
            <i class="fa fa-file-text-o"></i>
            <span>Quote Summary</span>
          </a>
          <a href="#" class="c-tabs-nav__link">
            <i class="fa fa-money"></i>
            <span>Purchase</span>
          </a>
        </div>
        <div class="c-tab is-active col-xs-12 no-pad clearfix">
          <div class="c-tab__content col-xs-12  clearfix">
          	<jsp:include page="warrantyInfo.jsp"/>
          </div>
        </div>
        <div class="c-tab col-xs-12 no-pad clearfix">
          <div class="c-tab__content">
            <jsp:include page="machineQuoteInfo.jsp"></jsp:include>
          </div>
        </div>
        <div class="c-tab col-xs-12 no-pad clearfix">
          <div class="c-tab__content">
			<jsp:include page="coverageInfo.jsp"></jsp:include>
          </div>
        </div>
        <div class="c-tab col-xs-12 no-pad clearfix">
          <div class="c-tab__content">
			<jsp:include page="quoteSummary.jsp"></jsp:include>
          </div>
        </div>
        <div class="c-tab col-xs-12 no-pad clearfix">
          <div class="c-tab__content">
			<jsp:include page="purchaseInfo.jsp"></jsp:include>
          </div>
        </div>
      </div>
    </div>
  </div>
			
	</header>
                
                
				 <!-- data table section -->
                
                
			
			<!-- end data table section -->
                
			</article>
			<!-- /Article -->
