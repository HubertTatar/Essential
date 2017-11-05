package com.hutatar.patterns.oop

import org.scalatest.{FlatSpec, FunSuite}

class DataBaseClientTest extends FlatSpec {

  "DataBaseClient" should "run for Oracle" in {
    val dataBaseClient = new OracleClient
    dataBaseClient.executeQuery("select 1 from dual")
  }


  "DataBaseClient" should "run for Postgre" in {
    val dataBaseClient = new PostgreClient
    dataBaseClient.executeQuery("select 1")
  }
}
