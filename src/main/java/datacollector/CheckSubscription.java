package datacollector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import api.Loader;

public class CheckSubscription {

	public static int checkUser(String ani) {
		int status = 0;
		try {
			int count = 0;
			String query = "select count(ani) as cnt from tbl_subscription where ani='" + ani
					+ "' and service_type='CashBattle'";
			PreparedStatement ps = Loader.con.prepareStatement(query);
			System.out.println(query);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				count = rs.getInt(1);

			}
			if (count >= 1) {
					count = 0;
					query = "select count(1) as cnt from tbl_subscription where ani='" + ani
							+ "' and service_type='CashBattle' " + "and date(next_billed_date)>= Date(now()) ";
					PreparedStatement pst = Loader.con.prepareStatement(query);
					System.out.println(query);
					ResultSet rst = pst.executeQuery();
					if (rst.next()) {
						count = rst.getInt(1);
					}
						if (count >= 1) {
							status = 1;
						} else {
							status = 2; 	
							
							query = "select count(1) as cnt from tbl_subscription where ani='" + ani
									+ "' and service_type='CashBattle' " + "and next_billed_date is null and last_billed_date is null";
							PreparedStatement pstt = Loader.con.prepareStatement(query);
							ResultSet rstt=pstt.executeQuery();
							System.out.println(query);

							rstt.next();
							 count=rstt.getInt(1);
							if(count >= 1)
							{
								  status=3;
								
							}
							
							}
			} else {
				status = 0;

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;
	}

}
