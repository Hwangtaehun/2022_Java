package LayoutManager;
import java.awt.*;
public class Sj6107 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Frame frm = new Frame("GridBagLayout 연습");
		Button bt;
		
		GridBagLayout gbl = new GridBagLayout();
		frm.setLayout(gbl);
		frm.setSize(200, 200);
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		//gbc.weighty = 0;
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		bt = new Button("111");
		gbl.setConstraints(bt, gbc);
		frm.add(bt);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 2;
		bt = new Button("22");
		gbl.setConstraints(bt, gbc);
		frm.add(bt);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		bt = new Button("333");
		gbl.setConstraints(bt, gbc);
		frm.add(bt);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 4;
		bt = new Button("444");
		gbl.setConstraints(bt, gbc);
		frm.add(bt);
		
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		bt = new Button("555");
		gbl.setConstraints(bt, gbc);
		frm.add(bt);
		
		gbc.gridx = 2;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		bt = new Button("666");
		gbl.setConstraints(bt, gbc);
		frm.add(bt);
		
		gbc.gridx = 2;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		bt = new Button("777");
		gbl.setConstraints(bt, gbc);
		frm.add(bt);
		
		gbc.gridx = 2;
		gbc.gridy = 4;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		bt = new Button("888");
		gbl.setConstraints(bt, gbc);
		frm.add(bt);
		
		frm.setVisible(true);
	}
}
