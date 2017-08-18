import java.awt.*;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class LogInScreen {
	public JFrame logInFrame;
	//	final private JPasswordField passwordField;
	private JLabel lblUserName;
	private Student new_student;
	private JTextPane user1;

	public LogInScreen(){
		init();
	}

	private void init() {
		logInFrame = new JFrame();
		logInFrame.setTitle("TeamD: Course Registration System");
		logInFrame.setBounds(100, 100, 474, 406);
		logInFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		logInFrame.getContentPane().setLayout(null);

		JLabel lblPleaseEnterYour = new JLabel("Please Enter Your Log In Details");
		lblPleaseEnterYour.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPleaseEnterYour.setBounds(45, 71, 313, 32);
		logInFrame.getContentPane().add(lblPleaseEnterYour);

		lblUserName = new JLabel("User Name:");
		lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUserName.setBounds(45, 127, 89, 32);
		logInFrame.getContentPane().add(lblUserName);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(45, 170, 89, 50);
		logInFrame.getContentPane().add(lblPassword);

		final JPasswordField passwordField = new JPasswordField();
		passwordField.setBounds(164, 172, 211, 32);
		logInFrame.getContentPane().add(passwordField);

		user1 = new JTextPane();
		user1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB)
				{
					e.consume();
					KeyboardFocusManager.
					getCurrentKeyboardFocusManager().focusNextComponent();
				}

				if (e.getKeyCode() == KeyEvent.VK_TAB
						&&  e.isShiftDown())
				{
					e.consume();
					KeyboardFocusManager.
					getCurrentKeyboardFocusManager().focusPreviousComponent();
				}
			}
		});
		user1.setBounds(164, 127, 211, 32);
		logInFrame.getContentPane().add(user1);

		JButton btnSubmit = new JButton("Log In");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new_student = new Student(user1.getText());

				String userName = user1.getText().trim();//
				@SuppressWarnings("deprecation")
				String password = passwordField.getText().trim();

				if (password.isEmpty() && userName.isEmpty()) {
					JOptionPane.showMessageDialog(null,"Please enter a username and password.");
				} else if (userName.isEmpty()){
					JOptionPane.showMessageDialog(null,"Please enter a user name");
				} else if (password.isEmpty()) {
					JOptionPane.showMessageDialog(null,"Please enter a password.");
				} else {
					if(!new_student.authentication(userName,password)) {
						JOptionPane.showMessageDialog(null,"Wrong Username/Password combination. Please try again or reset your password.");
					} else {
						Course course = new Course(new_student);
						logInFrame.dispose();
						StudentProfile s_profile = new StudentProfile(new_student, course);
						s_profile.studentProfileFrame.setVisible(true);	
					}
				}
			}
		});
		btnSubmit.setBounds(45, 259, 109, 33);
		logInFrame.getContentPane().add(btnSubmit);

		JButton btnNewButton = new JButton("Register");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				logInFrame.dispose();
				SignUpScreen registration = new SignUpScreen();
				registration.signUpFrame.setVisible(true);
			}
		});
		btnNewButton.setBounds(179, 259, 109, 33);
		logInFrame.getContentPane().add(btnNewButton);

		JLabel lblWelcomeToThe = new JLabel("Welcome To The Course Registration System.");
		lblWelcomeToThe.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToThe.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblWelcomeToThe.setBounds(10, 21, 438, 31);
		logInFrame.getContentPane().add(lblWelcomeToThe);

		JButton btnPssReset = new JButton("Password Reset");
		btnPssReset.setBounds(315, 259, 133, 32);
		logInFrame.getContentPane().add(btnPssReset);
	}
}