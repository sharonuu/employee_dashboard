<template>
  <layout-div>
    <div class="container">
      <h2 class="text-center mt-5 mb-3">Employee List</h2>
      <!-- <div class="card"> -->
      <!-- <div class="card-body"> -->

      <!-- <div class="mb-3 flex">
                        <input 
                        type="text"
                        v-model="searchQuery"
                        class="form-control w-50 mx-auto"
                        placeholder="first name">
                        <button @click="executeSearch" class="btn btn-outline-primary mx-1">Search</button>
                    </div> -->
      <table class="table table-bordered table-hover">
        <thead>
          <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Salary</th>
            <th width="240px">Action</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="e in employees" :key="e.employeeId">
            <td>{{ e.employeeFirstName }}</td>
            <td>{{ e.employeeLastName }}</td>
            <td>${{ formatCurrency(e.employeeSalary) }}</td>
            <td>
              <router-link
                :to="`/edit/${e.employeeId}`"
                class="btn btn-outline-success mx-1"
                >Edit</router-link
              >
              <button
                @click="handleDelete(e.employeeId)"
                className="btn btn-outline-danger mx-1"
              >
                Delete
              </button>
            </td>
          </tr>
        </tbody>
      </table>
      <div class="card-button">
        <router-link to="/create" class="btn btn-outline-primary"
          >Add new employee
        </router-link>
      </div>
    </div>
    <!-- </div> -->
    <!-- </div> -->
  </layout-div>
</template>

<script>
import LayoutDiv from "./LayoutDiv.vue";
import apiService from "@/services/apiService";
import Swal from "sweetalert2";

export default {
  name: "EmployeeList",

  components: {
    LayoutDiv,
  },

  data() {
    return {
      employees: [],
    };
  },

  created() {
    this.retrieveEmployees();
  },

  methods: {
    /*
            function for normalizing the salary currency
            @param: salary value
            @output: $100,000
        */
    formatCurrency(value) {
      return new Intl.NumberFormat("en-US", { style: "decimal" }).format(value);
    },

    /*
            function for get all employees
            @param
            @output: all employees
        */
    retrieveEmployees() {
      apiService
        .fetchEmployees()
        .then((response) => {
          this.employees = response.data;
          return response.data;
        })
        .catch((error) => {
          return error;
        });
    },

    /*
            function for delete single employee
            @param: employee id
            @output: successfully delete message or errors
        */
    handleDelete(employeeId) {
      Swal.fire({
        title: "Are you sure?",
        text: "You won't be able to revert this!",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Yes, delete it!",
      }).then((result) => {
        if (result.isConfirmed) {
          apiService
            .deleteEmployee(employeeId)
            .then((response) => {
              Swal.fire({
                icon: "success",
                title: "Employee deleted successfully!",
                showConfirmButton: false,
                timer: 1500,
              });
              this.retrieveEmployees();
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
        }
      });
    },
  },
};
</script>
