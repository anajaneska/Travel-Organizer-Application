import instance from '../custom-axios/axios'

const TravelAppService = {
    fetchAccommodations: () => {
        return instance.get("/accommodations/all");
    },
    fetchTrips: () => {
        return instance.get("/trips");
    },
     createTrip: (tripDto) => {
    return instance.post('/trips/create', tripDto);
  },
}

export default TravelAppService;