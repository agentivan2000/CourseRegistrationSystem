import java.io.*;
import java.util.*;


public class Course extends Student{
	//private String courseId;
	private String studentId;

	public Course() {
	}

	public Course(Student std) {
		studentId = std.getStudentId();
	}

	public int unRegisterByCourseName(String courseName) throws IOException{

		File courseFile2 = new File("coursesRegisteredFor.txt");
		if (!courseFile2.exists()) {
			throw new IOException();
		} else {
			Scanner c = new Scanner(courseFile2);

			while (c.hasNext()) {
				String[] input_list = c.nextLine().split(",");
				//check if the studentId
				if ((input_list[0].equals(studentId) && input_list[2].equals(courseName.trim()))) {
					c.close();
					String courseId = input_list[1];
					return unRegister(courseId.trim());
				}
			}
			c.close();
			return -1;}
	}

	public int unRegister(String courseId) throws IOException{
		//Check if student is already registered for the course
		//File Format: studentID,courseID,CourseName


		File courseFile2 = new File("coursesRegisteredFor.txt");
		if (!courseFile2.exists()) {
			throw new IOException();
		} else {
			Scanner c = new Scanner(courseFile2);
			boolean courseFound = false;

			while (c.hasNext()) {
				String[] input_list = c.nextLine().split(",");

				//check if the studentId
				if (input_list[0].equals(studentId)) {
					if(input_list[1].equals(courseId)) {
						c.close();
						courseFound = true;
						break;
					}
				}
			}
			c.close();
			if(!courseFound) {
				return 1; // Not registered for the course
			}	
		}


		//Unregister student by removing from the coursesRegisteredFor.txt file

		File c_all_file = new File("coursesRegisteredFor.txt");
		if (!c_all_file.exists()) {
			throw new IOException();
		} else {
			Scanner c1 = new Scanner(c_all_file);

			File tempFile = new File("temp");
			PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

			while (c1.hasNext()) {
				String input_list = c1.nextLine();

				if (input_list.contains(courseId)) {
					continue;
				}
				pw.println(input_list);
				pw.flush();
			}
			pw.close();
			c1.close();

			//remove the courseAvailable file to be replaced wit updated file. 
			if (c_all_file.exists()) {
				c_all_file.delete();
			}

			// Rename the tempFile file to courseAvailable.
			if (!c_all_file.exists()) {
				tempFile.renameTo(c_all_file);
			}
		}
		//Update the courseAvailable text to show the new available seats. 
		File c_all_file2 = new File("coursesAvailable.txt");
		if (!c_all_file2.exists()) {
			throw new IOException();
		} else {
			Scanner c2 = new Scanner(c_all_file2);

			File tempFile2 = new File("temp");
			PrintWriter pw2 = new PrintWriter(new FileWriter(tempFile2));

			while (c2.hasNext()) {
				String input_list = c2.nextLine();

				if (input_list.contains(courseId)) {
					String[] input_list2 = input_list.split(",");

					int new_student_cnt = 0;
					//Decrement the number of registered students for the course
					if (!(new_student_cnt == 0)) {
						new_student_cnt = Integer.parseInt(input_list2[2]) - 1;
					}

					String new_student_cnt_st = new_student_cnt+"";
					//input_list.replaceAll(text1,text2);
					String output_list = input_list2[0]+","+input_list2[1]+","+new_student_cnt_st+
							","+input_list2[3]+","+"true"+","+input_list2[5]+","+input_list2[6]+","+input_list2[7];
					input_list = output_list;
				}
				pw2.println(input_list);
				pw2.flush();
			}
			pw2.close();
			c2.close();

			//remove the courseAvailable file to be replaced wit updated file. 
			if (c_all_file2.exists()) {
				c_all_file2.delete();
			}

			// Rename the tempFile file to courseAvailable.
			if (!c_all_file2.exists()) {
				tempFile2.renameTo(c_all_file2);
			}
		}
		return 0;
	}

	public int registerByCourseName(String courseName) throws IOException {
		File courseFile = new File("coursesAvailable.txt");
		if (!courseFile.exists()) {
			throw new IOException();
		} else {
			Scanner scann = new Scanner(courseFile);

			while (scann.hasNext()) {
				String[] input_list = scann.nextLine().split(",");

				if (input_list[1].equals(courseName)) {
					String courseId = input_list[0];
					scann.close();
					return this.register(courseId);
				}
			}
			scann.close();
		}
		return -1;
	}

	public int register(String courseId) throws IOException {

		//Check if the course is full
		//File Format: courseID,CourseName,NumberOfStudentsRegistered,Max_Students,CanRegister

		File courseFile = new File("coursesAvailable.txt");
		if (!courseFile.exists()) {
			throw new IOException();
		} else {
			Scanner scann = new Scanner(courseFile);

			while (scann.hasNext()) {
				String[] input_list = scann.nextLine().split(",");

				if (input_list[0].equals(courseId)) {
					if(Integer.parseInt(input_list[2]) >= Integer.parseInt(input_list[3])) {
						scann.close();
						return 2; //Course already full
					}  
				}
			}
			scann.close();
		}
		//Check if student is already registered for the course
		//File Format: studentID,courseID,CourseName

		File courseFile2 = new File("coursesRegisteredFor.txt");
		if (!courseFile2.exists()) {
			throw new IOException();
		} else {
			Scanner c = new Scanner(courseFile2);

			while (c.hasNext()) {
				String[] input_list = c.nextLine().split(",");

				//check if the studentId
				if (input_list[0].equals(studentId) && input_list[1].equals(courseId)) {
					c.close();
					return 1; //already registered
				}
			}
			c.close();
		}
		//Go on and register the student for the course if pass the above
		//Update the coursesAvailable.txt with the new count of NumberOfStudentsRegistered

		File c_all_file = new File("coursesAvailable.txt");
		String courseName = null;
		String startDate = null;
		String endDate = null;
		String description = null;

		if (!c_all_file.exists()) {
			throw new IOException();
		} else {
			Scanner c1 = new Scanner(c_all_file);
			boolean CanRegister = true;

			File tempFile = new File("temp");
			PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

			while (c1.hasNext()) {
				String input_list = c1.nextLine();
				String[] input_list2 = input_list.split(",");

				if (input_list2[0].equals(courseId)) {
					courseName = input_list2[1];
					startDate = input_list2[5];	
					endDate = input_list2[6];
					description = input_list2[7];
					int new_student_cnt = 0;
					//Increment the number of registered students for the course
					if (!(new_student_cnt >= Integer.parseInt(input_list2[3]))) {
						new_student_cnt = Integer.parseInt(input_list2[2]) + 1;
					}

					if(new_student_cnt == Integer.parseInt(input_list2[3])) {
						CanRegister = false;
					}

					String new_student_cnt_st = new_student_cnt+"";
					//input_list.replaceAll(text1,text2);
					String output_list = input_list2[0]+","+input_list2[1]+","+new_student_cnt_st+
							","+input_list2[3]+","+CanRegister+","+input_list2[5]+","+input_list2[6]+","+input_list2[7]; 
					input_list = output_list;
				}
				pw.println(input_list);
				pw.flush();
			}
			pw.close();
			c1.close();

			//remove the courseAvailable file to be replaced wit updated file. 
			if (c_all_file.exists()) {
				c_all_file.delete();
			}

			// Rename the tempFile file to courseAvailable.
			if (!c_all_file.exists()) {
				tempFile.renameTo(c_all_file);
			}
		}
		//Update coursesRegisteredFor.txt with the new details
		File c_file = new File("coursesRegisteredFor.txt");

		if (!c_file.exists()) {
			c_file.createNewFile();
		}

		//String studentId = this.getStudentId();

		String[] courseRegistered = {studentId,courseId,courseName,startDate,endDate,description};

		// Append records to an existing file rather than overwrite it.
		FileWriter fw = new FileWriter(c_file, true);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter out = new PrintWriter(bw);

		out.print(courseRegistered[0]);

		for (int i = 1; i < courseRegistered.length; i++) {
			out.print("," + courseRegistered[i]);
		}
		out.print("\n");
		out.close();

		//update the coursesRegisteredFor array details by calling the method.
		//this.registeredFor();

		return 0; // Successful registration
	}

	public ArrayList<String> registeredFor() throws IOException {

		File courseFile = new File("coursesRegisteredFor.txt");
		Scanner scann = new Scanner(courseFile);

		ArrayList<String> studentCourseDetails = new ArrayList<String>();

		while (scann.hasNext()) {
			String[] input_list = scann.nextLine().split(",");

			//check if the studentId
			if (input_list[0].equals(studentId)) {
				studentCourseDetails.add(input_list[2]); //Add the course name to an arraylist
			}
		}
		scann.close();
		Collections.sort(studentCourseDetails);

		return studentCourseDetails;
	}

	public ArrayList<String> availableCourses() throws IOException {

		File courseFile = new File("coursesAvailable.txt");
		ArrayList<String> availableCourseDetails = new ArrayList<String>();

		if (!courseFile.exists()) {
			throw new IOException();
		} else {
			Scanner scann = new Scanner(courseFile);

			while (scann.hasNext()) {
				String[] input_list = scann.nextLine().split(",");
				String list2 ;

				if (input_list[2].equals("NumberOfStudentsRegistered")){
					continue;
				}

				if(Integer.parseInt(input_list[2]) >= Integer.parseInt(input_list[3])) {
					list2 = input_list[1] + " - Full"; 
				} else {
					int availableSeats = Integer.parseInt(input_list[3]) - Integer.parseInt(input_list[2]);
					list2 = input_list[1] + " - Available Seats: "+availableSeats;
				}

				availableCourseDetails.add(list2);
			}
			scann.close();
			Collections.sort(availableCourseDetails);

			return availableCourseDetails;
		}
	}
	public ArrayList<String> courseDetails(String courseName) throws IOException {

		File courseFile = new File("coursesAvailable.txt");
		ArrayList<String> courseDetails = new ArrayList<String>();

		if (!courseFile.exists()) {
			throw new IOException();
		} else if (courseName.isEmpty()){
			throw new IOException();
		} else {
			Scanner scann = new Scanner(courseFile);

			while (scann.hasNext()) {

				String list = scann.nextLine();
				if (list.contains(courseName.trim())) {
					courseDetails.addAll(Arrays.asList(list.split(",")));
				}
			}
			scann.close();
			return courseDetails;
		}	
	}
}
