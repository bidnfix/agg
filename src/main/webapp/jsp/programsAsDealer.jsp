<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Article main content -->
<article class="col-md-9 maincontent">
	<header class="page-header">
             	<div class="col-md-8 col-sm-12">
		<div class="sec-title">
                     
				<h2 class="wow animated bounceInLeft">Programs Info</h2>
			</div>
                     </div>
                     <c:if test="${user.roleName eq 'admin'}">
                     	<div class="col-md-4 col-sm-12"><a class="btn btn-primary pull-right  hvr-pulse" href="#/agg/addPrograms">Add New</a></div>
                     </c:if>
	</header>

	<!-- container -->
	<div class="container"> 

		<div class="row">
			
			<!-- Article main content -->
			<article class="col-md-12 maincontent">
				
                
                
				 <!-- data table section -->
                
                <div class="inner-main">
                	
                    <div class="main-login main-center" ng-controller="ProgramAsDealerController">
                    <h2 class="mar-bot">Vew as Dealer</h2>
                    <p class="success-msg" id="successMsg" hidden="true"></p>
                    <p class="err-msg" id="errMsg" hidden="true"></p>
                    <form role="form" ng-submit="submitDealer()">
					<div class="col-md-6">
                    
                    <div class="form-group">
							<label class="cols-sm-2 control-label" for="userName">Select a Program</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<select name="programName" ng-model="program.programDO" ng-options="program.name for program in programList" 
										ng-change="getProgDetails(program.programDO)">
     								</select> 
								</div>
							</div>
						</div>
                        
                        
                        <div class="form-group">
							<label class="cols-sm-2 control-label" for="password">Manufacturer</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<input type="text" ng-model="program.manufacturerDO.name" placeholder="Manufacturer" id="manfName" name="manfName" class="form-control" required="required">
								</div>
							</div>
						</div>
                        
                        
                        <div class="form-group">
							<label class="cols-sm-2 control-label" for="name">Model</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<input type="text" ng-model="program.model" placeholder="Dealer Name" id="name" name="name" class="form-control" required="required">
								</div>
							</div>
						</div>
                        
                        <div class="form-group">
							<label class="cols-sm-2 control-label" for="firstName">Model Year</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<input type="text" id="modelYear" name="firstName" ng-model="dealer.firstName" placeholder="First Name" class="form-control" required="required">
								</div>
							</div>
						</div>
                        
                        
                        <div class="form-group">
							<label class="cols-sm-2 control-label" for="lastName">Serial Number</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<input type="text" id="lastName" name="lastName" ng-model="dealer.lastName" placeholder="Last Name" class="form-control" required="required">
								</div>
							</div>
						</div>
                        
                        <div class="form-group">
							<label class="cols-sm-2 control-label" for="address1">Hours at Enrollment</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<input type="text" id="address1" name="address1" ng-model="dealer.address1" placeholder="Address 1" class="form-control" required="required">
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="cols-sm-2 control-label" for="address1">Start Date of Coverage</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<input type="date" id="address1" name="address1" ng-model="dealer.address1" placeholder="Address 1" class="form-control" required="required">
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="cols-sm-2 control-label" for="address1">Unusual Provisions</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<input type="textarea" id="address1" name="address1" ng-model="dealer.address1" placeholder="Address 1" class="form-control" required="required">
								</div>
							</div>
						</div>
                        
                         <div class="form-group ">
							<button class="btn btn-primary btn-lg btn-block login-button" type="submit">Submit</button>
						</div>
                        
                       <!--  <div class="form-group">
							<label class="cols-sm-2 control-label" for="address2">Address2</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"><i aria-hidden="true" class="fa fa-book fa"></i></span>
									<input type="text" id="address2" name="address2" ng-model="dealer.address2" placeholder="Address 2" class="form-control">
								</div>
							</div>
						</div>
                     -->
                    </div>
                    
                    
                    
                    <div class="col-md-6">

						
						<div class="form-group">
						<div class="cols-sm-10">
							<h3 class="mar-bot">Program Details</h3>
							
							</div>
							<div class="cols-sm-10">
							<label class="cols-sm-2 control-label" for="city">Condition</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<input type="text" id="city" name="city" ng-model="dealer.city" placeholder="City" class="form-control" required="required">
								</div>
							</div>
						</div>

						<div class="form-group">
							<label class="cols-sm-2 control-label" for="state">Type of Coverage</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<input type="text" id="city" name="city" ng-model="program.cType" placeholder="Coverage type" class="form-control" required="required">
								</div>
							</div>
						</div>
                        
                        
                        
                  <div class="form-group">
							<label class="cols-sm-2 control-label" for="zip">Coverage Term</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<input type="text" id="zip" name="zip" ng-model="dealer.zip" placeholder="Zip Code" class="form-control" required="required">
								</div>
							</div>
						</div>      
                        
                        
                        
            		<div class="form-group">
							<label class="cols-sm-2 control-label" for="marketEmail">Hours Covered</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<input type="text" id="marketEmail" name="marketEmail" ng-model="dealer.marketEmail" placeholder="Market Email" class="form-control" required="required">
								</div>
							</div>
						</div>
						
					<div class="form-group">
							<label class="cols-sm-2 control-label" for="invoiceEmail">Deductible</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<input type="text" id="invoiceEmail" name="invoiceEmail" ng-model="dealer.invoiceEmail" placeholder="Invoice Email" class="form-control" required="required">
								</div>
							</div>
						</div>            
                        
              		<div class="form-group">
							<label class="cols-sm-2 control-label" for="phone">Limit of Liability</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<input type="number" id="phone" name="phone" ng-model="dealer.phone" placeholder="Phone Number" class="form-control" required="required">
								</div>
							</div>
						</div>          
                        

					<div class="form-group">
							<label class="cols-sm-2 control-label" for="dealerUrl">Cost</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<input type="text" id="dealerUrl" name="dealerUrl" ng-model="dealer.dealerUrl" placeholder="URL" class="form-control" required="required">
								</div>
							</div>
						</div>	
						
						<div class="form-group">
							<label class="cols-sm-2 control-label" for="dealerUrl">Program Description</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<input type="text" id="dealerUrl" name="dealerUrl" ng-model="dealer.dealerUrl" placeholder="URL" class="form-control" required="required">
								</div>
							</div>
						</div>	
						
						<br/>
						<br/>
						<div class="cols-sm-10">
							<h4 class="mar-bot">Customer Information</h4>
							
							</div>
						
						<div class="form-group">
							<label class="cols-sm-2 control-label" for="dealerUrl">Name</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<input type="text" id="dealerUrl" name="dealerUrl" ng-model="dealer.dealerUrl" placeholder="URL" class="form-control" required="required">
								</div>
							</div>
						</div>	
						
						<div class="form-group">
							<label class="cols-sm-2 control-label" for="dealerUrl">Address</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<input type="text" id="dealerUrl" name="dealerUrl" ng-model="dealer.dealerUrl" placeholder="URL" class="form-control" required="required">
								</div>
							</div>
						</div>	
						
						<div class="form-group">
							<label class="cols-sm-2 control-label" for="dealerUrl">City</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<input type="text" id="dealerUrl" name="dealerUrl" ng-model="dealer.dealerUrl" placeholder="URL" class="form-control" required="required">
								</div>
							</div>
						</div>	
						
						<div class="form-group">
							<label class="cols-sm-2 control-label" for="dealerUrl">State/Province</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<input type="text" id="dealerUrl" name="dealerUrl" ng-model="dealer.dealerUrl" placeholder="URL" class="form-control" required="required">
								</div>
							</div>
						</div>	
						
						<div class="form-group">
							<label class="cols-sm-2 control-label" for="dealerUrl">Zip</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<input type="text" id="dealerUrl" name="dealerUrl" ng-model="dealer.dealerUrl" placeholder="URL" class="form-control" required="required">
								</div>
							</div>
						</div>	
						
						<div class="form-group">
							<label class="cols-sm-2 control-label" for="dealerUrl">Phone Number</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<input type="text" id="dealerUrl" name="dealerUrl" ng-model="dealer.dealerUrl" placeholder="URL" class="form-control" required="required">
								</div>
							</div>
						</div>	
						
						<div class="form-group">
							<label class="cols-sm-2 control-label" for="dealerUrl">Email</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<input type="text" id="dealerUrl" name="dealerUrl" ng-model="dealer.dealerUrl" placeholder="URL" class="form-control" required="required">
								</div>
							</div>
						</div>	
                        
                       
                        
</div>
	</form>
				</div>
                    
                    
                </div><!--inner main-->
			
			<!-- end data table section -->
                
			</article>
			<!-- /Article -->

		</div>
	</div>	<!-- /container -->
	
	