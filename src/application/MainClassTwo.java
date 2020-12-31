package application;

import java.util.List;

import db.HandleDatabase;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class MainClassTwo {
  public static void main(String[] args) {
	  
	  DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
	  
	  System.out.println("=== Test 1 : Department findById ===");
	  Department department = departmentDao.findById(2);
	  System.out.println(department);
	  
	  System.out.println("\n=== Test 2 : Department findAll ===");
	  List<Department> myList = departmentDao.findAll();
	  myList.stream().forEach(System.out::println);
	  
	  System.out.println("\n=== Test 3 : Department Insert ===");
	  Department newDepartment = new Department(null,"TI");
	  departmentDao.insert(newDepartment);
	  System.out.println("Iserted! New department ID = "+newDepartment.getId());
	  
	HandleDatabase.closeConnection();		  
	  
  }
}
