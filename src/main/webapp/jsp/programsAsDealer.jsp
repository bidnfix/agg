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
						<label for="name" class="col-sm-3 control-label">Model</label>
						<div class="col-sm-9">
							<!--  <select name="machine" ng-model="program.modelDO" ng-options="machine.name for machine in modelList">
		     				</select> -->
		     				<div class="input-group">
		     				<select name="modelName" ng-model="program.machineInfoDO" ng-options="machineInfooDO.model for machineInfooDO in program.machineInfoDOList" 
										ng-change="getRemProgDetails(program.machineInfoDO)">
     								</select> 
     								</div>
						</div>
					</div>	
                        
                        <div class="form-group">
							<label class="cols-sm-2 control-label">Model Year</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<input type="text" id="modelYear" name="modelYear" ng-model="program2.modelYear" placeholder="Model Year" class="form-control" required="required">
								</div>
							</div>
						</div>
                        
                        
                        <div class="form-group">
							<label class="cols-sm-2 control-label">Serial Number</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<input type="text" id="serialNum" name="serialNum" ng-model="program.serialNo" placeholder="Serial Number" class="form-control" required="required">
								</div>
							</div>
						</div>
                        
                        <div class="form-group">
							<label class="cols-sm-2 control-label" for="enrollment">Hours at Enrollment</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<input type="text" id="enrollment" name="addenrollmentress1" ng-model="program.enrolement" placeholder="Hours at Enrollment" class="form-control" required="required">
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="cols-sm-2 control-label" for="coverage">Start Date of Coverage</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<input type="date" id="coverage" name="coverage" ng-model="program.coverage" placeholder="Start Date of Coverage" class="form-control" required="required">
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="cols-sm-2 control-label" for="provisions">Unusual Provisions</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<input type="textarea" id="provisions" name="provisions" ng-model="program.provisions" placeholder="Unusual Provisions" class="form-control" required="required">
								</div>
							</div>
						</div>
                        
                         <div class="form-group ">
							<button class="btn btn-primary btn-lg btn-block login-button" type="submit">Submit</button>
						</div>
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
									<input type="text" id="condition" name="condition" ng-model="program.condition" class="form-control" required="required">
								</div>
							</div>
						</div>

						<div class="form-group">
							<label class="cols-sm-2 control-label" for="state">Type of Coverage</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<input type="text" id="cType" name="cType" ng-model="program.cType" placeholder="Coverage type" class="form-control" 
									required="required">
								</div>
							</div>
						</div>
                        
                        
                        
                  <div class="form-group">
							<label class="cols-sm-2 control-label" for="zip">Coverage Term</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<input type="text" id="cTerm" name="cTerm" ng-model="program.cTerm" class="form-control" required="required">
								</div>
							</div>
						</div>      
                        
                        
                        
            		<div class="form-group">
							<label class="cols-sm-2 control-label" for="marketEmail">Hours Covered</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<input type="number" id="cHour" name="cHour" ng-model="program.cHour" class="form-control" required="required">
								</div>
							</div>
						</div>
						
					<div class="form-group">
							<label class="cols-sm-2 control-label" for="invoiceEmail">Deductible</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<input type="number" id="deductible" name="deductible" ng-model="program.deductible" class="form-control" required="required">
								</div>
							</div>
						</div>            
                        
              		<div class="form-group">
							<label class="cols-sm-2 control-label" for="phone">Limit of Liability</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<input type="number" id="lol" name="lol" ng-model="program.lol" class="form-control" required="required">
								</div>
							</div>
						</div>          
                        

					<div class="form-group">
							<label class="cols-sm-2 control-label" for="dealerUrl">Cost</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<input type="number" id="cost" name="cost" ng-model="program.cost" class="form-control" required="required">
								</div>
							</div>
						</div>	
						
						<div class="form-group">
							<label class="cols-sm-2 control-label" for="dealerUrl">Program Description</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<input type="text" id="desc" name="desc" ng-model="program.desc" class="form-control" required="required">
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
	
	