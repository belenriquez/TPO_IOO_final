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
import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;


public class MenuSucursales extends JFrame {

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
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuSucursales.class.getResource("/iconos/virus_drugs_document_medical_investigation_vaccine_icon_140349.png")));
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
		
		JPanel panel = new JPanel();
		panel.setBounds(473, 11, 123, 112);
		contentPane.add(panel);
		
		JButton btnAgregar = new JButton("Agregar");
		
		JButton btnEditar = new JButton("Editar");
		
		JButton btnEliminar = new JButton("Eliminar");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(31)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnAgregar)
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(btnEditar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnEliminar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addGap(21))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(14)
					.addComponent(btnAgregar)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnEditar)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnEliminar)
					.addGap(7))
		);
		panel.setLayout(gl_panel);
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
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VistaNuevoSucursal.main(null);
				setVisible(false);
				  
			}
		});
		
		
		
	}
}
