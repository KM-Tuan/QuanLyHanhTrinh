import { useRef, useState } from "react";
import { Alert, Button, Form, Modal } from "react-bootstrap";
import Apis, { endpoints } from "../../configs/Apis";
import MySpinner from "../layouts/MySpinner";
import { useNavigate } from "react-router-dom";
import "../css/Register.css";

const Register = () => {
    const [user, setUser] = useState({});
    const avatar = useRef();
    const [msg, setMsg] = useState();
    const [loading, setLoading] = useState(false);
    const [showOtpModal, setShowOtpModal] = useState(false);
    const [otp, setOtp] = useState("");
    const [otpMsg, setOtpMsg] = useState(null);
    const [emailId, setEmailId] = useState(null);
    const nav = useNavigate();

    const info = [
        { title: "Tên tài khoản", field: "username", type: "text" },
        { title: "Mật khẩu", field: "password", type: "password" },
        { title: "Xác nhận mật khẩu", field: "confirm", type: "password" },
        { title: "Tên", field: "firstName", type: "text" },
        { title: "Họ và tên lót", field: "lastName", type: "text" },
        { title: "Email", field: "email", type: "email" },
        { title: "Số điện thoại", field: "phone", type: "text" }
    ];

    const setState = (value, field) => {
        setUser({ ...user, [field]: value });
    };

    const register = async (e) => {
        e.preventDefault();
        setMsg(null);

        if (user.password !== user.confirm) {
            setMsg("Mật khẩu không trùng khớp!");
            return;
        }

        let form = new FormData();
        for (let key in user) {
            if (key !== "confirm") form.append(key, user[key]);
        }
        if (avatar.current.files[0]) form.append("avatar", avatar.current.files[0]);
        form.append("role", user.role || "PASSENGER");
        form.append("isActive", false);

        try {
            setLoading(true);
            let res = await Apis.post(endpoints['register'], form, {
                headers: { 'Content-type': 'multipart/form-data' }
            });

            
            if (res.status === 201 || res.status === 200) {
                setEmailId(res.data.emailId);
                setShowOtpModal(true);
            }
        } catch (ex) {
            console.error(ex);
            setMsg("Đăng ký thất bại. Vui lòng thử lại.");
        } finally {
            setLoading(false);
        }
    };

    const verifyOtp = async () => {
        setOtpMsg(null);
        console.log(emailId, otp);
        try {
            let res = await Apis.post(endpoints['verify-otp'], null, {
                params: {
                    emailId: emailId,
                    otp: otp
                }
            });

            if (res.status === 200) {
                alert("Xác nhận OTP thành công!");
                setShowOtpModal(false);
                nav("/login");
            }
        } catch (ex) {
            console.error(ex);
            setOtpMsg("OTP không hợp lệ hoặc đã hết hạn. Vui lòng nhập lại.");
        }
    };

    return (
        <div className="register-page">
            <video autoPlay muted loop playsInline id="bg-video">
                <source src="https://res.cloudinary.com/daupdu9bs/video/upload/v1753496569/background_uonsor.mp4" type="video/mp4" />
            </video>

            <div className="register-box">
                <h1>ĐĂNG KÝ</h1>

                {msg && <Alert variant="danger">{msg}</Alert>}

                <Form onSubmit={register}>
                    {info.map(i => (
                        <Form.Control
                            key={i.field}
                            value={user[i.field] || ""}
                            onChange={e => setState(e.target.value, i.field)}
                            type={i.type}
                            placeholder={i.title}
                            required
                            className="mb-3"
                        />
                    ))}

                    <Form.Control
                        ref={avatar}
                        type="file"
                        placeholder="Ảnh đại diện"
                        required
                        className="mb-3"
                    />

                    {loading ? (
                        <MySpinner />
                    ) : (
                        <Button type="submit" className="mt-2" style={{ width: "100%" }}>
                            Đăng ký
                        </Button>
                    )}
                </Form>
            </div>

            
            <Modal show={showOtpModal} centered>
                <Modal.Header>
                    <Modal.Title>Xác thực OTP</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <p>Mã OTP đã được gửi về email của bạn. Vui lòng nhập OTP để kích hoạt tài khoản.</p>
                    {otpMsg && <Alert variant="danger">{otpMsg}</Alert>}
                    <Form.Control
                        value={otp}
                        onChange={(e) => setOtp(e.target.value)}
                        type="text"
                        placeholder="Nhập OTP"
                        className="mb-3"
                    />
                </Modal.Body>
                <Modal.Footer>
                    <Button onClick={verifyOtp}>Xác nhận</Button>
                </Modal.Footer>
            </Modal>
        </div>
    );
};

export default Register;
