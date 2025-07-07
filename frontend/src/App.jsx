import Pages from "./pages"
import { BrowserRouter, Route, Routes } from 'react-router-dom'

function App() {
  return (
    <>
      <BrowserRouter>
        <Routes>
          {Pages.map((pages, indx) => (<Route key={indx} path={pages.path} element={pages.component}/>))}
        </Routes>
      </BrowserRouter>
    </>
  )
}

export default App
