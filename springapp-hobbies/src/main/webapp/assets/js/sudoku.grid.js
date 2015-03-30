jQuery(function($) {
	
				// set for default display
				var duplicatepuzzle=function(){ 
					var size =9;
					var temp = new Array();
					for(var i=0;i<size;i++)
						{
							temp[i] = new Array();
							for(var j=0;j<size;j++)
							{
								temp[i][j]=0;
							}
						}
					return temp;	
				};
				var puzzleId='new'; // set for default display
				
				
				if(ispuzzle)
					{
					duplicatepuzzle = $.parseJSON(puzzle);
					puzzleId = id;
					var destination = 'sudocontainer';
					var btn = $('#btn-newPuzzle');
					createGrid(3,destination,btn);	
					
					}
				
				var selectedCellId;
				var max=1;			
				
				var isSave = false;
				var $box = $("#content");
			    
				$(document).ready(function() {
					//alert(duplicatepuzzle);
					if(!ispuzzle)
					{	
					$("#btn-reset").hide();		
					$("#btn-validate").hide();
					$("#btn-save").hide();
					}
				
				
				   $('#btn-newPuzzle').on(ace.click_event, function () {
					   $("#btn-reset").hide();	
						var btn = $(this);
						var destination = $(this).attr("targetDiv");
						$('#'+destination).empty();
						selectedCellId = null;
						console.log('target div '+destination);
						btn.button('loading');
						$.ajax({
									type:"get",
									url:"../sudoku/puzzle/1",
									 success: function (puzzle) {
											   
											   //puzzle = JSON.parse(data);
											    duplicatepuzzle = puzzle.grid.slice();
											    puzzleId = puzzle.id;
											    console.log("puzzle id " +puzzleId);
											    createGrid(3,destination,btn);												
											},
									error: function (jqXHR, textStatus, errorThrown) {										
										console.log('Error ajax call '+textStatus);
										console.log(errorThrown);
									}
								
								
								
								});
						
						
					   $("#btn-reset").show();	
					   $("#btn-validate").show();	

					});
				
				
					
					
					$("#sudocontainer").on("mousedown",$(".puzzle"),function(e){
					//alert('clicked cell '+$(e.target).attr("id"));
					if($('#'+selectedCellId) != 'undefined')
						{
	//						alert('not first select');
							$('#'+selectedCellId).toggleClass("focus");
							//$('#'+selectedCellId).toggleClass("selectfocus");
							crossmarkGrid(selectedCellId);
						}
						
						$(e.target).toggleClass("focus");
						//$('#'+selectedCellId).toggleClass("selectfocus");
						selectedCellId = $(e.target).attr("id");
						crossmarkGrid(selectedCellId);
						//$(e.target).toggleClass("focus");
					});
					

					});
				
					$("#sudocontainer").on("keydown keyup",$(".puzzle"),function(e){
						var id=$(e.target).attr("id");
						var row = $(e.target).attr("row");
						var col = $(e.target).attr("col");	
						 $(e.target).focus();
						check_charcount(id, max, e); 
						var input = $(e.target).text();
						console.log('Keyed  row '+row + ', col'+col );
						duplicatepuzzle[row][col] = input;
						//alert(duplicatepuzzle); 
						});

					
					$('#btn-EnterPuzzle').on(ace.click_event, function () {
						var btn = $(this);
						var destination = $(this).attr("targetDiv");
						$('#'+destination).empty();
						selectedCellId = null;
						console.log('target div '+destination);
						puzzleId = 'new';
						btn.button('loading');
						createBlankGrid(9,destination,btn);
						$("#btn-save").show();	
						$("#btn-validate").hide();

					});
				
					$('#btn-reset').on(ace.click_event, function () {
							var btn = $(this);
						    var destination = $(this).attr("targetDiv");
							btn.button('loading');
							selectedCellId = null;
						    $('#'+destination).empty();
						    
							$.ajax({
								type:"get",
								url:"../sudoku/puzzle/"+puzzleId,
								 success: function (puzzle) {
										   
										   //puzzle = JSON.parse(data);
										    duplicatepuzzle = puzzle.grid.slice();
										    puzzleId = puzzle.id;
										    console.log("puzzle id " +puzzleId);
										    createGrid(3,destination,btn);												
										},
								error: function (jqXHR, textStatus, errorThrown) {
									console.log('Error ajax call '+textStatus);
								}
							
							
							
							});

						   

						});
				
					$('#btn-save').on(ace.click_event, function () {
						var noError = true;
						//alert('save');
						$('.puzzle').each(function(i, obj) {

								var id = $(this).attr("id");
								var row = $(this).attr("row");
								var col = $(this).attr("col");
								var value = $(this).text();
								
								if(value != null && value != '')
								{	
									//alert(id+','+value);
									
									var error = validateCell(id);
									if(!error)
									{
										$('#'+id).removeClass("puzzle");
										$('#'+id).removeClass("highlighted");
										$('#'+id).removeClass("error");
										$('#'+id).removeAttr("contenteditable");
										duplicatepuzzle[row][col] = value;
									}	
									else
										noError =false;
								}
							});
						if(isSave && noError)
						{
							$('#btn-save').hide();
							$('#btn-reset').show();
							$('#btn-validate').show();
							$('#btn-solvePuzzle').show();
							// save at server side
							
							var jsonString = {'id':puzzleId,'grid':duplicatepuzzle};
							console.log(JSON.stringify(jsonString));
							$.ajax({
								type:"post",
								url:"../sudoku/puzzle/create",								
								contentType: "application/json; charset=utf-8",
								dataType:'json',
								data:JSON.stringify(jsonString),
								 success: function (puzzle) {
									 
									 var alert='<div class="alert alert-success"><button type="button" class="close" data-dismiss="alert"><i class="icon-remove"></i></button><strong><i class="icon-remove"></i> Saved puzzle !!! '+puzzle.id+' </strong>  <br /></div>';
										$(alert).insertBefore($box);
										   //puzzle = JSON.parse(data);
										
										puzzleId = puzzle.id;
										    												
										},
								error: function (jqXHR, textStatus, errorThrown) {
									console.log('Error ajax call '+textStatus);
									 var alert='<div class="alert alert-success"><button type="button" class="close" data-dismiss="alert"><i class="icon-remove"></i></button><strong><i class="icon-remove"></i> Error on Save !!!</strong>  <br /></div>';
										$(alert).insertBefore($box);
								}
							
							
							
							});
							
						}
						isSave = true; // only to avoid first time hiding buttons.
						//alert(duplicatepuzzle);
						});
					$("#btn-solvePuzzle").on(ace.click_event, function () {
						console.log('solve puzzle '+puzzleId);
						
					});
					$('#btn-validate').on(ace.click_event, function () {
						//alert('validate');
						isValidate = true;					
							$('.puzzle').each(function(i, obj) {

								var id = $(this).attr("id");
								var value = $(this).text();
								if(value != null && value != '')
									validateCell(id);
							});
						});
				
					function validateCell(id)
					{
						var row = $('#'+id).attr("row");
						var col = $('#'+id).attr("col");
						var value = $('#'+id).text();
						var gridSiz = 9;
						var isError = false;
						
						// validate row
						if(row < gridSiz)
							{
								for(var j=0;j<gridSiz;j++)
								{
									if(j!= col && duplicatepuzzle[row][j] == value)
									{
										// row is invalid
										$('#'+id).addClass("error");
										isError = true;
										return isError;
									}
									   
								}
							}
						
						if(col < gridSiz)
						{
							for(var j=0;j<gridSiz;j++)
								{
									if(j!= row && duplicatepuzzle[j][col] == value)
									{
										// row is invalid
										$('#'+id).addClass("error");
										isError = true;
										return isError;
									}
									   
								}
						}
						
						var startRow = 0;
						var startCol = 0;
						if(row<3)
								startRow =0
						else if(row<6)
								startRow =3;
						else
								startRow =6;
						
						if(col<3)
							    startCol =0;
						else if(col <6)
								startCol = 3;
						else 
								startCol =6;
						
						for(var countRow = 3;countRow >0;countRow--) // 3 count
						{
							for(var countCol = 0;countCol <3;countCol++)
							{	
								var gridVal = duplicatepuzzle[startRow][startCol+countCol];
								if(row != startRow && col != startCol+countCol && gridVal == value)
								{
									$('#'+id).addClass("error");
									isError = true;
									return isError;
								}
								
							}
							startRow++;
						}
						
						// no error send message
						
						return isError;
					}
				
				
					function check_charcount(content_id, max, e)
					{   
						var inputchar = $(e.target).text();
						console.log("input "+ inputchar );
						if(e.which != 8 && e.which != 9  && (inputchar.length > max-1))
						{
							console.log('not allowed more than 1');
							e.preventDefault();
						}

					}
			
					function createGrid(size,destinationDiv,button) {
							console.log("size "+size);
							var parent = $('#'+destinationDiv);
							console.log("parent  "+parent);
							var inputarray = duplicatepuzzle;
							console.log(inputarray);
							var htmlarr = new Array();	
							var html='';						
							for(var i=0;i<inputarray.length;i++)
							{
								var row = inputarray[i];
								var htmlarrRow = new Array();
								for(var j=0;j<row.length;j++)
								{
									if(row[j]=='0')
									htmlarrRow[j] = '<span id="'+i+''+j+'" class="puzzle highlighted" contenteditable="true" row="'+i+'" col="'+j+'"></span>';
									else
									htmlarrRow[j] = '<span id="'+i+''+j+'" row="'+i+'" col="'+j+'">'+row[j]+'</span>';
								}
								htmlarr[i]=htmlarrRow;  
							}
						
							var temphtmlArr = prepareInput(htmlarr);
							for(var i=0;i<temphtmlArr.length;i++)
							{
								html=html+'<div> <div>';
								var row = temphtmlArr[i];								
								for(var j=0;j<row.length;j++)
								{
									html= html + row[j]; 
									
								}
								html=html+'</div> </div>';	  
							}
							parent.append(html);
						    button.button('reset');

					}
				
				function createBlankGrid(size,destinationDiv,button) {
							console.log("size "+size);
							var parent = $('#'+destinationDiv);
							console.log("parent  "+parent);
							duplicatepuzzle = new Array();							
							var htmlarr = new Array();	
							var html='';						
							for(var i=0;i<size;i++)
							{
								
								var htmlarrRow = new Array();
								duplicatepuzzle[i] = new Array();
								for(var j=0;j<size;j++)
								{
									
									htmlarrRow[j] = '<span id="'+i+''+j+'" class="puzzle highlighted" contenteditable="true" row="'+i+'" col="'+j+'"></span>';
									duplicatepuzzle[i][j]=0;
									
								}
								htmlarr[i]=htmlarrRow;  
							}
						
							var temphtmlArr = prepareInput(htmlarr);
							for(var i=0;i<temphtmlArr.length;i++)
							{
								html=html+'<div> <div>'
								var row = temphtmlArr[i];								
								for(var j=0;j<row.length;j++)
								{
									html= html + row[j] 
									
								}
								html=html+'</div> </div>';	  
							}
							parent.append(html);
						    button.button('reset');

					}
				
				 
				
					function prepareInput(temphtmlArray)
					{
						var arr = new Array();
						var totalGrid =9;
						var gridIndex =0;
						
						var startRow = 0;
						var startCol = 0;
						
						while(gridIndex<totalGrid)
						{
							if(gridIndex<3)
								startRow =0
							else if(gridIndex<6)
								startRow =3;
							else
								startRow =6;
							var arrRow = new Array();
							var count = 0;
							for(var rowSize = 3; rowSize >0; rowSize-- ) // 3 row 
							{
								startCol = (gridIndex%3)*3;

								for(var colSize = 3;colSize >0;colSize--) // 3 col
								{
									arrRow[count++] = temphtmlArray[startRow][startCol];
									startCol++;
								}

								startRow++;	
							}
								
							
							arr[gridIndex] = arrRow;
							gridIndex++;
						}
					
						return arr;
					}
				
					function crossmarkGrid(selectedCellId)
					{
						var id=$('#'+selectedCellId).attr("id");
						var row = $('#'+selectedCellId).attr("row");
						var col = $('#'+selectedCellId).attr("col");
						
						$('[row='+row+']').toggleClass("focus");
						$('[col='+col+']').toggleClass("focus");
						
						//console.log('cross mark cell '+row+','+col);
					}
				
			});
			
		