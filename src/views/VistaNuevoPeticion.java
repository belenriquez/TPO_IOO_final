package views;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controllers.LaboratorioController;
import controllers.PacienteController;
import controllers.PracticaController;
import model.*;

public class VistaNuevoPeticion extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static Peticion peticion;
	private JTextField textField_obraSocial;
	private JTextField textField_dniPaciente;
	private JComboBox comboBox_sucursal;
	private JList list_practicasPedidas;
	private JTextField textField_Id;
	private Integer idPeticionEditada;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaNuevoPeticion frame = new VistaNuevoPeticion();
					frame.setVisible(true);
					FrameController.setWindowPosition(frame, 0);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VistaNuevoPeticion() {
		setTitle("Peticiones");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 673, 573);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNuevoPeticion = new JLabel("Informacion de la peticion");
		lblNuevoPeticion.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNuevoPeticion.setBounds(52, 13, 203, 25);
		contentPane.add(lblNuevoPeticion);

		textField_obraSocial = new JTextField();
		textField_obraSocial.setBounds(136, 149, 116, 22);
		contentPane.add(textField_obraSocial);
		textField_obraSocial.setColumns(10);

		JLabel lbId = new JLabel("DNI paciente");
		lbId.setBounds(52, 116, 85, 16);
		contentPane.add(lbId);

		JLabel lblDireccion = new JLabel("Obra social");
		lblDireccion.setBounds(52, 152, 85, 16);
		contentPane.add(lblDireccion);

		textField_dniPaciente = new JTextField();
		textField_dniPaciente.setBounds(136, 113, 116, 22);
		contentPane.add(textField_dniPaciente);
		textField_dniPaciente.setColumns(10);

		JLabel lblSucursal = new JLabel("Sucursal");
		lblSucursal.setBounds(52, 191, 56, 16);
		contentPane.add(lblSucursal);

		List<Sucursal> sucursales = LaboratorioController.getInstancia().getSucursales();
		List<String> direccionSucursales = new ArrayList<String>();
		for (Sucursal suc : sucursales)
			direccionSucursales.add(suc.getDireccion());

		this.comboBox_sucursal = new JComboBox();
		comboBox_sucursal.setModel(new DefaultComboBoxModel(direccionSucursales.toArray()));
		comboBox_sucursal.setBounds(136, 188, 119, 22);
		contentPane.add(comboBox_sucursal);

		JButton btn_cancelar = new JButton("Cancelar");
		btn_cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				MenuPeticiones back = new MenuPeticiones();
				back.setVisible(true);
				setVisible(false);

			}
		});
		btn_cancelar.setBounds(546, 488, 97, 25);
		contentPane.add(btn_cancelar);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String idSucursal = "";
				for (Sucursal suc : sucursales) {
					if (suc.getDireccion() == comboBox_sucursal.getSelectedItem()) {
						idSucursal = Integer.toString(suc.getNumeroSucursal());
					}
				}
				List<PracticaPeticion> pedidas = new ArrayList<PracticaPeticion>();

				for (Object ppo : ((DefaultListModel) list_practicasPedidas.getModel()).toArray()) {
					PracticaPeticion pp = (PracticaPeticion) ppo;
					pedidas.add(pp);
				}
				Paciente paciente = PacienteController.getInstancia()
						.getPacienteByDni(Integer.parseInt(textField_dniPaciente.getText()));

				if (paciente == null) {
					JOptionPane.showMessageDialog(new JFrame(), "El paciente no existe en el sistema");
					
//					int seleccion = JOptionPane.showOptionDialog(new JFrame(), "El paciente no existe en el sistema, ¿Desea darlo de alta?", "Pacientes",
//							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[] { "SI", "NO"}, "Si");
//					if(seleccion == 0) {
//						VistaNuevoPaciente.main(null);
//					}
				}else{
				
					Peticion nuevaPeticion = new Peticion(-1, paciente, textField_obraSocial.getText(), idSucursal, pedidas);
					
					if (idPeticionEditada == null) {
						nuevaPeticion.setIdPeticion(LaboratorioController.getInstancia().generateIdPeticion());
						LaboratorioController.getInstancia().altaPeticion(nuevaPeticion);
					} else {
						nuevaPeticion.setIdPeticion(idPeticionEditada);
						LaboratorioController.getInstancia().modificarPeticion(nuevaPeticion);
					}
					MenuPeticiones back = new MenuPeticiones();
					back.setVisible(true);
					setVisible(false);
				}
			}
		});

		btnGuardar.setBounds(432, 488, 97, 25);
		contentPane.add(btnGuardar);

		this.list_practicasPedidas = new JList<PracticaPeticion>();
		this.list_practicasPedidas.setModel(new DefaultListModel<PracticaPeticion>());
		list_practicasPedidas.setBounds(93, 268, 253, 213);
		contentPane.add(list_practicasPedidas);

		JLabel lblPracticas = new JLabel("Practicas");
		lblPracticas.setBounds(63, 239, 56, 16);
		contentPane.add(lblPracticas);

		JLabel lblAgregarPracticas = new JLabel("Agregar practicas");
		lblAgregarPracticas.setBounds(358, 269, 131, 16);
		contentPane.add(lblAgregarPracticas);

		List<Practica> practicas = PracticaController.getInstancia().getPracticas();
		JComboBox comboBox_practicas = new JComboBox(practicas.toArray());
		comboBox_practicas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultListModel model = (DefaultListModel) list_practicasPedidas.getModel();
				model.addElement(new PracticaPeticion((Practica) comboBox_practicas.getSelectedItem(), null));
				list_practicasPedidas.setModel(model);
			}
		});
		comboBox_practicas.setBounds(358, 298, 131, 25);
		contentPane.add(comboBox_practicas);

		JLabel lblId = new JLabel("ID");
		lblId.setBounds(93, 81, 24, 16);
		contentPane.add(lblId);

		textField_Id = new JTextField();
		textField_Id.setEditable(false);
		textField_Id.setBounds(136, 78, 116, 22);
		contentPane.add(textField_Id);
		textField_Id.setColumns(10);

		textField_Id.setText(String.valueOf(LaboratorioController.getInstancia().generateIdPeticion()));

	}

	public void editarPeticion(String cod) {
		idPeticionEditada = Integer.parseInt(cod);
		Peticion peticion = LaboratorioController.getInstancia().getPeticion(Integer.parseInt(cod));
		textField_Id.setText(cod);
		textField_obraSocial.setText(peticion.getObraSocial());
		textField_dniPaciente.setText(String.valueOf(peticion.getPaciente().getDni()));
		comboBox_sucursal.setSelectedItem(peticion.getSucursal().getDireccion());

		List<PracticaPeticion> practicasPedidas = LaboratorioController.getInstancia()
				.getPracticasPedidasByPeticion(Integer.parseInt(cod));
		if (!practicasPedidas.isEmpty()) {
			DefaultListModel model = new DefaultListModel<PracticaPeticion>();
			for (PracticaPeticion pra : practicasPedidas)
				model.addElement(pra);
			this.list_practicasPedidas.setModel(model);
		}

	}
}