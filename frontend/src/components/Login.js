import { useState } from 'react';
import instance from '../custom-axios/axios'

export default function Login() {
  const [form, setForm] = useState({ username: '', password: '' });

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await instance.post('/auth/login', form);
      const token = response.data; // assuming backend returns JWT token as plain text or JSON string
      localStorage.setItem('jwt', token);
      alert("Logged in successfully");
      // optionally redirect or update UI after login
    } catch (err) {
      alert("Login failed");
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>Login</h2>
      <input
        type="text"
        placeholder="Username"
        onChange={(e) => setForm({ ...form, username: e.target.value })}
      />
      <input
        type="password"
        placeholder="Password"
        onChange={(e) => setForm({ ...form, password: e.target.value })}
      />
      <button type="submit">Login</button>
    </form>
  );
}
