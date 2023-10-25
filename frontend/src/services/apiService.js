import axios from 'axios';

const API_URL = 'http://localhost:8080/api/employees'; // replace this with your Spring Boot backend URL

export default {
  fetchEmployees() {
    return axios.get(`${API_URL}/`);
  },

  getEmployeeById(id) {
    return axios.get(`${API_URL}/${id}`);
  },

  createEmployee(employee) {
    return axios.post(`${API_URL}/save`, employee);
  },

  updateEmployee(id, employee) {
    return axios.put(`${API_URL}/${id}`, employee);
  },

  deleteEmployee(id) {
    return axios.delete(`${API_URL}/${id}`);
  },

  analyze() {
    return axios.get(`${API_URL}/analyze`)
  },

  getActiveEmployees() {
    return axios.get(`${API_URL}/active`)
  },

  getInactiveEmployees() {
    return axios.get(`${API_URL}/inactive`)
  },

  getOnleaveEmployees() {
    return axios.get(`${API_URL}/on_leave`)
  },

  getSearch(searchParams) {
    const queryParams = new URLSearchParams();

    if (searchParams.params.employeeId) {
      console.log('employeeId', searchParams.params.employeeId)
      queryParams.append('employeeId', searchParams.params.employeeId);
    }

    if (searchParams.params.employeeFirstName) {
      queryParams.append('employeeFirstName', searchParams.params.employeeFirstName);
    }

    if (searchParams.params.employeeLastName) {
      queryParams.append('employeeLastName', searchParams.params.employeeLastName);
    }

    if (searchParams.params.employeeSalary) {
      queryParams.append('employeeSalary', searchParams.params.employeeSalary);
    }

    if (searchParams.params.employeePos) {
      queryParams.append('employeePos', searchParams.params.employeePos);
    }

    const fullUrl = `${API_URL}/search?${queryParams.toString()}`;
    return axios.get(fullUrl);

  }
  
};
