import java.awt.*;
import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SignUpScreen {

	protected JFrame signUpFrame;
	private TextField userName,studentId,firstName,middleName,lastName,dateOfBirth;
	private JPasswordField password1;
	private JPasswordField password2;

	public SignUpScreen() {
		signUpFrame = new JFrame();
		signUpFrame.setTitle("TeamD: Course Registration System");
		signUpFrame.setBounds(100, 100, 764, 486);
		signUpFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		signUpFrame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(108, 45, 195, 324);
		signUpFrame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblFirstName = new JLabel("First Name:*");
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFirstName.setBounds(10, 11, 128, 24);
		panel.add(lblFirstName);

		JLabel lblMiddleName = new JLabel("Middle Name:");
		lblMiddleName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMiddleName.setBounds(10, 46, 128, 24);
		panel.add(lblMiddleName);

		JLabel lblLastName = new JLabel("Last Name:*");
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLastName.setBounds(10, 81, 128, 24);
		panel.add(lblLastName);

		JLabel lblDateOfBirth = new JLabel("Date of Birth (MM/DD/YYYY):* ");
		lblDateOfBirth.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDateOfBirth.setBounds(10, 118, 188, 26);
		panel.add(lblDateOfBirth);

		JLabel lblStudentId = new JLabel("Student ID:* ");
		lblStudentId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblStudentId.setBounds(10, 151, 175, 30);
		panel.add(lblStudentId);

		JLabel lblUserName = new JLabel("User Name:*");
		lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUserName.setBounds(10, 196, 138, 27);
		panel.add(lblUserName);

		JLabel lblPassword = new JLabel("Password:*");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(10, 235, 128, 24);
		panel.add(lblPassword);

		JLabel lblRepeatPassword = new JLabel("Repeat Password:*");
		lblRepeatPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRepeatPassword.setBounds(10, 270, 128, 31);
		panel.add(lblRepeatPassword);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(313, 45, 253, 324);
		signUpFrame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		firstName = new TextField();
		firstName.setBounds(10, 10, 220, 22);
		panel_1.add(firstName);

		middleName = new TextField();
		middleName.setBounds(10, 48, 220, 22);
		panel_1.add(middleName);

		lastName = new TextField();
		lastName.setBounds(10, 81, 220, 22);
		panel_1.add(lastName);

		dateOfBirth = new TextField();
		dateOfBirth.setBounds(10, 118, 220, 22);
		panel_1.add(dateOfBirth);

		studentId = new TextField();
		studentId.setBounds(10, 154, 220, 22);
		panel_1.add(studentId);

		userName = new TextField();
		userName.setBounds(10, 200, 220, 22);
		panel_1.add(userName);

		password1 = new JPasswordField();
		password1.setBounds(10, 241, 220, 22);
		panel_1.add(password1);

		password2 = new JPasswordField();
		password2.setText("");
		password2.setBounds(10, 274, 220, 22);
		panel_1.add(password2);

		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!(password1.getText().trim().equals(password2.getText().trim()))) {
					JOptionPane.showMessageDialog(null,"Passwords do not match. Please try again");
				} else {
					try
					{
						if (userName.getText().isEmpty()|studentId.getText().isEmpty()|firstName.getText().isEmpty()|lastName.getText().isEmpty()|
								dateOfBirth.getText().isEmpty()|password1.getText().isEmpty()){
							JOptionPane.showMessageDialog(null,"Please enter all required details marked by an asterics.");
						}else {
							String[] registrationDetails = {userName.getText(),studentId.getText(),firstName.getText(),
									middleName.getText(),lastName.getText(),dateOfBirth.getText(),password1.getText()};
							Student new_student = new Student(userName.getText());
							boolean x = new_student.signUp(registrationDetails);
							if (x == false){
								JOptionPane.showMessageDialog(null,"Student ID already registered. Please try resetting your password in case you've forgotten it.");
							} else {
								Course course = new Course(new_student);
								signUpFrame.dispose();
								StudentProfile s_profile = new StudentProfile(new_student, course);
								s_profile.studentProfileFrame.setVisible(true);
							}
						}
					} 
					catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		});
		btnSignUp.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSignUp.setBounds(158, 378, 118, 43);
		signUpFrame.getContentPane().add(btnSignUp);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				signUpFrame.dispose();
				LogInScreen login = new LogInScreen();
				login.logInFrame.setVisible(true);
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancel.setBounds(386, 380, 119, 43);
		signUpFrame.getContentPane().add(btnCancel);

		JLabel lblRegisterInOrder = new JLabel("Sign Up In Order to Be Able to Register for Courses");
		lblRegisterInOrder.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegisterInOrder.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblRegisterInOrder.setBounds(92, 11, 491, 23);
		signUpFrame.getContentPane().add(lblRegisterInOrder);
	}
}
