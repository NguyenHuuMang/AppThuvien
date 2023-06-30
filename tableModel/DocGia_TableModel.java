package tableModel;

import entity.DocGia;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class DocGia_TableModel extends AbstractTableModel {

    private List<DocGia> ds;
    String[] headers = {"Mã Độc Giả", "Tên Độc Giả", "Email", "Số Điện Thoại", "Giới Tính", "Địa Chỉ", "ImageUrl"};

    public DocGia_TableModel(List<DocGia> ds) {
        super();
        this.ds = ds;
    }

    public String getColumnName(int column) {
        return headers[column];
    }

    @Override
    public int getRowCount() {
        return ds.size();
    }

    @Override
    public int getColumnCount() {
        return headers.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        DocGia t = ds.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return t.getMaDocGia();
            case 1:
                return t.getHoTen();
            case 2:
                return t.getEmail();
            case 3:
                return t.getSoDienThoai();
            case 4:
                return t.getGioiTinh();
            case 5:
                return t.getDiaChi();
            case 6:
                return t.getImageUrl();
            default:
                return t;
        }
    }
}