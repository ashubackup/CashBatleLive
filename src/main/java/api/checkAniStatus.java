package api;

import java.sql.*;

public class checkAniStatus {
	
	
	public String getUserID(String ani) {  
		String gameid="2";
		try {
			String query = "select * from tbl_player where ani='" + ani
					+ "' and gameid='"+gameid+"'";  // mtn Cashbattle id 2
			PreparedStatement ps = Loader.con.prepareStatement(query);
			ResultSet res = ps.executeQuery();
			System.out.println(query);
			if (res.next()) {
				
				return res.getString("_id");
			}else {
				
				String Iquery  = "insert into tbl_player (gameid,ani,lastplayed) values(?,?,now())";
				PreparedStatement statement = Loader.con.prepareStatement(Iquery,Statement.RETURN_GENERATED_KEYS);
				statement.setString(1, gameid);
				statement.setString(2, ani);
				statement.executeUpdate();
				System.out.println(Iquery);
				try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						return Long.toString(generatedKeys.getLong(1));
					} else {
						return "0";
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return "0";
		}
	}

}
