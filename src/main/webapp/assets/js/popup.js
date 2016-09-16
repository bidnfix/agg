function closePopup(id){
	$('#'+id).hide();
	hideMask('popup_mask');
}

function showSpinner(){
	var x = screen.width/2;
    var y = screen.height/2;
    showMask('popup_mask');
    $('#spinnerPopup').css("left", x+"px");
    $('#spinnerPopup').css("top", y+"px");
	$('#spinnerPopup').show();
}

function hideSpinner(){
	closePopup('spinnerPopup');
}