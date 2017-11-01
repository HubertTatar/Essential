package com.hutatar.polymorphism

import scala.collection.mutable

abstract class Item {
  def pack: String
}

class Fruit extends Item {
  override def pack = "Fruit"
}

class Drink extends Item {
  override def pack = "Drink"
}

object SubtypePolymorphism {
  def main(args: Array[String]): Unit = {
    val shoppingBasket: List[Item] = List(new Fruit, new Drink)
    shoppingBasket.foreach(i => println(i.pack))
  }
}

//parametric polymorphism = generics

//ad hoc polymorphism - self types
trait Adder[T] {
  def sum(a: T, b: T): T
}

object Adder {
  def sum[T: Adder](a: T, b: T): T = implicitly[Adder[T]].sum(a, b)

//  implicit val intAdder : Adder[Int] = new Adder[Int] {
//    override def sum(a: Int, b: Int) = a + b
//  }
  implicit val intAdder : Adder[Int] = (a: Int, b: Int) => a + b

//  implicit val stringAdder: Adder[String] = new Adder[String] {
//    override def sum(a: String, b: String) = s"$a concatenated with $b"
//  }
  implicit val stringAdder: Adder[String] = (a: String, b: String) => s"$a concatenated with $b"

//  implicit def numeric2adder[T: Numeric]: Adder[T] = new Adder[T] {
//    override def sum(a: T, b: T): T = implicitly[Numeric[T]].plus(a, b)
//  }
  implicit def numeric2adder[T: Numeric]: Adder[T] = (a: T, b: T) => implicitly[Numeric[T]].plus(a, b)
}

object AdderTest {

  import Adder._

  def main(args: Array[String]): Unit = {
    println(sum(1,2))
    println(sum(1.2,2.3))
    println(sum("asd","das"))

  }
}

//example
//trait Perister[T] {
//  def persist(data: T)
//}
/*
this: Database[T] =>. This allows us to access the methods of our included types directly as if they were methods of the trait that includes them
 */
trait Persister[T] {
  this: Database[T] with History with Mystery =>
    def persist(data: T): Unit = {
      println("calling persist")
      save(data)
      add()
    }
}

trait Database[T] {
  def save(data: T)
}

trait InMemoryDatabase[T] extends Database[T] {
  val db: mutable.MutableList[T] = mutable.MutableList.empty

  override def save(data: T): Unit = db += data
}

trait FileDataBase[T] extends Database[T] {
  override def save(data: T): Unit = println("Saving to file")
}

trait History {
  def add(): Unit = {
    println("added to history")
  }
}

trait Mystery {
  def add(): Unit = {
    println("mystery hapenning")
  }
}


class FilePersister[T] extends Persister[T] with FileDataBase[T] with History with Mystery {
  override def add(): Unit = super[History].add()
}
class MemoryPersister[T] extends Persister[T] with InMemoryDatabase[T] with History with Mystery {
  override def add(): Unit = super[Mystery].add()
}

object PersisterExmaple {
  def main(args: Array[String]): Unit = {
    val filePersister = new FilePersister[String]
    val memoryPerister = new MemoryPersister[Int]

    filePersister.persist("sth")
    filePersister.persist("sth else")

    memoryPerister.persist(123)
    memoryPerister.persist(234)
  }
}

//self types vs inheritance
trait DB {
  def connect(): Unit = {
    println("connected")
  }

  def drop(): Unit = {
    println("dropped")
  }

  def close(): Unit = {
    println("closed")
  }
}

trait UserDB extends DB {
  def createUser(name: String): Unit = {
    connect()
    try {
      println("created")
    } finally {
      close()
    }
  }

  def getUser(name: String): Unit = {
    connect()
    try {
      println("getting")
    } finally {
      close()
    }
  }
}


trait UserService extends UserDB {
  def bad() = {
    drop()
  }
}

//fix shit with self types
trait UserDb {
  this: DB =>

  def createUser(name: String): Unit = {
    connect()
    try {
      println("created")
    } finally {
      close()
    }
  }

  def getUser(name: String): Unit = {
    connect()
    try {
      println("getting")
    } finally {
      close()
    }
  }
}

trait UserService2 {
  this: UserDb =>
  def bad() = {
    //drop() cant do that
  }
}
