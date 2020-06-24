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

import controllers.UsuarioController;
import model.Usuario;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Toolkit;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dimension;

import javax.swing.ImageIcon;

public class MenuUsuarios extends JFrame {

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
					MenuUsuarios frame = new MenuUsuarios();
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
	public MenuUsuarios() {
		setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuUsuarios.class.getResource("/iconos/group_theapplication_users_2907.png")));
		setTitle("Usuarios");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 673, 573);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		DefaultTableModel model = new DefaultTableModel(new Object[][] {
				},
				new String[] {
					"ID", "Nombre usuario", "Nombre", "Email", "Rol"
				});
		
		List<Usuario> usuarios = UsuarioController.getInstancia().getUsuarios();
		for (Usuario u:usuarios){
				List<String> list = new ArrayList<String>();
				list.add(String.valueOf(u.getIdUsuario()));
				list.add(u.getNombreUsuario());
				list.add(u.getNombre());
				list.add(u.getEmail());
				list.add(String.valueOf(u.getRol()));
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
		panel.setBounds(248, 23, 349, 103);
		contentPane.add(panel);
		
		
		JButton btnAgregar = new JButton("Agregar");
		
		JButton btnEliminar = new JButton("Eliminar");
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(table.getSelectionModel().isSelectionEmpty()){
					JOptionPane.showMessageDialog(new JFrame(), "Seleccione un usuario de la lista para editar", "Usuarios",JOptionPane.ERROR_MESSAGE);				
				} else {				
					VistaNuevoUsuario nuevaVista = new VistaNuevoUsuario();
					nuevaVista.editarUsuario(table.getValueAt(table.getSelectedRow(), 0).toString());
					nuevaVista.setVisible(true);
					setVisible(false);
				}
				
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
					.addGap(268)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnEliminar, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
						.addComponent(btnEditar, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
						.addComponent(btnAgregar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnEditar)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAgregar)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnEliminar)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 488, 657, 46);
		contentPane.add(panel_1);
		
		JButton btn_cancelar = new JButton("Cancelar");
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap(582, Short.MAX_VALUE)
					.addComponent(btn_cancelar)
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap(284, Short.MAX_VALUE)
					.addComponent(btn_cancelar)
					.addContainerGap())
		);
		panel_1.setLayout(gl_panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(40, 0, 198, 126);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		btn_cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MenuPrincipal back = new MenuPrincipal();
				back.setVisible(true);
				setVisible(false);
				
			}
		});
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if(table.getSelectionModel().isSelectionEmpty()){
				    JOptionPane.showMessageDialog(new JFrame(), "Seleccione un usuario de la lista para eliminar", "Usuarios", JOptionPane.ERROR_MESSAGE);	
				} else {

					UsuarioController.getInstancia().bajaUsuario(Integer.valueOf(table.getValueAt(table.getSelectedRow(), 0).toString()));
					setVisible(false);
					main(null);
					
				}
				
				
			}
		});
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VistaNuevoUsuario.main(null);
				setVisible(false);
				  
			}
		});
		
		
		
	}
}
