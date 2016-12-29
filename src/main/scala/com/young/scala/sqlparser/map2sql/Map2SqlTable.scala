package com.young.scala.sqlparser.map2sql

import org.apache.calcite.DataContext
import org.apache.calcite.linq4j.{Enumerator, AbstractEnumerable, Enumerable}
import org.apache.calcite.rel.`type`.{RelDataType, RelDataTypeFactory}
import org.apache.calcite.schema.impl.AbstractTable
import org.apache.calcite.schema.{Statistic, ScannableTable}
import org.apache.calcite.schema.Schema.TableType
import org.apache.calcite.sql.`type`.{SqlTypeUtil, SqlTypeName}

/**
  * Created by yangyong3 on 2016/12/27.
  *
  * 根据文档中的指示，一般我们可以实现三种类型的Table：
  * a simple implementation of Table, using the ScannableTable interface, that enumerates all rows directly;
  * a more advanced implementation that implements FilterableTable, and can filter out rows according to simple predicates;
  * advanced implementation of Table, using TranslatableTable, that translates to relational operators using planner rules.
  * 当使用ScannableTable的时候，我们只需要实现函数Enumerable<Object[]> scan(DataContext root);，该函数返回Enumerable对象，通过该对
  * 象可以一行行的获取这个Table的全部数据（也就意味着每次的查询都是扫描这个表的数据）；当使用FilterableTable的时候，我们需要实现函数
  * Enumerable<Object[]> scan(DataContext root, List<RexNode> filters );参数中多了filters数组，这个数据包含了针对这个表的过滤条件，
  * 这样我们根据过滤条件只返回过滤之后的行，减少上层进行其它运算的数据集；当使用TranslatableTable的时候，我们需要实现
  * RelNode toRel( RelOptTable.ToRelContext context, RelOptTable relOptTable);，该函数可以让我们根据上下文自己定义表扫描的物理执行计划，
  * 至于为什么不在返回一个Enumerable对象了，因为上面两种其实使用的是默认的执行计划，转换成EnumerableTableAccessRel算子，
  * 通过TranslatableTable我们可以实现自定义的算子，以及执行一些其他的rule，Kylin就是使用这个类型的Table实现查询。
  * 为了简单，我们这里只是使用了ScannableTable，每次做全表扫描。当然除了上面Table需要实现的接口，还需要实现Calcite中最底层Table定义的接口，
  * 当然有AbstractTable实现了一些默认的方案，我们只需要实现获取表中元数据的函数getRowType和获取数据的函数scan。
  */
class Map2SqlTable(table: Table) extends AbstractTable with ScannableTable {

  var dataType: RelDataType = null

  override def scan(root: DataContext): Enumerable[Array[Object]] = {
    new AbstractEnumerable[Array[Object]]() {
      override def enumerator(): Enumerator[Array[Object]] = new Map2SqlEnumerator(table.data, table.fileds)
    }
  }

  override def getRowType(typeFactory: RelDataTypeFactory): RelDataType = {
    if (dataType == null) {
      val fieldInfo = typeFactory.builder()
      for (column <- table.fileds) {
        var sqlType = typeFactory.createSqlType(
          SqlTypeName.VARCHAR)
        sqlType = SqlTypeUtil.addCharsetAndCollation(sqlType, typeFactory)
        fieldInfo.add(column.name, sqlType);
      }
      this.dataType = typeFactory.createStructType(fieldInfo)
    }
    this.dataType
  }
}
