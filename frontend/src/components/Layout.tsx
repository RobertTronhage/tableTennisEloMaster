import { Outlet, NavLink } from 'react-router-dom'

const navItems = [
  { to: '/', label: 'Leaderboard', icon: '🏆' },
  { to: '/record', label: 'Record Match', icon: '⚔️' },
  { to: '/players', label: 'Players', icon: '👥' },
  { to: '/history', label: 'Match History', icon: '📜' },
]

export default function Layout() {
  return (
    <div className="min-h-screen flex flex-col">
      <header className="bg-slate-800 border-b border-slate-700 sticky top-0 z-10">
        <div className="max-w-5xl mx-auto px-4 flex items-center gap-6 h-14">
          <span className="text-lg font-bold text-green-400 tracking-tight whitespace-nowrap">
            🏓 ELO Master
          </span>
          <nav className="flex gap-1 flex-wrap">
            {navItems.map(({ to, label, icon }) => (
              <NavLink
                key={to}
                to={to}
                end={to === '/'}
                className={({ isActive }) =>
                  `px-3 py-1.5 rounded-md text-sm font-medium transition-colors ${
                    isActive
                      ? 'bg-green-600 text-white'
                      : 'text-slate-400 hover:text-white hover:bg-slate-700'
                  }`
                }
              >
                <span className="mr-1">{icon}</span>
                {label}
              </NavLink>
            ))}
          </nav>
        </div>
      </header>
      <main className="flex-1 max-w-5xl mx-auto w-full px-4 py-8">
        <Outlet />
      </main>
    </div>
  )
}
