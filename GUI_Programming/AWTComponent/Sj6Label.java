package AWTComponent;
import java.awt.*;

public class Sj6Label {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Frame frm = new Frame("Label Test");
		Label label1 = new Label("label 1");
		Label label2 = new Label("label 2");
		Label label3 = new Label("label 3");
		
		frm.setLayout(new FlowLayout());
		frm.addWindowListener(new WindowHandler63());
		frm.add(label1);
		frm.add(label2);
		frm.add(label3);
		frm.setSize(200, 100);
		frm.setVisible(true);
		
		label1.setText("LABEL 1");
		System.out.println("label1의 제목은 " + label1.getText());
	}

}
