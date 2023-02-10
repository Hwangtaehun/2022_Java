package Allowance;
import javax.swing.*;

public class DBA_ComboBox{
	private JComboBox <String> comboBox;
	private String dataModel[];
	
	public DBA_ComboBox() {}
	public DBA_ComboBox(String dataModel[])
	{
		this.dataModel = dataModel;
		comboBox = new JComboBox<String>(new DefaultComboBoxModel<String>(dataModel));
	}
	
	public void comboxSetValueAt(String dataModel[])
	{
		comboBox.removeAllItems();
		for (int i = 0; i < dataModel.length; i++) {
			comboBox.addItem(dataModel[i]);
		}
	}
}
