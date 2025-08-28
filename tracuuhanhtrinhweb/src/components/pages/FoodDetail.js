import { useEffect, useState, useContext } from "react";
import { useParams, Link } from "react-router-dom";
import Apis, { endpoints } from "../../configs/Apis";
import MySpinner from "../layouts/MySpinner";
import cookie from "react-cookies";
import { MyCartContext, MyUserContext } from "../../configs/MyContexts";

const FoodDetail = () => {
    const { foodId } = useParams(); // lấy id từ URL
    const [food, setFood] = useState(null);
    const [loading, setLoading] = useState(true);
    const [cart, cartDispatch] = useContext(MyCartContext);
    const user = useContext(MyUserContext);

    useEffect(() => {
        const loadFood = async () => {
            try {
                let res = await Apis.get(endpoints["food-detail"](foodId));
                setFood(res.data);
            } catch (err) {
                console.error("Lỗi load food detail:", err);
            } finally {
                setLoading(false);
            }
        };

        loadFood();
    }, [foodId]);

    const addToCart = (f) => {
        let cart = cookie.load("cart") || {};

        if (f.id in cart) {
            cart[f.id]["quantity"]++;
        } else {
            cart[f.id] = {
                id: f.id,
                name: f.name,
                price: f.price,
                quantity: 1,
            };
        }

        cookie.save("cart", cart);
        console.info("Giỏ hàng:", cart);

        cartDispatch({ type: "update" });
    };

    if (loading) return <MySpinner />;

    if (!food) return <div className="text-center mt-5">Không tìm thấy món ăn!</div>;

    return (
        <div className="container my-4">
            <div className="card shadow p-3">
                <div className="row g-4">
                    {/* Hình ảnh */}
                    <div className="col-md-5">
                        {food.image && (
                            <img
                                src={food.image}
                                alt={food.name}
                                className="img-fluid rounded"
                                style={{ objectFit: "cover", width: "100%", height: "300px" }}
                            />
                        )}
                    </div>

                    {/* Nội dung */}
                    <div className="col-md-7 d-flex flex-column">
                        <h2>{food.name}</h2>
                        <p className="text-muted">{food.description}</p>
                        <h4 className="text-danger">
                            {food.price?.toLocaleString()} VNĐ
                        </h4>
                        <p>Số lượng còn lại: <strong>{food.quantity}</strong></p>

                        <div className="mt-auto d-flex gap-2">
                            <Link to="/menu" className="btn btn-secondary">Quay lại Menu</Link>
                            <button
                                className="btn btn-success"
                                onClick={() => addToCart(food)}
                            >
                                Thêm vào giỏ
                            </button>

                            {user?.role === "STAFF" && (
                                <Link to={`/update-food/${food.id}`} className="btn btn-warning">
                                    Chỉnh sửa
                                </Link>
                            )}
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default FoodDetail;
