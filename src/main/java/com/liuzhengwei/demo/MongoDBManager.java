package com.liuzhengwei.demo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoDatabase;

/**
 * @Author ：请叫我伟哥.
 * @Date ：Created in 15:32 2018/8/18 0018
 * @Description：自定义连接池  不用没次使用的时候都创建
 * @Modified By：
 * @Version: $version$
 */
public class MongoDBManager {

        private static MongoClient mongoClient = null;

        private static void init(){
            System.out.println ("第五次修改");
            System.out.println ("第七次修改");
            //连接池选项
            MongoClientOptions.Builder builder = new MongoClientOptions.Builder();//选项构建者
            //设置连接超时时间
            builder.connectTimeout(5000);
            builder.socketTimeout(5000);//读取数据的超时时间
            builder.connectionsPerHost(30);//每个地址最大请求数
            builder.writeConcern(WriteConcern.NORMAL);//写入策略，仅抛出网络异常
            MongoClientOptions options = builder.build();
            mongoClient=new MongoClient("127.0.0.1",options);
        }

        public static MongoDatabase getMongoDatabase(){
            if (mongoClient ==null){
                init ();
            }
            return mongoClient.getDatabase ("itcastdb");


        }

}
