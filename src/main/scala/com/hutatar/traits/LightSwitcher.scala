package com.hutatar.traits

sealed trait Light {
  def switch(light: Light): Light =
    light match {
      case Red() => Green()
      case Green() => Blue()
      case Blue() => Red()
    }
}

final case class Red() extends Light
final case class Blue() extends Light
final case class Green() extends Light

object LightSwitcher {
  def switch(light: Light): Light =
    light match {
      case Red() => Green()
      case Green() => Blue()
      case Blue() => Red()
    }
}


