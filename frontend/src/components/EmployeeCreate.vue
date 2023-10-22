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
        employeeFirstName: "",
        employeeLastName: "",
        employeeSalary: 0.0,
      },
      isSaving: false,
    };
  },
  
  methods: {
    isValidForm() {
      if (!this.employee.employeeFirstName.trim() || !this.employee.employeeLastName.trim()) {
      Swal.fire({
        icon: "warning",
        title: "Please fill in all the fields!",
        showConfirmButton: true,
      });
      return false;
    }

      if (/[0-9!@#$%^&*()_+\-=\\[\]{};':"\\|,.<>\\/?]+/.test(this.employee.employeeFirstName) || /[0-9!@#$%^&*()_+\-=\\[\]{};':"\\|,.<>\\/?]+/.test(this.employee.employeeLastName)) {
      Swal.fire({
        icon: "warning",
        title: "Names should not contain numbers or special characters!",
        showConfirmButton: true,
      });
      return false;
    }

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
  

    handleSave() {
      if (!this.isValidForm()) {
        return;
      }
      this.isSaving = true;
      apiService
        .createEmployee(this.employee)
        .then((response) => {
          Swal.fire({
            icon: "success",
            title: "Employee information saved successfully!",
            showConfirmButton: false,
            timer: 1500,
          });
          this.isSaving = false;
          this.employee.firstname = "";
          this.employee.lastname = "";
          this.employee.salary = "";
          return response;
        })
        .catch((error) => {
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
