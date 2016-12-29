package com.young.scala.sqlparser.mongo2sql

import org.apache.calcite.linq4j.{QueryProvider, Queryable}
import org.apache.calcite.plan.RelOptTable
import org.apache.calcite.plan.RelOptTable.ToRelContext
import org.apache.calcite.rel.RelNode
import org.apache.calcite.rel.`type`.{RelDataType, RelDataTypeFactory}
import org.apache.calcite.schema.impl.AbstractTable
import org.apache.calcite.schema.{SchemaPlus, TranslatableTable}
/**
  * Created by yangyong3 on 2016/12/29.
  */
class MongoTable extends AbstractTable with TranslatableTable {
  override def toRel(context: ToRelContext, relOptTable: RelOptTable): RelNode = ???

  //override def asQueryable[T](queryProvider: QueryProvider, schema: SchemaPlus, tableName: String): Queryable[T] = ???

  override def getRowType(typeFactory: RelDataTypeFactory): RelDataType = ???
}
