<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!-- Sidebar -->
<aside class="col-md-3 sidebar sidebar-left">

				<!-- Navigation -->
	<nav class="mainNav" ng-controller="activateTabCtrl">
      <!-- <h3>Headding Comes Here</h3> -->
		<ul id="leftTabs">
			<c:forEach items="${user.userMenuDOList}" var="userMenus">
			   <c:choose>
				    <c:when test="${ not empty userMenus.userSubMenuDOList}">
				       <li class="animated fadeInLeftBig">
					       <a id="${fn:replace(fn:toLowerCase(userMenus.name), ' ', '')}" href="#${userMenus.url}" ng-click="activateTab($event);">${userMenus.name}</a>
					       <ul>
						       <c:forEach items="${userMenus.userSubMenuDOList}" var="userSubMenus">
						       		<li><a id="${fn:replace(fn:replace(fn:toLowerCase(userSubMenus.name), ' ', ''), '&','')}" href="#${userSubMenus.url}" ng-click="activateTab($event);">${userSubMenus.name}</a></li>
						       </c:forEach>
					       </ul>
				       </li>
				    </c:when>
				    <c:when test="${fn:replace(fn:toLowerCase(userMenus.name), ' ', '') eq 'logout'}">
				        <li class="animated fadeInLeft"><a id="${fn:replace(fn:toLowerCase(userMenus.name), ' ', '')}" href="${userMenus.url}" ng-click="activateTab($event);">${userMenus.name}</a></li>
				    </c:when>
				    <c:otherwise>
				        <li class="animated fadeInLeft"><a id="${fn:replace(fn:toLowerCase(userMenus.name), ' ', '')}" href="#${userMenus.url}" ng-click="activateTab($event);">${userMenus.name}</a></li>
				    </c:otherwise>
				</c:choose>
    		</c:forEach>
			<%-- <li class="animated fadeInLeft"><a id="worklist" href="#" ng-click="activateTab($event);">WORKLIST</a></li>
            <li class="animated fadeInLeft"><a id="serviceDesk" href="#/agg/programs" ng-click="activateTab($event);">SERVICE DRIVE</a></li>
            <li class="animated fadeInLeft"><a id="userTrack" href="#" ng-click="activateTab($event);">USER TRACK</a></li>
            <li class="animated fadeInLeft"><a id="archivedQuotes" href="#" ng-click="activateTab($event);">ARCHIVED QUOTES</a></li>
            <li class="animated fadeInLeft"><a id="adminSearch" href="#" ng-click="activateTab($event);">ADMIN SEARCH</a></li>
            <li class="animated fadeInLeftBig"><a id="machineSearch" href="#/agg/machineInfo" ng-click="activateTab($event);">MACHINE MANAGEMENT</a>
				<ul>
					<li><a id="manageLocations" href="#/agg/addMachine" ng-click="activateTab($event);">ADD MACHINE</a></li>
					<!-- <li><a id="manageUsers" href="#/agg/editMachine" ng-click="activateTab($event);">EDIT MACHINE</a></li> -->
				</ul>
			</li>
            <li class="animated fadeInLeft"><a id="quoteSearch" href="#" ng-click="activateTab($event);">QUOTE SEARCH</a></li>
            <li class="animated fadeInLeftBig"><a id="dealerManagement" href="#/agg/dealers" ng-click="activateTab($event);">DEALER MANAGEMENT</a>
				<ul>
					<c:if test="${user.roleName eq 'Dealer Admin'}">
					<li><a id="manageDealers" href="#/agg/addDealer" ng-click="activateTab($event);">ADD DEALER</a></li>
					</c:if>
					<!-- <li><a id="manageLocations" href="#/agg/addLocation" ng-click="activateTab($event);">MANAGE LOCATIONS</a></li> -->
					<!-- <li><a id="manageUsers" href="#/agg/addUser" ng-click="activateTab($event);">MANAGE USERS</a></li> -->
					<li><a id="pendingDealers" href="#/agg/pendingDealers" ng-click="activateTab($event);">PENDING DEALERS</a></li>
					<!-- <li><a id="dealerSearch" href="#" ng-click="activateTab($event);">DEALER SEARCH</a></li>  -->
				</ul>
			</li>
			<li class="animated fadeInLeftBig"><a id="userManagement" href="#/agg/users" ng-click="activateTab($event);">USER MANAGEMENT</a>
				<ul>
					<li><a id="manageUsers" href="#/agg/addUser" ng-click="activateTab($event);">ADD USER</a></li>
				</ul>
			</li>
			<li class="animated fadeInLeftBig"><a id="claims" href="#/agg/claimsInfo" ng-click="activateTab($event);">CLAIMS MANAGEMENT</a>
				<ul>
					<li><a id="fileClaim" href="#" ng-click="activateTab($event);">FILE A CLAIM</a></li>
					<li><a id="myClaims" href="#" ng-click="activateTab($event);">MY CLAIMS</a></li>
					<li><a id="archivedClaims" href="#/agg/addDealer" ng-click="activateTab($event);">ARCHIVED CLAIMS</a></li>
					<li><a id="fileClaimHelp" href="#" ng-click="activateTab($event);">HOW TO FILE A CLAIM</a></li>
				</ul>
			</li>
            <li class="animated fadeInLeft"><a id="termsConditions" href="#" ng-click="activateTab($event);">TERMS AND CONDITIONS</a></li>
            <li class="animated fadeInLeft"><a id="support" href="#" ng-click="activateTab($event);">SUPPORT</a></li>
            <li class="animated fadeInLeft"><a id="quotes" href="#/agg/addQuote" ng-click="activateTab($event);">QUOTES</a></li>
            <li class="animated fadeInLeft"><a id="reports" href="#" ng-click="activateTab($event);">REPORTS</a></li>
            <li class="animated fadeInLeft"><a href="/agg/logout">LOGOUT</a></li>
            <li class="gree animated fadeInLeft"><a id="help" href="#" ng-click="activateTab($event);">HELP</a></li> --%>
		</ul>
	</nav>
				
				

			</aside>
			<!-- /Sidebar -->