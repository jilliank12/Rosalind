package util

/**
  * Created by Jilliankim on 4/4/16.
  */

object Util {

	def readData(args: String): Vector[(String, String)] = {

		def process(rawData: String): (String, String) = {
			val lines = rawData.split("\n")
			val name = lines(0).substring(1)
			val data = lines.slice(1, lines.length).mkString("")
			(name, data)
		}

		val pattern = "(?s)>([^\\n]*)\\n([^>]*)".r

		val matching = pattern.findAllIn(args)
		matching.foldLeft(Vector[(String, String)]())((acc, next) => acc :+ process(next))

	}

	def readFastaFile(filename: String): Vector[(String, String)] = {
		val rawData = io.Source.fromFile(filename).mkString
		readData(rawData)

	}
}
