import { useRef, useState } from "react";
import { Alert, Button, Form } from "react-bootstrap";
import Apis, { endpoints } from "../../configs/Apis";
import MySpinner from "../layouts/MySpinner";
import { useNavigate } from "react-router-dom";
import "../css/Register.css";

const Register = () => {
    const [user, setUser] = useState({});
    const avatar = useRef();
    const [msg, setMsg] = useState();
    const [loading, setLoading] = useState(false);
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
        form.append("isActive", true);

        try {
            setLoading(true);
            await Apis.post(endpoints['register'], form, {
                headers: { 'Content-type': 'multipart/form-data' }
            });
            nav("/login");
        } catch (ex) {
            console.error(ex);
            setMsg("Đăng ký thất bại. Vui lòng thử lại.");
        } finally {
            setLoading(false);
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
        </div>
    );
};

export default Register;
