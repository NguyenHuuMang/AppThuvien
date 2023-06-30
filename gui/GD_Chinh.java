package gui;

import javax.swing.*;
import java.awt.*;

public class GD_Chinh extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel pnCneter;
    JLabel lblGiaoVien,lblTenGV,lblThanhVien,lblSV1,lbl1,lblSV2,lbl2,lblSV3;
    public GD_Chinh(){

        doShow();
    }
    public void doShow(){
        setSize(1200,700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Quản Lý Độc Giả");

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());


        Font ftmn = new Font("arial",Font.BOLD,16);

        //PnCneter
        pnCneter = new JPanel();
        QLDocGia_Form thuoc = new QLDocGia_Form();
        thuoc.doShow();
        pnCneter.removeAll();
        pnCneter.add(thuoc);
        validate();

        cp.add(pnCneter,BorderLayout.CENTER);

    }

    public static void main(String[] args) {
        new GD_Chinh().setVisible(true);
    }
}
