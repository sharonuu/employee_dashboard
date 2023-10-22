<template>
  <layout-div>
    <h2 class="text-center mt-5 mb-3">Edit employee</h2>
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
  name: "EmployeeEdit",
  
  components: {
    LayoutDiv,
  },

  data() {
    return {
      employee: {
        firstname: "",
        lastname: "",
        salary: 0,
      },
      isSaving: false,
    };
  },

  created() {
    const id = this.$route.params.id;
    apiService
      .updateEmployee(id, this.employee)
      .then((response) => {
        let employeeInfo = response.data;
        this.employee.firstname = employeeInfo.employeeFirstName;
        this.employee.lastname = employeeInfo.employeeLastName;
        this.employee.salary = employeeInfo.employeeSalary;
        return response;
      })
      .catch((error) => {
        Swal.fire({
          icon: "error",
          title: "An Error Occured!",
          showConfirmButton: false,
          timer: 1500,
        });
        return error;
      });
  },

  methods: {
    handleSave() {
      this.isSaving = true;
      const id = this.$route.params.id;
      apiService
        .updateEmployee(id, this.employee)
        .then((response) => {
          Swal.fire({
            icon: "success",
            title: "Employee information updated successfully!",
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
