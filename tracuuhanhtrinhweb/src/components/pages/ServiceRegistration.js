import { useEffect, useState, useContext } from "react";
import { useLocation, useParams } from "react-router-dom";
import { authApis, endpoints } from "../../configs/Apis";
import { MyUserContext } from "../../configs/MyContexts";

const ServiceRegistration = () => {
    const { stationId } = useParams();
    const [station, setStation] = useState(null);
    const [services, setServices] = useState([]);
    const [selectedService, setSelectedService] = useState(null);
    const [loading, setLoading] = useState(false);
    const [message, setMessage] = useState(null);
    const user = useContext(MyUserContext);

    // giả sử journeyName truyền qua localStorage hoặc props
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
            setMessage((err.response?.data || err.message));
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="container mt-3 d-flex flex-column align-items-center">
            {/* Ảnh Station */}
            {station && (
                <div className="text-center mb-4">
                    <img
                        src={station.image}
                        alt={station.name}
                        style={{ width: "400px", height: "400px", objectFit: "cover", borderRadius: "8px" }}
                    />
                    <h4 className="mt-2">{station.name}</h4>
                </div>
            )}

            {/* Thẻ Service */}
            <div className="d-flex flex-wrap justify-content-center gap-3">
                {services.map((service) => (
                    <div
                        key={service.id}
                        className={`d-flex align-items-center p-2 border rounded text-center ${selectedService?.id === service.id ? "border-primary" : "border-secondary"
                            }`}
                        style={{
                            cursor: service.isActive ? "pointer" : "not-allowed",
                            minWidth: "150px",
                            opacity: service.isActive ? 1 : 0.5,
                        }}
                        onClick={() => service.isActive && setSelectedService(service)}
                    >
                        <i className="bi bi-tools fs-4 me-2"></i>
                        <span>{service.name}</span>
                    </div>
                ))}
            </div>

            {/* Thông tin service đã chọn */}
            {selectedService && (
                <div className="card mt-4 p-3" style={{ maxWidth: "600px" }}>
                    <h5>{selectedService.name}</h5>
                    {selectedService.image && (
                        <img
                            src={selectedService.image}
                            alt={selectedService.name}
                            style={{ width: "100%", maxHeight: "300px", objectFit: "cover", marginBottom: "10px" }}
                        />
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

            {/* Thông báo */}
            {message && (
                <div className="alert alert-info mt-3" style={{ maxWidth: "600px" }}>
                    {message}
                </div>
            )}
        </div>
    );
};

export default ServiceRegistration;
