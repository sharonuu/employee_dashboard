<template>
  <layout-div>
    <div class="container">
    <h3 class="mt-5 mb-3" style="color:darkcyan;">Employee List</h3>
    <div class="form-check">
      <input class="form-check-input" type="checkbox" v-model="activeCheck" id="activeCheck" checked>
      <label class="form-check-label mr-5" for="activeCheck">
        ACTIVE
      </label>
      <input class="form-check-input" type="checkbox" v-model="inactiveCheck" id="inactiveCheck" checked>
      <label class="form-check-label mr-5" for="inactiveCheck">
        INACTIVE
      </label>
      <input class="form-check-input" type="checkbox" v-model="onLeaveCheck" id="onLeaveCheck" checked>
      <label class="form-check-label mr-5" for="onLeaveCheck">
        ON LEAVE
      </label>
    </div>
      <div class="card">
        <div class="card-header d-flex align-items-center justify-content-between">
            <form class="d-flex ms-auto align-items-center" role="search" @submit.prevent="getSearchRes">
                <select v-model="selectedCategory" class="form-control me-2" aria-label="Select Category">
                    <option selected value="employeeId">empId</option>
                    <option value="employeeFirstName">First Name</option>
                    <option value="employeeLastName">Last Name</option>
                    <option value="employeeSalary">Salary</option>
                    <option value="employeePos">Position</option>
                </select>
                <input v-model="searchTerm" class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success" type="submit">Search</button>
            </form>
            <div >
              <router-link to="/create" class="btn mb-2 icon-large">
                  <i class="bi bi-person-fill-add"></i>
              </router-link>
              <div class="btn-group btn-group-toggle" data-toggle="buttons">
                  <label class="btn btn-primary active" @click="showTableView">
                      <input type="radio" name="btnradio" id="btnradio1" autocomplete="off" checked> Table
                  </label>
                  <label class="btn btn-primary" @click="showGridView">
                      <input type="radio" name="btnradio" id="btnradio2" autocomplete="off"> Grid
                  </label>
              </div>
            </div>

        </div>
        <div class="card-body">
          <!-- Table view -->
          <table v-if="!isGridView" class="table table-hover" id="employee-table card-control" >
            <thead>
              <tr>
                <th>EmpId</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Salary</th>
                <th>Position</th>
                <th>Status</th>
                <th width="240px">Action</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="e in employees" :key="e.employeeId">
                <td>{{ e.employeeId }}</td>
                <td>{{ e.employeeFirstName }}</td>
                <td>{{ e.employeeLastName }}</td>
                <td>${{ formatCurrency(e.employeeSalary) }}</td>
                <td>{{ e.employeePos }}</td>
                <td><span :class="['badge', 'rounded-pill', getStatusClass(e.employeeStatus)]">{{ e.employeeStatus }}</span></td>
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

          <div class="row" v-else>
              <div class="col-md-4 mb-4" v-for="e in employees" :key="e.employeeId">
                  <div class="card h-100">
                      <div class="card-body">
                          <h5 class="card-title">{{ e.employeeFirstName }} {{ e.employeeLastName }}</h5>
                          <p class="card-text">${{ formatCurrency(e.employeeSalary) }}</p>
                          <p class="card-text">{{ e.employeePos }}</p>
                          <span :class="['badge', 'rounded-pill', getStatusClass(e.employeeStatus)]">{{ e.employeeStatus }}</span>
                      </div>
                      <div class="card-footer">
                          <router-link :to="`/edit/${e.employeeId}`" class="btn btn-outline-success mx-1">Edit</router-link>
                          <button @click="handleDelete(e.employeeId)" class="btn btn-outline-danger mx-1">Delete</button>
                      </div>
                  </div>
              </div>
          </div>


          <div class="card-footer">
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
import { ref, onMounted, watchEffect } from 'vue';



export default {
  name: "EmployeeList",

  components: {
    LayoutDiv
  },

  data() {
    return {
      perPage: 3,
      currentPage: 1,
      isLoading: false
    };
  },


  setup() {
    const selectedCategory = ref('employeeId');
    const searchTerm = ref('');
    const employees = ref([]);
    const activeCheck = ref(true);
    const inactiveCheck = ref(true);
    const onLeaveCheck = ref(true);
    const isGridView = ref(false);

    const showTableView = () => {
      isGridView.value = false;
    }

    const showGridView = () => {
      isGridView.value = true;
    }


    const getSearchRes = async () => {
      try {
        if (searchTerm.value) {
          const response = await apiService.getSearch({
            params: {
              [selectedCategory.value]: searchTerm.value
            }
          });
          console.log("search ans",response.data);
          employees.value = response.data;

        } else {
          retrieveEmployees();
        }
      } catch (error) {
        console.error('Error:', error);
      }
    };


      /*
            function for get all employees
            @param
            @output: all employees
        */
      const retrieveEmployees = async () => {
      try {
        let fetchedEmployees = []

        if (activeCheck.value && inactiveCheck.value && onLeaveCheck.value) {
          const response = await apiService.fetchEmployees();
          fetchedEmployees = response.data;
        } else {
          if (activeCheck.value) {
            const activeEmployees = await apiService.getActiveEmployees();
            fetchedEmployees = [...fetchedEmployees, ...activeEmployees.data];
          }

          if (inactiveCheck.value) {
            const inactiveEmployees = await apiService.getInactiveEmployees();
            fetchedEmployees = [...fetchedEmployees, ...inactiveEmployees.data];
          }

          if (onLeaveCheck.value) {
            const onLeaveEmployees = await apiService.getOnleaveEmployees();
            fetchedEmployees = [...fetchedEmployees, ...onLeaveEmployees.data];
          }
        }
        employees.value = fetchedEmployees;
        console.log("get all data", fetchedEmployees)
      } catch (error) {
        console.error('Error fetching employees:', error);
      }
    };

    onMounted(() => {
      retrieveEmployees();
    });

    watchEffect(() => {
      retrieveEmployees();
    });

      return {
        employees,
        selectedCategory,
        searchTerm,
        getSearchRes,
        retrieveEmployees,
        activeCheck,
        inactiveCheck,
        onLeaveCheck,
        showGridView,
        showTableView,
        isGridView
    }

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
    },
    getStatusClass(status) {
        switch(status) {
            case 'active':
                return 'bg-green';
            case 'inactive':
                return 'bg-grey';
            case 'on leave':
                return 'bg-yellow';
            default:
                return '';
        }
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

.grid-view {
    display: flex;
    flex-wrap: wrap;
    gap: 1rem;
}

.employee-card {
    flex: 1 0 calc(33.333% - 1rem);
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    padding: 1rem;
    border: 1px solid #e1e1e1;
}

</style>
