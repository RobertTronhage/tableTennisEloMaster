import { useState, useEffect } from 'react'
import { getMatches } from '../api'
import type { Match } from '../types'

export default function MatchHistoryPage() {
  const [matches, setMatches] = useState<Match[]>([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState<string | null>(null)

  useEffect(() => {
    getMatches()
      .then(m => setMatches([...m].reverse()))
      .catch(() => setError('Failed to load matches. Is the backend running?'))
      .finally(() => setLoading(false))
  }, [])

  if (loading) {
    return <div className="text-center py-20 text-slate-400 animate-pulse">Loading...</div>
  }

  if (error) {
    return <div className="text-center py-20 text-red-400">{error}</div>
  }

  return (
    <div>
      <div className="flex items-center justify-between mb-6">
        <h1 className="text-2xl font-bold">📜 Match History</h1>
        <span className="text-slate-400 text-sm">{matches.length} matches</span>
      </div>

      {matches.length === 0 ? (
        <div className="text-center py-20 text-slate-500">
          No matches recorded yet. Use Record Match to get started!
        </div>
      ) : (
        <div className="space-y-2">
          {matches.map((match, i) => {
            const winner = match.winningPlayer
            const loser = match.player1.id === winner.id ? match.player2 : match.player1
            const matchNumber = matches.length - i

            return (
              <div
                key={match.id}
                className="bg-slate-800 border border-slate-700 rounded-xl px-5 py-4 flex items-center gap-4 hover:border-slate-600 transition-colors"
              >
                <span className="text-slate-600 text-xs font-mono w-10 flex-shrink-0">
                  #{matchNumber}
                </span>

                <div className="flex-1 grid grid-cols-[1fr_auto_1fr] gap-4 items-center min-w-0">
                  {/* Winner */}
                  <div className="flex items-center gap-2.5 min-w-0">
                    <div className="w-9 h-9 rounded-full bg-green-700 flex items-center justify-center text-sm font-bold text-white flex-shrink-0">
                      {winner.name.charAt(0).toUpperCase()}
                    </div>
                    <div className="min-w-0">
                      <div className="font-semibold text-white truncate">{winner.name}</div>
                      <div className="text-xs text-green-400">Won</div>
                    </div>
                  </div>

                  <div className="text-slate-600 text-xs font-medium px-1">vs</div>

                  {/* Loser */}
                  <div className="flex items-center gap-2.5 justify-end min-w-0">
                    <div className="min-w-0 text-right">
                      <div className="font-semibold text-slate-400 truncate">{loser.name}</div>
                      <div className="text-xs text-red-400">Lost</div>
                    </div>
                    <div className="w-9 h-9 rounded-full bg-slate-700 flex items-center justify-center text-sm font-bold text-slate-400 flex-shrink-0">
                      {loser.name.charAt(0).toUpperCase()}
                    </div>
                  </div>
                </div>
              </div>
            )
          })}
        </div>
      )}
    </div>
  )
}
