package com.young.scala.sqlparser.mongo2sql.table

import com.mongodb.client.MongoCollection
import org.apache.calcite.schema.Table
import org.bson.Document

/**
  * Created by yangyong3 on 2017/1/3.
  */
object MongoTableFactory {

  def getTable(mongoCollection: MongoCollection[Document], tableType: TableType): Table = {
    tableType match {
      case SCAN => new ScanMongoTable(mongoCollection, null)
      case FILTER => new FilterMongoTable(mongoCollection, null)
      case GROUP => new GroupMongoTable(mongoCollection, null)
      case ORDER => new OrderMongoTable(mongoCollection, null)
    }
  }
}
