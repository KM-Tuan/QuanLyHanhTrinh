import { useEffect, useState } from "react";
import { authApis, endpoints } from "../../configs/Apis";
import { useParams } from "react-router-dom";

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
        console.log(userId)
        console.log(res.data);
      } catch (err) {
        setError(err.response?.data || "Lỗi khi lấy dữ liệu");
      } finally {
        setLoading(false);
      }
    };

    fetchServiceOrders();
  }, [userId]);

  if (loading) return <p>Đang tải dữ liệu...</p>;
  if (error) return <p style={{ color: 'red' }}>{error}</p>;
  if (serviceOrders.length === 0) return <p>Chưa có service order nào.</p>;

  return (
    <div>
      <h2>Danh sách Service Orders</h2>
      <table border="1" cellPadding="10" style={{ borderCollapse: "collapse" }}>
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
    </div>
  );
};

export default MyServiceOrders;
