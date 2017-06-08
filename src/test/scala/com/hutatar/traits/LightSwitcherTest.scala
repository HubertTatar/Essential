package com.hutatar.traits

import org.scalatest.{FunSpec, GivenWhenThen}

class LightSwitcherTest extends FunSpec with GivenWhenThen {

  describe("pattern matching in an external object") {
    Given("Light switcher")
      val light = LightSwitcher
    When("Blue")
      val res1 = light.switch(Blue())
    Then("Red")
      assert(res1 == Red())
    When("Red")
     val res2 = light.switch(Red())
    Then("Green")
      assert(res2 == Green())
    When("Green")
    val res3 = light.switch(Green())
    Then("Blue")
    assert(res3 == Blue())
  }

  describe("pattern matching trait") {
    When("Blue => Red")
    val res1 = Red().switch(Blue())
    Then("Red")
    assert(res1 == Red())
    When("Red")
    val res2 = Red().switch(Red())
    Then("Green")
    assert(res2 == Green())
    When("Green")
    val res3 = Red().switch(Green())
    Then("Blue")
    assert(res3 == Blue())
  }
}
