package views;

import controllers.UsuarioController;

import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import controllers.UsuarioController;
import javax.swing.JPasswordField;
import java.awt.Toolkit;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

public class Inicio extends JFrame {

	private JFrame frmLogin;
	private JTextField textField_UserName;
	private JLabel lblPassword;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio window = new Inicio();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Inicio() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setIconImage(Toolkit.getDefaultToolkit().getImage(Inicio.class.getResource("/iconos/laboratory_chemistry_science_microscope_icon-icons.com_66115.png")));

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 450, 300);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);

		textField_UserName = new JTextField();
		textField_UserName.setBounds(165, 52, 116, 22);
		frmLogin.getContentPane().add(textField_UserName);
		textField_UserName.setColumns(10);

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(60, 55, 93, 19);
		frmLogin.getContentPane().add(lblUsername);

		lblPassword = new JLabel("Password:");
		lblPassword.setBounds(60, 93, 93, 16);
		frmLogin.getContentPane().add(lblPassword);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String userName = textField_UserName.getText();
				String password = new String(passwordField.getPassword());

				if (UsuarioController.getInstancia().login(userName, password)) {
					setVisible(false);
					MenuPrincipal.main(null);
				} else {
					JOptionPane.showMessageDialog(new JFrame(), "Los datos ingresados son incorrectos", "Login",
							JOptionPane.ERROR_MESSAGE);
				}

			}

		});
		btnLogin.setBounds(165, 185, 116, 25);
		frmLogin.getContentPane().add(btnLogin);

		passwordField = new JPasswordField();
		passwordField.setBounds(165, 90, 116, 22);
		frmLogin.getContentPane().add(passwordField);
		
		FrameController.setWindowPosition(frmLogin, 0);
	}

}
