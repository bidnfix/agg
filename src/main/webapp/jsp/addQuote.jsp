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
						<label for="dealer" class="col-sm-5 control-label">Dealer</label>
						<div class="col-sm-4">
							<select class="form-control" name="dealer" ng-model="quote.dealerDO" id="dealer" ng-options="dealer.name for dealer in dealerList" required="required" ng-change="displayDealerText(quote.dealerDO)">
								<option value="">Select Dealer</option>
							</select>
						</div>
					</div>
					<div class="form-group" ng-disabled="disableSelected" id="dealerTxt" ng-class="{'hided-div':disableSelected}">
						<label class="col-sm-4 control-label"></label>
						<div class="col-sm-8">
							<p>{{dealerText}}</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-5">Manufacturer's Coverage Information</label>
						<div class="col-sm-4">
						</div>
					</div>
					<div class="form-group">
						<label for="coverageExpired" class="col-sm-5 control-label">Check here if the Manufacturer's Coverage has expired:</label>
						<div class="col-sm-1">
							<input type="checkbox" id="coverageExpired" name="coverageExpired" ng-model="quote.coverageExpired" value="true" class="form-control">
						</div>
					</div>
					<div id="manfCoverageExp" ng-disabled="quote.coverageExpired" ng-class="{'hided-div':quote.coverageExpired}">
						<div class="form-group">
							<label class="col-sm-5 control-label">
								<img src="/assets/images/info-icon.png" alt="Info" 
								data-toggle="tooltip" 
								tooltip-trigger tooltip-animation="false" 
					          	tooltip="The 'End Date' is the last day that the Manufacturer's base (powertrain) warranty is in effect-the day it expires. This is vital information because the AgGuard extended service contract will not take effect until after the date you enter. If the date you provide is wrong, it can leave the machine without coverage or cause the coverage to cost more. Always double-check the date by confirming the warranty end date with the manufacturer."
					          	tooltip-placement="top">&nbsp;End Date of Manufacturer's Base Coverage:
							</label>
							<div class="col-sm-4">
								<input type="date" id="coverageEndDate" name="coverageEndDate" min="{{date | date:'yyyy-MM-dd'}}" ng-model="quote.coverageEndDate" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label for="coverageEndDateUnknown" class="col-sm-5 control-label">Check if unknown</label>
							<div class="col-sm-1">
								<input type="checkbox" id="coverageEndDateUnknown" name="coverageEndDateUnknown" ng-model="quote.coverageEndDateUnknown" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label for="coverageEndDateVerified" class="col-sm-5 control-label">Check here if the End Date has been verified with Mfr.</label>
							<div class="col-sm-1">
								<input type="checkbox" id="coverageEndDateVerified" name="coverageEndDateVerified" ng-model="quote.coverageEndDateVerified" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label"><img src="/assets/images/info-icon.png" alt="Info" 
								data-toggle="tooltip" 
								tooltip-trigger tooltip-animation="false" 
					          	tooltip="Describe the manufacturer's coverage in terms of months and hours.  For example: a machine with 36 months and 3,000 hours of manufacture's coverage on the Powertrain,  but only  18 months and 1,000 hours on the Hydraulics and only 12 months and 500 hours on the Full Machine would be depicted as follows: "
					          	tooltip-placement="top">&nbsp;Describe the Manufacturer's Coverage Terms:</label>
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
								<input type="text" id="powerTrainMonths" name="powerTrainMonths" ng-model="quote.powerTrainMonths" class="form-control" value="24">
							</div>
							<div class="col-sm-2">
								<input type="text" id="powerTrainHours" name="powerTrainHours" ng-model="quote.powerTrainHours" class="form-control" value="2000">
							</div>
						</div>
						<div class="form-group">
							<label for="hydraulics" class="col-sm-4 control-label">Hydraulics</label>
							<div class="col-sm-2">
								<input type="text" id="hydraulicsMonths" name="hydraulicsMonths" ng-model="quote.hydraulicsMonths" class="form-control" value="24">
							</div>
							<div class="col-sm-2">
								<input type="text" id="hydraulicsHours" name="hydraulicsHours" ng-model="quote.hydraulicsHours" class="form-control" value="2000">
							</div>
						</div>
						<div class="form-group">
							<label for="fullMachine" class="col-sm-4 control-label">Full Machine</label>
							<div class="col-sm-2">
								<input type="text" id="fullMachineMonths" name="fullMachineMonths" ng-model="quote.fullMachineMonths" class="form-control" value="24">
							</div>
							<div class="col-sm-2">
								<input type="text" id="fullMachineHours" name="fullMachineHours" ng-model="quote.fullMachineHours" class="form-control" value="2000">
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-5 col-sm-offset-3">
							<button type="button" class="btn btn-primary btn-block" ng-click="changeTab(1)">Continue to "Machine Info"</button>
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
						<select class="form-control" name="manufacturer" ng-model="quote.manufacturerDO" ng-options="manufacturerObj.name for manufacturerObj in manufacturerList" ng-change="getMachineModel(quote.manufacturerDO)">
							<option value="">Select Manufacturer</option>
	     				</select> 
					</div>
				</div>
				<!-- <div class="form-group">
					<label for="mannufacturerName" class="col-sm-3 control-label">Enter Name if not found in list</label>
					<div class="col-sm-4">
						<input type="text" id="mannufacturerName" name="mannufacturerName" ng-model="quote.mannufacturerName" placeholder="Mannufacturer Name" class="form-control">&nbsp;<img src="/assets/images/info-icon.png" alt="Info" 
								data-toggle="tooltip" 
								tooltip-trigger tooltip-animation="false" 
					          	tooltip="If you are unable to find the machine manufacturer in the list, please enter its full name here and continue.  We will not be able to provide you with a price automatically.  However,   if you complete this form, we will have the information we need to respond to you quickly with a quote.  We apologize for the inconvenience."
					          	tooltip-placement="top">
					</div>
				</div> -->
				<div class="form-group">
					<label for="machineModel" class="col-sm-3 control-label">Model Number</label>
					<div class="col-sm-4">
						<select class="form-control" name="machineModel" ng-model="quote.machineInfoDO" ng-options="machineModel.model group by machineModel.machineType for machineModel in machineModelList">
							<option value="">Model Number</option>
						</select> 
					</div>
				</div>
				<!-- <div class="form-group">
					<label for="modelNumber" class="col-sm-3 control-label">Enter Model Number if not found in list</label>
					<div class="col-sm-4">
						<input type="text" id="modelNumber" name="modelNumber" ng-model="quote.modelNumber" placeholder="Model Number" class="form-control">&nbsp;<img src="/assets/images/info-icon.png" alt="Info" 
								data-toggle="tooltip" 
								tooltip-trigger tooltip-animation="false" 
					          	tooltip="If you are unable to find the model in the list, look to see if there is a close match and note the difference in the 'unusual provisions' section in step one. If the exact model is part of a series of similar machines, just choose the base model in the series.  If no model in the list is a good match, please enter the correct model here and continue.  We will not be able to provide you with a price automatically.  However,   if you complete this form, we will have the information we need to respond to you quickly with a quote.  We apologize for the inconvenience."
					          	tooltip-placement="top">
					</div>
				</div> -->
				<div class="form-group">
					<label for="horsePower" class="col-sm-3 control-label">Horsepower (Engine)</label>
					<div class="col-sm-4">
						<input type="text" id="horsePower" name="horsePower" ng-model="quote.horsePower" placeholder="Horse Power" class="form-control" required="required">&nbsp;<img src="/assets/images/info-icon.png" alt="Info" 
								data-toggle="tooltip" 
								tooltip-trigger tooltip-animation="false" 
					          	tooltip="Please use the engine-rated horsepower.  This is not the PTO power or peak power."
					          	tooltip-placement="top">
					</div>
				</div>
				<div class="form-group">
					<label for="serialNumber" class="col-sm-3 control-label">Serial Number</label>
					<div class="col-sm-4">
						<input type="text" id="serialNumber" name="serialNumber" ng-model="quote.serialNumber" placeholder="Serial Number" class="form-control" required="required">&nbsp;<img src="/assets/images/info-icon.png" alt="Info" 
								data-toggle="tooltip" 
								tooltip-trigger tooltip-animation="false" 
					          	tooltip="Enter the Manufacturer's serial number for the machine. If you need to price coverage for a machine you have ordered, but have not yet received, then check 'unknown' and you can update this field later when you purchase the coverage."
					          	tooltip-placement="top">
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
						<input type="text" id="retailPrice" name="retailPrice" ng-model="quote.retailPrice" class="form-control" placeholder="Retail Price" required="required">&nbsp;<img src="/assets/images/info-icon.png" alt="Info" 
								data-toggle="tooltip" 
								tooltip-trigger tooltip-animation="false" 
					          	tooltip="For our purposes, the 'Retail Price' is the advertised price of the machine not including any special deals or terms.  This is not necessarily the sales price.   We do not use the 'Retail Price'  to determine the price of coverage; however, we do use this information to compare coverage on certain machines and within certain price bands when we analyze our risk-so, it is important to report this accurately in order to help us keep our prices as low as possible."
					          	tooltip-placement="top">
					</div>
				</div>
				<div class="form-group">
					<label for="meterHours" class="col-sm-3 control-label">Meter Hours</label>
					<div class="col-sm-4">
						<input type="text" id="meterHours" name="meterHours" ng-model="quote.meterHours" placeholder="Meter Hours" class="form-control" required="required">&nbsp;<img src="/assets/images/info-icon.png" alt="Info" 
								data-toggle="tooltip" 
								tooltip-trigger tooltip-animation="false" 
					          	tooltip="This should be the exact hours shown on the Machine's tachometer or hours gauge. It is important that this information is recorded accurately to prevent lapses or denial of coverage."
					          	tooltip-placement="top">
					</div>
				</div>
				<div class="form-group">
					<label for="modelYear" class="col-sm-3 control-label">Model Year</label>
					<div class="col-sm-4">
						<input type="text" id="modelYear" name="modelYear" ng-model="quote.modelYear" placeholder="Model Year" class="form-control" required="required">&nbsp;<img src="/assets/images/info-icon.png" alt="Info" 
								data-toggle="tooltip" 
								tooltip-trigger tooltip-animation="false" 
					          	tooltip="The 'model year' may not correlate with age of the machine, but it may effect the price.  Sometimes components change by model year and the change is not reflected in the model number.  These changes can effect our risk  (and our pricing) significantly.  Therefore, it is important to record the actual model year.  If you do not know the model year, leave blank and explain why in the 'unusual provisions' section in step one."
					          	tooltip-placement="top">
					</div>
				</div>
				<div class="form-group">
					<label for="equipment" class="col-sm-3 control-label">Use of Equipment</label>
					<div class="col-sm-4">
						<select name="equipment" ng-model="quote.useOfEquipmentDO" class="form-control" ng-options="equipmentObj.equipName for equipmentObj in useOfEquipmentDOList">
							<option value="">Use of Equipment</option>
						</select>&nbsp;<img src="/assets/images/info-icon.png" alt="Info" 
								data-toggle="tooltip" 
								tooltip-trigger tooltip-animation="false" 
					          	tooltip="Choose the primary use for which the equipment is being purchased to perform. The pull-down options are broad categories and you should choose the best one. If you have concerns about an application, it is worth verifying how it might effect coverage before completing the purchase so that there are no surprises. How the machine will be used can effect pricing.  And if the machine is used in a materially different application than reported, it could compromise the coverage."
					          	tooltip-placement="top">
					</div>
				</div>
				<div class="form-group">
					<label for="estSaleDate" class="col-sm-3 control-label">Estimated Sale Date</label>
					<div class="col-sm-4">
						<input type="date" id="estSaleDate" name="estSaleDate" ng-model="quote.estSaleDate" class="form-control" min="{{date | date:'yyyy-MM-dd'}}" value="{{date | date:'MM/dd/yyyy'}}" required="required">&nbsp;<img src="/assets/images/info-icon.png" alt="Info" 
								data-toggle="tooltip" 
								tooltip-trigger tooltip-animation="false" 
					          	tooltip="The 'estimated sale date' is your best guess as to when you will complete the sale.  We use this date for follow-up and to make sure coverage is in place when you need it."
					          	tooltip-placement="top">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-5 col-sm-offset-3">
						<button type="button" class="btn btn-primary btn-block" ng-click="changeTab(2)">Continue to "Coverage Info"</button>
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
						<input type="radio" id="dealerMarkupPrice" name="dealerMarkupVlaue" ng-model="quote.dealerMarkupVlaue" value="dealerMarkupPrice" class="">&nbsp;Price
						&nbsp;&nbsp;<input type="radio" id="dealerMarkupPercent" name="dealerMarkupVlaue" ng-model="quote.dealerMarkupVlaue" value="dealerMarkupPercent" class="">&nbsp;Percent
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-6 control-label">Choose one deductible amount and one coverage term:</label>
					<div class="col-sm-4"></div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">
						<img src="/assets/images/info-icon.png" alt="Info" 
								data-toggle="tooltip" 
								tooltip-trigger tooltip-animation="false" 
					          	tooltip="Including a deductible can reduce the price of coverage, which is appealing on the front end.  However, customers can later regret a high deductible because it is assessed per failure, not per repair.  Therefore, if you repair a machine that has multiple failures that are being addressed at the same time, then the deductible can get large.  The deductible can also be a tool for reducing the number of small repairs, which can be an issue for some dealer's. Typically, AgGuard recommends no deductible on new machines; however, it may be appropriate in some cases."
					          	tooltip-placement="top">
						&nbsp;Deductible:
					</label>
					<div class="col-sm-8">
						<label ng-repeat="deductibleAmt in deductibleAmtList">
							<input  type="radio" id="deductiblePrice" name="deductiblePrice" ng-model="quote.deductiblePrice" class="" ng-value="deductibleAmt"><span>$</span>{{deductibleAmt}}
						</label>&nbsp;&nbsp;
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">
						<img src="/assets/images/info-icon.png" alt="Info" 
								data-toggle="tooltip" 
								tooltip-trigger tooltip-animation="false" 
					          	tooltip="Coverage begins on the 'Contract Inception Date' and expires according to the 'Term' in months or machine hours, whichever occurs first.  On newer equipment, the time and hour limits of the Term starts the day the manufacturer's warranty starts and at zero (0) Hours.  Coverage expires when the length of time or accumulated hours of the Term selected is reached. For example, 48 months would be the same as 2 years of Manufacturer's coverage and 2 more years of extended coverage.  On used equipment, the term is typically expressed in terms of additional time and hours."
					          	tooltip-placement="top">
						&nbsp;Coverage Term:
					</label>
					<div class="col-sm-8">
						<label ng-repeat="coverageTermVal in coverageTermList">
							<input type="radio" id="coverageTerm" name="coverageTerm" ng-model="quote.coverageTerm" class="" ng-value="coverageTermVal">{{coverageTermVal}}&nbsp;mos.
						</label>&nbsp;&nbsp;
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
						<button type="button" class="btn btn-primary btn-block" ng-click="changeTab(3)">Continue to "Quote Summary"</button>
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