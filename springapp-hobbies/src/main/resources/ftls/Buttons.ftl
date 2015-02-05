<#import "spring.ftl" as spring />
<#import "ButtonMacros.ftl" as buttonWidget />
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<title>FTL Test</title>

		<meta name="description" content="bootstrap grid sizing" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />

		<!-- basic styles -->

		<link href="<@spring.url '/assets/css/bootstrap.min.css'/>" rel="stylesheet" />
		<link rel="stylesheet" href="<@spring.url '/assets/css/font-awesome.min.css'/>" />

		<!--[if IE 7]>
		  <link rel="stylesheet" href="<@spring.url '/assets/css/font-awesome-ie7.min.css'/>" />
		<![endif]-->

		<!-- page specific plugin styles -->

		<!-- fonts -->

		<link rel="stylesheet" href="<@spring.url '/assets/css/ace-fonts.css'/>" />

		<!-- ace styles -->

		<link rel="stylesheet" href="<@spring.url '/assets/css/ace.min.css'/>"  />
		<link rel="stylesheet" href="<@spring.url '/assets/css/ace-rtl.min.css'/>"  />
		<link rel="stylesheet" href="<@spring.url '/assets/css/ace-skins.min.css'/>"/>

		<!--[if lte IE 8]>
		  <link rel="stylesheet" href="<@spring.url '/assets/css/ace-ie.min.css'/>"  />
		<![endif]-->

        <link rel="stylesheet" href="<@spring.url '/assets/css/sudoku.grid.css'/>" />

		<!-- ace settings handler -->
        <script src="<@spring.url '/assets/js/jquery-2.0.3.min.js'/>"></script>
		<script src="<@spring.url '/assets/js/ace-extra.min.js'/>"></script>

		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

		<!--[if lt IE 9]>
		<script src="<@spring.url '/assets/js/html5shiv.js'/>"></script>
		<script src="<@spring.url '/assets/js/respond.min.js'/>"></script>
		<![endif]-->
	</head>

	<body>
				
				<div class="center page-header" id="header">
							<h1>
								Buttons Page 
							</h1>	
								<div class="btn-group" id="actions">
												<button class="btn btn-success" id="success" targetDiv="container">Success</button>												
												<button class="btn btn-warning" id="warning" targetDiv="container">Warning</button>
												<button class="btn btn-danger" id="danger" targetDiv="container">Danger</button>
												<@buttonWidget.button name="widgetButton" id="dynamicID" styleClass="btn btn-success" actionUrl="/sudoku" divContentID="container"/>
													
								</div>
								
							
							<div id="message"></div>
				</div><!-- /.page-header -->

						<div  id="container">
							

						</div>
								
		
	
		<script src="<@spring.url '/assets/js/bootstrap.min.js'/>"></script>
		<script src="<@spring.url '/assets/js/typeahead-bs2.min.js'/>"></script>
		<script src="<@spring.url '/assets/js/jquery-ui-1.10.3.custom.min.js'/>"></script>
		<script src="<@spring.url '/assets/js/jquery.ui.touch-punch.min.js'/>"></script>
		<script src="<@spring.url '/assets/js/bootbox.min.js'/>"></script>

		<!-- page specific plugin scripts -->

		<!-- ace scripts -->

		<script src="<@spring.url '/assets/js/ace-elements.min.js'/>"></script>
		<script src="<@spring.url '/assets/js/ace.min.js'/>"></script>
		<script type="text/javascript">
			jQuery(function($) {
			
			
			});	
		</script>
		

	</body>
</html>
