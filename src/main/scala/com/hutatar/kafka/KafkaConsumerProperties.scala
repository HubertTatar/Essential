package com.hutatar.kafka

import java.util.Properties

import org.apache.kafka.common.serialization.{StringDeserializer, StringSerializer}

trait KafkaConsumerProperties {
  val properties = new Properties()
  properties.setProperty("bootstrap.servers", "127.0.0.1:9092")
  properties.setProperty("key.deserializer", classOf[StringDeserializer].getName)
  properties.setProperty("value.deserializer", classOf[StringDeserializer].getName)
  properties.setProperty("group.id", "test")
  properties.setProperty("enable.autocommit", "true")//it is like that by default
  properties.setProperty("auto.commit.interval.ms", "1000")

}

