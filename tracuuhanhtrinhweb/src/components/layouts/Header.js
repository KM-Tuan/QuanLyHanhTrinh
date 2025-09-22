import { useContext, useState, useRef, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import { MyCartContext, MyDispatcherContext, MyUserContext } from "../../configs/MyContexts";
import { Button } from "react-bootstrap";
import "../css/Header.css";

const Header = () => {
    const user = useContext(MyUserContext);
    const dispatch = useContext(MyDispatcherContext);
    const [showDropdown, setShowDropdown] = useState(false);
    const dropdownRef = useRef(null);
    const [cart] = useContext(MyCartContext);
    const nav = useNavigate();

    useEffect(() => {
        setShowDropdown(false);
    }, [user]);

    useEffect(() => {
        const handleClickOutside = (event) => {
            if (dropdownRef.current && !dropdownRef.current.contains(event.target)) {
                setShowDropdown(false);
            }
        };
        document.addEventListener("mousedown", handleClickOutside);
        return () => document.removeEventListener("mousedown", handleClickOutside);
    }, []);

    const handleCartClick = () => {
        if (cart === 0) {
            nav("/menu");
        } else {
            nav("/cart");
        }
    };

    return (
        <header className="app-header">
            <div className="d-flex justify-content-between align-items-center">
                
                <Link to="/" className="app-logo">
                    KMT-Tech
                </Link>

                
                <div className="d-flex gap-2 align-items-center">
                    
                    <Button onClick={handleCartClick} className="cart-btn">
                        <i className="bi bi-cart-fill"></i>
                        {cart === 0 ? "Mua sắm" : "Thanh toán"}
                        {cart > 0 && <span className="badge bg-danger ms-1">{cart}</span>}
                    </Button>

                    {user === null ? (
                        <>
                            <Link to="/login" className="btn login-btn">
                                <i className="bi bi-box-arrow-in-right"></i> Đăng nhập
                            </Link>
                            <Link to="/register" className="btn register-btn">
                                <i className="bi bi-person-plus-fill"></i> Đăng ký
                            </Link>
                        </>
                    ) : (
                        <div className="position-relative" ref={dropdownRef}>
                            
                            <div className="user-box" onClick={() => setShowDropdown(!showDropdown)}>
                                <img src={user.avatar} width="40" height="40" className="rounded-circle" />
                                <span className="user-name">{user.firstName} {user.lastName}</span>
                                <i className={`bi bi-caret-${showDropdown ? "up" : "down"}-fill text-white`}></i>
                            </div>

                            
                            {showDropdown && (
                                <div className="position-absolute mt-2 py-2 bg-white rounded shadow user-dropdown">
                                    {user.role === "STAFF" && (
                                        <Link to="/statistic" className="dropdown-item" onClick={() => setShowDropdown(false)}>
                                            <i className="bi bi-bar-chart-fill"></i> Thống kê
                                        </Link>
                                    )}

                                    {user.role === "PASSENGER" && (
                                        <Link to="/history" className="dropdown-item" onClick={() => setShowDropdown(false)}>
                                            <i className="bi bi-clock-history"></i> Lịch sử
                                        </Link>
                                    )}

                                    <Link to="/my-profile" className="dropdown-item" onClick={() => setShowDropdown(false)}>
                                        <i className="bi bi-person-circle"></i> Thông tin
                                    </Link>
                                    <div className="dropdown-divider"></div>
                                    <Button
                                        variant="danger"
                                        size="sm"
                                        className="w-100 logout-btn"
                                        onClick={() => dispatch({ type: "logout" })}
                                    >
                                        <i className="bi bi-box-arrow-right"></i> Đăng xuất
                                    </Button>
                                </div>
                            )}
                        </div>
                    )}
                </div>
            </div>
        </header>
    );
};

export default Header;
