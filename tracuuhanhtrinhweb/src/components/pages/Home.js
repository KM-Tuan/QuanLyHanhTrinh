import { useState } from "react";
import { useNavigate } from "react-router-dom";
import Apis, { endpoints } from "../../configs/Apis";

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
      const res = await Apis.get(endpoints['track-journey'], { params: { name } });

      // Nếu server trả 200 nhưng không có dữ liệu hợp lệ
      if (!res.data || typeof res.data.status === "undefined") {
        setError("Hành trình không tồn tại!");
        return;
      }

      // Hợp lệ → điều hướng
      nav("/track-journey", { state: { journeyName: name } });
    } catch (err) {
      // Lỗi HTTP (404, 500, ...) sẽ vào đây
      console.error(err);
      setError("Hành trình không tồn tại hoặc lỗi server!");
    }
  };

  return (
    <div
      style={{
        backgroundColor: "#31363F",
        minHeight: "100vh",
        color: "white",
        padding: "20px",
      }}
    >
      <h1>Trang Chủ</h1>

      <div className="p-4">
        <form onSubmit={handleTrack}>
          <input
            type="text"
            placeholder="Enter Journey Name"
            value={journeyName}
            onChange={(e) => setJourneyName(e.target.value)}
            className="border p-2"
          />
          <button type="submit" className="ml-2 p-2 bg-blue-500 text-white">
            Track
          </button>
        </form>

        {error && <p className="mt-2 text-red-500">{error}</p>}
      </div>
    </div>
  );
};

export default Home;
