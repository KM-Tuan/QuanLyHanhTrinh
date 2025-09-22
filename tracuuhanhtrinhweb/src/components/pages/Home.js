import { useState } from "react";
import { useNavigate } from "react-router-dom";
import Apis, { endpoints } from "../../configs/Apis";
import "../css/Home.css"

const Home = () => {
  const [journeyName, setJourneyName] = useState("");
  const [error, setError] = useState("");
  const nav = useNavigate();

  const handleTrack = async (e) => {
    e.preventDefault();
    setError("");

    const name = journeyName.trim();
    if (!name) {
      setError("Vui lòng nhập tên hành trình!");
      return;
    }

    try {
      const res = await Apis.get(endpoints["track-journey"], { params: { name } });

      if (!res.data || typeof res.data.status === "undefined") {
        setError("Không tìm thấy hành trình!");
        return;
      }

      const { status } = res.data;

      switch (status) {
        case "RUNNING":
          nav("/track-journey", { state: { journeyName: name } });
          break;
        case "WAITTING":
          setError("Hành trình chưa xuất phát!");
          break;
        case "COMPLETED":
          setError("Hành trình đã kết thúc!");
          break;
        default:
          setError("Không tìm thấy hành trình!");
      }
    } catch (err) {
      console.error(err);
      setError("Hành trình không tồn tại hoặc lỗi server!");
    }
  };

  return (
    <div className="home-page">
      
      <video autoPlay muted loop playsInline id="bg-video">
        <source src="https://res.cloudinary.com/daupdu9bs/video/upload/v1753496569/background_uonsor.mp4" type="video/mp4" />
      </video>

      
      <div className="home-wrapper">
        <div className="home-box">
          <h1>QUẢN LÝ HÀNH TRÌNH</h1>

          <form onSubmit={handleTrack}>
            <input
              type="text"
              placeholder="Nhập mã hành trình..."
              value={journeyName}
              onChange={(e) => setJourneyName(e.target.value)}
            />
            <button type="submit">Theo dõi</button>
          </form>

          {error && <p className="error">{error}</p>}
        </div>
      </div>
    </div>
  );
};

export default Home;
