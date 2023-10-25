<template>
  <div class="home">
    <div class="p-4">
      <h3 lass="mt-5 mb-3" style="color:darkcyan;">Employee Salary Analysis</h3>
      <div class="card">

        <!-- Card Header -->
        <div class="card-header">
          >>see more
        </div>

        <!-- Card Body -->
        <div class="card-body">
          <div class="row">

            <!-- All Employees Card -->
            <div class="col-md-3">
              <div class="card">
                <div class="card-body text-center">
                  <h5>All Employees</h5>
                  <span class="badge badge-info" style="font-size:x-large;">{{ total }}</span>
                </div>
              </div>
            </div>

            <!-- Maximum Salary Card -->
            <div class="col-md-3">
              <div class="card">
                <div class="card-body text-center">
                  <h5>Maximum Salary</h5>
                  <span class="badge badge-info" style="font-size:x-large;">{{ maximum }}</span>
                </div>
              </div>
            </div>

            <!-- Minimum Salary Card -->
            <div class="col-md-3">
              <div class="card">
                <div class="card-body text-center">
                  <h5>Minimum Salary</h5>
                  <span class="badge badge-info" style="font-size:x-large;">{{ minimum }}</span>
                </div>
              </div>
            </div>

            <!-- Average Salary Card -->
            <div class="col-md-3">
              <div class="card">
                <div class="card-body text-center">
                  <h5>Average Salary</h5>
                  <span class="badge badge-info" style="font-size:x-large;">{{ average }}</span>
                </div>
              </div>
            </div>

          </div>
        </div>
      </div>
    </div>
  </div>
</template>



<script>
import { ref, onMounted } from 'vue';
import apiService from "@/services/apiService";

export default {
  name: "widgetList",

  setup() {
    const employees = ref([]);
    const maximum = ref(0);
    const minimum = ref(0);
    const average = ref(0);
    const total = ref(0);

    const totalEmployees = ref(employees.value.length);

    const retrieveEmployees = () => {
      apiService
        .fetchEmployees()
        .then((response) => {
          employees.value = response.data;
        })
        .catch((error) => {
          console.error("Error fetching employees:", error);
        });
    };

    const getAnalyze = () => {
      apiService.analyze().then(
        (response) => {
          maximum.value = response.data.maxSalary;
          minimum.value = response.data.minSalary;
          average.value = response.data.avgSalary;
          total.value = response.data.totalEmployees;
        })
        .catch((error) => {
          console.error("Error analyzing data:", error);
        });
    };

    onMounted(() => {
      retrieveEmployees();
      getAnalyze();
    });

    return {
      employees,
      maximum,
      minimum,
      average,
      total,
      totalEmployees,
      retrieveEmployees,
      getAnalyze
    };
  }
};
</script>