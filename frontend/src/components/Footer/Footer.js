import './Footer.css'

const Footer = () => (
  <footer className="text-white p-5 mt-10">
    <div className="max-w-4xl mx-auto container p-4">
      <h3 className="text-xl mb-2 mt-4">Contact Us</h3>
      <form className="d-grid gap-2 justify-content-start mt-4">
        <input type="text" placeholder="Your Name" className="p-2 m-0 rounded text-black" />
        <input type="email" placeholder="Your Email" className="p-2 m-0 rounded text-black" />
        <textarea placeholder="Message..." className="p-2 m-0 rounded text-black" rows="3" />
        <button className="bg-blue-500 m-0 text-white rounded hover:bg-blue-600">
          Send
        </button>
      </form>
    </div>
  </footer>
);

export default Footer;
