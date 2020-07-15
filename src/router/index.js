import Vue from "vue";
import VueRouter from "vue-router";
import Search from "../views/Search.vue";
import ResultDisplay from "../components/ResultDisplay.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Search",
    component: Search
    // component: ResultDisplay
  },
  {
    path: "/search",
    name: "ResultDisplay",
    // component: Search
    component: ResultDisplay
  }
];

const router = new VueRouter({
  routes
});

export default router;
