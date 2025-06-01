package application;

import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
		System.out.println("==== TEST 1: department findById ====");
		Department department = departmentDao.findById(3);
		System.out.println(department);
		
		System.out.println("==== TEST 2: department findAll ====");
		List<Department> list = departmentDao.findAll();
		
		for (Department obj : list) {
			System.out.println(obj);
		}
		
		
		System.out.println("==== TEST 5: department update ====");
		department = departmentDao.findById(1);
		department.setName("Food");
		departmentDao.update(department);
		System.out.println("Update completed");
		
		System.out.println("==== TEST 6: department delete ====");
		System.out.print("Enter id for delete: ");
		int id = sc.nextInt();
		
		departmentDao.deleteById(id);
		System.out.println("Delete completed");
		
		sc.close();
	}

}
