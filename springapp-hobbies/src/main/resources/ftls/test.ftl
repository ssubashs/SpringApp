<#import "spring.ftl" as spring />
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<title>FTL Test</title>

		<meta name="description" content="bootstrap grid sizing" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />

		<!-- basic styles -->

		<link href="<@spring.url '/assets/css/bootstrap.min.css'/>" rel="stylesheet" />
		<link rel="stylesheet" href="<@spring.url '/assets/css/font-awesome.min.css'/>" th:href="@{/assets/css/font-awesome.min.css}" />

		<!--[if IE 7]>
		  <link rel="stylesheet" href="<@spring.url '/assets/css/font-awesome-ie7.min.css'/>" th:href="@{/assets/css/font-awesome.min.css}"  />
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

		<script src="<@spring.url '/assets/js/ace-extra.min.js'/>"></script>

		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

		<!--[if lt IE 9]>
		<script src="<@spring.url '/assets/js/html5shiv.js'/>"></script>
		<script src="<@spring.url '/assets/js/respond.min.js'/>"></script>
		<![endif]-->
	</head>

	<body>
				
		<div class="gridmain">					
					
						<div class="center page-header" id="header">
							<h1>
								Sudoku 
							</h1>	
								<div class="btn-group" id="actions">
												<button class="btn btn-success" id="btn-newPuzzle" targetDiv="sudocontainer">3X3</button>
												<button class="btn btn-success" id="btn-EnterPuzzle" targetDiv="sudocontainer">Create</button>
												<button class="btn btn-warning" id="btn-reset" targetDiv="sudocontainer">Reset</button>
												<button class="btn btn-danger" id="btn-save" targetDiv="sudocontainer">Save</button>
												<button class="btn btn-danger" id="btn-validate" targetDiv="sudocontainer">validate</button>
													
								</div>
								
							
							<div id="message"></div>
						</div><!-- /.page-header -->

						<div  class="sudoGrid" id="sudocontainer">
							
<!--
								<div id="sudoGrid11">
														<div id="11">
															<span id="1">1</span>
															<span id="2">2</span>
															<span id="3">3</span>
															<span id="4">4</span>
															<span id="5">5</span>
															<span class="puzzle" id="6" contenteditable="true"></span>
															<span class="puzzle" id="7" contenteditable="true"></span>
															<span id="8">8</span>
															<span id="9">9</span>
														</div>
								</div>
-->
						</div>
								
					
		</div><!-- /main -->
		
		<script src="<@spring.url '/assets/js/jquery-2.0.3.min.js'/>"></script>
		<script src="<@spring.url '/assets/js/bootstrap.min.js'/>"></script>
		<script src="<@spring.url '/assets/js/typeahead-bs2.min.js'/>"></script>

		<!-- page specific plugin scripts -->

		<!-- ace scripts -->

		<script src="<@spring.url '/assets/js/ace-elements.min.js'/>"></script>
		<script src="<@spring.url '/assets/js/ace.min.js'/>"></script>
		<script src="<@spring.url '/assets/js/sudoku.grid.js'/>"></script>
		

	</body>
</html>
