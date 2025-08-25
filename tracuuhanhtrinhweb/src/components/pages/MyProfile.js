import React, { useContext } from "react";
import { MyUserContext } from "../../configs/MyContexts";
import { Card, Badge } from "react-bootstrap";

const MyProfile = () => {
    const user = useContext(MyUserContext);

    if (!user) {
        return <p className="text-center text-muted mt-5">Bạn chưa đăng nhập!</p>;
    }

    return (
        <div className="d-flex justify-content-center align-items-center mt-5">
            <Card style={{ width: "600px", boxShadow: "0 4px 12px rgba(0,0,0,0.2)" }}>
                <Card.Body className="d-flex align-items-center">
                    {/* Avatar */}
                    <img
                        src={user.avatar}
                        alt="avatar"
                        width="120"
                        height="120"
                        className="rounded-circle me-4"
                        style={{ objectFit: "cover", border: "3px solid #C95792" }}
                    />

                    {/* Thông tin */}
                    <div>
                        <h4>
                            {user.lastName} {user.firstName}{" "}
                            <Badge bg={user.role === "ADMIN" ? "danger" : "secondary"}>
                                {user.role}
                            </Badge>
                        </h4>
                        <p className="mb-1">
                            <strong>Tên đăng nhập:</strong> {user.username}
                        </p>
                        <p className="mb-1">
                            <strong>Email:</strong> {user.email?.email}
                        </p>
                        <p className="mb-1">
                            <strong>Số điện thoại:</strong> {user.phone?.phone}
                        </p>
                        <p className="mb-1">
                            <strong>Trạng thái:</strong>{" "}
                            {user.isActive ? (
                                <span className="text-success fw-bold">Hoạt động</span>
                            ) : (
                                <span className="text-danger fw-bold">Bị khóa</span>
                            )}
                        </p>
                    </div>
                </Card.Body>
            </Card>
        </div>
    );
};

export default MyProfile;
