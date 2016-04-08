package bio

import util.Util
import Util._

import scala.io.Source

/**
  * Created by Jilliankim on 4/1/16.
  *
  * Rosalind problem #25
  * http://rosalind.info/problems/long/
  */

object Long {

	def main(args: Array[String]): Unit = {

//		val rosalindString = List("ATTAGACCTG", "CCTGCCGGAA", "AGACCTGCCG", "GCCGGAATAC")
//		val Rosalind_56 = "ATTAGACCTG"
//		val Rosalind_57 = "CCTGCCGGAA"
//		val rosalind_58 = "AGACCTGCCG"
//		val Rosalind_59 = "GCCGGAATAC"

//		val stream : InputStream = getClass.getResourceAsStream("/25Dataset.txt")
//		val reads = readFastaFile(args(0)).map(_._2).toSet
//		val filename = "IdeaProjects/Rosalind/src/resources/25Dataset.txt"
		val reads = readFastaFile(args(0)).map(_._2).toSet
		println(assemble(reads))

	}

	//read file in fatsa format and map it by index and string
	def readFile(args: String) = {
		val filename = args
		def parse(lines: Iterator[String]): List[(String, String)] = {
			if (lines.isEmpty) return List()
			val name = lines.next.drop(1)
			val (seq, rest) = lines.span(_ (0) != '>')
			(name, seq.mkString) :: parse(rest)
		}
		parse(Source.fromFile(filename).getLines())
		//    val row = Source.fromFile(filename).getLines().map(_.split(">"))
		//    while (row.hasNext) {
		//    }
	}

	//assemble longest string
	def assemble(reads: Set[String]): String = {
		def prepend(read: String, genome: String): Option[String] = {
			println("read: " + read)
			val length = read.length
			val startIndex = math.ceil(length / 2.0).toInt - 1

			(startIndex to 0 by -1).find { i =>
				read.substring(i) == genome.substring(0, length - 1)
			} map { j =>
				read.substring(0, j) ++ genome
			}
		}

		def append(genome: String, read: String): Option[String] = {
			val readLength = read.length
			val genomeLength = genome.length
			val startIndex = math.floor(readLength / 2.0).toInt + 1

			(startIndex to readLength).find { i =>
				genome.substring(genomeLength - i) == read.substring(0, i)
			} map { j =>
				genome.substring(0, genomeLength - j) ++ read
			}
		}

		def phaseOne(reads: Set[String]): (String, Set[String]) = {
			def loop(genome: String, remaining: Set[String]): (String, Set[String]) = {
				val res = remaining.find { s =>
					append(genome, s).isDefined
				}

				res match {
					case Some(s) => loop(append(genome, s).get, remaining - s)
					case None => (genome, remaining)
				}
			}

			val longest = reads.maxBy(_.length)

			loop(longest, reads - longest)
		}

		def phaseTwo(genome: String, reads: Set[String]): String = {
			def loop(gnome: String, remaining: Set[String]): String = {
				val res = remaining.find { s =>
					prepend(s, genome).isDefined
				}

				res match {
					case Some(s) => loop(prepend(s, genome).get, remaining - s)
					case None => genome
				}
			}

			loop(genome, reads)

		}
		val (partialGenome, remaining) = phaseOne(reads)
		phaseTwo(partialGenome, remaining)

	}
}





