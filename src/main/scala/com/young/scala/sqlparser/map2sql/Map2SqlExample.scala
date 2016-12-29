package com.young.scala.sqlparser.map2sql

import java.sql.{ResultSet, Statement, DriverManager, Connection}
import java.util.Properties

/**
  * Created by yangyong3 on 2016/12/28.
  */
object Map2SqlExample {

  def main(args: Array[String]) {
    val model: String = "D:\\young\\scala\\young-scala-example\\src\\main\\resources\\map2sql.json"
    Class.forName("org.apache.calcite.jdbc.Driver")
    val connection: Connection = DriverManager.getConnection("jdbc:calcite:model=" + model, new Properties)
    val meta = connection.getMetaData.getTables(null,null,null,null);
    while(meta.next()){
      System.out.println("Catalog : " + meta.getString(1) + ",Database : " + meta.getString(2) + ",Table : " + meta.getString(3));
    }
    val st: Statement = connection.createStatement
    val rs: ResultSet = st.executeQuery("select * from \"student\"")
    while (rs.next) {
      System.out.println(rs.getObject(1) + "," + rs.getObject(2))
    }
  }
}
