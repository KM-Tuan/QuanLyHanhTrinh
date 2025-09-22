import "../css/Footer.css";

const Footer = () => {
  return (
    <footer className="footer-modern">
      <div className="footer-container">
        
        <div className="footer-left">
          <div>
            <i className="bi bi-c-circle"></i>
            <span> 2025 KMT Tech</span>
          </div>
          <div>
            <i className="bi bi-info-circle-fill"></i>
            <a href="#"> Về chúng tôi</a>
          </div>
        </div>

        
        <div className="footer-right">
          <a href="#"><i className="bi bi-facebook"></i> Facebook</a>
          <a href="#"><i className="bi bi-instagram"></i> Instagram</a>
          <a href="#"><i className="bi bi-linkedin"></i> LinkedIn</a>
          <a href="#">Chính sách bảo mật</a>
          <a href="#">Quy chế hoạt động</a>
          <a href="#">Chính sách thanh toán</a>
        </div>
      </div>
    </footer>
  );
};

export default Footer;
