package com.jyc.rtodemo.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jyc.rtodemo.pojo.User;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Produce {
    static int cnt = 500000;

    public static void main(String[] args) throws Exception {
        for(int i = 0; i < 9; i++){
            createUser(500000);
            cnt += 500000;
        }

    }



    private static void createUser(int count) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Date> dates = randomDate("2021-6-21 00:00:00", "2022-11-05 23:59:59", count);

        List<User> users = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            User user = new User();
            user.setId(0L + cnt + i);
            user.setUsername("user" + i);
            user.setPassword("123456");
            user.setRegisterDate(new Date());
            user.setLastLoginDate(dates.get(i));
            users.add(user);
        }
        Connection conn = getConn();
        String sql = "insert into user(id,username,password,register_date,last_login_date) values(?,?,?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            pstmt.setLong(1, user.getId());
            pstmt.setString(2, user.getUsername());
            pstmt.setString(3, user.getPassword());
            pstmt.setTimestamp(4, new Timestamp(user.getRegisterDate().getTime()));
            pstmt.setTimestamp(5, new Timestamp(user.getLastLoginDate().getTime()));
            pstmt.addBatch();
        }
        pstmt.executeBatch();
        pstmt.clearParameters();
        conn.close();
        System.out.println("insert to DB");

//        String urlString = "http://localhost:8080/login/doLogin";
//        File file = new File("C:\\Users\\sxau\\Desktop\\config.txt");
//        if (file.exists()) {
//            file.delete();
//        }
//        RandomAccessFile raf = new RandomAccessFile(file, "rw");
//        file.createNewFile();
//        raf.seek(0);
//        for (int i = 0; i < users.size(); i++) {
//            User user = users.get(i);
//            URL url = new URL(urlString);
//            HttpURLConnection co = (HttpURLConnection) url.openConnection();
//            co.setRequestMethod("POST");
//            co.setDoOutput(true);
//            OutputStream out = co.getOutputStream();
//            String params = "mobile=" + user.getId() + "&password=" + MD5Util.inputPassToFromPass("123456");
//            out.write(params.getBytes());
//            out.flush();
//            InputStream inputStream = co.getInputStream();
//            ByteArrayOutputStream bout = new ByteArrayOutputStream();
//            byte buff[] = new byte[1024];
//            int len = 0;
//            while ((len = inputStream.read(buff)) >= 0) {
//                bout.write(buff, 0, len);
//            }
//            inputStream.close();
//            bout.close();
//            String response = new String(bout.toByteArray());
//            ObjectMapper mapper = new ObjectMapper();
//            RespBean respBean = mapper.readValue(response, RespBean.class);
//            String userTicket = ((String) respBean.getObj());
//            System.out.println("create userTicket : " + user.getId() + userTicket);
//
//            String row = user.getId() + "," + userTicket;
//            raf.seek(raf.length());
//            raf.write(row.getBytes());
//            raf.write("\r\n".getBytes());
//            System.out.println("write to file : " + user.getId());
//        }
//        raf.close();
//
//        System.out.println("over");

    }

    public static Connection getConn() throws Exception {
        String url = "jdbc:mysql://localhost:3306/rto?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
        String username = "root";
        String password = "123456";
        String drive = "com.mysql.cj.jdbc.Driver";
        Class.forName(drive);
        return DriverManager.getConnection(url, username, password);
    }

    public static List<Date> randomDate(String start, String end, int size) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startTime = sdf.parse(start);
        Date endTime = sdf.parse(end);

        Random random = new Random();
        List<Date> dates = random.longs(size, startTime.getTime(), endTime.getTime()).mapToObj(t -> new Date(t)).collect(Collectors.toList());

        dates.sort((t1,t2)->{
            return t2.compareTo(t1);
        });

        return dates;
    }



}
