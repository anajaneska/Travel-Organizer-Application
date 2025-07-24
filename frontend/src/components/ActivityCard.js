const ActivityCard = ({ activity }) => (
  <div className="border p-4 rounded shadow">
    <h3 className="text-lg font-semibold">{activity.title}</h3>
    <p>{activity.description}</p>
    <p>{activity.time}</p>
  </div>
);

export default ActivityCard;
