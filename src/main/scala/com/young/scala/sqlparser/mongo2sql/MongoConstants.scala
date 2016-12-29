package com.young.scala.sqlparser.mongo2sql

import java.util.{Locale, ResourceBundle}

/**
  * Created by yangyong3 on 2016/12/29.
  */
object MongoConstants {

  private val bundler = ResourceBundle.getBundle("mongo", Locale.getDefault)

  val MONGO_SCHEMA_JSON =
    """{
                              "version": "1.0",
                              "defaultSchema": """" + "admin" + "\"," +
      """"schemas": [
                                {
                                  "type": "custom",
                                  "name": "database",
                                  "factory": "com.young.scala.sqlparser.map2sql.Map2SqlSchemaFactory",
                                  "operand": {""" + "\"url\": \"" + bundler.getString("mongo.url") +"\",\"database\":\""+bundler.getString("mongo.db")+
      """"}
                                }
                              ]
                            }"""

  def main(args: Array[String]) {
    println(MONGO_SCHEMA_JSON)
  }
}
