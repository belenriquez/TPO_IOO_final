package views;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import controllers.UsuarioController;
import enums.Rol;
import model.Usuario;

public class VistaNuevoUsuario extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static Usuario usuario;
	private JTextField textField_userName;
	private JTextField textField_email;
	private JTextField textField_nombre;
	private JComboBox<Rol> comboBox_role;
	private JPasswordField passwordField;
	private JTextField textField_domicilio;
	private JTextField textField_dni;
	private JFormattedTextField textField_fechaNacimiento;
	private Integer idUsuarioEditado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaNuevoUsuario frame = new VistaNuevoUsuario();
					frame.setVisible(true);
					FrameController.setWindowPosition(frame, 0);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VistaNuevoUsuario() {
		setTitle("Usuarios");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 673, 573);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNuevoUsuario = new JLabel("Informacion del usuario");
		lblNuevoUsuario.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNuevoUsuario.setBounds(52, 13, 203, 25);
		contentPane.add(lblNuevoUsuario);

		textField_userName = new JTextField();
		textField_userName.setBounds(194, 90, 116, 22);
		contentPane.add(textField_userName);
		textField_userName.setColumns(10);

		textField_email = new JTextField();
		textField_email.setBounds(194, 174, 116, 22);
		contentPane.add(textField_email);
		textField_email.setColumns(10);

		textField_nombre = new JTextField();
		textField_nombre.setBounds(194, 211, 116, 22);
		contentPane.add(textField_nombre);
		textField_nombre.setColumns(10);

		JLabel lblUserName = new JLabel("Nombre de usuario");
		lblUserName.setBounds(52, 93, 97, 16);
		contentPane.add(lblUserName);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(52, 177, 56, 16);
		contentPane.add(lblEmail);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(52, 134, 56, 16);
		contentPane.add(lblPassword);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(52, 214, 56, 16);
		contentPane.add(lblNombre);

		JLabel lblRol = new JLabel("Role");
		lblRol.setBounds(52, 256, 56, 16);
		contentPane.add(lblRol);

		this.comboBox_role = new JComboBox<Rol>();
//		comboBox_role.setModel(new DefaultComboBoxModel(new String[] {"Admin", "Laboratorista", "Recepcionista"}));
		comboBox_role.setModel(new DefaultComboBoxModel<Rol>(Rol.values()));
		comboBox_role.setBounds(194, 253, 119, 22);
		contentPane.add(comboBox_role);

		JButton btn_cancelar = new JButton("Cancelar");
		btn_cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				MenuUsuarios back = new MenuUsuarios();
				back.setVisible(true);
				setVisible(false);

			}
		});
		btn_cancelar.setBounds(546, 488, 97, 25);
		contentPane.add(btn_cancelar);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

//				DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.US);
//				Date date = null;
//				try {
//					date = (Date) formatter.parse(textField_fechaNacimiento.getText());
//				} catch (ParseException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
				
				Date date = new Date(textField_fechaNacimiento.getText());
				Usuario nuevoUsuario = new Usuario(-1, textField_userName.getText(), textField_email.getText(),
						new String(passwordField.getPassword()), textField_nombre.getText(),
						(Rol) comboBox_role.getSelectedItem(), Integer.valueOf(textField_dni.getText()),
						textField_domicilio.getText(), date);
				if (idUsuarioEditado!=null) {
					nuevoUsuario.setIdUsuario(idUsuarioEditado);
					UsuarioController.getInstancia().modificarUsuario(nuevoUsuario);
				} else {
					nuevoUsuario.setIdUsuario(UsuarioController.getInstancia().generateIdUsuario());
					UsuarioController.getInstancia().altaUsuario(nuevoUsuario);
				}
				MenuUsuarios back = new MenuUsuarios();
				back.setVisible(true);
				setVisible(false);

			}

		});

		btnGuardar.setBounds(432, 488, 97, 25);
		contentPane.add(btnGuardar);

		passwordField = new JPasswordField();
		passwordField.setBounds(194, 131, 116, 22);
		contentPane.add(passwordField);

		JLabel lblDomicilio = new JLabel("Domicilio");
		lblDomicilio.setBounds(52, 299, 46, 14);
		contentPane.add(lblDomicilio);

		textField_domicilio = new JTextField();
		textField_domicilio.setBounds(194, 296, 119, 20);
		contentPane.add(textField_domicilio);
		textField_domicilio.setColumns(10);

		JLabel lblDni = new JLabel("DNI");
		lblDni.setBounds(52, 339, 46, 14);
		contentPane.add(lblDni);

		textField_dni = new JTextField();
		textField_dni.setBounds(194, 336, 119, 20);
		contentPane.add(textField_dni);
		textField_dni.setColumns(10);

		textField_fechaNacimiento = new JFormattedTextField();
		textField_fechaNacimiento.setBounds(194, 378, 119, 20);
		contentPane.add(textField_fechaNacimiento);
		textField_fechaNacimiento.setColumns(10);
		
		

		

		JLabel lblFechaNacimiento = new JLabel("Fecha de nacimiento");
		lblFechaNacimiento.setBounds(52, 381, 116, 14);
		contentPane.add(lblFechaNacimiento);

	}

	public void editarUsuario(String idUser) {
		int idUsuario = Integer.valueOf(idUser);
		
		usuario = UsuarioController.getInstancia().getUsuario(idUsuario);
		idUsuarioEditado = usuario.getIdUsuario();
		
		textField_userName.setText(usuario.getNombreUsuario());
		textField_email.setText(usuario.getEmail());
		passwordField.setText(usuario.getPassword());
		textField_nombre.setText(usuario.getNombre());
		textField_dni.setText(String.valueOf(usuario.getDni()));
		textField_domicilio.setText(usuario.getDomicilio());
		
		String pattern = "dd/MM/yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		String date = simpleDateFormat.format(usuario.getFechaNacimiento());
		
		textField_fechaNacimiento.setText(date);

		for (int i = 0; i < this.comboBox_role.getModel().getSize(); i++) {
			if (comboBox_role.getItemAt(i).toString().equals(usuario.getRol())) {
				comboBox_role.setSelectedIndex(i);
				break;
			}
		}

	}
}
