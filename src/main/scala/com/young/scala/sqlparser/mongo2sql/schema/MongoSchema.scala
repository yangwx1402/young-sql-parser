package com.young.scala.sqlparser.mongo2sql.schema

import java.util

import com.mongodb.client.MongoDatabase

import com.young.scala.sqlparser.mongo2sql.table.{MongoTableFactory, TableType, ScanMongoTable}
import org.apache.calcite.schema.Table
import org.apache.calcite.schema.impl.AbstractSchema

/**
  * Created by yangyong3 on 2016/12/29.
  */
class MongoSchema(database: MongoDatabase, tableType: TableType) extends AbstractSchema {

  override def getTableMap(): java.util.Map[String, Table] = {
    val map = new util.HashMap[String, Table]()
    val it = database.listCollectionNames().iterator()
    while (it.hasNext) {
      val collection = it.next()
      map.put(collection, MongoTableFactory.getTable(database.getCollection(collection), tableType))
    }
    map
  }
}
