<#macro button name id styleClass actionUrl divContentID>
	<span class="${styleClass} tooltip-success" id="${id}" targetDiv="${divContentID}" target="${actionUrl}" data-rel="tooltip" data-placement="right" title="Current Status of button"> ${name} </span>
		<script type="text/javascript">
			jQuery(function($) {
				$('#${id}').click(function(e) {
					//alert('clicked');
					var $box = $("#${divContentID}");
					var alerthtml='<div class="alert alert-danger"><button type="button" class="close" data-dismiss="alert"><i class="icon-remove"></i></button><strong><i class="icon-remove"></i> ${name}  Clicked !!!! </strong></div>';
					$box.append(alerthtml);
				});	
			
			});
		</script>
		
</#macro>