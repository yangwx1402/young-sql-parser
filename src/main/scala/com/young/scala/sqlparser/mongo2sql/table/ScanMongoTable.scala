package com.young.scala.sqlparser.mongo2sql.table

import com.mongodb.client.MongoCollection
import com.young.scala.sqlparser.mongo2sql.converter.support.MongoArrayConverter
import com.young.scala.sqlparser.mongo2sql.enumerator.MongoScannEnumerator
import org.apache.calcite.DataContext
import org.apache.calcite.linq4j.{AbstractEnumerable, Enumerable, Enumerator}
import org.apache.calcite.rel.`type`.RelProtoDataType
import org.apache.calcite.schema.ScannableTable
import org.bson.Document

/**
  * Created by yangyong3 on 2016/12/30.
  */
class ScanMongoTable(mongoCollection: MongoCollection[Document]) extends MongoTable(mongoCollection) with ScannableTable {
  override def scan(root: DataContext): Enumerable[Array[AnyRef]] = {
    new AbstractEnumerable[Array[Object]] {
      override def enumerator(): Enumerator[Array[Object]] = new MongoScannEnumerator[Array[Object]](mongoCollection.find().iterator(), new MongoArrayConverter)
    }
  }
}
