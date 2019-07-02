package DAL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import DB.DBManager;
import Models.Medicine;

public class BatchDAL {
	//TODO: Add Model of Medicine Here as input
	public static Long createBatch(Long id,Medicine currentMedicine) {
		String SPsql = "USE KAN_AMO;  EXEC [dbo].[usp_BatchMedicine_Insert] ?,?,?";
		Connection conn = DBManager.getDBConn();
		try {
			
			CallableStatement cstmt = conn.prepareCall(SPsql);		
			//TODO:Remove next line and get data from medicine model.
			
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


}
