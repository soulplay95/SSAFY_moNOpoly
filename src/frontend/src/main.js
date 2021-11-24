/*!

=========================================================
* BootstrapVue Argon Dashboard - v1.0.0
=========================================================

* Product Page: https://www.creative-tim.com/product/bootstrap-vue-argon-dashboard
* Copyright 2020 Creative Tim (https://www.creative-tim.com)

* Coded by www.creative-tim.com

=========================================================

* The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

*/
import Vue from 'vue';
import DashboardPlugin from './plugins/dashboard-plugin';
import App from './App.vue';
import VueKakaSdk from 'vue-kakao-sdk'
import axios from 'axios'
// router setup
import router from './routes/router';
import store from './store'
// plugin setup.
Vue.use(DashboardPlugin);
const apiKey = "335d6fe4a1069dc015a529d2faf8f75c"
Vue.use(VueKakaSdk,{apiKey})
Vue.prototype.$axios = axios
Vue.prototype.$baseUrl = "https://i5a209.p.ssafy.io:12346/"

/* eslint-disable no-new */
new Vue({
  el: '#app',
  store,
  render: h => h(App),

  router
});
