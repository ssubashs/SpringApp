/**
 * 
 */



$( document ).ready(function(){
	console.log('loaded the javascript for mobileffr ');
	var formcontainer = $('#ffrForm');
//	$.validator.setDefaults({
//		submitHandler: function() {
//			alert("submitted!");
//		}
//	});
	
	formcontainer.validate();
	
	
	$('#save').click(function(){
		  console.log('save invoked');		 
		
		  $('#ffrForm').append('<input type="hidden" name="pageName" value="EFFR" />');
		  //$('#ffrForm').append('<input type="hidden" name="operation" value="5" />');
		  ffrForm.operation.value = 5;		  
		  ecmsFriendlyURL(ffrForm,src_page);
		  ffrForm.submit();
		});
});

