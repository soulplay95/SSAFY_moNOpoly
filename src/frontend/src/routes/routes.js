import DashboardLayout from '@/views/Layout/DashboardLayout.vue';
import AuthLayout from '@/views/Pages/AuthLayout.vue';
import MainPage from '@/views/Pages/MainPage.vue'
import store from '../store/index.js'
import axios from 'axios'
import NotFound from '@/views/NotFoundPage.vue';

const baseUrl = "https://i5a209.p.ssafy.io:12346/"
const routes = [
  {
    path :'/',
    redirect : '/home'


  },
  {
    path: '/dashboard',
    redirect: 'dashboard',
    component: DashboardLayout,
    children: [
      {
        path: '/dashboard',
        name: '실시간 좌석 정보',
        beforeEnter: (to,from,next) =>{
          if(store.getters['library/getLibraryId']==null)
          {
            next('/home')
          }

          next()

        },
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import(/* webpackChunkName: "demo" */ '../views/Dashboard.vue')
      },
      {
        path: '/mislaid',
        name: 'mislaid',
        beforeEnter: (to,from,next) =>{
          if(store.getters['library/getLibraryId']==null)
          {
            next('/home')
          }

          next()

        },
        component: () => import(/* webpackChunkName: "demo" */ '../views/Mislaid.vue')
      },
      {
        path: '/profile',
        name: 'profile',
        beforeEnter: (to,from,next) =>{
          if(store.getters['user/getUserId']==null)
          {
            next('/home')
          }

          next()

        },
        component: () => import(/* webpackChunkName: "demo" */ '../views/Pages/UserProfile.vue')
      },
      {
        path: '/maps',
        name: 'maps',
        component: () => import(/* webpackChunkName: "demo" */ '../views/GoogleMaps.vue')
      },
      {
        path: '/noticeBoard',
        name: 'noticeBoard',
        beforeEnter: (to,from,next) =>{
          if(store.getters['library/getLibraryId']==null)
          {
            next('/home')
          }

          next()

        },
        component: () => import(/* webpackChunkName: "demo" */ '../views/NoticeBoard.vue')
      },
      {
        path: '/noticeDetail/:notice_id',
        name: 'noticeDetail',
        beforeEnter: (to,from,next) =>{
          if(store.getters['library/getLibraryId']==null)
          {
            next('/home')
          }

          next()

        },
        component: () => import(/* webpackChunkName: "demo" */ '../views/NoticeDetail.vue')
      },
      {
        path: '/noticeCreate',
        name: 'noticeCreate',
        beforeEnter: (to,from,next) =>{

          if(store.getters['library/getLibraryId']==null)
          {
            next('/home')
          }
          else if(store.getters['user/getUserId']==null)
          {

            next('/home')
          }
          else
          {
            axios.get(baseUrl+'admin/'+store.getters['library/getLibraryId'])
            .then(res => {

              if(res.data[0].id != store.getters['user/getUserId'])
              {
                next('/home')
              }
              else{

                next()
              }
            })


          }


        },
        component: () => import(/* webpackChunkName: "demo" */ '../views/NoticeCreate.vue')
      },
      {
        path: '/noticeEdit/:notice_id',
        name: 'noticeEdit',
        beforeEnter: (to,from,next) =>{

          if(store.getters['library/getLibraryId']==null)
          {
            next('/home')
          }
          else if(store.getters['user/getUserId']==null)
          {

            next('/home')
          }
          else
          {
            axios.get(baseUrl+'admin/'+store.getters['library/getLibraryId'])
            .then(res => {

              if(res.data[0].id != store.getters['user/getUserId'])
              {
                next('/home')
              }
              else{

                next()
              }
            })


          }


        },
        component: () => import(/* webpackChunkName: "demo" */ '../views/NoticeEdit.vue')
      },
      {
        path: '/tables',
        name: 'tables',
        component: () => import(/* webpackChunkName: "demo" */ '../views/RegularTables.vue')
      },
      {
          path: '/admin',
          name: '관리자 좌석관리',
          beforeEnter: (to,from,next) =>{

            if(store.getters['library/getLibraryId']==null)
            {
              next('/home')
            }
            else if(store.getters['user/getUserId']==null)
            {

              next('/home')
            }
            else
            {
              axios.get(baseUrl+'admin/'+store.getters['library/getLibraryId'])
              .then(res => {

                if(res.data[0].id != store.getters['user/getUserId'])
                {
                  next('/home')
                }
                else{

                  next()
                }
              })


            }


          },
          component: () => import('../views/DashboardAdmin.vue')


      },
      {
        path: '/seatAll',
        name: '관리자 좌석현황',
        beforeEnter: (to,from,next) =>{

          if(store.getters['library/getLibraryId']==null)
          {
            next('/home')
          }
          else if(store.getters['user/getUserId']==null)
          {

            next('/home')
          }
          else
          {
            axios.get(baseUrl+'admin/'+store.getters['library/getLibraryId'])
            .then(res => {

              if(res.data[0].id != store.getters['user/getUserId'])
              {
                next('/home')
              }
              else{

                next()
              }
            })


          }


        },
        component: () => import('../views/DashboardAdminOverall.vue')


    }
    ]
  },
  {
    path: '/login',
    redirect: 'login',
    component: AuthLayout,
    children: [

      {
        path : '/home',
        name : 'home',
        component :() => import('../views/Pages/MainPage.vue')


      },
      { path: '*', component: NotFound }
    ]
  }
];

export default routes;
