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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import controllers.PacienteController;
import model.Paciente;
import javax.swing.JFormattedTextField;

public class VistaNuevoPaciente extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static Paciente paciente;
	private JTextField textField_dni;
	private JTextField textField_nombre;
	private JTextField textField_direccion;
	private JComboBox comboBox_sexo;
	private Integer idPacienteModificado;
	private JTextField textField_edad;
	private JTextField textField_email;
	JFormattedTextField textFechaNacim;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaNuevoPaciente frame = new VistaNuevoPaciente();
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
	public VistaNuevoPaciente() {
		setTitle("Pacientes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 673, 573);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNuevoPaciente = new JLabel("Informacion del paciente");
		lblNuevoPaciente.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNuevoPaciente.setBounds(52, 13, 203, 25);
		contentPane.add(lblNuevoPaciente);

		textField_dni = new JTextField();
		textField_dni.setBounds(177, 90, 116, 22);
		contentPane.add(textField_dni);
		textField_dni.setColumns(10);

		textField_nombre = new JTextField();
		textField_nombre.setBounds(177, 131, 116, 22);
		contentPane.add(textField_nombre);
		textField_nombre.setColumns(10);

		textField_direccion = new JTextField();
		textField_direccion.setBounds(177, 164, 116, 22);
		contentPane.add(textField_direccion);
		textField_direccion.setColumns(10);

		textField_edad = new JTextField();
		textField_edad.setBounds(177, 249, 116, 22);
		contentPane.add(textField_edad);
		textField_edad.setColumns(10);

		textField_email = new JTextField();
		textField_email.setBounds(177, 288, 116, 22);
		contentPane.add(textField_email);
		textField_email.setColumns(10);

		this.comboBox_sexo = new JComboBox();
		comboBox_sexo.setModel(new DefaultComboBoxModel(new String[] { "Hombre", "Mujer", "Otro" }));
		comboBox_sexo.setBounds(177, 211, 116, 22);
		contentPane.add(comboBox_sexo);

		textFechaNacim = new JFormattedTextField();
		textFechaNacim.setBounds(177, 329, 116, 20);
		contentPane.add(textFechaNacim);

		try {
			textFechaNacim.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("##/##/####")));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		JLabel lblDni = new JLabel("DNI");
		lblDni.setBounds(31, 93, 56, 16);
		contentPane.add(lblDni);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(31, 134, 56, 16);
		contentPane.add(lblNombre);

		JLabel lblDireccion = new JLabel("Domicilio");
		lblDireccion.setBounds(31, 167, 56, 16);
		contentPane.add(lblDireccion);

		JLabel lblSexo = new JLabel("Sexo");
		lblSexo.setBounds(31, 214, 56, 16);
		contentPane.add(lblSexo);

		JLabel lblEdad = new JLabel("Edad");
		lblEdad.setBounds(31, 252, 108, 16);
		contentPane.add(lblEdad);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(31, 291, 108, 16);
		contentPane.add(lblEmail);

		JLabel lblFechaNacim = new JLabel("Fecha nacimiento");
		lblFechaNacim.setBounds(31, 331, 108, 16);
		contentPane.add(lblFechaNacim);

		JButton btn_cancelar = new JButton("Cancelar");
		btn_cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				MenuPacientes back = new MenuPacientes();
				back.setVisible(true);
				setVisible(false);

			}
		});
		btn_cancelar.setBounds(546, 488, 97, 25);
		contentPane.add(btn_cancelar);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Paciente nuevoPaciente = new Paciente(-1, Integer.parseInt(textField_dni.getText()),
						textField_nombre.getText(), comboBox_sexo.getSelectedItem().toString(), new Date(textFechaNacim.getText()), textField_direccion.getText(), Integer.valueOf(textField_edad.getText()), textField_email.getText());
				if (idPacienteModificado == null) {
					nuevoPaciente.setIdPaciente(PacienteController.getInstancia().generateIdPaciente());
					PacienteController.getInstancia().altaPaciente(nuevoPaciente);
				} else {
					nuevoPaciente.setIdPaciente(idPacienteModificado);
					PacienteController.getInstancia().modificarPaciente(nuevoPaciente);
				}

				MenuPacientes back = new MenuPacientes();
				back.setVisible(true);
				setVisible(false);

			}
		});

		btnGuardar.setBounds(432, 488, 97, 25);
		contentPane.add(btnGuardar);

	}

	public void editarPaciente(String idPaciente) {
		paciente = PacienteController.getInstancia().getPaciente(Integer.parseInt(idPaciente));
		idPacienteModificado = paciente.getIdPaciente();
		textField_dni.setText(String.valueOf(paciente.getDni()));
		textField_nombre.setText(paciente.getNombre());
		textField_direccion.setText(paciente.getDomicilio());
		
		String pattern = "dd/MM/yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		String fechaPaciente = simpleDateFormat.format(paciente.getFechaNacimiento());
		
		textFechaNacim.setText(fechaPaciente);
		textField_edad.setText(String.valueOf(paciente.getEdad()));
		textField_email.setText(paciente.getEmail());

		for (int i = 0; i < this.comboBox_sexo.getModel().getSize(); i++) {
			if (comboBox_sexo.getItemAt(i).toString().equals(paciente.getSexo())) {
				comboBox_sexo.setSelectedIndex(i);
				break;
			}
		}

	}
}
