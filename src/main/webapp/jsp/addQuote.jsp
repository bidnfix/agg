<!-- Article main content -->
			<article class="col-md-9 maincontent">
				<header class="page-header">
                	<div class="o-container">

    <div class="o-section">
      <div id="quoteTabs" class="c-tabs no-js">
        <div class="c-tabs-nav">
          <a href="#" class="c-tabs-nav__link is-active">
            <i class="fa fa-home"></i>
            <span>Warranty Info</span>
          </a>
          <a href="#" class="c-tabs-nav__link">
            <i class="fa fa-book"></i>
            <span>Machine Info</span>
          </a>
          <a href="#" class="c-tabs-nav__link">
            <i class="fa fa-heart"></i>
            <span>Coverage Info</span>
          </a>
          <a href="#" class="c-tabs-nav__link">
            <i class="fa fa-calendar"></i>
            <span>Quote Summary</span>
          </a>
          <a href="#" class="c-tabs-nav__link">
            <i class="fa fa-cog"></i>
            <span>Next Steps</span>
          </a>
          
          <a href="#" class="c-tabs-nav__link">
            <i class="fa fa-cog"></i>
            <span>Purchase</span>
          </a>
          
        </div>
        <form class="form-horizontal">
        <div class="c-tab is-active">
          <div class="c-tab__content">
            <div class="inner-main">
					<div class="form-group">
						<label for="dealer" class="col-sm-4 control-label">Dealer</label>
						<div class="col-sm-4">
							<!-- <select class="form-control" name="dealer" ng-model="user.dealerDO" id="dealer" ng-options="dealer.userName for dealer in dealerList" required="required" ng-change="getLocation(this.id)"> -->
							<select class="form-control" name="dealer" ng-model="quote.dealerDO" id="dealer" ng-options="dealer.userName for dealer in dealerList" required="required">
								<option value="">Select Dealer</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="coverageExpired" class="col-sm-4 control-label">Check here if the Manufacturer's Coverage has expired:</label>
						<div class="col-sm-1">
							<input type="checkbox" id="coverageExpired" name="coverageExpired" ng-model="quote.coverageExpired" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label for="coverageEndDate" class="col-sm-4 control-label">End Date of Manufacturer's Base Coverage:</label>
						<div class="col-sm-4">
							<input type="date" id="coverageEndDate" name="coverageEndDate" ng-model="quote.coverageEndDate" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label for="coverageEndDateUnknown" class="col-sm-4 control-label">Check if unknown</label>
						<div class="col-sm-1">
							<input type="checkbox" id="coverageEndDateUnknown" name="coverageEndDateUnknown" ng-model="quote.coverageEndDateUnknown" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label for="coverageEndDateVerified" class="col-sm-4 control-label">Check here if the End Date has been verified with Mfr.</label>
						<div class="col-sm-1">
							<input type="checkbox" id="coverageEndDateVerified" name="coverageEndDateVerified" ng-model="quote.coverageEndDateVerified" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label">Describe the Manufacturer's Coverage Terms:</label>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label"></label>
						<div class="col-sm-3">
							Months
						</div>
						<div class="col-sm-3">
							Hours
						</div>
					</div>
					<div class="form-group">
						<label for="powerTrain" class="col-sm-4 control-label">Power Train</label>
						<div class="col-sm-2">
							<input type="text" id="powerTrainMonths" name="powerTrainMonths" ng-model="quote.powerTrainMonths" class="form-control">
						</div>
						<div class="col-sm-2">
							<input type="text" id="powerTrainHours" name="powerTrainHours" ng-model="quote.powerTrainHours" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label for="hydraulics" class="col-sm-4 control-label">Hydraulics</label>
						<div class="col-sm-2">
							<input type="text" id="hydraulicsMonths" name="hydraulicsMonths" ng-model="quote.hydraulicsMonths" class="form-control">
						</div>
						<div class="col-sm-2">
							<input type="text" id="hydraulicsHours" name="hydraulicsHours" ng-model="quote.hydraulicsHours" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label for="fullMachine" class="col-sm-4 control-label">Full Machine</label>
						<div class="col-sm-2">
							<input type="text" id="fullMachineMonths" name="fullMachineMonths" ng-model="quote.fullMachineMonths" class="form-control">
						</div>
						<div class="col-sm-2">
							<input type="text" id="fullMachineHours" name="fullMachineHours" ng-model="quote.fullMachineHours" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-5 col-sm-offset-3">
							<button type="button" class="btn btn-primary btn-block">Continue to "Machine Info"</button>
						</div>
					</div>
			</div>
			<!--inner main-->
          </div>
        </div>
        <div class="c-tab">
          <div class="c-tab__content">
            <div class="inner-main">
				<div class="form-group">
					<label for="manufacturer" class="col-sm-3 control-label">Select Manufacturer</label>
					<div class="col-sm-4">
						<select name="manufacturer" ng-model="quote.manufacturerDO" ng-options="manufacturerObj.name for manufacturerObj in manufacturerList" ng-change="getMachineType()">
	     				</select> 
					</div>
				</div>
				<div class="form-group">
					<label for="mannufacturerName" class="col-sm-3 control-label">Enter Name if not found in list</label>
					<div class="col-sm-4">
						<input type="text" id="mannufacturerName" name="mannufacturerName" ng-model="quote.mannufacturerName" placeholder="mannufacturerName" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label for="machineType" class="col-sm-3 control-label">Machine Type</label>
					<div class="col-sm-4">
						<select name="machineType" ng-model="quote.machineTypeDO" ng-options="machineType.name for machineType in machineTypeList"></select> 
					</div>
				</div>
				<div class="form-group">
					<label for="model" class="col-sm-3 control-label">Model</label>
					<div class="col-sm-4">
						<input type="text" id="model" ng-model="quote.model" placeholder="Model Name" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label for="modelNumber" class="col-sm-3 control-label">Enter Model Number if not found in list</label>
					<div class="col-sm-4">
						<input type="text" id="modelNumber" name="modelNumber" ng-model="quote.modelNumber" placeholder="Model Number" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label for="horsePower" class="col-sm-3 control-label">Horsepower (Engine)</label>
					<div class="col-sm-4">
						<input type="text" id="horsePower" name="horsePower" ng-model="quote.horsePower" placeholder="Horse Power" class="form-control" required="required">
					</div>
				</div>
				<div class="form-group">
					<label for="serialNumber" class="col-sm-3 control-label">Serial Number</label>
					<div class="col-sm-4">
						<input type="text" id="serialNumber" name="serialNumber" ng-model="quote.serialNumber" placeholder="Serial Number" class="form-control" required="required">
					</div>
				</div>
				<div class="form-group">
					<label for="serialNumberUnknown" class="col-sm-3 control-label"></label>
					<div class="col-sm-4">
						<input type="checkbox" id="serialNumberUnknown" name="serialNumberUnknown" ng-model="quote.serialNumberUnknown" class="">Check if unknown
					</div>
				</div>
				<div class="form-group">
					<label for="retailPrice" class="col-sm-3 control-label">Retail Price (Aprox)</label>
					<div class="col-sm-4">
						<input type="text" id="retailPrice" name="retailPrice" ng-model="quote.retailPrice" class="form-control" placeholder="Retail Price" required="required">
					</div>
				</div>
				<div class="form-group">
					<label for="meterHours" class="col-sm-3 control-label">Meter Hours</label>
					<div class="col-sm-4">
						<input type="text" id="meterHours" name="meterHours" ng-model="quote.meterHours" placeholder="Meter Hours" class="form-control" required="required">
					</div>
				</div>
				<div class="form-group">
					<label for="modelYear" class="col-sm-3 control-label">Model Year</label>
					<div class="col-sm-4">
						<input type="text" id="modelYear" name="modelYear" ng-model="quote.modelYear" placeholder="Model Year" class="form-control" required="required">
					</div>
				</div>
				<div class="form-group">
					<label for="equipment" class="col-sm-3 control-label">Use of Equipment</label>
					<div class="col-sm-4">
						<select name="equipment" ng-model="quote.equipment" ng-options="equipmentObj.name for equipmentObj in equipmentList"></select> 
					</div>
				</div>
				<div class="form-group">
					<label for="estSaleDate" class="col-sm-3 control-label">Estimated Sale Date</label>
					<div class="col-sm-4">
						<input type="date" id="estSaleDate" name="estSaleDate" ng-model="quote.estSaleDate" class="form-control" required="required">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-5 col-sm-offset-3">
						<button type="button" class="btn btn-primary btn-block">Continue to "Coverage Info"</button>
					</div>
				</div>
			</div>
          </div>
        </div>
        <div class="c-tab">
          <div class="c-tab__content">
            <h2>Your Favourites!</h2>
            <p>Favourites ipsum dolor sit amet, consectetur adipisicing elit. Ipsam quo minus voluptate unde tempore eveniet consequuntur in, quod animi libero rem similique pariatur quos, et eum nisi ducimus, architecto voluptatibus!</p>
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Iusto aspernatur natus dolorem fuga cumque optio saepe corrupti earum. Ipsam quaerat asperiores similique omnis excepturi temporibus ab eum magnam ipsa, odio.</p>
          </div>
        </div>
        <div class="c-tab">
          <div class="c-tab__content">
            <h2>Stay Busy</h2>
            <p>Calendar ipsum dolor sit amet, consectetur adipisicing elit. Ipsam quo minus voluptate unde tempore eveniet consequuntur in, quod animi libero rem similique pariatur quos, et eum nisi ducimus, architecto voluptatibus!</p>
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Iusto aspernatur natus dolorem fuga cumque optio saepe corrupti earum. Ipsam quaerat asperiores similique omnis excepturi temporibus ab eum magnam ipsa, odio.</p>
          </div>
        </div>
        <div class="c-tab">
          <div class="c-tab__content">
            <h2>Change It Up</h2>
            <p>Settings ipsum dolor sit amet, consectetur adipisicing elit. Ipsam quo minus voluptate unde tempore eveniet consequuntur in, quod animi libero rem similique pariatur quos, et eum nisi ducimus, architecto voluptatibus!</p>
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Iusto aspernatur natus dolorem fuga cumque optio saepe corrupti earum. Ipsam quaerat asperiores similique omnis excepturi temporibus ab eum magnam ipsa, odio.</p>
          </div>
        </div>
        
        <div class="c-tab">
          <div class="c-tab__content">
            <h2>Change It Up</h2>
            <p>Settings ipsum dolor sit amet, consectetur adipisicing elit. Ipsam quo minus voluptate unde tempore eveniet consequuntur in, quod animi libero rem similique pariatur quos, et eum nisi ducimus, architecto voluptatibus!</p>
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Iusto aspernatur natus dolorem fuga cumque optio saepe corrupti earum. Ipsam quaerat asperiores similique omnis excepturi temporibus ab eum magnam ipsa, odio.</p>
          </div>
        </div>
        </form>
      </div>
    </div>

    
  </div>
			
	</header>
                
                
				 <!-- data table section -->
                
                
			
			<!-- end data table section -->
                
			</article>
			<!-- /Article -->