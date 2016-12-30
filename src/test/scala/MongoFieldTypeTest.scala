import com.young.java.sqlparser.mongo2sql.MongoFieldType

/**
  * Created by yangyong3 on 2016/12/30.
  */
object MongoFieldTypeTest {

  def main(args: Array[String]) {
    println(MongoFieldType.of("int"))
  }
}
