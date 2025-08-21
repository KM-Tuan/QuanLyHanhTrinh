import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { authApis, endpoints } from "../../configs/Apis";

const ServiceRegistration = () => {
    const { stationId } = useParams();
    const [station, setStation] = useState(null);
    const [services, setServices] = useState([]);
    const [selectedService, setSelectedService] = useState(null);

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
                        className={`d-flex align-items-center p-2 border rounded text-center ${
                            selectedService?.id === service.id ? "border-primary" : "border-secondary"
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
                        disabled={!selectedService.isActive}
                    >
                        Đăng ký dịch vụ
                    </button>
                </div>
            )}
        </div>
    );
};

export default ServiceRegistration;
