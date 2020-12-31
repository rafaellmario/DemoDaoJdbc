package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DbException;
import db.HandleDatabase;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao{
	
	private Connection conn; // 
	
	public SellerDaoJDBC() {
	}
	
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	
	@Override
	public void insert(Seller obj) {
		
		
	}

	@Override
	public void update(Seller obj) {
		
		
	}

	@Override
	public void deleteById(Integer id) {
		
		
	}

	@Override
	public Seller findById(Integer id) {
	  PreparedStatement state = null;
	  ResultSet result = null;
	  
	  try {
		  state = conn.prepareStatement(
				"SELECT seller.*,department.Name as DepName " + 
		  		"FROM seller INNER JOIN department " + 
		  		"ON seller.DepartmentId = department.Id " + 
		  		"WHERE seller.Id = ?");
		  state.setInt(1, id);
		  result = state.executeQuery();
		  
		  if(result.next()) {
			  Department dep = new Department();
			  dep.setId(result.getInt("DepartmentId"));
			  dep.setName(result.getString("DepName"));
			  
			  Seller seller = new Seller();
			  seller.setId(result.getInt("Id"));
			  seller.setName(result.getString("Name"));
			  seller.setEmail(result.getString("Email"));
			  seller.setBaseSalary(result.getDouble("BaseSalary"));
			  seller.setBirthDate(result.getDate("BirthDate"));
			  seller.setDepartment(dep);
			  
			  return seller;
		  }
		  else 
			  return null;
	  }
	  catch(SQLException e) {
		  throw new DbException(e.getMessage());
	  }
	  finally {
		  HandleDatabase.closeStatement(state);
		  HandleDatabase.closeResultSet(result);
	  }
	}

	@Override
	public List<Seller> findAll() {
		
		return null;
	}
	
	

}
