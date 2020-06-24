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

import controllers.PacienteController;
import model.Paciente;
import java.awt.Toolkit;


public class MenuPacientes extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPacientes frame = new MenuPacientes();
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
	public MenuPacientes() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuPacientes.class.getResource("/iconos/edition_theuser_theapplication_2909.png")));
		setTitle("Pacientes");
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
		
		DefaultTableModel model = new DefaultTableModel(new Object[][] {
				},
				new String[] {
					"ID", "DNI", "Nombre", "Sexo", "Fecha de nacimiento", "Domicilio"
				});
		
		List<Paciente> pacientes = PacienteController.getInstancia().getPacientes();
		for (Paciente p:pacientes){
				List<String> list = new ArrayList<String>();
				list.add(String.valueOf(p.getIdPaciente()));
				list.add(String.valueOf(p.getDni()));
				list.add(p.getNombre());
				list.add(p.getSexo());
//				list.add(p.getFechaNacimientoFormateada());
				list.add(String.valueOf(p.getFechaNacimiento()));
				list.add(p.getDomicilio());
				model.addRow(list.toArray());
		}
		

	
		table = new JTable();
		table.setModel(model);
		table.setBounds(68, 174, 529, 271);
		contentPane.add(table);		
				
	    JScrollPane scrollPane = new JScrollPane(table);
	    scrollPane.setBounds(40, 126, 557, 319);
	    getContentPane().add(scrollPane);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VistaNuevoPaciente.main(null);
				setVisible(false);
				  
			}
		});
		btnAgregar.setBounds(410, 39, 97, 25);
		contentPane.add(btnAgregar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if(table.getSelectionModel().isSelectionEmpty()){
				    JOptionPane.showMessageDialog(new JFrame(), "Seleccion un paciente de la lista para borrar", "Pacientes", JOptionPane.ERROR_MESSAGE);	
				} else {
					if (PacienteController.getInstancia().bajaPaciente(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString()))){
						setVisible(false);
						main(null);
					} else {
						JOptionPane.showMessageDialog(new JFrame(), "No se puede borrar el paciente seleccionado\nporque tiene peticiones", "Pacientes", JOptionPane.ERROR_MESSAGE);
					}
					
				}
				
				
			}
		});
		btnEliminar.setBounds(519, 77, 97, 25);
		contentPane.add(btnEliminar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(table.getSelectionModel().isSelectionEmpty()){
					JOptionPane.showMessageDialog(new JFrame(), "Seleccione un paciente de la lista para editar", "Pacientes",JOptionPane.ERROR_MESSAGE);
				} else {				
					VistaNuevoPaciente nuevaVista = new VistaNuevoPaciente();
					nuevaVista.editarPaciente(table.getValueAt(table.getSelectedRow(), 0).toString());
					nuevaVista.setVisible(true);
					setVisible(false);
				}
				
			}
		});
		btnEditar.setBounds(410, 77, 97, 25);
		contentPane.add(btnEditar);
		
		JButton btn_peticiones = new JButton("Peticiones");
		btn_peticiones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(table.getSelectionModel().isSelectionEmpty()){
					JOptionPane.showMessageDialog(new JFrame(),
							"Seleccione un paciente de la lista para ver sus peticiones",
							"Pacientes",JOptionPane.ERROR_MESSAGE);				
				} else {
					MenuPeticiones nuevaVista = new MenuPeticiones();
					nuevaVista.getPeticionesPaciente(Integer.parseInt(
							table.getValueAt(table.getSelectedRow(), 0).toString()) );
					nuevaVista.setVisible(true);
					setVisible(false);
				}
			}
		});
		
		btn_peticiones.setBounds(519, 39, 97, 25);
		contentPane.add(btn_peticiones);
		
	}
}
