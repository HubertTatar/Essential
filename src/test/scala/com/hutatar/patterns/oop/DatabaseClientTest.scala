package com.hutatar.patterns.oop

import org.scalatest.FlatSpec

class DatabaseClientTest extends FlatSpec {

  "oracle database client" should "run" in {
    val client = new DatabaseClient(new OracleConnectionFactory)
    client.executeQuery("select 1 drom dual")
  }

  "postgre database client" should "run" in {
    val client = new DatabaseClient(new PostgreConnectionFactory)
    client.executeQuery("select 1")
  }
}
