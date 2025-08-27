import React, { useState } from "react";
import { Link } from "react-router-dom";

const Statistic = () => {
    const squareStyle = {
        width: "200px",
        height: "200px",
        backgroundColor: "#4CAF50",
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

    const [hoverFood, setHoverFood] = useState(false);
    const [hoverCredit, setHoverCredit] = useState(false);

    return (
        <div
            className="d-flex justify-content-center align-items-center"
            style={{ height: "80vh", gap: "50px" }}
        >
            {/* Div icon đồ ăn */}
            <Link
                to="/most-ordered-statistic"
                style={{
                    ...squareStyle,
                    backgroundColor: "#FF6F61",
                    ...(hoverFood ? hoverStyle : {}),
                }}
                onMouseEnter={() => setHoverFood(true)}
                onMouseLeave={() => setHoverFood(false)}
            >
                <i className="bi bi-basket"></i>
            </Link>

            {/* Div icon tín dụng */}
            <Link
                to="/total-amount-statistic"
                style={{
                    ...squareStyle,
                    backgroundColor: "#3F51B5",
                    ...(hoverCredit ? hoverStyle : {}),
                }}
                onMouseEnter={() => setHoverCredit(true)}
                onMouseLeave={() => setHoverCredit(false)}
            >
                <i className="bi bi-currency-dollar"></i>
            </Link>
        </div>
    );
};

export default Statistic;
