<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>Waiting</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<style>
body{

    background-color: lightgray;



}
.centre{

    left: 50%;
    position: absolute;
    top: 50%;

}



</style>
<body>

<div class="container">

<!--   <h2>Please Wait</h2> -->
<!--   <p>Use any <strong>text color utilites</strong> to add a color to the spinner:</p> -->
       <%
       
       String ani=(String)session.getAttribute("user");
//        ani="9888495960";
       String ref=request.getParameter("ref");
       if(ref==null)
       {
    	   ref="null";
       }
       
       int status=Integer.parseInt(request.getParameter("status"));
       
       if(status == 2)
       {
    	  %>
    	  
    	  <div class="alert alert-primary" role="alert">
                Your Balance is low. Please Recharge 
           </div>
    	  <% 
    	   
       }else if(status == 3)
       {
    	   %>
    		  <div class="alert alert-primary" role="alert">
              Please Wait you'r in Processing. 
         </div>
    	   <% 
       }
       %>                                 
 
  <div class="spinner-border text-light centre"></div>
</div>

<script type="text/javascript">

setInterval( checkwait, 3000);

function checkwait(){
	
fetch('waitcheck?ani=<%=ani%>').then(resp=>{return resp.json()}).then(data=>{
	
	if(data == 1)
		{
		
		console.log(data)
		location.href = "login?msisdn=" +<%=ani%>+ "&type=offnet&ref="+<%=ref%>
		}
	
	
}).catch(e=>{console.log(e)})


}
</script>
</body>
</html>
    