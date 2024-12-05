import { createRouter, createWebHistory } from 'vue-router';
import Home from '../views/Home.vue';
import Items from '../views/Items.vue';
import ShoppingLists from '../views/ShoppingLists.vue';
import Users from '../views/Users.vue';

const routes = [
  { path: '/', name: 'Home', component: Home },
  { path: '/items', name: 'Items', component: Items },
  { path: '/shopping-lists', name: 'ShoppingLists', component: ShoppingLists },
  { path: '/users', name: 'Users', component: Users },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;