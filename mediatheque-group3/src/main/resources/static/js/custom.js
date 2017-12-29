$(document).ready(function(){
	new WOW().init();
	
	$('#listpersons').DataTable();
    $('select').addClass('mdb-select');
    //$('.mdb-select').material_select();
    $('select').css({'margin-top':'0px'});
});