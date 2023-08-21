<%@page import="datacollector.DataCollector"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cash Battle</title>
    <link rel="stylesheet" href="css/custom.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
</head>


<%
String ref = "";
String tag = "";
try {
String agent=request.getHeader("User-Agent");
	ref = request.getParameter("ref");
	tag = request.getParameter("tag");

	if (ref == null) {

		ref = "null";	
	}
	if (tag == null) {
		tag = "null";

	}
	DataCollector.insertOffnetUser(tag, ref,agent);
} catch (Exception e) {
	System.out.println(e.getMessage());

}
%>

<body>
    <div class="header">
        <div class="bg-img">
            <img src="images/banner.jpg" alt="banner" class="img-fluid">
        </div>
    </div>

    <div class="inner-part pt-4">
        <div class="container">
            <div class="row">
                <div class="col-md-6 text-center m-auto">
                    <h4>Y'ello. Enter your number below to subscribe to Cash Battle from FGR</h4>
                    <p>R5.00/day subscription service</p>
         
                        <div class="form-group">
                          <input type="number" class="form-control" placeholder="Enter Your Number" maxlength="11" id="ani">
                        </div>
                        
                        <div class="form-group form-check">
                            <input type="checkbox" class="form-check-input" id="check">
                            <label class="form-check-label text-white" for="check">I agreed to the Terms and Conditions</label>
                          </div>
                          
                        <button type="submit" class="btn btn-primary btnn" onclick="login()">Subscribe</button>
                    
                </div>
            </div>
        </div>
    </div>
    
<script type="text/javascript">
		function login() {

			let ani = document.getElementById("ani").value;
			let url=new URL(window.location.href).searchParams.get("ref");
			if (ani == "") {

				alert('Please put your Number !');
				return;

			}
			window.location.href = "login?msisdn=" + ani + "&type=offnet&ref="+url
		

		}
	</script>


    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js"></script>
</body>
</html>