<template>
    <div class="home">
      <div class="p-4">
        <div class="row">
          <div class="col-md-2">
            <router-link
              to="/"
              class="btn btn-primary p-4 dashboard-widget"
            >
              All Employees
              <br>
              <span class="badge badge-info" style="font-size:x-large;">
                {{totalEmployees}}
              </span>
            </router-link>
          </div>
          <div class="col-md-2">
            <router-link
              to="/"
              class="btn btn-warning p-4 dashboard-widget"
            >
              Average Salary
              <br>
              <span class="badge badge-info" style="font-size:x-large;">
                {{totalEmployees}}
              </span>
            </router-link>
          </div>
          <div class="col-md-2">
            <router-link
              to="/"
              class="btn btn-success p-4 dashboard-widget"
            >
              Minimum Salary
              <br>
              <span class="badge badge-info" style="font-size:x-large;">
                {{totalEmployees}}
              </span>
            </router-link>
          </div>
          <div class="col-md-2">
            <router-link
              to="/"
              class="btn btn-danger p-4 dashboard-widget"
            >
              Maximum Salary
              <br>
              <span class="badge badge-info" style="font-size:x-large;">
                {{totalEmployees}}
              </span>
            </router-link>
          </div>
          <div class="col-md-2">
            <router-link
              to="/"
              class="btn btn-info p-4 dashboard-widget"
            >
              Median Salary
              <br>
              <span class="badge badge-info" style="font-size:x-large;">
                {{totalEmployees}}
              </span>
            </router-link>
          </div>
        </div>
      </div>
      <div>
      </div>
    </div>
  </template>
  
  <script>
  import apiService from "@/services/apiService";

  export default {
    name: "widgetList",  
    data() {
        return {
            employees: []
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
    },
  };
</script>
<style>
  .dashboard-widget {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    min-height: 150px; /* Adjust this value as needed */
}

</style>