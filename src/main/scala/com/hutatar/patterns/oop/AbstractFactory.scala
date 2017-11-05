package com.hutatar.patterns.oop

trait DatabaseConnectionFactory {
  def connect(): SimpleConnection
}

class OracleConnectionFactory extends DatabaseConnectionFactory {
  override def connect(): SimpleConnection = new SimpleOracleConnection
}

class PostgreConnectionFactory extends DatabaseConnectionFactory {
  override def connect(): SimpleConnection = new SimplePostgreConnection
}

class DatabaseClient(connectionFactory: DatabaseConnectionFactory) {
  def executeQuery(query: String): Unit = {
    val connection = connectionFactory.connect()
    connection.executeQuery(query)
  }
}

/*
  Pros
  - object details creation are hidden
  - client is decuopled from concrete classes(intrface)

  Cons:
  - inconpatibility across many implementations
 */