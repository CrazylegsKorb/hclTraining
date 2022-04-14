package com.hcl.trainingprojects.students;

	public class Student implements Comparable<Student> {
		private int rollno;
		private String name;
		private int age;

		public Student(int rollno, String name, int age) {
			this.rollno = rollno;
			this.name = name;
			this.age = age;
		}

		public int getRollno() {
			return rollno;
		}

		public void setRollno(int rollno) {
			this.rollno = rollno;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}
		
		public String getStudent() {
			return (this.rollno+" "+this.name+" "+this.age);
		}
		
		public int hashCode() {
			return this.rollno+ this.name.hashCode()+ this.age;
		}
		
		public boolean equals(Object obj) {
			Student s = (Student) obj;
			return s.age == age && s.rollno == rollno && s.name.equals(name);
		}

		public int compareTo(Student o) {	
			return name.compareTo(o.getName());
		}
}
