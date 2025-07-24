const Footer = () => (
  <footer className="bg-gray-800 text-white p-4 mt-10">
    <div className="max-w-4xl mx-auto">
      <h3 className="text-xl mb-2">Contact Us</h3>
      <form className="flex flex-col gap-2">
        <input type="text" placeholder="Your Name" className="p-2 rounded text-black" />
        <input type="email" placeholder="Your Email" className="p-2 rounded text-black" />
        <textarea placeholder="Message..." className="p-2 rounded text-black" rows="3" />
        <button className="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600">
          Send
        </button>
      </form>
    </div>
  </footer>
);

export default Footer;
