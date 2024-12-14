import { createWebHistory, createRouter } from 'vue-router'

import HomePage from "../components/HomePage.vue";
import ListDetail from "../components/ListDetail.vue";

const routes = [
    { path: '/', component: HomePage },
    { path: "/list/:id", name: "ListDetail", component: ListDetail },
]

const router = createRouter({
    history: createWebHistory(),
    routes,
})

export default router