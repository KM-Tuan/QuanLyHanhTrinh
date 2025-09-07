import { useContext, useState } from "react";
import { Alert, Button, Form } from "react-bootstrap";
import Apis, { authApis, endpoints } from "../../configs/Apis";
import MySpinner from "../layouts/MySpinner";
import { useNavigate, useSearchParams } from "react-router-dom";
import cookie from "react-cookies";
import { MyDispatcherContext } from "../../configs/MyContexts";
import "../css/Login.css";

const Login = () => {
  const [user, setUser] = useState({});
  const [role, setRole] = useState("PASSENGER");
  const [msg, setMsg] = useState();
  const [loading, setLoading] = useState(false);
  const nav = useNavigate();
  const dispatch = useContext(MyDispatcherContext);
  const [q] = useSearchParams();

  const info = [
    { title: "Tên tài khoản", field: "username", type: "text" },
    { title: "Mật khẩu", field: "password", type: "password" }
  ];

  const setState = (value, field) => setUser({ ...user, [field]: value });

  const login = async (e) => {
    e.preventDefault();
    try {
      setLoading(true);
      setMsg(null);

      let res = await Apis.post(endpoints["login"], { ...user });
      cookie.save("token", res.data.token);

      let u = await authApis().get(endpoints["current-user"]);

      if (u.data.role !== role) {
        setMsg("Role không đúng. Vui lòng chọn role chính xác.");
        cookie.remove("token");
        return;
      }

      dispatch({ type: "login", payload: u.data });

      let next = q.get("next");
      if (next) nav(next);
      else nav("/");
    } catch (ex) {
      console.error(ex);
      setMsg("Đăng nhập thất bại. Kiểm tra lại tài khoản và mật khẩu.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="login-page">

      <video autoPlay muted loop playsInline id="bg-video">
        <source src="https://res.cloudinary.com/daupdu9bs/video/upload/v1753496569/background_uonsor.mp4" type="video/mp4" />
      </video>

      <div className="login-box">
        <h1>ĐĂNG NHẬP</h1>

        {msg && <Alert variant="danger">{msg}</Alert>}

        <Form.Select
          className="mt-2 mb-3"
          value={role}
          onChange={(e) => setRole(e.target.value)}
        >
          <option value="PASSENGER">Hành khách</option>
          <option value="STAFF">Nhân viên</option>
          <option value="ADMIN">Quản trị viên</option>
        </Form.Select>

        <Form onSubmit={login}>
          {info.map((i) => (
            <Form.Control
              key={i.field}
              value={user[i.field] || ""}
              onChange={(e) => setState(e.target.value, i.field)}
              type={i.type}
              placeholder={i.title}
              required
              className="mb-3"
            />
          ))}

          {loading ? (
            <MySpinner />
          ) : (
            <Button type="submit" className="mt-2" style={{ width: "100%" }}>
              Đăng nhập
            </Button>
          )}
        </Form>
      </div>
    </div>
  );
};

export default Login;
