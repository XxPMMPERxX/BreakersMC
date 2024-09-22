package jp.pmmper.breakersmc.history

import jp.pmmper.breakersmc.account.AccountID
import jp.pmmper.breakersmc.game.GameID
import jp.pmmper.breakersmc.team.TeamID

data class JoinGameHistory(val account: AccountID, val game: GameID, val team: TeamID)
