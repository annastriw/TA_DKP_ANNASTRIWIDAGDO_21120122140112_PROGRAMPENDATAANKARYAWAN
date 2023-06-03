package com.tadkp2023.annas;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

//kelas Methods (oop I / modul 5)
public class Methods {
    //pendeklarasian variabel (modul 1)
    private int id_karyawan;
    private String nama;
    private String jabatan;
    private String tgl_lahir;
    private String notelp;
    private String agama;
    private String genderr;

    //setter dan getter (oop II / modul 6)
    public int getId_karyawan() {
        return id_karyawan;
    }

    public void setId_karyawan(int id_karyawan) {
        this.id_karyawan = id_karyawan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getTgl_lahir() {
        return tgl_lahir;
    }

    public void setTgl_lahir(String tgl_lahir) {
        this.tgl_lahir = tgl_lahir;
    }

    public String getNotelp() {
        return notelp;
    }

    public void setNotelp(String notelp) {
        this.notelp = notelp;
    }

    public String getAgama() {
        return agama;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }

    public String getGenderr() {
        return genderr;
    }

    public void setGenderr(String genderr) {
        this.genderr = genderr;
    }

    public static List<Methods> getData() {
        List<Methods> arrayListMethods = new ArrayList<>();
        ResultSet resultSet = Koneksidatabase.executeQuery("SELECT * FROM db_karyawan");

        try {
            while (resultSet.next()) {
                String nama = resultSet.getString("nama");
                int id_karyawan = resultSet.getInt("id_karyawan");
                String jabatan = resultSet.getString("jabatan");
                String tgl_lahir = resultSet.getString("tgl_lahir");
                String notelp = resultSet.getString("no_telepon");
                String agama = resultSet.getString("agama");
                String genderr = resultSet.getString("jenis_kelamin");

                Methods methods = new Methods();
                methods.setNotelp(notelp);
                methods.setAgama(agama);
                methods.setGenderr(genderr);
                methods.setNama(nama);
                methods.setJabatan(jabatan);
                methods.setTgl_lahir(tgl_lahir);
                methods.setId_karyawan(id_karyawan);
                arrayListMethods.add(methods);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return arrayListMethods;
    }

    //method pendefinisi insertData (modul 4)
    public static void insertData(Methods methods) {
        String sql = "INSERT INTO db_karyawan VALUES (" +
                methods.getId_karyawan() + ", " +
                "'" + methods.getNama() + "', " +
                "'" + methods.getJabatan() + "', " +
                "'" + methods.getTgl_lahir() + "', " +
                "'" + methods.getNotelp() + "', " +
                "'" + methods.getAgama() + "', " +
                "'" + methods.getGenderr() + "')";

        Koneksidatabase.executeSql(sql);
    }

    public static void updateData(Methods methods) {
        String sql = "UPDATE db_karyawan SET " +
                "nama = '" + methods.getNama() + "', " +
                "jabatan = '" + methods.getJabatan() + "', " +
                "tgl_lahir = '" + methods.getTgl_lahir() + "', " +
                "agama = '" + methods.getAgama() + "', " +
                "jenis_kelamin = '" + methods.getGenderr() + "', " +
                "no_telepon = '" + methods.getNotelp() + "' " +
                "WHERE id_karyawan = " + methods.getId_karyawan();

        Koneksidatabase.executeSql(sql);
    }

    public static void deleteData(String id_karyawan) {
        String sql = "DELETE FROM db_karyawan " +
                "WHERE id_karyawan = " + id_karyawan;

        Koneksidatabase.executeSql(sql);
    }
}