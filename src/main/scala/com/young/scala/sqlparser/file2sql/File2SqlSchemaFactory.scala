package com.young.scala.sqlparser.file2sql

import java.util

import org.apache.calcite.schema.{Schema, SchemaPlus}
import org.apache.calcite.schema.SchemaFactory

/**
  * Created by yangyong3 on 2016/12/28.
  */
class File2SqlSchemaFactory extends SchemaFactory {
  override def create(parentSchema: SchemaPlus, name: String, operand: util.Map[String, AnyRef]): Schema = ???
}
