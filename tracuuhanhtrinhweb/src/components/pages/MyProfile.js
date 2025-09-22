import React, { useContext } from "react";
import { MyUserContext } from "../../configs/MyContexts";
import { Card, Badge } from "react-bootstrap";
import "../css/MyProfile.css";

const MyProfile = () => {
    const user = useContext(MyUserContext);

    if (!user) {
        return <p className="text-center text-muted mt-5">Bạn chưa đăng nhập!</p>;
    }

    return (
        <div className="my-profile-page">

            
            <video autoPlay muted loop playsInline id="bg-video">
                <source src="https://res.cloudinary.com/daupdu9bs/video/upload/v1753496569/background_uonsor.mp4" type="video/mp4" />
            </video>

            <Card className="my-profile-card d-flex align-items-center">
                <Card.Body className="d-flex align-items-center flex-wrap">
                    
                    <img
                        src={user.avatar}
                        alt="avatar"
                        className="me-4"
                    />

                    
                    <div>
                        <h4>
                            {user.lastName} {user.firstName}{" "}
                            <Badge bg={user.role === "ADMIN" ? "danger" : "secondary"}>
                                {user.role}
                            </Badge>
                        </h4>
                        <p><strong>Tên đăng nhập:</strong> {user.username}</p>
                        <p><strong>Email:</strong> {user.email?.email}</p>
                        <p><strong>Số điện thoại:</strong> {user.phone?.phone}</p>
                        <p>
                            <strong>Trạng thái:</strong>{" "}
                            {user.isActive ? (
                                <span className="text-success">Hoạt động</span>
                            ) : (
                                <span className="text-danger">Bị khóa</span>
                            )}
                        </p>
                    </div>
                </Card.Body>
            </Card>
        </div>
    );
};

export default MyProfile;
