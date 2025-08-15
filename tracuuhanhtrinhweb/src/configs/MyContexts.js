import { createContext } from "react";

export const MyUserContext = createContext(); //Lưu trạng thái toàn cục của user
export const MyDispatcherContext = createContext(); //Gửi tín hiệu cho reducer cập nhật state