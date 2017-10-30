package com.hutatar

import org.scalatest.{FlatSpec, FunSuite}

class OptionalsTest extends FlatSpec {

  "create from null" should "be empty" in {
    def opt1 = Option[String](null)
    assert(opt1.isEmpty)
  }

  "create from val" should "be defined" in {
    def opt2 = Option[String]("test")
    assert(opt2.isDefined)
  }

  "create from val2" should "return string" in {
    def opt3 = Option[String]("test")
    assert(opt3.get == "test")
  }

  "filter1" should "return" in {
    def opt3 = Option[String]("test")
    opt3.filter(s => s.contains("test")).map(s => s).foreach(println(_))
  }


}
