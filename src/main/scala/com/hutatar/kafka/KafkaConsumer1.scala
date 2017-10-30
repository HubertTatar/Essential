package com.hutatar.kafka

import org.apache.kafka.clients.consumer.{ConsumerRecords, KafkaConsumer}

import scala.collection.JavaConverters.asJavaCollection

//https://kafka.apache.org/documentation/#consumerconfigs
object KafkaConsumer1 extends KafkaConsumerProperties {
  def main(args: Array[String]): Unit = {
    val consumer = new KafkaConsumer[String, String](properties)
    consumer.subscribe(asJavaCollection(List("second_topic")))

    while(true) {
      val polled: ConsumerRecords[String, String] = consumer.poll(100)
      polled.forEach { it => println(s"${it.value()} ${it.key()} ${it.partition()} ${it.timestamp()}") }
    }
  }
}
