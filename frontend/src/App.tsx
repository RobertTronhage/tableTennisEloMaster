import { Routes, Route, Navigate } from 'react-router-dom'
import Layout from './components/Layout'
import LeaderboardPage from './pages/LeaderboardPage'
import RecordMatchPage from './pages/RecordMatchPage'
import PlayersPage from './pages/PlayersPage'
import MatchHistoryPage from './pages/MatchHistoryPage'

export default function App() {
  return (
    <Routes>
      <Route path="/" element={<Layout />}>
        <Route index element={<LeaderboardPage />} />
        <Route path="record" element={<RecordMatchPage />} />
        <Route path="players" element={<PlayersPage />} />
        <Route path="history" element={<MatchHistoryPage />} />
        <Route path="*" element={<Navigate to="/" replace />} />
      </Route>
    </Routes>
  )
}
