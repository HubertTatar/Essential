package com.hutatar

import org.scalatest.FlatSpec

class AdderTest extends FlatSpec {

  behavior of "AdderTest"

  it should "add" in {
    assert(new Adder(10).add(2) == 12)
  }

  "apply" should "add function" in {
    val a: Adder = new Adder(40)
    assert(a(2) == 42)
  }

  "companion object" should "create" in {
    val adder = Adder(12)
    assert(adder != null)
    assert(adder.amount == 12)
  }

  "person" should "compile" in {
    val b = Person("a", "b")
    print(b)
    assert(b != null)
  }

  "person" should "equal" in {
    assert(Person("a","b").equals(Person("a", "b")))
    assert(Person("a","b") == Person("a", "b"))
    assert(new Person("a","b").equals(new Person("a", "b")))
    assert(new Person("a","b") == new Person("a", "b"))
  }

  "vals" should "comapre" in {
    assert(1 != 2)
    assert(1 == 1)
    assert(1.equals(1))
  }


}
