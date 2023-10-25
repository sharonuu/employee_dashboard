<template>
  <layout-div>
    <h2 class="text-center mt-5 mb-3">Create new employee</h2>
    <div class="card">
      <div class="card-header">
        <router-link class="btn btn-outline-info float-right" to="/"
          >View All Employee
        </router-link>
      </div>
      <div class="card-body">
        <form>
          <div class="form-group">
            <label htmlFor="firstname">EmpId</label>
            <input
              v-model="employee.employeeId"
              type="text"
              class="form-control"
              id="EmpId"
              name="EmpId"
            />
            <label htmlFor="firstname">first name</label>
            <input
              v-model="employee.employeeFirstName"
              type="text"
              class="form-control"
              id="firstname"
              name="firstname"
            />
            <label htmlFor="lastname">last name</label>
            <input
              v-model="employee.employeeLastName"
              type="text"
              class="form-control"
              id="lastname"
              name="lastname"
            />
            <label htmlFor="salary">salary</label>
            <input
              v-model="employee.employeeSalary"
              type="text"
              class="form-control"
              id="salary"
              name="salary"
            />
            <label htmlFor="position">position</label>
            <input
              v-model="employee.employeePos"
              type="text"
              class="form-control mb-4"
              id="position"
              name="position"
            />      
            <label htmlFor="status">Status</label>
            <select v-model="employee.employeeStatus" class="form-select mr-1" aria-label="status">
              <option selected value="ACTIVE">ACTIVE</option>
              <option value="INACTIVE">INACTIVE</option>
              <option value="ON_LEAVE">ON LEAVE</option>
            </select>
          </div>

          <button
            @click="handleSave()"
            :disabled="isSaving"
            type="button"
            class="btn btn-outline-primary mt-3"
          >
            Save
          </button>
        </form>
      </div>
    </div>
  </layout-div>
</template>

<script>
import apiService from "@/services/apiService";
import LayoutDiv from "./LayoutDiv.vue";
import Swal from "sweetalert2";

export default {
  name: "EmployeeCreate",
  
  components: {
    LayoutDiv,
  },
  
  data() {
    return {
      employee: {
        employeeId: "",
        employeeFirstName: "",
        employeeLastName: "",
        employeeSalary: "",
        employeePos: "",
        employeeStatus: "ACTIVE"
      },
      isSaving: false, // Flag to check if currently saving data
    };
  },
  
  methods: {

    /*
      Check if the input form is valid:
      1. Fields are not empty.
      2. First name and Last name don't have numbers or special characters.
      3. Salary is a positive number.
    */
    isValidForm() {
      // Check if essential fields are empty
      if (!this.employee.employeeId.trim() || !this.employee.employeeFirstName.trim() || !this.employee.employeeLastName.trim()) {
        Swal.fire({
          icon: "warning",
          title: "Please fill in all the fields!",
          showConfirmButton: true,
        });
        return false;
      }

      // Check if names have numbers or special characters
      if (/[0-9!@#$%^&*()_+\-=\\[\]{};':"\\|,.<>\\/?]+/.test(this.employee.employeeFirstName) || /[0-9!@#$%^&*()_+\-=\\[\]{};':"\\|,.<>\\/?]+/.test(this.employee.employeeLastName)) {
        Swal.fire({
          icon: "warning",
          title: "Names should not contain numbers or special characters!",
          showConfirmButton: true,
        });
        return false;
      }

      // Check if salary is valid
      if (isNaN(this.employee.employeeSalary) || this.employee.employeeSalary < 0) {
        Swal.fire({
          icon: "warning",
          title: "Salary should be a positive number!",
          showConfirmButton: true,
        });
        return false;
      }

      return true;
    },
    
    /*
      Handles saving of the employee data to an API.
      Checks form validity before saving.
      Resets the form and provides user feedback on success or failure.
    */
    handleSave() {
      // Check if form is valid before saving
      if (!this.isValidForm()) {
        return;
      }

      this.isSaving = true;

      apiService.createEmployee(this.employee)
        .then((response) => {
          // On successful API call, show success message and reset the form
          Swal.fire({
            icon: "success",
            title: "Employee information saved successfully!",
            showConfirmButton: false,
            timer: 1500,
          });

          this.isSaving = false;
          this.employee.employeeId = "";
          this.employee.employeeFirstName = "";
          this.employee.employeeLastName = "";
          this.employee.employeeSalary = "";
          return response;
        })
        .catch((error) => {
          // On failed API call, show error message
          this.isSaving = false;
          Swal.fire({
            icon: "error",
            title: "An Error Occured!",
            showConfirmButton: false,
            timer: 1500,
          });
          return error;
        });
    },
  },
};
</script>
