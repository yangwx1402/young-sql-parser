package com.young.scala.sqlparser.mongo2sql.table

import com.mongodb.client.MongoCollection
import org.apache.calcite.plan.RelOptTable
import org.apache.calcite.plan.RelOptTable.ToRelContext
import org.apache.calcite.rel.RelNode
import org.apache.calcite.schema.TranslatableTable
import org.bson.Document

/**
  * Created by yangyong3 on 2017/1/9.
  */
class TranslatableMongoTable(mongoCollection: MongoCollection[Document]) extends MongoTable(mongoCollection) with TranslatableTable{
  override def toRel(context: ToRelContext, relOptTable: RelOptTable): RelNode = ???
}
