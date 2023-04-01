import Vue from 'vue'
import VueRouter from "vue-router";
import Layout from "@/layout/Layout";
import Login from "@/views/Login";
import HomeView from "@/views/User";
import Register from "@/views/Register";
import user from "@/views/User";
import User from "@/views/User";
import Book from "@/views/Book";
import Person from "@/views/Person";
import Email from "@/views/Email";
import Test from "@/views/Test";
import UserLogin from "@/views/UserLogin";

Vue.use(VueRouter)

const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}
const routes = [
  {
    path: '/',
    name: 'layout',
    // redirect:'/login',
    redirect:'/userLogin',
    component: Layout,
    children:[
      {
        path: '/user',
        name: 'user',
        component: User,
      },
      {
        path: '/book',
        name: 'book',
        component: Book,
      },{
        path: '/person',
        name: 'person',
        component: Person,
      }
    ]
  },
  {
    path: '/login',
    name: 'login',
    component: Login
  },
  {
    path: '/userLogin',
    name: 'UserLogin',
    component: UserLogin
  },
  {
    path: '/register',
    name: 'Register',
    component: Register
  },
  {
    path: '/email',
    name: 'Email',
    component: Email
  },
  {
    path: '/test',
    name: 'Test',
    component:Test
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
