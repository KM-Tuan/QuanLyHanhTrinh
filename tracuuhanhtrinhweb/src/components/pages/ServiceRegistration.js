import { useEffect, useState, useContext } from "react";
import { useLocation, useParams } from "react-router-dom";
import { authApis, endpoints } from "../../configs/Apis";
import { MyUserContext } from "../../configs/MyContexts";
import "../css/ServiceRegistration.css";

const ServiceRegistration = () => {
    const { stationId } = useParams();
    const [station, setStation] = useState(null);
    const [services, setServices] = useState([]);
    const [selectedService, setSelectedService] = useState(null);
    const [loading, setLoading] = useState(false);
    const [message, setMessage] = useState(null);
    const user = useContext(MyUserContext);

    const location = useLocation();
    const { journeyName } = location.state || {};

    useEffect(() => {
        const fetchStationAndServices = async () => {
            try {
                const stationRes = await authApis().get(endpoints["station"](stationId));
                setStation(stationRes.data);

                const serviceRes = await authApis().get(endpoints["station-services"](stationId));
                setServices(Array.isArray(serviceRes.data) ? serviceRes.data : []);
            } catch (err) {
                console.error("Fetch data error:", err);
            }
        };
        fetchStationAndServices();
    }, [stationId]);

    const handleRegisterService = async () => {
        setLoading(true);
        setMessage(null);

        try {
            let res = await authApis().post(endpoints["service-register"], null, {
                params: {
                    userId: user.id,
                    serviceId: selectedService.id,
                    stationId: stationId,
                    journeyName: journeyName
                }
            });

            setMessage("Đăng ký thành công!");
            console.log("Service order created:", res.data);
        } catch (err) {
            console.error("Register service error:", err);
            setMessage(err.response?.data || err.message);
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="service-registration-page">
            
            <video autoPlay muted loop playsInline id="bg-video">
                <source src="https://res.cloudinary.com/daupdu9bs/video/upload/v1753496569/background_uonsor.mp4" type="video/mp4" />
            </video>

            <div className="service-registration-box">
                
                {station && (
                    <div className="station-section">
                        <img src={station.image} alt={station.name} />
                        <h4>{station.name}</h4>
                    </div>
                )}

                
                <div className="services-list">
                    {services.map((service) => (
                        <div
                            key={service.id}
                            className={`service-card ${selectedService?.id === service.id ? "active" : ""} ${!service.isActive ? "disabled" : ""}`}
                            onClick={() => service.isActive && setSelectedService(service)}
                        >
                            <i className="bi bi-tools fs-4 me-2"></i>
                            <span>{service.name}</span>
                        </div>
                    ))}
                </div>

                
                {selectedService && (
                    <div className="selected-service">
                        <h5>{selectedService.name}</h5>
                        {selectedService.image && (
                            <img src={selectedService.image} alt={selectedService.name} />
                        )}
                        <p>{selectedService.description}</p>
                        <p>
                            Trạng thái:{" "}
                            {selectedService.isActive ? (
                                <span className="badge bg-success">Hoạt động</span>
                            ) : (
                                <span className="badge bg-secondary">Ngừng</span>
                            )}
                        </p>
                        <button
                            className="btn btn-primary"
                            disabled={!selectedService.isActive || loading}
                            onClick={handleRegisterService}
                        >
                            {loading ? "Đang xử lý..." : "Đăng ký dịch vụ"}
                        </button>
                    </div>
                )}

                
                {message && <div className="alert-box">{message}</div>}
            </div>
        </div>
    );
};

export default ServiceRegistration;
