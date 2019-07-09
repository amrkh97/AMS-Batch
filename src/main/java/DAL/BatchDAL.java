package DAL;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import DB.DBManager;
import Models.Medicine;

public class BatchDAL {

	public static Long createBatch(Long id, Medicine currentMedicine) {
		String SPsql = "USE KAN_AMO;  EXEC [dbo].[usp_BatchMedicine_Insert] ?,?,?";
		Connection conn = DBManager.getDBConn();
		try {

			CallableStatement cstmt = conn.prepareCall(SPsql);

			cstmt.setLong(1, id);
			cstmt.setString(2, currentMedicine.getBarCode());
			cstmt.setInt(3, currentMedicine.getCountInStock());
			cstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				System.out.println("Connection Closed");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return id;
	}
	
	public static String updateAmbulanceMapWithBatch(Integer VIN,Long ID) {
		String SPsql = "USE KAN_AMO;  EXEC [dbo].[usp_AmbulanceMap_Insert_Batch] ?,?,?";
		Connection conn = DBManager.getDBConn();
		String Result = "FF";
		try {

			CallableStatement cstmt = conn.prepareCall(SPsql);

			cstmt.setInt(1, VIN);
			cstmt.setLong(2, ID);
			cstmt.registerOutParameter(3, Types.NVARCHAR);
			cstmt.executeUpdate();
			Result = cstmt.getString(3);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				System.out.println("Connection Closed");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return Result;
	}

}
