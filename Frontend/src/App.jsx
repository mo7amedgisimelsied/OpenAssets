import './App.css'
import {HashRouter as Router, Route, Routes} from 'react-router-dom'
import Home from './pages/Home'
import Icons from './pages/Icons'
import Models from './pages/Models'
import Vector from './pages/Vector'

function App() {

  return (
    <Router>
    <Routes>
      <Route path='/' element={<Home />} />
      <Route path='/icons' element={<Icons />} />
      <Route path='/3d' element={<Models />} />
      <Route path='/vector' element={<Vector />} />
      <Route path='/home' element={<Home />} />
    </Routes>
    </Router>
  )
}

export default App
