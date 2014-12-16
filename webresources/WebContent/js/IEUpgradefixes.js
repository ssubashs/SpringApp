/**
 * 
 */

$( document ).ready(function(){
	
	fixblocks();
	
	
	var oldtabchange = showHideTabs;
	showHideTabs = function()
	{
		var result = oldtabchange.apply(this,arguments);		
		fixblocks();		
		return result;
	};
	
	
	var oldexpandAll = expandAll;
	expandAll = function()
	{
		var result = oldexpandAll.apply(this,arguments);		
		fixblocks();		
		return result;
	};
	
	var oldshowHideAll = showHideAll;
	showHideAll = function()
	{
		var result = oldshowHideAll.apply(this,arguments);		
		fixblocks();		
		return result;
	};

	});

function fixblocks()
{
	$('tr').filter(function(){		
	    if( $(this).css('display') == 'block')
    	{
//	    	console.log('found a display block element');	
	        $(this).css('display','table-row');
    	}
	});
	
	
	$('table').filter(function(){		
	    if( $(this).css('display') == 'block')
    	{
//	    	console.log('found a display block element');	
	        $(this).css('display','table');
    	}
	});
	
	$('td[colspan=2]').attr('colspan','1');
}

function changedisplayblock()
{
	$('tr').filter(function(){		
	    if( $(this).css('display') == 'table-row')
    	{
//	    	console.log('found a display block element');	
	        $(this).css('display','block');
    	}
	});
	
	
	$('table').filter(function(){		
	    if( $(this).css('display') == 'table')
    	{
//	    	console.log('found a display block element');	
	        $(this).css('display','block');
    	}
	});
}

