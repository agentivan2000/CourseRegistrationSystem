import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.TextField;
import java.awt.TextArea;
import javax.swing.JTextArea;


public class ViewCourseDetails {

	protected JFrame viewCourseDetails;

	/**
	 * Launch the application.
	 */
	//	public static void main(String[] args) {
	//		EventQueue.invokeLater(new Runnable() {
	//			public void run() {
	//				try {
	//					ViewCourseDetails window = new ViewCourseDetails(course, courseName);
	//					window.viewCourseDetails.setVisible(true);
	//				} catch (Exception e) {
	//					e.printStackTrace();
	//				}
	//			}
	//		});
	//	}

	/**
	 * Create the application.
	 */
	public ViewCourseDetails(Course course, String courseName) {
		initialize(course, courseName);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Course course,String courseName) {

		viewCourseDetails = new JFrame();
		viewCourseDetails.setTitle("TeamD: Course Registration System");
		viewCourseDetails.setBounds(100, 100, 524, 441);
		viewCourseDetails.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		viewCourseDetails.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 46, 218, 288);
		viewCourseDetails.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblCourseId = new JLabel("Course ID:");
		lblCourseId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCourseId.setBounds(10, 5, 148, 23);
		panel.add(lblCourseId);

		JLabel lblNewLabel = new JLabel("Course Name:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 39, 148, 23);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Number of Registered Students:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 73, 208, 23);
		panel.add(lblNewLabel_1);

		JLabel lblMaximumNumberOf = new JLabel("Maximum Number of Student:");
		lblMaximumNumberOf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMaximumNumberOf.setBounds(10, 107, 202, 23);
		panel.add(lblMaximumNumberOf);

		JLabel lblNewLabel_2 = new JLabel("Can Students Register:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(10, 141, 202, 23);
		panel.add(lblNewLabel_2);

		JLabel lblStartDate = new JLabel("Start Date: ");
		lblStartDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblStartDate.setBounds(10, 175, 202, 23);
		panel.add(lblStartDate);

		JLabel lblEndDate = new JLabel("End Date:");
		lblEndDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEndDate.setBounds(10, 209, 155, 23);
		panel.add(lblEndDate);

		JLabel lblBriefDescriptionOf = new JLabel("Brief Description of the Course:");
		lblBriefDescriptionOf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBriefDescriptionOf.setBounds(10, 246, 202, 23);
		panel.add(lblBriefDescriptionOf);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(231, 46, 268, 289);
		viewCourseDetails.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		TextField courseId = new TextField();
		courseId.setEditable(false);
		courseId.setBounds(10, 10, 248, 22);
		try
		{
			courseId.setText(course.courseDetails(courseName).get(0));
		}catch(Exception ex){
			ex.printStackTrace();
		}

		panel_1.add(courseId);

		TextField courseName1 = new TextField();
		courseName1.setEditable(false);
		courseName1.setBounds(10, 38, 248, 22);
		try
		{
			courseName1.setText(course.courseDetails(courseName).get(1));
		}catch(Exception ex){
			ex.printStackTrace();
		}
		panel_1.add(courseName1);

		TextField noStudentsReg = new TextField();
		noStudentsReg.setEditable(false);
		noStudentsReg.setBounds(10, 73, 248, 22);
		try
		{
			noStudentsReg.setText(course.courseDetails(courseName).get(2));
		}catch(Exception ex){
			ex.printStackTrace();
		}

		panel_1.add(noStudentsReg);

		TextField maxStudents = new TextField();
		maxStudents.setEditable(false);
		maxStudents.setBounds(10, 101, 248, 22);
		try
		{
			maxStudents.setText(course.courseDetails(courseName).get(3));
		}catch(Exception ex){
			ex.printStackTrace();
		}

		panel_1.add(maxStudents);

		TextField canRegister = new TextField();
		canRegister.setEditable(false);
		canRegister.setBounds(10, 139, 248, 22);
		try
		{
			canRegister.setText(course.courseDetails(courseName).get(4));
		}catch(Exception ex){
			ex.printStackTrace();
		}

		panel_1.add(canRegister);

		TextField startDate = new TextField();
		startDate.setEditable(false);
		startDate.setBounds(10, 167, 248, 22);
		try
		{
			startDate.setText(course.courseDetails(courseName).get(5));
		}catch(Exception ex){
			ex.printStackTrace();
		}

		panel_1.add(startDate);

		TextField endDate = new TextField();
		endDate.setEditable(false);
		endDate.setBounds(10, 202, 248, 22);
		try
		{
			endDate.setText(course.courseDetails(courseName).get(6));
		}catch(Exception ex){
			ex.printStackTrace();
		}

		panel_1.add(endDate);
		
		JTextArea description = new JTextArea();
		description.setWrapStyleWord(true);
		description.setLineWrap(true);
		description.setBounds(10, 241, 248, 48);
		panel_1.add(description);
		description.setEditable(false);
		try
		{
			description.setText(course.courseDetails(courseName).get(7));
		}catch(Exception ex){
			ex.printStackTrace();
		}

		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				viewCourseDetails.dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(164, 345, 130, 39);
		viewCourseDetails.getContentPane().add(btnNewButton);

		JLabel lblCourseDetails = new JLabel("Course Details");
		lblCourseDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblCourseDetails.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCourseDetails.setBounds(10, 11, 488, 33);
		viewCourseDetails.getContentPane().add(lblCourseDetails);
	}
}
