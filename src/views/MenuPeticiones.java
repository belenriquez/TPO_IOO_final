package views;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
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
import enums.TipoValor;
import model.Peticion;
import model.PracticaPeticion;
import java.awt.Toolkit;

public class MenuPeticiones extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tablePeticiones;
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
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuPeticiones.class.getResource("/iconos/edition_theproperty_3623.png")));
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

		tablePeticiones = new JTable();
		tablePeticiones.setBounds(68, 174, 529, 271);
		tablePeticiones.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					getResultados();
			}

			private void getResultados() {				
				Peticion peticion = LaboratorioController.getInstancia().getPeticion(Integer.parseInt(tablePeticiones.getValueAt(tablePeticiones.getSelectedRow(), 0).toString()));
				List<PracticaPeticion> practPeticiones = LaboratorioController.getInstancia().getPracticasPedidasByPeticion(peticion.getIdPeticion());
				
				boolean tieneReservados = false;
				
				for(PracticaPeticion pp : practPeticiones) {
					if(!tieneReservados) {
						tieneReservados = pp.getResultado()!= null ? pp.getResultado().getValor().getTipoValor() == TipoValor.RESERVADO : false;
					}
				}
				
				if (tieneReservados) {
					JOptionPane.showMessageDialog(null, "RETIRAR POR SUCURSAL");
				} else {

					VistaResultado dialog = new VistaResultado(new JFrame(), peticion);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				}
			}

		});

		model = new DefaultTableModel(new Object[][] {},
				new String[] { "Id", "DNI paciente", "Obra social", "Fecha ingreso", "Finalizado" }) {
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				} };

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
		} else {
			peticiones = new ArrayList<Peticion>();
		}

		tablePeticiones.setModel(model);
		contentPane.add(tablePeticiones);

		JScrollPane scrollPane = new JScrollPane(tablePeticiones);
		scrollPane.setBounds(50, 156, 557, 319);
		getContentPane().add(scrollPane);

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

				if (tablePeticiones.getSelectionModel().isSelectionEmpty()) {
					JOptionPane.showMessageDialog(new JFrame(), "Seleccion una petición de la lista para borrar",
							"Peticiones", JOptionPane.ERROR_MESSAGE);
				} else {
					LaboratorioController.getInstancia().bajaPeticion(Integer
							.parseInt(tablePeticiones.getValueAt(tablePeticiones.getSelectedRow(), 0).toString()));

				}

			}
		});
		btnEliminar.setBounds(519, 77, 97, 25);
		contentPane.add(btnEliminar);

		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (tablePeticiones.getSelectionModel().isSelectionEmpty()) {
					JOptionPane.showMessageDialog(new JFrame(), "Seleccion una petición de la lista para editar",
							"Peticiones", JOptionPane.ERROR_MESSAGE);
				} else {
					VistaNuevoPeticion nuevaVista = new VistaNuevoPeticion();
					nuevaVista
							.editarPeticion(tablePeticiones.getValueAt(tablePeticiones.getSelectedRow(), 0).toString());
					nuevaVista.setVisible(true);
					setVisible(false);
				}

			}
		});
		btnEditar.setBounds(410, 77, 97, 25);
		contentPane.add(btnEditar);

//		JButton btn_resultados = new JButton("Resultados");
//		btn_resultados.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				Resultados resultados = new Resultados();
//				resultados.cargarResultados(tablePeticiones.getValueAt(tablePeticiones.getSelectedRow(), 0).toString());
//				resultados.setVisible(true);
//				setVisible(false);
//			}
//		});
//		btn_resultados.setBounds(519, 39, 97, 25);
//		contentPane.add(btn_resultados);

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

		for (int i = tablePeticiones.getModel().getRowCount() - 1; i >= 0; i--)
			if (Integer.parseInt(tablePeticiones.getValueAt(i, 1).toString()) != dni) {
				this.model.removeRow(i);
			}

		this.tablePeticiones.setModel(this.model);
		this.tablePeticiones.repaint();
	}

	private void getPeticionesConResultadosCriticos() {
		Peticion pet = null;
		int i = 0;
		for (i = tablePeticiones.getModel().getRowCount() - 1; i >= 0; i--) {
			pet = LaboratorioController.getInstancia()
					.getPeticion(Integer.parseInt(tablePeticiones.getValueAt(i, 0).toString()));
//			if (!pet.tieneResultadosCriticos()){
//				this.model.removeRow(i);
//			}
		}
		this.tablePeticiones.setModel(this.model);
		this.tablePeticiones.repaint();
	}

}
