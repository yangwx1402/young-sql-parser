package com.young.scala.sqlparser.map2sql

import java.util

import org.apache.calcite.schema.{Schema, SchemaPlus}

/**
  * Created by yangyong3 on 2016/12/27.
  */
class Map2SqlSchemaFactory extends org.apache.calcite.schema.SchemaFactory{
  /**
    * 创建schema,这里面的参数都是通过Calcite model json文件读取并传入的
    * @param parentSchema
    * @param name
    * @param operand
    * @return
    */
  override def create(parentSchema: SchemaPlus, name: String, operand: util.Map[String, AnyRef]): Schema = {
    println("sechme name is "+name)
    println("sechme params is "+operand)
    new Map2SqlSchema(name)
  }
}
