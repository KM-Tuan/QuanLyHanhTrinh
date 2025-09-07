import { useEffect, useState, useRef, useContext } from "react";
import Apis, { endpoints, authApis } from "../../configs/Apis";
import MySpinner from "../layouts/MySpinner";
import { Button } from "react-bootstrap";
import { Link } from "react-router-dom";
import cookie from "react-cookies";
import { MyCartContext } from "../../configs/MyContexts";

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
    if (f.quantity <= 0) return; // không thêm nếu quantity = 0

    let cart = cookie.load("cart") || {};

    if (f.id in cart) {
      cart[f.id]['quantity']++;
    } else {
      cart[f.id] = {
        "id": f.id,
        "name": f.name,
        "price": f.price,
        "quantity": 1
      }
    }

    cookie.save('cart', cart);
    cartDispatch({ "type": "update" });

    // Gọi API giảm quantity món ăn
    try {
      let res = await authApis().post(endpoints["food-decrease"](f.id), null, {
        params: { quantityChange: 1 }
      });

      // Cập nhật lại state foods để phản ánh quantity mới
      setFoods((prev) =>
        prev.map((food) =>
          food.id === f.id ? { ...food, quantity: food.quantity - 1 } : food
        )
      );
    } catch (err) {
      console.error("Lỗi giảm quantity:", err);
    }
  }

  return (
    <div className="container my-4">
      <h1 className="text-center mb-4">Menu</h1>

      <div className="row justify-content-center">
        {foods.map((f) => (
          <div
            key={f.id}
            className="col-md-3 col-sm-6 mb-4 d-flex justify-content-center"
          >
            <div
              className="card shadow-sm"
              style={{
                width: "100%",
                maxWidth: "250px",
                transition: "transform 0.3s, box-shadow 0.3s",
              }}
              onMouseEnter={(e) => {
                e.currentTarget.style.transform = "scale(1.05)";
                e.currentTarget.style.boxShadow = "0 6px 20px rgba(0,0,0,0.3)";
              }}
              onMouseLeave={(e) => {
                e.currentTarget.style.transform = "scale(1)";
                e.currentTarget.style.boxShadow = "0 4px 10px rgba(0,0,0,0.1)";
              }}
            >
              {f.image && (
                <img
                  src={f.image}
                  className="card-img-top"
                  alt={f.name}
                  style={{ height: "160px", objectFit: "cover" }}
                  loading="lazy"
                />
              )}
              <div className="card-body d-flex flex-column text-center">
                <h5 className="card-title">{f.name}</h5>
                <p className="card-text text-muted">
                  {f.price?.toLocaleString()} VNĐ
                </p>
                <p className="card-text text-muted">
                  Số lượng còn lại: {f.quantity}
                </p>

                <div className="d-flex gap-2 mt-auto">
                  <Button
                    onClick={() => addToCart(f)}
                    className="flex-fill"
                    disabled={f.quantity <= 0}
                    style={{ opacity: f.quantity <= 0 ? 0.5 : 1 }}
                  >
                    Thêm
                  </Button>
                  <Link to={`/food-detail/${f.id}`} className="btn btn-primary flex-fill">
                    Chi tiết
                  </Link>
                </div>
              </div>
            </div>
          </div>
        ))}
      </div>

      {page + 1 <= totalPages && (
        <div ref={loaderRef} className="text-center my-3">
          {loading ? <MySpinner /> : "Đã hiển thị hết!"}
        </div>
      )}
    </div>
  );
};

export default Menu;
