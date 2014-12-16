<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html">
<%@ taglib prefix="mapper" uri="/WEB-INF/tld/json.tld"%>
<title>Customer Details JSP</title>
<link href="../assets/css/bootstrap.min.css" rel="stylesheet"  media="screen" />

    <style type="text/css" >

      body { padding-top: 80px;}
      
		    .node {
		  cursor: pointer;
		}		
		
    
	</style>
	<script type="text/javascript">
		var customerjson = ${mapper:toJson(customers)};
		console.log('customer json '+ JSON.stringify(customerjson));		
	</script>
	
</head>
<body>
<div class="container-fluid">

			<div id="searchbar">
				<div class="navbar navbar-inverse navbar-fixed-top">
				  <div class="navbar-inner">
					<div class="container-fluid">
						<div id="searchbar" class="row" style="padding-top: 20px; padding-left: 20px">
							<a id="home" class="brand" href="#">JSON Tree </a>							
						</div>
					</div>
				  </div>
				</div>
			</div>	
			<div class="row">	
				

				<div  id="pageContent">
					<div class="span10" id="content">
						<h4> Json content goes here</h4>
					</div>
				</div>	
			</div>
</div>

<script type="text/javascript" src="../assets/js/jquery-1.8.3.min.js"></script>
<!-- <script type="text/javascript" src="../assets/js/json2.js"></script>  -->
<!-- <script type="text/javascript" src="http://ajax.cdnjs.com/ajax/libs/json2/20110223/json2.js"></script> -->
<script type="text/javascript" src="../assets/js/bootstrap.min.js"></script>
 <script type="text/javascript">
 $(document).ready(function($){
     console.log('loaded the jsp successfully')
    });
 
 $('#content').html(json_tree(customerjson));
 
		function json_tree(object){
        var json="<ul>";
        for(prop in object){
            var value = object[prop];
            switch (typeof(value)){
                case "object":
                	if(value === undefined || value === null)
                		{
                			json += "<li>"+prop+"=</li>";
                		}
                	else
                		{
		                    var token = Math.random().toString(36).substr(2,16);
		                    json += "<li><a class='label' href='#"+token+"' data-toggle='collapse'>"+prop+"="+value+"</a><div id='"+token+"' class='collapse'>"+json_tree(value)+"</div></li>";
                		}
                break;
                default:
                json += "<li>"+prop+"="+value+"</li>";
            }
        }
        return json+"</ul>";
	};
 </script>
 

</body>
</html>