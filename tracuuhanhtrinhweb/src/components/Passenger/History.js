import React, { useContext } from "react";
import { Link } from "react-router-dom";
import { MyUserContext } from "../../configs/MyContexts";

const History = () => {
    const user = useContext(MyUserContext);

    const squareStyle = {
        width: "200px",
        height: "200px",
        backgroundColor: "#674188",
        color: "#fff",
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        fontSize: "3rem",
        borderRadius: "12px",
        boxShadow: "0 4px 10px rgba(0,0,0,0.3)",
        transition: "transform 0.2s, box-shadow 0.2s",
        cursor: "pointer",
    };

    const hoverStyle = {
        transform: "translateY(-10px)",
        boxShadow: "0 8px 20px rgba(0,0,0,0.4)",
    };

    const [hoverLeft, setHoverLeft] = React.useState(false);
    const [hoverRight, setHoverRight] = React.useState(false);

    return (
        <div
            className="d-flex justify-content-center align-items-center"
            style={{ height: "80vh", gap: "50px" }}
        >
            {/* Div bên trái */}
            <Link
                to={`/my-service/${user.id}`} 
                style={{
                    ...squareStyle,
                    ...(hoverLeft ? hoverStyle : {}),
                }}
                onMouseEnter={() => setHoverLeft(true)}
                onMouseLeave={() => setHoverLeft(false)}
            >
                <i className="bi bi-tools"></i>
            </Link>

            {/* Div bên phải */}
            <Link
                to={`/my-food/${user.id}`}
                style={{
                    ...squareStyle,
                    backgroundColor: "#C95792",
                    ...(hoverRight ? hoverStyle : {}),
                }}
                onMouseEnter={() => setHoverRight(true)}
                onMouseLeave={() => setHoverRight(false)}
            >
                <i className="bi bi-list"></i>
            </Link>
        </div>
    );
};

export default History;
