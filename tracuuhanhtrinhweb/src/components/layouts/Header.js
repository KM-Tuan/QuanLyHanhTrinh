import { useContext } from "react";
import { Link } from "react-router-dom";
import { MyDispatcherContext, MyUserContext } from "../../configs/MyContexts";
import { Button } from "react-bootstrap";

const Header = () => {
    const user = useContext(MyUserContext);
    const dispatch = useContext(MyDispatcherContext);



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
                    {user === null ?
                        <>
                            <Link to="/login" className="btn" style={{ backgroundColor: "#7C4585", color: "#131010", borderRadius: "8px", boxShadow: "0px 4px 6px rgba(0,0,0,0.2)", fontWeight: "bold" }}>Đăng nhập</Link>
                            <Link to="/register" className="btn" style={{ backgroundColor: "#674188", color: "#131010", borderRadius: "8px", boxShadow: "0px 4px 6px rgba(0,0,0,0.2)", fontWeight: "bold", textDecoration: "none" }}>Đăng ký</Link>
                        </>
                        :
                        <>
                            <Link to="/" className="nav-link text-danger">
                                <img src={user.avatar} width="40" className="rounded-circle"/>
                                Chào {user.firstName}
                            </Link>
                            <Button variant="danger" onClick={() => dispatch({"type": "logout"})}>Đăng xuất</Button>
                        </>}
                </div>
            </div>
        </header>
    );
}

export default Header;