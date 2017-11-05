package com.hutatar.patterns.oop

import org.scalatest.{FlatSpec, FunSuite}

class CarTest extends FlatSpec {

  "car factory" should "create car" in {
    println(Car("audi"))
    println(Car("skoda"))
    println(Car("dacia"))
    println(Car("fiat"))
    assertThrows[IllegalArgumentException]{
      Car("test")
    }
  }

}
