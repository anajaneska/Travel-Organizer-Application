import instance from '../custom-axios/axios'

const TravelAppService = {
    fetchAccommodations: (tripId) => {
        return instance.get(`accommodations/trips/${tripId}`);
    },
    fetchActivities: (tripId) => {
        return instance.get(`activities/trips/${tripId}`);
    },
    fetchTransportations: (tripId) => {
        return instance.get(`transportations/trips/${tripId}`);
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
    editTrip: (tripId, tripDetails) => {
        return instance.put(`/trips/${tripId}`)
    },
    deleteTrip: (tripId) => {
        return instance.delete(`/trips/${tripId}`);
    },
}

export default TravelAppService;