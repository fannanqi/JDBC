package com.Druid.test;

import com.Druid.Dao.Student;
import com.Druid.Service.DruidService;
import org.junit.Test;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class test {
    DruidService druidService=new DruidService();
    @Test
    public void updateById() throws SQLException {
        Map<String,String> cur=new HashMap<String,String>();
        cur.put("weight","56");
        int i = druidService.updateByName("kite", cur);
        System.out.println(i);
    }
    @Test
    public void getAll(){
        List<Student> students = druidService.selectAll();
        for (Student i:students
             ) {
            System.out.println(i);
        }
    }
}
