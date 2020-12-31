package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
			  Department dep = this.instantiateDepartment(result);
			  Seller seller = this.instantiateSeller(result, dep);
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
	public List<Seller> findByDepartment(Department dep){
		 PreparedStatement state = null;
		  ResultSet result = null;
		  
		  try {
			  state = conn.prepareStatement(
					  "SELECT seller.*,department.Name as DepName "+
					  "FROM seller INNER JOIN department "+
					  "ON seller.DepartmentId = department.Id "+
					  "WHERE DepartmentId = ? "+
					  "ORDER BY Name ");
			  
			  state.setInt(1, dep.getId());
			  result = state.executeQuery();
			  
			  List<Seller> list = new ArrayList<>();
			  Map<Integer, Department> map = new HashMap<>();
			  
			  while(result.next()) {
				  
				  Department objDep = map.get(result.getInt("DepartmentId"));
				  
				  if(objDep == null) {
					objDep = this.instantiateDepartment(result);
					map.put(result.getInt("DepartmentId"), objDep);
				  }
					  
				  Seller seller = this.instantiateSeller(result, objDep);
				  list.add(seller);
			  }
			  
			  return list;
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

// Auxiliary Methods
	private Department instantiateDepartment(ResultSet result) throws SQLException{
	  Department dep = new Department();
	  dep.setId(result.getInt("DepartmentId"));
	  dep.setName(result.getString("DepName"));
	  return dep;
	}
	
	private Seller instantiateSeller(ResultSet result, Department dep) throws SQLException{
	   Seller seller = new Seller();
	   seller.setId(result.getInt("Id"));
	   seller.setName(result.getString("Name"));
	   seller.setEmail(result.getString("Email"));
	   seller.setBaseSalary(result.getDouble("BaseSalary"));
	   seller.setBirthDate(result.getDate("BirthDate"));
	   seller.setDepartment(dep);
	   return seller;
	}

}
