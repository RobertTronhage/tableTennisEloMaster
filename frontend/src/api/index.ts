import type { Player, Match, LeaderboardEntry } from '../types'

const BASE = 'http://localhost:8080'

async function request<T>(path: string, init?: RequestInit): Promise<T> {
  const res = await fetch(`${BASE}${path}`, {
    headers: { 'Content-Type': 'application/json' },
    ...init,
  })
  if (!res.ok) throw new Error(`Request failed: ${res.status}`)
  return res.json()
}

export const getPlayers = () => request<Player[]>('/players/getallplayers')

export const addPlayer = (name: string) =>
  request<string>('/players/addplayer', {
    method: 'POST',
    body: JSON.stringify({ name }),
  })

export const getMatches = () => request<Match[]>('/matches/getallmatches')

export const createMatch = (player1Id: number, player2Id: number, winnerId: number) =>
  request<string>('/matches/creatematch', {
    method: 'POST',
    body: JSON.stringify({ player1Id, player2Id, winnerId }),
  })

export const getLeaderboard = () => request<LeaderboardEntry[]>('/leaderboard/get')
