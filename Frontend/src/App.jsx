import './App.css'
import {HashRouter as Router, Route, Routes} from 'react-router-dom'
import Home from './pages/Home'
import Icons from './pages/Icons'

function App() {

  return (
    <Router>
    <Routes>
      <Route path='/' element={<Icons />} />
    </Routes>
    </Router>
  )
}

export default App
