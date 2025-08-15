import { useContext, useState } from "react";
import { Alert, Button, Form } from "react-bootstrap";
import Apis, { authApis, endpoints } from "../../configs/Apis";
import MySpinner from "../layouts/MySpinner"
import { useNavigate } from "react-router-dom";
import cookie from "react-cookies";
import { MyDispatcherContext } from "../../configs/MyContexts";

const Login = () => {
    const [user, setUser] = useState({});
    const [msg, setMsg] = useState();
    const [loading, setLoading] = useState(false);
    const nav = useNavigate();
    const dispatch = useContext(MyDispatcherContext);

    const info = [{
        title: "Tên tài khoản",
        field: "username",
        type: "text"
    }, {
        title: "Mật khẩu",
        field: "password",
        type: "password"
    }];

    const setState = (value, field) => {
        setUser({ ...user, [field]: value });
    }

    const login = async (e) => {
        e.preventDefault();
        try {
          setLoading(true);
          let res = await Apis.post(endpoints['login'], {
            ...user
          });
          
          cookie.save('token', res.data.token);

          let u = await authApis().get(endpoints['current-user']);
          console.info(u.data);

          dispatch({
            "type": "login", 
            "payload": u.data
          });
          nav("/");
        } catch (ex) {
          console.error(ex);
        } finally {
          setLoading(false);
        }
    }






    return (
        <>
            <h1>Đăng Nhập</h1>

            {msg && <Alert variant="danger">{msg}</Alert>}

            <Form onSubmit={login}>
                {info.map(i => <Form.Control key={i.field} value={user[i.field]} onChange={e => setState(e.target.value, i.field)} className="mt-2 mb-1" type={i.type} placeholder={i.title} required />)}

                {loading === true ? <MySpinner /> : <Button type="submit" variant="success" className="mt-2 mb-1">Đăng nhập</Button>}
            </Form>
        </>
    )
}

export default Login;