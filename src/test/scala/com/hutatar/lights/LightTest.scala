package com.hutatar.lights

import org.scalatest.FlatSpec


class LightTest extends FlatSpec {

  "light" should "match" in {

    val light = Red

    val a = light match {
      case Red => "Red"
    }


  }
}
