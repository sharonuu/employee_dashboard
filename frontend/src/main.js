import { createApp } from "vue";
import App from "./App.vue";
import { createRouter, createWebHistory } from "vue-router";
import "bootstrap-icons/font/bootstrap-icons.css";
import EmployeeEdit from "./components/EmployeeEdit";
import EmployeeList from "./components/EmployeeList";
import EmployeeCreate from "./components/EmployeeCreate";
// import 'bootstrap/dist/css/bootstrap.css'
// import 'bootstrap-vue/dist/bootstrap-vue.css'


const router = createRouter({
  history: createWebHistory(),
  routes: [
    { path: "/", component: EmployeeList },
    { path: "/create", component: EmployeeCreate },
    { path: "/edit/:id", component: EmployeeEdit },
  ],
});
createApp(App).use(router).mount("#app");
