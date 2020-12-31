package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class MainClassTwo {
  public static void main(String[] args) {
	  
	  DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
	  
	  System.out.println("=== Test 1 : Department findById ===");
	  Department department = departmentDao.findById(2);
	  System.out.println(department);
	  
	  
	  
	  
	  
  }
}
