import { useEffect, useState } from "react";
import { authApis, endpoints } from "../../configs/Apis";
import { useParams } from "react-router-dom";
import "../css/MyFoodOrders.css";

const MyFoodOrders = () => {
  const { userId } = useParams();
  const [foodOrders, setFoodOrders] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    const fetchFoodOrders = async () => {
      try {
        const res = await authApis().get(endpoints['my-food'](userId));
        setFoodOrders(res.data);
      } catch (err) {
        setError(err.response?.data || "Lỗi khi lấy dữ liệu");
      } finally {
        setLoading(false);
      }
    };

    fetchFoodOrders();
  }, [userId]);

  return (
    <div className="myfood-page">
      
      <video autoPlay muted loop playsInline id="myfood-bg-video">
        <source
          src="https://res.cloudinary.com/daupdu9bs/video/upload/v1753496569/background_uonsor.mp4"
          type="video/mp4"
        />
      </video>

      
      <div className="myfood-box">
        <h2>DANH SÁCH HÓA ĐƠN</h2>

        {loading && <p>Đang tải dữ liệu...</p>}
        {error && <p style={{ color: 'red' }}>{error}</p>}
        {!loading && !error && foodOrders.length === 0 && <p>Chưa có đơn FoodOrder nào.</p>}

        {!loading && !error && foodOrders.length > 0 && (
          <table>
            <thead>
              <tr>
                <th>ID</th>
                <th>Tên</th>
                <th>Ngày tạo</th>
                <th>Hành trình</th>
                <th>Tổng tiền</th>
              </tr>
            </thead>
            <tbody>
              {foodOrders.map(order => (
                <tr key={order.id}>
                  <td>{order.id}</td>
                  <td>{order.name}</td>
                  <td>{new Date(order.createdAt).toLocaleString()}</td>
                  <td>{order.journeyName?.name || "N/A"}</td>
                  <td>{order.totalAmount?.toLocaleString("vi-VN")}₫</td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </div>
    </div>
  );
};

export default MyFoodOrders;
