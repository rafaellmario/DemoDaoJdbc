package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DbException;
import db.HandleDatabase;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {

	private Connection conn = null;
	
// Constructors 
	public DepartmentDaoJDBC() {
	}
	
	public DepartmentDaoJDBC(Connection conn) {
		this.conn  = conn;
	}
	
// Methods	
	@Override
	public void insert(Department obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Department obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Department findById(Integer id) {
		PreparedStatement state = null; 
		ResultSet result = null;
		
		try {
			state = this.conn.prepareStatement(
					"SELECT * FROM department WHERE Id = ?");
			state.setInt(1, id);
			result = state.executeQuery();
			
			if(result.next()) {
				Department dep = new Department();
				dep.setId(result.getInt("Id"));
				dep.setName(result.getString("Name"));
				
				return dep;
			}
			else
				return null;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			HandleDatabase.closeResultSet(result);
			HandleDatabase.closeStatement(state);
		}
	}

	@Override
	public List<Department> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
