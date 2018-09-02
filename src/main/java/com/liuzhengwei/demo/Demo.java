package com.liuzhengwei.demo;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * @Author ：请叫我伟哥.
 * @Date ：Created in 14:21 2018/8/18 0018
 * @Description：${description}
 * @Modified By：
 * @Version: $version$
 */
public class Demo {

    /**
     * 查询全部记录
     */
    @Test
    public void demotest(){
        System.out.println ("不不不不不不");
        System.out.println ("第三个版本");
        System.out.println ("第四个版本");
        System.out.println ("第五次修改");
        MongoClient client=new MongoClient();//创建连接对象
        MongoDatabase database = client.getDatabase("itcastdb");//获取数据库
        MongoCollection<Document> collection = database.getCollection("student");//获取集合

        FindIterable<Document> list = collection.find();//获取文档集合
        for( Document doc: list){//遍历集合中的文档输出数据
            System.out.println("name:"+ doc.getString("name") );
            System.out.println("sex:"+ doc.getString("sex") );
            System.out.println("age:"+ doc.getDouble("age") );//默认为浮点型
            System.out.println("address:"+ doc.getString("address") );
            System.out.println("--------------------------");
        }
    }

    /**
     * 条件查询
     */
    @Test
    public void  demo2(){
        MongoClient client=new MongoClient();//创建连接对象
        MongoDatabase database = client.getDatabase("itcastdb");//获取数据库
        MongoCollection<Document> collection = database.getCollection("student");//获取集合
        //构建查询条件
        BasicDBObject bson = new BasicDBObject ("name","白龙马");
        FindIterable <Document> documents = collection.find (bson);
        for (Document doc : documents) {
            System.out.println("name:"+ doc.getString("name") );
            System.out.println("sex:"+ doc.getString("sex") );
            System.out.println("age:"+ doc.getDouble("age") );//默认为浮点型
            System.out.println("address:"+ doc.getString("address") );
            System.out.println("--------------------------");
        }
    }

    /**
     * 构建模糊查询条件是通过正则表达式的方式来实现的
     *  完全匹配Pattern pattern = Pattern.compile("^name$");
     *  右匹配Pattern pattern = Pattern.compile("^.*name$");
     *  左匹配Pattern pattern = Pattern.compile("^name.*$");
     *  模糊匹配Pattern pattern = Pattern.compile("^.*name.*$");
     */
    @Test
    public void demo3(){
        MongoClient client=new MongoClient();//创建连接对象
        MongoDatabase database = client.getDatabase("itcastdb");//获取数据库
        MongoCollection<Document> collection = database.getCollection("student");//获取集合
        //构建模糊查询条件
        //Pattern pattern = Pattern.compile ("^白龙马$");
        Pattern pattern = Pattern.compile ("^.*白.*$");
        BasicDBObject bson = new BasicDBObject ("name", pattern);
        FindIterable <Document> list = collection.find (bson);
        for (Document doc : list) {
            System.out.println("name:"+ doc.getString("name") );
            System.out.println("sex:"+ doc.getString("sex") );
            System.out.println("age:"+ doc.getDouble("age") );//默认为浮点型
            System.out.println("address:"+ doc.getString("address") );
            System.out.println("--------------------------");
        }
    }

    /**
     * 大于小于
     *  条件嵌套
     */

    @Test
    public void  demo4(){
        MongoClient client=new MongoClient();//创建连接对象
        MongoDatabase database = client.getDatabase("itcastdb");//获取数据库
        MongoCollection<Document> collection = database.getCollection("student");//获取集合

        BasicDBObject $lt = new BasicDBObject ("$lte", 20);//小于 gt大于 gte大于等于 lte小于等于
        BasicDBObject bson = new BasicDBObject ("age", $lt);

        FindIterable <Document> list = collection.find (bson);
        for (Document doc : list) {
            System.out.println("name:"+ doc.getString("name") );
            System.out.println("sex:"+ doc.getString("sex") );
            System.out.println("age:"+ doc.getDouble("age") );//默认为浮点型
            System.out.println("address:"+ doc.getString("address") );
            System.out.println("--------------------------");
        }
    }

    /**
     * 条件连接--并且
     *
     */
    @Test
    public  void  demo5(){
        MongoClient client = new MongoClient ();
        MongoDatabase database = client.getDatabase ("itcastdb");
        MongoCollection <Document> collection = database.getCollection ("student");

        BasicDBObject gte = new BasicDBObject ("age", new BasicDBObject ("$gte",20));
        BasicDBObject lte = new BasicDBObject ("age", new BasicDBObject("$lt",30));
        BasicDBObject bson = new BasicDBObject ("$and", Arrays.asList (gte, lte));
        FindIterable <Document> list = collection.find (bson);
        for (Document doc : list) {
            System.out.println("name:"+ doc.getString("name") );
            System.out.println("sex:"+ doc.getString("sex") );
            System.out.println("age:"+ doc.getDouble("age") );//默认为浮点型
            System.out.println("address:"+ doc.getString("address") );
            System.out.println("--------------------------");
        }


    }
    /**
     * 条件连接--或者
     *
     */
    @Test
    public  void  demo6(){
        System.out.println ("aaaaaaaaa");


        MongoClient client = new MongoClient ();
        MongoDatabase database = client.getDatabase ("itcastdb");
        MongoCollection <Document> collection = database.getCollection ("student");

        BasicDBObject bson1 = new BasicDBObject ("age", new BasicDBObject ("$lte",20));
        BasicDBObject bson2 = new BasicDBObject ("sex", "女");
        BasicDBObject bson = new BasicDBObject ("$or", Arrays.asList (bson1, bson2));
        FindIterable <Document> list = collection.find (bson);
        for (Document doc : list) {
            System.out.println("name:"+ doc.getString("name") );
            System.out.println("sex:"+ doc.getString("sex") );
            System.out.println("age:"+ doc.getDouble("age") );//默认为浮点型
            System.out.println("address:"+ doc.getString("address") );
            System.out.println("--------------------------");
        }
    }

    /**
     * 增
     * insertOne方法来插入文档  updateMany方法用于修改符合条件的所有记录
                                updateOne方法用于修改符合条件的第一条记录
     */
    @Test
    public  void  demo7(){
        MongoClient client = new MongoClient ();
        MongoDatabase database = client.getDatabase ("itcastdb");
        MongoCollection <Document> collection = database.getCollection ("student");
        //创建集合封装对象
        HashMap <String, Object> map = new HashMap <> ();
        map.put ("name","铁扇公主");
        map.put ("age",23.0);
        map.put ("sex","女");
        map.put ("address","牛头山");
        Document document = new Document (map);
        collection.insertOne (document);//插入一条数据
        //collection.insertMany(documents);//一次性插入多条文档
    }

    /**
     * 删除文档
     *
     */
    @Test
    public void demo8(){
        MongoClient client = new MongoClient ();
        MongoDatabase database = client.getDatabase ("itcastdb");
        MongoCollection <Document> collection = database.getCollection ("student");
        BasicDBObject bson = new BasicDBObject ("name", "铁扇公主");
        collection.deleteOne (bson);
    }

    /**
     * 修改
     *
     *
     */

    @Test
    public  void demo9(){

        MongoClient client = new MongoClient ();
        MongoDatabase database = client.getDatabase ("itcastdb");
        MongoCollection <Document> collection = database.getCollection ("student");
        BasicDBObject bson = new BasicDBObject ("name", "铁扇公主");
        BasicDBObject bson2 = new BasicDBObject ("$set", new BasicDBObject ("address", "南山"));
        collection.updateMany (bson, bson2);

    }
}
