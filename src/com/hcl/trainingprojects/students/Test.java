package com.hcl.trainingprojects.students;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Test {
	public static void main(String[] args) {
		/*
		 * The requirement is, you need to build a menu driven program, that has ability
		 * to create, update, delete and read students based on sorted order.
		 * 
		 * For update and delete you need to pass the roll number and it should retrieve
		 * the student and you should be able to change the name or age.
		 */
		Set<Student> school = new TreeSet<>();
		Scanner bin = new Scanner(System.in);
		File save = new File(".\\src\\com\\hcl\\trainingprojects\\students\\save.txt");
		boolean run = findFile(save);

		importStudents(school, save);

		introBanner();
		do {
			menuBanner();

			switch (bin.next().toLowerCase()) {

			case "1":
			case "add":
				add(school, bin); // adds student to set if not already in set
				break;

			case "2":
			case "update":
				update(school, bin); // updates existing student by rollno
				break;

			case "3":
			case "remove":
				delete(school, bin); // deletes existing student by rollno
				break;

			case "4":
			case "list":
				list(school); // lists students in set
				break;

			case "5":
			case "quit":
				run = false; // flips flag to end operation loop
				break;

			default:
				errorBanner(); // unrecognized command
				break;
			}

		} while (run);

		saveStudents(school, save);
		outtroBanner();
		bin.close();
	}

	private static void saveStudents(Set<Student> school, File save) {
		try (FileWriter fw = new FileWriter(save, false);) {
			for (Student s : school) {
				fw.write(s.getStudent() + "\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void importStudents(Set<Student> school, File save) {
		try (Scanner in = new Scanner(save)) {
			String[] line;
			while (in.hasNextLine()) {
				line = in.nextLine().split(" ");
				school.add(new Student(Integer.parseInt(line[0]), line[1], Integer.parseInt(line[2])));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static boolean findFile(File save) {
		try {
			if (save.createNewFile()) {
				System.out.println("Save file created!\n");
			} else {
				System.out.println("Save file found!\n");
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	// list all students
	private static void list(Set<Student> school) {
		if(school.isEmpty()) {
			System.out.println("\nNo students enrolled!");
		}else {
			for (Student i : school) {
				System.out.println("\n# Name = " + i.getName());
				System.out.println("# Age  = " + i.getAge());
				System.out.println("# Id   = " + i.getRollno());
			}
		}
	}

	// removes student from set using rollno, prints if successful
	private static void delete(Set<Student> school, Scanner bin) {
		boolean flag = true;
		System.out.println("\nPlease enter student id");
		System.out.print(": ");
		int rollno = getNum(bin);
		// find student by id
		for (Student i : school) {
			// if student with rollno found
			if (i.getRollno() == rollno) {
				school.remove(i);
				flag = false;
				System.out.println("\nStudent with id = " + rollno + " removed!");
			}
		}
		// if student with rollno not found
		if (flag) {
			System.out.println("\nStudent with id = " + rollno + " not found!");
		}
	}

	// updates existing student from set using rollno, prints if successful
	private static void update(Set<Student> school, Scanner bin) {
		boolean flag = true;
		System.out.println("\nPlease enter student id");
		System.out.print(": ");
		int rollno = getNum(bin);
		// find student by id
		for (Student i : school) {
			// if student with rollno found
			if (i.getRollno() == rollno) {
				// get new name and age
				System.out.println("\nPlease enter updated student name");
				System.out.print(": ");
				i.setName(bin.next());
				System.out.println("\nPlease enter updated student age");
				System.out.print(": ");
				i.setAge(getNum(bin));
				System.out.println("\nStudent updated!");
				flag = false;
			}
		}
		// if student with rollno not found
		if (flag) {
			System.out.println("\nNo student with id = " + rollno + " found!");
		}
	}

	private static void add(Set<Student> school, Scanner bin) {
		boolean flag = true;
		System.out.println("\nPlease enter student id");
		System.out.print(": ");
		int rollno = getNum(bin);
		// find student by id
		for (Student i : school) {
			// if rollno found
			if (i.getRollno() == rollno) {
				System.out.println("\nStudent with id = " + rollno + " already exists!");
				flag = false;
			}
		}
		if (flag) {
			System.out.println("\nPlease enter student name");
			System.out.print(": ");
			String name = bin.next();
			System.out.println("\nPlease enter student age");
			System.out.print(": ");
			int age = getNum(bin);
			school.add(new Student(rollno, name.substring(0, 1).toUpperCase() + name.substring(1), age));
			System.out.println("\nStudent added!");
		}
	}

	private static int getNum(Scanner uin) {
		int num = 0;
		boolean flag = true;
		do {
			try {
				num = Integer.parseInt(uin.next());
				flag = false;
			} catch (Exception e) {
				System.out.println("\ninput not valid, please enter an integer");
				System.out.print(": ");
			}
		} while (flag);
		return num;
	}

	private static void errorBanner() {
		System.out.println();
		System.out.println("!! Incorrect input !!");
	}

	private static void outtroBanner() {
		System.out.println();
		System.out.println("####################################################");
		System.out.println("#                                                  #");
		System.out.println("#              Have a great day!   :)              #");
		System.out.println("#                                                  #");
		System.out.println("####################################################");
	}

	private static void menuBanner() {
		System.out.println();
		System.out.println("##################################");
		System.out.println("#   What would you like to do:   #");
		System.out.println("##################################");
		System.out.println("#                                #");
		System.out.println("#   1) Add a student:            #");
		System.out.println("#       type: \"ADD\"              #");
		System.out.println("#                                #");
		System.out.println("#   2) Update a student:         #");
		System.out.println("#       type: \"UPDATE\"           #");
		System.out.println("#                                #");
		System.out.println("#   3) Remove a student:         #");
		System.out.println("#       type: \"REMOVE\"           #");
		System.out.println("#                                #");
		System.out.println("#   4) List all students:        #");
		System.out.println("#       type: \"list\"             #");
		System.out.println("#                                #");
		System.out.println("#   5) Exit program:             #");
		System.out.println("#       type: \"QUIT\"             #");
		System.out.println("#                                #");
		System.out.println("##################################");
		System.out.print("\n: ");
	}

	private static void introBanner() {
		System.out.println("####################################################");
		System.out.println("#                                                  #");
		System.out.println("#     Welcome to the Student Management System     #");
		System.out.println("#                                                  #");
		System.out.println("####################################################");
	}

}
