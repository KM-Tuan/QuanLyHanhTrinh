import { useContext, useState, useRef, useEffect } from "react";
import { Link } from "react-router-dom";
import { MyDispatcherContext, MyUserContext } from "../../configs/MyContexts";
import { Button } from "react-bootstrap";

const Header = () => {
    const user = useContext(MyUserContext);
    const dispatch = useContext(MyDispatcherContext);
    const [showDropdown, setShowDropdown] = useState(false);
    const dropdownRef = useRef(null);

    // Đóng dropdown khi click ra ngoài
    useEffect(() => {
        const handleClickOutside = (event) => {
            if (dropdownRef.current && !dropdownRef.current.contains(event.target)) {
                setShowDropdown(false);
            }
        };
        document.addEventListener("mousedown", handleClickOutside);
        return () => document.removeEventListener("mousedown", handleClickOutside);
    }, []);

    return (
        <header
            style={{
                backgroundColor: "#131010",
                padding: "10px 20px",
            }}
        >
            <div className="d-flex justify-content-between align-items-center">
                {/* Logo góc trái */}
                <Link
                    to="/"
                    style={{
                        color: "#C95792",
                        fontWeight: "bold",
                        fontSize: "1.5rem",
                        textShadow: "1px 1px 3px rgba(255, 255, 255, 0.3)",
                        textDecoration: "none",
                    }}
                >
                    KMT-Tech
                </Link>

                {/* Nút góc phải */}
                <div className="d-flex gap-2 align-items-center">
                    {user === null ? (
                        <>
                            <Link
                                to="/login"
                                className="btn"
                                style={{
                                    backgroundColor: "#7C4585",
                                    color: "#131010",
                                    borderRadius: "8px",
                                    boxShadow: "0px 4px 6px rgba(0,0,0,0.2)",
                                    fontWeight: "bold",
                                }}
                            >
                                Đăng nhập
                            </Link>
                            <Link
                                to="/register"
                                className="btn"
                                style={{
                                    backgroundColor: "#674188",
                                    color: "#131010",
                                    borderRadius: "8px",
                                    boxShadow: "0px 4px 6px rgba(0,0,0,0.2)",
                                    fontWeight: "bold",
                                    textDecoration: "none",
                                }}
                            >
                                Đăng ký
                            </Link>
                        </>
                    ) : (
                        <div className="position-relative" ref={dropdownRef}>
                            {/* Avatar + Name */}
                            <div
                                className="d-flex align-items-center p-2 rounded"
                                style={{
                                    backgroundColor: "#1f1f1f",
                                    boxShadow: "0 4px 12px rgba(0,0,0,0.3)",
                                    cursor: "pointer",
                                }}
                                onClick={() => setShowDropdown(!showDropdown)}
                            >
                                <img
                                    src={user.avatar}
                                    width="40"
                                    className="rounded-circle me-2"
                                />
                                <span style={{ color: "#fff", fontWeight: "bold" }}>
                                    {user.firstName} {user.lastName}
                                </span>
                            </div>

                            {/* Dropdown */}
                            {showDropdown && (
                                <div
                                    className="position-absolute mt-2 py-2 bg-white rounded shadow"
                                    style={{ right: 0, minWidth: "150px", zIndex: 1000 }}
                                >
                                    <Link
                                        to="/sub-home"
                                        className="dropdown-item"
                                        style={{ color: "#131010" }}
                                    >
                                        Lịch sử
                                    </Link>
                                    <Link
                                        to="/profile"
                                        className="dropdown-item"
                                        style={{ color: "#131010" }}
                                    >
                                        Thông tin
                                    </Link>
                                    <div className="dropdown-divider"></div>
                                    <Button
                                        variant="danger"
                                        size="sm"
                                        className="w-100"
                                        onClick={() => dispatch({ type: "logout" })}
                                    >
                                        Đăng xuất
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
