import axios from "axios";

const BASE_URL = 'http://localhost:8080/SpringJourneyApp/api/';

export const endpoints = {
    'foods': '/foods',
    'register': '/users',
    'login': '/login',
    'current-user': '/secure/profile'
}

export default axios.create({
    baseURL: BASE_URL
})

