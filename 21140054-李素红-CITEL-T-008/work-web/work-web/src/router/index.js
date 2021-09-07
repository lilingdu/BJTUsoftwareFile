import Vue from 'vue'
import Router from 'vue-router'

import Home from '@/components/Home'
//import Home from '../pages/home'
import Update from '../components/update'
import Index from '../components/index'
import All from '../components/all'
import Result from '../components/findResult'

Vue.use(Router);

export default new Router({
  mode: 'history',//去掉#号
  routes: [
    {
      path: '/',   //path配置路由的路径
      name: 'Home',    //映射的组件所对应的name
      component: Home   //配置映射的组件
    },{
      path: '/home',
      name: 'Home',
      component: Home
    },
    {
      path: '/update',
      name: 'Update',
      component: Update
    },
    {
      path: '/index',
      name: 'Index',
      component: Index
    },
    {
      path: '/all',
      name: 'All',
      component: All
    },
    {
      path: '/result',
      name: 'Result',
      component: Result
    }
  ]
})
