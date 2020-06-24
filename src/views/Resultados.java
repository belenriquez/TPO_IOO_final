package views;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controllers.LaboratorioController;
import controllers.PracticaController;
import controllers.UsuarioController;
import enums.Rol;
import model.PracticaPeticion;

public class Resultados extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultTableModel model;
	private JTable table;
	private String idPeticion;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Resultados frame = new Resultados();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Resultados() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 310);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

//		List<PracticaPedida> listaPrueba = new ArrayList<PracticaPedida>();
//		listaPrueba.add(new PracticaPedida((new Practica("A00001","Glucosa en sangre",4,(new CriterioNumerico(100,1000)),(new CriterioNumerico(0,100)))),""));
				
		table = new JTable();
		table.setEditingColumn(1);
		table.setBounds(31, 31, 369, 176);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(32,32,370,176);
		contentPane.add(scrollPane);
		
		
		
		JButton btn_aceptar = new JButton("Aceptar");
		btn_aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { //TODO ver que chota hace
				List<PracticaPeticion> practicasPedidas = new ArrayList<PracticaPeticion>();
				for (int i = table.getModel().getRowCount() - 1 ; i >= 0 ; i --) {
					String practica = table.getValueAt(i, 0).toString();
					String resultado = table.getValueAt(i, 1).toString();
					practicasPedidas.add(new PracticaPeticion(PracticaController.getInstancia().getPracticaPorNombre(practica),resultado));
				}
				
				LaboratorioController.getInstancia().getPeticion(Integer.parseInt(idPeticion)).setPracticasPedidas(practicasPedidas);
				MenuPeticiones back = new MenuPeticiones();
				back.setVisible(true);
				setVisible(false);
			}
		});
		btn_aceptar.setBounds(323, 224, 97, 25);
		if (UsuarioController.getInstancia().getUsuarioActivo().getRol() == Rol.RECEPCION){
			btn_aceptar.setEnabled(false);
		}
		contentPane.add(btn_aceptar);
		
		JButton btn_cancelar = new JButton("Cancelar");
		btn_cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MenuPeticiones back = new MenuPeticiones();
				back.setVisible(true);
				setVisible(false);
			}
		});
		btn_cancelar.setBounds(209, 224, 97, 25);
		contentPane.add(btn_cancelar);
		

	}

	public void cargarResultados(String idPeticion) {
		this.idPeticion = idPeticion; //TODO ??????????????
		ArrayList<PracticaPedida> practicasPedidas = PeticionesManager.getInstancia().getPeticion(Integer.parseInt(idPeticion)).getPracticasPedidas();
		model = new DefaultTableModel(new Object[][] {
		},
		new String[] {
			"Practica","Resultado"
		});
		
		for(PracticaPedida pp : practicasPedidas){
			List<String> list = new ArrayList<String>();
			list.add(pp.getPractica().toString());
			if (pp.esReservada()){
				list.add("Retirar por sucursal");;
			} else { list.add(pp.getResultado()); }
			model.addRow(list.toArray());
		}
		
		table.setModel(model);
		
	}
}
