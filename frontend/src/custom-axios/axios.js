import axios from "axios";

const instance = axios.create({
  baseURL: 'http://localhost:9091/api',
  headers: {
    'Content-Type': 'application/json',
  },
  withCredentials: false, // or true if you're using cookies
});

export default instance;