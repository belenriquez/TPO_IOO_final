package views;

import controllers.UsuarioController;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class MenuPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal frame = new MenuPrincipal();
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
	public MenuPrincipal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuPrincipal.class.getResource("/iconos/test-lab-tubes_icon-icons.com_56328.png")));
		setTitle("Menu laboratorio");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 505, 296);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox<String> comboBox_opciones = new JComboBox<String>();
		comboBox_opciones.setToolTipText("");
		switch (UsuarioController.getInstancia().getUsuarioActivo().getRol()) {
			case ADMINISTRADOR:
				comboBox_opciones.setModel(new DefaultComboBoxModel<String>(new String[] {"Usuarios", "Sucursales", "Pacientes", "Practicas","Peticiones"}));
				break;
			case RECEPCION:
				comboBox_opciones.setModel(new DefaultComboBoxModel<String>(new String[] {"Pacientes","Peticiones"}));
				break;
			case LABORATORISTA:
				comboBox_opciones.setModel(new DefaultComboBoxModel<String>(new String[] {"Peticiones"}));
				break;
		}
		
		comboBox_opciones.setBounds(39, 178, 342, 22);
		contentPane.add(comboBox_opciones);
		
		JButton btn_ok = new JButton("Aceptar");
		btn_ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				switch(comboBox_opciones.getSelectedItem().toString()) {
				  case "Pacientes":
					  MenuPacientes.main(null);
					  setVisible(false);
				    break;
				  case "Sucursales":
					  MenuSucursales.main(null);
					  setVisible(false);
				    break;
				  case "Practicas":
					  MenuPracticas.main(null);
					  setVisible(false);
					    break;
				  case "Usuarios":
					  MenuUsuarios.main(null);
					  setVisible(false);
					    break;
				  case "Peticiones":
					  MenuPeticiones.main(null);
					  setVisible(false);
					    break;

				}
				
				
				
			}
		});
		btn_ok.setBounds(363, 211, 97, 25);
		contentPane.add(btn_ok);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/iconos/labscientist_101049.png")));
		lblNewLabel.setBounds(0, 0, 489, 257);
		contentPane.add(lblNewLabel);
		
		
		
		
	}
}
