import { useEffect, useState } from "react";
import { authApis, endpoints } from "../../configs/Apis";
import { useParams } from "react-router-dom";
import "../css/MyServiceOrders.css";

const MyServiceOrders = () => {
  const { userId } = useParams();
  const [serviceOrders, setServiceOrders] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    const fetchServiceOrders = async () => {
      try {
        const res = await authApis().get(endpoints['my-service'](userId));
        setServiceOrders(res.data);
      } catch (err) {
        setError(err.response?.data || "Lỗi khi lấy dữ liệu");
      } finally {
        setLoading(false);
      }
    };

    fetchServiceOrders();
  }, [userId]);

  return (
    <div className="myservice-page">
      {/* Video nền */}
      <video autoPlay muted loop playsInline id="myservice-bg-video">
        <source
          src="https://res.cloudinary.com/daupdu9bs/video/upload/v1753496569/background_uonsor.mp4"
          type="video/mp4"
        />
      </video>

      {/* Box */}
      <div className="myservice-box">
        <h2>DANH SÁCH ĐĂNG KÝ DỊCH VỤ</h2>

        {loading && <p>Đang tải dữ liệu...</p>}
        {error && <p style={{ color: 'red' }}>{error}</p>}
        {!loading && !error && serviceOrders.length === 0 && <p>Chưa có service order nào.</p>}

        {!loading && !error && serviceOrders.length > 0 && (
          <table>
            <thead>
              <tr>
                <th>ID</th>
                <th>Tên</th>
                <th>Ngày đăng ký</th>
                <th>Hành trình</th>
                <th>Ga tàu</th>
              </tr>
            </thead>
            <tbody>
              {serviceOrders.map(order => (
                <tr key={order.id}>
                  <td>{order.id}</td>
                  <td>{order.name}</td>
                  <td>{new Date(order.createdAt).toLocaleString()}</td>
                  <td>{order.journeyName?.name || "N/A"}</td>
                  <td>{order.stationId?.name || "N/A"}</td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </div>
    </div>
  );
};

export default MyServiceOrders;
