<%@page import="datacollector.DataCollector"%>
<%@page import="api.checkAniStatus"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>play</title>

</head>

<%
String ani = (String) session.getAttribute("user");
String gameurl = "";
// take to the live url 

if (ani == null) {
	
	response.sendRedirect("http://sdp.smartcalltech.co.za/Traffic/7984394d-48e5-4669-89f3-c93584fd423a");
	return;
} else {
	String userid = new checkAniStatus().getUserID(ani);
	gameurl = "https://gameninja.in/html/v1/BattleQuiz/index.html?gameid=2&userid=" + userid;
	response.sendRedirect(gameurl);
} 
 

String agent=request.getHeader("User-Agent");
DataCollector.insertUserDevice(ani,agent);
%>

<body>

	<iframe src="<%=gameurl%>" style="width: 100%; height: 100vh;"
		title="CashBattle"></iframe>
		
		<script type="text/javascript">
		
		
setTimeout(() => {
	  console.log('Foo bar')
	  checkwait();
	}, 60000*10);

function checkwait(){

	
		fetch('waitcheck?ani=<%=ani%>').then(resp=>{return resp.json()}).then(data=>{
			
			if(data == 1)
				{
				console.log("SuccessFully Charged"+data)
				}else 
					{
					location.href = "waiting.jsp?status=2&ref=000000";
					}
			
			
		}).catch(e=>{console.log(e)});
		
}
</script>
		
</body>
</html>