import { useEffect, useState } from "react";
import { authApis, endpoints } from "../../configs/Apis";
import { useParams } from "react-router-dom";

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
        console.log(userId);
        console.log(res.data);
      } catch (err) {
        setError(err.response?.data || "Lỗi khi lấy dữ liệu");
      } finally {
        setLoading(false);
      }
    };

    fetchFoodOrders();
  }, [userId]);

  if (loading) return <p>Đang tải dữ liệu...</p>;
  if (error) return <p style={{ color: 'red' }}>{error}</p>;
  if (foodOrders.length === 0) return <p>Chưa có đơn FoodOrder nào.</p>;

  return (
    <div>
      <h2>Danh sách Food Orders</h2>
      <table border="1" cellPadding="10" style={{ borderCollapse: "collapse" }}>
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
    </div>
  );
};

export default MyFoodOrders;
