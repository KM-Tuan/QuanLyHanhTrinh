import { BrowserRouter, Route, Routes } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import Header from "./components/layouts/Header";
import Footer from "./components/layouts/Footer";
import Home from "./components/pages/Home";
import Register from "./components/pages/Register";
import Login from "./components/pages/Login";
import Menu from "./components/pages/Menu";
import TrackJourney from "./components/pages/TrackJourney";
import { MyDispatcherContext, MyUserContext } from "./configs/MyContexts";
import { useEffect, useReducer } from "react";
import MyUserReducer from "./reducers/MyUserReducer";
import ServiceRegistration from "./components/Passengers/ServiceRegistration";
import cookie from "react-cookies";
import { authApis, endpoints } from "./configs/Apis";


const App = () => {
  const [user, dispatch] = useReducer(MyUserReducer, null);

  useEffect(() => {
    const token = cookie.load('token');
    if (!token) return; // chưa login
    // Nếu có token → gọi API current-user để khôi phục user
    authApis().get(endpoints['current-user'])
      .then(res => {
        dispatch({ type: "login", payload: res.data });
      })
      .catch(err => {
        console.error("Không thể load user từ token:", err);
        cookie.remove('token'); // token không hợp lệ → xóa
      });
  }, []);

  return (
    <MyUserContext.Provider value={user}>
      <MyDispatcherContext.Provider value={dispatch}>
        <BrowserRouter>
          <Header />

          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/register" element={<Register />} />
            <Route path="/login" element={<Login />} />
            <Route path="/menu" element={<Menu />} />
            <Route path="/track-journey" element={<TrackJourney />} />
            <Route path="/service-registration/:serviceId" element={<ServiceRegistration />} />
          </Routes>

          <Footer />
        </BrowserRouter>
      </MyDispatcherContext.Provider>
    </MyUserContext.Provider>
  );

}

export default App;