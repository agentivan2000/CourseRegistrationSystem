import java.awt.EventQueue;
import java.awt.Font;
import java.awt.List;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class StudentProfile {

	protected JFrame studentProfileFrame;
	private List availableCourses;
	private List registeredCourses;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Student student = new Student();
					Course course =new Course();

					StudentProfile window = new StudentProfile(student,course);
					window.studentProfileFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StudentProfile(final Student student,final Course course) {
		initialize(student,course);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final Student student,final Course course) {
		studentProfileFrame = new JFrame();
		studentProfileFrame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 14));
		studentProfileFrame.setTitle("TeamD: Course Registration System");
		studentProfileFrame.setBounds(100, 100, 764, 486);
		studentProfileFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		studentProfileFrame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(20, 50, 278, 160);
		studentProfileFrame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFirstName.setBounds(10, 11, 75, 29);
		panel.add(lblFirstName);

		JLabel lblMiddleName = new JLabel("Middle Name:");
		lblMiddleName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMiddleName.setBounds(10, 44, 92, 29);
		panel.add(lblMiddleName);

		JLabel lblLastName = new JLabel("Last Name: ");
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLastName.setBounds(10, 84, 92, 29);
		panel.add(lblLastName);

		JLabel lblNewLabel = new JLabel("Student ID:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 124, 80, 18);
		panel.add(lblNewLabel);

		TextField firstName = new TextField();
		firstName.setFont(new Font("Dialog", Font.PLAIN, 14));
		firstName.setEditable(false);
		firstName.setBounds(104, 11, 164, 22);
		firstName.setText(student.getFirstName());
		panel.add(firstName);

		TextField middleName = new TextField();
		middleName.setFont(new Font("Dialog", Font.PLAIN, 14));
		middleName.setEditable(false);
		middleName.setBounds(104, 44, 164, 22);
		middleName.setText(student.getMiddleName());
		panel.add(middleName);

		TextField lastName = new TextField();
		lastName.setFont(new Font("Dialog", Font.PLAIN, 14));
		lastName.setEditable(false);
		lastName.setBounds(104, 79, 164, 22);
		lastName.setText(student.getLastName());
		panel.add(lastName);

		TextField studentId = new TextField();
		studentId.setFont(new Font("Dialog", Font.PLAIN, 14));
		studentId.setEditable(false);
		studentId.setBounds(104, 119, 164, 23);
		studentId.setText(student.getStudentId());
		panel.add(studentId);

		JLabel lblStudentDetails = new JLabel("Student Details");
		lblStudentDetails.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblStudentDetails.setBounds(10, 11, 200, 29);
		studentProfileFrame.getContentPane().add(lblStudentDetails);

		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				studentProfileFrame.dispose();
				student.setToNull();
				LogInScreen login = new LogInScreen();
				login.logInFrame.setVisible(true);
			}
		});
		btnLogOut.setBounds(649, 11, 89, 23);
		studentProfileFrame.getContentPane().add(btnLogOut);

		registeredCourses = new List();
		registeredCourses.setFont(new Font("Dialog", Font.PLAIN, 14));
		registeredCourses.setMultipleSelections(false);
		registeredCourses.setBounds(20, 256, 278, 150);
		refreshRegisteredCourses(registeredCourses,course);
		studentProfileFrame.getContentPane().add(registeredCourses);

		JButton btnUnregister = new JButton("UnRegister");
		btnUnregister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					String courseName = registeredCourses.getSelectedItem();

					int x = course.unRegisterByCourseName(courseName);
					if (x == 0) {
						JOptionPane.showMessageDialog(null,"You've successfully unregistered for"+" "+courseName);
						refreshAvailableCourses(availableCourses,course);
						refreshRegisteredCourses(registeredCourses,course);
					} else {
						JOptionPane.showMessageDialog(null,"You are not registered for"+" "+courseName);
					}
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		});
		btnUnregister.setBounds(20, 414, 278, 23);
		studentProfileFrame.getContentPane().add(btnUnregister);

		JLabel lblRegisteredCourses = new JLabel("Registered Courses");
		lblRegisteredCourses.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRegisteredCourses.setBounds(20, 221, 174, 29);
		studentProfileFrame.getContentPane().add(lblRegisteredCourses);

		availableCourses = new List();
		availableCourses.setMultipleSelections(false);
		availableCourses.setBounds(437, 104, 301, 312);
		refreshAvailableCourses(availableCourses,course);

		final JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					if (availableCourses.getSelectedItem().equals("")){
						btnRegister.setEnabled(false);
					}
					else {
						btnRegister.setEnabled(true);

						String[] x_text = availableCourses.getSelectedItem().split("-");
						String courseName = x_text[0].trim();

						int x = course.registerByCourseName(courseName);
						if (x == 0) {
							JOptionPane.showMessageDialog(null,"You've successfully registered for"+" "+courseName);
							refreshAvailableCourses(availableCourses,course);
							refreshRegisteredCourses(registeredCourses,course);
						} else if (x == 1) {
							JOptionPane.showMessageDialog(null,"You are already registered for"+" "+courseName);
						} else if (x == 2) {
							JOptionPane.showMessageDialog(null,"Sorry, "+courseName + " is already full. Please try again next term.");
						} else {
							JOptionPane.showMessageDialog(null,"Sorry, couldn't find"+" "+courseName );
						}	
					}
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		});
		btnRegister.setBounds(437, 75, 151, 23);
		studentProfileFrame.getContentPane().add(btnRegister);

		JButton btnViewCoursesDetails = new JButton("View Details");
		btnViewCoursesDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					String[] x_text = availableCourses.getSelectedItem().split("-");
					String courseName = x_text[0].trim();

					if(availableCourses.getSelectedItem().length() < 1){
						JOptionPane.showMessageDialog(null,"Please select a course to view the details.");
					}else{
						ViewCourseDetails window = new ViewCourseDetails(course,courseName.trim());
						window.viewCourseDetails.setVisible(true);
					}
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		});
		btnViewCoursesDetails.setBounds(599, 75, 139, 23);
		studentProfileFrame.getContentPane().add(btnViewCoursesDetails);

		JButton btnViewRegisteredDetails = new JButton("View Details");
		btnViewRegisteredDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					String courseName = registeredCourses.getSelectedItem();
					if(courseName.length() < 1){
						JOptionPane.showMessageDialog(null,"Please select a course to view the details.");
					}else{
						ViewCourseDetails window = new ViewCourseDetails(course,courseName.trim());
						window.viewCourseDetails.setVisible(true);
					}
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		});
		btnViewRegisteredDetails.setBounds(164, 227, 134, 23);
		studentProfileFrame.getContentPane().add(btnViewRegisteredDetails);

		JLabel lblAllCoursesOffered = new JLabel("All Courses Offered");
		lblAllCoursesOffered.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAllCoursesOffered.setBounds(440, 41, 298, 23);
		studentProfileFrame.getContentPane().add(lblAllCoursesOffered);
	}

	public void refreshAvailableCourses(List list,Course course)
	{
		try
		{
			list.clear();
			int size = course.availableCourses().size();
			{
				for (int i = 0; i < size; i++) {
					list.addItem(course.availableCourses().get(i));
				}
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		studentProfileFrame.getContentPane().add(list);
	}

	public void refreshRegisteredCourses(List list,Course course)
	{
		try
		{
			list.clear();
			int size = course.registeredFor().size();
			{
				for (int i = 0; i < size; i++) {
					list.addItem(course.registeredFor().get(i));
				}
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		studentProfileFrame.getContentPane().add(list);
	}
}


