package tableModel;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import model.Resultado;

public class ResultadosTableModel extends DefaultTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Resultado> listaResultados;

	protected String[] columnNames = new String[] { "ID", "Valor", "Tipo de valor" };
	protected Class[] columnClasses = new Class[] { int.class, String.class, String.class };

	public String getColumnName(int col) {
		return columnNames[col];
	}

	public Class getColumnClass(int col) {
		return columnClasses[col];
	}

	public ResultadosTableModel(List<Resultado> listaResultadosPeticion) {
		listaResultados = listaResultadosPeticion;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return listaResultados.size();
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return listaResultados.get(rowIndex).getIdResultado();
		case 1:
			return listaResultados.get(rowIndex).getValor().getIdValor();
		case 2:
			return listaResultados.get(rowIndex).getValor().getTipoValor();
		default:
			return null;
		}
	}

	public void agregar(Resultado resultado) {
		listaResultados.add(resultado);
		fireTableDataChanged();
	}

	public void refresh() {
		fireTableDataChanged();
	}

	public void eliminar(int fila) {
		listaResultados.remove(fila);
		fireTableDataChanged();
	}

	public void eliminar(Resultado resultado) {
		eliminar(listaResultados.indexOf(resultado));
	}


	public List<Resultado> getListaResPeticiones() {
		return listaResultados;
	}

	public void setListaResPeticiones(List<Resultado> listaResultados) {
		this.listaResultados = listaResultados;
		this.refresh();
	}

}