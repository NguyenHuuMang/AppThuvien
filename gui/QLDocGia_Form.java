package gui;

import dao.DocGiaDao;
import entity.DocGia;
import tableModel.DocGia_TableModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class QLDocGia_Form extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    JPanel pnNorth,pnCenter,pnSouth;
    JLabel lblMa,lblTen,lblMail,lblDienThoai,lblDiaChi, lblGioiTinh, lblHinh, lblUrl;
    JTextField txtMa,txtTen,txtMail,txtDienThoai,txtDiaChi, txtUrl;
    JRadioButton rdNam, rdNu;
    JButton btnXem, btnChonAnh;
    File file1;

    public QLDocGia_Form(){
        doShow();
    }
    public void doShow(){
        //pnNorth
        pnNorth = new JPanel();
        JPanel pnTieuDe = new JPanel();
        pnNorth.setLayout(new BorderLayout());
        JLabel lblTieuDe = new JLabel("QUẢN LÝ ĐỘC GIẢ");
        lblTieuDe.setFont(new Font("arial", Font.BOLD,20));
        lblTieuDe.setForeground(Color.RED);
        pnTieuDe.add(lblTieuDe);
        pnNorth.add(pnTieuDe);

        //pnCenter
        pnCenter = new JPanel();
        pnCenter.setLayout(new BorderLayout());
        Box b,b1,b2,b3,b4,b5,b6,b7,b11,b21;
        JPanel pnCenN = new JPanel();
        JPanel pnCenC = new JPanel();
        b = Box.createHorizontalBox();
        b11 = Box.createVerticalBox();
        b21 = Box.createVerticalBox();
        b.add(b11);
        b.add(Box.createHorizontalStrut(30));
        b.add(b21);
        b.setPreferredSize(new Dimension(840,250));

        b11.add(b1 = Box.createHorizontalBox());
        b1.add(lblMa = new JLabel("Mã Độc Giả: "));
        b1.add(txtMa = new JTextField(30));
        b11.add(Box.createVerticalStrut(10));

        b11.add(b2 = Box.createHorizontalBox());
        b2.add(lblTen = new JLabel("Tên Độc Giả: "));
        b2.add(txtTen = new JTextField(30));
        b11.add(Box.createVerticalStrut(10));

        b11.add(b3 = Box.createHorizontalBox());
        b3.add(lblMail = new JLabel("Email:    "));
        b3.add(txtMail = new JTextField(30));
        b11.add(Box.createVerticalStrut(10));

        b11.add(b4 = Box.createHorizontalBox());
        b4.add(lblDienThoai = new JLabel("Số Điện Thoại:    "));
        b4.add(txtDienThoai = new JTextField(30));
        b11.add(Box.createVerticalStrut(10));

        b11.add(b5 = Box.createHorizontalBox());
        b5.add(lblGioiTinh = new JLabel("Giới Tính: "));
        rdNam = new JRadioButton("Nam");
        rdNu = new JRadioButton("Nữ");
        ButtonGroup btnGr = new ButtonGroup();
        btnGr.add(rdNam);
        btnGr.add(rdNu);
        b5.add(rdNam);
        b5.add(rdNu);
        b11.add(Box.createVerticalStrut(10));

        b11.add(b6 = Box.createHorizontalBox());
        b6.add(lblDiaChi = new JLabel("Địa Chỉ:    "));
        b6.add(txtDiaChi = new JTextField(30));
        b11.add(Box.createVerticalStrut(10));

        b11.add(b7 = Box.createHorizontalBox());
        b7.add(btnChonAnh = new JButton("Chọn ảnh "));
        b7.add(lblUrl = new JLabel(""));
        b11.add(Box.createVerticalStrut(10));

        b21.add(lblHinh = new JLabel("Image"));
        b21.add(Box.createVerticalStrut(10));
        b21.add(btnXem = new JButton("Xem Ảnh"));
        lblHinh.setPreferredSize(new Dimension(200, 200));


        lblMa.setPreferredSize(lblDienThoai.getPreferredSize());
        lblTen.setPreferredSize(lblDienThoai.getPreferredSize());
        lblMail.setPreferredSize(lblDienThoai.getPreferredSize());
        lblDiaChi.setPreferredSize(lblDienThoai.getPreferredSize());
        lblGioiTinh.setPreferredSize(lblDienThoai.getPreferredSize());


        JPanel pnCenS = new JPanel();
        JButton btnThem,btnXoa,btnSua,btnThoat,btnLuu;
        pnCenS.add(btnThem = new JButton("Thêm Độc Giả"));
        pnCenS.add(btnXoa = new JButton("Xóa Độc Giả"));
        pnCenS.add(btnSua = new JButton("Sửa Thông Tin"));
        pnCenS.add(btnLuu = new JButton("Lưu Thông Tin"));
        pnCenS.add(btnThoat = new JButton("Thoát"));


        pnCenN.add(b);
        pnCenter.add(pnCenN,BorderLayout.NORTH);
        pnCenter.add(pnCenC,BorderLayout.CENTER);
        pnCenter.add(pnCenS,BorderLayout.SOUTH);

        txtMa.setEditable(false);
        //pnSouth
        pnSouth = new JPanel();
        List<DocGia> ls = new ArrayList<>();
        DocGiaDao docGiaDao = new DocGiaDao();
        DocGia_TableModel model = new DocGia_TableModel(docGiaDao.getLS());
        JTable table = new JTable();
        table.setModel(model);
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int r = table.getSelectedRow();
                if(r != -1){
                    txtMa.setText(table.getValueAt(r,0).toString());
                    txtTen.setText(table.getValueAt(r,1).toString());
                    txtMail.setText(table.getValueAt(r,2).toString());
                    txtDienThoai.setText(table.getValueAt(r,3).toString());
                    String gt = table.getValueAt(r,4).toString();
                    if(gt.equalsIgnoreCase("Nam")){
                        rdNam.setSelected(true);
                    }else{
                        rdNu.setSelected(true);
                    }
                    txtDiaChi.setText(table.getValueAt(r,5).toString());
                    File file = new File(table.getValueAt(r, 6).toString());
                    lblUrl.setText(table.getValueAt(r, 6).toString());
                    System.out.println(file);
                    BufferedImage b;
                    try{
                        b = ImageIO.read(file);
                        lblHinh.setIcon(new ImageIcon(b));
                    }catch (Exception e1){

                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        JScrollPane sc = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sc.setPreferredSize(new Dimension(850,280));

        pnSouth.add(sc);
        pnSouth.setBorder(new TitledBorder("Danh Sách Độc Giả"));

        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearText();
            }
        });
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int r = table.getSelectedRow();
                if(r != -1){
                    int lc = JOptionPane.showConfirmDialog(null,"Bạn có chắc chắn muốn xóa!","update",JOptionPane.YES_NO_OPTION);
                    if(lc == JOptionPane.YES_OPTION) {
                        if (docGiaDao.deleteDG(txtMa.getText().trim())) {
                            JOptionPane.showMessageDialog(null, "Xóa thành công!");
                            clearText();
                            table.setModel(new DocGia_TableModel(docGiaDao.getLS()));
                        }
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"bạn chưa chọn dòng cần xóa!");
                }
            }
        });
        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int r = table.getSelectedRow();
                if(r != -1){
                    int lc = JOptionPane.showConfirmDialog(null,"Bạn có chắc chắn muốn sửa!","update",JOptionPane.YES_NO_OPTION);
                    if(lc == JOptionPane.YES_OPTION) {
                        String gt;
                        if(rdNam.isSelected()){
                            gt = "Nam";
                        }else{
                            gt = "Nữ";
                        }
                        String maS = txtMa.getText().trim();
                        DocGia docGia = new DocGia(txtMa.getText(),txtTen.getText(),txtMail.getText(),
                                txtDienThoai.getText(),gt,txtDiaChi.getText(), lblUrl.getText());
                        if (docGiaDao.updateDocGia(maS, docGia)) {
                            JOptionPane.showMessageDialog(null,"Sửa thành công!");
                            table.setModel(new DocGia_TableModel(docGiaDao.getLS()));
                            clearText();
                        }
                    }
                }
            }
        });
//
        btnLuu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!txtTen.getText().trim().equals("")){
                    String gt;
                    if(rdNam.isSelected()){
                        gt = "Nam";
                    }else{
                        gt = "Nữ";
                    }
                    DocGia docGia = new DocGia(txtMa.getText(),txtTen.getText(),txtMail.getText(),
                           txtDienThoai.getText(),gt,txtDiaChi.getText(), lblUrl.getText());
                    if(docGiaDao.addDocGia(docGia)){
                        table.setModel(new DocGia_TableModel(docGiaDao.getLS()));
                        clearText();
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Bạn chưa nhập tên độc giả!");
                }
            }
        });
        btnThoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int lc = JOptionPane.showConfirmDialog(null,"Bạn có chắc chắn muốn thoát chương trình không?","Xác nhận",JOptionPane.YES_NO_OPTION);
                if(lc == JOptionPane.YES_OPTION)
                    setVisible(false);
            }
        });
        btnChonAnh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser = new JFileChooser();
                jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

                int returnValue = jFileChooser.showOpenDialog(pnCenC);
                if(returnValue == JFileChooser.APPROVE_OPTION) {
                    File file = jFileChooser.getSelectedFile();
                    String pathFile = file.getAbsolutePath();
                    file1 = jFileChooser.getSelectedFile();
                    lblUrl.setText(pathFile);

                    BufferedImage b;
                    try{
                        b = ImageIO.read(file);
                        lblHinh.setIcon(new ImageIcon(b));
                    }catch (Exception e1){

                    }
                }
            }
        });
        btnXem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JLabel lbHienThi;
                System.out.println(file1);
                lbHienThi = new JLabel();
                frame.add(lbHienThi);
                BufferedImage b;
                try{
                    b = ImageIO.read(file1);
                    lbHienThi.setIcon(new ImageIcon(b));
                }catch (Exception e1){

                }
                frame.setSize(600, 400);
                frame.setVisible(true);

            }

        });


        this.setLayout(new BorderLayout());
        this.add(pnNorth,BorderLayout.NORTH);
        this.add(pnCenter,BorderLayout.CENTER);
        this.add(pnSouth,BorderLayout.SOUTH);

    }
    public void clearText(){
        txtMa.setText("");
        txtTen.setText("");
        txtMail.setText("");
        txtDienThoai.setText("");
        txtDiaChi.setText("");
        lblHinh.setIcon(new ImageIcon());
        lblUrl.setText("");
        txtTen.requestFocus();
    }
}
//sử dụng sql nâng cao để connect với dữ liệu random
//khi sử dụng chức năng, có thể sẽ không thêm AVATAR được nhưng không có nghĩa là code sai
//nên hãy sử dụng những ảnh có kích cỡ nhỏ hoặc là không dùng khi không cần thiết 
//đó là tính năng của chương trình

