import { useEffect, useState, useContext } from "react";
import { useParams, Link } from "react-router-dom";
import Apis, { endpoints } from "../../configs/Apis";
import MySpinner from "../layouts/MySpinner";
import cookie from "react-cookies";
import { MyCartContext, MyUserContext } from "../../configs/MyContexts";
import "../css/FoodDetail.css";

const FoodDetail = () => {
    const { foodId } = useParams();
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
        cartDispatch({ type: "update" });
    };

    if (loading) return <MySpinner />;
    if (!food) return <div className="text-center mt-5">Không tìm thấy món ăn!</div>;

    return (
        <div className="food-detail-page">

            
            <video autoPlay muted loop playsInline id="bg-video">
                <source src="https://res.cloudinary.com/daupdu9bs/video/upload/v1753496569/background_uonsor.mp4" type="video/mp4" />
            </video>

            <div className="food-detail-box">
                <div className="row g-4">
                    
                    <div className="col-md-5">
                        {food.image && (
                            <img
                                src={food.image}
                                alt={food.name}
                                className="img-fluid"
                            />
                        )}
                    </div>

                    
                    <div className="col-md-7 d-flex flex-column">
                        <h2>{food.name}</h2>
                        <p>{food.description}</p>
                        <h4>{food.price?.toLocaleString()} VNĐ</h4>
                        <p>Số lượng còn lại: <strong>{food.quantity}</strong></p>

                        <div className="mt-auto d-flex gap-2 flex-wrap">
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
