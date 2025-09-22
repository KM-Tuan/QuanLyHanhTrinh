import React, { useContext } from "react";
import { Link } from "react-router-dom";
import { MyUserContext } from "../../configs/MyContexts";
import "../css/History.css";

const History = () => {
  const user = useContext(MyUserContext);

  return (
    <div className="history-page">
      
      <video
        autoPlay
        muted
        loop
        playsInline
        id="history-bg-video"
      >
        <source
          src="https://res.cloudinary.com/daupdu9bs/video/upload/v1753496569/background_uonsor.mp4"
          type="video/mp4"
        />
      </video>

      
      <div className="history-wrapper">
        <Link to={`/my-service/${user.id}`} className="history-box service">
          <i className="bi bi-tools"></i>
        </Link>

        <Link to={`/my-food/${user.id}`} className="history-box food">
          <i className="bi bi-list"></i>
        </Link>
      </div>
    </div>
  );
};

export default History;
