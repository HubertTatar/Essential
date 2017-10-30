package com.hutatar.sdp.unification

class SumFunction extends Function2[Int, Int, Int] {
  override def apply(v1: Int, v2: Int) = v1 + v2
}

class FunctionLiterals {
  val sum = (a: Int, b: Int) => a + b

  def runOperation(f: (Int, Int) => Int, a: Int, b: Int): Int = f(a,b)
}

object FunctionLiterals {
  def main(args: Array[String]): Unit = {
    val obj = new FunctionLiterals
    println(s"sum 3 + 9 = ${obj.sum(3,9)}")
    println(obj.runOperation(obj.sum, 3,9))
    println(obj.runOperation(Math.max, 3,9))
  }
}
