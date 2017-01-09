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
      case SCAN => new ScanMongoTable(mongoCollection)
      case FILTER => new FilterMongoTable(mongoCollection)
      case GROUP => new GroupMongoTable(mongoCollection)
      case ORDER => new OrderMongoTable(mongoCollection)
    }
  }
}
