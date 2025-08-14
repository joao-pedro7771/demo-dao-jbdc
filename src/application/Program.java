package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			char resp;

			do {

				System.out.println("Welcome to Data Base");
				System.out.println("[1] Manage Department");
				System.out.println("[2] Manage Seller");
				int option = sc.nextInt();

				if (option == 1) {

					DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

					System.out.println("[1] Insert Department");
					System.out.println("[2] Update Department");
					System.out.println("[3] Delete Department by Id");
					System.out.println("[4] Find Department by Id");
					System.out.println("[5] Find All");
					int departmentOp = sc.nextInt();

					switch (departmentOp) {

					case 1:

						System.out.print("Enter the name of the new Department: ");
						sc.nextLine();
						String newDep = sc.nextLine();

						Department newDepartment = new Department(null, newDep);
						departmentDao.insert(newDepartment);

						System.out.println("Inserted! New Id: " + newDepartment.getId());
						break;

					case 2:

						System.out.print("Enter the Id of the Department to update: ");
						int updateByid = sc.nextInt();

						System.out.print("Enter the new name: ");
						sc.nextLine();
						String newName = sc.nextLine();

						Department departmentUpdate = departmentDao.findById(updateByid);
						departmentUpdate.setName(newName);
						departmentDao.update(departmentUpdate);
						System.out.println("Updated Successfully!");
						break;

					case 3:

						System.out.print("Enter the Id of the Department to delete: ");
						int delete = sc.nextInt();
						sc.nextLine();

						departmentDao.deleteById(delete);

						System.out.println("Deleted Successfully!");
						break;

					case 4:

						System.out.print("Enter the Id of the Department to be found: ");
						int findId = sc.nextInt();
						sc.nextLine();

						Department foundDepartment = departmentDao.findById(findId);
						System.out.println(foundDepartment);
						break;

					case 5:

						List<Department> depList = departmentDao.findAll();
						for (Department dep : depList) {
							System.out.println(dep);
						}
						break;

					default:

						System.out.println("Invalid Option!");

					}

				} else if (option == 2) {

					SellerDao sellerDao = DaoFactory.createSellerDao();

					System.out.println("[1] Insert Seller");
					System.out.println("[2] Update Seller");
					System.out.println("[3] Delete Seller by Id");
					System.out.println("[4] Find Seller by Id");
					System.out.println("[5] Find Seller by Department");
					System.out.println("[6] Find All");
					int sellerOp = sc.nextInt();

					switch (sellerOp) {

					case 1:

						System.out.print("Name of new Seller: ");
						sc.nextLine();
						String name = sc.nextLine();

						System.out.print("Email: ");
						String email = sc.nextLine();

						System.out.print("Birth Date: ");
						Date birthDate = sdf.parse(sc.nextLine());

						System.out.print("Base Salary: ");
						double baseSalary = sc.nextDouble();

						System.out.print("Id of Department: ");
						int insertId = sc.nextInt();
						sc.nextLine();

						Seller newSeller = new Seller(null, name, email, birthDate, baseSalary,
								new Department(insertId, null));
						sellerDao.insert(newSeller);

						System.out.println("Inserted! New Id: " + newSeller.getId());
						break;

					case 2:

						System.out.print("Enter the Id of the Seller to update: ");
						int updateByid = sc.nextInt();

						System.out.print("Enter the new name: ");
						sc.nextLine();
						String newSellerName = sc.nextLine();

						Seller sellerUpdate = sellerDao.findById(updateByid);
						sellerUpdate.setName(newSellerName);
						sellerDao.update(sellerUpdate);
						System.out.println("Updated Successfully!");
						break;

					case 3:

						System.out.print("Enter Id of the Seller to delete: ");
						int delete = sc.nextInt();
						sc.nextLine();

						sellerDao.deleteById(delete);
						System.out.println("Deleted Successfully!");
						break;

					case 4:

						System.out.print("Enter Id of the Seller to be found: ");
						int findId = sc.nextInt();
						sc.nextLine();

						Seller foundSeller = sellerDao.findById(findId);
						System.out.println(foundSeller);
						break;

					case 5:

						System.out.print("Enter the Id of the Department: ");
						int depId = sc.nextInt();
						sc.nextLine();

						List<Seller> sellerList = sellerDao.findByDepartment(new Department(depId, null));

						for (Seller seller : sellerList) {
							System.out.println(seller);
						}
						break;

					case 6:

						sellerList = sellerDao.findAll();
						for (Seller seller : sellerList) {
							System.out.println(seller);
						}
						break;

					default:

						System.out.println("Invalid Option!");

					}

				} else {
					System.out.println("Invalid Option!");
				}

				System.out.println("Deseja continuar? [Y]es / [N]o");
				resp = sc.next().charAt(0);
			} while (resp == 'y' || resp == 'Y');

			sc.close();
		} catch (ParseException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

}
