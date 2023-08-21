package api;

import java.io.*;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import datacollector.CheckSubscription;
import datacollector.DataCollector;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Login() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// hit hitesh sir login?msisdn=279888495960&type=onnet
		// hit sdp login?msisdn=279888495960&type=offnet
//		System.out.println("i am in login ");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String sdpurl = "http://sdp.smartcalltech.co.za/Traffic/7984394d-48e5-4669-89f3-c93584fd423a";
		String ani = checkAni(request.getParameter("msisdn"));
		String result = request.getParameter("type");
		String ref=request.getParameter("ref");
		
		if (ani == null || result == null) {
			response.sendRedirect(sdpurl);
			return;
		} else {

			int status = CheckSubscription.checkUser(ani);
			
			System.out.println("Status : "+status);

			 DataCollector.updateOffnetUser(ref,ani);
			if (status == 0) {
				if (result.equalsIgnoreCase("offnet")) {
				
					String flag="";
					String output="";
					
				
					String URL = "http://sdp.smartcalltech.co.za/sms/7984394d-48e5-4669-89f3-c93584fd423a/27"+ani+"/?ref="+ref;
				     DefaultHttpClient httpClient = new DefaultHttpClient();
					HttpPost postRequest = new HttpPost(URL);
					System.out.println(postRequest);
					HttpResponse resp = httpClient.execute(postRequest);
					BufferedReader br = new BufferedReader(new InputStreamReader((resp.getEntity().getContent())));
					
					System.out.println("Output from Server ....");
					while ((output = br.readLine()) != null) {
						System.out.println("output::" + output);
						flag = output;
					}
					if (flag.equalsIgnoreCase("true")) {
						  
						response.sendRedirect("wait?result="+flag+"&msisdn=" + ani+"&ref="+ref);
                         return;

					} else if (flag.equalsIgnoreCase("false")) {
						
							response.sendRedirect("wait?result="+flag+"&msisdn=" + ani+"&ref="+ref);
                         return;
						
					}
					return;
				} else {
					
					response.sendRedirect(sdpurl);
					return;
				}

			}   else if (status == 1) {
				
					session.setAttribute("user", ani);
					response.sendRedirect("index");
					return;
			}	else if (status == 2) {
					session.setAttribute("user", ani);
//					response.sendRedirect("waiting.jsp?status="+status+"&ref="+ref);
					response.sendRedirect("index");

					return;
			}	
			   else if (status == 3 ) {
					session.setAttribute("user", ani);
					response.sendRedirect("index");
//					response.sendRedirect("waiting.jsp?status="+status+"&ref="+ref);
					return;
			}	
			
			
			
			
		}

	}

	public String checkAni(String ani) {
		String countyCode = "27";
		if (ani.startsWith("0"))
			ani = ani.substring(1);
		if (ani.startsWith("+"))
			ani = ani.substring(1);
		int len = countyCode.length();
		if (ani.substring(0, len).equals(countyCode))
			ani = ani.substring(len);
		if (ani.contains(" "))
			ani = ani.replace(" ", "");
		return ani;
	}

}
