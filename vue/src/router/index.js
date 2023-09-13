import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import Login from "@/components/Login.vue";
import User from "@/components/User.vue";
import Information from "@/components/Information.vue";
import Order from "@/components/Order.vue";
import Register from "@/components/Register.vue";
import Brand from "@/components/Brand.vue";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path: '/login',
    name: 'login',
    component: Login
  },
  {
    path: '/user',
    name: 'user',
    component: User
  },
  {
    path: '/information',
    name: 'information',
    component: Information
  },
  {
    path: '/order',
    name: 'order',
    component: Order
  },
  {
    path: '/order',
    name: 'order',
    component: Order
  },
  {
    path: '/brand',
    name: 'brand',
    component: Brand
  },
  {
    path: '/register',
    name: 'register',
    component: Register
  }
]

const router = new VueRouter({
  routes
})

export default router
