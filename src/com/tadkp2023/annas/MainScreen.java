package com.tadkp2023.annas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import java.awt.Image;

public class MainScreen extends JFrame {
    //deklarasi komponen GUI dan penamaan variabel komponen GUI (modul 8)
    private JPanel panelMain;
    private JTable jTableKaryawan;
    private JTextField txtNama;
    private JTextField txtId_karyawan;
    private JTextField txtJabatan;
    private JTextField txtNotelp;
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnClear;
    private JTextField txtSearch;
    private JButton btnSearch;
    private JTextField txtTgl_lahir;
    private JComboBox<String> txtAgama;
    private JRadioButton radioCowo;
    private JRadioButton radioCewe;
    private JLabel labellogo;
    private JButton btnX;
    private DefaultTableModel defaultTableModel = new DefaultTableModel();

    public MainScreen() {
        super("Data Karyawan PT Example");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panelMain);
        setResizable(true);
        pack();

        createTable();
        refreshTable(Methods.getData());

        btnAdd.addActionListener(e -> {
            int id_karyawan = Integer.parseInt(txtId_karyawan.getText());
            String nama = txtNama.getText();
            String jabatan = txtJabatan.getText();
            String tgl_lahir = txtTgl_lahir.getText();
            String notelp = txtNotelp.getText();
            String agama = txtAgama.getSelectedItem().toString();

            String genderr = "";
            //pengkondisian if elif (modul 2)
            if (radioCowo.isSelected()) {
                genderr = radioCowo.getText();
            } else if (radioCewe.isSelected()) {
                genderr = radioCewe.getText();
            }

            List<Methods> data = Methods.getData();
            boolean idExists = false;
            for (Methods method : data) {
                if (method.getId_karyawan() == id_karyawan) {
                    idExists = true;
                    break;
                }
            }

            if (idExists) {
                JOptionPane.showMessageDialog(panelMain, "Tidak dapat menambahkan data dengan ID Karyawan yang sama!");
            } else {
                int dialogResult = JOptionPane.showConfirmDialog(panelMain, "Apakah Anda ingin menambahkan data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    Methods methods = new Methods();
                    methods.setNama(nama);
                    methods.setJabatan(jabatan);
                    methods.setTgl_lahir(tgl_lahir);
                    methods.setId_karyawan(id_karyawan);
                    methods.setAgama(agama);
                    methods.setNotelp(notelp);
                    methods.setGenderr(genderr);

                    clearForm();
                    Methods.insertData(methods); //pemanggilan method insertData (modul 4)
                    refreshTable(Methods.getData());
                }
            }
        });

        jTableKaryawan.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                int row = jTableKaryawan.getSelectedRow();
                String id_karyawan = jTableKaryawan.getValueAt(row, 0).toString();
                String nama = jTableKaryawan.getValueAt(row, 1).toString();
                String jabatan = jTableKaryawan.getValueAt(row, 2).toString();
                String tgl_lahir = jTableKaryawan.getValueAt(row, 3).toString();
                String notelp = jTableKaryawan.getValueAt(row, 4).toString();
                String agama = jTableKaryawan.getValueAt(row, 5).toString();
                String genderr = jTableKaryawan.getValueAt(row, 6).toString();

                txtNama.setText(nama);
                txtJabatan.setText(jabatan);
                txtTgl_lahir.setText(tgl_lahir);
                txtId_karyawan.setText(id_karyawan);
                txtNotelp.setText(notelp);
                txtAgama.setSelectedItem(agama);

                if (genderr.equals("Laki-laki")) {
                    radioCowo.setSelected(true);
                } else if (genderr.equals("Perempuan")) {
                    radioCewe.setSelected(true);
                }
            }
        });

        btnUpdate.addActionListener(e -> {
            int id_karyawan = Integer.parseInt(txtId_karyawan.getText());
            String nama = txtNama.getText();
            String jabatan = txtJabatan.getText();
            String tgl_lahir = txtTgl_lahir.getText();
            String notelp = txtNotelp.getText();
            String agama = txtAgama.getSelectedItem().toString();

            String genderr = "";
            if (radioCowo.isSelected()) {
                genderr = radioCowo.getText();
            } else if (radioCewe.isSelected()) {
                genderr = radioCewe.getText();
            }

            Methods methods = new Methods();
            methods.setNama(nama);
            methods.setJabatan(jabatan);
            methods.setTgl_lahir(tgl_lahir);
            methods.setId_karyawan(id_karyawan);
            methods.setNotelp(notelp);
            methods.setAgama(agama);
            methods.setGenderr(genderr);

            int dialogResult = JOptionPane.showConfirmDialog(panelMain, "Apakah Anda ingin mengupdate data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) {
                clearForm();
                Methods.updateData(methods);
                refreshTable(Methods.getData());
            }
        });

        btnDelete.addActionListener(e -> {
            int id_karyawan = Integer.parseInt(txtId_karyawan.getText());

            int dialogResult = JOptionPane.showConfirmDialog(panelMain, "Apakah Anda ingin menghapus data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) {
                clearForm();
                Methods.deleteData(String.valueOf(id_karyawan));
                refreshTable(Methods.getData());
            }
        });

        btnClear.addActionListener(e -> clearForm());

        btnSearch.addActionListener(e -> {
            String keyword = txtSearch.getText();
            refreshTable(searchData(keyword));
        });

        btnX.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
                refreshTable(Methods.getData());
            }
        });

        txtTgl_lahir.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (txtTgl_lahir.getText().equals("dd/mm/yyyy")) {
                    txtTgl_lahir.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if (txtTgl_lahir.getText().equals("")) {
                    txtTgl_lahir.setText("dd/mm/yyyy");
                }
            }
        });

        txtNotelp.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (txtNotelp.getText().equals("08xxxxxxxxxx")) {
                    txtNotelp.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if (txtNotelp.getText().equals("")) {
                    txtNotelp.setText("08xxxxxxxxxx");
                }
            }
        });

        // Menambahkan gambar pada variabel labellogo
        ImageIcon logo = new ImageIcon("image/logo.png");
        labellogo.setIcon(logo);

        // Mengubah ukuran logo
        ImageIcon resizedLogo = new ImageIcon(logo.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH));
        labellogo.setIcon(resizedLogo);
    }

    private void createTable() {
        defaultTableModel = new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID Karyawan", "Nama", "Jabatan", "Tanggal Lahir", "No Telepon", "Agama", "Jenis Kelamin"}
        );
        jTableKaryawan.setModel(defaultTableModel);
    }

    public void refreshTable(List<Methods> arrayListMethods) {
        defaultTableModel.setRowCount(0);

        //perulangan for (modul 3)
        for (Methods method : arrayListMethods) {
            Object[] rowData = {
                    method.getId_karyawan(),
                    method.getNama(),
                    method.getJabatan(),
                    method.getTgl_lahir(),
                    method.getNotelp(),
                    method.getAgama(),
                    method.getGenderr()
            };
            defaultTableModel.addRow(rowData);
        }
    }

    private void clearForm() {
        txtNama.setText("");
        txtJabatan.setText("");
        txtTgl_lahir.setText("");
        txtId_karyawan.setText("");
        txtNotelp.setText("");
        txtSearch.setText("");
    }

    public static List<Methods> searchData(String keyword) {
        List<Methods> arrayListMethods = new ArrayList<>();
        ResultSet resultSet = Koneksidatabase.executeQuery("SELECT * FROM db_karyawan WHERE id_karyawan LIKE '%" + keyword + "%' OR nama LIKE '%" + keyword + "%'");
        try {
            while (resultSet.next()) {
                String nama = resultSet.getString("nama");
                int id_karyawan = Integer.parseInt(resultSet.getString("id_karyawan"));
                String jabatan = resultSet.getString("jabatan");
                String tgl_lahir = resultSet.getString("tgl_lahir");
                String notelp = resultSet.getString("no_telepon");
                String agama = resultSet.getString("agama");
                String genderr = resultSet.getString("jenis_kelamin");

                Methods methods = new Methods();
                methods.setNotelp(notelp);
                methods.setNama(nama);
                methods.setAgama(agama);
                methods.setGenderr(genderr);
                methods.setJabatan(jabatan);
                methods.setTgl_lahir(tgl_lahir);
                methods.setId_karyawan(Integer.parseInt(String.valueOf(id_karyawan)));
                arrayListMethods.add(methods);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayListMethods;
    }

    public static void main(String[] args) {
        MainScreen mainScreen = new MainScreen();
        mainScreen.setVisible(true);
    }
}