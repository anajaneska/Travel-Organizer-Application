import axios from 'axios';

const axiosAccommodation = axios.create({
  baseURL: 'http://localhost:9091/api/amadeus',
});

export default axiosAccommodation;
