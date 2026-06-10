import { useState, useEffect, useRef } from 'react'
import { getPlayers, createMatch, addPlayer } from '../api'
import type { Player } from '../types'

function calcEloPreview(p1: Player, p2: Player) {
  const k1 = p1.matchesPlayed > 5 ? 30 : 15
  const k2 = p2.matchesPlayed > 5 ? 30 : 15
  const e1 = 1 / (1 + Math.pow(10, (p2.elo - p1.elo) / 400))
  const e2 = 1 / (1 + Math.pow(10, (p1.elo - p2.elo) / 400))
  return {
    p1wins: {
      p1: Math.round(p1.elo + k1 * (1 - e1)),
      p2: Math.round(p2.elo + k2 * (0 - e2)),
    },
    p2wins: {
      p1: Math.round(p1.elo + k1 * (0 - e1)),
      p2: Math.round(p2.elo + k2 * (1 - e2)),
    },
  }
}

function Delta({ current, next }: { current: number; next: number }) {
  const diff = next - current
  return (
    <span className={`text-sm font-semibold ${diff >= 0 ? 'text-green-400' : 'text-red-400'}`}>
      {diff >= 0 ? '+' : ''}{diff}
    </span>
  )
}

export default function RecordMatchPage() {
  const [players, setPlayers] = useState<Player[]>([])
  const [p1Id, setP1Id] = useState<number | ''>('')
  const [p2Id, setP2Id] = useState<number | ''>('')
  const [winnerId, setWinnerId] = useState<number | null>(null)
  const [submitting, setSubmitting] = useState(false)
  const [success, setSuccess] = useState(false)
  const [error, setError] = useState<string | null>(null)
  const [showAddPlayer, setShowAddPlayer] = useState(false)
  const [newPlayerName, setNewPlayerName] = useState('')
  const [addingPlayer, setAddingPlayer] = useState(false)
  const [addPlayerError, setAddPlayerError] = useState<string | null>(null)
  const addPlayerInputRef = useRef<HTMLInputElement>(null)

  useEffect(() => {
    getPlayers().then(setPlayers).catch(() => {})
  }, [])

  useEffect(() => {
    if (showAddPlayer) setTimeout(() => addPlayerInputRef.current?.focus(), 50)
  }, [showAddPlayer])

  async function submitNewPlayer(e: React.FormEvent) {
    e.preventDefault()
    const name = newPlayerName.trim()
    if (!name) return
    setAddingPlayer(true)
    setAddPlayerError(null)
    try {
      await addPlayer(name)
      const updated = await getPlayers()
      setPlayers(updated)
      const created = updated.find(p => p.name === name)
      if (created) {
        if (!p1Id) setP1Id(created.id)
        else if (!p2Id) setP2Id(created.id)
      }
      setNewPlayerName('')
      setShowAddPlayer(false)
    } catch {
      setAddPlayerError('Failed to add player')
    } finally {
      setAddingPlayer(false)
    }
  }

  const p1 = players.find(p => p.id === p1Id) ?? null
  const p2 = players.find(p => p.id === p2Id) ?? null
  const preview = p1 && p2 ? calcEloPreview(p1, p2) : null

  async function submit() {
    if (!p1Id || !p2Id || !winnerId) return
    setSubmitting(true)
    setError(null)
    try {
      await createMatch(Number(p1Id), Number(p2Id), winnerId)
      setSuccess(true)
      setP1Id('')
      setP2Id('')
      setWinnerId(null)
      setTimeout(() => setSuccess(false), 4000)
    } catch {
      setError('Failed to record match. Is the backend running?')
    } finally {
      setSubmitting(false)
    }
  }

  return (
    <div className="max-w-xl mx-auto">
      <h1 className="text-2xl font-bold mb-6">⚔️ Record Match</h1>

      {success && (
        <div className="mb-4 p-3 bg-green-500/20 border border-green-500/30 rounded-lg text-green-400 text-sm">
          ✓ Match recorded successfully!
        </div>
      )}
      {error && (
        <div className="mb-4 p-3 bg-red-500/20 border border-red-500/30 rounded-lg text-red-400 text-sm">
          {error}
        </div>
      )}

      <div className="bg-slate-800 rounded-xl border border-slate-700 p-6 space-y-6">

        {/* Player selectors */}
        <div className="grid grid-cols-[1fr_40px_1fr] gap-3 items-start">
          <div className="space-y-2">
            <label className="block text-xs font-semibold uppercase tracking-wider text-slate-400">
              Player 1
            </label>
            <select
              value={p1Id}
              onChange={e => {
                setP1Id(e.target.value ? Number(e.target.value) : '')
                setWinnerId(null)
              }}
              className="w-full bg-slate-700 border border-slate-600 rounded-lg px-3 py-2 text-sm focus:outline-none focus:border-green-500 transition-colors"
            >
              <option value="">Select...</option>
              {players.filter(p => p.id !== p2Id).map(p => (
                <option key={p.id} value={p.id}>{p.name}</option>
              ))}
            </select>
            {p1 && (
              <div className="p-3 bg-slate-700/60 rounded-lg border border-slate-600">
                <div className="text-2xl font-bold text-green-400">{Math.round(p1.elo)}</div>
                <div className="text-xs text-slate-400 mt-0.5">
                  {p1.wins}W · {p1.losses}L · {p1.matchesPlayed} games
                </div>
              </div>
            )}
          </div>

          <div className="pt-8 text-center text-slate-500 font-bold text-sm">VS</div>

          <div className="space-y-2">
            <label className="block text-xs font-semibold uppercase tracking-wider text-slate-400">
              Player 2
            </label>
            <select
              value={p2Id}
              onChange={e => {
                setP2Id(e.target.value ? Number(e.target.value) : '')
                setWinnerId(null)
              }}
              className="w-full bg-slate-700 border border-slate-600 rounded-lg px-3 py-2 text-sm focus:outline-none focus:border-green-500 transition-colors"
            >
              <option value="">Select...</option>
              {players.filter(p => p.id !== p1Id).map(p => (
                <option key={p.id} value={p.id}>{p.name}</option>
              ))}
            </select>
            {p2 && (
              <div className="p-3 bg-slate-700/60 rounded-lg border border-slate-600">
                <div className="text-2xl font-bold text-green-400">{Math.round(p2.elo)}</div>
                <div className="text-xs text-slate-400 mt-0.5">
                  {p2.wins}W · {p2.losses}L · {p2.matchesPlayed} games
                </div>
              </div>
            )}
          </div>
        </div>

        {/* Quick add player */}
        {!showAddPlayer ? (
          <button
            onClick={() => setShowAddPlayer(true)}
            className="w-full py-2 border border-dashed border-slate-600 hover:border-slate-500 rounded-lg text-sm text-slate-500 hover:text-slate-300 transition-colors"
          >
            + Add a new player
          </button>
        ) : (
          <form onSubmit={submitNewPlayer} className="border border-slate-600 rounded-lg p-4 space-y-3">
            <div className="text-xs font-semibold uppercase tracking-wider text-slate-400">New Player</div>
            {addPlayerError && (
              <div className="text-red-400 text-xs">{addPlayerError}</div>
            )}
            <div className="flex gap-2">
              <input
                ref={addPlayerInputRef}
                type="text"
                value={newPlayerName}
                onChange={e => setNewPlayerName(e.target.value)}
                placeholder="Player name..."
                className="flex-1 bg-slate-700 border border-slate-600 rounded-lg px-3 py-2 text-sm focus:outline-none focus:border-green-500 transition-colors"
              />
              <button
                type="submit"
                disabled={addingPlayer || !newPlayerName.trim()}
                className="px-4 py-2 bg-green-600 hover:bg-green-500 disabled:bg-slate-700 disabled:text-slate-500 disabled:cursor-not-allowed rounded-lg text-sm font-semibold transition-colors"
              >
                {addingPlayer ? 'Adding...' : 'Add'}
              </button>
              <button
                type="button"
                onClick={() => { setShowAddPlayer(false); setNewPlayerName(''); setAddPlayerError(null) }}
                className="px-3 py-2 bg-slate-700 hover:bg-slate-600 rounded-lg text-sm transition-colors text-slate-400"
              >
                Cancel
              </button>
            </div>
          </form>
        )}

        {/* ELO preview */}
        {preview && p1 && p2 && (
          <div className="rounded-lg border border-slate-700 overflow-hidden">
            <div className="bg-slate-700/50 px-4 py-2 text-xs font-semibold uppercase tracking-wider text-slate-400">
              ELO Preview
            </div>
            <div className="grid grid-cols-2 divide-x divide-slate-700 text-sm">
              <div className="p-4 space-y-1.5">
                <div className="text-xs text-slate-400 mb-2">If {p1.name} wins</div>
                <div className="flex justify-between">
                  <span className="text-slate-300">{p1.name}</span>
                  <span>{preview.p1wins.p1} <Delta current={p1.elo} next={preview.p1wins.p1} /></span>
                </div>
                <div className="flex justify-between">
                  <span className="text-slate-300">{p2.name}</span>
                  <span>{preview.p1wins.p2} <Delta current={p2.elo} next={preview.p1wins.p2} /></span>
                </div>
              </div>
              <div className="p-4 space-y-1.5">
                <div className="text-xs text-slate-400 mb-2">If {p2.name} wins</div>
                <div className="flex justify-between">
                  <span className="text-slate-300">{p1.name}</span>
                  <span>{preview.p2wins.p1} <Delta current={p1.elo} next={preview.p2wins.p1} /></span>
                </div>
                <div className="flex justify-between">
                  <span className="text-slate-300">{p2.name}</span>
                  <span>{preview.p2wins.p2} <Delta current={p2.elo} next={preview.p2wins.p2} /></span>
                </div>
              </div>
            </div>
          </div>
        )}

        {/* Winner selection */}
        {p1 && p2 && (
          <div className="space-y-3">
            <label className="block text-xs font-semibold uppercase tracking-wider text-slate-400">
              Winner
            </label>
            <div className="grid grid-cols-2 gap-3">
              {[p1, p2].map(p => (
                <button
                  key={p.id}
                  onClick={() => setWinnerId(p.id)}
                  className={`p-4 rounded-lg border-2 transition-all text-left ${
                    winnerId === p.id
                      ? 'border-green-500 bg-green-500/10'
                      : 'border-slate-600 bg-slate-700 hover:border-slate-500'
                  }`}
                >
                  <div className="font-semibold">{p.name}</div>
                  <div className="text-sm text-slate-400">{Math.round(p.elo)} ELO</div>
                  {winnerId === p.id && (
                    <div className="text-xs text-green-400 mt-1">✓ Selected</div>
                  )}
                </button>
              ))}
            </div>
          </div>
        )}

        <button
          onClick={submit}
          disabled={!p1Id || !p2Id || !winnerId || submitting}
          className="w-full py-3 bg-green-600 hover:bg-green-500 disabled:bg-slate-700 disabled:text-slate-500 disabled:cursor-not-allowed rounded-lg font-semibold transition-colors"
        >
          {submitting ? 'Recording...' : 'Record Match'}
        </button>
      </div>
    </div>
  )
}
