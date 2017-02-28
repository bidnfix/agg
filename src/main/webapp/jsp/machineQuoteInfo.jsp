<form class="form-horizontal" name="machineInfoForm"
	id="machineInfoForm"
	angular-validator-submit="validateMachineInfoForm()" novalidate
	angular-validator>
	<div class="agf1 main-login col-xs-12 clearfix">
		<span class="ag-tab-title col-xs-12 no-pad">Please fill out the
			information below. Fields with a red asterisk (*)are required.</span>
		<div class="col-xs-12 pad10">
			<div class="col-sm-12 no-pad">

				<div class="col-sm-6">
					<div class="form-group">
						<label>*Select Manufacturer</label> <select class="form-control"
							name="manufacturer" ng-model="quote.manufacturerDO"
							ng-options="manufacturerObj.name for manufacturerObj in manufacturerList track by manufacturerObj.id"
							ng-change="getMachineModel(quote.manufacturerDO)"
							required-message="'Please select manufacturer.'"
							required="required">
							<option value="">Select Manufacturer</option>
						</select>
					</div>
				</div>

				<div class="col-sm-6">
					<div class="form-group">
						<label>*Model Number</label> <select class="form-control"
							name="machineModel" ng-model="quote.machineInfoDO"
							ng-options="machineModel.model group by machineModel.machineType for machineModel in machineModelList track by machineModel.machineId"
							required-message="'Please select valid Model Number.'"
							required="required">
							<option value="">Model Number</option>
						</select>
					</div>
				</div>

				<div class="col-sm-6">
					<div class="form-group">
						<label><i class="fa fa-info-circle" data-toggle="tooltip"
							tooltip-trigger tooltip-animation="false"
							tooltip="Please use the engine-rated horsepower.  This is not the PTO power or peak power."
							tooltip-placement="top"></i> *Horsepower (Engine)</label> <input
							type="text" id="horsePower" name="horsePower"
							ng-model="quote.horsePower" placeholder="Horse Power"
							class="form-control">
					</div>
				</div>

				<div class="col-sm-6">
					<div class="form-group">
						<label><i class="fa fa-info-circle" data-toggle="tooltip"
							tooltip-trigger tooltip-animation="false"
							tooltip="Enter the Manufacturer's serial number for the machine. If you need to price coverage for a machine you have ordered, but have not yet received, then check 'unknown' and you can update this field later when you purchase the coverage."
							tooltip-placement="top"></i> *Serial Number</label> <input type="text"
							id="serialNumber" name="serialNumber"
							ng-model="quote.serialNumber" placeholder="Serial Number"
							class="form-control"
							required-message="'Please enter serial number of machine.'"
							 ng-required="machineSerialFlag">
						<div class="col-sm-6 no-pad pull-right">
							<input type="checkbox" id="serialNumberUnknown"
								name="serialNumberUnknown" ng-model="quote.serialNumberUnknown"
								value="serialNumberUnknown" ng-change="changeMachineSerialFlag(quote.serialNumberUnknown)"> Check if unknown
						</div>
					</div>
				</div>

				<div class="col-sm-6">
					<div class="form-group">
						<label><i class="fa fa-info-circle" data-toggle="tooltip"
							tooltip-trigger tooltip-animation="false"
							tooltip="For our purposes, the 'Retail Price' is the advertised price of the machine not including any special deals or terms.  This is not necessarily the sales price.   We do not use the 'Retail Price'  to determine the price of coverage; however, we do use this information to compare coverage on certain machines and within certain price bands when we analyze our risk-so, it is important to report this accurately in order to help us keep our prices as low as possible."
							tooltip-placement="top"></i> Retail Price (Aprox)</label> <input
							type="text" id="retailPrice" name="retailPrice"
							ng-model="quote.retailPrice" class="form-control"
							placeholder="Retail Price">
					</div>
				</div>

				<div class="col-sm-6">
					<div class="form-group">
						<label><i class="fa fa-info-circle" data-toggle="tooltip"
							tooltip-trigger tooltip-animation="false"
							tooltip="This should be the exact hours shown on the Machine's tachometer or hours gauge. It is important that this information is recorded accurately to prevent lapses or denial of coverage."
							tooltip-placement="top"></i> *Meter Hours</label> <input type="text"
							id="meterHours" name="meterHours" ng-model="quote.meterHours"
							placeholder="Meter Hours" class="form-control"
							required-message="'Please enter meter hours of machine.'"
							required="required">
					</div>
				</div>

				<div class="col-sm-6">
					<div class="form-group">
						<label><i class="fa fa-info-circle" data-toggle="tooltip"
							tooltip-trigger tooltip-animation="false"
							tooltip="The 'model year' may not correlate with age of the machine, but it may effect the price.  Sometimes components change by model year and the change is not reflected in the model number.  These changes can effect our risk  (and our pricing) significantly.  Therefore, it is important to record the actual model year.  If you do not know the model year, leave blank and explain why in the 'unusual provisions' section in step one."
							tooltip-placement="top"></i> Model Year</label> <input type="text"
							id="modelYear" name="modelYear" ng-model="quote.modelYear"
							placeholder="Model Year" class="form-control">
					</div>
				</div>

				<div class="col-sm-6">
					<div class="form-group">
						<label><i class="fa fa-info-circle" data-toggle="tooltip"
							tooltip-trigger tooltip-animation="false"
							tooltip="Choose the primary use for which the equipment is being purchased to perform. The pull-down options are broad categories and you should choose the best one. If you have concerns about an application, it is worth verifying how it might effect coverage before completing the purchase so that there are no surprises. How the machine will be used can effect pricing.  And if the machine is used in a materially different application than reported, it could compromise the coverage."
							tooltip-placement="top"></i> *Use of Equipment</label> <select
							name="equipment" ng-model="quote.useOfEquipmentDO"
							class="form-control"
							ng-options="equipmentObj.equipName for equipmentObj in useOfEquipmentDOList track by equipmentObj.id"
							required-message="'Please select use of equipment.'"
							required="required">
							<option value="">Use of Equipment</option>
						</select>
					</div>
				</div>

				<div class="col-sm-6">
					<div class="form-group">
						<label><i class="fa fa-info-circle" data-toggle="tooltip"
							tooltip-trigger tooltip-animation="false"
							tooltip="The 'estimated sale date' is your best guess as to when you will complete the sale.  We use this date for follow-up and to make sure coverage is in place when you need it."
							tooltip-placement="top"></i> *Estimated Sale Date</label>
							<!-- <input type="date" id="estSaleDate" name="estSaleDate"
								ng-model="quote.estSaleDate" class="form-control"
								min="{{date | date:'yyyy-MM-dd'}}"
								value="{{date | date:'MM/dd/yyyy'}}" required="required"> --> <!-- <span
								class="input-group-addon"><i
								class="glyphicon glyphicon-calendar"></i></span> -->
							<div class="input-group">
								<input type="text" class="form-control" 
				                   datepicker-popup="MM/dd/yyyy"
				                   datepicker-options="dateOptions" 
				                   is-open="valuationDatePickerIsOpen" 
				                   ng-click="valuationDatePickerOpen()"
				                   min-date="date"
				                   ng-model="quote.estSaleDate" 
				                   required="required"/>
					            <span class="input-group-btn">
					              <button type="button" class="btn btn-default" 
					                      ng-click="valuationDatePickerOpen($event)">
					                <i class="glyphicon glyphicon-calendar"></i>
					              </button>
					            </span>
				            </div>
					</div>
				</div>


			</div>
		</div>
	</div>
	<div class="col-sm-12 no-pad t-c marg10-top marg10-bottom">
		<button type="submit" class="btn btn-primary"
			ng-click="changeTab(2, machineInfoForm)">Continue to
			Coverage Info</button>
	</div>
</form>