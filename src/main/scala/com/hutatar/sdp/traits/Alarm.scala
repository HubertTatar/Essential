package com.hutatar.sdp.traits

//traits as interfaces
trait Alarm {
  def trigger(): String
}

trait Notifier {
  val notificationMessage: String

  def printNotification(): Unit = println(notificationMessage)

  def clear()
}

class NotifierImpl(val notificationMessage: String) extends Notifier {
  override def clear(): Unit = println("cleared")
}

//traits as classes
trait Beeper {
  def beep(times: Int): Unit = {
    assert(times > 0)
    1 to times foreach(i => println(i))
  }
}

object BeeperRun {
  def main(args: Array[String]): Unit = {
    val a = new Beeper {}
    a.beep(5)
  }
}

//extending classes
abstract class Connector {
  def connect()
  def close()
}

trait ConnectorWithHelper extends Connector {
  def findDriver(): Unit = println("Find driver called..")
}

class SomeSqlConnector extends ConnectorWithHelper {
  override def connect(): Unit = println("Connected to some sql")

  override def close(): Unit = println("Closing connection to some sql")
}

//exteding traits
trait Ping {
  def ping(): Unit = println ("ping")
}

trait Pong {
  def pong(): Unit = println("pong")
}

trait PingPong extends Ping with Pong {
  def pingPong(): Unit = {
    ping()
    pong()
  }
}

object PPRunner {
  def main(args: Array[String]): Unit = {
    val a = new PingPong {}
    a.pingPong()
  }
}

object MixinRunner extends Ping with Pong {
  def main(args: Array[String]): Unit = {
    ping()
    pong()
  }
}

//composing
class Watch(brand: String, initialTime: Long) {
  def getTime(): Long = System.currentTimeMillis() - initialTime
}

object WatchUser {
  def main(args: Array[String]): Unit = {

    val expensiveWatch = new Watch("expensive watch", 2000L) with Alarm with Notifier {
      override def trigger(): String = "expensive - triggered"

      override val notificationMessage: String = "expensive - alarma!"

      override def clear(): Unit = println("expensive - clear")
    }

    val cheapWatch = new Watch("checp watch", 3000L) with Alarm {
      override def trigger() = "cheap - triggered"
    }

    expensiveWatch.trigger()
    expensiveWatch.printNotification()
    expensiveWatch.clear()

    cheapWatch.trigger()
  }
}

//composing complex traits
//enforcing class to be extended
/*object WatchRunner2 {
  def main(args: Array[String]): Unit = {
    //fails - not extending Connector
    val reallyExpensiveWatch = new Watch("really exp", 300L) with ConnectorWithHelper {
      override def connect(): Unit = println("connecting")

      override def close(): Unit = println("closing")
    }

    reallyExpensiveWatch.findDriver()
    reallyExpensiveWatch.connect()
    reallyExpensiveWatch.close()
  }
}*/

//composing with self types
//enforcing trait to be mixed
trait AlarmNotifier {
  //brings all the methods of Notifier to the scope of our new trait and it also requires that any class that mixes in AlarmNotifier should also mix in Notifier
  this: Notifier =>

  def trigger(): String
}

object SelfTypeUser {
  def main(args: Array[String]): Unit = {
    /*val watch = new Watch("watch", 100L) with AlarmNotifier {
      override def trigger() = ""
    }*/

    val watch2 = new Watch("watch2", 200L) with AlarmNotifier with Notifier {
      override def trigger(): String = ""

      override val notificationMessage: String = ""

      override def clear(): Unit = println("clear")
    }
  }
}

//clashing traits
trait FormalGreeting {
  def hello(): String
}

trait InformalGreeting {
  def hello(): String
}

class Greeter extends FormalGreeting with InformalGreeting {
  override def hello() = "hi"
}

object GreeterClash {
  def main(args: Array[String]): Unit = {
    val a = new Greeter()
    println(a.hello())
  }
}



