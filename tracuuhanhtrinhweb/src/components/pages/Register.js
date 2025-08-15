import { useRef, useState } from "react";
import { Alert, Button, Form } from "react-bootstrap";
import Apis, { endpoints } from "../../configs/Apis";
import MySpinner from "../layouts/MySpinner"
import { useNavigate } from "react-router-dom";

const Register = () => {
    const [user, setUser] = useState({});
    const avatar = useRef();
    const [msg, setMsg] = useState();
    const [loading, setLoading] = useState(false);
    const nav = useNavigate();

    const info = [{
        title: "Tên tài khoản",
        field: "username",
        type: "text"
    }, {
        title: "Mật khẩu",
        field: "password",
        type: "password"
    }, {
        title: "Xác nhận mật khẩu",
        field: "confirm",
        type: "password"
    }, {
        title: "Tên",
        field: "firstName",
        type: "text"
    }, {
        title: "Họ và tên lót",
        field: "lastName",
        type: "text"
    }];

    const setState = (value, field) => {
        setUser({ ...user, [field]: value });
    }

    const register = async (e) => {
        e.preventDefault();

        if (user.password !== user.confirm) {
            setMsg("Mật khẩu không trùng khớp!");
        } else {
            let form = new FormData();
            for (let key in user) {
                if (key !== "confirm")
                    form.append(key, user[key]);
            }

            form.append("avatar", avatar.current.files[0]);
            form.append("role", user.role || "PASSENGER");
            form.append("isActive", true);

            try {
                setLoading(true);
                await Apis.post(endpoints['register'], form, {
                    headers: {
                        'Content-type': 'multipart/form-data'
                    }
                });

                nav("/login");
            } catch (ex) {
                console.error(ex);
            } finally {
                setLoading(false);
            }
        }
    }






    return (
        <>
            <h1>Đăng Ký</h1>

            {msg && <Alert variant="danger">{msg}</Alert>}

            <Form onSubmit={register}>
                {info.map(i => <Form.Control key={i.field} value={user[i.field]} onChange={e => setState(e.target.value, i.field)} className="mt-2 mb-1" type={i.type} placeholder={i.title} required />)}

                <Form.Control ref={avatar} className="mt-2 mb-1" type="file" placeholder="Ảnh đại diện" required />


                {loading === true ? <MySpinner /> : <Button type="submit" variant="success" className="mt-2 mb-1">Đăng ký</Button>}
            </Form>
        </>
    )
}

export default Register;