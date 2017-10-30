package com.hutatar.kafka

import java.util.concurrent.TimeUnit

import org.apache.kafka.clients.producer.{KafkaProducer, Producer, ProducerRecord}

//https://www.phdata.io/try-with-resources-in-scala/
//https://stackoverflow.com/questions/25634455/simple-scala-pattern-for-using-try-with-resources-automatic-resource-manageme
//https://kafka.apache.org/documentation/#producerconfigs
//https://spark.apache.org/docs/2.1.0/streaming-kafka-0-10-integration.html
object KafkaProducer1 extends KafkaProducerProperties {

  def findPart(i: Int): _root_.scala.Predef.String = i match {
    case x if i % 3 == 0 =>  "3"
    case x if i % 2 == 0 =>  "2"
    case _               =>  "1"
  }

  def main(args: Array[String]): Unit = {

    val producer: Producer[String, String] = new KafkaProducer[String, String](properties)

    (1 to 100).map {
      i => {
        new ProducerRecord[String, String]("second_topic", findPart(i), s"message test $i")
      }
    }.map {
      TimeUnit.SECONDS.sleep(5)
      s => producer.send(s)
    }

    producer.close()
  }
}


