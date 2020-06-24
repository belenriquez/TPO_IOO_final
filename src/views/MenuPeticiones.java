package views;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controllers.LaboratorioController;
import controllers.PracticaController;
import model.Peticion;

public class MenuPeticiones extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField textField_search;
	private DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPeticiones frame = new MenuPeticiones();
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
	public MenuPeticiones() {
		setTitle("Peticiones");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 673, 573);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btn_cancelar = new JButton("Cancelar");
		btn_cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				MenuPrincipal back = new MenuPrincipal();
				back.setVisible(true);
				setVisible(false);

			}
		});
		btn_cancelar.setBounds(546, 488, 97, 25);
		contentPane.add(btn_cancelar);

		table = new JTable();
		table.setBounds(68, 174, 529, 271);

		model = new DefaultTableModel(new Object[][] {},
				new String[] { "Id", "DNI paciente", "Obra social", "Fecha ingreso", "Finalizado" });

		List<Peticion> peticiones = LaboratorioController.getInstancia().getPeticiones();

		if (peticiones != null) {
			for (Peticion pet : peticiones) {
				List<String> list = new ArrayList<String>();
				list.add(String.valueOf(pet.getIdPeticion()));
				list.add(String.valueOf(pet.getPaciente().getDni()));
				list.add(pet.getObraSocial());
				list.add(pet.getFechaCarga().toString());
				list.add(pet.getEstado().name());
				model.addRow(list.toArray());
			}
		}else {
			peticiones = new ArrayList<Peticion>();
		}

		table.setModel(model);
		contentPane.add(table);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(50, 156, 557, 319);
		getContentPane().add(scrollPane);

		textField_search = new JTextField();
		textField_search.setBounds(40, 91, 320, 22);
		contentPane.add(textField_search);
		textField_search.setColumns(10);

		JLabel lblDni = new JLabel("Numero de peticion:");
		lblDni.setBounds(40, 62, 205, 16);
		contentPane.add(lblDni);

		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VistaNuevoPeticion.main(null);
				setVisible(false);

			}
		});
		btnAgregar.setBounds(410, 39, 97, 25);
		contentPane.add(btnAgregar);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (table.getSelectionModel().isSelectionEmpty()) {
					JOptionPane.showMessageDialog(new JFrame(), "Seleccion un paciente de la lista para borrar",
							"Peticiones", JOptionPane.ERROR_MESSAGE);
				} else {
					LaboratorioController.getInstancia()
							.bajaPeticion(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString()));

				}

			}
		});
		btnEliminar.setBounds(519, 77, 97, 25);
		contentPane.add(btnEliminar);

		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (table.getSelectionModel().isSelectionEmpty()) {
					JOptionPane.showMessageDialog(new JFrame(), "Seleccion un paciente de la lista para editar",
							"Peticiones", JOptionPane.ERROR_MESSAGE);
				} else {
					VistaNuevoPeticion nuevaVista = new VistaNuevoPeticion();
					nuevaVista.editarPeticion(table.getValueAt(table.getSelectedRow(), 0).toString());
					nuevaVista.setVisible(true);
					setVisible(false);
				}

			}
		});
		btnEditar.setBounds(410, 77, 97, 25);
		contentPane.add(btnEditar);

		JButton btn_resultados = new JButton("Resultados");
		btn_resultados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Resultados resultados = new Resultados();
				resultados.cargarResultados(table.getValueAt(table.getSelectedRow(), 0).toString());
				resultados.setVisible(true);
				setVisible(false);
			}
		});
		btn_resultados.setBounds(519, 39, 97, 25);
		contentPane.add(btn_resultados);

		JButton btn_filtrarCriticas = new JButton("Filtrar por resultados criticos");
		btn_filtrarCriticas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getPeticionesConResultadosCriticos();
			}

		});
		btn_filtrarCriticas.setBounds(410, 115, 206, 25);
		contentPane.add(btn_filtrarCriticas);

	}

	public void getPeticionesPaciente(int dni) {

		for (int i = table.getModel().getRowCount() - 1; i >= 0; i--)
			if (Integer.parseInt(table.getValueAt(i, 1).toString()) != dni) {
				this.model.removeRow(i);
			}

		this.table.setModel(this.model);
		this.table.repaint();
	}

	private void getPeticionesConResultadosCriticos() {
		Peticion pet = null;
		int i = 0;
		for (i = table.getModel().getRowCount() - 1; i >= 0; i--) {
			pet = LaboratorioController.getInstancia().getPeticion(Integer.parseInt(table.getValueAt(i, 0).toString()));
//			if (!pet.tieneResultadosCriticos()){
//				this.model.removeRow(i);
//			}
		}
		this.table.setModel(this.model);
		this.table.repaint();
	}

}
