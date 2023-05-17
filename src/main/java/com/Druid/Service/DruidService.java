package com.Druid.Service;

import com.Druid.Dao.Student;
import com.Druid.Dao.User;
import com.Druid.config.JDBCUtils_Druid;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Component
@ComponentScan("com.Druid")
public class DruidService {
    JdbcTemplate template = new JdbcTemplate(JDBCUtils_Druid.getDataSource());
    public Connection conn=null;
    public PreparedStatement pstmt=null;
    public boolean add(Student student){
        try{
            conn= JDBCUtils_Druid.getConnection();
            conn.setAutoCommit(false);//开启事物
            String sql="insert into stu_info(id,name,chinese,math,eng,sport)" +
                    "values(?,?,?,?,?,?)";
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,student.getId());
            pstmt.setString(2,student.getName());
            pstmt.setDouble(3,student.getChinese());
            pstmt.setDouble(4,student.getMath());
            pstmt.setDouble(5,student.getEng());
            pstmt.setDouble(6,student.getSport());
            int i = pstmt.executeUpdate();
            conn.commit();//提交事物
            if(i>0)
                return true;
            else return false;
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        JDBCUtils_Druid.close(null,pstmt,conn);
        return true;
    }
    public User login(User loginUser){
        try {
            //编写sql
            String sql="select * from user where username=? and password=? ";
            //调用query方法
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class),
                    loginUser.getUsername(), loginUser.getPassword());
            return user;
        }catch (DataAccessException e){
            e.printStackTrace();//后期记录日志
            return null;
        }
    }
    public List<Student> selectAll(){
        List<Student> students = template.query("select * from stu_info",new BeanPropertyRowMapper<Student>(Student.class));
        return students;
    }
    public Student selectById(String id){
        String sql="select * from stu_info where id=?";
        Student student = template.queryForObject(sql, new BeanPropertyRowMapper<Student>(Student.class), id);
        return student;
    }
    public Student selectByName(String name){
        String sql="select * from stu_info where name=?";
        Student student = template.queryForObject(sql, new BeanPropertyRowMapper<Student>(Student.class),name);
        return student;
    }

    public int deleteById(String id){
        String sql="delete from stu_info where id=?";
        int update = template.update(sql,id);
        return update;
    }

    public int deleteByName(String name){
        String sql="delete from stu_info where name=?";
        int update = template.update(sql,name);
        return update;
    }

    public int updateById(Integer id, Map<String,String> cur) throws SQLException {
        int update = 0;
        try {
            conn = JDBCUtils_Druid.getConnection();
            conn.setAutoCommit(false);//开启事物
            // 假设 conn 是已经创建好的数据库连接对象
            String sql = "UPDATE stu_info SET ";
            List<Object> params = new ArrayList<>();

            if (cur.containsKey("name") && cur.get("name") != null) {
                sql += "name = ?, ";
                params.add(cur.get("name"));
            }
            if (cur.containsKey("chinese") && cur.get("chinese") != null) {
                sql += "chinese = ?, ";
                params.add(cur.get("chinese"));
            }
            if (cur.containsKey("math") && cur.get("math") != null) {
                sql += "math = ?, ";
                params.add(cur.get("math"));
            }
            if (cur.containsKey("eng") && cur.get("eng") != null) {
                sql += "eng = ?, ";
                params.add(cur.get("eng"));
            }
            if (cur.containsKey("sport") && cur.get("sport") != null) {
                sql += "sport = ?, ";
                params.add(cur.get("sport"));
            }

// 去掉 SQL 语句末尾的逗号和空格
            sql = sql.substring(0, sql.length() - 2);

// 添加 WHERE 子句
            sql += " WHERE id = ?";
            params.add(id);
            System.out.println(sql);

            PreparedStatement stmt = conn.prepareStatement(sql);
            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }
            update= stmt.executeUpdate();
            conn.commit();
            if(update>0){
                return update;
            }else{
                return 0;
            }

        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        JDBCUtils_Druid.close(null, pstmt, conn);
        return update;
    }
    public int updateByName(String name, Map<String,String> cur) throws SQLException {
        int update = 0;
        try {
            conn = JDBCUtils_Druid.getConnection();
            conn.setAutoCommit(false);//开启事物
            // 假设 conn 是已经创建好的数据库连接对象
            String sql = "UPDATE stu_info SET ";
            List<Object> params = new ArrayList<>();

            if (cur.containsKey("sport") && cur.get("sport") != null) {
                sql += "sport = ?, ";
                params.add(cur.get("sport"));
            }
            if (cur.containsKey("chinese") && cur.get("chinese") != null) {
                sql += "chinese = ?, ";
                params.add(cur.get("chinese"));
            }
            if (cur.containsKey("math") && cur.get("math") != null) {
                sql += "math = ?, ";
                params.add(cur.get("math"));
            }
            if (cur.containsKey("eng") && cur.get("eng") != null) {
                sql += "eng = ?, ";
                params.add(cur.get("eng"));
            }

// 去掉 SQL 语句末尾的逗号和空格
            sql = sql.substring(0, sql.length() - 2);

// 添加 WHERE 子句
            sql += " WHERE name = ?";
            params.add(name);

            PreparedStatement stmt = conn.prepareStatement(sql);
            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }
            update= stmt.executeUpdate();
            conn.commit();
            if(update>0){
                return update;
            }else{
                return 0;
            }
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        JDBCUtils_Druid.close(null, pstmt, conn);
        return update;
    }
    public boolean addUser(User user){
        try{
            conn= JDBCUtils_Druid.getConnection();
            conn.setAutoCommit(false);//开启事物
            String sql="insert into user(username,password)" +
                    "values(?,?)";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,user.getUsername());
            pstmt.setString(2,user.getPassword());
            int i = pstmt.executeUpdate();
            conn.commit();//提交事物
            String sql2="select * from user where username=?";
            User usr2= template.queryForObject(sql2, new BeanPropertyRowMapper<User>(User.class),user.getUsername());
            if(i>0&&usr2!=null)
                return true;
            else return false;
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        JDBCUtils_Druid.close(null,pstmt,conn);
        return true;
    }
}
