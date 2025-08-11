import { BrowserRouter, Route, Routes } from "react-router-dom";
import Header from "./components/layouts/Header";
import Footer from "./components/layouts/Footer";
import Home from "./components/pages/Home";
import Register from "./components/pages/Register";

const App = () => {

  return (
    <BrowserRouter>
      <Header />

        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/register" element={<Register />} />
        </Routes>

      <Footer />
    </BrowserRouter>
  )

}

export default App;