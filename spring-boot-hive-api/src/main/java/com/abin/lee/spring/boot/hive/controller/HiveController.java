package com.abin.lee.spring.boot.hive.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
/**
 * Created by abin on 2018/2/22 20:59.
 * spring-boot-hive1
 * com.abin.lee.spring.boot.hive.controller
 */


@Controller
@RequestMapping("/hive")
public class HiveController {
    private static final Logger logger = LoggerFactory.getLogger(HiveController.class);

    @Autowired
    @Qualifier("hiveJdbcTemplate")
    private JdbcTemplate hiveJdbcTemplate;

    @RequestMapping("/create")
    @ResponseBody
    public ModelAndView create() {
        StringBuffer sql = new StringBuffer("create table IF NOT EXISTS ");
        sql.append("HIVE_TEST");
        sql.append("(KEY INT, VALUE STRING)");
//        sql.append("PARTITIONED BY (CTIME DATE)"); // 分区存储
//        sql.append("ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n' "); // 定义分隔符
        sql.append("STORED AS TEXTFILE"); // 作为文本存储
        // drop table
        // StringBuffer sql = new StringBuffer("DROP TABLE IF EXISTS ");
        // sql.append("HIVE_TEST1");
        logger.info(sql.toString());
        hiveJdbcTemplate.execute(sql.toString());
        return new ModelAndView("index");

    }

    @RequestMapping("/insert")
    @ResponseBody
    public String insert() {
        hiveJdbcTemplate.execute("insert into hive_test(key, value) values('Neo','Chen')");
        return "Done";
    }

    @RequestMapping("/select")
    @ResponseBody
    public String select() {
        String sql = "select * from HIVE_TEST";
        List<Map<String, Object>> rows = hiveJdbcTemplate.queryForList(sql);
        Iterator<Map<String, Object>> it = rows.iterator();
        while (it.hasNext()) {
            Map<String, Object> row = it.next();
            System.out.println(String.format("%s\t%s", row.get("key"), row.get("value")));
        }
        return "Done";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete() {
        StringBuffer sql = new StringBuffer("DROP TABLE IF EXISTS ");
        sql.append("HIVE_TEST");
        logger.info(sql.toString());
        hiveJdbcTemplate.execute(sql.toString());
        return "Done";
    }











}

