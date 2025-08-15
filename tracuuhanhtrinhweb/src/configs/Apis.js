import axios from "axios";
import cookie from "react-cookies";

const BASE_URL = 'http://localhost:8080/SpringJourneyApp/api/';

export const endpoints = {
    'foods': '/foods',
    'register': '/users',
    'login': '/login',
    'current-user': '/secure/profile'
}

export const authApis = () => { //Dùng để gọi các api cần chứng thực
    return axios.create({
        baseURL: BASE_URL,
        headers: {
            'Authorization': `Bearer ${cookie.load('token')}`
        }
    })
}

export default axios.create({
    baseURL: BASE_URL
});

