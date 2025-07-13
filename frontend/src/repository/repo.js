import axios from '../custom-axios/axios'

const TravelAppService = {
    fetchAccommodations: () => {
        return axios.get("/accommodations");
    },
    fetchTrips: () => {
        return axios.get("/trips");
    }
}

export default TravelAppService;