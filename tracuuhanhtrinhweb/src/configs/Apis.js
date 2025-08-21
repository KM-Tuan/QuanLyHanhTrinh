import axios from "axios";
import cookie from "react-cookies";

const BASE_URL = 'http://localhost:8080/SpringJourneyApp/api/';

export const endpoints = {
    'foods': '/foods',
    'journeys': '/journeys',
    'register': '/users',
    'login': '/login',
    'current-user': '/secure/profile',
    'track-journey': '/track-journey',
    'station': (stationId) => `/stations/${stationId}`,
    'journey-stations': (journeyName) => `/journeys/${journeyName}/stations`,
    'station-services': (stationId) => `/stations/${stationId}/services`
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

