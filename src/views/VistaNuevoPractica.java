package views;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controllers.PracticaController;
import enums.EstadoPractica;
import enums.Rol;
import model.*;

public class VistaNuevoPractica extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static Practica practica;
	private JTextField textField_codigo;
	private JTextField textField_nombre;
	private JTextField textField_hs;
	private JTextField textField_criticoDesc;
	private JTextField textField_reservadoDesc;
	private JTextField textField_criticoNumMin;
	private JTextField textField_criticoNumMax;
	private JTextField textField_reservadoNumMin;
	private JTextField textField_reservadoNumMax;
	private JLabel lblMax;
	private JLabel lblMin;
	private JLabel labelMax;
	private JLabel labelMin;
	private Integer idPracticaEditada;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaNuevoPractica frame = new VistaNuevoPractica();
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
	public VistaNuevoPractica() {
		setTitle("Practicas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 673, 573);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNuevoPractica = new JLabel("Informacion del practica");
		lblNuevoPractica.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNuevoPractica.setBounds(52, 13, 203, 25);
		contentPane.add(lblNuevoPractica);

		textField_codigo = new JTextField();

		textField_nombre = new JTextField();
		textField_nombre.setBounds(136, 131, 116, 22);
		contentPane.add(textField_nombre);
		textField_nombre.setColumns(10);

		textField_hs = new JTextField();
		textField_hs.setBounds(136, 177, 116, 22);
		contentPane.add(textField_hs);
		textField_hs.setColumns(10);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(52, 134, 56, 16);
		contentPane.add(lblNombre);

		JLabel lblCantHoras = new JLabel("Horas");
		lblCantHoras.setBounds(52, 180, 56, 16);
		contentPane.add(lblCantHoras);

		JButton btn_cancelar = new JButton("Cancelar");
		btn_cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				MenuPracticas back = new MenuPracticas();
				back.setVisible(true);
				setVisible(false);

			}
		});
		btn_cancelar.setBounds(546, 488, 97, 25);
		contentPane.add(btn_cancelar);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Valor critico = null;
				Valor reservado = null;
				// TODO: dejar andando
//				if (comboBox_criterio.getSelectedItem() == "Descriptivo") {
//					critico = new ValorTexto(Arrays.asList(textField_criticoDesc.getText().split(",")));
//					reservado = new ValorTexto(Arrays.asList(textField_reservadoDesc.getText().split(",")));
//				} else {
//					critico = new ValorExacto(Integer.parseInt(textField_criticoNumMin.getText()),Integer.parseInt(textField_criticoNumMax.getText()));
//					reservado = new ValorPorRango(Integer.parseInt(textField_reservadoNumMin.getText()),Integer.parseInt(textField_reservadoNumMax.getText()));
//				}

				Practica nuevaPractica = new Practica(-1, textField_nombre.getText(), EstadoPractica.HABILITADA,
						Integer.valueOf(textField_hs.getText()), false);

				if (idPracticaEditada != null) {
					nuevaPractica.setCodigoPractica(idPracticaEditada);
					PracticaController.getInstancia().modificarPractica(nuevaPractica);
				} else {
					nuevaPractica.setCodigoPractica(PracticaController.getInstancia().generateIdPractica());
					PracticaController.getInstancia().altaPractica(nuevaPractica);
				}
				MenuPracticas back = new MenuPracticas();
				back.setVisible(true);
				setVisible(false);

			}
		});

		btnGuardar.setBounds(432, 488, 97, 25);
		contentPane.add(btnGuardar);

//		JLabel lblCriterio = new JLabel("Criterio");
//		lblCriterio.setBounds(52, 214, 56, 16);
//		contentPane.add(lblCriterio);
//		
//		this.comboBox_criterio = new JComboBox();
//		comboBox_criterio.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				if (comboBox_criterio.getSelectedItem() == "Descriptivo") {
//					showDescriptivo();
//				}else { showNumerico(); }
//			}
//		});

//		comboBox_criterio.setModel(new DefaultComboBoxModel(new String[] {"Cuantitativo", "Descriptivo"}));
//		comboBox_criterio.setBounds(136, 211, 116, 22);
//		contentPane.add(comboBox_criterio);

//		JLabel lblValoresCriticos = new JLabel("Valores aceptados como no criticos:");
//		lblValoresCriticos.setBounds(22, 264, 248, 16);
//		contentPane.add(lblValoresCriticos);
//		
//		JLabel lblValoresReservados = new JLabel("Valores aceptados como no reservados:");
//		lblValoresReservados.setBounds(22, 340, 260, 16);
//		contentPane.add(lblValoresReservados);

//		textField_criticoDesc = new JTextField();
//		textField_criticoDesc.setBounds(32, 293, 291, 22);
//		contentPane.add(textField_criticoDesc);
//		textField_criticoDesc.setColumns(10);
//		
//		textField_reservadoDesc = new JTextField();
//		textField_reservadoDesc.setColumns(10);
//		textField_reservadoDesc.setBounds(32, 369, 291, 22);
//		contentPane.add(textField_reservadoDesc);
//			
//		
//		textField_criticoNumMin = new JTextField();
//		textField_criticoNumMin.setBounds(102, 293, 56, 22);
//		contentPane.add(textField_criticoNumMin);
//		textField_criticoNumMin.setColumns(10);
//		
//		this.lblMin = new JLabel("Min");
//		lblMin.setBounds(32, 296, 56, 16);
//		contentPane.add(lblMin);
//		
//		textField_criticoNumMax = new JTextField();
//		textField_criticoNumMax.setBounds(265, 293, 56, 22);
//		contentPane.add(textField_criticoNumMax);
//		textField_criticoNumMax.setColumns(10);
//		
//		this.lblMax = new JLabel("max");
//		lblMax.setBounds(197, 296, 56, 16);
//		contentPane.add(lblMax);
//		
//		this.labelMin = new JLabel("Min");
//		labelMin.setBounds(32, 372, 56, 16);
//		contentPane.add(labelMin);
//		
//		textField_reservadoNumMin = new JTextField();
//		textField_reservadoNumMin.setColumns(10);
//		textField_reservadoNumMin.setBounds(102, 369, 56, 22);
//		contentPane.add(textField_reservadoNumMin);
//		
//		this.labelMax = new JLabel("max");
//		labelMax.setBounds(197, 372, 56, 16);
//		contentPane.add(labelMax);
//		
//		textField_reservadoNumMax = new JTextField();
//		textField_reservadoNumMax.setColumns(10);
//		textField_reservadoNumMax.setBounds(265, 369, 56, 22);
//		contentPane.add(textField_reservadoNumMax);
//		
//		
//		textField_criticoNumMin.setVisible(false);
//		textField_criticoNumMax.setVisible(false);
//		textField_reservadoNumMin.setVisible(false);
//		textField_reservadoNumMax.setVisible(false);
//		lblMin.setVisible(false);
//		lblMax.setVisible(false);
//		labelMin.setVisible(false);
//		labelMax.setVisible(false);
//		textField_criticoDesc.setVisible(false);
//		textField_reservadoDesc.setVisible(false);

	}

	public void editarPractica(String codigo) {
		practica = PracticaController.getInstancia().getPractica(Integer.parseInt(codigo));
		idPracticaEditada = practica.getCodigoPractica();
		textField_nombre.setText(practica.getNombrePractica());
		textField_hs.setText(String.valueOf(practica.getCantidadHorasEspera()));
	}

	private void showDescriptivo() {

		textField_criticoNumMin.setVisible(false);
		textField_criticoNumMax.setVisible(false);
		textField_reservadoNumMin.setVisible(false);
		textField_reservadoNumMax.setVisible(false);
		lblMin.setVisible(false);
		lblMax.setVisible(false);
		labelMin.setVisible(false);
		labelMax.setVisible(false);

		textField_criticoDesc.setVisible(true);
		textField_reservadoDesc.setVisible(true);

	}

	private void showNumerico() {

		textField_criticoNumMin.setVisible(!false);
		textField_criticoNumMax.setVisible(!false);
		textField_reservadoNumMin.setVisible(!false);
		textField_reservadoNumMax.setVisible(!false);
		lblMin.setVisible(!false);
		lblMax.setVisible(!false);
		labelMin.setVisible(!false);
		labelMax.setVisible(!false);

		textField_criticoDesc.setVisible(!true);
		textField_reservadoDesc.setVisible(!true);

	}
}
