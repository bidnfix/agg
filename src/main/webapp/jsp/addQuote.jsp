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
							<select class="form-control" name="dealer" ng-model="quote.dealerDO" id="dealer" ng-options="dealer.name for dealer in dealerList" required="required">
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
						<label for="coverageEndDate" class="col-sm-4 control-label">
							End Date of Manufacturer's Base Coverage:
						</label>
						<div class="col-sm-4">
							<input type="date" id="coverageEndDate" name="coverageEndDate" ng-model="quote.coverageEndDate" class="form-control">
						</div>
					</div>
					<img src="/assets/images/info-icon.png" alt="Info" 
							data-toggle="tooltip" 
							tooltip-trigger tooltip-animation="false" 
				          	tooltip="The 'End Date' is the last day that the Manufacturer's base (powertrain) warranty is in effect-the day it expires. This is vital information because the AgGuard extended service contract will not take effect until after the date you enter. If the date you provide is wrong, it can leave the machine without coverage or cause the coverage to cost more. Always double-check the date by confirming the warranty end date with the manufacturer."
				          	tooltip-placement="top">
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
            <div class="inner-main">
            	<p>Determine Dealer Markup: Enter the Dealer Mark-Up You want calculated into this quote as either a percentage or specific dollar amount</p>
            	<p>Recommended Markup:	Your Dealership does not currently have a recommended markup.</p>
				<div class="form-group">
					<label for="dealerMarkup" class="col-sm-3 control-label"></label>
					<div class="col-sm-4">
						<input type="text" id="dealerMarkup" name="dealerMarkup" ng-model="quote.dealerMarkup" placeholder="Dealer Markup" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label for="dealerMarkupVlaue" class="col-sm-3 control-label"></label>
					<div class="col-sm-4">
						<input type="radio" id="dealerMarkupPrice" name="dealerMarkupPrice" ng-model="quote.dealerMarkupPrice" class="">Price
						<input type="radio" id="dealerMarkupPercent" name="dealerMarkupPercent" ng-model="quote.dealerMarkupPercent" class="">Percent
					</div>
				</div>
				<div class="form-group">
					<label for="coverageTerm" class="col-sm-3 control-label">Choose one deductible amount and one coverage term:</label>
					<div class="col-sm-4">
						Deductible:&nbsp;&nbsp;<input type="radio" id="deductiblePrice" name="deductiblePrice" ng-model="quote.deductiblePrice" class="" value="250"> $250
						<input type="radio" id="deductiblePrice" name="deductiblePrice" ng-model="quote.deductiblePrice" class="" value="500"> $500
					</div>
				</div>
				<div class="form-group">
					<label for="model" class="col-sm-3 control-label"></label>
					<div class="col-sm-4">
						Coverage Term:&nbsp;&nbsp;<input type="radio" id="coverageTerm" name="coverageTerm" ng-model="quote.coverageTerm" class="" value="12"> 12 mos.
						<input type="radio" id="coverageTerm" name="coverageTerm" ng-model="quote.coverageTerm" class="" value="24"> 24 mos.
					</div>
				</div>
				<div class="form-group">
					<label for="modelNumber" class="col-sm-3 control-label">Please enter the customer's information below Or assign a "Nickname" to this quote.</label>
					<div class="col-sm-4">
					</div>
				</div>
				<div class="form-group">
					<label for="dealerName" class="col-sm-3 control-label">Name/Nickname</label>
					<div class="col-sm-4">
						<input type="text" id="dealerName" name="dealerName" ng-model="quote.dealerName" placeholder="Dealer Name" class="form-control" required="required">
					</div>
				</div>
				<div class="form-group">
					<label for="dealerAddress" class="col-sm-3 control-label">Address</label>
					<div class="col-sm-4">
						<input type="text" id="dealerAddress" name="dealerAddress" ng-model="quote.dealerAddress" placeholder="Dealer Address" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label for="dealerCity" class="col-sm-3 control-label">City</label>
					<div class="col-sm-4">
						<input type="text" id="dealerCity" name="dealerCity" ng-model="quote.dealerCity" placeholder="Dealer City" class="form-control" required="required">
					</div>
				</div>
				<div class="form-group">
					<label for="dealerState" class="col-sm-3 control-label">State/Province</label>
					<div class="col-sm-4">
						<select class="form-control" name="dealerState" ng-model="quote.dealerState" id="dealerState" required="required">
							<option value="">Select State/Province</option>
							<option value="AL">Alabama</option>
							<option value="AK">Alaska</option>
							<option value="AZ">Arizona</option>
							<option value="AR">Arkansas</option>
							<option value="CA">California</option>
							<option value="CO">Colorado</option>
							<option value="CT">Connecticut</option>
							<option value="DE">Delaware</option>
							<option value="DC">District Of Columbia</option>
							<option value="FL">Florida</option>
							<option value="GA">Georgia</option>
							<option value="HI">Hawaii</option>
							<option value="ID">Idaho</option>
							<option value="IL">Illinois</option>
							<option value="IN">Indiana</option>
							<option value="IA">Iowa</option>
							<option value="KS">Kansas</option>
							<option value="KY">Kentucky</option>
							<option value="LA">Louisiana</option>
							<option value="ME">Maine</option>
							<option value="MD">Maryland</option>
							<option value="MA">Massachusetts</option>
							<option value="MI">Michigan</option>
							<option value="MN">Minnesota</option>
							<option value="MS">Mississippi</option>
							<option value="MO">Missouri</option>
							<option value="MT">Montana</option>
							<option value="NE">Nebraska</option>
							<option value="NV">Nevada</option>
							<option value="NH">New Hampshire</option>
							<option value="NJ">New Jersey</option>
							<option value="NM">New Mexico</option>
							<option value="NY">New York</option>
							<option value="NC">North Carolina</option>
							<option value="ND">North Dakota</option>
							<option value="OH">Ohio</option>
							<option value="OK">Oklahoma</option>
							<option value="OR">Oregon</option>
							<option value="PA">Pennsylvania</option>
							<option value="RI">Rhode Island</option>
							<option value="SC">South Carolina</option>
							<option value="SD">South Dakota</option>
							<option value="TN">Tennessee</option>
							<option value="TX">Texas</option>
							<option value="UT">Utah</option>
							<option value="VT">Vermont</option>
							<option value="VA">Virginia</option>
							<option value="WA">Washington</option>
							<option value="WV">West Virginia</option>
							<option value="WI">Wisconsin</option>
							<option value="WY">Wyoming</option>
							<option value="AB">Alberta</option>
							<option value="BC">British Columbia</option>
							<option value="MB">Manitoba</option>
							<option value="NB">New Brunswick</option>
							<option value="NL">Newfoundland and Labrador</option>
							<option value="NS">Nova Scotia</option>
							<option value="ON">Ontario</option>
							<option value="PE">Prince Edward Island</option>
							<option value="QC">Quebec</option>
							<option value="SK">Saskatchewan</option>
							<option value="NT">Northwest Territories</option>
							<option value="NU">Nunavut</option>
							<option value="YT">Yukon</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="dealerZip" class="col-sm-3 control-label">Zip</label>
					<div class="col-sm-4">
						<input type="text" id="dealerZip" name="dealerZip" ng-model="quote.dealerZip" placeholder="Zip" class="form-control" required="required">
					</div>
				</div>
				<div class="form-group">
					<label for="dealerPhone" class="col-sm-3 control-label">Phone Number</label>
					<div class="col-sm-4">
						<input type="text" id="dealerPhone" name="dealerPhone" ng-model="quote.dealerPhone" placeholder="Phone Number" class="form-control" required="required">
					</div>
				</div>
				<div class="form-group">
					<label for="dealerEmail" class="col-sm-3 control-label">Email Address</label>
					<div class="col-sm-4">
						<input type="text" id="dealerEmail" name="dealerEmail" ng-model="quote.dealerEmail" placeholder="Email" class="form-control" required="required"> 
					</div>
				</div>
				<p>If you cannot find the coverage you want, please select the Help Request form from the black menu bar at the top of the page and we will communicate with you soon to explore other options.</p>
				<div class="form-group">
					<div class="col-sm-5 col-sm-offset-3">
						<button type="button" class="btn btn-primary btn-block">Continue to "Quote Summary"</button>
					</div>
				</div>
			</div>
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