//can't load in REPL because of package stmt

package redpoint

object RosterStringCheck {

  // Remove the spaces between CSVs and any final \n
  def scrub(rawString: RawString): Scrubbed = {
    rawString
      .stripLineEnd
      .replaceAll(", ", ",")
  }

  // Split string into lines
  def lines(scrubbed: Scrubbed): List[String] = scrubbed.split('\n').toList

  // Remove name from player Array
  def removeName(player: List[String]): List[String] = player.head :: player.tail.tail

  // Ensure string is not nil, empty or only spaces. Returns a scrubbed string
  def nonBlankString(rawString: RawString): Either[ErrorString, Scrubbed] = {
    if (rawString == null ||
      rawString
        .trim
        .isEmpty) {
      Left("the roster string was null, empty or only spaces")
    } else {
      Right(scrub(rawString))
    }
  }

  // A string of newlines >= 4?
  def validLengthString(eScrubbed: Either[ErrorString, Scrubbed]): Either[ErrorString, Scrubbed] = {
    eScrubbed match {
      case Right(r) =>
        if (r.filter(_ == '\n').length < 4) {
          Left("roster string is not long enough")
        } else {
          Right(r)
        }
      case Left(l) =>
        Left(l)
    }
  }

  // Got an info string?
  def rosterInfoLinePresent(eScrubbed: Either[ErrorString, Scrubbed]): Either[ErrorString, Scrubbed] = {
    eScrubbed match {
      case Right(r) =>
        val mInfoString = nonBlankString(lines(r).head)
        if (mInfoString.isLeft) {
          Left("the roster info line is blank")
        } else {
          Right(r)
        }
      case Left(l) =>
        Left(l)
    }
  }

  // Got a roster name?
  def namePresent(eScrubbed: Either[ErrorString, Scrubbed]): Either[ErrorString, Scrubbed] = {
    eScrubbed match {
      case Right(r) =>
        val mRosterName = nonBlankString(lines(r).head.split(",").head)
        if (mRosterName.isLeft) {
          Left("the name value is missing")
        } else {
          Right(r)
        }
      case Left(l) =>
        Left(l)
    }
  }

  // Got a roster year?
  def yearPresent(eScrubbed: Either[ErrorString, Scrubbed]): Either[ErrorString, Scrubbed] = {
    eScrubbed match {
      case Right(r) =>
        if (lines(r).head.split(",").length != 2) {
          Left("the year value is missing")
        } else {
          Right(r)
        }
      case Left(l) =>
        Left(l)
    }
  }

  // Return the raw-info-string if the year text all digits
  def yearTextAllDigits(eScrubbed: Either[ErrorString, Scrubbed]): Either[ErrorString, Scrubbed] = {
    eScrubbed match {
      case Right(r) =>
        if (!lines(r).head.split(",").last.forall(c => c.isDigit)) {
          Left("the year value is not all digits")
        } else {
          Right(r)
        }
      case Left(l) =>
        Left(l)
    }
  }

  // Return the info-string if 1956 <= year <= 2056
  def yearInRange(eScrubbed: Either[ErrorString, Scrubbed]): Either[ErrorString, Scrubbed] = {
    eScrubbed match {
      case Right(r) =>
        val year = lines(r).head.split(",").last.toInt
        if (1956 > year || year > 2056) {
          Left("not 1956 <= year <= 2056")
        } else {
          Right(r)
        }
      case Left(l) =>
        Left(l)
    }
  }

  // Given a valid scrubbed-string, return an array of player strings
  def makePlayerArrays(scrubbed: Scrubbed): List[String] = lines(scrubbed).tail

  // Returns all player vectors void of names - symbols only
  def makeOnlySymbols(playersArray: List[String]): List[List[String]] = {
    playersArray.map(_.split(",").toList).map(i => removeName(i))
  }

  // All strings in the arrays are 6 chars long
  def allSixChars(playerSymbols: List[String]): Boolean = {
    val count = playerSymbols.length
    val six = playerSymbols.filter(p => p.length == 6)
    count == 3 && six.length == 3
  }

  // All of the arrays only symbols
  def allArraysAllSix(playerArrays: List[List[String]]): Boolean = {
    val theBools = playerArrays.map(a => allSixChars(a))
    theBools.contains(false)
  }

  // Test
  def playersValid(eScrubbed: Either[ErrorString, Scrubbed]): Either[ErrorString, Scrubbed] = {
    eScrubbed match {
      case Right(r) =>
        if (allArraysAllSix(makeOnlySymbols(makePlayerArrays(r)))) {
          Left("the players sub-string is invalid")
        } else {
          Right(r)
        }
      case Left(l) =>
        Left(l)
    }
  }

  // Ensure that raw-string is scrubbed and fully valid
  def scrubbedRosterString(rawString: RawString): Either[ErrorString, Scrubbed] = {
    var result = nonBlankString(rawString)
    result = validLengthString(result)
    result = rosterInfoLinePresent(result)
    result = namePresent(result)
    result = yearPresent(result)
    result = yearTextAllDigits(result)
    result = yearInRange(result)
    playersValid(result)
  }

}
