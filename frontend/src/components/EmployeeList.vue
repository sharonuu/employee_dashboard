<template>
  <layout-div>
    <div class="container">
    <h3 class="mt-5 mb-3" style="color:darkcyan;">Employee List</h3> 
      <div class="card">
        <div class="card-header d-flex align-items-center justify-content-between">
            <form class="d-flex ms-auto align-items-center" role="search">
                        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                        <button class="btn btn-outline-success" type="submit">Search</button>
            </form>
            <router-link to="/create" class="btn mb-2 icon-large" style="margin-right: 1rem;">
              <i class="bi bi-person-fill-add"></i>
            </router-link>
        </div>
        <div class="card-body">
          <table class="table table-hover" id="employee-table" >
            <thead>
              <tr>
                <th>EmpId</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Salary</th>
                <th width="240px">Action</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="e in employees" :key="e.employeeId">
                <td>{{ e.employeeId }}</td>
                <td>{{ e.employeeFirstName }}</td>
                <td>{{ e.employeeLastName }}</td>
                <td>${{ formatCurrency(e.employeeSalary) }}</td>
                <td>
                  <router-link
                    :to="`/edit/${e.employeeId}`"
                    class="btn btn-outline-success mx-1"
                  >Edit</router-link>
                  <button
                    @click="handleDelete(e.employeeId)"
                    class="btn btn-outline-danger mx-1"
                  >
                    Delete
                  </button>
                </td>
              </tr>
            </tbody>
          </table>
          <div class="card-footer">
            <pagination
              v-model="currentPage"
              :total-rows="totalEmployees"
              :per-page="perPage"
              aria-controls="employee-table"
            ></pagination>
            <p class="mt-3">Current Page: {{ currentPage }}</p>
          </div>
        </div>
      </div>
    </div>
  </layout-div>
</template>

<script>
import LayoutDiv from "./LayoutDiv.vue";
import apiService from "@/services/apiService";
import Swal from "sweetalert2";

export default {
  name: "EmployeeList",

  components: {
    LayoutDiv
  },

  data() {
    return {
      employees: [],
      perPage: 3,
      currentPage: 1,
      isLoading: false
    };
  },
  computed: {
    totalEmployees() {
      return this.employees.length;
    }
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
      this.isLoading = true;
      apiService
        .fetchEmployees()
        .then((response) => {
          this.employees = response.data;
          // Swal.fire("Success", "Employees fetched successfully", "success");
        })
        .catch((error) => {
          // Swal.fire("Error", "Failed to fetch employees", "error");
          console.error("Error fetching employees:", error);
        })
        .finally(() => {
          this.isLoading = false;
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
        confirmButtonText: "Yes, delete it!"
      }).then((result) => {
        if (result.isConfirmed) {
          apiService
            .deleteEmployee(employeeId)
            .then(() => {
              Swal.fire("Success", "Employee deleted successfully!", "success");
              this.retrieveEmployees();
            })
            .catch((error) => {
              Swal.fire("Error", "Failed to delete employee", "error");
              console.error("Error deleting employee:", error);
            });
        }
      });
    }
  }
};
</script>

<style scoped>

.card {
  border-radius: 10px;
}

.card-footer {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.btn-outline-primary, .btn-outline-success, .btn-outline-danger {
  margin: 0.2rem;
}

.icon-large {
    font-size: 2rem; 
}
</style>
