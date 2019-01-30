/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import domain.Instrument;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author David Shepherd
 */
public class InstrumentTableModel extends AbstractTableModel{
        private int totalRows = 11;
        private List<Instrument> instruments;
	private String[] columnNames = {
		"Sub Type",
		"Type",
		"Make",
		"Model",
		"Serial Number"
	};
        
        public InstrumentTableModel(List<Instrument> i){
            this.instruments = i;
        }
        
        public InstrumentTableModel(){
            this.instruments = new ArrayList<Instrument>();
        }
	
	@Override
	public int getColumnCount(){
		return columnNames.length;
	}
	@Override
	public String getColumnName(int column){
		return columnNames[column];
	}
	@Override
	public int getRowCount(){
		return instruments.size() + (totalRows - instruments.size());
	}    
        @Override
        public Class getColumnClass(int column){
            return String.class;
        }
        @Override
        public boolean isCellEditable(int row, int column){
            return false;
        }
        @Override
        public Object getValueAt(int row, int column){
            if(row < instruments.size()){
                Instrument i = instruments.get(row);
                switch(column){
                    case 0: return i.getSubType().getDescription();
                    case 1: return i.getType().getDescription();
                    case 2: return i.getMake();
                    case 3: return i.getModel();
                    case 4: return i.getSerialNumber();
                    default: return null;                    
                }                
            }else{
                return null;
            }
        }
        @Override
        public void setValueAt(Object value, int row, int column){
            // Method not needed
        }
        public Instrument getSelectedItem(int row){
            return instruments.get(row);
        }
        
}
