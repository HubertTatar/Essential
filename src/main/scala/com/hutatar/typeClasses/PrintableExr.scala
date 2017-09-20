package com.hutatar.typeClasses

trait Printable[A] {
  def format(value: A): String
}

object PrintableInstanes {
  implicit val stringPrintable = new Printable[String] {
    override def format(value: String): String = value.toString
  }

  implicit val intPrintable = new Printable[Int] {
    override def format(value: Int): String = value.toString
  }

  implicit val catPrintable = new Printable[Cat] {
    override def format(cat: Cat): String = {
      val a = Printable.format(cat.name)
      val b = Printable.format(cat.age)
      val c = Printable.format(cat.color)
      s"Name ${a} Age ${b} Color ${c}"
    }
  }
}

object Printable {
  def format[A](value: A)(implicit p: Printable[A]): String = p.format( value)
  def print[A](value: A)(implicit p: Printable[A]): Unit = println(format(value))
}

object PrintableSyntax {
  implicit class PrintOps[A](value: A) {
    def format(implicit p: Printable[A]): String = p.format(value)
    def print(implicit p: Printable[A]): Unit = println(p.format(value))
  }
}

final case class Cat(name: String, age: Int, color: String)

object PrintableExr {
  import com.hutatar.typeClasses.PrintableInstanes._

  def main(args: Array[String]): Unit = {
    Printable.print(new Cat("raz", 22, "trze"))
  }
}

object PrintableExr2 {
  import PrintableSyntax._
  import com.hutatar.typeClasses.PrintableInstanes._

  def main(args: Array[String]): Unit = {
    new Cat("raz", 22, "trze").print
  }
}