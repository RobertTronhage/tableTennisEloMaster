import { useState, useEffect } from 'react'
import { getLeaderboard } from '../api'
import type { LeaderboardEntry } from '../types'

const MEDALS = ['🥇', '🥈', '🥉']
const AVATAR_COLORS = [
  'bg-yellow-500', 'bg-slate-400', 'bg-amber-600',
  'bg-blue-500', 'bg-purple-500', 'bg-pink-500', 'bg-teal-500',
]

export default function LeaderboardPage() {
  const [entries, setEntries] = useState<LeaderboardEntry[]>([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState<string | null>(null)

  const load = async () => {
    setLoading(true)
    setError(null)
    try {
      setEntries(await getLeaderboard())
    } catch {
      setError('Failed to load leaderboard. Is the backend running?')
    } finally {
      setLoading(false)
    }
  }

  useEffect(() => { load() }, [])

  if (loading) {
    return <div className="text-center py-20 text-slate-400 animate-pulse">Loading...</div>
  }

  if (error) {
    return (
      <div className="text-center py-20">
        <div className="text-red-400 mb-4">{error}</div>
        <button onClick={load} className="px-4 py-2 bg-slate-700 hover:bg-slate-600 rounded-lg text-sm transition-colors">
          Retry
        </button>
      </div>
    )
  }

  return (
    <div>
      <div className="flex items-center justify-between mb-6">
        <h1 className="text-2xl font-bold">🏆 Leaderboard</h1>
        <button
          onClick={load}
          className="px-3 py-1.5 bg-slate-700 hover:bg-slate-600 rounded-lg text-sm transition-colors"
        >
          Refresh
        </button>
      </div>

      {entries.length === 0 ? (
        <div className="text-center py-20 text-slate-500">No players yet — add some on the Players page.</div>
      ) : (
        <div className="bg-slate-800 rounded-xl border border-slate-700 overflow-hidden">
          <table className="w-full">
            <thead>
              <tr className="border-b border-slate-700 text-xs font-semibold uppercase tracking-wider text-slate-400">
                <th className="px-4 py-3 text-left w-12">Rank</th>
                <th className="px-4 py-3 text-left">Player</th>
                <th className="px-4 py-3 text-right">ELO</th>
                <th className="px-4 py-3 text-right">W</th>
                <th className="px-4 py-3 text-right">L</th>
                <th className="px-4 py-3 text-right hidden sm:table-cell">Win %</th>
                <th className="px-4 py-3 text-right hidden md:table-cell">Games</th>
              </tr>
            </thead>
            <tbody>
              {entries.map((entry, i) => {
                const winPct = entry.matchesPlayed > 0
                  ? Math.round((entry.wins / entry.matchesPlayed) * 100)
                  : 0
                return (
                  <tr
                    key={entry.playerId}
                    className={`border-b border-slate-700/50 last:border-0 transition-colors hover:bg-slate-700/30 ${
                      i === 0 ? 'bg-yellow-500/5' : ''
                    }`}
                  >
                    <td className="px-4 py-4 text-center">
                      {i < 3
                        ? <span className="text-lg">{MEDALS[i]}</span>
                        : <span className="text-slate-500 font-mono text-sm">{i + 1}</span>
                      }
                    </td>
                    <td className="px-4 py-4">
                      <div className="flex items-center gap-3">
                        <div className={`w-9 h-9 rounded-full flex items-center justify-center text-sm font-bold text-white flex-shrink-0 ${AVATAR_COLORS[i % AVATAR_COLORS.length]}`}>
                          {entry.playerName.charAt(0).toUpperCase()}
                        </div>
                        <span className="font-medium">{entry.playerName}</span>
                      </div>
                    </td>
                    <td className="px-4 py-4 text-right">
                      <span className={`font-bold text-lg ${i === 0 ? 'text-yellow-400' : 'text-green-400'}`}>
                        {Math.round(entry.elo)}
                      </span>
                    </td>
                    <td className="px-4 py-4 text-right font-medium text-green-400">{entry.wins}</td>
                    <td className="px-4 py-4 text-right font-medium text-red-400">{entry.losses}</td>
                    <td className="px-4 py-4 text-right hidden sm:table-cell">
                      <div className="flex items-center justify-end gap-2">
                        <div className="w-16 bg-slate-700 rounded-full h-1.5 hidden lg:block">
                          <div
                            className="bg-green-500 h-1.5 rounded-full"
                            style={{ width: `${winPct}%` }}
                          />
                        </div>
                        <span className="text-slate-300 text-sm">{winPct}%</span>
                      </div>
                    </td>
                    <td className="px-4 py-4 text-right text-slate-400 text-sm hidden md:table-cell">
                      {entry.matchesPlayed}
                    </td>
                  </tr>
                )
              })}
            </tbody>
          </table>
        </div>
      )}
    </div>
  )
}
