import { useContext, useState } from "react";
import { Alert, Button, Form, Table } from "react-bootstrap";
import cookie from "react-cookies";
import { MyCartContext, MyUserContext } from "../../configs/MyContexts";
import { Link, useNavigate } from "react-router-dom";
import { authApis, endpoints } from "../../configs/Apis";

const Cart = () => {
    const [cart, setCart] = useState(cookie.load('cart') || {});
    const [showJourneyInput, setShowJourneyInput] = useState(false);
    const [journeyName, setJourneyName] = useState("");
    const user = useContext(MyUserContext);
    const [, cartDispatch] = useContext(MyCartContext);
    const nav = useNavigate();

    // Xóa sản phẩm
    const removeItem = (id) => {
        const newCart = { ...cart };
        delete newCart[id];
        setCart(newCart);
        cookie.save("cart", newCart);
    };

    // Tăng/giảm số lượng
    const updateQuantity = (id, delta) => {
        const newCart = { ...cart };
        if (newCart[id]) {
            newCart[id].quantity += delta;
            if (newCart[id].quantity <= 0) {
                delete newCart[id];
            }
        }
        setCart(newCart);
        cookie.save("cart", newCart);
    };

    const handleCheckoutClick = () => {
        setShowJourneyInput(true);
    };

    const confirmCheckout = async () => {
        if (!journeyName.trim()) {
            alert("Vui lòng nhập mã hành trình!");
            return;
        }

        try {
            // 🔎 Kiểm tra hành trình có tồn tại không (dùng query param)
            let checkRes = await authApis().get(`${endpoints['journey']}?journeyName=${journeyName}`);

            // Nếu tồn tại thì mới thanh toán
            const payload = {
                userId: user.id,
                journeyName: journeyName,
                items: Object.values(cart)
            };

            let res = await authApis().post(endpoints['cart'], payload);
            if (res.status === 200) {
                cookie.remove('cart');
                setCart({});
                cartDispatch({ "Type": "paid" });
                alert("Thanh toán thành công!");
                nav("/");
            }
        } catch (err) {
            console.error(err);
            alert("Không tìm thấy hành trình!");
        }
    };

    const totalQuantity = Object.values(cart).reduce((sum, c) => sum + c.quantity, 0);
    const totalPrice = Object.values(cart).reduce((sum, c) => sum + c.price * c.quantity, 0);

    return (
        <>
            <h1>GIỎ HÀNG</h1>

            {Object.keys(cart).length === 0 ? (
                <Alert>Không có sản phẩm nào trong giỏ hàng</Alert>
            ) : (
                <>
                    <Table striped bordered hover>
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Tên sản phẩm</th>
                                <th>Đơn giá</th>
                                <th>Số lượng</th>
                                <th>Hành động</th>
                            </tr>
                        </thead>
                        <tbody>
                            {Object.values(cart).map(c => (
                                <tr key={c.id}>
                                    <td>{c.id}</td>
                                    <td>{c.name}</td>
                                    <td>{c.price.toLocaleString()} VND</td>
                                    <td>
                                        <Button
                                            size="sm"
                                            variant="secondary"
                                            onClick={() => updateQuantity(c.id, -1)}
                                        >-</Button>{" "}
                                        <span className="mx-2">{c.quantity}</span>
                                        <Button
                                            size="sm"
                                            variant="secondary"
                                            onClick={() => updateQuantity(c.id, 1)}
                                        >+</Button>
                                    </td>
                                    <td>
                                        <Button variant="danger" onClick={() => removeItem(c.id)}>
                                            &times;
                                        </Button>
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                        <tfoot>
                            <tr>
                                <td colSpan="3" className="text-end fw-bold">Tổng cộng:</td>
                                <td className="fw-bold">{totalQuantity} sản phẩm</td>
                                <td className="fw-bold text-danger">
                                    {totalPrice.toLocaleString()} VND{" "}
                                    {user && !showJourneyInput && (
                                        <Button onClick={handleCheckoutClick} className="btn btn-danger ms-2">
                                            Thanh toán
                                        </Button>
                                    )}
                                </td>
                            </tr>
                        </tfoot>
                    </Table>

                    {showJourneyInput && (
                        <div className="border rounded p-3 mt-2">
                            <Form.Group>
                                <Form.Label>Nhập mã hành trình</Form.Label>
                                <Form.Control
                                    type="text"
                                    placeholder="VD: JRN000"
                                    value={journeyName}
                                    onChange={(e) => setJourneyName(e.target.value)}
                                />
                            </Form.Group>
                            <div className="mt-2 d-flex gap-2">
                                <Button variant="success" onClick={confirmCheckout}>Xác nhận thanh toán</Button>
                                <Button variant="secondary" onClick={() => setShowJourneyInput(false)}>Hủy</Button>
                            </div>
                        </div>
                    )}

                    {user === null ? (
                        <Alert variant="warning" className="mt-2">
                            Vui lòng <Link to="/login?next=/cart">đăng nhập</Link> để thanh toán
                        </Alert>
                    ) : (
                        <div className="mt-2">
                            <Button as={Link} to="/menu" variant="secondary">
                                Quay lại menu
                            </Button>
                        </div>
                    )}
                </>
            )}
        </>
    )
}

export default Cart;
