import { useEffect, useState, useContext } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import Apis, { endpoints } from "../../configs/Apis";
import { MyUserContext } from "../../configs/MyContexts";
import "../css/TrackJourney.css";

const TrackJourney = () => {
    const location = useLocation();
    const nav = useNavigate();
    const { journeyName } = location.state || {};
    const user = useContext(MyUserContext);

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
            setDisplayProgress((prev) => {
                if (prev < progress) {
                    rafId = requestAnimationFrame(animate);
                    return prev + 1;
                } else return prev;
            });
        };
        if (displayProgress < progress) rafId = requestAnimationFrame(animate);
        return () => cancelAnimationFrame(rafId);
    }, [progress, displayProgress]);

    const handleStationClick = (stationId) => {
        if (!user) {
            alert("Vui lòng đăng nhập trước khi đăng ký dịch vụ!");
            nav("/login");
            return;
        }
        nav(`/service-registration/${stationId}`, { state: { journeyName } });
    };

    return (
        <div className="track-journey-page">
            {/* Video nền */}
            <video autoPlay muted loop playsInline id="bg-video">
                <source src="https://res.cloudinary.com/daupdu9bs/video/upload/v1753496569/background_uonsor.mp4" type="video/mp4" />
            </video>

            <div className="track-journey-box">
                <h4>HÀNH TRÌNH: {journeyName}</h4>

                {stations.length > 0 && (
                    <div className="stations-row">
                        {stations.map((station) => (
                            <div
                                key={station.id}
                                className="station-icon"
                                onMouseEnter={() => setHoverStation(station)}
                                onMouseLeave={() => setHoverStation(null)}
                                onClick={() => handleStationClick(station.id)}
                            >
                                <i className="bi bi-geo-alt-fill fs-3"></i>

                                {hoverStation?.id === station.id && (
                                    <div className="station-tooltip">
                                        <img src={station.image} alt={station.name} />
                                        <p className="fw-bold">{station.name}</p>
                                        <p>Khoảng cách tới ga kế tiếp: {station.distance} km</p>
                                    </div>
                                )}
                            </div>
                        ))}
                    </div>
                )}

                {status === "RUNNING" && (
                    <>
                        <div className="progress">
                            <div
                                className="progress-bar"
                                role="progressbar"
                                style={{ width: `${displayProgress}%` }}
                                aria-valuenow={displayProgress}
                                aria-valuemin="0"
                                aria-valuemax="100"
                            >
                                {displayProgress}%
                            </div>
                        </div>
                        <p className="status-running">Đang chạy...</p>
                        {remainingDistance !== null && (
                            <p className="fw-bold">Khoảng cách còn lại tổng: {remainingDistance} km</p>
                        )}
                    </>
                )}

                {status === "COMPLETED" && (
                    <p className="status-text status-completed">✅ Hành trình đã kết thúc</p>
                )}
                {status === "WAITTING" && (
                    <p className="status-text status-waiting">⌛ Hành trình chưa được xuất phát</p>
                )}
            </div>
        </div>
    );
};

export default TrackJourney;
