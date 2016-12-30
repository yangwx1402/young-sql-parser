import java.sql.{DriverManager, Statement, Connection}

/**
  * Created by yangyong3 on 2016/12/30.
  */
object CsvSqlExample {

  def main(args: Array[String]) {
    var connection: Connection = null
    var statement: Statement = null
    val model = "D:\\young\\scala\\young-scala-example\\src\\test\\resources\\model.json"
    try {
      val info = new java.util.Properties()
      info.put("model", model)
      connection = DriverManager.getConnection("jdbc:calcite:", info)
      statement = connection.createStatement()
      val sql = "select * from depts"
      val resultSet =
        statement.executeQuery(
          sql)
      val meta = resultSet.getMetaData
      println(meta.getColumnCount)
      while (resultSet.next()) {
        for (i <- 0 until meta.getColumnCount)
          print(resultSet.getObject(i + 1) + ",")
        println()
      }
    }
  }
}
