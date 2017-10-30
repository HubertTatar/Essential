package com.hutatar.kafka

import java.util.Properties

import org.apache.kafka.common.serialization.StringSerializer

trait KafkaProducerProperties {
  val properties = new Properties()
  properties.setProperty("bootstrap.servers", "127.0.0.1:9092")
  properties.setProperty("key.serializer", classOf[StringSerializer].getName)
  properties.setProperty("value.serializer", classOf[StringSerializer].getName)
  properties.setProperty("acks", "1")
  properties.setProperty("retries", "3")
  properties.setProperty("linger.ms", "1")
}
