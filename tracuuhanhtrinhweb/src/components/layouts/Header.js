import { Link } from "react-router-dom";

const Header = () => {
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
                <div className="d-flex gap-2">
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
                            textDecoration: "none", // bỏ gạch chân
                        }}
                    >
                        Đăng ký
                    </Link>
                </div>
            </div>
        </header>
    );
}

export default Header;