$(document).ready(function(){
	new WOW().init();
	
	$('#listpersons').DataTable();
    $('select').addClass('mdb-select');
//    $('.mdb-select').material_select();
    $('select').css({'margin-top':'0px'});
    
    $('#easyPaginate').easyPaginate({
        paginateElement: 'li',
        elementsPerPage: 6,
        effect: 'slide'
    });
    
//    $('#input-search').autocomplete({
//    	serviceUrl: '@{/search/getTags}',
//    	paramName: "tagName", // ?tagName='user input'
//    	delimiter: ",",
//    	source: function(response) {
//    		return {
//    			suggestions: $.map($.parseJSON(response), function(item) {
//    				return { 
//    					value: item.nameCategory 
//    				};
//    			})
//    			response
//	
//    		};
//    	}
//    });
});