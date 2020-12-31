package model.dao;

import db.HandleDatabase;
import model.dao.impl.SellerDaoJDBC;

public class DaoFactory {
	
	
  public static SellerDao createSellerDao() {
	 return new SellerDaoJDBC(HandleDatabase.getConnection()); 
  }
  
  
}
