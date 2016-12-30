package com.young.scala.sqlparser.mongo2sql

import java.util

import com.mongodb.{MongoClient, MongoClientURI}
import org.apache.calcite.schema.{Schema, SchemaPlus}

/**
  * Created by yangyong3 on 2016/12/29.
  */
class MongoSchemaFactory extends org.apache.calcite.schema.SchemaFactory {
  override def create(parentSchema: SchemaPlus, name: String, operand: util.Map[String, AnyRef]): Schema = {
    val url = if (operand.get("url") == null) "localhost" else operand.get("url").toString
    val database = if (operand.get("database") == null) name else operand.get("database").toString
    val tableType = if (operand.get("type") == null) name else operand.get("type").toString
    val uri: MongoClientURI = new MongoClientURI(url)
    val client: MongoClient = new MongoClient(uri)
    val db = client.getDatabase(database)
    new MongoSchema(db,TableType.of(tableType))
  }
}
