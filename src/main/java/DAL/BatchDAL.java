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
	public static String createBatch(Integer id,Medicine currentMedicine) {
		String SPsql = "USE KAN_AMO;  EXEC [dbo].[usp_BatchMedicine_Insert]";
		ResultSet RS;
		String resultOut = "99";
		Connection conn = DBManager.getDBConn();
		try {
			
			CallableStatement cstmt = conn.prepareCall(SPsql);		
			//TODO:Remove next line and get data from medicine model.
			
			cstmt.setInt(1, id); 
			cstmt.setString(2, currentMedicine.getBarCode());
			cstmt.setString(3, currentMedicine.getCountInStock());
			
			RS=cstmt.executeQuery();
			RS.next();
			resultOut = RS.getString(1);
			
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
		
		
		return resultOut;
	}
	
	
	public static int checkID(Integer id) {
		
		String SPsql = "USE KAN_AMO;  EXEC [dbo].[usp_BatchMedicine_CheckID]";
		ResultSet RS;
		Integer resultOfID = 99;
		Integer randomID = id;
		Connection conn = DBManager.getDBConn();
		try {
			
			CallableStatement cstmt = conn.prepareCall(SPsql);	
			cstmt.setInt(1, randomID);
			RS=cstmt.executeQuery();
			RS.next();
		    resultOfID = RS.getInt(1);
			
			//TODO: Think of a better solution to the problem of the BatchID.
			
			while(resultOfID == 1) {
				
			    randomID = (int)(Math.random() * 99999 + 1);
			    cstmt = conn.prepareCall(SPsql);	
				cstmt.setInt(1, randomID);	
				RS=cstmt.executeQuery();
				RS.next();
				resultOfID = RS.getInt(1);
				
			}
			
			id=randomID;
			
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
