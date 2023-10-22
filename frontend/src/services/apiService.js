import axios from 'axios';

const API_URL = 'http://localhost:8080'; // replace this with your Spring Boot backend URL

export default {
  fetchEmployees() {
    return axios.get(`${API_URL}/employee`);
  },

  createEmployee(employee) {
    return axios.post(`${API_URL}/employee`, employee);
  },

  updateEmployee(id, employee) {
    return axios.put(`${API_URL}/employee/${id}`, employee);
  },

  deleteEmployee(id) {
    return axios.delete(`${API_URL}/employee/${id}`);
  }
};
