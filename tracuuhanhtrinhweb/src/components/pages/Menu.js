import { useEffect, useState, useRef, useContext } from "react";
import Apis, { endpoints, authApis } from "../../configs/Apis";
import MySpinner from "../layouts/MySpinner";
import { Button } from "react-bootstrap";
import { Link } from "react-router-dom";
import cookie from "react-cookies";
import { MyCartContext } from "../../configs/MyContexts";
import "../css/Menu.css";

const Menu = () => {
  const [foods, setFoods] = useState([]);
  const [page, setPage] = useState(0);
  const [totalPages, setTotalPages] = useState(1);
  const [loading, setLoading] = useState(false);
  const loaderRef = useRef(null);
  const [cart, cartDispatch] = useContext(MyCartContext);

  const loadFoods = async (pageNum) => {
    if (loading) return;
    setLoading(true);
    try {
      let res = await Apis.get(`${endpoints["foods"]}?page=${pageNum}&size=8`);
      const data = res.data;

      if (pageNum === 0) {
        setFoods(data.foods);
      } else {
        await new Promise((resolve) => setTimeout(resolve, 500));
        setFoods((prev) => [...prev, ...data.foods]);
      }

      setTotalPages(data.totalPages);
    } catch (err) {
      console.error("Lỗi load foods:", err);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    loadFoods(page);
  }, [page]);

  useEffect(() => {
    if (!loaderRef.current || page + 1 >= totalPages) return;

    const observer = new IntersectionObserver(
      (entries) => {
        if (entries[0].isIntersecting && !loading) {
          setPage((prev) => prev + 1);
        }
      },
      { threshold: 1.0 }
    );

    observer.observe(loaderRef.current);

    return () => {
      if (loaderRef.current) observer.unobserve(loaderRef.current);
    };
  }, [totalPages, page, loading]);

  const addToCart = async (f) => {
    if (f.quantity <= 0) return;

    let cartCookie = cookie.load("cart") || {};

    if (f.id in cartCookie) {
      cartCookie[f.id]['quantity']++;
    } else {
      cartCookie[f.id] = {
        id: f.id,
        name: f.name,
        price: f.price,
        quantity: 1
      };
    }

    cookie.save('cart', cartCookie);
    cartDispatch({ type: "update" });

    try {
      await authApis().post(endpoints["food-decrease"](f.id), null, {
        params: { quantityChange: 1 }
      });

      setFoods((prev) =>
        prev.map((food) =>
          food.id === f.id ? { ...food, quantity: food.quantity - 1 } : food
        )
      );
    } catch (err) {
      console.error("Lỗi giảm quantity:", err);
    }
  };

  return (
    <div className="menu-page">
      <video autoPlay muted loop playsInline id="bg-video">
        <source src="https://res.cloudinary.com/daupdu9bs/video/upload/v1753496569/background_uonsor.mp4" type="video/mp4" />
      </video>

      <h1>THỰC ĐƠN</h1>

      <div className="row">
        {foods.map(f => (
          <div key={f.id}>
            <div className="card">
              {f.image && <img src={f.image} alt={f.name} className="card-img-top" />}
              <div className="card-body d-flex flex-column">
                <h5 className="card-title">{f.name}</h5>
                <p className="card-text">{f.price?.toLocaleString()} VNĐ</p>
                <p className="card-text">Số lượng còn lại: {f.quantity}</p>
                <div className="d-flex gap-2 mt-auto">
                  <Button onClick={() => addToCart(f)} disabled={f.quantity <= 0} className="btn-add flex-fill">
                    Thêm
                  </Button>
                  <Link to={`/food-detail/${f.id}`} className="btn btn-primary flex-fill">Chi tiết</Link>
                </div>
              </div>
            </div>
          </div>
        ))}
      </div>

      {page + 1 <= totalPages && (
        <div ref={loaderRef} className="loader">
          {loading ? <MySpinner /> : "Đã hiển thị hết!"}
        </div>
      )}
    </div>
  );
};

export default Menu;
