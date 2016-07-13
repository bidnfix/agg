<table class="table">
	<tr>
		<td>Contract Id</td>
		<td>{{contractInfoList.contractId}}</td>
	</tr>
	<tr>
		<td>Serial/VIN #</td>
		<td>{{contractInfoList.machineSerialNo}}</td>
	</tr>
	<tr>
		<td>Manufacturer</td>
		<td>{{contractInfoList.manufacturerDO.name}}</td>
	</tr>
	<!-- <tr>
		<td>Model</td>
		<td>{{claim.machineModel}}</td>
	</tr> -->
	<tr>
		<td>Contract Expiration Date</td>
		<td>{{contractInfoList.expirationDate}}</td>
	</tr> 
	<tr>
		<td>Usage Hours covered</td>
		<td>{{contractInfoList.coverageLevelHours}}</td>
	</tr>
	<tr>
		<td>Limit of Liability (LOL)</td>
		<td>{{contractInfoList.lol}}</td>
	</tr>
	<tr>
		<td>Available LOL</td>
		<td>{{contractInfoList.availabeLol}}</td>
	</tr>
	<tr>
		<td>Deductible</td>
		<td>{{contractInfoList.deductible}}</td>
	</tr>
</table>