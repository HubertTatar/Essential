package com.hutatar

import java.util.concurrent.TimeUnit

import org.scalatest.{FlatSpec, FunSuite}

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global


class FuturesTest extends FlatSpec {

  "sth" should "a" in {
    def f = Future {5}
    f foreach {
      msg => println(msg)
    }
  }

  "sth2" should "a" in {
    def f1 = Future {5}
    def f2 = Future {6}
    def f3 = Future {7}
    def f4 = Future {8}
    val a =  for {
      r1: Int <- f1
      r2: Int <- f2
      r3: Int <- f3
      r4: Int <- f4
    } yield {
      r1 + r2 + r3 + r4
    }
    a.foreach(s => print(s))
  }

  "sth3" should "s" in {
    def f1 = Future {
      TimeUnit.SECONDS.sleep(1)
      5
    }

    f1.onComplete(s => println(s))
    TimeUnit.SECONDS.sleep(2)
  }

  "sth4" should "a" in {
    val f1 = Future {
      TimeUnit.SECONDS.sleep(2)
      5
    }
    val f2: Future[Int] = Future {
      TimeUnit.SECONDS.sleep(1)
      6
    }
    val f3 = Future {
      TimeUnit.SECONDS.sleep(1)
      7
    }
    val f4 = Future {
      TimeUnit.SECONDS.sleep(1)
      8
    }
    val futures = List(f1,f2,f3,f4)
    val result = Future.reduceLeft(futures)(_ + _)

    TimeUnit.SECONDS.sleep(10)

    println(result)
  }
}
