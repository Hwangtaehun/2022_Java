package Swing;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

public class Sj64JList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SjswList frame = new SjswList("JList Test");
		frame.initForm();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

}

class SjswList extends JFrame implements ListSelectionListener{
	JList <String> list;
	Vector <String> vData1;
	
	public SjswList(){}
	public SjswList(String str) {
		super(str);
	}
	
	public void initForm() {
		vData1 = new Vector<String>();
		vData1.addElement("세 종");
		vData1.addElement("정보처리");
		vData1.addElement("컴퓨터");
		vData1.addElement("학원");
		vData1.addElement("주)세종");
		
		list = new JList<String>();
		list.setListData(vData1);
		//list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		//list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		list.addListSelectionListener(this);
		list.setVisibleRowCount(4);
		
		ListModel<String> lm;
		lm = list.getModel();
		vData1.addElement("세종기획");
		System.out.println("현재 Model의 Data 개수 = " + lm.getSize() + " " + vData1.size());
		
		JScrollPane scrollPane1 = new JScrollPane(list);
		scrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		getContentPane().add(scrollPane1, "Center");
		//getContentPane().add(list, "Center");
	}
	
	@Override
	public void valueChanged(ListSelectionEvent event) {
		// TODO Auto-generated method stub
		if(!event.getValueIsAdjusting()) {
			String str = (String)list.getSelectedValue();
			System.out.println(str + "\tindex " + list.getSelectedIndex());
		}
	}
	
}