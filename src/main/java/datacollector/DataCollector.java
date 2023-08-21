package datacollector;

import java.sql.PreparedStatement;

import api.Loader;
import net.sf.uadetector.OperatingSystem;
import net.sf.uadetector.ReadableUserAgent;
import net.sf.uadetector.UserAgent;
import net.sf.uadetector.UserAgentStringParser;
import net.sf.uadetector.service.UADetectorServiceFactory;

public class DataCollector {

	
	public static void insertOffnetUser(String tag,String ref,String agent)
	{
		
		try {
			String query="insert into tbl_offnetuser (portal,tag,ref,device)values('cashbattle','"+tag+"','"+ref+"','"+getDevice(agent)+"')";
			PreparedStatement ps=Loader.con.prepareStatement(query);
			System.out.println(query);
			ps.executeUpdate();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

	public static void updateOffnetUser(String ref, String ani) {
		
		try {
			String query="update tbl_offnetuser set ani='"+ani+"' where ref='"+ref+"' ORDER BY DATETIME DESC LIMIT 1";
			PreparedStatement ps=Loader.con.prepareStatement(query);
			System.out.println(query);
			ps.executeUpdate();
				
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	public static String   getDevice(String agent ) {
		
		try {
			
		UserAgentStringParser parser =UADetectorServiceFactory.getResourceModuleParser();
		ReadableUserAgent user=parser.parse(agent);
		OperatingSystem  os=user.getOperatingSystem();
		return os.getFamilyName().toUpperCase();
		
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return null;
		
		
	}
	
	public static void insertUserDevice(String ani,String agent)
	{
		
		try {
			String query="insert into tbl_user_device (ani,device)values('"+ani+"','"+getDevice(agent)+"')";
			PreparedStatement ps=Loader.con.prepareStatement(query);
			System.out.println(query);
			ps.executeUpdate();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	
}
