import { useEffect, useState } from "react";
import { BarChart, Bar, YAxis, Tooltip, ResponsiveContainer, Cell, XAxis } from "recharts";
import { Button, Spinner } from "react-bootstrap";
import { endpoints, authApis } from "../../configs/Apis";
import "../css/MostOrderedStatistic.css";

const MostOrderedStatistic = () => {
    const [data, setData] = useState([]);
    const [loading, setLoading] = useState(true);

    const colors = ["#8884d8", "#82ca9d", "#ffc658", "#ff7f50", "#00c49f"];

    useEffect(() => {
        const fetchData = async () => {
            try {
                setLoading(true);
                const res = await authApis().get(endpoints["ordered-json"]);

                const formattedData = res.data
                    .map(([journey, food, total], idx) => ({
                        id: idx,
                        journey,
                        food,
                        total,
                        color: colors[idx % colors.length]
                    }))
                    .sort((a, b) => b.total - a.total);

                setData(formattedData);
            } catch (err) {
                console.error(err);
            } finally {
                setLoading(false);
            }
        };

        fetchData();
    }, []);

    const handleExportCsv = async () => {
        try {
            const res = await authApis().get(endpoints["ordered-csv"], {
                responseType: "blob"
            });

            const url = window.URL.createObjectURL(new Blob([res.data]));
            const link = document.createElement("a");
            link.href = url;
            link.setAttribute("download", "MostOrderedFood.csv");
            document.body.appendChild(link);
            link.click();
            link.remove();
            window.URL.revokeObjectURL(url);
        } catch (err) {
            console.error("Error downloading CSV:", err);
        }
    };

    const handleExportPdf = async () => {
        try {
            const res = await authApis().get(endpoints["ordered-pdf"], {
                responseType: "blob"
            });

            const url = window.URL.createObjectURL(new Blob([res.data]));
            const link = document.createElement("a");
            link.href = url;
            link.setAttribute("download", "MostOrderedFood.pdf");
            document.body.appendChild(link);
            link.click();
            link.remove();
            window.URL.revokeObjectURL(url);
        } catch (err) {
            console.error("Error downloading PDF:", err);
        }
    };

    const CustomTooltip = ({ active, payload }) => {
        if (active && payload && payload.length) {
            const item = payload[0].payload;
            return (
                <div className="custom-tooltip">
                    <p><strong>Journey:</strong> {item.journey}</p>
                    <p><strong>Food:</strong> {item.food}</p>
                    <p><strong>Total Ordered:</strong> {item.total}</p>
                </div>
            );
        }
        return null;
    };

    return (
        <div className="mostordered-page">
            
            <video autoPlay muted loop playsInline id="mostordered-bg-video">
                <source
                    src="https://res.cloudinary.com/daupdu9bs/video/upload/v1753496569/background_uonsor.mp4"
                    type="video/mp4"
                />
            </video>

            
            <div className="mostordered-box">
                <h2>THỐNG KÊ MÓN ĂN ĐẶT NHIỀU NHẤT</h2>

                <div className="btn-group">
                    <Button variant="success" onClick={handleExportCsv}>Xuất CSV</Button>
                    <Button variant="danger" onClick={handleExportPdf}>Xuất PDF</Button>
                </div>

                {loading ? (
                    <div className="loading">
                        <Spinner animation="border" />
                    </div>
                ) : (
                    <ResponsiveContainer width="100%" height={400}>
                        <BarChart
                            data={data}
                            margin={{ top: 20, right: 30, left: 20, bottom: 20 }}
                        >
                            <XAxis />
                            <YAxis />
                            <Tooltip content={<CustomTooltip />} />

                            <Bar
                                dataKey="total"
                                name="Total Ordered"
                                radius={[5, 5, 0, 0]}
                                stroke="#555"
                                strokeWidth={1}
                            >
                                {data.map((entry, index) => (
                                    <Cell
                                        key={index}
                                        fill={entry.color}
                                        style={{ filter: "drop-shadow(2px 2px 4px rgba(0,0,0,0.3))" }}
                                    />
                                ))}
                            </Bar>
                        </BarChart>
                    </ResponsiveContainer>
                )}
            </div>
        </div>
    );
};

export default MostOrderedStatistic;
