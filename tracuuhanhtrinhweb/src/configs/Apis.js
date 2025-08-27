import axios from "axios";
import cookie from "react-cookies";

const BASE_URL = 'http://localhost:8080/SpringJourneyApp/api/';

export const endpoints = {
    'foods': '/foods',
    'journeys': '/journeys',
    'journey': '/journey',
    'register': '/users',
    'login': '/login',
    'current-user': '/secure/profile',
    'track-journey': '/track-journey',
    'station': (stationId) => `/stations/${stationId}`,
    'journey-stations': (journeyName) => `/journeys/${journeyName}/stations`,
    'station-services': (stationId) => `/stations/${stationId}/services`,
    'service-register': '/service-register',
    'my-service': (userId) => `/service-register/user/${userId}`,
    'my-food': (userId) => `/cart/user/${userId}`,
    'cart': '/cart',

    // Thống kê
    'ordered-json': '/statistics/most-ordered',
    'ordered-csv': '/statistics/most-ordered/csv',
    'ordered-pdf': '/statistics/most-ordered/pdf',
    'total-day-json': '/statistics/day',
    'total-month-json': '/statistics/month',
    'total-year-json': '/statistics/year',
    'total-day-csv': '/statistics/day/csv',
    'total-month-csv': '/statistics/month/csv',
    'total-year-csv': '/statistics/year/csv',
    'total-day-pdf': '/statistics/day/pdf',
    'total-month-pdf': '/statistics/month/pdf',
    'total-year-pdf': '/statistics/year/pdf',
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

