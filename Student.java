import java.io.*;
import java.io.IOException;
import java.util.*;

public class Student {
	private boolean authenticated = false;
	private String userName;
	private int password;
	private String studentId;
	private String firstName;
	private String middleName;
	private String lastName;
	private String dateOfBirth;

	public Student() {
	}

	public Student(String user) {
		userName = user;
	}
	
	public void setToNull() {
		studentId = null;
		firstName = null;
		middleName = null;
		lastName = null;
		dateOfBirth = null;
		userName = null;
		password = 0;
		authenticated = false;
	}
	
	public boolean signUp(String[] registrationDetails) throws Exception{
		//		Check if student is already registered. 
		try 
		{
			File student_details = new File("students_details.txt");
			Scanner scann = new Scanner(student_details);
			
			if (!(student_details.exists())){
				student_details.createNewFile();
			}

			boolean end_loop = false;

			while (scann.hasNext() && end_loop == false) {
				String input_list = scann.nextLine();

				//check if the studentId
				if (input_list.contains(registrationDetails[1])) {
					scann.close();
					return false;
				}
			}
			scann.close();

			//If new student. Set the student details 

			studentId = registrationDetails[1];
			firstName = registrationDetails[2];
			middleName = registrationDetails[3];
			lastName = registrationDetails[4];
			dateOfBirth = registrationDetails[5];
			userName = registrationDetails[0];
			password = registrationDetails[6].hashCode() * registrationDetails[0].hashCode();

			// save details in the students file and the authentication file
			String[] students_details = {this.userName,this.studentId,this.firstName,this.middleName,this.lastName,this.dateOfBirth};
			saveDetails(students_details,"students_details.txt");

			String[] authentication_details = {this.userName,this.password + "",this.studentId};
			saveDetails(authentication_details,"authentication.txt");
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return true;
	}

	public String getStudentId() {
		return studentId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public boolean authentication(String user, String pass) {

		try {
			int loginhash = user.hashCode() * pass.hashCode();
			String login = loginhash+"";

			Scanner scan = new Scanner(new File("authentication.txt"));
			boolean stoploop = false;

			do {
				String[] inline = scan.nextLine().split(",");

				if (login.equals(inline[1])) {
					setStudentDetails(userName,login);
					authenticated = true;
					stoploop = true;
				}

			} while (scan.hasNext() && stoploop == false);

			scan.close();
		} catch (IOException ex) {
			System.out.println(ex);
		}
		return authenticated;
	}

	private void setStudentDetails(String user, String login) {
		try {
			File student_auth = new File("authentication.txt");
			Scanner scanner1 = new Scanner(student_auth);

			boolean end_loop1 = false;

			while (scanner1.hasNext() && end_loop1 == false) {
				String input_list = scanner1.nextLine();
				if (input_list.contains(user) && input_list.contains(login)) {
					String[] student_auth_array = input_list.split(",");
					studentId = student_auth_array[2];
					end_loop1 = true;
				}
			}
			scanner1.close();

			File student_details = new File("students_details.txt");
			Scanner scanner = new Scanner(student_details);

			boolean end_loop = false;

			while (scanner.hasNext() && end_loop == false) {
				String input_list = scanner.nextLine();
				if (input_list.contains(user) && input_list.contains(studentId)) {
					String[] student_records = input_list.split(",");

					studentId = student_records[1];
					firstName = student_records[2];
					middleName = student_records[3];
					lastName = student_records[4];
					dateOfBirth = student_records[5];
					end_loop = true;
				}
			}
			scanner.close();
		} catch (IOException ex) {
			System.out.println(ex);
		}
	}

	public void saveDetails(String[] new_input,
			String fileName ) {
		try {
			String[] possibleFileNames = { "students_details.txt", "authentication.txt", "availableCourses.txt",
			"coursesRegisteredFor.txt" };

			if (Arrays.asList(possibleFileNames).contains(fileName)) {
				File student_details = new File(fileName);

				if (!student_details.exists()) {
					student_details.createNewFile();
				}

				// Append records to an existing file rather than overwrite it.
				FileWriter fw = new FileWriter(student_details, true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw);

				out.print(new_input[0]);

				for (int i = 1; i < new_input.length; i++) {
					out.print("," + new_input[i]);
				}
				out.print("\n");
				out.close();
			}
		} catch (IOException ex) {
			System.out.println(ex);
		}
	}
}
