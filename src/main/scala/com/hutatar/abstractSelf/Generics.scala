package com.hutatar.abstractSelf

object Generics {
  def main(args: Array[String]): Unit = {
    val a = new Generics()
    a.test()
  }
}

class Generics extends Addder {
  def test(): Unit = {
    val a = new Container[Int](10)
    println(sum(1,2))
    println(a.compare(4))
  }
}

trait Addder {
  def sum[T](a: T, b: T)(implicit numeric: Numeric[T]): T = numeric.plus(a, b)
}

class Container[T](data: T) {
  def compare(other: T) = data.equals(other)
}

//abstract type way

trait ContainerAT {
  type T
  val data: T

  def compare(other: T) = data.equals(other)
}

class StringContainer(val data:String) extends ContainerAT {
  override type T = String
}

object AbstractTypesExamples {
  def main(args: Array[String]): Unit = {
    val strContainer = new StringContainer("some text")
    println(strContainer.compare("some text"))
  }
}

