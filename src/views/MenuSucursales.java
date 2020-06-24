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
import model.Sucursal;


public class MenuSucursales extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField textField_search;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuSucursales frame = new MenuSucursales();
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
	public MenuSucursales() {
		setTitle("Sucursales");
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
					"ID", "Numero", "Direccion","Responsable Tecnico"
				});
		
		List<Sucursal> sucursales = LaboratorioController.getInstancia().getSucursales();
		for (Sucursal s:sucursales){
				List<String> list = new ArrayList<String>();
				list.add(String.valueOf(s.getIdSucursal()));
				list.add(String.valueOf(s.getNumeroSucursal()));
				list.add(s.getDireccion());
				list.add(s.getResponsableTecnico().getNombre());
				model.addRow(list.toArray());	
		}
		

	
		table = new JTable();
		table.setModel(model);
		table.setBounds(68, 174, 529, 271);
		contentPane.add(table);		
				
	    JScrollPane scrollPane = new JScrollPane(table);
	    scrollPane.setBounds(40, 126, 557, 319);
	    getContentPane().add(scrollPane);
		
		textField_search = new JTextField();
		textField_search.setBounds(40, 91, 320, 22);
		contentPane.add(textField_search);
		textField_search.setColumns(10);
		
		JLabel lblDni = new JLabel("Numero de sucursal:");
		lblDni.setBounds(40, 62, 205, 16);
		contentPane.add(lblDni);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VistaNuevoSucursal.main(null);
				setVisible(false);
				  
			}
		});
		btnAgregar.setBounds(410, 39, 97, 25);
		contentPane.add(btnAgregar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if(table.getSelectionModel().isSelectionEmpty()){
				    JOptionPane.showMessageDialog(new JFrame(), "Seleccione una sucursal de la lista para borrar", "Sucursales", JOptionPane.ERROR_MESSAGE);	
				} else {
					if (LaboratorioController.getInstancia().bajaSucursal(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString()))){
						setVisible(false);
						main(null);
					} else {
						JOptionPane.showMessageDialog(new JFrame(), "No se puede eliminar la sucursal\n porque tiene peticiones", "Sucursales", JOptionPane.ERROR_MESSAGE);						
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
					JOptionPane.showMessageDialog(new JFrame(), "Seleccione una sucursal de la lista para editar", "Sucursales",JOptionPane.ERROR_MESSAGE);				
				} else {				
					VistaNuevoSucursal nuevaVista = new VistaNuevoSucursal();
					nuevaVista.editarSucursal(table.getValueAt(table.getSelectedRow(), 0).toString());
					nuevaVista.setVisible(true);
					setVisible(false);
				}
				
			}
		});
		btnEditar.setBounds(410, 77, 97, 25);
		contentPane.add(btnEditar);
		
		
		
	}
}
