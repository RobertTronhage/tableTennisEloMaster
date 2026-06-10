export interface Player {
  id: number
  name: string
  elo: number
  matchesPlayed: number
  wins: number
  losses: number
}

export interface Match {
  id: number
  player1: Player
  player2: Player
  winningPlayer: Player
}

export interface LeaderboardEntry {
  playerId: number
  playerName: string
  elo: number
  matchesPlayed: number
  wins: number
  losses: number
  winLossRatio: number
}
