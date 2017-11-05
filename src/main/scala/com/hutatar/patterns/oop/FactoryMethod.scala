package com.hutatar.patterns.oop

trait SimpleConnection {
  def getName(): String
  def executeQuery(query: String): Unit
}

class SimpleOracleConnection extends SimpleConnection {
  override def getName(): String = "SimpleOracleConnection"

  override def executeQuery(query: String): Unit = println(s"Executing $query Oracle way.")
}

class SimplePostgreConnection extends SimpleConnection {
  override def getName(): String = "SimplePostgreConnection"

  override def executeQuery(query: String): Unit = println(s"Executing $query Postgre way.")
}

abstract class DataBaseClient {
  def executeQuery(query: String): Unit = {
    val connection = connect()
    connection.executeQuery(query)
  }

  protected def connect(): SimpleConnection
}

class OracleClient extends DataBaseClient {
  override protected def connect() = new SimpleOracleConnection
}

class PostgreClient extends DataBaseClient {
  override protected def connect() = new SimplePostgreConnection
}

/*
  Pros:
  - object creation details are hidden
  - if instance creation process is changed we dont need to change code across app, only creators
  - object creation is defered to subclasses

  Cons:
  - badly designed may force to implement many methods(or mark many of them as not implemented)
  - may force methods that are incompatible
 */