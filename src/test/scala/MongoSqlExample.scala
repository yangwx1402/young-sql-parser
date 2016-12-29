import java.sql.{ResultSet, Statement, DriverManager, Connection}
import java.util.Properties

/**
  * Created by yangyong3 on 2016/12/28.
  */
object MongoSqlExample {

  def main(args: Array[String]) {
    val model: String = "D:\\young\\scala\\young-scala-example\\src\\main\\resources\\mongo2sql.json"
    Class.forName("org.apache.calcite.jdbc.Driver")
    val connection: Connection = DriverManager.getConnection("jdbc:calcite:model=" + model, new Properties)
    val meta = connection.getMetaData.getTables(null,null,null,null);
//    while(meta.next()){
//      System.out.println("Catalog : " + meta.getString(1) + ",Database : " + meta.getString(2) + ",Table : " + meta.getString(3));
//    }
    val stmt = connection.createStatement()
    val rs = stmt.executeQuery("select * from TEST where id=1")
    while(rs.next()){
      println(rs.getString("ID"))
    }
  }
}
