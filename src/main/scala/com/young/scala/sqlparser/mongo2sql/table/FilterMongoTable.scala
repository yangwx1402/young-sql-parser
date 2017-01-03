package com.young.scala.sqlparser.mongo2sql.table

import java.util

import com.mongodb.client.MongoCollection
import com.young.scala.sqlparser.mongo2sql.converter.support.MongoArrayConverter
import com.young.scala.sqlparser.mongo2sql.enumerator.MongoScannEnumerator
import org.apache.calcite.DataContext
import org.apache.calcite.linq4j.{Enumerator, AbstractEnumerable, Enumerable}
import org.apache.calcite.rel.`type`.RelProtoDataType
import org.apache.calcite.rex.RexNode
import org.apache.calcite.schema.FilterableTable
import org.bson.Document

/**
  * Created by yangyong3 on 2016/12/30.
  */
class FilterMongoTable(mongoCollection: MongoCollection[Document], relProtoDataType: RelProtoDataType) extends MongoTable(mongoCollection, relProtoDataType) with FilterableTable {

  override def scan(root: DataContext, filters: util.List[RexNode]): Enumerable[Array[AnyRef]] = {
    for (i <- 0 until filters.size()) {
      println(filters.get(i).getKind)
      println(filters.get(i).getType)
    }
    new AbstractEnumerable[Array[Object]] {
      override def enumerator(): Enumerator[Array[Object]] = new MongoScannEnumerator[Array[Object]](mongoCollection, new MongoArrayConverter)
    }
  }
}
