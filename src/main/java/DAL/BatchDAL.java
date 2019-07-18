package DAL;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import DB.DBManager;
import Models.ArrayOfMedicines;
import Models.Medicine;
import Models.ServerResponse;
import Models.Data.DataModel;

public class BatchDAL {

	public static String createBatch(Long id, Medicine currentMedicine, Connection singleConnection) {
		String SPsql = "USE KAN_AMO;  EXEC [dbo].[usp_BatchMedicine_Insert] ?,?,?,?";
		Connection conn = singleConnection;
		String result = "FF";
		try {

			CallableStatement cstmt = conn.prepareCall(SPsql);

			cstmt.setLong(1, id);
			cstmt.setString(2, currentMedicine.getBarCode());
			cstmt.setInt(3, currentMedicine.getCountInStock());
			cstmt.registerOutParameter(4,Types.NVARCHAR);
			cstmt.executeUpdate();
			result= cstmt.getString(4);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
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

	public static ServerResponse updateMedicinesUsed(Long batchID,Integer sequenceNumber,String barCode, Integer usedAmt ,Connection singleConnection) {
		
		String SPsql = "USE KAN_AMO;  EXEC [dbo].[usp_Batch_MedicineUsed] ?,?,?,?,?";
		Connection conn = singleConnection;
		ServerResponse result = new ServerResponse();
		try {

			CallableStatement cstmt = conn.prepareCall(SPsql);

			cstmt.setLong(1, batchID);
			cstmt.setInt(2, sequenceNumber);
			cstmt.setString(3, barCode);
			cstmt.setInt(4,usedAmt);
			cstmt.registerOutParameter(5,Types.NVARCHAR);
			cstmt.executeUpdate();
			result.setResponseHexCode(cstmt.getString(5));
			if(result.getResponseHexCode().equals("00")) {
				result.setResponseMsg("Succesfully Updated");
			}else {
				result.setResponseMsg("Update Failed!");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
	
	public static ArrayList<Medicine> getAllMedicines(Long batchID) {
		ArrayList<Medicine> arrayOfMedicines = new ArrayList<Medicine>();
		
		String SPsql = "USE KAN_AMO;  EXEC [dbo].[usp_Batch_getMedicines] ?";
		Connection conn = DBManager.getDBConn();
		ResultSet rs;
		try {

			CallableStatement cstmt = conn.prepareCall(SPsql);

			cstmt.setLong(1, batchID);
			rs = cstmt.executeQuery();
			while(rs.next()) {
				
				Medicine medicine = new Medicine();
				medicine.setBarCode(rs.getString(1));
				medicine.setMedicineName(rs.getString(2));
				medicine.setPrice(rs.getInt(3));
				medicine.setCountInStock(rs.getInt(4));
				medicine.setImplications(rs.getString(5));
				medicine.setMedicineUsage(rs.getString(6));
				medicine.setSideEffects(rs.getString(7));
				medicine.setActiveComponent(rs.getString(8));
				medicine.setMedicineStatus(rs.getString(9));
				arrayOfMedicines.add(medicine);
			}
			rs.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return arrayOfMedicines;
	}

}
