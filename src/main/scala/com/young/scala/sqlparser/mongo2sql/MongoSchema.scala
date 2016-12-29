package com.young.scala.sqlparser.mongo2sql

import com.mongodb.client.MongoDatabase
import org.apache.calcite.schema.impl.AbstractSchema
import org.apache.calcite.schema.Table

/**
  * Created by yangyong3 on 2016/12/29.
  */
class MongoSchema(database:MongoDatabase) extends AbstractSchema {

  override def getTableMap(): java.util.Map[String, Table] = {
    val it = database.listCollections().iterator()
    while(it.hasNext)
      println(it.next())
    null
  }
}
