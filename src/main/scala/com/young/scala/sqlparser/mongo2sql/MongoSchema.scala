package com.young.scala.sqlparser.mongo2sql

import java.util

import com.mongodb.client.MongoDatabase
import org.apache.calcite.schema.impl.AbstractSchema
import org.apache.calcite.schema.Table

/**
  * Created by yangyong3 on 2016/12/29.
  */
class MongoSchema(database: MongoDatabase, tableType: TableType) extends AbstractSchema {

  override def getTableMap(): java.util.Map[String, Table] = {
    val map = new util.HashMap[String, Table]()
    val it = database.listCollectionNames().iterator()
    while (it.hasNext) {
      val collection = it.next()
      map.put(it.next(), new ScanMongoTable(database.getCollection(collection), null))
    }
    map
  }
}
