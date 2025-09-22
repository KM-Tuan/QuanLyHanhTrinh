import React from "react";
import { Link } from "react-router-dom";
import "../css/Statistic.css";

const Statistic = () => {
  return (
    <div className="statistic-page">
      
      <video
        autoPlay
        muted
        loop
        playsInline
        id="statistic-bg-video"
      >
        <source
          src="https://res.cloudinary.com/daupdu9bs/video/upload/v1753496569/background_uonsor.mp4"
          type="video/mp4"
        />
      </video>

      
      <div className="statistic-wrapper">
        <Link to="/most-ordered-statistic" className="statistic-box food">
          <i className="bi bi-basket"></i>
        </Link>

        <Link to="/total-amount-statistic" className="statistic-box credit">
          <i className="bi bi-currency-dollar"></i>
        </Link>
      </div>
    </div>
  );
};

export default Statistic;
