package dao;

import connection.MyConnection;
import entity.DocGia;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DocGiaDao {
    private Connection con;

    public DocGiaDao() {
        con = MyConnection.getInstance().getConnection();
    }

    public ResultSet getResultSet(String StoreName) throws Exception {
        ResultSet rs = null;
        try {
            String callStore;
            callStore = "{Call " + StoreName + "}";
            CallableStatement cs = this.con.prepareCall(callStore);
            cs.executeQuery();
            rs = cs.getResultSet();
        } catch (Exception e) {
            throw new Exception("Error get Store " + e.getMessage());
        }
        return rs;
    }

    public List<DocGia> getLS() {
        List<DocGia> ds = new ArrayList<>();
        try {
            ResultSet rs = getResultSet("select_DG");
            while (rs.next()) {
                DocGia dg =new DocGia(rs.getString(1),rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
                ds.add(dg);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ds;
    }

    public boolean addDocGia(DocGia dg) {
        try {
            PreparedStatement dgAdd = con.prepareStatement("INSERT INTO DOCGIA ([HoTen],[Email],[SoDienThoai]," +
                    "[GioiTinh],[DiaChi], [ImageUrl]) VALUES(?,?,?,?,?,?)");
            dgAdd.setString(1, dg.getHoTen());
            dgAdd.setString(2, dg.getEmail());
            dgAdd.setString(3, dg.getSoDienThoai());
            dgAdd.setString(4, dg.getGioiTinh());
            dgAdd.setString(5, dg.getDiaChi());
            dgAdd.setString(6, dg.getImageUrl());

            int n = dgAdd.executeUpdate();
            if (n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteDG(String maDocGia) {
        try {
            PreparedStatement stmt = con.prepareStatement("delete from DOCGIA where MaDocGia = ?");
            stmt.setString(1, maDocGia);
            int n = stmt.executeUpdate();
            if (n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateDocGia(String maDocGia, DocGia dg) {
        String sql = "update DOCGIA set HoTen = ?, "
                + "Email = ?,SoDienThoai = ? ,GioiTinh = ?,DiaChi = ? , ImageUrl = ? where MaDocGia = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, dg.getHoTen());
            stmt.setString(2, dg.getEmail());
            stmt.setString(3, dg.getSoDienThoai());
            stmt.setString(4, dg.getGioiTinh());
            stmt.setString(5, dg.getDiaChi());
            stmt.setString(6, dg.getImageUrl());
            stmt.setString(7, maDocGia);

            int n = stmt.executeUpdate();
            if (n > 0)
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public DocGia TimKiemMa(String ma) {
        DocGia dg = null;
        try {
            PreparedStatement stmt = con.prepareStatement("select * from DOCGIA where MaDocGia = ?");
            stmt.setString(1, ma);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                dg = new DocGia(rs.getString(1),rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dg;
    }
}
