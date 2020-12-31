package application;

import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class MainClass {
  public static void main(String[] args) {

	  
	  SellerDao sellerDao = DaoFactory.createSellerDao();
	  
	  System.out.println("=== Test 1 : seller findById ===");
	  Seller seller = sellerDao.findById(3);
	  System.out.println(seller);
	  
	  System.out.println("\n=== Test 2 : seller findByDepartment ===");
	  Department dep = new Department(2,null);
	  List<Seller> list = sellerDao.findByDepartment(dep);
	  for(Seller obj : list)
		  System.out.println(obj);
	  
	  System.out.println("\n=== Test 3 : seller findAll ===");
	  list = sellerDao.findAll();
	  for(Seller obj : list)
		  System.out.println(obj);
	  
	  System.out.println("\n=== Test 4 : seller Insert ===");
	  Seller newSeller = new Seller(null, "Greg Black", "greg@gmail.com", new Date(), 4000.00, dep);
	  sellerDao.insert(newSeller);
	  System.out.println("Inserted! New Id = "+newSeller.getId());
	  
	  System.out.println("\n=== Test 4 : seller Update ===");
	  seller = sellerDao.findById(8);
	  seller.setName("Marta Wayne");
	  sellerDao.update(seller);
	  System.out.println("Update Completed");
	  
  }
}
