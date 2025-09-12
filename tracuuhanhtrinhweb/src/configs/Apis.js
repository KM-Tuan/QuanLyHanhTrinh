import axios from "axios";
import cookie from "react-cookies";

const BASE_URL = 'http://localhost:8080/SpringJourneyApp/api/';

export const endpoints = {
    // Journey
    'journeys': '/journeys',
    'journey': '/journey',
    'track-journey': '/track-journey',
    'station': (stationId) => `/stations/${stationId}`,
    'journey-stations': (journeyName) => `/journeys/${journeyName}/stations`,

    // Service
    'station-services': (stationId) => `/stations/${stationId}/services`,
    'service-register': '/service-register',
    'my-service': (userId) => `/service-register/user/${userId}`,

    // Food
    'foods': '/foods',
    'food-categories': '/food-categories',
    'food-detail': (foodId) => `/foods/${foodId}`,
    'update-food': (foodId) => `/foods/update/${foodId}`,
    'food-decrease': (foodId) => `/foods/${foodId}/decrease-quantity`,
    'food-increase': (foodId) => `/foods/${foodId}/increase-quantity`,
    'cart': '/cart',
    'my-food': (userId) => `/cart/user/${userId}`,

    // User
    'register': '/users',
    'verify-otp': '/users/verify-otp',
    'login': '/login',
    'current-user': '/secure/profile',

    // Statistic
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

