import { useEffect, useState, useContext } from "react";
import { useLocation, useNavigate } from "react-router-dom"; 
import Apis, { endpoints, authApis } from "../../configs/Apis";
import { MyUserContext } from "../../configs/MyContexts";

const TrackJourney = () => {
    const location = useLocation();
    const nav = useNavigate();
    const { journeyName } = location.state || {};
    const user = useContext(MyUserContext); // Lấy user từ context

    const [progress, setProgress] = useState(0);
    const [displayProgress, setDisplayProgress] = useState(0);
    const [status, setStatus] = useState("");
    const [remainingDistance, setRemainingDistance] = useState(null);
    const [stations, setStations] = useState([]);
    const [hoverStation, setHoverStation] = useState(null);

    // Fetch progress và stations
    useEffect(() => {
        if (!journeyName) return;

        const fetchStations = async () => {
            try {
                const res = await Apis.get(endpoints["journey-stations"](journeyName));
                setStations(Array.isArray(res.data) ? res.data : []);
            } catch (err) {
                console.error("Journey stations API error:", err);
            }
        };

        fetchStations();

        const interval = setInterval(async () => {
            try {
                const res = await Apis.get(endpoints["track-journey"], {
                    params: { name: journeyName },
                });

                setProgress(res.data.progress ?? 0);
                setStatus(res.data.status ?? "");
                setRemainingDistance(res.data.remainingDistance ?? null);
            } catch (err) {
                console.error("TrackJourney API error:", err);
                clearInterval(interval);
            }
        }, 1000);

        return () => clearInterval(interval);
    }, [journeyName]);

    // Animation tăng displayProgress
    useEffect(() => {
        let rafId;
        const animate = () => {
            setDisplayProgress(prev => {
                if (prev < progress) {
                    rafId = requestAnimationFrame(animate);
                    return prev + 1;
                } else return prev;
            });
        };
        if (displayProgress < progress) rafId = requestAnimationFrame(animate);
        return () => cancelAnimationFrame(rafId);
    }, [progress, displayProgress]);

    // Hàm xử lý click service
    const handleServiceClick = (serviceId) => {
        if (!user) {
            alert("Vui lòng đăng nhập trước khi đăng ký dịch vụ!");
            nav("/login"); // điều hướng tới login nếu chưa đăng nhập
            return;
        }
        nav(`/service-registration/${serviceId}`);
    };

    return (
        <div className="p-3">
            <h4 className="mb-3">Journey: {journeyName}</h4>

            {stations.length > 0 && (
                <div className="d-flex justify-content-between mb-2 position-relative">
                    {stations.map((station) => (
                        <div
                            key={station.id}
                            className="position-relative text-center"
                            style={{ flex: 1 }}
                            onMouseEnter={() => setHoverStation(station)}
                            onMouseLeave={() => setHoverStation(null)}
                        >
                            <i className="bi bi-train-front fs-3 text-primary"></i>

                            {hoverStation?.id === station.id && (
                                <div
                                    className="position-absolute p-2 bg-light text-dark border rounded"
                                    style={{
                                        top: "-220px",
                                        left: "50%",
                                        transform: "translateX(-50%)",
                                        width: "250px",
                                        zIndex: 100,
                                        textAlign: "center"
                                    }}
                                >
                                    <img
                                        src={station.image}
                                        alt={station.name}
                                        style={{ width: "100%", height: "100px", objectFit: "cover", borderRadius: "6px" }}
                                    />
                                    <p className="fw-bold mt-1 mb-0">{station.name}</p>
                                    <p className="mb-1">Khoảng cách tới ga kế tiếp: {station.distance} km</p>

                                    {station.serviceSet?.length > 0 && (
                                        <div className="mt-2 text-start">
                                            <p className="fw-bold mb-1">Dịch vụ:</p>
                                            {station.serviceSet.map(service => (
                                                <div
                                                    key={service.id}
                                                    onClick={() => handleServiceClick(service.id)}
                                                    className="px-2 py-1 rounded mb-1 text-center"
                                                    style={{
                                                        backgroundColor: service.isActive ? "#28a745" : "#d6d6d6",
                                                        color: service.isActive ? "white" : "#666",
                                                        fontSize: "0.9rem",
                                                        cursor: "pointer"
                                                    }}
                                                >
                                                    {service.name}
                                                </div>
                                            ))}
                                        </div>
                                    )}
                                </div>
                            )}
                        </div>
                    ))}
                </div>
            )}

            {status === "RUNNING" && (
                <>
                    <div style={{ position: "relative", width: "100%", marginTop: "10px" }}>
                        <div className="progress" style={{ height: "30px" }}>
                            <div
                                className="progress-bar progress-bar-striped progress-bar-animated bg-success"
                                role="progressbar"
                                style={{ width: `${displayProgress}%` }}
                                aria-valuenow={displayProgress}
                                aria-valuemin="0"
                                aria-valuemax="100"
                            >
                                {displayProgress}%
                            </div>
                        </div>
                    </div>
                    <p className="mt-2 text-success">Đang chạy...</p>
                    {remainingDistance !== null && (
                        <p className="mt-1 fw-bold">🚉 Khoảng cách còn lại tổng: {remainingDistance} km</p>
                    )}
                </>
            )}

            {status === "COMPLETED" && <p className="text-primary mt-2 fw-bold">Hành trình đã kết thúc</p>}
            {status === "WAITTING" && <p className="text-warning mt-2 fw-bold">Hành trình chưa được xuất phát</p>}
        </div>
    );
};

export default TrackJourney;
