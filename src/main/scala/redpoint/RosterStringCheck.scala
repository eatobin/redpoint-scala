package redpoint

object RosterStringCheck {

  // Remove the spaces between CSVs and any final \n
  def scrub(rawString: RawString): Scrubbed = {
    rawString
      .replaceAll(", ", ",")
      .stripLineEnd
  }

  // Ensure string is not nil, empty or only spaces. Returns a scrubbed string
  def nonBlankString(rawString: RawString): ErrorOrScrubbed = {
    if (rawString == null || rawString.trim.isEmpty) {
      Left("the roster string was null, empty or only spaces")
    } else {
      Right(scrub(rawString))
    }
  }

  // Given a valid scrubbed-string, return an array of Entity lists
  def makeEntityList(scrubbed: Scrubbed): EntityList = scrubbed.split('\n').toList.map(_.split(",").toList)

  // An Entity of lists >= 5?
  def validLengthString(errorOrScrubbed: ErrorOrScrubbed): ErrorOrScrubbed = {
    errorOrScrubbed match {
      case Right(scrubbed) =>
        if (makeEntityList(scrubbed).length < 5) {
          Left("roster string is not long enough")
        } else {
          Right(scrubbed)
        }
      case Left(error) =>
        Left(error)
    }
  }

  // Got an info string?
  def rosterInfoLinePresent(errorOrScrubbed: ErrorOrScrubbed): ErrorOrScrubbed = {
    errorOrScrubbed match {
      case Right(scrubbed) =>
        val mInfoList = makeEntityList(scrubbed).head.length
        if (mInfoList == 1) {
          Left("the roster info line is blank")
        } else {
          Right(scrubbed)
        }
      case Left(error) =>
        Left(error)
    }
  }

  // Got a roster name?
  def namePresent(errorOrScrubbed: ErrorOrScrubbed): ErrorOrScrubbed = {
    errorOrScrubbed match {
      case Right(scrubbed) =>
        val mRosterName = makeEntityList(scrubbed).head.head.length
        if (mRosterName == 0) {
          Left("the name value is missing")
        } else {
          Right(scrubbed)
        }
      case Left(error) =>
        Left(error)
    }
  }

  // Got a roster year?
  def yearPresent(errorOrScrubbed: ErrorOrScrubbed): ErrorOrScrubbed = {
    errorOrScrubbed match {
      case Right(scrubbed) =>
        val mInfoList = makeEntityList(scrubbed).head
        if (mInfoList.length != 2) {
          Left("the year value is missing")
        } else {
          Right(scrubbed)
        }
      case Left(error) =>
        Left(error)
    }
  }

  // Return the raw-info-string if the year text all digits
  def yearTextAllDigits(errorOrScrubbed: ErrorOrScrubbed): ErrorOrScrubbed = {
    errorOrScrubbed match {
      case Right(scrubbed) =>
        val mInfoList = makeEntityList(scrubbed).head
        if (!mInfoList.last.forall(c => c.isDigit)) {
          Left("the year value is not all digits")
        } else {
          Right(scrubbed)
        }
      case Left(error) =>
        Left(error)
    }
  }

  // Return the info-string if 1956 <= year <= 2056
  def yearInRange(errorOrScrubbed: ErrorOrScrubbed): ErrorOrScrubbed = {
    errorOrScrubbed match {
      case Right(scrubbed) =>
        val year = makeEntityList(scrubbed).head.last.toInt
        if (1956 > year || year > 2056) {
          Left("not 1956 <= year <= 2056")
        } else {
          Right(scrubbed)
        }
      case Left(error) =>
        Left(error)
    }
  }

  // Given a valid scrubbed-string, return an array of player strings
  def makePlayersList(scrubbed: Scrubbed): PlayersList = makeEntityList(scrubbed).tail

  // Remove name from player Array
  def removeName(player: PlayerAsList): PlayerAsList = player.head :: player.tail.tail


  // Returns all player vectors void of names - symbols only
  def makeOnlySymbols(players: PlayersList): PlayersListSymbols = {
    players.map(p => removeName(p))
  }

//  // All strings in the arrays are 6 chars long
//  def allSixChars(playerSymbols: PlayerAsListOfSymbols): Boolean = {
//    val count = playerSymbols.length
//    val six = playerSymbols.filter(p => p.length == 6)
//    count == 3 && six.length == 3
//  }
//
//  // All of the arrays only symbols
//  def allListsAllSix(playerArrays: PlayersAsListOfSymbolsLists): Boolean = {
//    playerArrays.forall(a => allSixChars(a))
//  }
//
//  // Test
//  def playersValid(errorOrScrubbed: ErrorOrScrubbed): ErrorOrScrubbed = {
//    errorOrScrubbed match {
//      case Right(r) =>
//        if (!allListsAllSix(makeOnlySymbols(makePlayersList(r)))) {
//          Left("the players sub-string is invalid")
//        } else {
//          Right(r)
//        }
//      case Left(l) =>
//        Left(l)
//    }
//  }
//
//  // Ensure that raw-string is scrubbed and fully valid
//  def scrubbedRosterString(rawString: RawString): ErrorOrScrubbed = {
//    var result = nonBlankString(rawString)
//    result = validLengthString(result)
//    result = rosterInfoLinePresent(result)
//    result = namePresent(result)
//    result = yearPresent(result)
//    result = yearTextAllDigits(result)
//    result = yearInRange(result)
//    playersValid(result)
//  }

}
