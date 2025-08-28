import { BrowserRouter, Route, Routes } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import Header from "./components/layouts/Header";
import Footer from "./components/layouts/Footer";
import Home from "./components/pages/Home";
import Register from "./components/pages/Register";
import Login from "./components/pages/Login";
import Menu from "./components/pages/Menu";
import TrackJourney from "./components/pages/TrackJourney";
import { MyCartContext, MyDispatcherContext, MyUserContext } from "./configs/MyContexts";
import { useEffect, useReducer } from "react";
import MyUserReducer from "./reducers/MyUserReducer";
import ServiceRegistration from "./components/pages/ServiceRegistration";
import cookie from "react-cookies";
import { authApis, endpoints } from "./configs/Apis";
import History from "./components/Passenger/History";
import MyServiceOrders from "./components/Passenger/MyServiceOrders";
import MostOrderedStatistic from "./components/Staff/MostOrderedStatistic";
import Statistic from "./components/Staff/Statistic";
import MyProfile from "./components/pages/MyProfile";
import MyCartReducer from "./reducers/MyCartReducer";
import Cart from "./components/pages/Cart";
import TotalAmountStatistic from "./components/Staff/TotalAmountStatistic";
import MyFoodOrders from "./components/Passenger/MyFoodOrders";
import FoodDetail from "./components/pages/FoodDetail";
import FoodUpdate from "./components/Staff/FoodUpdate";

const getCartTotalFromCookie = () => {
  const cartCookie = cookie.load('cart') || {};
  return Object.values(cartCookie).reduce((sum, c) => sum + c.quantity, 0);
};

const App = () => {
  const [user, dispatch] = useReducer(MyUserReducer, null);
  const [cart, cartDispatch] = useReducer(MyCartReducer, getCartTotalFromCookie());

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
        <MyCartContext.Provider value={[cart, cartDispatch]}>
          <BrowserRouter>
            <Header />

            <Routes>
              <Route path="/" element={<Home />} />
              <Route path="/register" element={<Register />} />
              <Route path="/login" element={<Login />} />
              <Route path="/menu" element={<Menu />} />
              <Route path="/track-journey" element={<TrackJourney />} />
              <Route path="/service-registration/:stationId" element={<ServiceRegistration />} />
              <Route path="/history" element={<History />} />
              <Route path="/my-service/:userId" element={<MyServiceOrders />} />
              <Route path="/my-food/:userId" element={<MyFoodOrders />} />
              <Route path="/food-detail/:foodId" element={<FoodDetail />} />
              <Route path="/update-food/:foodId" element={<FoodUpdate />} />
              <Route path="/my-profile" element={<MyProfile />} />
              <Route path="/cart" element={<Cart />} />

              {/* Thống kê */}
              <Route path="/statistic" element={<Statistic />} />
              <Route path="/most-ordered-statistic" element={<MostOrderedStatistic />} />
              <Route path="/total-amount-statistic" element={<TotalAmountStatistic />} />
            </Routes>

            <Footer />
          </BrowserRouter>
        </MyCartContext.Provider>
      </MyDispatcherContext.Provider>
    </MyUserContext.Provider>
  );

}

export default App;