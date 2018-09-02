package com.liuzhengwei.demo;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * @Author ：请叫我伟哥.
 * @Date ：Created in 15:37 2018/8/18 0018
 * @Description：数据访问层
 * @Modified By：
 * @Version: $version$
 */
public class StudentDao {

    public void save(String name,String sex,double age,String address){
        MongoDatabase database = MongoDBManager.getMongoDatabase ();
        MongoCollection<Document> collection = database.getCollection("student");
        Document docment=new Document();
        docment.put("name", name);
        docment.put("sex", sex);
        docment.put("age", age);
        docment.put("address", address);
        collection.insertOne(docment);
    }
}
