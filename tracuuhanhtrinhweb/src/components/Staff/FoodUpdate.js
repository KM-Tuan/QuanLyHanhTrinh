import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { endpoints, authApis, default as Apis } from "../../configs/Apis";
import MySpinner from "../layouts/MySpinner";

const FoodEdit = () => {
  const { foodId } = useParams();
  const navigate = useNavigate();
  const [food, setFood] = useState(null);
  const [categories, setCategories] = useState([]);
  const [loading, setLoading] = useState(true);
  const [submitting, setSubmitting] = useState(false);
  const [selectedFile, setSelectedFile] = useState(null);

  // Load food & categories
  useEffect(() => {
    const loadData = async () => {
      try {
        const resFood = await Apis.get(endpoints["food-detail"](foodId));
        setFood(resFood.data);

        const resCate = await authApis().get(endpoints["food-categories"]);
        setCategories(resCate.data);
      } catch (err) {
        console.error(err);
      } finally {
        setLoading(false);
      }
    };
    loadData();
  }, [foodId]);

  if (loading || !food) return <MySpinner />;

  const handleChange = (e) => {
    const { name, value } = e.target;
    if (name === "categoryId") {
      setFood((prev) => ({ ...prev, categoryId: { id: parseInt(value) } }));
    } else if (name === "price" || name === "quantity") {
      setFood((prev) => ({ ...prev, [name]: Number(value) }));
    } else {
      setFood((prev) => ({ ...prev, [name]: value }));
    }
  };

  const handleFileChange = (e) => {
    if (e.target.files.length > 0) {
      setSelectedFile(e.target.files[0]);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setSubmitting(true);
    try {
      const formData = new FormData();
      formData.append("name", food.name);
      formData.append("description", food.description);
      formData.append("price", food.price);
      formData.append("quantity", food.quantity);
      formData.append("categoryId", food.categoryId.id);
      if (selectedFile) {
        formData.append("file", selectedFile);
      }

      await authApis().put(endpoints["update-food"](foodId), formData, {
        headers: { "Content-Type": "multipart/form-data" },
      });

      alert("Cập nhật món ăn thành công!");
      navigate("/menu");
    } catch (err) {
      console.error(err);
      alert("Cập nhật thất bại!");
    } finally {
      setSubmitting(false);
    }
  };

  return (
    <div className="container my-4">
      <h2>Cập nhật món ăn</h2>
      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label className="form-label">Tên món</label>
          <input
            type="text"
            className="form-control"
            name="name"
            value={food.name}
            onChange={handleChange}
            required
          />
        </div>

        <div className="mb-3">
          <label className="form-label">Mô tả</label>
          <textarea
            className="form-control"
            name="description"
            value={food.description}
            onChange={handleChange}
            rows={3}
            required
          />
        </div>

        <div className="mb-3">
          <label className="form-label">Giá</label>
          <input
            type="number"
            className="form-control"
            name="price"
            value={food.price}
            onChange={handleChange}
            required
          />
        </div>

        <div className="mb-3">
          <label className="form-label">Số lượng</label>
          <input
            type="number"
            className="form-control"
            name="quantity"
            value={food.quantity}
            onChange={handleChange}
            required
          />
        </div>

        <div className="mb-3">
          <label className="form-label">Hình ảnh hiện tại</label>
          {food.image && (
            <div className="mb-2">
              <img
                src={food.image}
                alt={food.name}
                style={{ width: "200px", objectFit: "cover" }}
              />
            </div>
          )}
          <input
            type="file"
            className="form-control"
            accept="image/*"
            onChange={handleFileChange}
          />
        </div>

        <div className="mb-3">
          <label className="form-label">Danh mục</label>
          <select
            className="form-select"
            name="categoryId"
            value={food.categoryId?.id || ""}
            onChange={handleChange}
            required
          >
            <option value="">Chọn danh mục</option>
            {categories.map((c) => (
              <option key={c.id} value={c.id}>
                {c.name}
              </option>
            ))}
          </select>
        </div>

        <button className="btn btn-success" type="submit" disabled={submitting}>
          {submitting ? "Đang cập nhật..." : "Cập nhật"}
        </button>
      </form>
    </div>
  );
};

export default FoodEdit;
