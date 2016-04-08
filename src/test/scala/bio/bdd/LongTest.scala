package bio.bdd

import org.scalatest._
import bio.Long

/**
  * Created by Jilliankim on 4/4/16.
  */
class LongTest extends FunSpec with GivenWhenThen {

	//check if file exists and opens correctly
	describe("open file") {
		it("should open a file in the designated path") {
			given("an url")
			val url = "/Users/Jilliankim/Desktop/25Dataset.txt"
			when("readFastaFile method is invoked")
			then("open a file in the path")

		}
	}

	//check if fasta file is read and parsed correctly
	describe("parse FASTA file") {
		it("should map header and partial genome correctly") {
			given("a FASTA file")
			when("readFastaFile methodo is invoked")
			then("return a vector tuple of Strings")
		}
	}

}
