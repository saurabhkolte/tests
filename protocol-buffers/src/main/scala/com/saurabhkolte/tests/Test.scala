package com.saurabhkolte.tests

import java.io.{FileOutputStream, _}
import java.nio.file.Files

import com.saurabhkolte.proto.Family.Family
import com.saurabhkolte.proto.Person.{Gender, Person}

import scala.io.Source
import scala.collection.mutable.ListBuffer
import scala.util.Random

object Test extends App{

  var people = ListBuffer[Person]()
  (1 to 1000000).foreach(i=>{
    val random = new Random()
    if(i==100) {
      people += Person(
        "Purushottam",
        "Lakshman",
        "Deshpande",
        random.alphanumeric.toString(),
        random.alphanumeric.toString(),
        Gender.MALE
      )
    }
    else {
      people += Person(
        random.alphanumeric.toString(),
        random.alphanumeric.toString(),
        random.alphanumeric.toString(),
        random.alphanumeric.toString(),
        random.alphanumeric.toString(),
        Gender.MALE
      )
    }
  })

  val family = Family(people)
  println(family.person.size)

  val path = System.getProperty("user.home")+File.separator+"Downloads"+File.separator+"family"
  val file = new File(path)
  val writer = new BufferedOutputStream(new FileOutputStream(file))
  family.writeTo(writer)
  writer.close()
  println("reading")
  val reader = new BufferedInputStream(new FileInputStream(file))
  Family.parseFrom(reader).person.foreach(p=>{
    if(p.firstName.equals("Purushottam")){
      println(p)
    }
  })
  reader.close()
  println("completed.")
}
