// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import Router from 'vue-router'//引入路由
import axios from 'axios'
import Index from '@/components/index'

import ElementUI from 'element-ui';
import router from './router'  // 引入自己配置的路由信息
import store from './store'  // 引入自己配置的路由信息


import 'element-ui/lib/theme-chalk/index.css';
Vue.config.productionTip = false;
Vue.use(ElementUI);
Vue.use(Router);
Vue.use(store);
const originalPush = Router.prototype.push;
Router.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
};
/* eslint-disable no-new */

Vue.prototype.$http = axios;// 将axios赋值给Vue原型的$http属性，这样所有vue实例都可使用该对象

/*const router = new Router({
 mode: 'history',
  routes:routers
});*/
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
});
