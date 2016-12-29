import java.util.Date

/**
  * Created by yangyong3 on 2016/12/29.
  */
object TimestampTest {

  def main(args: Array[String]) {
    println(System.currentTimeMillis())
    println(new Date(1482740134))
    println(1000l*60*60*24*30)
  }
}
