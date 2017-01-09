package com.young.scala.sqlparser.mongo2sql.table

import java.util

import com.mongodb.client.MongoCollection
import com.young.scala.sqlparser.mongo2sql.converter.support.MongoArrayConverter
import com.young.scala.sqlparser.mongo2sql.enumerator.MongoScannEnumerator
import org.apache.calcite.DataContext
import org.apache.calcite.linq4j.{AbstractEnumerable, Enumerable, Enumerator}
import org.apache.calcite.rex.{RexLiteral, RexCall, RexNode}
import org.apache.calcite.schema.FilterableTable
import org.bson.Document

/**
  * Created by yangyong3 on 2016/12/30.
  */
class FilterMongoTable(mongoCollection: MongoCollection[Document]) extends MongoTable(mongoCollection) with FilterableTable {

  override def scan(root: DataContext, filters: util.List[RexNode]): Enumerable[Array[AnyRef]] = {
    var call: RexCall = null
    var temp: RexCall = null;
    val find = new Document()
    println(filters)
    for (i <- 0 until filters.size()) {
      call = filters.get(i).asInstanceOf[RexCall]
      temp = call.getOperands.get(0).asInstanceOf[RexCall]
      val field = temp.getOperands.get(0).toString.replaceAll("\\$", "").toInt
      val value = call.getOperands.get(1).asInstanceOf[RexLiteral].toString
      find.append(fieldTypes(field)._1,getValueObject(field,value))
    }
    new AbstractEnumerable[Array[Object]] {
      override def enumerator(): Enumerator[Array[Object]] = new MongoScannEnumerator[Array[Object]](mongoCollection.find(find).iterator(), new MongoArrayConverter)
    }
  }
}
