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
              <span class="badge badge-info">
                {{totalEmployees}}
              </span>
            </router-link>
          </div>
          <div class="col-md-2">
            <router-link
              to="/"
              class="btn btn-primary p-4 dashboard-widget"
            >
              Create New Table <i class="fa fa-plus-circle" />
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