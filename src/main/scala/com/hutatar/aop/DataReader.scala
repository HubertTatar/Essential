package com.hutatar.aop

import org.json4s._
import org.json4s.native.JsonMethods._

case class Person(firstName: String, lastName: String, age: Int)

trait DataReader {
  def readData(): List[Person]
  def readDataInefficently(): List[Person]
}

class DataReaderImpl extends DataReader {
  implicit val formats = DefaultFormats
  //it may be nedded to be copied to target
  private def readUntimed(): List[Person] = parse(StreamInput(getClass.getResourceAsStream("ppl.json"))).extract[List[Person]]

  override def readData() = readUntimed()

  override def readDataInefficently(): List[Person] = {
    (1 to 1000).foreach {
      case num => readUntimed()
    }
    readUntimed()
  }
}

class DataReaderImplv2 extends DataReader {
  implicit val formats = DefaultFormats
  //it may be nedded to be copied to target
  private def readUntimed(): List[Person] = parse(StreamInput(getClass.getResourceAsStream("ppl.json"))).extract[List[Person]]

  override def readData() = {
    val startMilis = System.currentTimeMillis()
    val result = readUntimed()
    val time = System.currentTimeMillis()
    println(time - startMilis)
    result
  }

  override def readDataInefficently(): List[Person] = {
    val startMilis = System.currentTimeMillis()
    (1 to 1000).foreach {
      case num => readUntimed()
    }
    val result = readUntimed()
    val time = System.currentTimeMillis()
    println(time - startMilis)
    result
  }
}

trait LoggingDataReader extends DataReader {
  abstract override def readData(): List[Person] = {
    val startMillis = System.currentTimeMillis()
    val result = super.readData()
    val time = System.currentTimeMillis() - startMillis
    System.err.println(s"readData took ${time} milliseconds.")
    result
  }

  abstract override def readDataInefficently(): List[Person] = {
    val startMillis = System.currentTimeMillis()
    val result = super.readDataInefficently()
    val time = System.currentTimeMillis() - startMillis
    System.err.println(s"readDataInefficiently took ${time} milliseconds.")
    result
  }
}

object DataReaderExample {
  def main(args: Array[String]): Unit = {
    val dataReader = new DataReaderImplv2
    println(s"Read data efficently: ${dataReader.readData()}")
    println(s"Read data inefficently: ${dataReader.readDataInefficently()}")
  }
}

object DataReaderAOPExample {
  def main(args: Array[String]): Unit = {
    val dataReader = new DataReaderImpl with LoggingDataReader
    System.out.println(s"I just read the following data efficiently: ${dataReader.readData()}")
    System.out.println(s"I just read the following data inefficiently: ${dataReader.readDataInefficently()}")
  }
}