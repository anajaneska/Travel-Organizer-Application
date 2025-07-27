import instance from '../custom-axios/axios'

const TravelAppService = {
    fetchAccommodations: () => {
        return instance.get("/accommodations/all");
    },
    fetchTrips: () => {
        return instance.get("/trips");
    },
    createTrip: (tripDto) => {
    return instance.post('/trips', tripDto);
    },
    fetchTripById: (tripId) => {
        return instance.get(`/trips/${tripId}`);
    },
    deleteTrip: (tripId) => {
        return instance.delete(`/trips/${tripId}`);
    }
}

export default TravelAppService;