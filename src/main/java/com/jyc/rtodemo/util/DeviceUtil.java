package com.jyc.rtodemo.util;

import com.jyc.rtodemo.pojo.Device;
import com.jyc.rtodemo.pojo.User;

import java.io.File;
import java.io.RandomAccessFile;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class DeviceUtil {

    static int cnt = 0;

    public static void main(String[] args) throws Exception {
        for(int i = 0; i < 10; i++){
            createUser(500000);
            cnt += 500000;
        }
//        createUser(50);


    }



    private static void createUser(int count) throws Exception {

        List<Device> devices = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Device device = new Device();
            device.setId(1L + cnt + i);
            device.setJdpin(1L + cnt + i);
            device.setDid(UUID.randomUUID().toString());
            devices.add(device);
        }
        Connection conn = getConn();
        String sql = "insert into device(id,did,jdpin) values(?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        for (int i = 0; i < devices.size(); i++) {
            Device device = devices.get(i);
            pstmt.setLong(1, device.getId());
            pstmt.setString(2, device.getDid());
            pstmt.setLong(3, device.getJdpin());
            pstmt.addBatch();
        }
        pstmt.executeBatch();
        pstmt.clearParameters();
        conn.close();
        System.out.println("insert to DB");


        File file = new File("C:\\Users\\JYC\\Desktop\\config.txt");
        if (file.exists()) {
            file.delete();
        }
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        file.createNewFile();
        raf.seek(0);
        for (int i = 0; i < devices.size(); i++) {
            Device device = devices.get(i);

            String row = device.getDid();
            raf.seek(raf.length());
            raf.write(row.getBytes());
            raf.write("\r\n".getBytes());
//            System.out.println("write to file : " + user.getId());
        }
        raf.close();

        System.out.println("over");

    }

    public static Connection getConn() throws Exception {
        String url = "jdbc:mysql://localhost:3306/rto?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
        String username = "root";
        String password = "123456";
        String drive = "com.mysql.cj.jdbc.Driver";
        Class.forName(drive);
        return DriverManager.getConnection(url, username, password);
    }



}
