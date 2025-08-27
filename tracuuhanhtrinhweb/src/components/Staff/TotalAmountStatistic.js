import React, { useEffect, useState } from "react";
import { PieChart, Pie, Cell, Tooltip, Legend, ResponsiveContainer } from "recharts";
import { Button, Spinner } from "react-bootstrap";
import { authApis, endpoints } from "../../configs/Apis";

const COLORS = [
  "#0088FE", "#00C49F", "#FFBB28", "#FF8042",
  "#A28EFF", "#FF6F91", "#FF9F40", "#FFCD56"
];

const TotalAmountStatistic = () => {
  const [data, setData] = useState([]);
  const [type, setType] = useState("day");
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    fetchData(type);
  }, [type]);

  const fetchData = async (type) => {
    setLoading(true);
    try {
      let url = "";
      if (type === "day") url = endpoints["total-day-json"];
      else if (type === "month") url = endpoints["total-month-json"];
      else if (type === "year") url = endpoints["total-year-json"];

      const res = await authApis().get(url);

      const formatted = res.data.map(item => {
        let name = "";
        let value = Number(item[item.length - 1]);
        if(type === "day") {
          name = new Date(item[0]).toLocaleDateString();
        } else if(type === "month") {
          // item = [year, month, value]
          name = `${item[1]}/${item[0]}`; // mm/yyyy
        } else if(type === "year") {
          // item = [year, value]
          name = `${item[0]}`; // yyyy
        }
        return { name, value };
      });

      setData(formatted);
    } catch (error) {
      console.error("Error fetching revenue data:", error);
      setData([]);
    }
    setLoading(false);
  };

  // Xuất CSV
  const handleExportCsv = async () => {
    try {
      let url = "";
      if(type === "day") url = endpoints["total-day-csv"];
      else if(type === "month") url = endpoints["total-month-csv"];
      else if(type === "year") url = endpoints["total-year-csv"];

      const res = await authApis().get(url, { responseType: "blob" });
      const link = document.createElement("a");
      link.href = window.URL.createObjectURL(new Blob([res.data]));
      link.setAttribute("download", `Revenue_${type}.csv`);
      document.body.appendChild(link);
      link.click();
      link.remove();
    } catch (err) {
      console.error("Error downloading CSV:", err);
    }
  };

  // Xuất PDF
  const handleExportPdf = async () => {
    try {
      let url = "";
      if(type === "day") url = endpoints["total-day-pdf"];
      else if(type === "month") url = endpoints["total-month-pdf"];
      else if(type === "year") url = endpoints["total-year-pdf"];

      const res = await authApis().get(url, { responseType: "blob" });
      const link = document.createElement("a");
      link.href = window.URL.createObjectURL(new Blob([res.data]));
      link.setAttribute("download", `Revenue_${type}.pdf`);
      document.body.appendChild(link);
      link.click();
      link.remove();
    } catch (err) {
      console.error("Error downloading PDF:", err);
    }
  };

  return (
    <div style={{ padding: "20px" }}>
      <h3 className="mb-4">Thống kê doanh thu theo {type}</h3>

      <div className="mb-3 d-flex gap-2">
        <select value={type} onChange={(e) => setType(e.target.value)}>
          <option value="day">Ngày</option>
          <option value="month">Tháng</option>
          <option value="year">Năm</option>
        </select>
        <Button variant="success" onClick={handleExportCsv}>Xuất CSV</Button>
        <Button variant="danger" onClick={handleExportPdf}>Xuất PDF</Button>
      </div>

      {loading ? (
        <div className="d-flex justify-content-center align-items-center" style={{ height: "300px" }}>
          <Spinner animation="border" />
        </div>
      ) : (
        <ResponsiveContainer width="100%" height={400}>
          <PieChart>
            <Pie
              data={data}
              dataKey="value"
              nameKey="name"
              cx="50%"
              cy="50%"
              outerRadius={120}
              fill="#8884d8"
              label={(entry) => `${entry.name}: ${new Intl.NumberFormat('vi-VN').format(entry.value)} VNĐ`}
            >
              {data.map((entry, index) => (
                <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
              ))}
            </Pie>
            <Tooltip formatter={(value) => new Intl.NumberFormat('vi-VN').format(value) + " VNĐ"} />
            <Legend />
          </PieChart>
        </ResponsiveContainer>
      )}
    </div>
  );
};

export default TotalAmountStatistic;
