package com.edu.xd.hbase;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class HbaseUtilTest {

    @Test
    public void createTable() {
    }

    @Test
    public void truncateTable() {
    }

    @Test
    public void deleteTable() {
    }

    @Test
    public void deleteByRowKey() {
    }

    @Test
    public void addColumnFamily() {
    }

    @Test
    public void deleteColumnFamily() {
    }

    @Test
    public void insert() {
        HbaseUtil hu=new HbaseUtil();
        // 需要插入数据库的数据集合
        List<Put> putList = new ArrayList<Put>();
        Put put;
        // 生成数据集合

        put = new Put(Bytes.toBytes("test"));
        put.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("rawData"),Bytes.toBytes("test"));
        putList.add(put);
        hu.insert("test",putList);
    }

    @Test
    public void queryAllTable() {
    }

    @Test
    public void queryTableByRowKey() {
    }
}