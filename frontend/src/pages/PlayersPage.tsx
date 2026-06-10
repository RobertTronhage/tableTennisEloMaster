import { useState, useEffect, useRef } from 'react'
import { getPlayers, addPlayer } from '../api'
import type { Player } from '../types'

const AVATAR_COLORS = [
  'bg-green-600', 'bg-blue-600', 'bg-purple-600',
  'bg-pink-600', 'bg-teal-600', 'bg-orange-600', 'bg-indigo-600',
]

function PlayerCard({ player, colorIndex }: { player: Player; colorIndex: number }) {
  const winPct = player.matchesPlayed > 0
    ? Math.round((player.wins / player.matchesPlayed) * 100)
    : 0

  return (
    <div className="bg-slate-800 border border-slate-700 rounded-xl p-5 flex flex-col gap-4 hover:border-slate-600 transition-colors">
      <div className="flex items-center gap-3">
        <div className={`w-12 h-12 rounded-full flex items-center justify-center text-xl font-bold text-white flex-shrink-0 ${AVATAR_COLORS[colorIndex % AVATAR_COLORS.length]}`}>
          {player.name.charAt(0).toUpperCase()}
        </div>
        <div>
          <div className="font-semibold text-base">{player.name}</div>
          <div className="text-2xl font-bold text-green-400 leading-tight">{Math.round(player.elo)}</div>
        </div>
      </div>

      <div className="grid grid-cols-3 gap-2 text-center text-sm">
        <div className="bg-slate-700 rounded-lg py-2">
          <div className="font-bold text-green-400">{player.wins}</div>
          <div className="text-xs text-slate-400">Wins</div>
        </div>
        <div className="bg-slate-700 rounded-lg py-2">
          <div className="font-bold text-red-400">{player.losses}</div>
          <div className="text-xs text-slate-400">Losses</div>
        </div>
        <div className="bg-slate-700 rounded-lg py-2">
          <div className="font-bold text-slate-200">{winPct}%</div>
          <div className="text-xs text-slate-400">Win rate</div>
        </div>
      </div>

      <div className="text-xs text-slate-500">{player.matchesPlayed} games played</div>
    </div>
  )
}

export default function PlayersPage() {
  const [players, setPlayers] = useState<Player[]>([])
  const [loading, setLoading] = useState(true)
  const [showForm, setShowForm] = useState(false)
  const [newName, setNewName] = useState('')
  const [adding, setAdding] = useState(false)
  const [error, setError] = useState<string | null>(null)
  const inputRef = useRef<HTMLInputElement>(null)

  const load = () =>
    getPlayers()
      .then(setPlayers)
      .catch(() => {})
      .finally(() => setLoading(false))

  useEffect(() => { load() }, [])
  useEffect(() => {
    if (showForm) setTimeout(() => inputRef.current?.focus(), 50)
  }, [showForm])

  async function submit(e: React.FormEvent) {
    e.preventDefault()
    const name = newName.trim()
    if (!name) return
    setAdding(true)
    setError(null)
    try {
      await addPlayer(name)
      setNewName('')
      setShowForm(false)
      await load()
    } catch {
      setError('Failed to add player. Is the backend running?')
    } finally {
      setAdding(false)
    }
  }

  if (loading) {
    return <div className="text-center py-20 text-slate-400 animate-pulse">Loading...</div>
  }

  return (
    <div>
      <div className="flex items-center justify-between mb-6">
        <h1 className="text-2xl font-bold">👥 Players</h1>
        <button
          onClick={() => setShowForm(v => !v)}
          className="px-4 py-2 bg-green-600 hover:bg-green-500 rounded-lg text-sm font-semibold transition-colors"
        >
          {showForm ? '✕ Cancel' : '+ Add Player'}
        </button>
      </div>

      {showForm && (
        <form
          onSubmit={submit}
          className="mb-6 bg-slate-800 border border-slate-700 rounded-xl p-5"
        >
          <div className="text-sm font-semibold text-slate-300 mb-3">New Player</div>
          {error && <div className="mb-3 text-red-400 text-sm">{error}</div>}
          <div className="flex gap-3">
            <input
              ref={inputRef}
              type="text"
              value={newName}
              onChange={e => setNewName(e.target.value)}
              placeholder="Enter player name..."
              className="flex-1 bg-slate-700 border border-slate-600 rounded-lg px-3 py-2 text-sm focus:outline-none focus:border-green-500 transition-colors"
            />
            <button
              type="submit"
              disabled={adding || !newName.trim()}
              className="px-4 py-2 bg-green-600 hover:bg-green-500 disabled:bg-slate-700 disabled:text-slate-500 disabled:cursor-not-allowed rounded-lg text-sm font-semibold transition-colors"
            >
              {adding ? 'Adding...' : 'Add'}
            </button>
          </div>
        </form>
      )}

      {players.length === 0 ? (
        <div className="text-center py-20 text-slate-500">
          No players yet. Add the first one!
        </div>
      ) : (
        <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-4">
          {players.map((p, i) => (
            <PlayerCard key={p.id} player={p} colorIndex={i} />
          ))}
        </div>
      )}
    </div>
  )
}
